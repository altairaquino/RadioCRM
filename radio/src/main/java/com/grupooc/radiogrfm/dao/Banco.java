package com.grupooc.radiogrfm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Banco {
	
	private static Connection con = null;
	
	public static Connection getConnection(){
		if(con == null){
			try{
				Class.forName("org.firebirdsql.jdbc.FBDriver");
				con = DriverManager.getConnection("jdbc:firebirdsql:localhost:D:\\workspaces\\workspaceAltair\\radio\\banco\\sicom.gdb?defaultResultSetHoldable=True","SYSDBA","masterkey");				
//				con = DriverManager.getConnection("jdbc:firebirdsql:189.43.225.161:sicom?defaultResultSetHoldable=True","SYSDBA","masterkey");				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
		
}
