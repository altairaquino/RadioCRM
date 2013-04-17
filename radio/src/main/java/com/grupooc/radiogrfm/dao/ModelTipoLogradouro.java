package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanTipoLogradouro;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelTipoLogradouro {

	public static ModelTipoLogradouro getInstance(){
		return new ModelTipoLogradouro();
	}
	
	public ArrayList<BeanTipoLogradouro> getTiposLogradouro(){
		ArrayList<BeanTipoLogradouro> logradouros = new ArrayList<BeanTipoLogradouro>();
		try {
			String sql = " SELECT TLNCODG, TLCDESC FROM VW_TIPOLOGRADOURO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			logradouros.addAll(Utils.getObjectsStr(st, BeanTipoLogradouro.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return logradouros;
		
	}
	
	public BeanTipoLogradouro getTipoLogradouro(int tlncodg){
		BeanTipoLogradouro logradouro = null;
		try {
			String sql = "SELECT TLNCODG, TLCDESC FROM VW_TIPOLOGRADOURO WHERE TLNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tlncodg);
			
			List<BeanTipoLogradouro> l = Utils.getObjectsStr(st, BeanTipoLogradouro.class); 
			
			if (!l.isEmpty())
				logradouro = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return logradouro;
		
	}
}
