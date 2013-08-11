package br.com.company.gwt.client.form;

import java.util.ArrayList;
import java.util.List;

import br.com.company.gwt.client.InstanceService;
import br.com.company.gwt.client.component.CurrencyField;
import br.com.company.gwt.client.component.TextFieldUpper;
import br.com.company.gwt.client.component.WebMessageBox;
import br.com.company.gwt.client.resources.ImagensResources;
import br.com.company.gwt.shared.dto.DTOPrograma;
import br.com.company.gwt.shared.dto.DTOProgramacao;
import br.com.company.gwt.shared.enums.DiaSemana;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class FormPrograma extends Window {

	private ToolBar toolBar;
	private Button btnSalvar;
	private ContentPanel centerPanel;
	private ContentPanel rightPanel;
	private FieldSet fsDadosDoPrograma;
	private FieldSet fsProgramacao;
	private TextFieldUpper tfNome;
	private CurrencyField tfValor;
	private Grid<DTOProgramacao> gridProgramacao;
	private ListStore<DTOProgramacao> storeProgramacao;
	private CheckBox checkHabilitado;
	private SimpleComboBox<String> comboDiaSemana;
	private SimpleComboBox<String> comboHoraInicio;
	private SimpleComboBox<String> comboHoraFinal;
	private Button btnAdiciona;
	private Integer id;

	public FormPrograma() {
		
		setHeadingHtml("Cadastro do Programa");
		setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeMicrofone16()));
		setMinimizable(true);
		setSize(700, 340);
		setLayout(new BorderLayout());
		id = null;
		
		centerPanel = new ContentPanel();
		centerPanel.setFrame(true);
		centerPanel.setHeaderVisible(false);
		
		rightPanel = new ContentPanel();
		rightPanel.setFrame(true);
		rightPanel.setHeaderVisible(false);
		rightPanel.setLayout(new FitLayout());
		
		toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		toolBar.setSize("300px", "30px");
		
		btnSalvar = new Button("Salvar");
		btnSalvar.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeConfirma16()));
		btnSalvar.setWidth("120px");
		btnSalvar.setBorders(true);
		btnSalvar.addSelectionListener(new SelectionListener<ButtonEvent>() {			
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!validaCampos()){
					return;
				}
				salvar();
			}
		});
		
		toolBar.add(btnSalvar);
		
		centerPanel.setLayout(new FitLayout());		
		
		fsDadosDoPrograma = new FieldSet();
		
		fsDadosDoPrograma.setLayout(new FormLayout(LabelAlign.TOP));
		fsDadosDoPrograma.setHeadingHtml("Dados do programa");
		
		tfNome = new TextFieldUpper();
		tfNome.setFieldLabel("Nome");

		fsDadosDoPrograma.add(tfNome, new FormData("100%"));
		
		tfValor = new CurrencyField();
		tfValor.setFieldLabel("Valor do Patrocinio");

		fsDadosDoPrograma.add(tfValor, new FormData("50%"));
		
		checkHabilitado = new CheckBox();
		checkHabilitado.setValue(true);
		checkHabilitado.setBoxLabel("Habilitado");
		checkHabilitado.setHideLabel(true);
		fsDadosDoPrograma.add(checkHabilitado, new FormData("100%"));
		
		centerPanel.add(fsDadosDoPrograma);		
		
		fsProgramacao = new FieldSet();
		fsProgramacao.setHeadingHtml("Programação");
		fsProgramacao.setLayout(new AbsoluteLayout());
		
		comboDiaSemana = new SimpleComboBox<String>();
		comboDiaSemana.setSize("112px", "22px");
		comboDiaSemana.setEditable(false);
		for (DiaSemana dia : DiaSemana.values()) {
			comboDiaSemana.add(dia.name());
		}
		fsProgramacao.add(comboDiaSemana, new AbsoluteData(0, 17));
		
		comboHoraInicio = new SimpleComboBox<String>();
		comboHoraInicio.setSize("61px", "22px");
		comboHoraInicio.add(getHorasCombo());
		comboHoraInicio.setEditable(false);
		fsProgramacao.add(comboHoraInicio, new AbsoluteData(118, 17));
		
		comboHoraFinal = new SimpleComboBox<String>();
		comboHoraFinal.setSize("61px", "22px");
		comboHoraFinal.add(getHorasCombo());
		comboHoraFinal.setEditable(false);
		fsProgramacao.add(comboHoraFinal, new AbsoluteData(185, 17));
		
		btnAdiciona = new Button();
		btnAdiciona.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.iconeAdiciona16()));
		btnAdiciona.setSize("23px", "23px");
		btnAdiciona.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				adicionaProgramacao();
			}
		});
		
		fsProgramacao.add(btnAdiciona, new AbsoluteData(252, 16));
		
		fsProgramacao.add(new LabelField("Dia da Semana:"), new AbsoluteData(0, -2));
		
		fsProgramacao.add(new LabelField("Início:"), new AbsoluteData(118, -2));
		
		fsProgramacao.add(new LabelField("Termino:"), new AbsoluteData(184, -2));

		storeProgramacao = new ListStore<DTOProgramacao>();
		
		gridProgramacao = new Grid<DTOProgramacao>(storeProgramacao, getColumnModel());
		gridProgramacao.setSize("300px", "180px");
		gridProgramacao.setBorders(true);
		gridProgramacao.setAutoExpandColumn("diaSemana");

		fsProgramacao.add(gridProgramacao, new AbsoluteData(0, 45));
		
		rightPanel.add(fsProgramacao);
		
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER, 350, 300, 400));		
		add(rightPanel, new BorderLayoutData(LayoutRegion.EAST, 350, 300, 400));
		
		setBottomComponent(toolBar);
	}
	
	protected void adicionaProgramacao() {
		DTOProgramacao dto = new DTOProgramacao();
		dto.setDiaSemana(comboDiaSemana.getRawValue());
		dto.setHoraInicio(comboHoraInicio.getRawValue());
		dto.setHoraTermino(comboHoraFinal.getRawValue());
		storeProgramacao.add(dto);		
	}

	private ColumnModel getColumnModel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig columnConfig = new ColumnConfig("diaSemana", "Dia da Semana", 120);
		columnConfig.setAlignment(HorizontalAlignment.CENTER);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("horaInicio", "Inicio", 60);
		columnConfig.setAlignment(HorizontalAlignment.CENTER);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("horaTermino", "Término", 60);
		columnConfig.setAlignment(HorizontalAlignment.CENTER);
		configs.add(columnConfig);
		
		columnConfig = new ColumnConfig("button", "-", 30);
		columnConfig.setAlignment(HorizontalAlignment.CENTER);
		columnConfig.setRenderer(buttonRenderer);
		configs.add(columnConfig);
		
		
		return new ColumnModel(configs);

	}
	
	private void salvar(){
		mask("Salvando programa...");
		InstanceService.PROGRAMA_SERVICE.salvar(getDTOPrograma(), new AsyncCallback<DTOPrograma>() {
			
			@Override
			public void onSuccess(DTOPrograma result) {
				Info.display("Info", "Programa salvo com sucesso.");
				FormPrograma.this.hide();
				unmask();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				WebMessageBox.error(caught.getMessage());
				unmask();
			}
		});
		
	}
	
	private Boolean validaCampos(){
		if (tfNome.getValue() == null){
			WebMessageBox.alert("Informe o nome do programa.");
			return false;
		}else if (tfValor.getValue() == null){
			WebMessageBox.alert("Informe o valor do patrocinio!");
			return false;
		} 
		return true;
	}
	
	private DTOPrograma getDTOPrograma(){
		DTOPrograma dto = new DTOPrograma();
		dto.setId(id);
		dto.setNome(tfNome.getValue());
		dto.setValorPatrocinio(tfValor.getValue().floatValue());
		dto.setAtivo(checkHabilitado.getValue());
		dto.setProgramacao(storeProgramacao.getModels());
		return dto;
	}
	
	public void carregaForm(DTOPrograma dto){
		id = dto.getId();
		tfNome.setValue(dto.getNome());
		tfValor.setValue(dto.getValorPatrocinio());
		checkHabilitado.setValue(dto.getAtivo());
		storeProgramacao.removeAll();
		storeProgramacao.add(dto.getProgramacao());
	}
	
	private ArrayList<String> getHorasCombo(){
		ArrayList<String> horas = new ArrayList<String>();
		NumberFormat format = NumberFormat.getFormat("00");
		for (int i = 0; i <= 23; i++) {
			horas.add(format.format(i)+":00");
		}
		return horas;
	}
	
	private GridCellRenderer<DTOProgramacao> buttonRenderer = new GridCellRenderer<DTOProgramacao>() {  

		public Object render(final DTOProgramacao model, String property, ColumnData config, final int rowIndex,  
				final int colIndex, ListStore<DTOProgramacao> store, Grid<DTOProgramacao> grid) {  
			
			Button b = new Button();
			b.setIcon(AbstractImagePrototype.create(ImagensResources.INSTANCE.cancelar16()));
			b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);  
			b.setToolTip("Click para remover");
			b.addSelectionListener(new SelectionListener<ButtonEvent>() {  
				@Override  
				public void componentSelected(ButtonEvent ce) {  
					removeProgramacao(model);
				}

			});

			return b;  
		}  
	};
	
	private void removeProgramacao(DTOProgramacao dtoProgramacao) {
		storeProgramacao.remove(dtoProgramacao);
	}
	
}