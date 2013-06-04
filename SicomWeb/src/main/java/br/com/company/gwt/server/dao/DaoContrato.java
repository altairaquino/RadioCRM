package br.com.company.gwt.server.dao;

import javax.inject.Named;

import br.com.company.gwt.server.entities.Contrato;

@Named(value="daoContrato")
public class DaoContrato extends DaoAbstract<Contrato, Integer> {
	
	@Override
	protected Integer getId(Contrato contrato) {
		return contrato.getId();
	}

}