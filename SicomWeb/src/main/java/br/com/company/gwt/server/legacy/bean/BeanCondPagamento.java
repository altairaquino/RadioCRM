package br.com.company.gwt.server.legacy.bean;

import java.io.Serializable;

public class BeanCondPagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cpncodg;
	private String cpcdesc;
	
	public String getCpncodg() {
		return cpncodg;
	}
	public void setCpncodg(String cpncodg) {
		this.cpncodg = cpncodg;
	}
	public String getCpcdesc() {
		return cpcdesc;
	}
	public void setCpcdesc(String cpcdesc) {
		this.cpcdesc = cpcdesc;
	}
	
		

}
