package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOTipoLogradouro;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/tipoLogradouroService")
public interface TipoLogradouroService extends RemoteService {

	List<DTOTipoLogradouro> listAll();
	
}