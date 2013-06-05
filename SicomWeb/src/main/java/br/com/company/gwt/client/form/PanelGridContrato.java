package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOCliente;
import br.com.company.gwt.shared.dto.DTOContrato;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class PanelGridContrato extends Window {

	private ContentPanel mainPanel;
	private ContentPanel panelTool;
	private Button btnRelatrio;
	private Grid<DTOContrato> gridContratos;
	private ListStore<DTOContrato> storeContratos;
	private Button btnPesquisa;
	private DateField tfData;
	private Button btnEditar;
	private Button btnNovo;
	private ComboBox<DTOCliente> comboCliente;
	private ListStore<DTOCliente> storeCliente;

	public PanelGridContrato() {
		
		setCollapsible(true);
		setResizable(false);
		setMinimizable(true);
		setHeadingHtml("Contratos");
		setSize(700, 459);
		setLayout(new FitLayout());
		
		mainPanel = new ContentPanel();
		mainPanel.setFrame(true);
		mainPanel.setHeaderVisible(false);
		mainPanel.setLayout(new RowLayout(Orientation.VERTICAL));
		
		panelTool = new ContentPanel();
		panelTool.setHeaderVisible(false);
		panelTool.setFrame(true);
		panelTool.setCollapsible(true);
		panelTool.setLayout(new AbsoluteLayout());
		
		btnRelatrio = new Button("Relatório");
		btnRelatrio.setSize("44px", "44px");
		btnRelatrio.setIconAlign(IconAlign.TOP);
		btnRelatrio.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.report24()));
		btnRelatrio.setEnabled(false);

		panelTool.add(btnRelatrio, new AbsoluteData(606, 6));
		
		btnEditar = new Button("Editar");
		btnEditar.setSize("44px", "44px");
		btnEditar.setIconAlign(IconAlign.TOP);
		btnEditar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconEditar24()));
		btnEditar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				FormContrato formContrato = ProviderFacadeManager.formContrato.createInstance();
				formContrato.setModal(true);
				formContrato.loadDTOContrato(getSelecaoGrid());
				formContrato.show();
			}
		});
		panelTool.add(btnEditar, new AbsoluteData(556, 6));
		
		btnNovo = new Button("Novo");
		btnNovo.setSize("44px", "44px");
		btnNovo.setIconAlign(IconAlign.TOP);
		btnNovo.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconAdicionar24()));
		btnNovo.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				FormContrato formContrato = ProviderFacadeManager.formContrato.createInstance();
				formContrato.setModal(true);
				formContrato.show();
			}
		});
		panelTool.add(btnNovo, new AbsoluteData(506, 6));

		panelTool.add(new LabelField("Cliente:"), new AbsoluteData(6, 6));
		
		RpcProxy<PagingLoadResult<DTOCliente>> proxyCliente = new RpcProxy<PagingLoadResult<DTOCliente>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOCliente>> callback) {
				InstanceService.CLIENTE_SERVICE.loadPagingList((PagingLoadConfig) loadConfig, callback);
			}
		};

	    PagingLoader<PagingLoadResult<ModelData>> loaderCliente = new BasePagingLoader<PagingLoadResult<ModelData>>(proxyCliente);
		
		storeCliente = new ListStore<DTOCliente>(loaderCliente);
		
		comboCliente = new ComboBox<DTOCliente>();
		comboCliente.setStore(storeCliente);
		comboCliente.setSize("286px", "22px");
		comboCliente.setTemplate(getTemplateNome());
		comboCliente.setValueField("id");
		comboCliente.setDisplayField("nome");
		comboCliente.setItemSelector("div.search-item");
		comboCliente.setHideTrigger(true);
		comboCliente.setLoadingText("Carregando...");
		comboCliente.setPageSize(10);
		
		panelTool.add(comboCliente, new AbsoluteData(6, 28));
		
		tfData = new DateField();
		tfData.setSize("119px", "22px");
		panelTool.add(tfData, new AbsoluteData(298, 28));
		
		btnPesquisa = new Button();
		btnPesquisa.setSize("44px", "44px");
		btnPesquisa.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconPesquisa24()));
		btnPesquisa.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				pesquisa();				
			}
		});
		panelTool.add(btnPesquisa, new AbsoluteData(423, 6));
		
		panelTool.add(new LabelField("Data:"), new AbsoluteData(300, 6));
		mainPanel.add(panelTool, new RowData(Style.DEFAULT, 75.0, new Margins(3)));
		
		storeContratos = new ListStore<DTOContrato>(); 
		
		gridContratos = new Grid<DTOContrato>(storeContratos, getColumnModel());
		gridContratos.setBorders(true);
		gridContratos.setAutoExpandColumn("cliente");
		
		mainPanel.add(gridContratos, new RowData(Style.DEFAULT, 340.0, new Margins(3)));
		
		add(mainPanel);
		
		loadContratos();
		
	}

	protected void pesquisa() {
		// TODO Auto-generated method stub
		
	}

	private void loadContratos() {
		mainPanel.mask("Carregando dados. Aguarde...");
		InstanceService.CONTRATO_SERVICE.listAll(new AsyncCallback<List<DTOContrato>>() {
			
			@Override
			public void onSuccess(List<DTOContrato> contratos) {
				storeContratos.add(contratos);
				mainPanel.unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				mainPanel.unmask();
			}
		});
	}

	private ColumnModel getColumnModel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig columnConfig = new ColumnConfig("id", "Código", 50);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("cliente", "Cliente", 250);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("dataInicio", "Início", 80);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("dataTermino", "Término", 80);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("valor", "Valor", 80);
		configs.add(columnConfig);
		
		return new ColumnModel(configs);
	}
	
	private DTOContrato getSelecaoGrid(){
		return gridContratos.getSelectionModel().getSelectedItem();
	}
	
	private native String getTemplateNome() /*-{
	return [ 
    	'<tpl for="."><div class="search-item">', 
    	'<span>{nome}</span>', 
    	'</div></tpl>' 
    ].join(""); 
	}-*/;
	
}