package br.com.company.gwt.server.dao;

import javax.inject.Named;

import br.com.company.gwt.server.entities.Programa;

@Named(value="daoPrograma")
public class DaoPrograma extends DaoAbstract<Programa, Integer> {
	
	@Override
	protected Integer getId(Programa programa) {
		return programa.getId();
	}
	
}