package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelEmpresa {

	public static ModelEmpresa getInstance(){
		return new ModelEmpresa();
	}
	
	public ArrayList<BeanEmpresa> getEmpresas(){
		ArrayList<BeanEmpresa> cidades = new ArrayList<BeanEmpresa>();
		try {
			String sql = "SELECT * FROM VW_EMPRESA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			cidades.addAll(Utils.getObjectsStr(st, BeanEmpresa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return cidades;
		
	}
	
	public BeanEmpresa getEmpresa(int epncodg){
		BeanEmpresa empresa = null;
		try {
			String sql = " SELECT * FROM VW_EMPRESA WHERE EPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			List<BeanEmpresa> l = Utils.getObjectsStr(st, BeanEmpresa.class); 
			
			if (!l.isEmpty())
				empresa = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return empresa;
		
	}
}
