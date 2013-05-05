package br.com.company.gwt.server.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Programa {
	
	@Id
	@SequenceGenerator(name = "seq", sequenceName = "sq_programa_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	private Integer id;
	
	@Column(length=40)
	private String nome;
	
	private Float valorInsercao;
	private Integer duracaoInsercao;
	
	@Column(columnDefinition="boolean default true")
	private Boolean ativo;
	
	@OneToMany(mappedBy="programa", cascade=CascadeType.ALL)
	private List<ProgramacaoPrograma> programacao;
	
	public Programa() {
		setAtivo(true);
	}
	
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
	
	public Boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<ProgramacaoPrograma> getProgramacao() {
		return programacao;
	}

	public void setProgramacao(List<ProgramacaoPrograma> programacao) {
		this.programacao = programacao;
	}
	
}