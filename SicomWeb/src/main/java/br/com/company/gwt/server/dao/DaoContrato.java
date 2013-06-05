package br.com.company.gwt.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.com.company.gwt.server.entities.Cliente;
import br.com.company.gwt.server.entities.Contrato;

@Named(value="daoContrato")
public class DaoContrato extends DaoAbstract<Contrato, Integer> {
	
	@Override
	protected Integer getId(Contrato contrato) {
		return contrato.getId();
	}
	
	public List<Contrato> pesquisa(Cliente cliente, Date date){
		List<Contrato> contratos = new ArrayList<Contrato>();
		try{
			String hql = " from Contrato c" +
						 " where c.cliente = :cliente " +
						 " and :data between c.dataInicio and c.dataTermino";
			
			Criteria criteria = createCriteria();
			criteria.add(Restrictions.eq("cliente", cliente));			
			
			Query query = createQuery(hql);
			query.setParameter("cliente", cliente);
			query.setDate("data", date);
			
			contratos.addAll(listFromQuery(query));			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}

}