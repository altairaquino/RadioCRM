package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOTipoLogradouro extends BaseModelData {
	
	private static final long serialVersionUID = 8604973707850497152L;

	public Integer getOperacaoId() {
		return get("operacaoId");
	}

	public void setOperacaoId(Integer operacaoId) {
		set("operacaoId", operacaoId);
	}

	public String getDescricao() {
		return get("descricao");
	}

	public void setDescricao(String descricao) {
		set("descricao", descricao);
	}	

}