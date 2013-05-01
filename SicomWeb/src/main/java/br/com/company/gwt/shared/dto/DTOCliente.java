package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOCliente extends BaseModelData {

	private static final long serialVersionUID = -2413990358760812109L;
	
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

}