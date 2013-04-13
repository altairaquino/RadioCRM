package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanOperacao implements Serializable{
	
	private static final long serialVersionUID = -6448245703932955062L;
	private String opncodg;
	private String opnpart;
	private String opcdesc;
	private String opchint;
	private String opclink;
	
	public String getOpncodg() {
		return opncodg;
	}
	public void setOpncodg(String opncodg) {
		this.opncodg = opncodg;
	}
	public String getOpnpart() {
		return opnpart;
	}
	public void setOpnpart(String opnpart) {
		this.opnpart = opnpart;
	}
	public String getOpcdesc() {
		return opcdesc;
	}
	public void setOpcdesc(String opcdesc) {
		this.opcdesc = opcdesc;
	}
	public String getOpchint() {
		return opchint;
	}
	public void setOpchint(String opchint) {
		this.opchint = opchint;
	}
	public String getOpclink() {
		return opclink;
	}
	public void setOpclink(String opclink) {
		this.opclink = opclink;
	}
	

}
