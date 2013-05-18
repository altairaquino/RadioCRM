package br.com.company.gwt.shared.dto;

import java.util.Date;

import br.com.company.gwt.client.dto.DTOCidade;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("unused")
public class DTOCliente extends BaseModelData {

	private static final long serialVersionUID = -2413990358760812109L;
	
	private DTOAgencia agencia;
	private DTOTipoLogradouro tipoLogradouro;
	private DTOCidade cidade;
	
	public Integer getId(){
		return get("id");
	}
	
	public void setId(Integer id){
		set("id", id);
	}
	
	public String getNome(){
		return get("nome");
	}
	
	public void setNome(String nome){
		set("nome", nome);
	}

	public String getDocumento(){
		return get("documento");
	}
	
	public void setDocumento(String documento){
		set("documento", documento);
	}

	public String getRazaoSocial() {
		return get("razaoSocial");
	}

	public void setRazaoSocial(String razaoSocial) {
		this.set("razaoSocial", "razaoSocial");
	}

	public String getSegmento() {
		return get("segmento");
	}

	public void setSegmento(String segmento) {
		this.set("segmento", "segmento");
	}

	public String getEmail() {
		return get("email");
	}

	public void setEmail(String email) {
		this.set("email", "email");
	}

	public String getFone() {
		return get("fone");
	}

	public void setFone(String fone) {
		this.set("fone", "fone");
	}

	public String getTipoPessoa() {
		return get("tipoPessoa");
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.set("tipoPessoa", "tipoPessoa");
	}

	public Date getDataNascimento() {
		return get("dataNascimento");
	}

	public void setDataNascimento(Date dataNascimento) {
		this.set("dataNascimento", "dataNascimento");
	}

	public DTOAgencia getAgencia() {
		return get("agencia");
	}

	public void setAgencia(DTOAgencia agencia) {
		this.set("agencia", "agencia");
	}

	public DTOTipoLogradouro getTipoLogradouro() {
		return get("tipoLogradouro");
	}

	public void setTipoLogradouro(DTOTipoLogradouro tipoLogradouro) {
		this.set("tipoLogradouro", "tipoLogradouro");
	}

	public String getLogradouro() {
		return get("logradouro");
	}

	public void setLogradouro(String logradouro) {
		this.set("logradouro", "logradouro");
	}

	public String getComplemento() {
		return get("complemento");
	}

	public void setComplemento(String complemento) {
		this.set("complemento", "complemento");
	}

	public String getCep() {
		return get("cep");
	}

	public void setCep(String cep) {
		this.set("cep", "cep");
	}

	public String getBairro() {
		return get("bairro");
	}

	public void setBairro(String bairro) {
		this.set("bairro", "bairro");
	}

	public DTOCidade getCidade() {
		return get("cidade");
	}

	public void setCidade(DTOCidade cidade) {
		this.set("cidade", "cidade");
	}

	public String getNomeContato() {
		return get("nomeContato");
	}

	public void setNomeContato(String nomeContato) {
		this.set("nomeContato", "nomeContato");
	}

	public String getFoneContato() {
		return get("foneContato");
	}

	public void setFoneContato(String foneContato) {
		this.set("foneContato", "foneContato");
	}

	public String getCellContato() {
		return get("cellContato");
	}

	public void setCellContato(String cellContato) {
		this.set("cellContato", "cellContato");
	}

	public Date getDataNascimentoContato() {
		return get("dataNascimentoContato");
	}

	public void setDataNascimentoContato(Date dataNascimentoContato) {
		this.set("dataNascimentoContato", "dataNascimentoContato");
	}

	public String getNomeProprietario() {
		return get("nomeProprietario");
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.set("nomeProprietario", "nomeProprietario");
	}

	public Date getDataNascimentoProprietario() {
		return get("dataNascimentoProprietario");
	}

	public void setDataNascimentoProprietario(Date dataNascimentoProprietario) {
		this.set("dataNascimentoProprietario", "dataNascimentoProprietario");
	}

	public Boolean getAtivo() {
		return get("ativo");
	}

	public void setAtivo(Boolean ativo) {
		this.set("ativo", ativo);
	}

}