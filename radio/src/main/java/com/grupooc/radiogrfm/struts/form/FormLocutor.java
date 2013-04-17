package com.grupooc.radiogrfm.struts.form;

import org.apache.struts.action.ActionForm;

public class FormLocutor extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	private String lcncodg;
	private String lccdesc;
	
	public String getLcncodg() {
		return lcncodg;
	}
	public void setLcncodg(String lcncodg) {
		this.lcncodg = lcncodg;
	}
	public String getLccdesc() {
		return lccdesc;
	}
	public void setLccdesc(String lccdesc) {
		this.lccdesc = lccdesc;
	}	

}
