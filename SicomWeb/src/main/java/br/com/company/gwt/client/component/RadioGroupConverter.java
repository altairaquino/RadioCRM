package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.binding.Converter;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;

public class RadioGroupConverter<D extends Enum<D>> extends Converter {

	protected RadioGroup radioGroup;
	private Class<D> clazz;

	public RadioGroupConverter(Class<D> clazz, RadioGroup field) {
		this.radioGroup = field;
		this.clazz = clazz;
	}

	@Override
	public Object convertFieldValue(Object value) {
		if (value == null) {
			return null;
		}
		String name = ((Radio)value).getData(radioGroup.getName()); 
		if (name == null) {
			return null;
		}
		D d = null;
		try {
			d = Enum.<D>valueOf(clazz, name);
		} catch (Exception e) {}
		return d;
	}

}