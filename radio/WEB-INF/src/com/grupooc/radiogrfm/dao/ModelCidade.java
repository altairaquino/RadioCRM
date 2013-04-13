package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanCidade;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelCidade {

	public static ModelCidade getInstance(){
		return new ModelCidade();
	}
	
	public ArrayList<BeanCidade> getCidadesDoEstado(String uf){
		ArrayList<BeanCidade> cidades = new ArrayList<BeanCidade>();
		try {
			String sql = "SELECT CDNCODG, CDCUF, CDCDESC FROM VW_CIDADE WHERE CDCUF = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setString(1, uf);
			
			cidades.addAll(Utils.getObjectsStr(st, BeanCidade.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cidades;
		
	}
	
	public ArrayList<BeanCidade> getCidades(){
		ArrayList<BeanCidade> cidades = new ArrayList<BeanCidade>();
		try {
			String sql = "SELECT CDNCODG, CDCUF, CDCDESC FROM VW_CIDADE";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			cidades.addAll(Utils.getObjectsStr(st, BeanCidade.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cidades;
		
	}
	
	public ArrayList<BeanCidade> getEstados(){
		ArrayList<BeanCidade> cidades = new ArrayList<BeanCidade>();
		try {
			
			String sql = " SELECT DISTINCT CDCUF FROM VW_CIDADE ";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
						
			cidades.addAll(Utils.getObjectsStr(st, BeanCidade.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cidades;
		
	}
	
	public BeanCidade getCidade(int cdncodg){
		BeanCidade cidade = null;
		try {
			String sql = "  SELECT CDNCODG, CDCUF, CDCDESC FROM VW_CIDADE WHERE CDNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, cdncodg);
			
			List<BeanCidade> l = Utils.getObjectsStr(st, BeanCidade.class); 
			
			if (!l.isEmpty())
				cidade = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cidade;
		
	}
}
