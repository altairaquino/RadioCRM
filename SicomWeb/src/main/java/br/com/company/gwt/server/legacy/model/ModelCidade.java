package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanCidade;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelCidade")
public class ModelCidade extends ModelAbstract{

	public ArrayList<BeanCidade> getCidadesDoEstado(String uf){
		ArrayList<BeanCidade> cidades = new ArrayList<BeanCidade>();
		try {
			String sql = "SELECT CDNCODG, CDCUF, CDCDESC FROM VW_CIDADE WHERE CDCUF = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
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
			PreparedStatement st = getConnection().prepareStatement(sql);
						
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
			PreparedStatement st = getConnection().prepareStatement(sql);
						
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
			PreparedStatement st = getConnection().prepareStatement(sql);
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
