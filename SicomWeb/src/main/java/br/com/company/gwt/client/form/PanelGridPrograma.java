package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.PanelGridWindow;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.shared.dto.DTOPrograma;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PanelGridPrograma extends PanelGridWindow<DTOPrograma> {
	
	public PanelGridPrograma() {
		setHeadingHtml("Cadastro de Programa");
		grid.setAutoExpandColumn("nome");
		loadDados();
	}

	private void loadDados() {
		InstanceService.PROGRAMA_SERVICE.listAll(new AsyncCallback<List<DTOPrograma>>() {
			
			@Override
			public void onSuccess(List<DTOPrograma> result) {
				getStore().removeAll();
				getStore().add(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
			}
		});		
	}

	@Override
	protected void actionPesquisa(String consulta) {
		InstanceService.PROGRAMA_SERVICE.pesquisa(consulta, new AsyncCallback<List<DTOPrograma>>() {
			
			@Override
			public void onSuccess(List<DTOPrograma> result) {
				getStore().removeAll();
				getStore().add(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
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
		FormPrograma formPrograma = new FormPrograma();
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
				
	}

	@Override
	protected void actionButtonAtualizaClick() {
	}

}