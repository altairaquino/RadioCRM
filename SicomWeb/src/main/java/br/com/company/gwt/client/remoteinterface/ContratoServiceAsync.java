package br.com.company.gwt.client.remoteinterface;

import java.util.Date;
import java.util.List;

import br.com.company.gwt.shared.dto.DTOCliente;
import br.com.company.gwt.shared.dto.DTOContrato;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ContratoServiceAsync {

	void listAll(AsyncCallback<List<DTOContrato>> callback);

	void salvar(DTOContrato dtoContrato, AsyncCallback<DTOContrato> asyncCallback);

	void pesquisa(DTOCliente cliente, Date date, AsyncCallback<List<DTOContrato>> callback);

}