package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanMidia;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelMidia")
public class ModelMidia extends ModelAbstract{

	public static ModelMidia getInstance(){
		return new ModelMidia();
	}
	
	public ArrayList<BeanMidia> getMidias(int epncodg){
		ArrayList<BeanMidia> dias = new ArrayList<BeanMidia>();
		try {
			String sql = " SELECT * FROM VW_MIDIA WHERE MDNCGEP = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			dias.addAll(Utils.getObjectsStr(st, BeanMidia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dias;		
	}
	
	public BeanMidia getMidia(int mdncodg){
		BeanMidia dia = null;
		try {
			String sql = "SELECT * FROM VW_MIDIA WHERE MDNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, mdncodg);
			
			List<BeanMidia> l = Utils.getObjectsStr(st, BeanMidia.class); 
			
			if (!l.isEmpty())
				dia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dia;		
	}

	public void inserir(BeanMidia midia) {
		try {
			String sql = "INSERT INTO MIDIA (MDCDESC) VALUES (?)";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, midia.getMdcdesc().toUpperCase());
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
