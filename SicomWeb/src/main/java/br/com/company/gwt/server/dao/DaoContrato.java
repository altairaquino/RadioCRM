package br.com.company.gwt.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Cliente;
import br.com.company.gwt.server.entities.Contrato;

@Named(value="daoContrato")
public class DaoContrato extends DaoAbstract<Contrato, Integer> {
	
	@Override
	protected Integer getId(Contrato contrato) {
		return contrato.getId();
	}
	
	@Override
	public List<Contrato> loadAll() {
		List<Contrato> contratos = new ArrayList<Contrato>();
		try {
			Query query = createQuery(getQRAll() + " " +
					" where e.dataCancelamento is null" +
					" order by e.dataCadastro desc");
			
			query.setMaxResults(40);
			
			contratos.addAll(listFromQuery(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return contratos;
	}
	
	public List<Contrato> pesquisa(Cliente cliente, Date date){
		List<Contrato> contratos = new ArrayList<Contrato>();
		try{
			String hql = " from Contrato c" +
						 " where c.cliente = :cliente " +
						 " and :data between c.dataInicio and c.dataTermino" +
						 " and c.dataCancelamento is null";
			
			Query query = createQuery(hql);
			query.setParameter("cliente", cliente);
			query.setDate("data", date);
			
			contratos.addAll(listFromQuery(query));			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
	
	public List<Contrato> pesquisa(Cliente cliente){
		List<Contrato> contratos = new ArrayList<Contrato>();
		try{
			String hql = " from Contrato c" +
					" where c.cliente = :cliente " +
					" and c.dataCancelamento is null" +
					" order by c.dataCadastro desc";
			
			Query query = createQuery(hql);
			query.setParameter("cliente", cliente);
			
			contratos.addAll(listFromQuery(query));			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
	
	public List<Contrato> pesquisa(Date date){
		List<Contrato> contratos = new ArrayList<Contrato>();
		try{
			String hql = " from Contrato c" +
					" where cast(c.dataCadastro as date) = :data" +
					" and c.dataCancelamento is null";
			
			Query query = createQuery(hql);
			query.setDate("data", date);
			
			contratos.addAll(listFromQuery(query));			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}

}