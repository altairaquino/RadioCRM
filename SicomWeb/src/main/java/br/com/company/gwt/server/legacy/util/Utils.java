package br.com.company.gwt.server.legacy.util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;


public class Utils {
	
	
	/**
	 * 
	 * @param valor Valor a preencher a String.
	 * @param tam Tamanho final da String preenchida.
	 * @param str String original.
	 * @return Retorna uma String modificada com o preenchimento a esquerda.
	 */
	
	public static String preencherEsquerda(char valor,int tam,String str){
		String ret = str;
		for (int i = 0; i < tam - str.length(); i++) {
			ret = "" + valor + ret;
		}
		return ret;
	}
	
	public static String converteFloatBR(String floatV){
		String x = "0";
		try {
			if (floatV != null && !floatV.equals("")){
				x = floatV.replace(".", "").replace(",", ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return x;
	}
	
	public static Time converteStringToTime(String time){
		Time x = null;
		try {
			if (time != null){
				x = Time.valueOf(time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return x;
	}
	
	/**
	 * @param tam Tamanho final da String.
	 * @param str String passada para modifica��o.
	 * @return Uma String completada com zeros a esquerda
	 * EX: zerosEsquerda(4,"45") e retorna "0045" 
	 */
	public static String zerosEsquerda(int tam,String str){
		return preencherEsquerda('0', tam, str);
	}
	
	
	
	public static String toStringCripto(String arg){
		String ret = "";
		try  {  
			   MessageDigest md = MessageDigest.getInstance("MD5");  
			   
			   md.update(arg.getBytes());  
			   BigInteger hash = new BigInteger(1, md.digest());  
			   ret = hash.toString(16);
			   
		}catch(NoSuchAlgorithmException ns)  {  
			ns.printStackTrace();  
		}
		return ret;
	}
	
	public static String criptografaSenha(String login, String senha){
		return toStringCripto(login+senha);
	}
	
	public static java.sql.Date stringToDateSQL(String datastr){
		java.sql.Date data = null;
		try {
			if(datastr != null && !datastr.equals(""))
			data = new Date( 
					new SimpleDateFormat("dd/MM/yyyy")
					.parse(datastr)
					.getTime()
			);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return data;
	}
	
	public static java.util.Date stringToTimeSQL(String datastr){
		java.util.Date data = null;
		try {
			if(datastr != null && !datastr.equals(""))
			data = new SimpleDateFormat("HH:mm")
					.parse(datastr);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return data;
	}	
		
	public static String getDataHoraAtual(){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
	}
	
	public static <T> T copyProperties(T dest, Object orig) throws Exception{
		BeanUtils.copyProperties(dest, orig);
		return dest;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T copyProperties2(T dest, Object orig) throws Exception{
		return dest != null ? copyProperties((T)copyProperties((dest.getClass()).newInstance(), dest), orig) : null;
	}

	public static Integer getInt(String value){
		Integer i = null;
			try {
				i = value != null && !value.equals("") && !value.equals("0") ? new Integer(value) : null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return i;
	}
			
	public static <T> Collection<T> getCollectionBean(Collection<T> collection, T obj0, int max){
		Iterator<T> iter = 
			(collection != null && !collection.isEmpty()) ?
				collection.iterator() : new ArrayList<T>().iterator();
		Collection<T> collection2 = new ArrayList<T>();
		for (int i = 0; i < max; i++) {
			collection2.add(iter.hasNext()? iter.next() : obj0);
		}
		return collection2;
	}
	
	@SuppressWarnings("unchecked")
	public static <T,E> Collection<E> getCollectionBean(Collection<T> collection, Class<E> class0, T obj0, int max) throws Exception{
		Iterator<T> iter = 
			(collection != null && !collection.isEmpty()) ?
				collection.iterator() : new ArrayList<T>().iterator();
		Collection<E> collection2 = new ArrayList<E>();
		Object object;
		for (int i = 0; i < max; i++) {
			object = class0.newInstance();
			BeanUtils.copyProperties(object, iter.hasNext()? iter.next() : obj0);
			collection2.add((E)object);
		}
		return collection2;
	}
	
	
	
	/*
	 public static <T> boolean setCollectionBean(
			Collection<T> alterado,Collection<T> original, Map<String, Object> map, AlterarObjCollection reAlt) throws Exception{
		//Collection<T> alterado,Collection<T> original, Map<String, Object> map, AlterarObjCollection reAlt) throws Exception{
		boolean fAlterado = false, aux;
		if(original == null){
			original = new ArrayList<T>();
		} 
		else{
			Iterator<T> itOrig = original.iterator();
			for (Iterator<T> itAlt = alterado.iterator(); itAlt.hasNext();) {
				T t0 =  itAlt.next();
				T t1 =  itOrig.hasNext() ? itOrig.next() :t0;
				BeanUtils.copyProperties(t0, t1);
			}
		}
		for (Iterator<T> iterator = alterado.iterator(); iterator.hasNext();) {
			if(fone.getFncdesc() != null && fone.getFnncgtf()!= null 
					&& fone.getFnncgtf().getTfncodg() != null){  
				fone.setFnncgpe(pessoa);
				if(fone.getFnncodg() == null)dao.persist(fone);
				fAlterado = true;
			}else iterator.remove();
			if(!(aux = reAlt.alterar(iterator.next(), map)))iterator.remove();
			fAlterado = fAlterado || aux; 
		}
		
		return fAlterado;
	}
	 
	 	 */
	
	
	public static <T> boolean setCollectionBean(
			Collection<T> alterado, Map<String, Object> map, AlterarObjCollection reAlt) throws Exception{
		boolean fAlterado = false, aux;
		for (Iterator<T> iterator = alterado.iterator(); iterator.hasNext();) {
			if(!(aux = reAlt.alterar(iterator.next(), map)))iterator.remove();
			fAlterado = fAlterado || aux; 
		}
		
		return fAlterado;
	}
	
	public interface AlterarObjCollection{
		public boolean alterar(Object element, Map<String, Object> map); 
	}
	
	public static String toCapitalizeCase(String str){
		String ret = "";
		if(str !=null && !str.equals("")){
			ret = str.substring(0,1).toUpperCase();
			if(str.length()> 1)
				ret += str.substring(1).toLowerCase();
		}
		return ret;
	}
	
	
	public static int fator(String s, int min, int max){
		int ret = 0;
		int peso = min;		
		char[] d = s.toCharArray();
		for (int i = d.length -1; i >= 0; i--) {
			ret += (d[i] - '0') * peso;
			peso = min + (peso + 1 - min) % (max + 1 - min); 
		}
		return ret;
	}
	
	public static int fator(String s){
		return fator(s, 2, 9);
	}	
	
	public static int mod11(int valor){
		valor = (valor % 11);
		return (valor > 1) ? 11 - valor : 0;
	}
	
	public static int dvMod11(String s){
		return mod11(fator(s));
	}
	
	public static boolean isDigit(String s){
		boolean ret = false;
		char[] d = s.toCharArray();
		for (int i = 0; !ret && i < d.length; i++) {
			ret = Character.isDigit(d[i]);
		}
		return ret;
	}
	
		
	public static String blobToStr(Blob blob){
		StringBuilder ret = new StringBuilder();
		try {
			InputStream in = blob.getBinaryStream();
			int lidos = 0;
			byte b[] = new byte[2048];
			String temp = null;
			while ((lidos = in.read(b)) != -1) {
				temp = new String(b, 0, lidos);
				ret.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret.toString();  
	}
	
	private static final String FORMAT_DATE_BR = "dd/MM/yyyy";
	private static final String FORMAT_TIMESTAMP_BR = "dd/MM/yyyy hh:mm:ss";
	private static final String FORMAT_FLOAT_BR = "#,##0.00";
	
	public static java.util.Date strBRToDate(String format,String value) throws ParseException{
		return value == null ? null : new SimpleDateFormat(format).parse(value);
	}
	
	public static java.util.Date strBRToDate(String value) throws ParseException{
		return strBRToDate(FORMAT_DATE_BR,value);
	}
		
	public static String dateToStrBR(String format,java.util.Date date){
		return date == null ? null : new SimpleDateFormat(format).format(date);
	}
	
	public static String dateToStrBR(java.util.Date date){
		return dateToStrBR(FORMAT_DATE_BR,date);
	}
	
	public static String timestampToStrBR(java.util.Date date){
		return dateToStrBR(FORMAT_TIMESTAMP_BR,date);
	}
	
	public static String getDateToStrBR(ResultSet rs, String value) throws SQLException{
		return dateToStrBR(rs.getDate(value));
	}			
	
	public static String getTimestampToStrBR(ResultSet rs, String value) throws SQLException{
		return timestampToStrBR(rs.getTimestamp(value));
	}
	
	public static String floatToStrBR(String format,Float value){
		return value == null ? null : new DecimalFormat(format).format(value);
	}
	
	public static String doubleToStrBR(String format, Double value){
		return value == null ? null : new DecimalFormat(format).format(value);
	}
	
	public static String longToStrBR(String format,Long value){
		return value == null ? null : new DecimalFormat(format).format(value);
	}
	
	public static String objectToStrBR(String format,Object value){
		return value == null ? null : new DecimalFormat(format).format(value);
	}
	
	public static String floatToStrBR(Float value){
		return floatToStrBR(FORMAT_FLOAT_BR,value);
	}
	
	public static String objectToStrBR(Float value){
		return objectToStrBR(FORMAT_FLOAT_BR,value);
	}
	
	public static String getFloatToStrBR(ResultSet rs, String value) throws SQLException{
		return floatToStrBR(rs.getFloat(value));
	}
	
	public static String getObjectToStrBR(ResultSet rs, String value) throws SQLException{
		return objectToStrBR(rs.getFloat(value));
	}
	
	public static String toCapitalize(String str){
		return toCapitazile(str, Locale.getDefault()); 
	}
	
	public static String toCapitazile(String str, Locale locale) {
		return (str == null || str.length() == 0 ? str : str.substring(0,1).toUpperCase(locale) + (str.length() == 1 ? "" : str.substring(1).toLowerCase(locale)));
	}
	
	public static <T> T getAtributo(Class<?> c, String atributo, Class<T> classTipo){
		T ret = null;
		try {
    		Method m = c.getMethod("get" + Utils.toCapitalize(atributo) , classTipo);
    		ret = classTipo.cast( m.invoke(c.newInstance()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return ret;
	}
	
	public static <T> T getAtributo(String s, String atributo, Class<T> classTipo){
		T ret = null;
		try {    		
    		ret = getAtributo(Class.forName(s), atributo, classTipo);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return ret;
	}
	
	public static <T> void setAtributo(T object, Class<T> c, String atributo, Class<?> classTipo, Object value){
		try {
    		Method m = c.getMethod("set" + Utils.toCapitalize(atributo) , classTipo);
    		m.invoke(object, value);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public static  <T> void setAtributoStr(T object, Class<T> class1, Class<?> classAtributo, ResultSet rs, String atributo){
		String str = "";
		try{
			if(classAtributo == java.util.Date.class || classAtributo == java.sql.Date.class){
				str = Utils.getDateToStrBR(rs, atributo);
			}
			else if(classAtributo == java.sql.Timestamp.class){
				str = Utils.getTimestampToStrBR(rs, atributo);
			}			
			else if(classAtributo == Float.class || classAtributo == Double.class || classAtributo == Long.class){
				str = Utils.getObjectToStrBR(rs, atributo);
			}
			else{
				str = rs.getString(atributo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		setAtributo(object,class1, atributo, String.class, str);
	}
	
	public static <T> List<T> getObjectsStr(PreparedStatement st, Class<T> class0){
		List<T> lista = new ArrayList<T>();
		try {
			ResultSetMetaData metaData = st.getMetaData();
			ResultSet rs = st.executeQuery();
			int colls = metaData.getColumnCount();
			while(rs.next()){
				T object = class0.newInstance();
				for (int i = 1; i <= colls; i++) {
					String className = metaData.getColumnClassName(i);
					String atributo = metaData.getColumnName(i);
					Utils.setAtributoStr(object, class0, Class.forName(className), rs, atributo);
				}
				lista.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista; 
	}

	public static <T> T nullRet(Object object, T ret){
		return object == null ? null : ret;
	}
	
	public static String isNull(String s, String s2, String s3){
		return (s == null || s.equals("")?s2:s3);
	}
	
	public static String isNull(String s){
		//return (s == null || s.equals("")?"":s);
		return isNull(s,"",s);
	}
	
}
