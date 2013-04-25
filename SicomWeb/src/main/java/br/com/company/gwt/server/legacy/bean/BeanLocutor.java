package br.com.company.gwt.server.legacy.bean;

import java.io.Serializable;

public class BeanLocutor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String lcncodg;
	private String lccnome;
	
	public String getLcncodg() {
		return lcncodg;
	}
	public void setLcncodg(String lcncodg) {
		this.lcncodg = lcncodg;
	}
	public String getLccnome() {
		return lccnome;
	}
	public void setLccnome(String lccnome) {
		this.lccnome = lccnome;
	}	

}
