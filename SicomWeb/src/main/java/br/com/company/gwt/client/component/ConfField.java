package br.com.company.gwt.client.component;

public class ConfField{
	
	private String label;
	private String name;
	String valueField;
	String displayField;
	
	public ConfField(String label, String name) {
		this.label = label;
		this.name = name;
	}
	
	public ConfField(String label, String name, String valueField, String displayField) {
		this(label, name);
		this.valueField = valueField;
		this.displayField = displayField;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}
	public String getValueField() {
		return valueField;
	}
	
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}
	
	public String getDisplayField() {
		return displayField;
	}
	
	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}
	
}
