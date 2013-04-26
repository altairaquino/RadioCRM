package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanMetaMes;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelMetaMes")
public class ModelMetaMes extends ModelAbstract{

	public ArrayList<BeanMetaMes> getMetasMensaisDaMeta(int mtncodg){
		ArrayList<BeanMetaMes> metas = new ArrayList<BeanMetaMes>();
		try {
			String sql = " SELECT * FROM VW_METAMES WHERE MMNCGMT = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, mtncodg);
			
			metas.addAll(Utils.getObjectsStr(st, BeanMetaMes.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return metas;		
	}
	
	public BeanMetaMes getMetaMes(int mmncodg){
		BeanMetaMes meta = null;
		try {
			String sql = "SELECT * FROM VW_METAMES WHERE MMNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, mmncodg);
			
			List<BeanMetaMes> l = Utils.getObjectsStr(st, BeanMetaMes.class); 
			
			if (!l.isEmpty())
				meta = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return meta;		
	}

	public void inserir(BeanMetaMes meta) {
		try {
			String sql = "INSERT INTO METAMES (MMNCGMT, MMNCGMS, MMNVALR) VALUES (?,?,?)";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(meta.getMmncgmt()));
			st.setInt(2, Integer.parseInt(meta.getMmncgms()));
			st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(meta.getMmnvalr())));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(BeanMetaMes meta) {
		try {
			String sql = "UPDATE METAMES SET MMNCGMT = ?, MMNCGMS = ?, MMNVALR = ? WHERE MMNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(meta.getMmncgmt()));
			st.setInt(2, Integer.parseInt(meta.getMmncgms()));
			st.setFloat(3, Float.parseFloat(Utils.converteFloatBR(meta.getMmnvalr())));
			st.setInt(4, Integer.parseInt(meta.getMmncodg()));
			
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
