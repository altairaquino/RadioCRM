package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.TipoLogradouroService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoTipoLogradouro;
import br.com.company.gwt.server.entities.TipoLogradouro;
import br.com.company.gwt.shared.dto.DTOTipoLogradouro;

@Named("tipoLogradouroService")
public class TipoLogradouroServiceImpl extends InputServletImpl implements TipoLogradouroService{
	
	@Inject private DaoTipoLogradouro daoTipoLogradouro;

	@Override
	public List<DTOTipoLogradouro> listAll() {
		List<DTOTipoLogradouro> tipos = new ArrayList<DTOTipoLogradouro>();
		try {
			for (TipoLogradouro tipo : daoTipoLogradouro.loadAll()) {
				DTOTipoLogradouro dto = new DTOTipoLogradouro();
				dto.setId(tipo.getId());
				dto.setNome(tipo.getNome());
				
				tipos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipos;
	}	

}