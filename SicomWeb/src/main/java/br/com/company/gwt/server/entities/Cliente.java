package br.com.company.gwt.server.entities;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {
	
	@Id
	@SequenceGenerator(name = "seq", sequenceName = "sq_cliente_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	private Integer id;
	
	@Column(length=50)
	private String nome;
	
	@Column(name="razao_social", length=50)
	private String razaoSocial;
	
	@Column(length=50)
	private String segmento;
	
	@Column(length=50)
	private String email;

	@Column(length=15)
	private String fone;
	
	@Column(name="tipo_pessoa")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;

	@Column(name="data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Agencia agencia;
	
	@Column(unique=true, length=20)
	private String documento;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private TipoLogradouro tipoLogradouro;
	
	@Column(length=50)
	private String logradouro;
	
	@Column(length=50)
	private String complemento;
	
	@Column(length=15)
	private String cep;
	
	@Column(length=40)
	private String bairro;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Cidade cidade;
	
	@Column(name="nome_contato", length=50)
	private String nomeContato;
	
	@Column(name = "fone_contato", length=15)
	private String foneContato;
	
	@Column(name="cell_contato", length=15)
	private String cellContato;
	
	@Column(name="data_nascimento_contato")
	@Temporal(TemporalType.DATE)
	private Date dataNascimentoContato;
	
	@Column(name="nome_proprietario", length=50)
	private String nomeProprietario;
	
	@Column(name="data_nascimento_proprietario")
	@Temporal(TemporalType.DATE)
	private Date dataNascimentoProprietario;
	
	@Column(nullable=false, columnDefinition="boolean default true")
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getFoneContato() {
		return foneContato;
	}

	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}

	public String getCellContato() {
		return cellContato;
	}

	public void setCellContato(String cellContato) {
		this.cellContato = cellContato;
	}

	public Date getDataNascimentoContato() {
		return dataNascimentoContato;
	}

	public void setDataNascimentoContato(Date dataNascimentoContato) {
		this.dataNascimentoContato = dataNascimentoContato;
	}

	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public Date getDataNascimentoProprietario() {
		return dataNascimentoProprietario;
	}

	public void setDataNascimentoProprietario(Date dataNascimentoProprietario) {
		this.dataNascimentoProprietario = dataNascimentoProprietario;
	}
	
}