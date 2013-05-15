package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOProgramacao extends BaseModelData {
	
	private static final long serialVersionUID = 8604973707850497152L;

	public Integer getId() {
		return get("id");
	}

	public void setId(Integer id) {
		set("id", id);
	}

	public String getDiaSemana() {
		return get("diaSemana");
	}

	public void setDiaSemana(String diaSemana) {
		set("diaSemana",  diaSemana);
	}

	public String getHoraInicio() {
		return get("horaInicio");
	}

	public void setHoraInicio(String horaInicio) {
		set("horaInicio", horaInicio);
	}

	public String getHoraTermino() {
		return get("horaTermino");
	}

	public void setHoraTermino(String horaTermino) {
		set("horaTermino",  horaTermino);
	}

}