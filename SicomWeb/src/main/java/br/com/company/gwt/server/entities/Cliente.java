package br.com.company.gwt.server.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Cliente {
	
	@Id
	@SequenceGenerator(name = "seq", sequenceName = "sq_cliente_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	private Integer id;
	
	@Column(length=50)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	@Column(length=20)
	private String documento;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private TipoLogradouro tipoLogradouro;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Cidade cidade;
	
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

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}