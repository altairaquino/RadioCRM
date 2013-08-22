package br.com.company.gwt.server.dao;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.OrigemContrato;

@Named(value="daoOrigemContrato")
public class DaoOrigemContrato extends DaoAbstract<OrigemContrato, Integer> {
	
	@Override
	protected Integer getId(OrigemContrato origemContrato) {
		return origemContrato.getId();
	}
	
	public OrigemContrato findByNome(String nome){
		OrigemContrato origemContrato = null;
		try {
			String hql = " from OrigemContrato tc where tc.nome = :nome";
			Query query = createQuery(hql);
			query.setString("nome", nome);
			query.setMaxResults(1);
			
			origemContrato = (OrigemContrato) query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origemContrato;
	}

}