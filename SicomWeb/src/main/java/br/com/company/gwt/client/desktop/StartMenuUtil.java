package br.com.company.gwt.client.desktop;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.component.ComponentProvider;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOUsuario;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class StartMenuUtil {
	
	public static List<MenuItem> getMenuItems(){
		
		DTOUsuario user = Registry.get("user");
		
		//List<Integer> permis = new ArrayList<Integer>();// user.getJanelas();
		
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		MenuItem menuItem = new MenuItem("Consulta Contratos");
		menuItem.addSelectionListener(getSelectionListener(ProviderFacadeManager.panelGridContrato));
		menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeVenda16()));
		menuItems.add(menuItem);
		
		
	    menuItem = new MenuItem("Cadastros");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));

		    Menu sub = new Menu();
	
		    MenuItem item = new MenuItem("Clientes");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeCliente16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.panelGridCliente));
		    sub.add(item);

		    item = new MenuItem("Programas");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeMicrofone16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.panelGridPrograma));
		    sub.add(item);
		    
		    item = new MenuItem("Agência/Corretor");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.panelGridAgencia));
		    sub.add(item);
		    
		    item = new MenuItem("Tipo de Contrato");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formTipoContrato));
		    
		    if (user.getAdmin()){
		    	sub.add(item);  	
		    }
		    
		    item = new MenuItem("Contratos");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.panelGridContrato));
		    sub.add(item);

	    menuItem.setSubMenu(sub);
	    
	    menuItems.add(menuItem);
	    
	    menuItem = new MenuItem("Relatórios");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
	    
		    sub = new Menu();
		    
		    item = new MenuItem("Gerenciais (Período)");
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formRelatorio));
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
		    
		    sub.add(item);
		    
	    menuItem.setSubMenu(sub);
	    
	    menuItems.add(menuItem);	    
	    
	    return menuItems;
	}
	
	private static SelectionListener<MenuEvent> getSelectionListener(final ComponentProvider<? extends Window> provider){
		SelectionListener<MenuEvent> menuListener = new SelectionListener<MenuEvent>() {
			@Override
			public void componentSelected(MenuEvent me) {
				if (provider != null){
					DesktopAppView.getInstance().addWindow(provider.createInstance());
				}
			}
		};
		return menuListener;
	}

}