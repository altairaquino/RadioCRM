package br.com.company.gwt.server.entities;

import java.util.Date;

public class Contrato {
	
	private Integer id;
	
	private Cliente cliente;
	
	private Float valor;
	
	private Date dataCadastro;

	private Date dataCancelamento;

	public Integer getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
