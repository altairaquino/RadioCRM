package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOUsuario;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	
	void login(DTOUsuario user, AsyncCallback<DTOUsuario> callback);

	void updateUser(DTOUsuario user, AsyncCallback<Boolean> callback);

	void lista(AsyncCallback<List<DTOUsuario>> callback);	

}