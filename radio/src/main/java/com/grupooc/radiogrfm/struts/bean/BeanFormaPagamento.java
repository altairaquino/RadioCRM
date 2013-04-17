package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanFormaPagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fpncodg;
	private String fpcdesc;
	
	public String getFpncodg() {
		return fpncodg;
	}
	public void setFpncodg(String fpncodg) {
		this.fpncodg = fpncodg;
	}
	public String getFpcdesc() {
		return fpcdesc;
	}
	public void setFpcdesc(String fpcdesc) {
		this.fpcdesc = fpcdesc;
	}
	
		

}
