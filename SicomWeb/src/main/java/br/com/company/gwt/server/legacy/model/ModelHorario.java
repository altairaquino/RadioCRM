package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanHorario;
import br.com.company.gwt.server.legacy.util.Utils;

@Named("modelHorario")
public class ModelHorario extends ModelAbstract{

	public ArrayList<BeanHorario> getHorariosDoPrograma(int pgncodg){
		ArrayList<BeanHorario> horarios = new ArrayList<BeanHorario>();
		try {
			String sql = "SELECT * FROM VW_HORARIO WHERE HRNCGPG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, pgncodg);
			
			horarios.addAll(Utils.getObjectsStr(st, BeanHorario.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return horarios;
		
	}
	
	public BeanHorario getHorario(int hrncodg){
		BeanHorario programa = null;
		try {
			String sql = "SELECT * FROM VW_HORARIO WHERE HRNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, hrncodg);
			
			List<BeanHorario> l = Utils.getObjectsStr(st, BeanHorario.class); 
			
			if (!l.isEmpty())
				programa = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programa;
		
	}

	public void inserir(BeanHorario horario) {
		try {
			String sql = "INSERT INTO HORARIO (HRNCGPG, HRNCGDS, HRHINIC, HRHTERM) VALUES (?,?,?,?)";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(horario.getHrncgpg()));
			st.setInt(2, Integer.parseInt(horario.getHrncgds()));
			st.setTime(3, Time.valueOf(horario.getHrhinic()));
			st.setTime(4, Time.valueOf(horario.getHrhterm()));
			
			st.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void update(BeanHorario horario) {
		try {
			String sql = " UPDATE HORARIO SET " +
					     " HRNCGPG = ?," +
					     " HRNCGDS = ?," +
					     " HRHINIC = ?," +
					     " HRHTERM = ?" +
					     " WHERE HRNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(horario.getHrncgpg()));
			st.setInt(2, Integer.parseInt(horario.getHrncgds()));
			st.setTime(3, Time.valueOf(horario.getHrhinic()));
			st.setTime(4, Time.valueOf(horario.getHrhterm()));
			st.setInt(5, Integer.parseInt(horario.getHrncodg()));
			
			st.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
