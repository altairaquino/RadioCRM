package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanInsercao;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelInsercao {

	public static ModelInsercao getInstance(){
		return new ModelInsercao();
	}
	
	public ArrayList<BeanInsercao> getInsercoesDoContrato(int crncodg){
		ArrayList<BeanInsercao> horarios = new ArrayList<BeanInsercao>();
		try {
			String sql = "SELECT * FROM VW_INSERCAO WHERE INNCGCR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, crncodg);
			
			horarios.addAll(Utils.getObjectsStr(st, BeanInsercao.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return horarios;
		
	}
	
	public String valorInsercoesDoContrato(int crncodg){
		String ret = null;
		try {
			String sql = "SELECT SUM(INNSOMA)INNSOMA FROM VW_INSERCAO WHERE INNCGCR = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, crncodg);
			
			ret = Utils.getObjectsStr(st, BeanInsercao.class).get(0).getInnsoma();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ret;
		
	}
	
	public BeanInsercao getInsercao(int inncodg){
		BeanInsercao programa = null;
		try {
			String sql = "SELECT * FROM VW_INSERCAO WHERE INNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, inncodg);
			
			List<BeanInsercao> l = Utils.getObjectsStr(st, BeanInsercao.class); 
			
			if (!l.isEmpty())
				programa = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programa;
		
	}

	public void inserir(BeanInsercao insercao) {
		try {
			String sql = " INSERT INTO INSERCAO(INNCGCR,INNCGPG,INDDATA,INNDESC,INNQTD)" +
					     " VALUES(?,?,?,?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(insercao.getInncgcr()));
			st.setInt(2, Integer.parseInt(insercao.getInncgpg()));
			st.setDate(3, Utils.stringToDateSQL(insercao.getInddata()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(insercao.getInndesc())));
			st.setInt(5, Integer.parseInt(insercao.getInnqtd()));
			
			st.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void update(BeanInsercao insercao) {
		try {
			String sql = " UPDATE INSERCAO SET INNCGCR = ?,INNCGPG = ?,INDDATA = ?,INNDESC = ?,INNQTD = ? " +
		     			 " WHERE INNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(insercao.getInncgcr()));
			st.setInt(2, Integer.parseInt(insercao.getInncgpg()));
			st.setDate(3, Utils.stringToDateSQL(insercao.getInddata()));
			st.setFloat(4, Float.parseFloat(Utils.converteFloatBR(insercao.getInndesc())));
			st.setInt(5, Integer.parseInt(insercao.getInnqtd()));
			st.setInt(6, Integer.parseInt(insercao.getInncodg()));
			
			st.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void desativar(int inncodg) {
		try {
			String sql = " UPDATE INSERCAO SET INLATIV = 'F'" +
			 			 " WHERE INNCODG = ? AND INLATIV = 'T'";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, inncodg);
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
