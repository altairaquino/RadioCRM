package br.com.company.gwt.shared.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.com.company.gwt.client.dto.DTOCidade;
import br.com.company.gwt.server.entities.Cidade;
import br.com.company.gwt.server.entities.TipoLogradouro;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("unused")
public class DTOAgencia extends BaseModelData {
	
	private static final long serialVersionUID = 8604973707850497152L;
	
	private DTOTipoLogradouro tipoLogradouro;
	private DTOCidade cidade;
	
	public Integer getId(){
		return this.get("id");
	}
	
	public void setId(Integer id){
		this.set("id", id);
	}
	
	public String getNome(){
		return this.get("nome");
	}
	
	public void setNome(String nome){
		this.set("nome", nome);
	}

	public String getRazaoSocial() {
		return get("razaoSocial");
	}

	public void setRazaoSocial(String razaoSocial) {
		this.set("razaoSocial" , razaoSocial);
	}

	public String getDocumento() {
		return get("documento");
	}

	public void setDocumento(String documento) {
		this.set("documento" , documento);
	}

	public Float getComissao() {
		return get("comissao");
	}

	public void setComissao(Float comissao) {
		this.set("comissao" , comissao);
	}

	public String getTelefone() {
		return get("telefone");
	}

	public void setTelefone(String telefone) {
		this.set("telefone" , telefone);
	}

	public String getCelular() {
		return get("celular");
	}

	public void setCelular(String celular) {
		this.set("celular" , celular);
	}

	public String getEmail() {
		return get("email");
	}

	public void setEmail(String email) {
		this.set("email" , email);
	}

	public DTOTipoLogradouro getTipoLogradouro() {
		return get("tipoLogradouro");
	}

	public void setTipoLogradouro(DTOTipoLogradouro tipoLogradouro) {
		this.set("tipoLogradouro" , tipoLogradouro);
	}

	public String getLogradouro() {
		return get("logradouro");
	}

	public void setLogradouro(String logradouro) {
		this.set("logradouro" , logradouro);
	}

	public String getComplemento() {
		return get("complemento");
	}

	public void setComplemento(String complemento) {
		this.set("complemento" , complemento);
	}

	public String getCep() {
		return get("cep");
	}

	public void setCep(String cep) {
		this.set("cep" , cep);
	}

	public String getBairro() {
		return get("bairro");
	}

	public void setBairro(String bairro) {
		this.set("bairro" , bairro);
	}

	public DTOCidade getCidade() {
		return get("cidade");
	}

	public void setCidade(DTOCidade cidade) {
		this.set("cidade" , cidade);
	}

	public Boolean getAtivo() {
		return get("ativo");
	}

	public void setAtivo(Boolean ativo) {
		this.set("ativo" , ativo);
	}
	
	

}