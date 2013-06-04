package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOContrato;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/contratoService")
public interface ContratoService extends RemoteService {

	List<DTOContrato> listAll();

	DTOContrato salvar(DTOContrato dtoContrato) throws Exception;
	
}