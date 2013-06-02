package br.com.company.gwt.server.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contrato {
	
	@Id
	@SequenceGenerator(name = "seq", sequenceName = "sq_contrato_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private TipoContrato tipoContrato;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy="contrato")
	private List<ProgramaContrato> programas;
	
	private Float valor;
	
	@Basic(optional=false)
	@Column(name="informacoes_texto", length=2000)
	private String informacoesTexto;
	
	@Column(name="percentual_comissao")
	private Float percentualComissao;
	
	@Column(name="percentual_permuta")
	private Float percentualPermuta;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name="data_inicio")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name="data_termino")
	@Temporal(TemporalType.DATE)
	private Date dataTermino;	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cancelamento")
	private Date dataCancelamento;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getInformacoesTexto() {
		return informacoesTexto;
	}

	public void setInformacoesTexto(String informacoesTexto) {
		this.informacoesTexto = informacoesTexto;
	}

	public Float getPercentualComissao() {
		return percentualComissao;
	}

	public void setPercentualComissao(Float percentualComissao) {
		this.percentualComissao = percentualComissao;
	}

	public Float getPercentualPermuta() {
		return percentualPermuta;
	}

	public void setPercentualPermuta(Float percentualPermuta) {
		this.percentualPermuta = percentualPermuta;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public List<ProgramaContrato> getProgramas() {
		return programas;
	}

	public void setProgramas(List<ProgramaContrato> programas) {
		this.programas = programas;
	}
	
}