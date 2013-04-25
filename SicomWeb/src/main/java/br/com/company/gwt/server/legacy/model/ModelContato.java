package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.server.legacy.bean.BeanContato;
import br.com.company.gwt.server.legacy.util.Utils;
import br.com.company.gwt.server.legacy.util.ValidaObjeto;

public class ModelContato {

	public static ModelContato getInstance(){
		return new ModelContato();
	}
	
	public ArrayList<BeanContato> getContatos(int epncodg){
		ArrayList<BeanContato> contatos = new ArrayList<BeanContato>();
		try {
			String sql = " SELECT * FROM VW_CONTATO WHERE CTNCGEP = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			contatos.addAll(Utils.getObjectsStr(st, BeanContato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contatos;
		
	}
	
	public BeanContato getContato(int ctncodg){
		BeanContato contato = null;
		try {
			String sql = "SELECT * FROM VW_CONTATO WHERE CTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			List<BeanContato> l = Utils.getObjectsStr(st, BeanContato.class); 
			
			if (!l.isEmpty())
				contato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contato;
		
	}

	public void update(BeanContato contato) {
		try {
			String sql = " UPDATE CONTATO SET CTCNOME = ?,CTCMATR = ?," +
					     " CTCFUNC = ?, CTDNASC = ?,CTCSEXO = ?" +
						 ",CTCFONE = ?,CTCCELL = ?,CTCMAIL = ?" +
						 " WHERE CTNCODG = ?";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contato.getCtcnome());
			st.setString(2, contato.getCtcmatr());
			st.setString(3, contato.getCtcfunc());
			st.setDate(4, Utils.stringToDateSQL(contato.getCtdnasc()));
			st.setString(5, contato.getCtcsexo());
			st.setString(6, ValidaObjeto.removeCharOfInteger(contato.getCtcfone()));
			st.setString(7, ValidaObjeto.removeCharOfInteger(contato.getCtccell()));
			st.setString(8, contato.getCtcmail());
			st.setInt(9, Integer.parseInt(contato.getCtncodg()));
			
			st.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserir(BeanContato contato) {
		try {
			String sql = " INSERT INTO  CONTATO(CTCNOME, CTCMATR, CTCFUNC, CTDNASC,CTCSEXO,CTCFONE,CTCCELL,CTCMAIL, CTNCGEP)"+
						" VALUES(?,?,?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, contato.getCtcnome().toUpperCase());
			st.setString(2, contato.getCtcmatr());
			st.setString(3, contato.getCtcfunc().toUpperCase());
			st.setDate(4, Utils.stringToDateSQL(contato.getCtdnasc()));
			st.setString(5, contato.getCtcsexo());
			st.setString(6, ValidaObjeto.removeCharOfInteger(contato.getCtcfone()));
			st.setString(7, ValidaObjeto.removeCharOfInteger(contato.getCtccell()));
			st.setString(8, contato.getCtcmail());
			st.setInt(9, Integer.parseInt(contato.getCtncgep()));
						
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
