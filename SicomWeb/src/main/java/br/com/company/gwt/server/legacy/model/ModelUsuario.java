package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanUsuario;
import br.com.company.gwt.server.legacy.util.Utils;

@Named
public class ModelUsuario extends ModelAbstract{
	
	public BeanUsuario getBeanUsuario(int usncodg){
		BeanUsuario usuario = null;
		try {
			String sql = " SELECT USNCODG, USCLOGN, USCSENH, USCNOME, USLATIV FROM VW_USUARIO WHERE USNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, usncodg);
			List<BeanUsuario> l = Utils.getObjectsStr(st, BeanUsuario.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public List<BeanUsuario> getUsuarios(){
		List<BeanUsuario> usuarios = new ArrayList<BeanUsuario>();
		try {
			String sql = " SELECT USNCODG, USCLOGN, USCSENH, USCNOME, USLATIV FROM VW_USUARIO";
			PreparedStatement st = getConnection().prepareStatement(sql);
			usuarios.addAll(Utils.getObjectsStr(st, BeanUsuario.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void alteraSenha(int usncodg, String novaSenha){
		try {
			String sql = " UPDATE USUARIO SET USCSENH = ? WHERE USNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, novaSenha);
			st.setInt(2, usncodg);
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BeanUsuario getBeanUsuarioPorLogin(String usclogn){
		BeanUsuario usuario = null;
		try {
			String sql = "SELECT * FROM VW_USUARIO WHERE USCLOGN = UPPER(?)";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, usclogn);
			List<BeanUsuario> l = Utils.getObjectsStr(st, BeanUsuario.class);
			if (!l.isEmpty()){
				usuario = l.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public BeanUsuario autenticaUsuario(String usclogn, String uscpswd){
		BeanUsuario usuario = null;
		try {
			String sql = "SELECT * FROM VW_USUARIO WHERE USCLOGN = UPPER(?) AND USCSENH = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, usclogn);
			st.setString(2, uscpswd);
			
			List<BeanUsuario> usuarios = Utils.getObjectsStr(st, BeanUsuario.class);
			
			if (!usuarios.isEmpty()){
				usuario = usuarios.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	

}
