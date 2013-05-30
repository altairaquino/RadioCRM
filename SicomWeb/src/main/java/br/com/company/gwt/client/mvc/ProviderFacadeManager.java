package br.com.company.gwt.client.mvc;

import br.com.company.gwt.client.component.ComponentProvider;
import br.com.company.gwt.client.form.FormAgencia;
import br.com.company.gwt.client.form.FormCliente;
import br.com.company.gwt.client.form.FormConfigUserPermissao;
import br.com.company.gwt.client.form.FormPrograma;
import br.com.company.gwt.client.form.FormTipoContrato;
import br.com.company.gwt.client.form.PanelGridAgencia;
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
	
	public static ComponentProvider<FormPrograma> formPrograma = new ComponentProvider<FormPrograma>() {
		
		@Override
		public FormPrograma createInstance() {
			return new FormPrograma();
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
	
	public static ComponentProvider<PanelGridAgencia> panelGridAgencia = new ComponentProvider<PanelGridAgencia>() {
		
		@Override
		public PanelGridAgencia createInstance() {
			return new PanelGridAgencia();
		}
		
	};
	
	public static ComponentProvider<FormAgencia> formAgencia = new ComponentProvider<FormAgencia>() {
		
		@Override
		public FormAgencia createInstance() {
			return new FormAgencia();
		}
		
	};
	
	public static ComponentProvider<FormCliente> formCliente = new ComponentProvider<FormCliente>() {
		
		@Override
		public FormCliente createInstance() {
			return new FormCliente();
		}
		
	};
	
	public static ComponentProvider<FormTipoContrato> formTipoContrato = new ComponentProvider<FormTipoContrato>() {
		
		@Override
		public FormTipoContrato createInstance() {
			return new FormTipoContrato();
		}
		
	};

}