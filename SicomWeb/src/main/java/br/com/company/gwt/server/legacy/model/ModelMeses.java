package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanMeses;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelMeses")
public class ModelMeses extends ModelAbstract{

	public ArrayList<BeanMeses> getMeses(){
		ArrayList<BeanMeses> dias = new ArrayList<BeanMeses>();
		try {
			String sql = " SELECT MSNCODG, MSCDESC FROM MESES ORDER BY MSNCODG";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
			dias.addAll(Utils.getObjectsStr(st, BeanMeses.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dias;
		
	}
	
	public BeanMeses getMes(int msncodg){
		BeanMeses dia = null;
		try {
			String sql = "SELECT MSNCODG, MSCDESC FROM MESES WHERE MSNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, msncodg);
			
			List<BeanMeses> l = Utils.getObjectsStr(st, BeanMeses.class); 
			
			if (!l.isEmpty())
				dia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dia;		
	}
}
