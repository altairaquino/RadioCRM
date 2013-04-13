package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanMeses implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String msncodg;
	private String mscdesc;
	
	public String getMsncodg() {
		return msncodg;
	}
	public void setMsncodg(String msncodg) {
		this.msncodg = msncodg;
	}
	public String getMscdesc() {
		return mscdesc;
	}
	public void setMscdesc(String mscdesc) {
		this.mscdesc = mscdesc;
	}		

}
