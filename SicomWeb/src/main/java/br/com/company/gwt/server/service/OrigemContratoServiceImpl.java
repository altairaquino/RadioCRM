package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.company.gwt.client.remoteinterface.OrigemContratoService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoOrigemContrato;
import br.com.company.gwt.server.entities.OrigemContrato;
import br.com.company.gwt.shared.dto.DTOOrigemContrato;

@Named("origemContratoService")
public class OrigemContratoServiceImpl extends InputServletImpl implements OrigemContratoService{
	
	@Inject private DaoOrigemContrato daoOrigemContrato;

	@Override
	public List<DTOOrigemContrato> listAll() {
		List<DTOOrigemContrato> tipos = new ArrayList<DTOOrigemContrato>();
		try {
			for (OrigemContrato origem : daoOrigemContrato.loadAll()) {
				DTOOrigemContrato dto = new DTOOrigemContrato();
				dto.setId(origem.getId());
				dto.setNome(origem.getNome());
				dto.setAtivo(origem.getAtivo());
				
				tipos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipos;
	}

	@Transactional
	@Override
	public DTOOrigemContrato salvar(DTOOrigemContrato dtoOrigemContrato) throws Exception {
		try{
			OrigemContrato origemContrato = null;
			if (dtoOrigemContrato.getId() == null){
				origemContrato = new OrigemContrato();
			}else{
				origemContrato = daoOrigemContrato.findByPrimaryKey(dtoOrigemContrato.getId());
			}
			
			if (daoOrigemContrato.findByNome(dtoOrigemContrato.getNome()) != null){
				throw new Exception("Já existe origem de contrato com este nome!");
			}
			
			origemContrato.setNome(dtoOrigemContrato.getNome());
			origemContrato.setAtivo(true);
			
			daoOrigemContrato.store(origemContrato);
			
			dtoOrigemContrato.setId(origemContrato.getId());
			
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return dtoOrigemContrato;
		
	}

}