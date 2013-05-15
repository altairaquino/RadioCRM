package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOPrograma;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProgramaServiceAsync {

	void listAll(AsyncCallback<List<DTOPrograma>> callback);

	void pesquisa(String query, AsyncCallback<List<DTOPrograma>> callback);

}