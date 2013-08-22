package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOOrigemContrato extends BaseModelData{

	private static final long serialVersionUID = 219207327028169784L;
	
	public Integer getId() {
		return get("id");
	}

	public void setId(Integer id) {
		set("id", id);
	}

	public void setNome(String nome) {
		set("nome", nome);
	}

	public String getNome() {
		return get("nome");
	}
	
	public void setAtivo(Boolean ativo) {
		set("ativo", ativo);
	}
	
	public Boolean getAtivo() {
		return get("ativo");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((get("id") == null) ? 0 : get("id").hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOOrigemContrato other = (DTOOrigemContrato) obj;
		if (get("id") == null) {
			if (other.get("id") != null)
				return false;
		} else if (!get("id").equals(other.get("id")))
			return false;
		return true;
	}	

}