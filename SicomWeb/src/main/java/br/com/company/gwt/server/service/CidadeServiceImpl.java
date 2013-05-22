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

	private DTOCidade parseToDTOCidade(Cidade cidade){
		DTOCidade dto = new DTOCidade();
		dto.setId(cidade.getId());
		dto.setNome(cidade.getNome());
		dto.setUF(cidade.getUf());
		return dto;
	}

}