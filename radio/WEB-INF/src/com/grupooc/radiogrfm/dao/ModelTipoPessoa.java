package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanTipoPessoa;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelTipoPessoa {

	public static ModelTipoPessoa getInstance(){
		return new ModelTipoPessoa();
	}
	
	public ArrayList<BeanTipoPessoa> getTiposPessoa(){
		ArrayList<BeanTipoPessoa> tipos = new ArrayList<BeanTipoPessoa>();
		try {
			String sql = " SELECT * FROM VW_TIPOPESSOA";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			tipos.addAll(Utils.getObjectsStr(st, BeanTipoPessoa.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipos;
		
	}
	
	public BeanTipoPessoa getTipoPessoa(int tpncodg){
		BeanTipoPessoa tipo = null;
		try {
			String sql = "SELECT * FROM VW_TIPOPESSOA WHERE TPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, tpncodg);
			
			List<BeanTipoPessoa> l = Utils.getObjectsStr(st, BeanTipoPessoa.class); 
			
			if (!l.isEmpty())
				tipo = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return tipo;		
	}
}
