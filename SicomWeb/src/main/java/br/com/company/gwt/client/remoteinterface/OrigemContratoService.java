package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOOrigemContrato;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/origemContratoService")
public interface OrigemContratoService extends RemoteService {

	List<DTOOrigemContrato> listAll();
	
	DTOOrigemContrato salvar(DTOOrigemContrato dtoOrigemContrato) throws Exception;
	
}