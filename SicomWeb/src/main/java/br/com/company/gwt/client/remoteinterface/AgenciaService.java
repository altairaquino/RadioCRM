package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOAgencia;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/agenciaService")
public interface AgenciaService extends RemoteService {

	List<DTOAgencia> listAll();

	List<DTOAgencia> pesquisa(String query);

	DTOAgencia salvar(DTOAgencia dtoAgencia) throws Exception;

	PagingLoadResult<DTOAgencia> loadPagingList(PagingLoadConfig config);
	
}