package br.com.company.gwt.server.dao;

import javax.inject.Named;

import br.com.company.gwt.server.entities.Agencia;

@Named(value="daoPrograma")
public class DaoAgencia extends DaoAbstract<Agencia, Integer> {
	
	@Override
	protected Integer getId(Agencia agencia) {
		return agencia.getId();
	}
	
}