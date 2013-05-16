package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOAgencia extends BaseModelData {
	
	private static final long serialVersionUID = 8604973707850497152L;

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

}