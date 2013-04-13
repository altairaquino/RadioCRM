package com.grupooc.radiogrfm.struts.form;

import org.apache.struts.action.ActionForm;

public class FormMeta extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String mtncodg;
	private String mtncgct;
	private String mtnano;
	private String mtyacum;
	
	public String getMtyacum() {
		return mtyacum;
	}
	public void setMtyacum(String mtyacum) {
		this.mtyacum = mtyacum;
	}
	public String getMtncodg() {
		return mtncodg;
	}
	public void setMtncodg(String mtncodg) {
		this.mtncodg = mtncodg;
	}
	public String getMtncgct() {
		return mtncgct;
	}
	public void setMtncgct(String mtncgct) {
		this.mtncgct = mtncgct;
	}
	public String getMtnano() {
		return mtnano;
	}
	public void setMtnano(String mtnano) {
		this.mtnano = mtnano;
	}

}
