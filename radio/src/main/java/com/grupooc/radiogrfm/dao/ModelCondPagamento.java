package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanCondPagamento;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelCondPagamento {

	public static ModelCondPagamento getInstance(){
		return new ModelCondPagamento();
	}
	
	public ArrayList<BeanCondPagamento> getCondicoesDePagamento(){
		ArrayList<BeanCondPagamento> dias = new ArrayList<BeanCondPagamento>();
		try {
			String sql = " SELECT * FROM VW_CONDPAGAMENTO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			dias.addAll(Utils.getObjectsStr(st, BeanCondPagamento.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dias;		
	}
	
	public BeanCondPagamento getCondPagamento(int fpncodg){
		BeanCondPagamento dia = null;
		try {
			String sql = "SELECT * FROM VW_CONDPAGAMENTO WHERE FPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, fpncodg);
			
			List<BeanCondPagamento> l = Utils.getObjectsStr(st, BeanCondPagamento.class); 
			
			if (!l.isEmpty())
				dia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dia;		
	}
}
