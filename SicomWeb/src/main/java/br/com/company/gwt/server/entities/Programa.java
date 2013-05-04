package br.com.company.gwt.server.entities;

public class Programa {
	
	private Integer id;
	private String nome;
	private Float valorInsercao;
	private Integer duracaoInsercao;
	private Boolean ativo;
	
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
	public Float getValorInsercao() {
		return valorInsercao;
	}
	public void setValorInsercao(Float valorInsercao) {
		this.valorInsercao = valorInsercao;
	}
	public Integer getDuracaoInsercao() {
		return duracaoInsercao;
	}
	public void setDuracaoInsercao(Integer duracaoInsercao) {
		this.duracaoInsercao = duracaoInsercao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}