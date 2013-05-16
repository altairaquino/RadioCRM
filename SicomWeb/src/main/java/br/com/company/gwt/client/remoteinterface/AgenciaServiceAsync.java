package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOAgencia;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AgenciaServiceAsync {

	void listAll(AsyncCallback<List<DTOAgencia>> callback);

	void pesquisa(String query, AsyncCallback<List<DTOAgencia>> callback);

}