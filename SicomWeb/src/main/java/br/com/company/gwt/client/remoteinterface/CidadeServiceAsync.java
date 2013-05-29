package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOCidade;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CidadeServiceAsync {

	void listAll(AsyncCallback<List<DTOCidade>> callback);

	void listCidadesByUF(String nome, String uf, AsyncCallback<List<DTOCidade>> callback);

	void loadPagingList(String estado, PagingLoadConfig config,	AsyncCallback<PagingLoadResult<DTOCidade>> callback);

}
