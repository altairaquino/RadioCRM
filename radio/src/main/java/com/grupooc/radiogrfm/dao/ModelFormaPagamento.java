package com.grupooc.radiogrfm.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.grupooc.radiogrfm.struts.bean.BeanFormaPagamento;
import com.grupooc.radiogrfm.utils.Utils;

public class ModelFormaPagamento {

	public static ModelFormaPagamento getInstance(){
		return new ModelFormaPagamento();
	}
	
	public ArrayList<BeanFormaPagamento> getFormasDePagamento(){
		ArrayList<BeanFormaPagamento> dias = new ArrayList<BeanFormaPagamento>();
		try {
			String sql = " SELECT * FROM VW_FORMAPAGAMENTO";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			dias.addAll(Utils.getObjectsStr(st, BeanFormaPagamento.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dias;		
	}
	
	public BeanFormaPagamento getFormaPagamento(int fpncodg){
		BeanFormaPagamento dia = null;
		try {
			String sql = "SELECT * FROM VW_FORMAPAGAMENTO WHERE FPNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, fpncodg);
			
			List<BeanFormaPagamento> l = Utils.getObjectsStr(st, BeanFormaPagamento.class); 
			
			if (!l.isEmpty())
				dia = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dia;		
	}
}
