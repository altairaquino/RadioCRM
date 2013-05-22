package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.company.gwt.client.remoteinterface.TipoContratoService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoTipoContrato;
import br.com.company.gwt.server.entities.TipoContrato;
import br.com.company.gwt.shared.dto.DTOTipoContrato;

@Named("tipoContratoService")
public class TipoContratoServiceImpl extends InputServletImpl implements TipoContratoService{
	
	@Inject private DaoTipoContrato daoTipoContrato;

	@Override
	public List<DTOTipoContrato> listAll() {
		List<DTOTipoContrato> tipos = new ArrayList<DTOTipoContrato>();
		try {
			for (TipoContrato tipo : daoTipoContrato.loadAll()) {
				DTOTipoContrato dto = new DTOTipoContrato();
				dto.setId(tipo.getId());
				dto.setNome(tipo.getNome());
				dto.setAtivo(tipo.getAtivo());
				
				tipos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipos;
	}

	@Transactional
	@Override
	public DTOTipoContrato salvar(DTOTipoContrato dtoTipoContrato) throws Exception {
		try{
			TipoContrato tipoContrato = null;
			if (dtoTipoContrato.getId() == null){
				tipoContrato = new TipoContrato();
			}else{
				tipoContrato = daoTipoContrato.findByPrimaryKey(dtoTipoContrato.getId());
			}
			
			if (daoTipoContrato.findByNome(dtoTipoContrato.getNome()) != null){
				throw new Exception("Já existe tipo de contrato com este nome!");
			}
			
			tipoContrato.setNome(dtoTipoContrato.getNome());
			tipoContrato.setAtivo(true);
			
			daoTipoContrato.store(tipoContrato);
			
			dtoTipoContrato.setId(tipoContrato.getId());
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return dtoTipoContrato;
		
	}

}