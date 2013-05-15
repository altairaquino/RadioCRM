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

}