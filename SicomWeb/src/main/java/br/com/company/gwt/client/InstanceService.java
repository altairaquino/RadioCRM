package br.com.company.gwt.client;

import br.com.company.gwt.client.remoteinterface.CidadeService;
import br.com.company.gwt.client.remoteinterface.CidadeServiceAsync;
import br.com.company.gwt.client.remoteinterface.ClienteService;
import br.com.company.gwt.client.remoteinterface.ClienteServiceAsync;
import br.com.company.gwt.client.remoteinterface.OperacaoService;
import br.com.company.gwt.client.remoteinterface.OperacaoServiceAsync;
import br.com.company.gwt.client.remoteinterface.UserService;
import br.com.company.gwt.client.remoteinterface.UserServiceAsync;

import com.google.gwt.core.client.GWT;

public class InstanceService {
	
	public final static UserServiceAsync USER_SERVICE = GWT.create(UserService.class);
	public final static CidadeServiceAsync CIDADE_SERVICE = GWT.create(CidadeService.class);
	public final static OperacaoServiceAsync OPERACAO_SERVICE = GWT.create(OperacaoService.class);
	public final static ClienteServiceAsync CLIENTE_SERVICE = GWT.create(ClienteService.class);

}