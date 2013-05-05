package br.com.company.gwt.server.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Config {
	
	@Id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}