package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOCliente;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClienteServiceAsync {

	void listAll(AsyncCallback<List<DTOCliente>> callback);

	void pesquisa(String query, AsyncCallback<List<DTOCliente>> callback);

}