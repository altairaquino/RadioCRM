package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Frame;

public class WebWindowReport extends Window{
	
	public WebWindowReport(String title, String urlPdf){
		
		title = (title == null || title.trim().equals(""))?"Relatório":title;
		
		this.setSize(640, 480);
		this.setHeadingHtml(title);
		this.setResizable(true);
		this.setMaximizable(true);
		this.setMinimizable(true);
		this.setModal(false);

		this.setLayout(new FitLayout());		
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("background-color","#d8da3d");
		
		Frame frame = new Frame(urlPdf);
	    frame.setWidth("100%");
	    frame.setHeight("100%");
	    panel.add(frame);
	    add(panel);
	}
	
}