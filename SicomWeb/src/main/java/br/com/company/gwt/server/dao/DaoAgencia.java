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
	
	public List<Agencia> loadSubList(int offSet,int maxResult, String query){
    	List<Agencia> agencias = new ArrayList<Agencia>();
    	try {
    		String hql = " from Agencia a " +
						 " where upper(a.nome) like upper(:nome)" +
						 " order by a.nome";

			Query qr = createQuery(hql);
			qr.setParameter("nome", "%"+query+"%");
			qr.setFirstResult(offSet);
			qr.setMaxResults(maxResult);
			
			agencias.addAll(listFromQuery(qr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return agencias;
    }
	
}