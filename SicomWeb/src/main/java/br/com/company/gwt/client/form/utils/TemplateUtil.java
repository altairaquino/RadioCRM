package br.com.company.gwt.client.form.utils;

public class TemplateUtil {
	
	public native static String getTemplateProduto() /*-{
    return [ 
    	'<tpl for="."><div class="search-item">', 
    	'<span>{nome} - R$ {valorVendaVarejo} </span>', 
    	'</div></tpl>' 
    ].join(""); 
	}-*/;

}
