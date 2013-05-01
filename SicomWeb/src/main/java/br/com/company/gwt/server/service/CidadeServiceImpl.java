package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.dto.DTOCidade;
import br.com.company.gwt.client.remoteinterface.CidadeService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.legacy.bean.BeanCidade;
import br.com.company.gwt.server.legacy.model.ModelCidade;

@Named("cidadeService")
public class CidadeServiceImpl extends InputServletImpl implements CidadeService{
	
	@Inject private ModelCidade modelCidade;

	@Override
	public List<DTOCidade> listAll() {
		List<DTOCidade> cidades = new ArrayList<DTOCidade>();
		try {
			for (BeanCidade bean : modelCidade.getCidades()) {
				DTOCidade cidade = new DTOCidade();
				cidade.setId(Integer.parseInt(bean.getCdncodg()));
				cidade.setNome(bean.getCdcdesc());
				cidades.add(cidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cidades;
	}

	@Override
	public List<DTOCidade> listCidadesByUF(String uf) {
		List<DTOCidade> cidades = new ArrayList<DTOCidade>();
		try {
			for (BeanCidade bean : modelCidade.getCidadesDoEstado(uf)) {
				DTOCidade cidade = new DTOCidade();
				cidade.setId(Integer.parseInt(bean.getCdncodg()));
				cidade.setNome(bean.getCdcdesc());
				cidades.add(cidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cidades;
	}

	

}