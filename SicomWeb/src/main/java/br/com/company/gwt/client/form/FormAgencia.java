package br.com.company.gwt.client.form;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.TextFieldUpper;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.dto.DTOCidade;
import br.com.company.gwt.shared.dto.DTOAgencia;
import br.com.company.gwt.shared.dto.DTOTipoLogradouro;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FormAgencia extends Window {

	private ContentPanel mainPanel;
	private TextFieldUpper tfNome;
	private TextFieldUpper tfRazaoSocial;
	private TextField<String> tfDocumento;
	private NumberField tfComissao;
	private FieldSet fsDadosDaAgencia;
	private FieldSet fsContatos;
	private TextField<String> tfTelefone;
	private TextField<String> tfCelular;
	private TextField<String> tfEmail;
	private MultiField<Field<?>> multiFieldNome;
	private MultiField<Field<?>> multiField;
	private MultiField<Field<?>> mfdTelefone;
	private FieldSet fsEndereco;
	private MultiField<Field<?>> mfdLogradouro;
	private ComboBox<DTOTipoLogradouro> comboTipoLogradouro;
	private TextFieldUpper tfLogradouro;
	private TextField<String> tfNumeroLogradouro;
	private MultiField<Field<?>> mfdComplemento;
	private TextFieldUpper tfComplemento;
	private TextFieldUpper tfBairro;
	private MultiField<Field<?>> mfdCepEstado;
	private TextField<String> tfCep;
	private SimpleComboBox<String> comboEstado;
	private ComboBox<DTOCidade> comboCidade;
	private ToolBar toolBar;
	private Button btSalvar;
	private ListStore<DTOTipoLogradouro> storeTipoLogradouro;
	private Integer id;

	public FormAgencia() {
		setResizable(false);
		setMinimizable(true);
		setHeadingHtml("Cadastro da Agência");
		setSize(640, 515);
		setLayout(new FitLayout());
		
		mainPanel = new ContentPanel();
		mainPanel.setLayout(new FormLayout(LabelAlign.TOP));
		mainPanel.setHeaderVisible(false);
		mainPanel.setFrame(true);
		
		multiFieldNome = new MultiField<Field<?>>();
		multiFieldNome.setSpacing(10);
		multiFieldNome.setFieldLabel("Nome / Razão Social");
		
		fsDadosDaAgencia = new FieldSet();
		fsDadosDaAgencia.setLayout(new FormLayout(LabelAlign.TOP));
		fsDadosDaAgencia.setHeadingHtml("Dados da Agência");
		
		tfNome = new TextFieldUpper();
		tfNome.setWidth(307);

		multiFieldNome.add(tfNome);
		
		tfRazaoSocial = new TextFieldUpper();
		tfRazaoSocial.setWidth(280);
		
		multiFieldNome.add(tfRazaoSocial);

		fsDadosDaAgencia.add(multiFieldNome, new FormData("100%"));
		
		multiField = new MultiField<Field<?>>();
		multiField.setSpacing(10);
		multiField.setFieldLabel("Documento (CPF / CNPJ):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Comissão (%)");
		
		tfDocumento = new TextField<String>();
		tfDocumento.setWidth("180px");
		tfDocumento.setFieldLabel("Documento");
		multiField.add(tfDocumento);
		
		tfComissao = new NumberField();
		tfComissao.setAllowNegative(false);
		tfComissao.setAllowDecimals(true);
		tfComissao.setFieldLabel("Comissão");
		multiField.add(tfComissao);

		fsDadosDaAgencia.add(multiField, new FormData("100%"));

		mainPanel.add(fsDadosDaAgencia, new FormData("100%"));
		
		fsContatos = new FieldSet();
		fsContatos.setHeadingHtml("<b>Contatos</b>");
		fsContatos.setLayout(new FormLayout(LabelAlign.TOP));
		
		mfdTelefone = new MultiField<Field<?>>();
		mfdTelefone.setFieldLabel("Telefone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Celular:" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;E-mail");
		mfdTelefone.setSpacing(10);
		
		tfTelefone = new TextField<String>();
		tfTelefone.setWidth("94px");
		mfdTelefone.add(tfTelefone);
		
		tfCelular = new TextField<String>();
		tfCelular.setWidth("101px");
		mfdTelefone.add(tfCelular);
		
		tfEmail = new TextField<String>();
		tfEmail.setFieldLabel("E-mail");
		tfEmail.setWidth("250px");
		mfdTelefone.add(tfEmail);
		
		fsContatos.add(mfdTelefone, new FormData("100%"));
		
		mainPanel.add(fsContatos, new FormData("100%"));		
		
		fsEndereco = new FieldSet();
		fsEndereco.setLayout(new FormLayout(LabelAlign.TOP));
		fsEndereco.setHeadingHtml("Endereço");
		
		mfdLogradouro = new MultiField<Field<?>>();
		mfdLogradouro.setSpacing(10);
		mfdLogradouro.setFieldLabel("Logradouro");
		
		storeTipoLogradouro = new ListStore<DTOTipoLogradouro>();
		
		comboTipoLogradouro = new ComboBox<DTOTipoLogradouro>();
		comboTipoLogradouro.setStore(storeTipoLogradouro);
		comboTipoLogradouro.setWidth("130px");
		
		mfdLogradouro.add(comboTipoLogradouro);
		
		tfLogradouro = new TextFieldUpper();
		tfLogradouro.setWidth("356px");
		mfdLogradouro.add(tfLogradouro);
		
		tfNumeroLogradouro = new TextField<String>();
		tfNumeroLogradouro.setWidth("92px");
		mfdLogradouro.add(tfNumeroLogradouro);

		fsEndereco.add(mfdLogradouro, new FormData("100%"));
		
		mfdComplemento = new MultiField<Field<?>>();
		mfdComplemento.setSpacing(10);
		mfdComplemento.setFieldLabel("Complemento / Bairro");
		
		tfComplemento = new TextFieldUpper();
		tfComplemento.setWidth("284px");
		tfComplemento.setFieldLabel("complemento");
		
		mfdComplemento.add(tfComplemento);
		
		tfBairro = new TextFieldUpper();
		tfBairro.setWidth("301px");
		tfBairro.setFieldLabel("bairro");
		
		mfdComplemento.add(tfBairro);
		
		fsEndereco.add(mfdComplemento, new FormData("100%"));
				
		mfdCepEstado = new MultiField<Field<?>>();
		mfdCepEstado.setSpacing(10);
		mfdCepEstado.setFieldLabel("Cep / Estado / Cidade");
		
		tfCep = new TextField<String>();
		tfCep.setWidth("129px");
		tfCep.setFieldLabel("cep");
		mfdCepEstado.add(tfCep);
		
		comboEstado = new SimpleComboBox<String>();
		comboEstado.setWidth("70px");
		comboEstado.setFieldLabel("estado");
		mfdCepEstado.add(comboEstado);
		
		comboCidade = new ComboBox<DTOCidade>();
		comboCidade.setDisplayField("nome");
		comboCidade.setForceSelection(true);
		comboCidade.setStore(new ListStore<DTOCidade>());
		comboCidade.setWidth("380px");
		comboCidade.setFieldLabel("cidade");
		mfdCepEstado.add(comboCidade);
		
		fsEndereco.add(mfdCepEstado, new FormData("100%"));

		mainPanel.add(fsEndereco, new FormData("100%"));
		
		toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		
		btSalvar = new Button("Salvar");
		btSalvar.setWidth("102px");
		btSalvar.setBorders(true);
		btSalvar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (validaCampos()){
					salvar();
				}
				
			}
		});
		toolBar.add(btSalvar);
		
		setBottomComponent(toolBar);
		
		add(mainPanel);
		
	}

	protected void salvar() {
		InstanceService.AGENCIA_SERVICE.salvar(getDTOAgenciaFromForm(), new AsyncCallback<DTOAgencia>() {
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
			}
			
			@Override
			public void onSuccess(DTOAgencia result) {
				Info.display("Sucesso", "Agência salva com sucesso.");
			}
			
		});
		
	}

	private DTOAgencia getDTOAgenciaFromForm() {
		DTOAgencia dtoAgencia = new DTOAgencia();
		dtoAgencia.setId(id);
		dtoAgencia.setNome(tfNome.getValue());
		dtoAgencia.setRazaoSocial(tfRazaoSocial.getValue());
		dtoAgencia.setDocumento(tfDocumento.getValue());
		dtoAgencia.setComissao(tfComissao.getValue().floatValue());
		dtoAgencia.setTelefone(tfTelefone.getValue());
		dtoAgencia.setCelular(tfCelular.getValue());
		dtoAgencia.setTipoLogradouro(comboTipoLogradouro.getValue());
		dtoAgencia.setLogradouro(tfLogradouro.getValue());
		dtoAgencia.setComplemento(tfComplemento.getValue());
		dtoAgencia.setBairro(tfBairro.getValue());
		dtoAgencia.setCep(tfCep.getValue());
		dtoAgencia.setCidade(comboCidade.getValue());
		return dtoAgencia;
	}

	protected boolean validaCampos() {
		if (tfNome.getValue() == null){
			WebMessageBox.alert("Nome é obrigatório.");
			return false;
		}
		return true;
	}

}