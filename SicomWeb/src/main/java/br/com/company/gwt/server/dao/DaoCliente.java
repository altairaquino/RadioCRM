package br.com.company.gwt.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Cliente;

@Named(value="daoCliente")
public class DaoCliente extends DaoAbstract<Cliente, Integer> {
	
	@Override
	protected Integer getId(Cliente cliente) {
		return cliente.getId();
	}
	
	@Override
	public List<Cliente> loadAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Query query = createQuery(getQRAll());
			query.setMaxResults(40);
			
			clientes.addAll(listFromQuery(query));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public List<Cliente> getClienteByNome(String nome){
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String hql = " from Cliente c " +
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
	
	public Cliente getClienteByDocumento(String documento){
		try {
			String hql = " from Cliente c " +
					" where c.documento = :documento ";
			
			Query query = createQuery(hql);
			query.setParameter("documento", documento);
			query.setMaxResults(1);
			
			return (Cliente) query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Cliente> loadSubList(int offSet,int maxResult, String query){
    	List<Cliente> clientes = new ArrayList<Cliente>();
    	try {
    		String hql = " from Cliente c " +
    					 " where (upper(c.nome) like upper(:nome)" +
    					 " or c.documento = :documento ) "+
    					 " order by c.nome";

			Query qr = createQuery(hql);
			qr.setParameter("nome", "%"+query+"%");
			qr.setParameter("documento", query);
			qr.setFirstResult(offSet);
			qr.setMaxResults(maxResult);
			
			clientes.addAll(listFromQuery(qr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return clientes;
    }

}