package br.com.company.gwt.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Cidade;

@Named(value="daoCidade")
public class DaoCidade extends DaoAbstract<Cidade, Integer> {
	
	@Override
	protected Integer getId(Cidade cidade) {
		return cidade.getId();
	}
	
	public List<Cidade> getCidadeByNome(String nome, String uf){
		List<Cidade> cidades = new ArrayList<Cidade>();
		try {
			String hql = " from Cidade c " +
						 " where (upper(c.nome) like upper(:nome)" +
						 " and c.uf = :uf ) "+
						 " order by c.nome";
			
			Query query = createQuery(hql);
			query.setParameter("nome", "%"+nome+"%");
			query.setParameter("uf", uf);
			query.setMaxResults(20);
			
			cidades.addAll(listFromQuery(query));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cidades;
	}
	
	public List<Cidade> loadSubList(int offSet,int maxResult, String query, String uf){
    	List<Cidade> cidades = new ArrayList<Cidade>();
    	try {
    		String hql = " from Cidade c " +
						 " where upper(c.nome) like upper(:nome)" +
						 " and c.uf = :uf" +
						 " order by c.nome";

			Query qr = createQuery(hql);
			qr.setParameter("nome", "%"+query+"%");
			qr.setParameter("uf", uf);
			qr.setFirstResult(offSet);
			qr.setMaxResults(maxResult);
			
			cidades.addAll(listFromQuery(qr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return cidades;
    }

}