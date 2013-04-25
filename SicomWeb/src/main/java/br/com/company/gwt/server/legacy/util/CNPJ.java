package br.com.company.gwt.server.legacy.util;

public class CNPJ{
	
	private static final int TAM = 12;
	
	private static String dv(String s) {
		int dv1 = Utils.dvMod11(s);
		return "" + dv1 + "" + Utils.dvMod11(s + dv1);
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