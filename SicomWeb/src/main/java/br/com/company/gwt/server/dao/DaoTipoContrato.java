package br.com.company.gwt.server.dao;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.TipoContrato;

@Named(value="daoTipoContrato")
public class DaoTipoContrato extends DaoAbstract<TipoContrato, Integer> {
	
	@Override
	protected Integer getId(TipoContrato tipoContrato) {
		return tipoContrato.getId();
	}
	
	public TipoContrato findByNome(String nome){
		TipoContrato tipoContrato = null;
		try {
			String hql = " from TipoContrato tc where tc.nome = :nome";
			Query query = createQuery(hql);
			query.setString("nome", nome);
			query.setMaxResults(1);
			
			tipoContrato = (TipoContrato) query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoContrato;
	}

}