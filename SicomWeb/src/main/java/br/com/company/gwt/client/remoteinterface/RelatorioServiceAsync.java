package br.com.company.gwt.client.remoteinterface;

import br.com.company.gwt.shared.bean.ParametrosReport;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RelatorioServiceAsync {
	
	void relatorioAgencias(ParametrosReport parametros, AsyncCallback<String> callback);
	void relatorioClientes(ParametrosReport parametros, AsyncCallback<String> callback);
	void relatorioProgramas(ParametrosReport parametros, AsyncCallback<String> callback);
	void relatorio(ParametrosReport parametros, AsyncCallback<String> callback);
	
}