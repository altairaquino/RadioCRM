package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanDiaSemana;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelDiaSemana")
public class ModelDiaSemana extends ModelAbstract{

	public ArrayList<BeanDiaSemana> getDiasDaSemana(){
		ArrayList<BeanDiaSemana> dias = new ArrayList<BeanDiaSemana>();
		try {
			String sql = " SELECT DSNCODG, DSCDESC FROM DIASEMANA ORDER BY DSNCODG";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
			dias.addAll(Utils.getObjectsStr(st, BeanDiaSemana.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dias;
		
	}
	
	public BeanDiaSemana getDiaSemana(int dsncodg){
		BeanDiaSemana dia = null;
		try {
			String sql = "SELECT DSNCODG, DSCDESC FROM DIASEMANA WHERE DSNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, dsncodg);
			
			List<BeanDiaSemana> l = Utils.getObjectsStr(st, BeanDiaSemana.class); 
			
			if (!l.isEmpty())
				dia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dia;		
	}
}