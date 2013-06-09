package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.PanelGridWindow;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.shared.dto.DTOAgencia;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PanelGridAgencia extends PanelGridWindow<DTOAgencia> {
	
	public PanelGridAgencia() {
		setHeadingHtml("Cadastro de Agência");
		grid.setAutoExpandColumn("nome");
		loadDados();
	}

	private void loadDados() {
		painelPrincipal.mask("Aguarde. Carregando dados..");
		InstanceService.AGENCIA_SERVICE.listAll(new AsyncCallback<List<DTOAgencia>>() {
			
			@Override
			public void onSuccess(List<DTOAgencia> result) {
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
		painelPrincipal.mask("Carregando dados...");
		InstanceService.AGENCIA_SERVICE.pesquisa(consulta, new AsyncCallback<List<DTOAgencia>>() {
			
			@Override
			public void onSuccess(List<DTOAgencia> result) {
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
		config = new ColumnConfig("telefone", "Fone", 80);
		configs.add(config);
		config = new ColumnConfig("celular", "Celular", 80);
		configs.add(config);
		config = new ColumnConfig("documento", "Documento", 100);
		configs.add(config);
				
		return new ColumnModel(configs);
	}

	@Override
	protected void actionButtonNovoClick() {
		FormAgencia formAgencia = new FormAgencia();
		formAgencia.setModal(true);
		formAgencia.show();		
	}

	@Override
	protected void actionButtonRemoveClick() {
		
	}

	@Override
	protected void actionButtonReportClick() {
				
	}

	@Override
	protected void actionButtonEditarClick() {
		DTOAgencia selecao = getSelecaoGrid();
		if (selecao != null){
			FormAgencia formAgencia = ProviderFacadeManager.formAgencia.createInstance();
			formAgencia.loadDTOAgencia(selecao);
			formAgencia.setModal(true);
			formAgencia.show();			
		}else{
			WebMessageBox.alert("Selecione a agência!");
		}
	}

	@Override
	protected void actionButtonAtualizaClick() {
		loadDados();
	}

}