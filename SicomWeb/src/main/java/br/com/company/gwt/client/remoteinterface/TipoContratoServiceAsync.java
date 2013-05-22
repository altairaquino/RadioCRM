package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOTipoContrato;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TipoContratoServiceAsync {

	void listAll(AsyncCallback<List<DTOTipoContrato>> callback);

	void salvar(DTOTipoContrato dtoTipoContrato, AsyncCallback<DTOTipoContrato> callback);

}