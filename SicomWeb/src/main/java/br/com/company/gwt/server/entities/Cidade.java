package br.com.company.gwt.server.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cidade {
	
	@Id
	private Integer id;
	
	private String nome;
	
	private String uf;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}	

}