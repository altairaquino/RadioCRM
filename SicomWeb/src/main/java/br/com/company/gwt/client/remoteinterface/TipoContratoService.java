package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOTipoContrato;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/tipoContratoService")
public interface TipoContratoService extends RemoteService {

	List<DTOTipoContrato> listAll();
	
	DTOTipoContrato salvar(DTOTipoContrato dtoTipoContrato) throws Exception;
	
}