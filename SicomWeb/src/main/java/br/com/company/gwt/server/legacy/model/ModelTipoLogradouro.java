package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanTipoLogradouro;
import br.com.company.gwt.server.legacy.util.Utils;

@Named
public class ModelTipoLogradouro extends ModelAbstract{

	public ArrayList<BeanTipoLogradouro> getTiposLogradouro(){
		ArrayList<BeanTipoLogradouro> logradouros = new ArrayList<BeanTipoLogradouro>();
		try {
			String sql = " SELECT TLNCODG, TLCDESC FROM VW_TIPOLOGRADOURO";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
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
			PreparedStatement st = getConnection().prepareStatement(sql);
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
