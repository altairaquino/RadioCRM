package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOFormaPagamento;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FormaPagamentoServiceAsync {

	void listAll(AsyncCallback<List<DTOFormaPagamento>> callback);

}