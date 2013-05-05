package br.com.company.gwt.server.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	private Float valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
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
