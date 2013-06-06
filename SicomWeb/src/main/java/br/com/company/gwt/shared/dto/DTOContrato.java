package br.com.company.gwt.shared.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("unused")
public class DTOContrato extends BaseModelData {

	private static final long serialVersionUID = 5725429209891863235L;
	
	private DTOCliente cliente;
	private DTOTipoContrato tipoContrato;
	private DTOFormaPagamento formaPagamento;
	private List<DTOPrograma> programas;

	public Integer getId() {
		return get("id");
	}

	public void setId(Integer id) {
		this.set("id", id);
	}

	public DTOCliente getCliente() {
		return get("cliente");
	}

	public void setCliente(DTOCliente cliente) {
		this.set("cliente", cliente);
	}
	
	public String getNomeCliente() {
		return get("nomeCliente");
	}
	
	public void setNomeCliente(String cliente) {
		this.set("nomeCliente", cliente);
	}

	public DTOTipoContrato getTipoContrato() {
		return get("tipoContrato");
	}

	public void setTipoContrato(DTOTipoContrato tipoContrato) {
		this.set("tipoContrato", tipoContrato);
	}

	public DTOFormaPagamento getFormaPagamento() {
		return get("formaPagamento");
	}

	public void setFormaPagamento(DTOFormaPagamento formaPagamento) {
		this.set("formaPagamento", formaPagamento);
	}

	public List<DTOPrograma> getProgramas() {
		if (get("programas") == null){
			setProgramas(new ArrayList<DTOPrograma>());
		}
		return get("programas");
	}

	public void setProgramas(List<DTOPrograma> programas) {
		this.set("programas", programas);
	}

	public Float getValor() {
		return get("valor");
	}

	public void setValor(Float valor) {
		this.set("valor", valor);
	}

	public String getInformacoesTexto() {
		return get("informacoesTexto");
	}

	public void setInformacoesTexto(String informacoesTexto) {
		this.set("informacoesTexto", informacoesTexto);
	}

	public Float getPercentualComissao() {
		return get("percentualComissao");
	}

	public void setPercentualComissao(Float percentualComissao) {
		this.set("percentualComissao", percentualComissao);
	}

	public Float getPercentualPermuta() {
		return get("percentualPermuta");
	}

	public void setPercentualPermuta(Float percentualPermuta) {
		this.set("percentualPermuta", percentualPermuta);
	}

	public Date getDataCadastro() {
		return get("dataCadastro");
	}

	public void setDataCadastro(Date dataCadastro) {
		this.set("dataCadastro", dataCadastro);
	}

	public Date getDataInicio() {
		return get("dataInicio");
	}

	public void setDataInicio(Date dataInicio) {
		this.set("dataInicio", dataInicio);
	}

	public Date getDataTermino() {
		return get("dataTermino");
	}

	public void setDataTermino(Date dataTermino) {
		this.set("dataTermino", dataTermino);
	}

	public Date getDataCancelamento() {
		return get("dataCancelamento");
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.set("dataCancelamento", dataCancelamento);
	}

	public String getTipoPagamento() {
		return get("tipoPagamento");
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.set("tipoPagamento", tipoPagamento);
	}

	public Date getDataPagamento() {
		return get("dataPagamento");
	}

	public void setDataPagamento(Date dataPagamento) {
		this.set("dataPagamento", dataPagamento);
	}	

}