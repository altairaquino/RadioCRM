package br.com.company.gwt.client.form.utils;

import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.Field;

public class FormsUtil {
	
	public static Listener<BoxComponentEvent> getListenerFocus(final Field<?> nextField){
		return new Listener<BoxComponentEvent>() {
			@Override
			public void handleEvent(BoxComponentEvent be) {
				if (nextField == null){
					be.setCancelled(true);
				}
				if (be.getKeyCode() == 13){
					nextField.focus();
				}
			}
		};
	}

}
