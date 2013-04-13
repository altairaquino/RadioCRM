package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public final class BeanTipoPessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tpncodg;
	private String tpcdesc;
	private String tpctpdc;
	
	public String getTpncodg() {
		return tpncodg;
	}
	public void setTpncodg(String tpncodg) {
		this.tpncodg = tpncodg;
	}
	public String getTpcdesc() {
		return tpcdesc;
	}
	public void setTpcdesc(String tpcdesc) {
		this.tpcdesc = tpcdesc;
	}
	public String getTpctpdc() {
		return tpctpdc;
	}
	public void setTpctpdc(String tpctpdc) {
		this.tpctpdc = tpctpdc;
	}
	
}
