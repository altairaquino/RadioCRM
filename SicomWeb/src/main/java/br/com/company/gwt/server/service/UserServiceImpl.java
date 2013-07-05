package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import br.com.company.gwt.client.remoteinterface.UserService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoUsuario;
import br.com.company.gwt.server.entities.Usuario;
import br.com.company.gwt.shared.dto.DTOUsuario;

@Named("userService")
public class UserServiceImpl extends InputServletImpl implements UserService {
	
	@Inject private DaoUsuario daoUsuario;

	@Override
	public DTOUsuario login(DTOUsuario user) throws Exception {
		HttpSession session = getServletRequest().getSession();
		
		Usuario usuario = daoUsuario.login(user.getUserName(), user.getPassword());
		
		if (usuario != null){
			
			user.setId(usuario.getId());
			user.setAdmin(usuario.isAdmin());
			user.setNome(usuario.getNome());
			
			if (!usuario.isAtivo()){
				throw new Exception("Usuário com acesso inabilitado!");
			}
			
			session.setAttribute("user", usuario);
			
		}
		
		return user;
	}

	@Transactional
	@Override
	public Boolean updateUser(DTOUsuario user) {
		try {
			
			Usuario usuario = daoUsuario.findByPrimaryKey(user.getId());
			usuario.setSenha(user.getNewPassword());
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public List<DTOUsuario> lista(){
		List<DTOUsuario> lista = new ArrayList<DTOUsuario>();
		try {
			List<Usuario> usuarios = daoUsuario.loadAll();
			for (Usuario usuario : usuarios) {
				DTOUsuario dto = new DTOUsuario();
				dto.setId(usuario.getId());
				dto.setUserName(usuario.getNome());
				dto.setAdmin(usuario.isAdmin());
				lista.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
		
}