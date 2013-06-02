package br.com.company.gwt.client.form;

import java.util.Date;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;

public class FormContrato extends Window {
	
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");

	private ContentPanel mainPanel;
	private ComboBox comboCliente;
	private ListStore storeCliente;
	private DateField tfDataCadastro;
	private FieldSet fsDadosDoContrato;
	private FieldSet fsVigenciaDoContrato;
	private DateField tfDataInicio;
	private DateField tfDataTermino;
	private FieldSet fsInformacoesDeTexto;
	private TextArea tfTexto;
	private ComboBox comboFormaPagamento;
	private ListStore storeFormaPagamento;
	private DateField tfDataPagamento;
	private NumberField tfValor;
	private NumberField tfPermuta;
	private ComboBox comboTipoContrato;
	private ListStore storeTipoContrato;
	private Integer id;

	private RadioGroup radioGroup;

	private Radio rdValorLiquido;

	private Radio rdValorBruto;

	private DualListField tfProgramas;

	private ListStore storeProgramasFrom;

	private ListStore storeProgramasTo;

	private Button btnSalvar;

	private Button btnCancelar;

	public FormContrato() {
		setResizable(false);
		setMinimizable(true);
		setHeading("Cadastro de Contrato");
		setSize(640, 538);
		setLayout(new FitLayout());
		
		id = null;
		
		mainPanel = new ContentPanel();
		mainPanel.setFrame(true);
		mainPanel.setHeaderVisible(false);
		mainPanel.setLayout(new AbsoluteLayout());
		
		fsDadosDoContrato = new FieldSet();
		fsDadosDoContrato.setSize("602px", "460px");
		fsDadosDoContrato.setHeading("Dados do Contrato");
		fsDadosDoContrato.setLayout(new AbsoluteLayout());
		
		storeCliente = new ListStore();
		
		comboCliente = new ComboBox();
		comboCliente.setStore(storeCliente);
		comboCliente.setSize("456px", "22px");
		
		fsDadosDoContrato.add(comboCliente, new AbsoluteData(0, 17));
		
		fsDadosDoContrato.add(new LabelField("Cliente:"), new AbsoluteData(0, 0));
		
		tfDataCadastro = new DateField();
		tfDataCadastro.setSize("118px", "22px");
		tfDataCadastro.setValue(new Date());
		tfDataCadastro.getPropertyEditor().setFormat(dateFormat);

		fsDadosDoContrato.add(tfDataCadastro, new AbsoluteData(462, 17));
		
		fsDadosDoContrato.add(new LabelField("Data de Cadastro:"), new AbsoluteData(462, 0));
		
		fsVigenciaDoContrato = new FieldSet();
		fsVigenciaDoContrato.setLayout(new AbsoluteLayout());
		fsVigenciaDoContrato.setSize("580px", "60px");
		fsVigenciaDoContrato.setHeading("Vigência do Contrato");
		
		fsVigenciaDoContrato.add(new LabelField("Data de Início:"), new AbsoluteData(0, 0));
		
		tfDataInicio = new DateField();
		tfDataInicio.setSize("124px", "22px");
		tfDataInicio.getPropertyEditor().setFormat(dateFormat);
		fsVigenciaDoContrato.add(tfDataInicio, new AbsoluteData(84, -3));
		
		fsVigenciaDoContrato.add(new LabelField("Data de Término:"), new AbsoluteData(222, 0));
		
		tfDataTermino = new DateField();
		tfDataTermino.setSize("118px", "22px");
		tfDataTermino.getPropertyEditor().setFormat(dateFormat);
		fsVigenciaDoContrato.add(tfDataTermino, new AbsoluteData(327, -3));
		
		fsDadosDoContrato.add(fsVigenciaDoContrato, new AbsoluteData(0, 43));
		
		fsInformacoesDeTexto = new FieldSet();
		fsInformacoesDeTexto.setLayout(new FitLayout());
		fsInformacoesDeTexto.setSize("580px", "100px");
		fsInformacoesDeTexto.setHeading("Informações de texto");
		
		tfTexto = new TextArea();
		fsInformacoesDeTexto.add(tfTexto);

		fsDadosDoContrato.add(fsInformacoesDeTexto, new AbsoluteData(0, 110));
		
		fsDadosDoContrato.add(new LabelField("Forma de Pagamento:"), new AbsoluteData(0, 215));
		
		storeFormaPagamento = new ListStore();
		
		comboFormaPagamento = new ComboBox();
		comboFormaPagamento.setStore(storeFormaPagamento);
		comboFormaPagamento.setSize("276px", "22px");
		fsDadosDoContrato.add(comboFormaPagamento, new AbsoluteData(0, 235));
		
		fsDadosDoContrato.add(new LabelField("Data pagamento:"), new AbsoluteData(378, 259));
		
		tfDataPagamento = new DateField();
		tfDataPagamento.setSize("114px", "22px");
		tfDataPagamento.getPropertyEditor().setFormat(dateFormat);
		fsDadosDoContrato.add(tfDataPagamento, new AbsoluteData(379, 279));
		
		fsDadosDoContrato.add(new LabelField("Valor:"), new AbsoluteData(282, 214));
		
		tfValor = new NumberField();
		tfValor.setSize("92px", "22px");
		fsDadosDoContrato.add(tfValor, new AbsoluteData(282, 235));
		
		fsDadosDoContrato.add(new LabelField("Permuta (%):"), new AbsoluteData(282, 258));
		
		tfPermuta = new NumberField();
		tfPermuta.setEnabled(false);
		tfPermuta.setSize("91px", "22px");
		fsDadosDoContrato.add(tfPermuta, new AbsoluteData(282, 279));	
		
		fsDadosDoContrato.add(new LabelField("Tipo de Contrato:"), new AbsoluteData(0, 261));
		
		storeTipoContrato = new ListStore();
		
		comboTipoContrato = new ComboBox();
		comboTipoContrato.setStore(storeTipoContrato);
		comboTipoContrato.setSize("276px", "22px");
		fsDadosDoContrato.add(comboTipoContrato, new AbsoluteData(0, 279));
		
		radioGroup = new RadioGroup();
		radioGroup.setHeight("20px");
		
		rdValorLiquido = new Radio();
		rdValorLiquido.setWidth("92px");
		rdValorLiquido.setBoxLabel("Valor líquido");
		rdValorLiquido.setHideLabel(true);
		radioGroup.add(rdValorLiquido);
		
		rdValorBruto = new Radio();
		rdValorBruto.setBoxLabel("Valor bruto");
		rdValorBruto.setHideLabel(true);
		radioGroup.add(rdValorBruto);
		radioGroup.setValue(rdValorLiquido);

		fsDadosDoContrato.add(radioGroup, new AbsoluteData(380, 235));
		
		fsDadosDoContrato.add(new LabelField("Programas:"), new AbsoluteData(0, 307));		
		fsDadosDoContrato.add(new LabelField("Programas patrocinados:"), new AbsoluteData(305, 306));
		
		storeProgramasFrom = new ListStore();
		storeProgramasTo = new ListStore();
		
		tfProgramas = new DualListField();
		tfProgramas.getFromList().setStore(new ListStore());
		tfProgramas.getToList().setStore(new ListStore());
		tfProgramas.getFromList().setStore(new ListStore());
		tfProgramas.getToList().setStore(new ListStore());
		tfProgramas.getFromList().setStore(storeProgramasFrom);
		tfProgramas.getToList().setStore(storeProgramasTo);	
		tfProgramas.setSize("580px", "93px");
		
		fsDadosDoContrato.add(tfProgramas, new AbsoluteData(0, 328));		

		mainPanel.add(fsDadosDoContrato, new AbsoluteData(6, 0));
		
		btnSalvar = new Button("Salvar");
		btnSalvar.setSize("100px", "24px");
		mainPanel.add(btnSalvar, new AbsoluteData(508, 464));
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.setSize("100px", "24px");
		mainPanel.add(btnCancelar, new AbsoluteData(402, 464));
		
		add(mainPanel);
		
	}
}