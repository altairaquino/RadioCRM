package br.com.company.gwt.server.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Named("gerarReport")
public class GerarReport {
	
	@Inject
	private DriverManagerDataSource dataSource;
	
	public String exportToPDF(HttpServletRequest request, Map<String, Object> params) throws Exception {
		
		String realPath = request.getSession().getServletContext().getRealPath("");

		parseParams(realPath, params);
		
		InputStream inputStream = new FileInputStream(
				new File(params.get("JASPER_PATH").toString()+
						params.get("JASPER_NAME").toString() + ".jasper"));
		
		String reportName = System.currentTimeMillis() + ".pdf";

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource.getConnection());
		
		JasperExportManager.exportReportToPdfFile(jasperPrint,params.get("REPORT_OUT").toString() + reportName);
		
		return reportName;
	}
	
	public String exportToPDF(HttpServletRequest request, Map<String, Object> params, Collection<?> collection) throws Exception {
		
		String realPath = request.getSession().getServletContext().getRealPath("");
		
		parseParams(realPath, params);
		
		InputStream inputStream = new FileInputStream(
				new File(params.get("JASPER_PATH").toString()+
						params.get("JASPER_NAME").toString() + ".jasper"));
		
		String reportName = System.currentTimeMillis() + ".pdf";
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, jrDataSource); 
		
		JasperExportManager.exportReportToPdfFile(jasperPrint,params.get("REPORT_OUT").toString() + reportName);
		
		return reportName;
	}
	
	private void parseParams(String realPath, Map<String, Object> params){
		params.put("IMAGEM_DIR", realPath + "/imagens/");
		params.put("JASPER_PATH", realPath + "/jasper/");
		params.put("REPORT_OUT", realPath +"/reports/");
		params.put("REPORT_LOCALE", new Locale("pt", "BR"));
		params.put("SUBREPORT_DIR", params.get("JASPER_PATH"));
//		params.put("IMAGEM_LOGO", params.get("IMAGEM_DIR") + "m2m_logo.jpg");
	}

}