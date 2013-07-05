package br.com.company.gwt.client.form.relatorio;

import java.util.Date;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.JasperName;
import br.com.company.gwt.client.component.RelatorioCallback;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.bean.ParametrosReport;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormRelatorioPeriodo extends Window {

	private DateField tfDataIncio;
	private DateField tfDataFinal;
	private FieldSet fsRelatorios;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private Button btnGerarRelatorio;
	private FormPanel panelRelatorio;
	private RadioGroup radioGroup;
	private Radio rdVendasPeriodo;
	private Radio rdVendasPorAgencia;
	private Radio rdRankingVendasAgencia;
	private Radio rdPatrocionioProgramas;
	private Radio rdAniversariantes;

	@SuppressWarnings("deprecation")
	public FormRelatorioPeriodo() {
		
		setHeadingHtml("Relatórios por data");
		setMinimizable(true);
		setResizable(false);
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
		
		setSize(477, 340);
		setLayout(new FitLayout());
		
		formPanel = new FormPanel();
		formPanel.setPadding(4);
		formPanel.setHeaderVisible(false);
		
		tfDataIncio = new DateField();
		tfDataIncio.setFieldLabel("Data Início");
		tfDataIncio.setEditable(false);
		Date data = new Date();
		data.setDate(1);
		tfDataIncio.setValue(data);
		tfDataIncio.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		tfDataIncio.getDatePicker().addListener(Events.Select, new Listener<DatePickerEvent>() {
			
			@Override
			public void handleEvent(DatePickerEvent be) {
				Date data = be.getDatePicker().getValue();
				tfDataFinal.setMinValue(data);
				tfDataFinal.setValue(data);
			};
			
		});
		
		formPanel.add(tfDataIncio, new FormData("100%"));
		
		tfDataFinal = new DateField();
		tfDataFinal.setFieldLabel("Data Final");
		tfDataFinal.setValue(new Date());
		tfDataFinal.setEditable(false);
		tfDataFinal.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		tfDataFinal.setMinValue(data);
		
		formPanel.add(tfDataFinal, new FormData("100%"));
		
		fsRelatorios = new FieldSet();
		fsRelatorios.setHeadingHtml("Relatórios");
		fsRelatorios.setLayout(new FitLayout());
		fsRelatorios.setStyleAttribute("padding", "2px");
		
		panelRelatorio = new FormPanel();
		panelRelatorio.setPadding(0);
		panelRelatorio.setLabelWidth(0);
		panelRelatorio.setLabelSeparator("");
		panelRelatorio.setHeaderVisible(false);
		
		radioGroup = new RadioGroup();
		radioGroup.setOrientation(Orientation.VERTICAL);
		
		rdVendasPeriodo = new Radio();
		rdVendasPeriodo.setBoxLabel("Vendas no período");
		rdVendasPeriodo.setHideLabel(true);
		
		radioGroup.add(rdVendasPeriodo);
		
		rdVendasPorAgencia = new Radio();
		rdVendasPorAgencia.setBoxLabel("Vendas por Agência");
		rdVendasPorAgencia.setHideLabel(true);
		
		radioGroup.add(rdVendasPorAgencia);
		
		rdRankingVendasAgencia = new Radio();
		rdRankingVendasAgencia.setBoxLabel("Ranking de Vendas (Agência)");
		rdRankingVendasAgencia.setHideLabel(true);
		
		radioGroup.add(rdRankingVendasAgencia);
		
		rdPatrocionioProgramas = new Radio();
		rdPatrocionioProgramas.setBoxLabel("Patrocínio de programas");
		rdPatrocionioProgramas.setHideLabel(true);
		rdPatrocionioProgramas.setEnabled(false);
		
		radioGroup.add(rdPatrocionioProgramas);
		
		rdAniversariantes = new Radio();
		rdAniversariantes.setBoxLabel("Aniversariantes");
		rdAniversariantes.setHideLabel(true);
		
		radioGroup.add(rdAniversariantes);
		
		radioGroup.setValue(rdVendasPeriodo);
		
		panelRelatorio.add(radioGroup, new FormData("100%"));
		
		fsRelatorios.add(panelRelatorio);
		
		formPanel.add(fsRelatorios, new FormData("100% 72%"));
		
		toolBar = new ToolBar();
		
		btnGerarRelatorio = new Button("Gerar Relatório");
		btnGerarRelatorio.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeReport16()));
		btnGerarRelatorio.setWidth("117px");
		btnGerarRelatorio.setBorders(true);
		btnGerarRelatorio.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				gerarRelatorio();
			}
		});
		
		toolBar.add(btnGerarRelatorio);
		
		setTopComponent(toolBar);
		
		add(formPanel, new FitData(5));
		
	}
	
	private void gerarRelatorio(){
		
		if (!validaCampos()){
			return;
		}
		
		ParametrosReport parametros = new ParametrosReport();
		parametros.setDataIncio(tfDataIncio.getValue());
		parametros.setDataFim(tfDataFinal.getValue());
		
		if (radioGroup.getValue().equals(rdVendasPeriodo)){
			parametros.setNomeRelatorio(JasperName.CONTRATO_PERIODO);
		}else if (radioGroup.getValue().equals(rdVendasPorAgencia)){
			parametros.setNomeRelatorio(JasperName.VENDAS_AGENCIA_PERIODO);
		}else if (radioGroup.getValue().equals(rdRankingVendasAgencia)){
			parametros.setNomeRelatorio(JasperName.RANKING_VENDAS_AGENCIA);
		}else if (radioGroup.getValue().equals(rdAniversariantes)){
			parametros.setNomeRelatorio(JasperName.ANIVERSARIANTES);
		}
		
		
		InstanceService.RELATORIO_SERVICE.relatorioPeriodo(parametros, new RelatorioCallback());
		
	}

	private boolean validaCampos() {		
		return true;
	}	

}