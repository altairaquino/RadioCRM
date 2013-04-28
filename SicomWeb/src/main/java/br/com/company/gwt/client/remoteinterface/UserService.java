package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOUsuario;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("services/userService")
public interface UserService extends RemoteService {
	
	public DTOUsuario login(DTOUsuario user) throws Exception;
	public Boolean updateUser(DTOUsuario user);
	public List<DTOUsuario> lista();
	
}