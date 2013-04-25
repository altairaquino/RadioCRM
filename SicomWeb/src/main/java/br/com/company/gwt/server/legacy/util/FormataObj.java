package br.com.company.gwt.server.legacy.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormataObj {

	private static final String COUNTRY = "BR";
	private static final String LANGUAGE = "pt";
		
	private static final String FORMAT_DATE_BR = "dd/MM/yyyy";
	private static final String FORMAT_MOEDA_BR = "R$ ##,##0.00";
	private static final String FORMAT_NUMERO_BR = "##,##0.00";
	
	Locale local = new Locale(LANGUAGE,COUNTRY);
	
	private SimpleDateFormat formataData;
	private DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(local);
	private DecimalFormat formataNumero;
	
	public String getDiaSemanaPorExtenso(Date data){
		String ret = null;
		if (data != null){
			formataData = new SimpleDateFormat("EEEEEE",local);
			ret = formataData.format(data);
		}
		return ret; 
	}
	
	public String getValorMoedaPorExtenso(float numero){
		return Extenso.converteMoeda(formataValor(numero));
	}
	
	public String formataValorMoeda(float valor){
		String ret = null;
		try {
			formataNumero = new DecimalFormat(FORMAT_MOEDA_BR, formatSymbols);
			ret = formataNumero.format(valor);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ret; 
	}
	
	public String formataValor(float valor){
		String ret = null;
		try {
			formataNumero = new DecimalFormat(FORMAT_NUMERO_BR, formatSymbols);
			ret = formataNumero.format(valor);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return ret; 
	}
	
	public String getMesPorExtenso(Date data){
		String ret = null;
		if (data != null){
			formataData = new SimpleDateFormat("MMMMM",local);
			ret = formataData.format(data);
		}
		return ret; 
	}
	
	public String getDataPorExtensoComDia(Date data){
		String ret = null;
		if (data != null){
			formataData = new SimpleDateFormat("EEEEEE ',' dd 'de' MMMMM 'de' yyyy'.'",local);
			ret = formataData.format(data);
		}
		return ret; 
	}
	
	public String getDataPorExtenso(Date data){
		String ret = null;
		if (data != null){
			formataData = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy'.'",local);
			ret = formataData.format(data);
		}
		return ret;
	}
	
	public String formataData(Date data){
		String ret = null;
		if (data != null){
			formataData = new SimpleDateFormat(FORMAT_DATE_BR,local);
			ret = formataData.format(data);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(new FormataObj().getValorMoedaPorExtenso(2345.56f));
	}
	
}
