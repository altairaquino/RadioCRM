package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOContrato;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContratoServiceAsync {

	void listAll(AsyncCallback<List<DTOContrato>> callback);

	void salvar(DTOContrato dtoContrato, AsyncCallback<DTOContrato> asyncCallback);

}