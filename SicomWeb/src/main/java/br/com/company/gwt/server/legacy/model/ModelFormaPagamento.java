package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanFormaPagamento;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelFormaPagamento")
public class ModelFormaPagamento extends ModelAbstract{

	public ArrayList<BeanFormaPagamento> getFormasDePagamento(){
		ArrayList<BeanFormaPagamento> dias = new ArrayList<BeanFormaPagamento>();
		try {
			String sql = " SELECT * FROM VW_FORMAPAGAMENTO";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
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
			PreparedStatement st = getConnection().prepareStatement(sql);
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
