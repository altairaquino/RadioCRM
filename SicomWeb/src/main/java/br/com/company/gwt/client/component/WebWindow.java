package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public abstract class WebWindow extends Window {
	
	protected ToolBar toolBar;
	protected Button buttonLoad;
	
	public WebWindow() {
		
		setWidth(480);
		setHeight(360);
		setLayout(new FitLayout());
		setHeading("Web Window");
		setResizable(false);
		setMaximizable(true);
		setMinimizable(true);
		setIconStyle("app_window");
		
		toolBar = new ToolBar();
		
		buttonLoad = new Button("Atualiza Dados");
		buttonLoad.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				loadDados();
			}
		});
		
		toolBar.add(buttonLoad);
		
		setTopComponent(toolBar);
		
		initComponents();
		
	}
	
	protected void addToToolBar(Component component){
		toolBar.add(component);
	}
	
	protected abstract void initComponents();
	protected abstract void loadDados();

}
