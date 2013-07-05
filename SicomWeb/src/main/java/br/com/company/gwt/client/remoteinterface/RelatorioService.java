package br.com.company.gwt.client.remoteinterface;

import br.com.company.gwt.shared.bean.ParametrosReport;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/relatorioService")
public interface RelatorioService extends RemoteService{
	
	String relatorioAgencias(ParametrosReport parametros) throws Exception;
	String relatorioClientes(ParametrosReport parametros) throws Exception;
	String relatorioProgramas(ParametrosReport parametros) throws Exception;
	String relatorio(ParametrosReport parametros) throws Exception;
	String relatorioPeriodo(ParametrosReport parametros) throws Exception;
	
}