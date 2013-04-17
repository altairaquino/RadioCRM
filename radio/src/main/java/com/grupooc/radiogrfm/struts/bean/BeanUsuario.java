package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanUsuario implements Serializable{
	
	private static final long serialVersionUID = -7101847679210286297L;
	
	private String usncodg;
	private String uscnome;
	private String usclogn;
	private String uscsenh;
	private String usncgep;
	private String uscnmep;
	private String uslativ;	
	
	public String getUsncgep() {
		return usncgep;
	}
	public void setUsncgep(String usncgep) {
		this.usncgep = usncgep;
	}
	public String getUscnmep() {
		return uscnmep;
	}
	public void setUscnmep(String uscnmep) {
		this.uscnmep = uscnmep;
	}
	public String getUsncodg() {
		return usncodg;
	}
	public void setUsncodg(String usncodg) {
		this.usncodg = usncodg;
	}
	public String getUscnome() {
		return uscnome;
	}
	public void setUscnome(String uscnome) {
		this.uscnome = uscnome;
	}
	public String getUsclogn() {
		return usclogn;
	}
	public void setUsclogn(String usclogn) {
		this.usclogn = usclogn;
	}
	
	public String getUscsenh() {
		return uscsenh;
	}
	public void setUscsenh(String uscsenh) {
		this.uscsenh = uscsenh;
	}
	public String getUslativ() {
		return uslativ;
	}
	public void setUslativ(String uslativ) {
		this.uslativ = uslativ;
	}
	

}
