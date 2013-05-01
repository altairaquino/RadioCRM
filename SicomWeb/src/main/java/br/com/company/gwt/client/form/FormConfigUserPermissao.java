package br.com.company.gwt.client.form;

import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOOperacao;
import br.com.company.gwt.shared.dto.DTOUsuario;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormConfigUserPermissao extends Window {

	private ListStore<DTOUsuario> listStoreUser;
	private ListStore<DTOOperacao> listStoreOperacao;

	private ListView<DTOUsuario> userList;
	private CheckBoxListView<DTOOperacao> operacaoList;

	private ContentPanel mainPanel;
	private ContentPanel panelActionEvent;
	private ContentPanel panelEventCondition;
	private StoreFilterField<DTOUsuario> filterUser;
	private StoreFilterField<DTOOperacao> filterOperacao;
	private ToolBar toolBarUsuario;
	private ToolBar toolBarOperacao;

	public FormConfigUserPermissao() {
		setHeadingHtml("Configuração de Permissões do Usuário");
		setLayout(new FitLayout());
		setBodyBorder(false);
		setBorders(false);
		setSize(640, 480);
		setResizable(false);
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeConfiguracao16()));

		initComponents();
	}

	private void initComponents() {

		panelActionEvent = new ContentPanel(new FitLayout());
		panelActionEvent.setBodyBorder(Boolean.FALSE);
		panelActionEvent.setHeadingHtml("Permissões");

		panelEventCondition = new ContentPanel(new FitLayout());
		panelEventCondition.setBodyBorder(Boolean.FALSE);
		panelEventCondition.setHeadingHtml("Usuários");

		mainPanel = new ContentPanel(new BorderLayout());
		mainPanel.setHeaderVisible(false);

		listStoreUser = new ListStore<DTOUsuario>();
		listStoreOperacao = new ListStore<DTOOperacao>();

		userList = new ListView<DTOUsuario>();
		userList.setStore(listStoreUser);
		userList.setBorders(Boolean.FALSE);
		userList.setDisplayProperty("userName");
		userList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		userList.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<DTOUsuario>>() {
			public void handleEvent(SelectionChangedEvent<DTOUsuario> be) {
				DTOUsuario selectedItem = be.getSelectedItem();
				if (selectedItem != null) {
					operacaoList.setEnabled(true);
					configureActionEventList(selectedItem);
				}
			}
		});
		
		operacaoList = new CheckBoxListView<DTOOperacao>();
		operacaoList.setEnabled(false);
		operacaoList.setBorders(Boolean.FALSE);
		operacaoList.setDisplayProperty("descricao");
		operacaoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		operacaoList.addListener(Events.Select, new Listener<ListViewEvent<DTOOperacao>>() {

			@Override
			public void handleEvent(ListViewEvent<DTOOperacao> be) {

				boolean isChecked;
				DTOOperacao dto = be.getModel();

				if (be.getTarget().getTagName().equalsIgnoreCase("input")) {
					isChecked = be.getTarget().getPropertyBoolean("checked");
					operacaoList.setChecked(dto, isChecked);
				} else {
					isChecked = operacaoList.getChecked().contains(be.getModel());
					operacaoList.setChecked(dto, !isChecked);
				}

				if (!isChecked) {
					adicionaOperacao(dto);
				} else {
					removeOperacao(dto);
				}

			}
		});

		operacaoList.setStore(listStoreOperacao);

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 250);
		westData.setCollapsible(true);
		westData.setFloatable(true);
		westData.setHideCollapseTool(true);
		westData.setSplit(true);
		westData.setMargins(new Margins(3, 0, 3, 3));

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setCollapsible(true);
		centerData.setFloatable(true);
		centerData.setHideCollapseTool(true);
		centerData.setSplit(true);
		centerData.setMargins(new Margins(3));

		filterOperacao = new StoreFilterField<DTOOperacao>() {

			@Override
			protected boolean doSelect(Store<DTOOperacao> store, DTOOperacao parent, DTOOperacao record, String property, String filter) {

				String name = record.get("descricao");
				name = name.toLowerCase();

				if (name.contains(filter.toLowerCase())) {
					return true;
				}

				return false;
			}
		};
		
		filterOperacao.bind(listStoreOperacao);

		filterUser = new StoreFilterField<DTOUsuario>() {

			@Override
			protected boolean doSelect(Store<DTOUsuario> store, DTOUsuario parent, DTOUsuario record, String property, String filter) {

				String name = record.get("userName");
				name = name.toLowerCase();
				if (name.contains(filter.toLowerCase())) {
					return true;
				}
				return false;
			}
		};

		filterUser.bind(listStoreUser);

		toolBarOperacao = new ToolBar();
		toolBarOperacao.setAlignment(HorizontalAlignment.RIGHT);

		toolBarUsuario = new ToolBar();

		toolBarUsuario.add(new Label("Pesquisar &nbsp;"));
		toolBarUsuario.add(filterUser);

		toolBarOperacao.add(new Label("Pesquisar &nbsp;"));
		toolBarOperacao.add(filterOperacao);

		panelActionEvent.add(operacaoList);
		panelEventCondition.add(userList);

		panelEventCondition.setTopComponent(toolBarUsuario);
		panelActionEvent.setTopComponent(toolBarOperacao);

		mainPanel.add(panelEventCondition, westData);
		mainPanel.add(panelActionEvent, centerData);

		add(mainPanel);

		addListener(Events.Attach, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				loadDefaultData();
				loadOperacoes();
			}
		});
	}

	protected void removeOperacao(DTOOperacao dto) {
		DTOUsuario usuario = userList.getSelectionModel().getSelectedItem();
		
		
		InstanceService.OPERACAO_SERVICE.removeOperacao(usuario.getId(), dto.getOperacaoId(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());								
			}
			
			@Override
			public void onSuccess(Boolean result) {
				Info.display("Info", "Permissão removida.");				
			}		
		});
	}

	protected void adicionaOperacao(DTOOperacao dto) {

		DTOUsuario usuario = userList.getSelectionModel().getSelectedItem();
		
		InstanceService.OPERACAO_SERVICE.adicionaOperacao(usuario.getId(), dto.getOperacaoId(), new AsyncCallback<Boolean>() {
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());								
			}
			
			@Override
			public void onSuccess(Boolean result) {
				Info.display("Info", "Permissão concedida.");				
			}		
		});
	}

	protected void configureActionEventList(DTOUsuario usuario) {
		
		InstanceService.OPERACAO_SERVICE.listaOperacaoUsuario(usuario.getId(), new AsyncCallback<List<DTOOperacao>>() {
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
			}

			public void onSuccess(List<DTOOperacao> listOperacao) {

				clearListViewOperacao();
				
				for (DTOOperacao operac : listOperacao) {
					for (DTOOperacao op : listStoreOperacao.getModels()) {
						if (op.getOperacaoId().equals(operac.getOperacaoId())) {
							operacaoList.setChecked(op, true);
						}
					}
				}
			}
		});
	}

	private void clearListViewOperacao() {
		for (DTOOperacao event : listStoreOperacao.getModels()) {
			operacaoList.setChecked(event, false);
		}
	}

	private void loadDefaultData() {
		loadUsuarios();
		loadOperacoes();
	}

	private void loadUsuarios() {
		
		InstanceService.USER_SERVICE.lista(new AsyncCallback<List<DTOUsuario>>() {
			
			@Override
			public void onSuccess(List<DTOUsuario> users) {
				listStoreUser.add(users);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
			}
		});
		
	}

	private void loadOperacoes() {
		
		InstanceService.OPERACAO_SERVICE.listaOperacoes(new AsyncCallback<List<DTOOperacao>>() {
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
			}

			public void onSuccess(List<DTOOperacao> result) {
				listStoreOperacao.removeAll();
				listStoreOperacao.add(result);
			}
		});		
	}	

}