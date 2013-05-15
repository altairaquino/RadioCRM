package br.com.company.gwt.client.desktop;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.component.ComponentProvider;
import br.com.company.gwt.client.component.WebUtil;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;

import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;

public class ShortCutUtil {

	public static List<Shortcut> getShortcuts(){

		List<Shortcut> shortCuts = new ArrayList<Shortcut>();

		SelectionListener<ComponentEvent> shortcutListener = new SelectionListener<ComponentEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void componentSelected(ComponentEvent ce) {
				Shortcut shortcut = (Shortcut)ce.getSource();
				ComponentProvider<? extends Window> window = (ComponentProvider<? extends Window>) shortcut.getData("provider");
				if (window != null){
					DesktopAppView.getInstance().addWindow(window.createInstance());
				}
			}
		};
		
		
		shortCuts.add(WebUtil.createShortcut("Clientes", "cliente-shortcut", ProviderFacadeManager.panelGridCliente));
		shortCuts.add(WebUtil.createShortcut("Programas", "contato-shortcut", ProviderFacadeManager.panelGridPrograma));
		shortCuts.add(WebUtil.createShortcut("Contratos", "contrato-shortcut", ProviderFacadeManager.panelGridPrograma));
		shortCuts.add(WebUtil.createShortcut("Agências", "agencia-shortcut", ProviderFacadeManager.panelGridCliente));
		
		
		/*
		DTOUsuario user = Registry.get("user");
		
		List<Integer> permis = new ArrayList<Integer>(); // user.getJanelas();

		
		if (permis.contains(1)){
			shortCuts.add(WebUtil.createShortcut("Produtos", "produto-shortcut", ProviderFacadeManager.gridProdutoWindow));			
		}
		
		shortCuts.add(WebUtil.createShortcut("Vendas", "venda-shortcut", ProviderFacadeManager.formPreVendaWindow));
		shortCuts.add(WebUtil.createShortcut("Meu Estoque", "caixa_produto-shortcut", ProviderFacadeManager.formConsultaEstoque));
		
		if (permis.contains(4)){
			shortCuts.add(WebUtil.createShortcut("Caixa", AbstractImagePrototype.create(ImagensResources.INSTANCE.atalhoCaixa64()) , ProviderFacadeManager.formCaixa));			
		}
		
		 */
		for (Shortcut shortcut : shortCuts) {
			shortcut.addSelectionListener(shortcutListener);
		}
		
		return shortCuts;
	}

}