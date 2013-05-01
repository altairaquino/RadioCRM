package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.PanelGridWindow;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.shared.dto.DTOCliente;

public class PanelGridCliente extends PanelGridWindow<DTOCliente> {
	
	public PanelGridCliente() {
		setHeadingHtml("Cadastro de Clientes");
		loadDados();
	}

	private void loadDados() {
		InstanceService.CLIENTE_SERVICE.listAll(new AsyncCallback<List<DTOCliente>>() {
			
			@Override
			public void onSuccess(List<DTOCliente> result) {
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
				
	}

	@Override
	protected ColumnModel getColumnModel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig config = new ColumnConfig("id", "Codigo", 50);
		configs.add(config);
		config = new ColumnConfig("nome", "Nome", 250);
		configs.add(config);
		
		return new ColumnModel(configs);
	}

	@Override
	protected void actionButtonNovoClick() {
				
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
