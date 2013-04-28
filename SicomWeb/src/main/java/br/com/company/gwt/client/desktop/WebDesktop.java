package br.com.company.gwt.client.desktop;

import br.com.company.gwt.shared.dto.DTOUsuario;

import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.desktop.client.TaskBar;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;

public class WebDesktop extends Desktop {

	private TaskBar taskBar;
	private StartMenu startMenu;

	public WebDesktop() {
		super();
		taskBar = super.getTaskBar();
		startMenu = taskBar.getStartMenu();
		DTOUsuario dtoUser = Registry.get("user");
		if(dtoUser != null){
			setStartMenuHeading("user_icon_man", "Sicom Web - " + dtoUser.getUserName());
		}else{
			setStartMenuHeading("contato_window", "Sicom Web");
		}
		
		Button button = (Button)startMenu.getData("parent");
		if(button != null){
			button.setText("Iniciar");
			button.setId("botaoIniciar");
		}

	}

	public void setStartMenuHeading(String icon, String title){
		startMenu.setIconStyle(icon);
		startMenu.setHeading("<span style=\"font-size: 12pt; margin-left: 8px;\">" + title + "</span>");
	}

	public void addShortcut(Shortcut shortcut) {
		super.addShortcut(shortcut);
	}

	public void addWindow(Window window) {
		super.addWindow(window);
		window.show();
		window.toFront();
		super.getStartMenu().hide();
	}

	public TaskBar getTaskBar() {
		return taskBar;
	}

	public void setTaskBar(TaskBar taskBar) {
		this.taskBar = taskBar;
	}

	public StartMenu getStartMenu() {
		return startMenu;
	}

	public void setStartMenu(StartMenu startMenu) {
		this.startMenu = startMenu;
	}

}