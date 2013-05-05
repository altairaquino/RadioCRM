package br.com.company.gwt.server.dao;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Usuario;

@Named(value="daoUsuario")
public class DaoUsuario extends DaoAbstract<Usuario, Integer> {
	
	@Override
	protected Integer getId(Usuario usuario) {
		return usuario.getId();
	}
	
	public Usuario login(String login, String senha){
		try {
			String hql = " from Usuario u where u.login = :login and u.senha = :senha";
			Query query = createQuery(hql);
			query.setString("login", login);
			query.setString("senha", senha);
			query.setMaxResults(1);
			
			return (Usuario) query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}