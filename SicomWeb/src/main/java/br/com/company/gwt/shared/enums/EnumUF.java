package br.com.company.gwt.shared.enums;

public enum EnumUF {
	
	AC("ACRE"),
	AL("ALAGOAS"), 
	AM("AMAZONAS"), 
	AP("AMAPÁ"), 
	BA("BAHIA"), 
	CE("CEARÁ"), 
	DF("DISTRITO FEDERAL"), 
	ES("ESPÍRITO SANTO"), 
	GO("GOIÁS"), 
	MA("MARANHÃO"), 
	MG("MINAS GERAIS"), 
	MS("MATO GROSSO DO SUL"), 
	MT("MATO GROSSO"), 
	PA("PARÁ"), 
	PB("PARAÍBA"), 
	PE("PERNAMBUCO"), 
	PI("PIAUÍ"), 
	PR("PARANÁ"), 
	RJ("RIO DE JANEIRO"), 
	RN("RIO GRANDE DO NORTE"), 
	RO("RONDÔNIA"), 
	RR("RORAIMA"), 
	RS("RIO GRANDE DO SUL"), 
	SC("SANTA CATARINA"), 
	SE("SERGIPE"), 
	SP("SÃO PAULO"), 
	TO("TOCANTINS");
	
	private String nome;
	
	private EnumUF(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}

}