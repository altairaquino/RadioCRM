package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.JasperName;
import br.com.company.gwt.client.component.PanelGridWindow;
import br.com.company.gwt.client.component.RelatorioCallback;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.shared.bean.ParametrosReport;
import br.com.company.gwt.shared.dto.DTOCliente;

public class PanelGridCliente extends PanelGridWindow<DTOCliente> {
	
	public PanelGridCliente() {
		setHeadingHtml("Cadastro de Clientes");
		grid.setAutoExpandColumn("nome");
		loadDados();
	}

	private void loadDados() {
		painelPrincipal.mask("Aguarde carregando...");
		InstanceService.CLIENTE_SERVICE.listAll(new AsyncCallback<List<DTOCliente>>() {
			
			@Override
			public void onSuccess(List<DTOCliente> result) {
				getStore().removeAll();
				getStore().add(result);
				painelPrincipal.unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				painelPrincipal.unmask();
			}
		});		
	}

	@Override
	protected void actionPesquisa(String consulta) {
		painelPrincipal.mask("Aguarde carregando consulta...");
		InstanceService.CLIENTE_SERVICE.pesquisa(consulta, new AsyncCallback<List<DTOCliente>>() {
			
			@Override
			public void onSuccess(List<DTOCliente> result) {
				getStore().removeAll();
				getStore().add(result);
				painelPrincipal.unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				painelPrincipal.unmask();
			}
		});
	}

	@Override
	protected ColumnModel getColumnModel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig config = new ColumnConfig("id", "Codigo", 50);
		configs.add(config);
		config = new ColumnConfig("nome", "Nome", 250);
		configs.add(config);
		config = new ColumnConfig("tipoPessoa", "Tipo", 70);
		configs.add(config);
		config = new ColumnConfig("fone", "Fone", 90);
		configs.add(config);
		config = new ColumnConfig("segmento", "Segmento", 120);
		configs.add(config);
		
		return new ColumnModel(configs);
	}

	@Override
	protected void actionButtonNovoClick() {
		FormCliente formCliente = ProviderFacadeManager.formCliente.createInstance();
		formCliente.setBlinkModal(true);
		formCliente.show();
	}

	@Override
	protected void actionButtonRemoveClick() {
				
	}

	@Override
	protected void actionButtonReportClick() {
		ParametrosReport parametros = new ParametrosReport();
		parametros.setNomeRelatorio(JasperName.CLIENTES);
		
		InstanceService.RELATORIO_SERVICE.relatorio(parametros, new RelatorioCallback());
				
	}

	@Override
	protected void actionButtonEditarClick() {
		DTOCliente cliente = getSelecaoGrid();
		if (cliente != null){
			FormCliente formCliente = ProviderFacadeManager.formCliente.createInstance();
			formCliente.loadDTOCliente(cliente);
			formCliente.setBlinkModal(true);
			formCliente.show();
		}else{
			WebMessageBox.alert("Selecione o cliente!");
		}
	}

	@Override
	protected void actionButtonAtualizaClick() {
		loadDados();
	}

}