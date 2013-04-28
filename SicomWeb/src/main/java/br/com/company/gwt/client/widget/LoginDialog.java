package br.com.company.gwt.client.widget;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.remoteinterface.UserServiceAsync;
import br.com.company.gwt.shared.dto.DTOUsuario;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoginDialog extends Dialog {

	protected TextField<String> userName;
	protected TextField<String> password;
	protected Button login;
	protected Status status;
	
	private static UserServiceAsync service = InstanceService.USER_SERVICE;

	public LoginDialog() {
		FormLayout layout = new FormLayout();
		layout.setLabelWidth(90);
		layout.setDefaultWidth(155);
		setLayout(layout);

		setButtonAlign(HorizontalAlignment.LEFT);
		setButtons("");
		setIconStyle("login_window");
		setHeading("Sicom Web - 1.0");
		setModal(true);
		setBodyBorder(true);
		setBodyStyle("padding: 8px; background: none");
		setWidth(300);
		setResizable(false);

		userName = new TextField<String>();
		userName.setMinLength(4);
		userName.setFieldLabel("Usuário");
		userName.addListener(Events.KeyPress, keyLogin);
		add(userName);

		password = new TextField<String>();
		password.setMinLength(4);
		password.setPassword(true);
		password.setFieldLabel("Senha");
		password.addListener(Events.KeyPress, keyLogin);
		
		add(password);

		setFocusWidget(userName);
		
	}
	
	Listener<BoxComponentEvent> keyLogin = new Listener<BoxComponentEvent>(){
		public void handleEvent(BoxComponentEvent e) {
			if(e.getKeyCode() == 13){
				onSubmit();
			}
		};
	};

	@Override
	protected void createButtons() {
		super.createButtons();
		status = new Status();
		status.setBusy("Aguarde Carregando...");
		status.hide();
		status.setAutoWidth(true);
		getButtonBar().add(status);

		getButtonBar().add(new FillToolItem());

		login = new Button("Entrar");
//		login.disable();
		login.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				onSubmit();
			}
		});

		addButton(login);	

	}

	protected void onSubmit() {
		final DTOUsuario user = new DTOUsuario();
		user.setUserName(userName.getValue());
		user.setPassword(password.getValue());
		
		service.login(user, new AsyncCallback<DTOUsuario>() {
			
			@Override
			public void onSuccess(DTOUsuario dto) {
				
				if (dto.getId() == null){
					WebMessageBox.alert("Usuário ou senha inválidos!");
					return;
				}
				Registry.register("user", dto);
					status.show();
					getButtonBar().disable();
					Timer t = new Timer() {
						
						@Override
						public void run() {
							LoginDialog.this.hide();
						}						
					};
					t.schedule(2000);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.info("Erro: "+ caught.getMessage());				
			}
			
		});
	}

	protected boolean hasValue(TextField<String> field) {
		return field.getValue() != null && field.getValue().length() > 0;
	}

	protected void validate() {
		login.setEnabled(hasValue(userName) && hasValue(password)
				&& password.getValue().length() > 3);
	}

}