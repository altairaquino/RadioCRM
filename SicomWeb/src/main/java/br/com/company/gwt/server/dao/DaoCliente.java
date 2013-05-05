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
	
	public List<Cliente> getClienteByNome(String nome){
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String hql = " from Cliente c " +
						 " where (upper(c.nome) like upper(:nome)" +
						 " or c.documento = :nome ) "+
						 " order by c.nome";
			
			Query query = createQuery(hql);
			query.setParameter("nome", "%"+nome+"%");
			query.setParameter("cpf", nome);
			query.setMaxResults(20);
			
			clientes.addAll(listFromQuery(query));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public List<Cliente> loadSubList(int offSet,int maxResult, String query){
    	List<Cliente> clientes = new ArrayList<Cliente>();
    	try {
    		String hql = " from Cliente c " +
						 " where (upper(c.nome) like upper(:nome)" +
						 " or c.cpf = :cpf ) "+
						 " order by c.nome";

			Query qr = createQuery(hql);
			qr.setParameter("nome", "%"+query+"%");
			qr.setParameter("cpf", query);
			qr.setFirstResult(offSet);
			qr.setMaxResults(maxResult);
			
			clientes.addAll(listFromQuery(qr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return clientes;
    }

}