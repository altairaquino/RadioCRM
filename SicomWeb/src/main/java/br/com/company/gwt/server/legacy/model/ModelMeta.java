package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.server.legacy.bean.BeanMeta;
import br.com.company.gwt.server.legacy.util.Utils;

public class ModelMeta {

	public static ModelMeta getInstance(){
		return new ModelMeta();
	}
	
	public ArrayList<BeanMeta> getMetasDoContato(int ctncodg){
		ArrayList<BeanMeta> metas = new ArrayList<BeanMeta>();
		try {
			String sql = " SELECT * FROM VW_META WHERE MTNCGCT = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			metas.addAll(Utils.getObjectsStr(st, BeanMeta.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return metas;
		
	}
	
	public BeanMeta getMeta(int mtncodg){
		BeanMeta meta = null;
		try {
			String sql = "SELECT * FROM VW_META WHERE MTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, mtncodg);
			
			List<BeanMeta> l = Utils.getObjectsStr(st, BeanMeta.class); 
			
			if (!l.isEmpty())
				meta = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return meta;		
	}

	public void inserir(BeanMeta meta) {		
		try {
			String sql = "INSERT INTO META (MTNCGCT, MTNANO) VALUES (?,?)";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(meta.getMtncgct()));
			st.setInt(2, Integer.parseInt(meta.getMtnano()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(BeanMeta meta) {
		try {
			String sql = "UPDATE META SET MTNCGCT = ?, MTNANO = ? WHERE MTNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(meta.getMtncgct()));
			st.setInt(2, Integer.parseInt(meta.getMtnano()));
			st.setInt(3, Integer.parseInt(meta.getMtncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
