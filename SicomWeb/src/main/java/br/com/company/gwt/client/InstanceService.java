package br.com.company.gwt.client;

import br.com.company.gwt.client.remoteinterface.AgenciaService;
import br.com.company.gwt.client.remoteinterface.AgenciaServiceAsync;
import br.com.company.gwt.client.remoteinterface.CidadeService;
import br.com.company.gwt.client.remoteinterface.CidadeServiceAsync;
import br.com.company.gwt.client.remoteinterface.ClienteService;
import br.com.company.gwt.client.remoteinterface.ClienteServiceAsync;
import br.com.company.gwt.client.remoteinterface.ContratoService;
import br.com.company.gwt.client.remoteinterface.ContratoServiceAsync;
import br.com.company.gwt.client.remoteinterface.FormaPagamentoService;
import br.com.company.gwt.client.remoteinterface.FormaPagamentoServiceAsync;
import br.com.company.gwt.client.remoteinterface.OperacaoService;
import br.com.company.gwt.client.remoteinterface.OperacaoServiceAsync;
import br.com.company.gwt.client.remoteinterface.ProgramaService;
import br.com.company.gwt.client.remoteinterface.ProgramaServiceAsync;
import br.com.company.gwt.client.remoteinterface.TipoContratoService;
import br.com.company.gwt.client.remoteinterface.TipoContratoServiceAsync;
import br.com.company.gwt.client.remoteinterface.TipoLogradouroService;
import br.com.company.gwt.client.remoteinterface.TipoLogradouroServiceAsync;
import br.com.company.gwt.client.remoteinterface.UserService;
import br.com.company.gwt.client.remoteinterface.UserServiceAsync;

import com.google.gwt.core.client.GWT;

public class InstanceService {
	
	public final static UserServiceAsync USER_SERVICE = GWT.create(UserService.class);
	public final static CidadeServiceAsync CIDADE_SERVICE = GWT.create(CidadeService.class);
	public final static OperacaoServiceAsync OPERACAO_SERVICE = GWT.create(OperacaoService.class);
	public final static ClienteServiceAsync CLIENTE_SERVICE = GWT.create(ClienteService.class);
	public final static ProgramaServiceAsync PROGRAMA_SERVICE = GWT.create(ProgramaService.class);
	public final static AgenciaServiceAsync AGENCIA_SERVICE = GWT.create(AgenciaService.class);
	public final static TipoContratoServiceAsync TIPOCONTRATO_SERVICE = GWT.create(TipoContratoService.class);
	public final static TipoLogradouroServiceAsync TIPOLOGRADOURO_SERVICE = GWT.create(TipoLogradouroService.class);
	public final static FormaPagamentoServiceAsync FORMAPAGAMENTO_SERVICE = GWT.create(FormaPagamentoService.class);
	public final static ContratoServiceAsync CONTRATO_SERVICE = GWT.create(ContratoService.class);

}