package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.binding.Converter;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;

public class SimpleComboBoxConverter<D> extends Converter {

	protected SimpleComboBox<D> comboBox;

	public SimpleComboBoxConverter(SimpleComboBox<D> field) {
		this.comboBox = field;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object convertFieldValue(Object value) {
		if (value == null) {
			return null;
		}
		
		return ((SimpleComboValue<D>)value).getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object convertModelValue(Object value) {
		return comboBox.findModel((D)value);
	}

}