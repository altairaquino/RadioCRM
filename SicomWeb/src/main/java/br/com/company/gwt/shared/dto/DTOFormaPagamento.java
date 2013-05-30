package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOFormaPagamento extends BaseModelData {
	
	private static final long serialVersionUID = 8604973707850497152L;

	public Integer getId() {
		return get("id");
	}

	public void setId(Integer id) {
		set("id", id);
	}

	public String getNome() {
		return get("nome");
	}

	public void setNome(String nome) {
		set("nome", nome);
	}	

}