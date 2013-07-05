package br.com.company.gwt.client.component;

import br.com.company.gwt.client.resources.ImagensResources;

import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public abstract class PanelGridWindow<T extends ModelData> extends Window {
	
	protected ContentPanel painelPrincipal;
	protected Button buttonNovo;
	protected Button buttonEditar;
	protected ListStore<T> store;
	protected ButtonBar buttonBar;
	protected ContentPanel panelToolBar;
	protected Button buttonRemove;
	protected TextFieldUpper fieldPesquisa;
	protected Button buttonPesquisa;
	protected Grid<T> grid;
	private Button buttonAtualiza;
	
	public PanelGridWindow() {
		
		setSize(650, 420);
		setLayout(new FitLayout());
		setMinimizable(true);
		setResizable(false);
		setFrame(true);
		setCollapsible(true);
		
		iniciaComponentes();
		
	}
	
	private void iniciaComponentes() {
		
		painelPrincipal = new ContentPanel(new RowLayout(Orientation.VERTICAL));
		painelPrincipal.setHeaderVisible(false);
		painelPrincipal.setBodyBorder(false);
		painelPrincipal.setBorders(false);
		
		fieldPesquisa = new TextFieldUpper();
		fieldPesquisa.addKeyListener(new KeyListener(){
			@Override
			public void componentKeyPress(ComponentEvent event) {
				
				if (event.getKeyCode() == 13){
					buttonPesquisa.fireEvent(Events.Select);
				}
			}
		});
		
		store = new ListStore<T>();
		
		buttonBar = new ButtonBar();
		buttonBar.setAutoHeight(true);
		buttonBar.setAutoWidth(true);
		buttonBar.setSpacing(7);		
		
		panelToolBar = new ContentPanel(new FitLayout());
		panelToolBar.setHeaderVisible(false);
		panelToolBar.setBodyBorder(false);
		panelToolBar.setBorders(false);
		panelToolBar.setFrame(true);
		
		setIconStyle("app_window");
		
		buttonNovo = createButtonBar("Novo");
		buttonNovo.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconAdicionar24()));
		buttonNovo.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				actionButtonNovoClick();		
			}
		});
		
		buttonEditar = createButtonBar("Editar");
		buttonEditar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconEditar24()));
		buttonEditar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				actionButtonEditarClick();		
			}
		});
		
		buttonRemove = createButtonBar("Relatório");
		buttonRemove.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.report24()));
		buttonRemove.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				actionButtonReportClick();
			}
		});
		
		buttonAtualiza = createButtonBar("Atualiza");
		buttonAtualiza.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.refresh24()));
		buttonAtualiza.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				actionButtonAtualizaClick();
			}
		});
		
		buttonPesquisa = new Button();
		buttonPesquisa.setWidth(35);
		buttonPesquisa.setHeight(30);
		buttonPesquisa.setIconAlign(IconAlign.TOP);
		buttonPesquisa.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconPesquisa24()));
		buttonPesquisa.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				String pesquisa = fieldPesquisa.getValue();
				if (pesquisa != null && pesquisa.length() < 4){
					WebMessageBox.alert("Informe ao menos 4 letras na pesquisa!");
					ce.setCancelled(true);
				}else{
					actionPesquisa(fieldPesquisa.getValue());					
				}
			}
		});
		
		addToToolBar(buttonNovo);
		addToToolBar(buttonEditar);
		addToToolBar(buttonRemove);
		addToToolBar(buttonAtualiza);
		addToToolBar(new Label("Pesquisar:"));
		addToToolBar(fieldPesquisa);
		addToToolBar(buttonPesquisa);
		
		panelToolBar.add(buttonBar);
		
		createGrid();
		
		painelPrincipal.add(panelToolBar, new RowData(1, .16));
		painelPrincipal.add(grid, new RowData(1, .84));
		
		add(painelPrincipal);
		
	}

	private Button createButtonBar(String label){
		Button button = new Button(label);
		button.setWidth(60);
		button.setHeight(47);
		button.setIconAlign(IconAlign.TOP);
		return button;
	}
	
	private void createGrid(){
		grid = new Grid<T>(store, getColumnModel());
		grid.setStateful(true);
		grid.setLoadMask(true);
		grid.setBorders(false);
		grid.setView(new BufferView());
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<T>>(){

			@Override
			public void handleEvent(GridEvent<T> be) {
				actionButtonEditarClick();   
			}
			
		});
	}
	
	protected void addToToolBar(Component component){
		buttonBar.add(component);
	}
	
	public ListStore<T> getStore() {
		return store;
	}
	
	public T getSelecaoGrid(){
		return grid.getSelectionModel().getSelectedItem();
	}
	
	public Button getButtonNovo() {
		return buttonNovo;
	}
	
	public Button getButtonEditar() {
		return buttonEditar;
	}
	
	public Button getButtonRemove() {
		return buttonRemove;
	}
	
	public Button getButtonAtualiza() {
		return buttonAtualiza;
	}
	
	protected abstract void actionPesquisa(String consulta);
	protected abstract ColumnModel getColumnModel();
	protected abstract void actionButtonNovoClick();
	protected abstract void actionButtonRemoveClick();
	protected abstract void actionButtonReportClick();
	protected abstract void actionButtonEditarClick();
	protected abstract void actionButtonAtualizaClick();

}