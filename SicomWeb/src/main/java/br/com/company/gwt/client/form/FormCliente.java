package br.com.company.gwt.client.form;

import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.TextFieldUpper;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.mvc.ProviderFacadeManager;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOAgencia;
import br.com.company.gwt.shared.dto.DTOCidade;
import br.com.company.gwt.shared.dto.DTOCliente;
import br.com.company.gwt.shared.dto.DTOTipoLogradouro;
import br.com.company.gwt.shared.enums.EnumUF;

import com.extjs.gxt.ui.client.data.BaseModelData;
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
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormCliente extends Window {

	private ContentPanel mainPanel;
	private FieldSet fsDados;
	private ComboBox<DTOAgencia> comboAgencia;
	private Button btnAgencia;
	private RadioGroup radioGroupTipoPessoa;
	private Radio rdFisica;
	private Radio rdJuridica;
	private ListStore<DTOAgencia> storeAgencia;
	private TextFieldUpper tfRazaoSocial;
	private TextFieldUpper tfNome;
	private TextFieldUpper tfSegmento;
	private TextField<String> tfEmail;
	private TextField<String> tfTelefone;
	private FieldSet fsContato;
	private TextFieldUpper tfNomeContato;
	private TextField<String> tfFoneContato;
	private TextField<String> tfCelularContato;
	private DateField tfAniversarioContato;
	private FieldSet fsEndereco;
	private DateField tfAniversarioCliente;
	private ListStore<DTOTipoLogradouro> storeTipoLogradouro;
	private ComboBox<DTOTipoLogradouro> comboTipoLogradouro;
	private TextFieldUpper tfLogradouro;
	private TextFieldUpper tfComplemento;
	private TextField<String> tfCEP;
	private TextFieldUpper tfBairro;
	private ComboBox<BaseModelData> comboEstado;
	private ComboBox<DTOCidade> comboCidade;
	private TextFieldUpper tfNomeProprietario;
	private DateField tfAniversarioProprietario;
	private CheckBox checkAtivo;
	private Button btnCancelar;
	private Button btnSalvar;
	private FieldSet fieldSetDadosDoProprietrio;
	private ListStore<DTOCidade> storeCidade;
	private ListStore<BaseModelData> storeEstado;
	private Integer id;

	public FormCliente() {
		setResizable(false);
		setMinimizable(true);
		setHeadingHtml("Cadastro do Cliente");
		setSize(640, 497);
		setLayout(new FitLayout());
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeCliente16()));
		id = null;
		
		mainPanel = new ContentPanel();
		mainPanel.setFrame(true);
		mainPanel.setHeaderVisible(false);
		mainPanel.setLayout(new AbsoluteLayout());
		
		fsDados = new FieldSet();
		fsDados.setSize("608px", "412px");
		fsDados.setHeadingHtml("Dados do Cliente");
		fsDados.setLayout(new AbsoluteLayout());
		
		fsDados.add(new LabelField("Tipo de pessoa:"), new AbsoluteData(0, 0));
		
		radioGroupTipoPessoa = new RadioGroup();
		radioGroupTipoPessoa.setSize("144px", "20px");
		
		rdFisica = new Radio();
		rdFisica.setWidth("60px");
		rdFisica.setData("tipo", "FISICA");
		rdFisica.setBoxLabel("Física");
		rdFisica.setHideLabel(true);
		
		radioGroupTipoPessoa.add(rdFisica);
		
		rdJuridica = new Radio();
		rdJuridica.setWidth("71px");
		rdFisica.setData("tipo", "JURIDICA");
		rdJuridica.setBoxLabel("Jurídica");
		rdJuridica.setHideLabel(true);
		radioGroupTipoPessoa.add(rdJuridica);

		radioGroupTipoPessoa.setValue(rdFisica);
		
		fsDados.add(radioGroupTipoPessoa, new AbsoluteData(0, 17));
		
		fsDados.add(new LabelField("Agência/Corretor de atendimento:"), new AbsoluteData(283, 0));
		
		RpcProxy<PagingLoadResult<DTOAgencia>> proxyAgencia = new RpcProxy<PagingLoadResult<DTOAgencia>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOAgencia>> callback) {
				InstanceService.AGENCIA_SERVICE.loadPagingList((PagingLoadConfig) loadConfig, callback);
			}
		};
		
		PagingLoader<PagingLoadResult<ModelData>> loaderAgencia = new BasePagingLoader<PagingLoadResult<ModelData>>(proxyAgencia);
		
		storeAgencia = new ListStore<DTOAgencia>(loaderAgencia);
		
		comboAgencia = new ComboBox<DTOAgencia>();
		comboAgencia.setStore(storeAgencia);
		comboAgencia.setSize("278px", "22px");
		comboAgencia.setTemplate(getTemplateNome());
		comboAgencia.setValueField("id");
		comboAgencia.setDisplayField("nome");
		comboAgencia.setItemSelector("div.search-item");
		comboAgencia.setHideTrigger(true);
		comboAgencia.setLoadingText("Carregando...");
		comboAgencia.setEmptyText("Pesquisa a agência..");
		comboAgencia.setPageSize(10);
		
		fsDados.add(comboAgencia, new AbsoluteData(283, 17));
		
		btnAgencia = new Button();
		btnAgencia.setSize("22px", "22px");
		btnAgencia.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeAdiciona16()));
		btnAgencia.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				FormAgencia formAgencia = ProviderFacadeManager.formAgencia.createInstance();
				formAgencia.setModal(true);
				formAgencia.show();
			}
		});
		
		fsDados.add(btnAgencia, new AbsoluteData(562, 17));
		
		fsDados.add(new LabelField("Nome:"), new AbsoluteData(0, 43));
		
		tfNome = new TextFieldUpper();
		tfNome.setSize("277px", "22px");

		fsDados.add(tfNome, new AbsoluteData(0, 59));
		
		fsDados.add(new LabelField("Razão Social:"), new AbsoluteData(283, 43));
		
		tfRazaoSocial = new TextFieldUpper();
		tfRazaoSocial.setSize("303px", "22px");

		fsDados.add(tfRazaoSocial, new AbsoluteData(283, 59));
		
		fsDados.add(new LabelField("Segmento:"), new AbsoluteData(0, 85));
		
		tfSegmento = new TextFieldUpper();
		tfSegmento.setSize("241px", "22px");

		fsDados.add(tfSegmento, new AbsoluteData(0, 103));
		
		fsDados.add(new LabelField("E-mail:"), new AbsoluteData(247, 85));
		
		tfEmail = new TextField<String>();
		tfEmail.setSize("187px", "22px");
		
		fsDados.add(tfEmail, new AbsoluteData(247, 103));
		
		
		fsDados.add(new LabelField("Telefone:"), new AbsoluteData(442, 85));
		
		tfTelefone = new TextField<String>();
		tfTelefone.setSize("144px", "22px");
		
		fsDados.add(tfTelefone, new AbsoluteData(442, 103));
		
		fsContato = new FieldSet();
		fsContato.setSize("586px", "70px");
		fsContato.setHeadingHtml("Dados do Contato");
		fsContato.setLayout(new AbsoluteLayout());
		
		fsContato.add(new LabelField("Nome do contato:"), new AbsoluteData(0, 0));
		
		tfNomeContato = new TextFieldUpper();
		tfNomeContato.setSize("214px", "22px");
		fsContato.add(tfNomeContato, new AbsoluteData(0, 16));
		
		fsContato.add(new LabelField("Telefone:"), new AbsoluteData(220, 0));
		
		tfFoneContato = new TextFieldUpper();
		tfFoneContato.setSize("107px", "22px");
		fsContato.add(tfFoneContato, new AbsoluteData(220, 16));
		
		fsContato.add(new LabelField("Celular:"), new AbsoluteData(333, 0));
		
		tfCelularContato = new TextField<String>();
		tfCelularContato.setSize("107px", "22px");

		fsContato.add(tfCelularContato, new AbsoluteData(333, 16));
		
		fsContato.add(new LabelField("Aniversário:"), new AbsoluteData(447, 0));
		
		tfAniversarioContato = new DateField();
		tfAniversarioContato.setSize("117px", "22px");
		tfAniversarioContato.setEditable(false);
		tfAniversarioContato.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		fsContato.add(tfAniversarioContato, new AbsoluteData(447, 16));		
		
		fsDados.add(fsContato, new AbsoluteData(0, 238));
		
		fsEndereco = new FieldSet();
		fsEndereco.setLayout(new AbsoluteLayout());
		fsEndereco.setSize("586px", "105px");
		fsEndereco.setHeadingHtml("Endereço");
		
		storeTipoLogradouro = new ListStore<DTOTipoLogradouro>();
		
		fsEndereco.add(new LabelField("Tipo:"), new AbsoluteData(0, -4));
		
		comboTipoLogradouro = new ComboBox<DTOTipoLogradouro>();
		comboTipoLogradouro.setStore(storeTipoLogradouro);
		comboTipoLogradouro.setSize("66px", "22px");
		comboTipoLogradouro.setDisplayField("nome");
		comboTipoLogradouro.setValueField("id");
		comboTipoLogradouro.setTriggerAction(TriggerAction.ALL);
		comboTipoLogradouro.setEditable(false);
		
		carregaTipoLogradouro();
		
		fsEndereco.add(comboTipoLogradouro, new AbsoluteData (0, 12));
		
		tfLogradouro = new TextFieldUpper();
		tfLogradouro.setSize("259px", "22px");
		fsEndereco.add(tfLogradouro, new AbsoluteData(74, 12));
		
		tfComplemento = new TextFieldUpper();
		tfComplemento.setSize("225px", "22px");
		fsEndereco.add(tfComplemento, new AbsoluteData(339, 12));
		
		fsEndereco.add(new LabelField("Logradouro:"), new AbsoluteData(75, -4));
		
		fsEndereco.add(new LabelField("Complemento:"), new AbsoluteData(339, -4));
		
		fsEndereco.add(new LabelField("CEP:"), new AbsoluteData(1, 36));
		
		tfCEP = new TextField<String>();
		fsEndereco.add(tfCEP, new AbsoluteData(0, 52));
		tfCEP.setSize("106px", "22px");
		
		fsEndereco.add(new LabelField("Bairro:"), new AbsoluteData(113, 36));
		
		tfBairro = new TextFieldUpper();
		tfBairro.setSize("163px", "22px");
		fsEndereco.add(tfBairro, new AbsoluteData(112, 52));
		
		storeEstado = new ListStore<BaseModelData>();
		
		fsEndereco.add(new LabelField("Estado:"), new AbsoluteData(281, 36));
		
		comboEstado = new ComboBox<BaseModelData>();
		comboEstado.setDisplayField("uf");
		comboEstado.setTriggerAction(TriggerAction.ALL);
		comboEstado.setEditable(false);
		comboEstado.setStore(storeEstado);
		comboEstado.setSize("52px", "22px");
		comboEstado.addSelectionChangedListener(new SelectionChangedListener<BaseModelData>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<BaseModelData> se) {
				if (se.getSelectedItem() != null){
					comboCidade.setEnabled(true);
				}
			}
		});
		fsEndereco.add(comboEstado, new AbsoluteData(281, 52));		
		
		carregaEstados();
		
		fsEndereco.add(new LabelField("Cidade:"), new AbsoluteData(339, 36));
		
		RpcProxy<PagingLoadResult<DTOCidade>> proxyCidade = new RpcProxy<PagingLoadResult<DTOCidade>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOCidade>> callback) {
				InstanceService.CIDADE_SERVICE.loadPagingList(comboEstado.getRawValue(),(PagingLoadConfig) loadConfig, callback);
			}
		};

	    PagingLoader<PagingLoadResult<ModelData>> loaderCidade = new BasePagingLoader<PagingLoadResult<ModelData>>(proxyCidade);
		
		storeCidade = new ListStore<DTOCidade>(loaderCidade);
		
		comboCidade = new ComboBox<DTOCidade>();
		comboCidade.setStore(storeCidade);
		comboCidade.setTemplate(getTemplateNome());
		comboCidade.setEnabled(false);
		comboCidade.setSize("225px", "22px");
		comboCidade.setValueField("id");
		comboCidade.setDisplayField("nome");
		comboCidade.setItemSelector("div.search-item");
		comboCidade.setHideTrigger(true);
		comboCidade.setLoadingText("Carregando cidades...");
		comboCidade.setPageSize(10);		
		
		fsEndereco.add(comboCidade, new AbsoluteData(339, 52));
		
		fsDados.add(fsEndereco, new AbsoluteData(0, 129));
		
		fsDados.add(new LabelField("Aniversário:"), new AbsoluteData(160, -1));
		
		tfAniversarioCliente = new DateField();
		tfAniversarioCliente.setSize("117px", "22px");
		tfAniversarioCliente.setEditable(false);
		tfAniversarioCliente.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		
		fsDados.add(tfAniversarioCliente, new AbsoluteData(160, 17));
		
		mainPanel.add(fsDados, new AbsoluteData(3, 1));
		
		fieldSetDadosDoProprietrio = new FieldSet();
		fieldSetDadosDoProprietrio.setLayout(new AbsoluteLayout());
		fieldSetDadosDoProprietrio.setSize("586px", "70px");
		fieldSetDadosDoProprietrio.setHeadingHtml("Dados do Proprietário");
		
		fieldSetDadosDoProprietrio.add(new LabelField("Nome do proprietário:"), new AbsoluteData(0, -2));
		
		tfNomeProprietario = new TextFieldUpper();
		fieldSetDadosDoProprietrio.add(tfNomeProprietario, new AbsoluteData(0, 16));
		tfNomeProprietario.setSize("328px", "22px");
		
		fieldSetDadosDoProprietrio.add(new LabelField("Aniversário:"), new AbsoluteData(334, -2));
		
		tfAniversarioProprietario = new DateField();
		tfAniversarioProprietario.setSize("104px", "22px");
		tfAniversarioProprietario.setEditable(false);
		tfAniversarioProprietario.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		fieldSetDadosDoProprietrio.add(tfAniversarioProprietario, new AbsoluteData(334, 16));
		
		mainPanel.add(fieldSetDadosDoProprietrio, new AbsoluteData(13, 333));
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.setSize("114px", "24px");
		btnCancelar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.cancelar16()));
		btnCancelar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				FormCliente.this.hide();
			}
		});
		mainPanel.add(btnCancelar, new AbsoluteData(361, 419));
		
		btnSalvar = new Button("Salvar");
		btnSalvar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeConfirma16()));
		btnSalvar.setSize("114px", "24px");
		btnSalvar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (validaCampos()){
					salvar();					
				}
			}
		});
		
		mainPanel.add(btnSalvar, new AbsoluteData(497, 419));
		
		checkAtivo = new CheckBox();
		checkAtivo.setValue(true);
		checkAtivo.setBoxLabel("Ativo");
		checkAtivo.setHideLabel(true);
		
		mainPanel.add(checkAtivo, new AbsoluteData(3, 421));
		
		add(mainPanel);
		
	}

	protected void salvar() {
		mainPanel.mask("Salvando cliente. Aguarde...");
		
		if (!validaCampos()){
			return;
		}
		
		InstanceService.CLIENTE_SERVICE.salvar(getDTOClienteFromForm(), new AsyncCallback<DTOCliente>() {
			
			@Override
			public void onSuccess(DTOCliente cliente) {
				if (cliente.getId() != null){
					Info.display("Sucesso.", "Cliente salvo com sucesso.");
					FormCliente.this.hide();					
				}
				mainPanel.unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				mainPanel.unmask();
			}
		});		
	}

	private DTOCliente getDTOClienteFromForm() {
		DTOCliente dto = new DTOCliente();
		dto.setId(id);
		dto.setNome(tfNome.getValue());
		dto.setDataNascimento(tfAniversarioCliente.getValue());
		dto.setRazaoSocial(tfRazaoSocial.getValue());
		if (radioGroupTipoPessoa.getValue().equals(rdFisica)){
			dto.setTipoPessoa("FISICA");			
		}else{
			dto.setTipoPessoa("JURIDICA");
		}
		dto.setAgencia(comboAgencia.getValue());
		// TODO CRIAR CAMPO DE DOCUMENTO
		dto.setDocumento("");
		dto.setSegmento(tfSegmento.getValue());
		dto.setEmail(tfEmail.getValue());
		dto.setFone(tfTelefone.getValue());
		dto.setTipoLogradouro(comboTipoLogradouro.getValue());
		dto.setLogradouro(tfLogradouro.getValue());
		dto.setComplemento(tfComplemento.getValue());
		dto.setCep(tfCEP.getValue());
		dto.setBairro(tfBairro.getValue());
		dto.setCidade(comboCidade.getValue());
		dto.setNomeContato(tfNomeContato.getValue());
		dto.setFoneContato(tfFoneContato.getValue());
		dto.setCellContato(tfCelularContato.getValue());
		dto.setDataNascimentoContato(tfAniversarioContato.getValue());
		dto.setNomeProprietario(tfNomeProprietario.getValue());
		dto.setDataNascimentoProprietario(tfAniversarioProprietario.getValue());
		dto.setAtivo(checkAtivo.getValue());
		return dto;
	}
	
	public void loadDTOCliente(DTOCliente dto) {
		id = dto.getId();
		tfNome.setValue(dto.getNome());
		tfAniversarioCliente.setValue(dto.getDataNascimento());
		tfRazaoSocial.setValue(dto.getRazaoSocial());
		
		if (dto.getTipoPessoa().equals("FISICA")){
			radioGroupTipoPessoa.setValue(rdFisica);
		}else{
			radioGroupTipoPessoa.setValue(rdJuridica);
		}
		comboAgencia.setValue(dto.getAgencia());
		// TODO CRIAR CAMPO DE DOCUMENTO
		// dto.setDocumento("");
		tfSegmento.setValue(dto.getSegmento());
		tfEmail.setValue(dto.getEmail());
		tfTelefone.setValue(dto.getFone());
		comboTipoLogradouro.setValue(dto.getTipoLogradouro());
		tfLogradouro.setValue(dto.getLogradouro());
		tfComplemento.setValue(dto.getComplemento());
		tfCEP.setValue(dto.getCep());
		tfBairro.setValue(dto.getBairro());
		comboCidade.setValue(dto.getCidade());
		
		if (dto.getCidade() != null){
			BaseModelData modelData = new BaseModelData();
			modelData.set("uf", dto.getCidade().getUF());
			comboEstado.setValue(modelData);
		}
		tfNomeContato.setValue(dto.getNomeContato());
		tfFoneContato.setValue(dto.getFoneContato());
		tfCelularContato.setValue(dto.getCellContato());
		tfAniversarioContato.setValue(dto.getDataNascimentoContato());
		tfNomeProprietario.setValue(dto.getNomeProprietario());
		tfAniversarioProprietario.setValue(dto.getDataNascimentoProprietario());
		checkAtivo.setValue(dto.getAtivo());
	}

	protected boolean validaCampos() {
		if (comboAgencia.getValue() == null){
			WebMessageBox.alert("Informe a Agência!");
			return false;
		}
		if (tfNome.getValue() == null){
			WebMessageBox.alert("Informe o nome!");
			return false;
		}
		if (tfRazaoSocial.getValue() == null){
			WebMessageBox.alert("Informe a razão social!");
			return false;
		}
		if (tfSegmento.getValue() == null){
			WebMessageBox.alert("Informe o segmento!");
			return false;
		}
		if (tfEmail.getValue() == null){
			WebMessageBox.alert("Informe o e-mail!");
			return false;
		}
		if (tfTelefone.getValue() == null){
			WebMessageBox.alert("Informe o telefone!");
			return false;
		}
		if (tfLogradouro.getValue() == null){
			WebMessageBox.alert("Informe o logradouro!");
			return false;
		}
		if (tfComplemento.getValue() == null){
			WebMessageBox.alert("Informe o complemento do endereço!");
			return false;
		}
		if (tfCEP.getValue() == null){
			WebMessageBox.alert("Informe o CEP!");
			return false;
		}
		if (tfBairro.getValue() == null){
			WebMessageBox.alert("Informe o bairro!");
			return false;
		}
		if (comboCidade.getValue() == null){
			WebMessageBox.alert("Informe a cidade!");
			return false;
		}
		if (tfNomeContato.getValue() == null){
			WebMessageBox.alert("Informe o nome do contato!");
			return false;
		}
		if (tfFoneContato.getValue() == null){
			WebMessageBox.alert("Informe o fone do contato!");
			return false;
		}
		if (tfCelularContato.getValue() == null){
			WebMessageBox.alert("Informe o celular do contato!");
			return false;
		}
		if (tfAniversarioContato.getValue() == null){
			WebMessageBox.alert("Informe o aniversário do contato!");
			return false;
		}
		if (tfNomeProprietario.getValue() == null){
			WebMessageBox.alert("Informe o nome do proprietário!");
			return false;
		}
		if (tfAniversarioProprietario.getValue() == null){
			WebMessageBox.alert("Informe o aniversário do proprietário!");
			return false;
		}
		return true;
	}

	private void carregaEstados() {
		for (EnumUF enumUF : EnumUF.values()) {
			BaseModelData bmd = new BaseModelData();
			bmd.set("uf", enumUF.name());
			storeEstado.add(bmd);
		}		
	}
	
	private void carregaTipoLogradouro() {
		InstanceService.TIPOLOGRADOURO_SERVICE.listAll(new AsyncCallback<List<DTOTipoLogradouro>>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());			
			}
			
			public void onSuccess(List<DTOTipoLogradouro> result) {
				storeTipoLogradouro.removeAll();
				storeTipoLogradouro.add(result);
				if (!result.isEmpty()){
					comboTipoLogradouro.setValue(result.get(0));
				}
			};
		});
		
	}
	
	private native String getTemplateNome() /*-{
	return [ 
    	'<tpl for="."><div class="search-item">', 
    	'<span>{nome}</span>', 
    	'</div></tpl>' 
    ].join(""); 
	}-*/;
	
}