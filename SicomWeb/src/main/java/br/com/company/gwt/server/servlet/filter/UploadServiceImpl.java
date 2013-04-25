package br.com.company.gwt.server.servlet.filter;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServiceImpl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

		/*
		 * Extensão do arquivo passado como parametro para ser salvo.
		 */
		String extensaoFile = req.getParameter("ext");

		FileItem uploadItem = getFileItem(req);
		if (uploadItem == null) {
			resp.getWriter().write("Arquivo não encontrado.");
			return;
		}

		try {
			String fileUploadPath = this.getServletContext().getRealPath("") + "/uploadtemp/";
			String generatedFileName = Date.class.newInstance().getTime() + "."	+ (extensaoFile == null ? "csv" : extensaoFile);

			File uploadFile = new File(fileUploadPath, generatedFileName);
			uploadItem.write(uploadFile);
			resp.getWriter().write(generatedFileName);

		} catch (Exception e) {
			resp.getWriter().write("ERROR");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private FileItem getFileItem(HttpServletRequest req) {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> it = items.iterator();

			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (!item.isFormField()
						&& "uploadFormElement".equals(item.getFieldName())) {
					return item;
				}
			}
		} catch (FileUploadException e) {
			return null;
		}

		return null;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
