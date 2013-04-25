package br.com.company.gwt.client.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOCidade extends BaseModelData{

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
	
	public void setUF(String uf) {
		set("uf", uf);
	}
	
	public String getUF() {
		return get("uf");
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
		DTOCidade other = (DTOCidade) obj;
		if (get("id") == null) {
			if (other.get("id") != null)
				return false;
		} else if (!get("id").equals(other.get("id")))
			return false;
		return true;
	}
	
	

}