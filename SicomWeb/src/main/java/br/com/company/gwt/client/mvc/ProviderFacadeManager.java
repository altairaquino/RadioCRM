package br.com.company.gwt.client.mvc;

import br.com.company.gwt.client.component.ComponentProvider;
import br.com.company.gwt.client.form.FormConfigUserPermissao;
import br.com.company.gwt.client.form.PanelGridCliente;
import br.com.company.gwt.client.form.PanelGridPrograma;
import br.com.company.gwt.client.widget.DialogoSobre;
import br.com.company.gwt.client.widget.FormAlteraSenha;

public class ProviderFacadeManager {
	
	public static ComponentProvider<FormAlteraSenha> formAlteraSenha = new ComponentProvider<FormAlteraSenha>() {
		
		@Override
		public FormAlteraSenha createInstance() {
			return new FormAlteraSenha();
		}
	};
	
	public static ComponentProvider<FormConfigUserPermissao> formConfigUserPermissao = new ComponentProvider<FormConfigUserPermissao>() {
		
		@Override
		public FormConfigUserPermissao createInstance() {
			return new FormConfigUserPermissao();
		}
	};	
		
	public static ComponentProvider<DialogoSobre> dialogoSobre = new ComponentProvider<DialogoSobre>() {

		@Override
		public DialogoSobre createInstance() {
			return new DialogoSobre();
		}
		
	};
	
	public static ComponentProvider<PanelGridCliente> panelGridCliente = new ComponentProvider<PanelGridCliente>() {
		
		@Override
		public PanelGridCliente createInstance() {
			return new PanelGridCliente();
		}
		
	};
	
	public static ComponentProvider<PanelGridPrograma> panelGridPrograma = new ComponentProvider<PanelGridPrograma>() {
		
		@Override
		public PanelGridPrograma createInstance() {
			return new PanelGridPrograma();
		}
		
	};

}