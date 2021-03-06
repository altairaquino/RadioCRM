package br.com.company.gwt.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class DTOUsuario extends BaseModelData {

	private static final long serialVersionUID = 6743308332155712937L;
	
	public Integer getId() {
		return get("id");
	}
	
	public void setId(Integer id) {
		this.set("id", id);
	}
	
	public String getUserName() {
		return get("userName");
	}
	
	public void setUserName(String userName) {
		this.set("userName", userName);
	}
	public String getPassword() {
		return get("password");
	}
	public void setPassword(String password) {
		this.set("password", password);
	}
	
	public void setNewPassword(String newPassword){
		set("newPassword", newPassword);
	}
	
	public String getNewPassword(){
		return get("newPassword");
	}

	public void setAdmin(Boolean admin) {
		set("admin", admin);		
	}
	
	public Boolean getAdmin(){
		return get("admin");
	}

	public void setNome(String nome) {
		set("nome", nome);		
	}
	
	public String getNome() {
		return get("nome");		
	}

}