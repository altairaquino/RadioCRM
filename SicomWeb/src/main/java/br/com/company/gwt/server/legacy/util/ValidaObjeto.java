package br.com.company.gwt.server.legacy.util;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class ValidaObjeto {
	
	public static boolean validaInteiro(String numero){
		boolean ok = false;
		try {
			Integer.parseInt(numero);
			ok = true;
		} catch (Exception e) {
			ok = false;
		}
		return ok;
	}
	
	public static boolean validaFloat(String floatValue){
		boolean ok = false;
		try {
			Float.parseFloat(floatValue);
			ok = true;
		} catch (Exception e) {
			ok = false;
		}
		return ok;
	}
	
	public static boolean validaData(String data){
		boolean ret = false;
		try {
			if (data != null){
				if (data.length() == 10){
					if (new SimpleDateFormat("dd/MM/yyyy").parse(data)!=null)
						ret = true;
				}
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
		
	}
	
	public static boolean validaHora(String hora) throws IllegalArgumentException{
		boolean ret = false;
		try {
			if (hora != null){
				if (Time.valueOf(hora) != null){
					ret = true;
				}
			}
		} catch (Exception e) {
			ret = false;
		}
		return ret;		
	}
	
	public static boolean validaString(String str){
		if (str == null){
			return false;
		}else{
			return !str.equals("");
		}
	}
	
	public static String removeCharOfInteger(String str){
		String ret = "";
		if (str != null){
			ret = str.replace("/", "").replace("-", "").replace("(", "").replace(")", "").replace("_", "").replace(".", "").replace(",", "");
		}
		return ret;
	}

}
