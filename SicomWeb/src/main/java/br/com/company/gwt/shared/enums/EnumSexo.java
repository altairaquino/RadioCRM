package br.com.company.gwt.shared.enums;

import java.io.Serializable;

public enum EnumSexo implements Serializable{
	
	M("Masculino"), F("Feminino");
	
	private String descricao;
	
	private EnumSexo(String nome){
		this.descricao = nome;
	}
	
	public String getNome() {
		return descricao;
	}

}