package br.com.company.gwt.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="forma_pagamento")
public class FormaPagamento {
	
	@Id
	private Integer id;
	
	@Column(length=40)
	private String nome;
	
	@Column(name="tem_permuta", columnDefinition="boolean default false")
	private Boolean temPermuta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getTemPermuta() {
		return temPermuta;
	}

	public void setTemPermuta(Boolean temPermuta) {
		this.temPermuta = temPermuta;
	}
	
}