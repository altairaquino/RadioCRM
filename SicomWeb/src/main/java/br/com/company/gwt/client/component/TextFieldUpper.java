package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class TextFieldUpper extends TextField<String> {
	
	public TextFieldUpper() {
		super();
		setInputStyleAttribute("text-transform", "uppercase");
	}
	
	@Override
	public String getValue() {
		String value = super.getValue();
		if (value != null){
			value = super.getValue().toUpperCase(); 
		}
		return value;
	}

}