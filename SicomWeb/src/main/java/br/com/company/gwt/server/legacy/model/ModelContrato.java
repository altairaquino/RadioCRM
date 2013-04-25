package br.com.company.gwt.server.legacy.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.company.gwt.server.legacy.bean.BeanContrato;
import br.com.company.gwt.server.legacy.util.Utils;

public class ModelContrato {

	public static ModelContrato getInstance(){
		return new ModelContrato();
	}
	
	public ArrayList<BeanContrato> getContratosAberto(int epncodg){
		ArrayList<BeanContrato> contatos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT * FROM VW_CONTRATO WHERE 'NOW' <= CRDTERM AND CRNCGEP = ? ORDER BY CRDTERM DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, epncodg);
			
			contatos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contatos;
		
	}
	
	public BeanContrato getContrato(int crncodg){
		BeanContrato contato = null;
		try {
			String sql = "SELECT * FROM VW_CONTRATO WHERE CRNCODG = ?";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, crncodg);
			
			List<BeanContrato> l = Utils.getObjectsStr(st, BeanContrato.class); 
			
			if (!l.isEmpty())
				contato = l.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return contato;
		
	}
	
	public boolean temCredito(int crncodg, float valor){
		boolean ret = false;
		try {
			String sql = "  SELECT" +
					     "   CRNVALR," +
					     "   COALESCE(SUM(INNSOMA),0) INNSOMA" +
					     " FROM" +
					     "   CONTRATO LEFT JOIN VW_INSERCAO ON" +
					     "      INNCGCR = CRNCODG" +
					     " WHERE CRNCODG = ?" +
					     " GROUP BY CRNVALR";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, crncodg);
			
			ResultSet rs = st.executeQuery();
			
			float valorContrato = 0;
			float valorContratado = 0;
			
			if (rs.next()){
				valorContrato = rs.getInt("CRNVALR");
				valorContratado = rs.getFloat("INNSOMA");
			}
			
			ret = ((valorContrato - valorContratado) >= valor);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ret;		
	}
	
	public boolean estaPeriodoVigencia(int crncodg, Date data){
		boolean ret = false;
		try {
			String sql = " SELECT * FROM VW_CONTRATO" +
					     " WHERE CRNCODG = ? AND ? BETWEEN CRDINIC AND CRDTERM";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, crncodg);
			st.setDate(2, new java.sql.Date(data.getTime()));
			
			ret = !Utils.getObjectsStr(st, BeanContrato.class).isEmpty();								
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ret;		
	}

	public void update(BeanContrato contrato) {
		try {
			String sql = " UPDATE CONTRATO SET CRNCGCL = ?, CRNCGAG = ?, CRCPGTO = ?, CRMTEXT = ?," +
					     " CRDINIC = ?,CRDTERM = ?, CRNVALR = ?, CRNCGFP = ?, CRNCGCP = ?, CRLLIQU = ?," +
					     " CRNPERM = ?, CRNCGMD = ?, CRDCADT = ?, CRCNOTA = ?" +
		     			 " WHERE CRNCODG =  ?";

			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(contrato.getCrncgcl()));
			st.setInt(2, Integer.parseInt(contrato.getCrncgag()));
			st.setString(3, contrato.getCrcpgto().toUpperCase());
			st.setString(4, contrato.getCrmtext().toUpperCase());
			st.setDate(5, Utils.stringToDateSQL(contrato.getCrdinic()));
			st.setDate(6, Utils.stringToDateSQL(contrato.getCrdterm()));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(contrato.getCrnvalr())));
			st.setInt(8, Integer.parseInt(contrato.getCrncgfp()));
			st.setInt(9, Integer.parseInt(contrato.getCrncgcp()));
			st.setString(10, contrato.getCrlliqu());
			st.setFloat(11, Float.parseFloat(Utils.converteFloatBR(contrato.getCrnperm())));
			st.setInt(12, Integer.parseInt(contrato.getCrncgmd()));
			st.setDate(13, Utils.stringToDateSQL(contrato.getCrdcadt()));
			st.setString(14, contrato.getCrcnota());
			st.setInt(15, Integer.parseInt(contrato.getCrncodg()));
			
			st.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inserir(BeanContrato contrato) {
		try {
			String sql = " INSERT INTO CONTRATO( CRNCGCL, CRNCGAG, CRCPGTO, CRMTEXT, CRDINIC," +
					     " CRDTERM, CRNVALR, CRNCGFP, CRNCGCP, CRLLIQU, CRNPERM, CRNCGMD, CRDCADT," +
					     " CRCNOTA, CRNCGEP)" +
					     " VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, Integer.parseInt(contrato.getCrncgcl()));
			st.setInt(2, Integer.parseInt(contrato.getCrncgag()));
			st.setString(3, contrato.getCrcpgto().toUpperCase());
			st.setString(4, contrato.getCrmtext().toUpperCase());
			st.setDate(5, Utils.stringToDateSQL(contrato.getCrdinic()));
			st.setDate(6, Utils.stringToDateSQL(contrato.getCrdterm()));
			st.setFloat(7, Float.parseFloat(Utils.converteFloatBR(contrato.getCrnvalr())));
			st.setInt(8, Integer.parseInt(contrato.getCrncgfp()));
			st.setInt(9, Integer.parseInt(contrato.getCrncgcp()));
			st.setString(10, contrato.getCrlliqu());
			st.setFloat(11, Float.parseFloat(Utils.converteFloatBR(contrato.getCrnperm())));
			st.setInt(12, Integer.parseInt(contrato.getCrncgmd()));
			st.setDate(13, Utils.stringToDateSQL(contrato.getCrdcadt()));
			st.setString(14, contrato.getCrcnota());
			st.setInt(15, Integer.parseInt(contrato.getCrncgep()));
						
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public void excluir(BeanContrato contrato) {
		try {
			String sql = " DELETE FROM CONTRATO WHERE CRNCODG = (?)";
			
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			
			st.setInt(1, Integer.parseInt(contrato.getCrncodg()));
						
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	public ArrayList<BeanContrato> contratosDoCliente(int clncodg) {
		ArrayList<BeanContrato> contratos = new ArrayList<BeanContrato>();
		try {
			String sql = " SELECT * FROM VW_CONTRATO WHERE CRNCGCL = ? ORDER BY CRDTERM DESC";
			PreparedStatement st = Banco.getConnection().prepareStatement(sql);
			st.setInt(1, clncodg);
			
			contratos.addAll(Utils.getObjectsStr(st, BeanContrato.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
}
