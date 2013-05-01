package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanCliente;
import br.com.company.gwt.server.legacy.util.Utils;
import br.com.company.gwt.server.legacy.util.ValidaObjeto;

@Named("modelCliente")
public class ModelCliente extends ModelAbstract{

	public ArrayList<BeanCliente> getClientes(int epncodg){
		ArrayList<BeanCliente> programas = new ArrayList<BeanCliente>();
		try {
			String sql = " SELECT FIRST 50 * FROM VW_CLIENTE WHERE CLNCGEP = ? ORDER BY CLCNOME";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			programas.addAll(Utils.getObjectsStr(st, BeanCliente.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programas;		
	}
	
	public ArrayList<BeanCliente> getClientesDoContato(int ctncodg, int epncodg){
		ArrayList<BeanCliente> programas = new ArrayList<BeanCliente>();
		try {
			String sql = " SELECT * FROM VW_CLIENTE WHERE CLNCGCT = ? ORDER BY CLCNOME";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, ctncodg);
			
			programas.addAll(Utils.getObjectsStr(st, BeanCliente.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programas;		
	}
	
	public BeanCliente getCliente(int clncodg){
		BeanCliente programa = null;
		try {
			String sql = "SELECT * FROM VW_CLIENTE WHERE CLNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, clncodg);
			
			List<BeanCliente> l = Utils.getObjectsStr(st, BeanCliente.class); 
			
			if (!l.isEmpty())
				programa = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programa;
		
	}
	
	public List<BeanCliente> getClientesPorNomeDoc(String clcnome, int epncodg){
		List<BeanCliente> alunos = new ArrayList<BeanCliente>();
		try {
			String sql = "SELECT * FROM VW_CLIENTE WHERE (UPPER(CLCNOME) LIKE upper('%"+clcnome+"%') OR CLCDOCM = '"+clcnome+"') AND CLNCGEP = ? ORDER BY CLCNOME";
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
						
			alunos = Utils.getObjectsStr(st, BeanCliente.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return alunos;
	}
	
	public void update(BeanCliente cliente) {
		try {
			String sql = " UPDATE CLIENTE SET CLNCGTP = ?, CLCNOME = ?, CLCDOCM = ?, CLCFONE = ?, CLCMAIL = ?, CLNCGTL = ?, CLLENDR = ?, CLCNUMR = ?," +
					     " CLCCOMP = ?, CLNCGCD = ?,CLCCEP = ?, CLCBAIR = ?, CLMOBS = ?, CLNCGCT = ?, CLCCONT = ?, CLDDNCT = ?, CLCRAMO = ?," +
					     " CLCRG = ?, CLCOERG = ?, CLCUFRG = ?, CLCINES = ?, CLCINMU = ?"+
					     " WHERE CLNCODG = ?";  
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(cliente.getClncgtp()));
			st.setString(2, cliente.getClcnome().toUpperCase());
			st.setString(3, ValidaObjeto.removeCharOfInteger(cliente.getClcdocm()));
			st.setString(4, ValidaObjeto.removeCharOfInteger(cliente.getClcfone()));
			st.setString(5, cliente.getClcmail());
			st.setInt(6, Integer.parseInt(cliente.getClncgtl()));
			st.setString(7, cliente.getCllendr().toUpperCase());
			st.setString(8, cliente.getClcnumr());
			st.setString(9, cliente.getClccomp().toUpperCase());
			st.setInt(10, Integer.parseInt(cliente.getClncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(cliente.getClccep()));
			st.setString(12, cliente.getClcbair().toUpperCase());
			st.setString(13, cliente.getClmobs());
			st.setInt(14, Integer.parseInt(cliente.getClncgct()));
			st.setString(15, cliente.getClccont());
			if (ValidaObjeto.validaData(cliente.getClddnct()))
				st.setDate(16, Utils.stringToDateSQL(cliente.getClddnct()));
			else
				st.setNull(16, Types.DATE);
			st.setString(17, cliente.getClcramo());
			st.setString(18, cliente.getClcrg());
			st.setString(19, cliente.getClcoerg());
			st.setString(20, cliente.getClcufrg());
			st.setString(21, cliente.getClcines());
			st.setString(22, cliente.getClcinmu());
			st.setInt(23, Integer.parseInt(cliente.getClncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserir(BeanCliente cliente) {
		
		try {
			String sql = " INSERT INTO CLIENTE(CLNCGTP, CLCNOME, CLCDOCM, CLCFONE, CLCMAIL, " +
					                          "CLNCGTL, CLLENDR, CLCNUMR, CLCCOMP, CLNCGCD," +
					                          "CLCCEP, CLCBAIR, CLMOBS, Clccont, Clddnct, " +
					                          "CLNCGCT, CLCRAMO, CLCRG, CLCOERG, CLCUFRG," +
					                          "CLCINES, CLCINMU, CLNCGEP)" +
					     " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					             " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					             " ?, ?, ?)";  
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(cliente.getClncgtp()));
			st.setString(2, cliente.getClcnome().toUpperCase());
			st.setString(3, ValidaObjeto.removeCharOfInteger(cliente.getClcdocm()));
			st.setString(4, ValidaObjeto.removeCharOfInteger(cliente.getClcfone()));
			st.setString(5, cliente.getClcmail());
			st.setInt(6, Integer.parseInt(cliente.getClncgtl()));
			st.setString(7, cliente.getCllendr().toUpperCase());
			st.setString(8, cliente.getClcnumr());
			st.setString(9, cliente.getClccomp().toUpperCase());
			st.setInt(10, Integer.parseInt(cliente.getClncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(cliente.getClccep()));
			st.setString(12, cliente.getClcbair().toUpperCase());
			st.setString(13, cliente.getClmobs());
			st.setString(14, cliente.getClccont());
			
			if (ValidaObjeto.validaData(cliente.getClddnct()))
				st.setDate(15, Utils.stringToDateSQL(cliente.getClddnct()));
			else
				st.setNull(15, Types.DATE);
			
			st.setInt(16, Integer.parseInt(cliente.getClncgct()));
			st.setString(17, cliente.getClcramo());
			st.setString(18, cliente.getClcrg());
			st.setString(19, cliente.getClcoerg());
			st.setString(20, cliente.getClcufrg());
			st.setString(21, cliente.getClcines());
			st.setString(22, cliente.getClcinmu());
			st.setInt(23, Integer.parseInt(cliente.getClncgep()));
						
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
