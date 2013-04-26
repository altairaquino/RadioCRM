package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanPrograma;
import br.com.company.gwt.server.legacy.util.Utils;

@Named
public class ModelPrograma extends ModelAbstract{

	public ArrayList<BeanPrograma> getProgramas(int epncodg){
		ArrayList<BeanPrograma> programas = new ArrayList<BeanPrograma>();
		try {
			String sql = " SELECT * FROM VW_PROGRAMA WHERE PGNCGEP = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			programas.addAll(Utils.getObjectsStr(st, BeanPrograma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programas;
		
	}
	
	public ArrayList<BeanPrograma> getProgramasComValor(){
		ArrayList<BeanPrograma> programas = new ArrayList<BeanPrograma>();
		try {
			String sql = " SELECT PGNCODG, PGCDESC||' - (R$ '|| CAST(PGYVALR AS DECIMAL(10,2))||' - '||PGNDURC||' seg)' PGCDESC,  PGYVALR,   PGNDURC" +
					     " FROM VW_PROGRAMA";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
			programas.addAll(Utils.getObjectsStr(st, BeanPrograma.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programas;
		
	}
	
	public BeanPrograma getPrograma(int pgncodg){
		BeanPrograma programa = null;
		try {
			String sql = "SELECT * FROM VW_PROGRAMA WHERE PGNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, pgncodg);
			
			List<BeanPrograma> l = Utils.getObjectsStr(st, BeanPrograma.class); 
			
			if (!l.isEmpty())
				programa = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programa;
		
	}

	public void update(BeanPrograma programa) {
		try {
			String sql = " UPDATE PROGRAMA SET" +
					     " PGCDESC = ?," +
					     " PGNDURC = ?," +
					     " PGYVALR = ?"+
					     " WHERE PGNCODG = ?";
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, programa.getPgcdesc().toUpperCase());
			st.setInt(2, Integer.parseInt(programa.getPgndurc()));
			st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(programa.getPgyvalr())));
			st.setInt(4, Integer.parseInt(programa.getPgncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void inserir(BeanPrograma programa) {
		try {
			String sql = " INSERT INTO PROGRAMA (PGCDESC, PGNDURC, PGYVALR, PGNCGEP) VALUES (?,?,?, ?)";					     
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, programa.getPgcdesc().toUpperCase());
			st.setInt(2, Integer.parseInt(programa.getPgndurc()));
			st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(programa.getPgyvalr())));
			st.setInt(4, Integer.parseInt(programa.getPgncgep()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
