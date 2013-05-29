package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.CidadeService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoCidade;
import br.com.company.gwt.server.entities.Cidade;
import br.com.company.gwt.shared.dto.DTOCidade;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

@Named("cidadeService")
public class CidadeServiceImpl extends InputServletImpl implements CidadeService{
	
	@Inject private DaoCidade daoCidade;

	@Override
	public List<DTOCidade> listAll() {
		List<DTOCidade> cidades = new ArrayList<DTOCidade>();
		try {
			for (Cidade cidade : daoCidade.loadAll()) {
				cidades.add(parseToDTOCidade(cidade));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cidades;
	}

	@Override
	public List<DTOCidade> listCidadesByUF(String nome, String uf) {
		List<DTOCidade> cidades = new ArrayList<DTOCidade>();
		try {
			for (Cidade cidade : daoCidade.getCidadeByNome(nome, uf)) {
				cidades.add(parseToDTOCidade(cidade));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cidades;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<DTOCidade> loadPagingList(String estado, PagingLoadConfig config) {
		List<DTOCidade> sublist = new ArrayList<DTOCidade>();
		List<Cidade> entities = (ArrayList<Cidade>) loadSubList(config.getOffset(), config.getLimit(), (String)config.get("query"), estado);
		try {
			for (Cidade cidade : entities) {
				
				sublist.add(parseToDTOCidade(cidade));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (PagingLoadResult<DTOCidade>)new BasePagingLoadResult(sublist);
	}
	
	private List<Cidade> loadSubList(Integer offset, Integer limit, String query, String estado) {
		List<Cidade> produtos = new ArrayList<Cidade>();
		try {
			if (query != null){
				produtos.addAll(daoCidade.loadSubList(offset, limit, query.toUpperCase(), estado));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

	private DTOCidade parseToDTOCidade(Cidade cidade){
		DTOCidade dto = new DTOCidade();
		dto.setId(cidade.getId());
		dto.setNome(cidade.getNome());
		dto.setUF(cidade.getUf());
		return dto;
	}

}