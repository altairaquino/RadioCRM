package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOCliente;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/clienteService")
public interface ClienteService extends RemoteService {

	List<DTOCliente> listAll();

	List<DTOCliente> pesquisa(String query);

	DTOCliente salvar(DTOCliente dtoCliente) throws Exception;

	PagingLoadResult<DTOCliente> loadPagingList(PagingLoadConfig config);
	
}