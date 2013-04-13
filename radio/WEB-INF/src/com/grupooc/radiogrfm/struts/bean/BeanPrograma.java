package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanPrograma implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String pgncodg;
	private String pgcdesc;
	private String pgyvalr;
	private String pgndurc;
	private String pgncgep;
	private String pgcnmep;
	private String pglativ;	
	
	public String getPgncgep() {
		return pgncgep;
	}
	public void setPgncgep(String pgncgep) {
		this.pgncgep = pgncgep;
	}
	public String getPgcnmep() {
		return pgcnmep;
	}
	public void setPgcnmep(String pgcnmep) {
		this.pgcnmep = pgcnmep;
	}
	public String getPglativ() {
		return pglativ;
	}
	public void setPglativ(String pglativ) {
		this.pglativ = pglativ;
	}
	public String getPgncodg() {
		return pgncodg;
	}
	public void setPgncodg(String pgncodg) {
		this.pgncodg = pgncodg;
	}
	public String getPgcdesc() {
		return pgcdesc;
	}
	public void setPgcdesc(String pgcdesc) {
		this.pgcdesc = pgcdesc;
	}
	public String getPgyvalr() {
		return pgyvalr;
	}
	public void setPgyvalr(String pgyvalr) {
		this.pgyvalr = pgyvalr;
	}
	public String getPgndurc() {
		return pgndurc;
	}
	public void setPgndurc(String pgndurc) {
		this.pgndurc = pgndurc;
	}

}
