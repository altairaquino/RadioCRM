package com.grupooc.radiogrfm.struts.form;

import org.apache.struts.action.ActionForm;

public class FormLogin extends ActionForm {
	
	private static final long serialVersionUID = 8550664795827715564L;
	
	private String login;
	private String senha;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
