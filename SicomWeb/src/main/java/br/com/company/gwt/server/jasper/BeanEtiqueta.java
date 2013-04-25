package br.com.company.gwt.server.jasper;

import java.io.Serializable;

public class BeanEtiqueta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeLoja;
	private String nomeProduto;
	private Float preco;
	private String codigoInterno;
	
	public String getNomeLoja() {
		return nomeLoja;
	}
	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public String getCodigoInterno() {
		return codigoInterno;
	}
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

}