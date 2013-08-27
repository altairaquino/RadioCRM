package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOTipoLogradouro extends BaseModelData {
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		DTOTipoLogradouro other = (DTOTipoLogradouro) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}