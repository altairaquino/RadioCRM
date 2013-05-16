package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.AgenciaService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoAgencia;
import br.com.company.gwt.server.entities.Agencia;
import br.com.company.gwt.shared.dto.DTOAgencia;

@Named("agenciaService")
public class AgenciaServiceImpl extends InputServletImpl implements AgenciaService{
	
	@Inject private DaoAgencia daoAgencia;
	
	@Override
	public List<DTOAgencia> listAll() {
		List<DTOAgencia> agencias = new ArrayList<DTOAgencia>();
		try {
			for (Agencia agencia : daoAgencia.loadAll()) {
				agencias.add(parseToDTOAgencia(agencia));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencias;
	}
	
	@Override
	public List<DTOAgencia> pesquisa(String query) {
		List<DTOAgencia> clientes = new ArrayList<DTOAgencia>();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	private DTOAgencia parseToDTOAgencia(Agencia agencia){
		DTOAgencia dto = new DTOAgencia();
		dto.setId(agencia.getId());
		dto.setNome(agencia.getNome());
		return dto;
	}

}