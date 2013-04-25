package br.com.company.gwt.shared.bean;

import java.io.Serializable;
import java.util.Date;

public class ParametrosReport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nomeRelatorio;
	private Date dataIncio;
	private Date dataFim;
	private Integer codigoLoja;
	private Integer vendaId;
	private Integer lojaId;
	
	public ParametrosReport(){}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public Date getDataIncio() {
		return dataIncio;
	}

	public void setDataIncio(Date dataIncio) {
		this.dataIncio = dataIncio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		this.codigoLoja = codigoLoja;
	}

	public void setVendaId(Integer vendaId) {
		this.vendaId = vendaId;
	}
	
	public Integer getVendaId() {
		return vendaId;
	}

	public void setLojaId(Integer lojaId) {
		this.lojaId = lojaId;
	}

	public Integer getLojaId() {
		return lojaId;
	}

}