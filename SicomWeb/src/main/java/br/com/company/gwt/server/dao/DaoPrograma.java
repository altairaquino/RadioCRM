package br.com.company.gwt.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Programa;

@Named(value="daoPrograma")
public class DaoPrograma extends DaoAbstract<Programa, Integer> {
	
	@Override
	protected Integer getId(Programa programa) {
		return programa.getId();
	}
	
	public List<Programa> getProgramaByNome(String nome){
		List<Programa> programas = new ArrayList<Programa>();
		try {
			String hql = " from Programa c " +
						 " where upper(c.nome) like upper(:nome)" +
						 " order by c.nome";
			
			Query query = createQuery(hql);
			query.setParameter("nome", "%"+nome+"%");
			query.setMaxResults(20);
			
			programas.addAll(listFromQuery(query));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return programas;
	}
	
}