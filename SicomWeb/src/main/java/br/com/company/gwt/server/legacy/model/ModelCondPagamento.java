package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanCondPagamento;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelCondPagamento")
public class ModelCondPagamento extends ModelAbstract{

	public ArrayList<BeanCondPagamento> getCondicoesDePagamento(){
		ArrayList<BeanCondPagamento> dias = new ArrayList<BeanCondPagamento>();
		try {
			String sql = " SELECT * FROM VW_CONDPAGAMENTO";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
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
			PreparedStatement st = getConnection().prepareStatement(sql);
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
