package com.grupooc.radiogrfm.dao.Entity;

import java.io.Serializable;

public class Cidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer cdncodg;
	private String cdcuf;
	private String cdcdesc;
	
	public Integer getCdncodg() {
		return cdncodg;
	}
	public void setCdncodg(Integer cdncodg) {
		this.cdncodg = cdncodg;
	}
	public String getCdcuf() {
		return cdcuf;
	}
	public void setCdcuf(String cdcuf) {
		this.cdcuf = cdcuf;
	}
	public String getCdcdesc() {
		return cdcdesc;
	}
	public void setCdcdesc(String cdcdesc) {
		this.cdcdesc = cdcdesc;
	}

}
