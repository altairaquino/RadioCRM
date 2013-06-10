package br.com.company.gwt.server.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.company.gwt.client.remoteinterface.RelatorioService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.jasper.GerarReport;
import br.com.company.gwt.shared.bean.ParametrosReport;

@Named("relatorioService")
public class RelatorioServiceImpl extends InputServletImpl implements RelatorioService{
	
	private static Logger logger = Logger.getLogger(RelatorioServiceImpl.class);
	
	@Inject
	private GerarReport gerarReport;

	@Override
	public String relatorioAgencias(ParametrosReport parametros) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("JASPER_NAME", parametros.getNomeRelatorio());
		logger.debug("relatorioProdutos()");
		return gerarReport.exportToPDF(getServletRequest(), params);
	}
	
	@Override
	public String relatorio(ParametrosReport parametros) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("JASPER_NAME", parametros.getNomeRelatorio());
		logger.debug("relatorio()");
		return gerarReport.exportToPDF(getServletRequest(), params);
	}

	@Override
	public String relatorioClientes(ParametrosReport parametros) throws Exception {
		return null;
	}
	
	@Override
	public String relatorioProgramas(ParametrosReport parametros) throws Exception {
		return null;
	}

	@Override
	public String relatorioPeriodo(ParametrosReport parametros) {
		return null;
	}	

}