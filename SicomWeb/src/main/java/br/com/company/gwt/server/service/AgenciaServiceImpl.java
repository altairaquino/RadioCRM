package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.AgenciaService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoAgencia;
import br.com.company.gwt.server.dao.DaoCidade;
import br.com.company.gwt.server.dao.DaoTipoLogradouro;
import br.com.company.gwt.server.entities.Agencia;
import br.com.company.gwt.shared.dto.DTOAgencia;

@Named("agenciaService")
public class AgenciaServiceImpl extends InputServletImpl implements AgenciaService{
	
	@Inject private DaoAgencia daoAgencia;
	@Inject private DaoTipoLogradouro daoTipoLogradouro;
	@Inject private DaoCidade daoCidade;
	
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

	@Override
	public DTOAgencia salvar(DTOAgencia dtoAgencia) throws Exception {
		try {
			Agencia agencia = null;
			if (dtoAgencia.getId() == null){
				agencia = new Agencia();
			}else{
				agencia = daoAgencia.findByPrimaryKey(dtoAgencia.getId());
			}
			
			agencia.setNome(dtoAgencia.getNome());
			agencia.setRazaoSocial(dtoAgencia.getRazaoSocial());
			agencia.setDocumento(dtoAgencia.getDocumento());
			agencia.setComissao(dtoAgencia.getComissao());
			agencia.setTelefone(dtoAgencia.getTelefone());
			agencia.setCelular(dtoAgencia.getCelular());
			agencia.setEmail(dtoAgencia.getEmail());
			agencia.setTipoLogradouro(daoTipoLogradouro.findByPrimaryKey(dtoAgencia.getTipoLogradouro().getId()));
			agencia.setLogradouro(dtoAgencia.getLogradouro());
			agencia.setComplemento(dtoAgencia.getComplemento());
			agencia.setCep(dtoAgencia.getCep());
			agencia.setBairro(dtoAgencia.getBairro());
			agencia.setCidade(daoCidade.findByPrimaryKey(dtoAgencia.getCidade().getId()));
			
			
			daoAgencia.store(agencia);
			
			dtoAgencia.setId(agencia.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}