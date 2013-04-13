package com.grupooc.radiogrfm.struts.bean;

import java.io.Serializable;

public class BeanCidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cdncodg;
	private String cdcuf;
	private String cdcdesc;
	
	public String getCdncodg() {
		return cdncodg;
	}
	public void setCdncodg(String cdncodg) {
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
