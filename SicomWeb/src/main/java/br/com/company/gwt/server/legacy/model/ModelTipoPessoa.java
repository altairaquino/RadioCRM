package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanTipoPessoa;
import br.com.company.gwt.server.legacy.util.Utils;

@Named
public class ModelTipoPessoa extends ModelAbstract{

	public ArrayList<BeanTipoPessoa> getTiposPessoa(){
		ArrayList<BeanTipoPessoa> tipos = new ArrayList<BeanTipoPessoa>();
		try {
			String sql = " SELECT * FROM VW_TIPOPESSOA";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
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
			PreparedStatement st = getConnection().prepareStatement(sql);
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
