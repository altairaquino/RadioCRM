package br.com.company.gwt.server.legacy.bean;

import java.io.Serializable;

public class BeanAgenciaContato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String acncodg;
	private String acncgct;
	private String accnmct;
	private String acncgag;
	private String accnmag;
	
	public String getAcncodg() {
		return acncodg;
	}
	public void setAcncodg(String acncodg) {
		this.acncodg = acncodg;
	}
	public String getAcncgct() {
		return acncgct;
	}
	public void setAcncgct(String acncgct) {
		this.acncgct = acncgct;
	}
	public String getAccnmct() {
		return accnmct;
	}
	public void setAccnmct(String accnmct) {
		this.accnmct = accnmct;
	}
	public String getAcncgag() {
		return acncgag;
	}
	public void setAcncgag(String acncgag) {
		this.acncgag = acncgag;
	}
	public String getAccnmag() {
		return accnmag;
	}
	public void setAccnmag(String accnmag) {
		this.accnmag = accnmag;
	}
	
}
