package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.company.gwt.client.remoteinterface.UserService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.legacy.bean.BeanUsuario;
import br.com.company.gwt.server.legacy.model.ModelUsuario;
import br.com.company.gwt.shared.dto.DTOUsuario;

@Named("userService")
public class UserServiceImpl extends InputServletImpl implements UserService {
	
	@Inject private ModelUsuario modelUsuario;

	@Override
	public DTOUsuario login(DTOUsuario user) throws Exception {
		HttpSession session = getServletRequest().getSession();
		
		BeanUsuario usuario = modelUsuario.autenticaUsuario(user.getUserName(), user.getPassword());
		
		if (usuario != null){
			
			user.setId(Integer.parseInt(usuario.getUsncodg()));
			
			if (usuario.getUslativ().equals("F")){
				throw new Exception("Usuário com acesso inabilitado!");
			}
			
			session.setAttribute("user", usuario);
			
			/*
			List<Operacao> janelas = usuario.getOperacoes();
			
			for (Operacao janela: janelas) {
				user.getJanelas().add(janela.getOperacaoId());
			}
			*/
			
		}
		
		return user;
	}

	@Override
	public Boolean updateUser(DTOUsuario user) {
		try {			
			modelUsuario.alteraSenha(user.getId(), user.getNewPassword());
			
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
			List<BeanUsuario> usuarios = modelUsuario.getUsuarios();
			for (BeanUsuario usuario : usuarios) {
				DTOUsuario dto = new DTOUsuario();
				dto.setId(Integer.parseInt(usuario.getUsncodg()));
				dto.setUserName(usuario.getUscnome());
				lista.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
		
}