package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOTipoLogradouro;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TipoLogradouroServiceAsync {

	void listAll(AsyncCallback<List<DTOTipoLogradouro>> callback);

}