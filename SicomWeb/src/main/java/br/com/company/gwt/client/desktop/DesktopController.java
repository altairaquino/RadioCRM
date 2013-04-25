package br.com.company.gwt.client.desktop;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class DesktopController extends Controller {

	private DesktopAppView desktopAppView;

	public DesktopController(){
		
		registerEventTypes(DesktopAppEvents.Init);
		registerEventTypes(DesktopAppEvents.Login);
		
		desktopAppView = new DesktopAppView(this);
		
	}

	@Override
	public void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == DesktopAppEvents.Init) {
			onInit(event);
		} else if (type == DesktopAppEvents.Login) {
			onLogin(event);
		}
	}

	private void onLogin(AppEvent event) {
		forwardToView(desktopAppView, event);
	}

	private void onInit(AppEvent event) {
		forwardToView(desktopAppView, event);
	}

	public DesktopAppView getDesktopAppView() {
		return desktopAppView;
	}

	public void setDesktopAppView(DesktopAppView desktopAppView) {
		this.desktopAppView = desktopAppView;
	}

}