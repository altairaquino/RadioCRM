package br.com.company.gwt.client.form;

import java.util.Date;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOCliente;
import br.com.company.gwt.shared.dto.DTOContrato;
import br.com.company.gwt.shared.dto.DTOFormaPagamento;
import br.com.company.gwt.shared.dto.DTOPrograma;
import br.com.company.gwt.shared.dto.DTOTipoContrato;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormContrato extends Window {
	
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");

	private ContentPanel mainPanel;
	private ComboBox<DTOCliente> comboCliente;
	private ListStore<DTOCliente> storeCliente;
	private DateField tfDataCadastro;
	private FieldSet fsDadosDoContrato;
	private FieldSet fsVigenciaDoContrato;
	private DateField tfDataInicio;
	private DateField tfDataTermino;
	private FieldSet fsInformacoesDeTexto;
	private TextArea tfTexto;
	private ComboBox<DTOFormaPagamento> comboFormaPagamento;
	private ListStore<DTOFormaPagamento> storeFormaPagamento;
	private DateField tfDataPagamento;
	private NumberField tfValor;
	private NumberField tfPermuta;
	private ComboBox<DTOTipoContrato> comboTipoContrato;
	private ListStore<DTOTipoContrato> storeTipoContrato;
	private Integer id;

	private RadioGroup radioGroup;

	private Radio rdValorLiquido;

	private Radio rdValorBruto;

	private DualListField<DTOPrograma> tfProgramas;

	private ListStore<DTOPrograma> storeProgramasFrom;

	private ListStore<DTOPrograma> storeProgramasTo;

	private Button btnSalvar;

	private Button btnCancelar;

	public FormContrato() {
		setResizable(false);
		setMinimizable(true);
		setHeadingHtml("Cadastro de Contrato");
		setSize(640, 538);
		setLayout(new FitLayout());
		
		id = null;
		
		mainPanel = new ContentPanel();
		mainPanel.setFrame(true);
		mainPanel.setHeaderVisible(false);
		mainPanel.setLayout(new AbsoluteLayout());
		
		fsDadosDoContrato = new FieldSet();
		fsDadosDoContrato.setSize("602px", "460px");
		fsDadosDoContrato.setHeadingHtml("Dados do Contrato");
		fsDadosDoContrato.setLayout(new AbsoluteLayout());
		
		RpcProxy<PagingLoadResult<DTOCliente>> proxyCliente = new RpcProxy<PagingLoadResult<DTOCliente>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOCliente>> callback) {
				InstanceService.CLIENTE_SERVICE.loadPagingList((PagingLoadConfig) loadConfig, callback);
			}
		};

	    PagingLoader<PagingLoadResult<ModelData>> loaderCliente = new BasePagingLoader<PagingLoadResult<ModelData>>(proxyCliente);
		
		storeCliente = new ListStore<DTOCliente>(loaderCliente);
		
		comboCliente = new ComboBox<DTOCliente>();
		comboCliente.setStore(storeCliente);
		comboCliente.setSize("456px", "22px");
		comboCliente.setTemplate(getTemplateNome());
		comboCliente.setValueField("id");
		comboCliente.setDisplayField("nome");
		comboCliente.setItemSelector("div.search-item");
		comboCliente.setHideTrigger(true);
		comboCliente.setLoadingText("Carregando clientes...");
		comboCliente.setPageSize(10);
		
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
		fsVigenciaDoContrato.setHeadingHtml("Vigência do Contrato");
		
		fsVigenciaDoContrato.add(new LabelField("Data de Início:"), new AbsoluteData(0, 0));
		
		tfDataInicio = new DateField();
		tfDataInicio.setSize("124px", "22px");
		tfDataInicio.setValue(new Date());
		tfDataInicio.getPropertyEditor().setFormat(dateFormat);
		fsVigenciaDoContrato.add(tfDataInicio, new AbsoluteData(84, -3));
		
		fsVigenciaDoContrato.add(new LabelField("Data de Término:"), new AbsoluteData(222, 0));
		
		tfDataTermino = new DateField();
		tfDataTermino.setSize("118px", "22px");
		tfDataTermino.setValue(new Date());
		tfDataTermino.getPropertyEditor().setFormat(dateFormat);
		fsVigenciaDoContrato.add(tfDataTermino, new AbsoluteData(327, -3));
		
		fsDadosDoContrato.add(fsVigenciaDoContrato, new AbsoluteData(0, 43));
		
		fsInformacoesDeTexto = new FieldSet();
		fsInformacoesDeTexto.setLayout(new FitLayout());
		fsInformacoesDeTexto.setSize("580px", "100px");
		fsInformacoesDeTexto.setHeadingHtml("Informações de texto");
		
		tfTexto = new TextArea();
		fsInformacoesDeTexto.add(tfTexto);

		fsDadosDoContrato.add(fsInformacoesDeTexto, new AbsoluteData(0, 110));
		
		fsDadosDoContrato.add(new LabelField("Forma de Pagamento:"), new AbsoluteData(0, 215));
		
		storeFormaPagamento = new ListStore<DTOFormaPagamento>();
		
		comboFormaPagamento = new ComboBox<DTOFormaPagamento>();
		comboFormaPagamento.setStore(storeFormaPagamento);
		comboFormaPagamento.setValueField("id");
		comboFormaPagamento.setDisplayField("nome");
		comboFormaPagamento.setTriggerAction(TriggerAction.QUERY);
		comboFormaPagamento.setSize("276px", "22px");
		comboFormaPagamento.addSelectionChangedListener(new SelectionChangedListener<DTOFormaPagamento>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DTOFormaPagamento> se) {
				Boolean temPermuta = se.getSelectedItem().getTemPermuta();
				tfPermuta.setEnabled(temPermuta);
				if (!temPermuta){
					tfPermuta.clear();
				}
			}
		});
		fsDadosDoContrato.add(comboFormaPagamento, new AbsoluteData(0, 235));
		
		fsDadosDoContrato.add(new LabelField("Data pagamento:"), new AbsoluteData(378, 259));
		
		tfDataPagamento = new DateField();
		tfDataPagamento.setSize("114px", "22px");
		tfDataPagamento.setValue(new Date());
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
		
		storeTipoContrato = new ListStore<DTOTipoContrato>();
		
		comboTipoContrato = new ComboBox<DTOTipoContrato>();
		comboTipoContrato.setStore(storeTipoContrato);
		comboTipoContrato.setValueField("id");
		comboTipoContrato.setDisplayField("nome");
		comboTipoContrato.setTriggerAction(TriggerAction.QUERY);
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
		
		storeProgramasFrom = new ListStore<DTOPrograma>();
		storeProgramasTo = new ListStore<DTOPrograma>();
		
		tfProgramas = new DualListField<DTOPrograma>();
		tfProgramas.getFromList().setStore(storeProgramasFrom);
		tfProgramas.getFromList().setDisplayField("nome");
		tfProgramas.getToList().setStore(storeProgramasTo);	
		tfProgramas.getToList().setDisplayField("nome");
		tfProgramas.setSize("580px", "93px");
		
		fsDadosDoContrato.add(tfProgramas, new AbsoluteData(0, 328));	

		mainPanel.add(fsDadosDoContrato, new AbsoluteData(6, 0));
		
		btnSalvar = new Button("Salvar");
		btnSalvar.setSize("100px", "24px");
		btnSalvar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeConfirma16()));
		btnSalvar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (validaCampos()){
					salvar();
				}				
			}
		});
		mainPanel.add(btnSalvar, new AbsoluteData(508, 464));
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.cancelar16()));
		btnCancelar.setSize("100px", "24px");
		mainPanel.add(btnCancelar, new AbsoluteData(402, 464));
		
		add(mainPanel);
		
		loadProgramas();
		loadTiposContrato();
		loadFormasPagamento();
		
	}
	
	private void loadFormasPagamento() {
		InstanceService.FORMAPAGAMENTO_SERVICE.listAll(new AsyncCallback<List<DTOFormaPagamento>>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());			
			}
			@Override
			public void onSuccess(List<DTOFormaPagamento> formas) {
				storeFormaPagamento.add(formas);
			};
		});
		
	}

	private void loadTiposContrato() {
		InstanceService.TIPOCONTRATO_SERVICE.listAll(new AsyncCallback<List<DTOTipoContrato>>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());			
			}
			@Override
			public void onSuccess(List<DTOTipoContrato> tipos) {
				storeTipoContrato.add(tipos);
			};
		});
		
	}

	private void loadProgramas() {
		InstanceService.PROGRAMA_SERVICE.listAll(new AsyncCallback<List<DTOPrograma>>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());			
			}
			@Override
			public void onSuccess(List<DTOPrograma> programas) {
				storeProgramasFrom.add(programas);
			};
		});
		
	}

	protected void salvar() {
		mainPanel.mask("Salvando. Aguarde...");
		InstanceService.CONTRATO_SERVICE.salvar(getDTOContratoFromForm(), new AsyncCallback<DTOContrato>() {
			
			@Override
			public void onSuccess(DTOContrato result) {
				Info.display("Info", "Salvo com sucesso!");
				mainPanel.unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				mainPanel.unmask();
			}
		});
	}

	protected Boolean validaCampos(){
		
		return true;
	}

	public void loadDTOContrato(DTOContrato dto) {
		id = dto.getId();
	}
	
	protected DTOContrato getDTOContratoFromForm(){
		DTOContrato dto = new DTOContrato();
		dto.setId(id);
		return dto;
	}
	
	private native String getTemplateNome() /*-{
	return [ 
    	'<tpl for="."><div class="search-item">', 
    	'<span>{nome}</span>', 
    	'</div></tpl>' 
    ].join(""); 
	}-*/;
	
}