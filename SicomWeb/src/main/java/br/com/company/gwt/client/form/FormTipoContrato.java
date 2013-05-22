package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.TextFieldUpper;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOTipoContrato;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormTipoContrato extends Window {

	private TextFieldUpper tfNome;
	private Grid<DTOTipoContrato> grid;
	private FieldSet fsDados;
	private ContentPanel mainPanel;
	private ListStore<DTOTipoContrato> storeTipoContrato;
	private Button button;

	public FormTipoContrato() {
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));
		setResizable(false);
		setMinimizable(true);
		setHeadingHtml("Cadastro de Tipo de Contrato");
		setSize(454, 311);
		setLayout(new FitLayout());
		
		mainPanel = new ContentPanel();
		mainPanel.setHeaderVisible(false);
		mainPanel.setFrame(true);
		mainPanel.setLayout(new FitLayout());
		
		fsDados = new FieldSet();
		fsDados.setLayout(new AbsoluteLayout());
		fsDados.setHeadingHtml("Dados");
		
		tfNome = new TextFieldUpper();
		tfNome.setSize("370px", "22px");
		fsDados.add(tfNome, new AbsoluteData(0, 17));
		
		fsDados.add(new LabelField("Nome:"), new AbsoluteData(0, 0));
		
		button = new Button();
		button.setSize("24px", "24px");
		button.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeAdiciona16()));
		button.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (tfNome.getValue() != null){
					salvar();
				}else{
					WebMessageBox.alert("Informe o nome do tipo de contrato!");
				}
			}
		});
		fsDados.add(button, new AbsoluteData(376, 15));
		
		storeTipoContrato = new ListStore<DTOTipoContrato>();
		
		grid = new Grid<DTOTipoContrato>(storeTipoContrato, getColumnModel());
		grid.setSize("404px", "173px");
		grid.setBorders(true);
		grid.setAutoExpandColumn("nome");
		fsDados.add(grid, new AbsoluteData(0, 45));

		mainPanel.add(fsDados);
		
		add(mainPanel);
		
		loadDados();
		
		tfNome.focus();
	}
	
	private void loadDados() {
		mainPanel.mask("Carregando dados. Aguarde...");
		InstanceService.TIPOCONTRATO_SERVICE.listAll(new AsyncCallback<List<DTOTipoContrato>>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				mainPanel.unmask();
			}
			@Override
			public void onSuccess(List<DTOTipoContrato> result) {
				storeTipoContrato.add(result);
				mainPanel.unmask();
			}
		});
	}

	private ColumnModel getColumnModel(){
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig columnConfig = new ColumnConfig("id", "Id", 50);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("nome", "Nome", 150);
		configs.add(columnConfig);
		
		CheckColumnConfig checkColumnConfig = new CheckColumnConfig("ativo", "Ativo", 70);
		configs.add(checkColumnConfig);
		
		return new ColumnModel(configs);
	}
	
	private void salvar(){
		mainPanel.mask("Salvando dados...");
		InstanceService.TIPOCONTRATO_SERVICE.salvar(getDTOTipoContrato(), new AsyncCallback<DTOTipoContrato>() {
			
			@Override
			public void onSuccess(DTOTipoContrato result) {
				storeTipoContrato.add(result);
				tfNome.clear();
				mainPanel.unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				mainPanel.unmask();
			}
		});
	}

	private DTOTipoContrato getDTOTipoContrato() {
		DTOTipoContrato dto = new DTOTipoContrato();
		dto.setNome(tfNome.getValue());
		dto.setAtivo(true);
		return dto;
	}
	
}