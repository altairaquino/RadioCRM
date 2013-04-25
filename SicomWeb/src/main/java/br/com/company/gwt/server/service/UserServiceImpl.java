package br.com.company.gwt.server.service;

import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.UserService;
import br.com.company.gwt.server.InputServletImpl;

@Named("userService")
public class UserServiceImpl extends InputServletImpl implements UserService {
	
	

	/*
	@Override
	@Transactional
	public DTOUser login(DTOUser user) {
		boolean loginSucess = false;
		HttpSession session = getServletRequest().getSession();
		
		Usuario usuario = daoUser.login(user.getUserName(), user.getPassword());
		loginSucess = (usuario != null);
		user.setAutenticado(loginSucess);
		if (loginSucess){
			
			user.setUserId(usuario.getId());
			user.setAdmin(usuario.getAdmin());
			user.setFuncionarioId(usuario.getFuncionario().getFuncionarioId());
			
			session.setAttribute("user", usuario);
			
			List<Operacao> janelas = usuario.getOperacoes();
			
			for (Operacao janela: janelas) {
				user.getJanelas().add(janela.getOperacaoId());
			}
			
			LogLogin logLogin = new LogLogin();
			logLogin.setUsuario(usuario);
			logLogin.setDataHora(new Date());
			logLogin.setRequestIP(getServletRequest().getRemoteHost());
			daoLogLogin.store(logLogin);
		}else{
			LogLoginInvalido logLogin = new LogLoginInvalido();
			logLogin.setUsuario(user.getUserName());
			logLogin.setSenha(user.getPassword());
			logLogin.setDataHora(new Date());
			logLogin.setRequestIP(getServletRequest().getRemoteHost());
			daoLogLoginInvalido.store(logLogin);
		}
		
		return user;
	}

	@Transactional
	@Override
	public Boolean updateUser(DTOUser user) {
		try {
			Usuario usuario = daoUser.findByPrimaryKey(user.getUserId());
			usuario.setSenha(user.getNewPassword());
			
			daoUser.store(usuario);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public List<DTOUser> lista(){
		List<DTOUser> lista = new ArrayList<DTOUser>();
		try {
			List<Usuario> usuarios = daoUser.loadAll();
			for (Usuario usuario : usuarios) {
				DTOUser dto = new DTOUser();
				dto.setUserId(usuario.getId());
				dto.setUserName(usuario.getLogin());				
				lista.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	*/
		
}