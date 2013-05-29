package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOAgencia;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AgenciaServiceAsync {

	void listAll(AsyncCallback<List<DTOAgencia>> callback);

	void pesquisa(String query, AsyncCallback<List<DTOAgencia>> callback);

	void salvar(DTOAgencia dtoAgencia, AsyncCallback<DTOAgencia> asyncCallback);

	void loadPagingList(PagingLoadConfig config, AsyncCallback<PagingLoadResult<DTOAgencia>> callback);

}