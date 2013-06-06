package br.com.company.gwt.shared.dto;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOPrograma extends BaseModelData {
	
	private static final long serialVersionUID = 8604973707850497152L;
	
	private List<DTOProgramacao> programacao;

	public Integer getId() {
		return get("id");
	}

	public void setId(Integer id) {
		this.set("id", id);
	}

	public String getNome() {
		return get("nome");
	}

	public void setNome(String nome) {
		this.set("nome",  nome);
	}

	public Float getValorPatrocinio() {
		return get("valorPatrocinio");
	}

	public void setValorPatrocinio(Float valorPatrocinio) {
		this.set("valorPatrocinio", valorPatrocinio);
	}

	public Boolean getAtivo() {
		return get("ativo");
	}

	public void setAtivo(Boolean ativo) {
		this.set("ativo", ativo);
	}

	public List<DTOProgramacao> getProgramacao() {
		return programacao;
	}

	public void setProgramacao(List<DTOProgramacao> programacao) {
		this.programacao = programacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getId() == null) ? 0 : getId().hashCode());
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
		DTOPrograma other = (DTOPrograma) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}