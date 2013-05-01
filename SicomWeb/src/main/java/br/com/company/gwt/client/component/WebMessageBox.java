package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.widget.MessageBox;

public class WebMessageBox extends MessageBox{

	public static MessageBox error(String title, String msg,
			Listener<MessageBoxEvent> callback) {
		MessageBox box = new MessageBox();
		box.setTitleHtml(title);
		box.setMessage(msg);
		box.addCallback(callback);
		box.setButtons(OK);
		box.setIcon(MessageBox.ERROR);
		box.show();
		return box;
	}

	public static MessageBox confirm(String title, String msg, Listener<MessageBoxEvent> callback) {
		MessageBox box = new MessageBox();
		box.setTitleHtml(title);
		box.setMessage(msg);
		box.addCallback(callback);
		box.setButtons(YESNOCANCEL);
		box.setIcon(MessageBox.QUESTION);
		box.show();
		return box;
	}

	public static MessageBox error(String title, String msg){
		return error(title, msg, null);
	}

	public static MessageBox alert(String title, String msg) {
		return alert(title, msg, null);
	}

	public static MessageBox confirm(String title, String msg) {
		return confirm(title, msg, null);
	}

	public static MessageBox info(String title, String msg) {
		return info(title, msg, null);
	}

	public static MessageBox error(String msg){
		return error("Erro", msg);
	}

	public static MessageBox alert(String msg) {
		return alert("Alert", msg);
	}

	public static MessageBox alert(String msg, Params params) {
		return alert("Alert", Format.substitute(msg, params));
	}

	public static MessageBox confirm(String msg) {
		return confirm("Confirmação", msg);
	}

	public static MessageBox info(String msg) {
		return info("Info", msg);
	}

	public static MessageBox error(String msg, Listener<MessageBoxEvent> callback){
		return error("Erro", msg, callback);
	}

	public static MessageBox alert(String msg, Listener<MessageBoxEvent> callback) {
		return alert("Alerta", msg, callback);
	}

	public static MessageBox confirm(String msg, Listener<MessageBoxEvent> callback) {
		return confirm("Confirmação", msg, callback);
	}

	public static MessageBox info(String msg, Listener<MessageBoxEvent> callback) {
		return info("Info", msg, callback);
	}

}