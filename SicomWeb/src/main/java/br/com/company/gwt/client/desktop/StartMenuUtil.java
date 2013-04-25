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
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class StartMenuUtil {
	
	public static List<MenuItem> getMenuItems(){
		
		DTOUsuario user = Registry.get("user");
		
		List<Integer> permis = new ArrayList<Integer>();// user.getJanelas();
		
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		MenuItem menuItem = new MenuItem("Clientes");
		/*
		menuItem.addSelectionListener(getSelectionListener(ProviderFacadeManager.gridClienteWindow));
		menuItem.setIcon(IconHelper.createStyle("tabs"));
	    menuItems.add(menuItem);
		
	    menuItem = new MenuItem("Consulta Vendas");
		menuItem.addSelectionListener(getSelectionListener(ProviderFacadeManager.formVendasLista));
		menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeVenda16()));
	    menuItems.add(menuItem);

	    menuItem = new MenuItem("Cadastros");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));

		    Menu sub = new Menu();
	
		    MenuItem item = new MenuItem("Produtos");
		    item.setIcon(IconHelper.createStyle("icone_produto"));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.gridProdutoWindow));
		   
		    if (permis.contains(1)){
		    	sub.add(item);
		    }
		    
		    item = new MenuItem("Clientes");
		    item.setIcon(IconHelper.createStyle("icone_cliente"));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.gridClienteWindow));
		    sub.add(item);
		    
		    item = new MenuItem("Categorias");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeForm16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formCategoriaWindow));
		    sub.add(item);

	    menuItem.setSubMenu(sub);
	    
	    menuItems.add(menuItem);
	    
	    menuItem = new MenuItem("Impressões");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeImpressora16()));
	    
		    sub = new Menu();
		    
		    item = new MenuItem("Etiquetas de Preço");
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeImpressora16()));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formImprimeEtiqueta));
		    sub.add(item);
		    
		menuItem.setSubMenu(sub);
	    
	    menuItems.add(menuItem);
	    
	    menuItem = new MenuItem("Movimento");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeMovimento16()));

		    sub = new Menu();
	
		    item = new MenuItem("Entrada de Produtos");
		    item.setIcon(IconHelper.createStyle("tabs"));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formEntradaProdutoWindow));
		    sub.add(item);
		    
		    item = new MenuItem("Transferência de Produtos");
		    item.setIcon(IconHelper.createStyle("icon_transferencia"));
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formTransferenciaEstoque));
		    sub.add(item);
	    
	    menuItem.setSubMenu(sub);
	    
	    if (permis.contains(3)){
	    	menuItems.add(menuItem);
	    }
	    
	    menuItem = new MenuItem("Relatórios");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
	    
		    sub = new Menu();
		    
		    item = new MenuItem("Inventário de Estoque");
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formRelatorioLoja));
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
		    		    
		    sub.add(item);
	    
		    item = new MenuItem("Gerenciais (Período)");
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formRelatorioLojaData));
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
		    
		    sub.add(item);
		    
	    menuItem.setSubMenu(sub);
	    
	    if (permis.contains(2)){
	    	menuItems.add(menuItem);
	    }
	    
	    menuItem = new MenuItem("Manutenção");
	    menuItem.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeConfiguracao16()));
	    
		    sub = new Menu();
		    
		    item = new MenuItem("Preço e Estoque");
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formAlteraPreco));
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeCaixa16()));
		    		    
		    sub.add(item);
	    
		    /*
		    item = new MenuItem("Relatórios por Loja (Período)");
		    item.addSelectionListener(getSelectionListener(ProviderFacadeManager.formRelatorioLojaData));
		    item.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
		    
		    sub.add(item);
		    
	    menuItem.setSubMenu(sub);
		 */
	    
	    if (permis.contains(5)){
	    	menuItems.add(menuItem);
	    }
	    
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