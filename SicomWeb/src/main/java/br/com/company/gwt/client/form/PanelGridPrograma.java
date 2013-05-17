package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.PanelGridWindow;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOPrograma;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class PanelGridPrograma extends PanelGridWindow<DTOPrograma> {
	
	public PanelGridPrograma() {
		setHeadingHtml("Cadastro de Programa");
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeMicrofone16()));
		grid.setAutoExpandColumn("nome");
		loadDados();
	}

	private void loadDados() {
		painelPrincipal.mask("Aguarde carregando...");
		InstanceService.PROGRAMA_SERVICE.listAll(new AsyncCallback<List<DTOPrograma>>() {
			
			@Override
			public void onSuccess(List<DTOPrograma> result) {
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
		painelPrincipal.mask("Aguarde carregando...");
		InstanceService.PROGRAMA_SERVICE.pesquisa(consulta, new AsyncCallback<List<DTOPrograma>>() {
			
			@Override
			public void onSuccess(List<DTOPrograma> result) {
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
		ColumnConfig config = new ColumnConfig("id", "Codigo", 70);
		configs.add(config);
		config = new ColumnConfig("nome", "Nome", 250);
		configs.add(config);
		config = new ColumnConfig("valorPatrocinio", "Valor Patrocinio", 100);
		configs.add(config);
		
		return new ColumnModel(configs);
	}

	@Override
	protected void actionButtonNovoClick() {
		FormPrograma formPrograma = ProviderFacadeManager.formPrograma.createInstance();
		formPrograma.setModal(true);
		formPrograma.show();		
	}

	@Override
	protected void actionButtonRemoveClick() {
				
	}

	@Override
	protected void actionButtonReportClick() {
				
	}

	@Override
	protected void actionButtonEditarClick() {
		FormPrograma formPrograma = ProviderFacadeManager.formPrograma.createInstance();
		formPrograma.carregaForm(getSelecaoGrid());
		formPrograma.setModal(true);
		formPrograma.show();
	}

	@Override
	protected void actionButtonAtualizaClick() {
		loadDados();
	}

}