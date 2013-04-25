package br.com.company.gwt.client.widget;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class DialogoSobre extends Dialog {
	
	private ContentPanel contentPanel = new ContentPanel();

	public DialogoSobre() {
		setSize(322, 250);
		setModal(true);
		setResizable(false);
		setHeading("Sobre o Sistema");
		setButtons("");
		setLayout(new FitLayout());
		add(getPanelTemplate());
	}
	
	protected ContentPanel getPanelTemplate() {
		ContentPanel contentPanel = new ContentPanel();	
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setBodyBorder(false);
		contentPanel.setAnimCollapse(false);
		contentPanel.setCollapsible(false);
		contentPanel.setWidth(300);
		contentPanel.setHeight(300);
		contentPanel.addText(getTemplate());
		return contentPanel;
	}

	private native String getTemplate() /*-{ 
    var html = [ 
    '<p style="">Genial Estoque</p>', 
    '<p>Company: Genial Sistemas</p>', 
    '<p>Versão: 1.0.0</p>', 
    '<p>Genial Estoque facilitando o gerenciamento de sua empresa.</p>', 
    '<p>Copyright 2013 Genial Sistemas. Todos os direitos reservados.</p>', 
    '</tpl>' 
    ]; 
    return html.join(""); 
  }-*/; 
	
}
