package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.FormaPagamentoService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoFormaPagamento;
import br.com.company.gwt.server.entities.FormaPagamento;
import br.com.company.gwt.shared.dto.DTOFormaPagamento;

@Named("formaPagamentoService")
public class FormaPagamentoServiceImpl extends InputServletImpl implements FormaPagamentoService{
	
	@Inject private DaoFormaPagamento daoFormaPagamento;

	@Override
	public List<DTOFormaPagamento> listAll() {
		List<DTOFormaPagamento> tipos = new ArrayList<DTOFormaPagamento>();
		try {
			for (FormaPagamento forma : daoFormaPagamento.loadAll()) {
				DTOFormaPagamento dto = new DTOFormaPagamento();
				dto.setId(forma.getId());
				dto.setNome(forma.getNome());
				
				tipos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipos;
	}	

}