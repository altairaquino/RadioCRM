package br.com.company.gwt.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Agencia;

@Named(value="daoAgencia")
public class DaoAgencia extends DaoAbstract<Agencia, Integer> {
	
	@Override
	protected Integer getId(Agencia agencia) {
		return agencia.getId();
	}
	
	@Override
	public List<Agencia> loadAll() {
		List<Agencia> agencias = new ArrayList<Agencia>();
		try {
			Query query = createQuery(getQRAll());
			query.setMaxResults(40);
			
			agencias.addAll(listFromQuery(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return agencias;
	}
	
	public List<Agencia> getAgenciaByNome(String nome){
		List<Agencia> clientes = new ArrayList<Agencia>();
		try {
			String hql = " from Agencia c " +
						 " where (upper(c.nome) like upper(:nome)" +
						 " or c.documento = :documento ) "+
						 " order by c.nome";
			
			Query query = createQuery(hql);
			query.setParameter("nome", "%"+nome+"%");
			query.setParameter("documento", nome);
			query.setMaxResults(20);
			
			clientes.addAll(listFromQuery(query));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public List<Agencia> loadSubList(int offSet,int maxResult, String query){
    	List<Agencia> agencias = new ArrayList<Agencia>();
    	try {
    		String hql = " from Agencia a " +
						 " where upper(a.nome) like upper(:nome)" +
						 " order by a.nome";

			Query qr = createQuery(hql);
			qr.setString("nome", "%"+query+"%");
			qr.setFirstResult(offSet);
			qr.setMaxResults(maxResult);
			
			agencias.addAll(listFromQuery(qr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return agencias;
    }
	
	public Agencia getAgenciaByDocumento(String documento){
		try {
			String hql = " from Agencia c " +
					" where c.documento = :documento ";
			
			Query query = createQuery(hql);
			query.setParameter("documento", documento);
			query.setMaxResults(1);
			
			return (Agencia) query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}