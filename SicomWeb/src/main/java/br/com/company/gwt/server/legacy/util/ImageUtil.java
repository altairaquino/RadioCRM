package br.com.company.gwt.server.legacy.util;

/* 
 * Copyright (c) 2003, Rafael Steil 
 * All rights reserved. 

 * Redistribution and use in source and binary forms,  
 * with or without modification, are permitted provided  
 * that the following conditions are met: 

 * 1) Redistributions of source code must retain the above  
 * copyright notice, this list of conditions and the  
 * following  disclaimer. 
 * 2)  Redistributions in binary form must reproduce the  
 * above copyright notice, this list of conditions and  
 * the following disclaimer in the documentation and/or  
 * other materials provided with the distribution. 
 * 3) Neither the name of "Rafael Steil" nor  
 * the names of its contributors may be used to endorse  
 * or promote products derived from this software without  
 * specific prior written permission. 
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT  
 * HOLDERS AND CONTRIBUTORS "AS IS" AND ANY  
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING,  
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF  
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR  
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL  
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE  
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,  
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES  
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF  
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,  
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER  
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER  
 * IN CONTRACT, STRICT LIABILITY, OR TORT  
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN  
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF  
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE 
 *  
 * This file creation date: 21/04/2004 - 19:54:16 
 * net.jforum.util.image.ImageUtils.java 
 * The JForum Project 
 * http://www.jforum.net 
 *  
 * $Id: ImageUtils.java,v 1.11 2004/05/04 00:59:44 rafaelsteil Exp $ 
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

/**
 * Utilities methods for image manipulation. It does not support writting of GIF
 * images, but it can read from. GIF images will be saved as PNG.
 * 
 * @author Rafael Steil
 */
public class ImageUtil {
	
	public static final int IMAGE_JPEG = 0;
	public static final int IMAGE_PNG = 1;

	/**
	 * Resizes an image
	 * 
	 * @param imgName
	 *            The image name to resize. Must be the complet path to the file
	 * @param maxWidth
	 *            The image's max width
	 * @param maxHeight
	 *            The image's max height
	 * @return A resized <code>BufferedImage</code>
	 * @throws IOException
	 *             If the file is not found
	 */
	public static BufferedImage resizeImage(String imgName, int type,
			int maxWidth, int maxHeight) throws IOException {
		return resizeImage(ImageIO.read(new File(imgName)), type, maxWidth,
				maxHeight);
	}

	/**
	 * Resizes an image.
	 * 
	 * @param image
	 *            The image to resize
	 * @param maxWidth
	 *            The image's max width
	 * @param maxHeight
	 *            The image's max height
	 * @return A resized <code>BufferedImage</code>
	 */
	public static BufferedImage resizeImage(Image image, int type,
			int maxWidth, int maxHeight) {
		// float zoom = 1.0F;
		Dimension largestDimension = new Dimension(maxWidth, maxHeight);

		// Original size
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);

		float aspectRation = (float) imageWidth / imageHeight;

		if (imageWidth > maxWidth || imageHeight > maxHeight) {
			// int scaledW = Math.round(imageWidth * zoom);
			// int scaledH = Math.round(imageHeight * zoom);

			// Dimension preferedSize = new Dimension(scaledW, scaledH);

			if ((float) largestDimension.width / largestDimension.height > aspectRation) {
				largestDimension.width = (int) Math
						.ceil(largestDimension.height * aspectRation);
			} else {
				largestDimension.height = (int) Math
						.ceil((float) largestDimension.width / aspectRation);
			}

			imageWidth = largestDimension.width;
			imageHeight = largestDimension.height;
		}

		return createBufferedImage(image, type, imageWidth, imageHeight);
	}

	/**
	 * Saves an image to the disk.
	 * 
	 * @param image
	 *            The image to save
	 * @param toFileName
	 *            The filename to use
	 * @param type
	 *            The image type. Use <code>ImageUtils.IMAGE_JPEG</code> to save
	 *            as JPEG images, or <code>ImageUtils.IMAGE_PNG</code> to save
	 *            as PNG.
	 * @return <code>false</code> if no appropriate writer is found
	 * @throws IOException
	 */
	public static boolean saveImage(BufferedImage image, String toFileName,
			int type) throws IOException {
		return ImageIO.write(image, type == IMAGE_JPEG ? "jpg" : "png",
				new File(toFileName));
	}

	/**
	 * Compress and save an image to the disk. Currently this method only
	 * supports JPEG images.
	 * 
	 * @param image
	 *            The image to save
	 * @param toFileName
	 *            The filename to use
	 * @param type
	 *            The image type. Use <code>ImageUtils.IMAGE_JPEG</code> to save
	 *            as JPEG images, or <code>ImageUtils.IMAGE_PNG</code> to save
	 *            as PNG.
	 * @param compress
	 *            Set to <code>true</code> if you want to compress the image.
	 * @return <code>false</code> if no appropriate writer is found
	 * @throws IOException
	 */
	public static void saveCompressedImage(BufferedImage image,
			String toFileName, int type) throws IOException {
		if (type == IMAGE_PNG) {
			throw new UnsupportedOperationException(
					"PNG compression not implemented");
		}

		ImageWriter writer = null;

		Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
		writer = (ImageWriter) iter.next();

		ImageOutputStream ios = ImageIO.createImageOutputStream(new File(
				toFileName));
		writer.setOutput(ios);

		ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());

		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality(0.7F);

		writer.write(null, new IIOImage(image, null, null), iwparam);

		ios.flush();
		writer.dispose();
		ios.close();
	}

	/**
	 * Creates a <code>BufferedImage</code> from an <code>Image</code>.
	 * 
	 * @param image
	 *            The image to convert
	 * @param w
	 *            The desired image width
	 * @param h
	 *            The desired image height
	 * @return The converted image
	 */
	public static BufferedImage createBufferedImage(Image image, int type,
			int w, int h) {
		if (type == ImageUtil.IMAGE_PNG && hasAlpha(image)) {
			type = BufferedImage.TYPE_INT_ARGB;
		} else {
			type = BufferedImage.TYPE_INT_RGB;
		}

		BufferedImage bi = new BufferedImage(w, h, type);

		Graphics g = bi.createGraphics();
		g.drawImage(image, 0, 0, w, h, null);
		g.dispose();

		return bi;
	}

	/**
	 * Determines if the image has transparent pixels.
	 * 
	 * @param image
	 *            The image to check for transparent pixel.s
	 * @return <code>true</code> of <code>false</code>, according to the result
	 * @throws InterruptedException
	 */
	public static boolean hasAlpha(Image image) {
		try {
			PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
			pg.grabPixels();

			return pg.getColorModel().hasAlpha();
		} catch (InterruptedException e) {
			return false;
		}
	}

	public static void main(String args[]) {
		Image img = null;
		try {
			img = new ImageIcon("C://virusderede.jpg").getImage();
		} catch (RuntimeException e1) {
			e1.printStackTrace();
		}

		try {
			ImageUtil.saveImage(ImageUtil.resizeImage(img,
					ImageUtil.IMAGE_JPEG, 100, 300), "C://4.jpg",
					ImageUtil.IMAGE_JPEG);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * @author Altair Aquino 
	 * @param path Path para a criação da imagem
	 * @param image Array de Bytes que compoem a imagem
	 * @return Gera uma imagem no disco e etorna o path com o nome do arquivo de imagem para ser
	 * exibido no cliente do gwt.
	 */
	public static String createImageFromBytes(String fileName, byte[] image){
		try {
			if (image == null){
				return null;
			}
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(image);
			FileDescriptor fd = fos.getFD();  
			fos.flush();
			fd.sync();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static byte[] getBytesFromImage(String fileIn, String fileOut) {
		byte[] photo = null;
		try {

			File file = new File(fileIn);
			BufferedImage imagem = ImageIO.read(file);
			int new_w = 320, new_h = 240;
			BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.SCALE_DEFAULT);
			Graphics2D g = new_img.createGraphics();
			g.drawImage(imagem, 0, 0, new_w, new_h, null);
			File out = new File(fileOut);
			ImageIO.write(new_img, "JPG", out);

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			FileInputStream fi = new FileInputStream(fileOut);
			byte buffer[] = new byte[fi.available()];
			int bytesRead = 0;
			while ((bytesRead = fi.read(buffer)) != -1) {
				arrayOutputStream.write(buffer, 0, bytesRead);
			}
			arrayOutputStream.close();

			photo = arrayOutputStream.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return photo;
	}

	public static byte[] getBytesFromImage(String fileIn, String fileOut, int height, int width) {
		byte[] photo = null;
		try {
			
			File file = new File(fileIn);
			BufferedImage imagem = ImageIO.read(file);
			int new_w = width, new_h = height;
			BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.SCALE_DEFAULT);
			Graphics2D g = new_img.createGraphics();
			g.drawImage(imagem, 0, 0, new_w, new_h, null);
			File out = new File(fileOut);
			ImageIO.write(new_img, "JPG", out);
			
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			FileInputStream fi = new FileInputStream(fileOut);
			byte buffer[] = new byte[fi.available()];
			int bytesRead = 0;
			while ((bytesRead = fi.read(buffer)) != -1) {
				arrayOutputStream.write(buffer, 0, bytesRead);
			}
			arrayOutputStream.close();
			
			photo = arrayOutputStream.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photo;
	}
	
}