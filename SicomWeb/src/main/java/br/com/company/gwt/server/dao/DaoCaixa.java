/*
package br.com.company.gwt.server.dao;

import java.util.Date;

import javax.inject.Named;

import org.hibernate.Query;

import br.com.company.gwt.server.entities.Caixa;
import br.com.company.gwt.server.entities.Funcionario;
import br.com.company.gwt.server.entities.ItemCaixa;
import br.com.company.gwt.server.entities.Loja;
import br.com.company.gwt.server.entities.enums.EnumStatusItemCaixa;

@Named(value="daoCaixa")
public class DaoCaixa extends DaoAbstract<Caixa, Integer> {
	
	@Override
	protected Integer getId(Caixa caixa) {
		return caixa.getCaixaId();
	}
	
	public Caixa getCaixaByDate(Loja loja, Date data){
		Caixa caixa = null;
		try {
			String hql = " from Caixa c " +
						 " where cast(c.dataAbertura as date) = :data" +
						 " and c.loja = :loja";
			
			Query query = createQuery(hql);
			query.setDate("data", data);
			query.setParameter("loja", loja);
			query.setMaxResults(1);
			
			caixa = (Caixa) query.uniqueResult();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return caixa;
	}
	
	public Caixa getAberturaCaixaDefault(Loja loja, Funcionario funcionario){
		Caixa caixa = new Caixa();
		try {
			
			Date date = new Date();
			
			caixa.setDataAbertura(date);
			caixa.setLoja(loja);
			caixa.setFuncionarioAbertura(funcionario);
			
			ItemCaixa itemCaixa = new ItemCaixa();
			itemCaixa.setCaixa(caixa);
			itemCaixa.setStatusItemCaixa(EnumStatusItemCaixa.FINALIZADO);
			itemCaixa.setDataRegistro(date);
			itemCaixa.setValor(0f);
			itemCaixa.setFuncionario(funcionario);
			itemCaixa.setDescricao("Abertura de Caixa");

			caixa.addItemCaixa(itemCaixa);
			
			store(caixa);			
						
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return caixa;
	}

}
*/