package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.com.company.gwt.server.legacy.bean.BeanAgencia;
import br.com.company.gwt.server.legacy.bean.BeanCliente;
import br.com.company.gwt.server.legacy.util.Utils;
import br.com.company.gwt.server.legacy.util.ValidaObjeto;

@Named("modelAgencia")
public class ModelAgencia extends ModelAbstract{

	public ArrayList<BeanAgencia> getAgencias(int epncodg){
		ArrayList<BeanAgencia> programas = new ArrayList<BeanAgencia>();
		try {
			String sql = " SELECT * FROM VW_AGENCIA WHERE AGNCGEP = ? ORDER BY AGCNOME";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			programas.addAll(Utils.getObjectsStr(st, BeanAgencia.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programas;		
	}
	
	public BeanAgencia getAgencia(int agncodg){
		BeanAgencia programa = null;
		try {
			String sql = "SELECT * FROM VW_AGENCIA WHERE AGNCODG = ?";
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, agncodg);
			
			List<BeanAgencia> l = Utils.getObjectsStr(st, BeanAgencia.class); 
			
			if (!l.isEmpty())
				programa = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return programa;
		
	}
	
	public void update(BeanAgencia agencia) {
		try {
			String sql = " UPDATE AGENCIA SET AGCNOME = ?,AGCCNPJ = ?,AGCRZSC = ?,AGCFONE = ?,AGCMAIL = ?, AGNCGTL = ?,AGLENDR = ?" +
					     ",AGCNUMR = ?,AGCCOMP = ?,AGNCGCD = ?,AGCCEP = ?,AGCBAIR = ?, AGMOBS = ?, AGNCGTP = ?, AGNCOMS = ? " +
					     " WHERE AGNCODG = ?";		     
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, agencia.getAgcnome().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(agencia.getAgccnpj()));
			st.setString(3, agencia.getAgcrzsc().toUpperCase());
			st.setString(4, ValidaObjeto.removeCharOfInteger(agencia.getAgcfone()));
			st.setString(5, agencia.getAgcmail());
			st.setInt(6, Integer.parseInt(agencia.getAgncgtl()));
			st.setString(7, agencia.getAglendr().toUpperCase());
			st.setString(8, agencia.getAgcnumr());
			st.setString(9, agencia.getAgccomp().toUpperCase());
			st.setInt(10, Integer.parseInt(agencia.getAgncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(agencia.getAgccep()));
			st.setString(12, agencia.getAgcbair().toUpperCase());
			st.setString(13, agencia.getAgmobs());
			st.setInt(14, Integer.parseInt(agencia.getAgncgtp()));
			st.setFloat(15, Float.parseFloat(Utils.converteFloatBR(agencia.getAgncoms())));
			st.setInt(16, Integer.parseInt(agencia.getAgncodg()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inserir(BeanAgencia agencia) {
		
		try {
			String sql = " INSERT INTO AGENCIA(AGCNOME,AGCCNPJ,AGCRZSC,AGCFONE,AGCMAIL, AGNCGTL,AGLENDR" +
					     ",AGCNUMR,AGCCOMP,AGNCGCD,AGCCEP,AGCBAIR, AGMOBS, AGNCGTP, AGNCOMS, AGNCGEP) " +
					     "VALUES( ?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?, ?)";
			
			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setString(1, agencia.getAgcnome().toUpperCase());
			st.setString(2, ValidaObjeto.removeCharOfInteger(agencia.getAgccnpj()));
			st.setString(3, agencia.getAgcrzsc().toUpperCase());
			st.setString(4, ValidaObjeto.removeCharOfInteger(agencia.getAgcfone()));
			st.setString(5, agencia.getAgcmail());
			st.setInt(6, Integer.parseInt(agencia.getAgncgtl()));
			st.setString(7, agencia.getAglendr().toUpperCase());
			st.setString(8, agencia.getAgcnumr());
			st.setString(9, agencia.getAgccomp().toUpperCase());
			st.setInt(10, Integer.parseInt(agencia.getAgncgcd()));
			st.setString(11, ValidaObjeto.removeCharOfInteger(agencia.getAgccep()));
			st.setString(12, agencia.getAgcbair().toUpperCase());
			st.setString(13, agencia.getAgmobs());
			st.setInt(14, Integer.parseInt(agencia.getAgncgtp()));
			st.setFloat(15, Float.parseFloat(Utils.converteFloatBR(agencia.getAgncoms())));
			st.setInt(16, Integer.parseInt(agencia.getAgncgep()));
			
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<BeanCliente> getClienteDaAgencia(Integer agncodg) {
		List<BeanCliente> programas = new ArrayList<BeanCliente>();
		try {
			String sql = " SELECT DISTINCT * FROM VW_CLIENTE" +
					" WHERE EXISTS(SELECT * FROM CONTRATO WHERE CRNCGCL = VW_CLIENTE.CLNCODG" +
					" AND CRNCGAG = ?) ";

			PreparedStatement st = getConnection().prepareStatement(sql);
			st.setInt(1, agncodg);
			
			programas.addAll(Utils.getObjectsStr(st, BeanCliente.class));
			
		} catch (Exception e) {
			
		}
		return programas;
	}

		
}
