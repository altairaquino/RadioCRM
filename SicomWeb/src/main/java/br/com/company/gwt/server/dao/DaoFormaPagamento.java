package br.com.company.gwt.server.dao;

import javax.inject.Named;

import br.com.company.gwt.server.entities.FormaPagamento;

@Named(value="daoFormaPagamento")
public class DaoFormaPagamento extends DaoAbstract<FormaPagamento, Integer> {
	
	@Override
	protected Integer getId(FormaPagamento formaPagamento) {
		return formaPagamento.getId();
	}

}