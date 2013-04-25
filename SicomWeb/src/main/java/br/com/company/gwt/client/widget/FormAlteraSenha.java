package br.com.company.gwt.client.widget;

import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOUsuario;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormAlteraSenha extends Dialog {

	private FormPanel formPanel;
	private TextField<String> tfUsuario;
	private TextField<String> tfSenhaAtual;
	private TextField<String> tfNovaSenha;
	private TextField<String> tfConfirmaSenha;
	private Button btAltera;
	private DTOUsuario user;

	public FormAlteraSenha() {
		setHeading("Alteração de Senha");
		setSize(325, 190);
		setLayout(new FitLayout());
		setModal(true);
		setBodyBorder(true);
		setResizable(false);
		setButtonAlign(HorizontalAlignment.RIGHT);
		setButtons("");
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.chaveIcone16()));
		
		user = Registry.get("user");
		
		formPanel = new FormPanel();
		formPanel.setLabelWidth(95);
		formPanel.setHeaderVisible(false);
		formPanel.setHeading("");
		
		tfUsuario = new TextField<String>();
		tfUsuario.setFieldLabel("Usuário");
		tfUsuario.setEnabled(false);
		//tfUsuario.setValue(user.getUserName());
		
		formPanel.add(tfUsuario, new FormData("100%"));
		
		tfSenhaAtual = new TextField<String>();
		tfSenhaAtual.setPassword(true);
		tfSenhaAtual.setFieldLabel("Senha Atual");
		
		formPanel.add(tfSenhaAtual, new FormData("100%"));
		
		tfNovaSenha = new TextField<String>();
		tfNovaSenha.setPassword(true);
		tfNovaSenha.setFieldLabel("Nova Senha");
		
		formPanel.add(tfNovaSenha, new FormData("100%"));
		
		tfConfirmaSenha = new TextField<String>();
		tfConfirmaSenha.setPassword(true);
		tfConfirmaSenha.setFieldLabel("Confirma Senha");
		
		formPanel.add(tfConfirmaSenha, new FormData("100%"));
		
		btAltera = new Button("Alterar");
		btAltera.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				onSubmit();			
			}
		});
		
		addButton(btAltera);
		
		add(formPanel);
	}
	
	private Boolean validaCampos(){
		if (tfSenhaAtual.getValue() == null){
			WebMessageBox.alert("Informe a senha atual!");
		}
		if (tfNovaSenha.getValue() == null || !tfNovaSenha.getValue().equals(tfConfirmaSenha.getValue())){
			WebMessageBox.alert("Nova senha e confirmação não conferem!");
			return false;
		}
		return true;
	}

	protected void onSubmit() {
		if (validaCampos()){
			/*
			user.setPassword(tfSenhaAtual.getValue());
			InstanceService.USER_SERVICE.login(user, new AsyncCallback<DTOUser>() {
				
				@Override
				public void onSuccess(DTOUser dtoUser) {
					if (dtoUser.isAutenticado()){
						confirmaAlteraSenha(dtoUser);
					}else{
						WebMessageBox.alert("Senha atual não confere.");
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					WebMessageBox.error(caught.getMessage());										
				}
			});
			*/
		}
	}

	protected void confirmaAlteraSenha(DTOUsuario dtoUser) {
		/*
		dtoUser.setNewPassword(tfNovaSenha.getValue());
		InstanceService.USER_SERVICE.updateUser(dtoUser, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
			}
			@Override
			public void onSuccess(Boolean result) {
				if (result){
					WebMessageBox.info("Senha alterada com sucesso.");
					FormAlteraSenha.this.hide();				
				}else{
					WebMessageBox.info("Falha ao alterar senha.");					
				}				
			}
		});
		*/		
	}

}
