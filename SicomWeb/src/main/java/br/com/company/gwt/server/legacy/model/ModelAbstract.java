package br.com.company.gwt.server.legacy.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class ModelAbstract {
	
	@Inject private DriverManagerDataSource dataSource;
	
	protected Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

}