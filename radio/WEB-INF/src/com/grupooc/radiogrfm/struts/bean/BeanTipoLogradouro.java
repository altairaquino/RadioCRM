package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanTipoLogradouro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String tlncodg;
	private String tlcdesc;
	
	public String getTlncodg() {
		return tlncodg;
	}
	public void setTlncodg(String tlncodg) {
		this.tlncodg = tlncodg;
	}
	public String getTlcdesc() {
		return tlcdesc;
	}
	public void setTlcdesc(String tlcdesc) {
		this.tlcdesc = tlcdesc;
	}
	
		

}
