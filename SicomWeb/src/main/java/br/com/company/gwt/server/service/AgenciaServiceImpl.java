package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.company.gwt.client.remoteinterface.AgenciaService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoAgencia;
import br.com.company.gwt.server.dao.DaoCidade;
import br.com.company.gwt.server.dao.DaoTipoLogradouro;
import br.com.company.gwt.server.entities.Agencia;
import br.com.company.gwt.server.entities.Cidade;
import br.com.company.gwt.server.entities.TipoLogradouro;
import br.com.company.gwt.shared.dto.DTOAgencia;
import br.com.company.gwt.shared.dto.DTOCidade;
import br.com.company.gwt.shared.dto.DTOTipoLogradouro;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

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
			for (Agencia agencia : daoAgencia.getAgenciaByNome(query)) {
				clientes.add(parseToDTOAgencia(agencia));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	private DTOAgencia parseToDTOAgencia(Agencia agencia){
		DTOAgencia dto = new DTOAgencia();
		dto.setId(agencia.getId());
		dto.setNome(agencia.getNome());
		dto.setRazaoSocial(agencia.getRazaoSocial());
		dto.setDocumento(agencia.getDocumento());
		dto.setComissao(agencia.getComissao());
		dto.setTelefone(agencia.getTelefone());
		dto.setCelular(agencia.getCelular());
		dto.setEmail(agencia.getEmail());
		TipoLogradouro tipoLogradouro = agencia.getTipoLogradouro(); 
		if (tipoLogradouro != null){
			DTOTipoLogradouro dtoTipoLogradouro = new DTOTipoLogradouro();
			dtoTipoLogradouro.setId(tipoLogradouro.getId());
			dtoTipoLogradouro.setNome(tipoLogradouro.getNome());
			dto.setTipoLogradouro(dtoTipoLogradouro);
		}
		dto.setLogradouro(agencia.getLogradouro());
		dto.setComplemento(agencia.getComplemento());
		dto.setBairro(agencia.getBairro());
		dto.setCep(agencia.getCep());
		Cidade cidade = agencia.getCidade(); 
		if (cidade != null){
			DTOCidade dtoCidade = new DTOCidade();
			dtoCidade.setId(cidade.getId());
			dtoCidade.setNome(cidade.getNome());
			dtoCidade.setUF(cidade.getUf());
			dto.setCidade(dtoCidade);
		}
		
		return dto;
	}

	@Transactional
	@Override
	public DTOAgencia salvar(DTOAgencia dtoAgencia) throws Exception {
		try {
			Agencia agencia = null;
			if (dtoAgencia.getId() == null){
				agencia = new Agencia();
				agencia.setAtivo(true);
				
				Agencia agenciaTemp = daoAgencia.getAgenciaByDocumento(dtoAgencia.getDocumento()); 
				if (agenciaTemp != null){
					throw new Exception("Já existe agência cadastrada com este documento: ["+dtoAgencia.getDocumento()+ "].");
				}
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
			throw new Exception(e.getMessage());
		}
		return dtoAgencia;
	}
	
	@Override
	public PagingLoadResult<DTOAgencia> loadPagingList(PagingLoadConfig config) {
		List<DTOAgencia> sublist = new ArrayList<DTOAgencia>();
		List<Agencia> entities = (ArrayList<Agencia>) loadSubList(config.getOffset(), config.getLimit(), (String)config.get("query"));
		try {
			for (Agencia agencia : entities) {
				sublist.add(parseToDTOAgencia(agencia));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (PagingLoadResult<DTOAgencia>)new BasePagingLoadResult<DTOAgencia>(sublist);
	}
	
	private List<Agencia> loadSubList(Integer offset, Integer limit, String query) {
		List<Agencia> agencias = new ArrayList<Agencia>();
		try {
			if (query != null){
				agencias.addAll(daoAgencia.loadSubList(offset, limit, query.toUpperCase()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencias;
	}

}