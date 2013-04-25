package br.com.company.gwt.server.legacy.bean;

import java.io.Serializable;

public class BeanMidia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String mdncodg;
	private String mdcdesc;
	private String mdncgep;
	private String mdcnmep;
	private String mdlativ;	
	
	public String getMdncgep() {
		return mdncgep;
	}
	public void setMdncgep(String mdncgep) {
		this.mdncgep = mdncgep;
	}
	public String getMdcnmep() {
		return mdcnmep;
	}
	public void setMdcnmep(String mdcnmep) {
		this.mdcnmep = mdcnmep;
	}
	public String getMdlativ() {
		return mdlativ;
	}
	public void setMdlativ(String mdlativ) {
		this.mdlativ = mdlativ;
	}
	public String getMdncodg() {
		return mdncodg;
	}
	public void setMdncodg(String mdncodg) {
		this.mdncodg = mdncodg;
	}
	public String getMdcdesc() {
		return mdcdesc;
	}
	public void setMdcdesc(String mdcdesc) {
		this.mdcdesc = mdcdesc;
	}
	
		

}
