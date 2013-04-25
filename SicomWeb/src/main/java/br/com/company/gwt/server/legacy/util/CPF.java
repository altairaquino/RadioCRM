package br.com.company.gwt.server.legacy.util;

public class CPF{
	
	private static final int TAM = 9;
	
	private static String dv(String s) {
		int dv1 = dvFatorX(s);
		return "" + dv1 + "" + dvFatorX(s + dv1);
	}
	
	private static int dvFatorX(String s){
		return Utils.mod11(Utils.fator(s, 2, s.length() + 1));
	}
	
	public static String gerar() {
		String s = "";
		for (int i = 0; i < TAM; i++) {
			s += (int) (Math.random() * 10);
		}
		return s + dv(s);
	}

	public static boolean validar(String s) {
		return s != null && Utils.isDigit(s) && s.length() == (TAM + 2)
				&& dv(s.substring(0, TAM)).equals(s.substring(TAM));
	}
	
}