package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOOrigemContrato;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrigemContratoServiceAsync {

	void listAll(AsyncCallback<List<DTOOrigemContrato>> callback);

	void salvar(DTOOrigemContrato dtoTipoContrato, AsyncCallback<DTOOrigemContrato> callback);

}