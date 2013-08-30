package br.com.company.gwt.client.desktop;

import br.com.company.gwt.client.component.WebUtil;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.client.widget.LoginDialog;
import br.com.company.gwt.shared.dto.DTOUsuario;

import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class DesktopAppView extends View {

	private LoginDialog dialog;
	private static WebDesktop webDesktop;

	public DesktopAppView(Controller controller) {
		super(controller);
	}

	protected void initialize() {
		GXT.hideLoadingPanel("loading");
		login();
	}

	protected void login() {
		dialog = new LoginDialog();
		dialog.setClosable(false);
		dialog.addListener(Events.Hide, new Listener<WindowEvent>() {
			public void handleEvent(WindowEvent be) {
				Dispatcher.forwardEvent(DesktopAppEvents.Init);
			}
		});
		dialog.show();
	}

	@Override
	protected void handleEvent(AppEvent event) {
		if (event.getType() == DesktopAppEvents.Init) {
			initUI();
		}
	}
	
	public void init() {
		webDesktop = new WebDesktop();
	}
	
	public static WebDesktop getInstance(){
		return webDesktop;
	}

	private void initUI() {

		init();
		
		createTool();
		
		addShortcuts();
		
		addMenus();

	}

	private void addMenus() {
		for(final MenuItem mi: StartMenuUtil.getMenuItems()){
			webDesktop.getStartMenu().add(mi);
		}		
	}

	private void addShortcuts() {
		for(final Shortcut shortcut: ShortCutUtil.getShortcuts()){
			webDesktop.addShortcut(shortcut);
		}
		
	}

	private void createTool() {
		StartMenu startMenu = webDesktop.getStartMenu();
//		MenuItem menuItemSys = WebUtil.createMenuItem("Preferencias", "control-sys-icon");
//
//		Menu subMenu = new Menu();
//		subMenu.add(WebUtil.createMenuItem("perfil", "profile-icon", null, ""));
//		subMenu.add(WebUtil.createMenuItem("configuracoes", "settings-icon", null, ""));
//		subMenu.add(WebUtil.createMenuItem("parametrosDeIntegracao", "cadastro-icon", null, ""));
//		subMenu.add(WebUtil.createMenuItem("configuracaoLinha", "cadastro-icon", null, ""));
//
//		menuItemSys.setSubMenu(subMenu);
//
//		startMenu.addTool(menuItemSys);
		createUpdateSenha();
		startMenu.addToolSeperator();
		createAboutTool();
		startMenu.addToolSeperator();
		createExitTool();
		startMenu.setToolWidth(125);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createExitTool(){
		MenuItem menuItem = WebUtil.createMenuItem("Sair do Sistema", "icon_logout");
		SelectionListener listenerExit = new SelectionListener<ComponentEvent>() {

			public void componentSelected(ComponentEvent ce) {
				Registry.unregister("user");
				WebUtil.reloadPage();
			}

		};
		menuItem.addSelectionListener(listenerExit);
		webDesktop.getStartMenu().addTool(menuItem);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createAboutTool(){
		MenuItem menuItem = WebUtil.createMenuItem("Sobre", "icon_about");
		SelectionListener listenerExit = new SelectionListener<ComponentEvent>() {

			public void componentSelected(ComponentEvent ce) {
				ProviderFacadeManager.dialogoSobre.createInstance().show();
			}

		};
		menuItem.addSelectionListener(listenerExit);
		webDesktop.getStartMenu().addTool(menuItem);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createUpdateSenha(){
		MenuItem menuItem = WebUtil.createMenuItem("Altera Senha", "icon_about");
		menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.chaveIcone16()));
		SelectionListener listenerOpenWindow = new SelectionListener<ComponentEvent>() {
			
			public void componentSelected(ComponentEvent ce) {
				ProviderFacadeManager.formAlteraSenha.createInstance().show();
			}
			
		};
		menuItem.addSelectionListener(listenerOpenWindow);
		webDesktop.getStartMenu().addTool(menuItem);
		
		DTOUsuario user = Registry.get("user");
		
		menuItem = WebUtil.createMenuItem("Conf. Usuários", "icon_about");
		menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeConfiguracao16()));
		listenerOpenWindow = new SelectionListener<ComponentEvent>() {
			
			public void componentSelected(ComponentEvent ce) {
				DesktopAppView.getInstance().addWindow(ProviderFacadeManager.formConfigUserPermissao.createInstance());
			}
			
		};
		menuItem.addSelectionListener(listenerOpenWindow);
		if (user.getAdmin()){
			webDesktop.getStartMenu().addTool(menuItem);			
		}
		
	}

}