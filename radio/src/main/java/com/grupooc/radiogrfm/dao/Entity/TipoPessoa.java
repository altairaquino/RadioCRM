package com.grupooc.radiogrfm.dao.Entity;

import java.io.Serializable;

public final class TipoPessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer tpncodg;
	private String tpcdesc;
	private String tpctpdc;
	
	public Integer getTpncodg() {
		return tpncodg;
	}
	public void setTpncodg(Integer tpncodg) {
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
