package br.com.company.gwt.client.remoteinterface;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("services/userService")
public interface UserService extends RemoteService {
	
	/*
	public DTOUser login(DTOUser user);
	public Boolean updateUser(DTOUser user);
	List<DTOUser> lista();
	*/
	
}