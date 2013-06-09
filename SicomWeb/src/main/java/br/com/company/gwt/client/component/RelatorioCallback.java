package br.com.company.gwt.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RelatorioCallback implements AsyncCallback<String> {
	
	@Override
	public void onFailure(Throwable caught) {
		WebMessageBox.error("Erro ao gerar relatório: "+ caught.getMessage());
	}
	
	@Override
	public void onSuccess(String reportName) {
		if (reportName != null){
			WebWindowReport windowReport = new WebWindowReport(null, GWT.getHostPageBaseURL()+ "reports/" + reportName);
			windowReport.setVisible(true);
		}
	}
}