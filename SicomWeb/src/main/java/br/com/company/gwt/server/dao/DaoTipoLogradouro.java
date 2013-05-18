package br.com.company.gwt.server.dao;

import javax.inject.Named;

import br.com.company.gwt.server.entities.TipoLogradouro;

@Named(value="daoTipoLogradouro")
public class DaoTipoLogradouro extends DaoAbstract<TipoLogradouro, Integer> {
	
	@Override
	protected Integer getId(TipoLogradouro tipoLogradouro) {
		return tipoLogradouro.getId();
	}

}