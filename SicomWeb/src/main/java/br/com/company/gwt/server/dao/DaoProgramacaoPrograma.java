package br.com.company.gwt.server.dao;

import javax.inject.Named;

import br.com.company.gwt.server.entities.ProgramacaoPrograma;

@Named(value="daoProgramacaoPrograma")
public class DaoProgramacaoPrograma extends DaoAbstract<ProgramacaoPrograma, Integer> {
	
	@Override
	protected Integer getId(ProgramacaoPrograma programacaoPrograma) {
		return programacaoPrograma.getId();
	}

}