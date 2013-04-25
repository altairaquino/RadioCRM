package br.com.company.gwt.client.component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.SortInfo;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BaseObservable;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Layout;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class WebUtil {

	public enum FieldType{
		DATE, DATE2, COMBOBOX, SIMPLECOMBOBOX
	}
	
	public static ColumnConfig createColumnEditorComboBox(String columnName, String columnLabel, String valueField, String displayField) {
		ColumnConfig column = new ColumnConfig();
		column.setId(columnName);
		column.setHeader(columnLabel);
		column.setEditor(new CellEditor(createComboBox(columnName, columnLabel,	valueField, displayField)));
		column.setWidth(200);
		column.setRenderer(new GridCellRendererProperty(columnName,	displayField));
		return column;
	}

	public static ComboBox<BaseModelData> createComboBox(String columnName, String columnLabel, String valueField, String displayField) {
		return createComboBox(BaseModelData.class, columnName, columnLabel, valueField, displayField);
	}

	public static <T extends BaseModelData> ComboBox<T> createComboBox(Class<T> class1, String columnName, String columnLabel, String valueField, String displayField) {
		ComboBox<T> editor = new ComboBox<T>();
		setComboBox(editor, columnName, columnLabel, valueField, displayField);
		return editor;
	}

	public static<T extends BaseModelData> void setComboBox(ComboBox<T> comboBox, String columnName, String columnLabel, String valueField, String displayField) {
		comboBox.setName(columnName);
		comboBox.setEditable(false);
		comboBox.setTypeAhead(true);
		comboBox.setTriggerAction(TriggerAction.ALL);
		comboBox.setValueField(valueField);
		comboBox.setDisplayField(displayField);
		comboBox.setStore(new ListStore<T>());
		comboBox.setFieldLabel(columnLabel);
	}

	public static <T> SimpleComboBox<T> createSimpleComboBox(ConfField conf) {
		return createSimpleComboBox(conf.getName(), conf.getLabel(), conf.getValueField(), conf.getDisplayField());
	}

	public static <T extends Enum<T>> SimpleComboBox<T> createSimpleComboBox(ConfField conf, List<T> list) {
		SimpleComboBox<T> editor = createSimpleComboBox(conf);
		if(list != null && !list.isEmpty()){
			for (T t : list) {
				editor.add(t);
			}
			String className = null;
			if(!conf.getValueField().equals(conf.getDisplayField())){
				for(SimpleComboValue<T> value : editor.getStore().getModels()){
					T t = value.getValue();
					if(className == null){
						className = t.getClass().getName() + ".";
					}
					value.set(conf.getDisplayField(), Registry.get(className + t.name()));
				}
			}
		}
		return editor;
	}

	public static <T extends Enum<T>> SimpleComboBox<T> createSimpleComboBox(ConfField conf, T... array) {
		return createSimpleComboBox(conf, Arrays.asList(array));
	}

	public static <T extends Enum<T>> SimpleComboBox<T> createSimpleComboBox(ConfField conf, boolean converter ,T... array) {
		SimpleComboBox<T> editor = createSimpleComboBox(conf, Arrays.asList(array));
		if(converter){
			editor.setData("converter", new SimpleComboBoxConverter<T>(editor));
		}
		return editor;
	}

	public static <T> SimpleComboBox<T> createSimpleComboBox(String columnName, String columnLabel, String valueField, String displayField) {
		SimpleComboBox<T> editor = new SimpleComboBox<T>();
		editor.setName(columnName);
		editor.setEditable(false);
		editor.setTypeAhead(true);
		editor.setTriggerAction(TriggerAction.ALL);
		if(valueField != null){
			editor.setValueField(valueField);
		}
		if(displayField != null){
			editor.setDisplayField(displayField);
		}
		editor.setFieldLabel(columnLabel);
		return editor;
	}

	public static RadioGroup createRadioGroup(String columnName, String columnLabel, String valueField, String displayField, boolean readOnly) {
		RadioGroup editor = new RadioGroup(columnName);
		editor.setName(columnName);
		editor.setFieldLabel(columnLabel);
		editor.setReadOnly(readOnly);
		return editor;
	}
	public static RadioGroup createRadioGroup(ConfField conf, boolean readOnly) {
		return createRadioGroup(conf.getName(), conf.getLabel(), conf.getValueField(), conf.getDisplayField(), readOnly);
	}
	public static RadioGroup createRadioGroup(ConfField conf) {
		return createRadioGroup(conf, false);
	}

	public static <T extends Enum<?>> RadioGroup createRadioGroup(Class<T> clazz, ConfField conf, boolean readOnly, List<T> list) {
		RadioGroup editor = createRadioGroup(conf, readOnly);
		String className = clazz.getName() + ".";
		for (T t : list) {
			Radio radio = new Radio();
			radio.setData(editor.getName(), t.name());
			editor.add(radio);
			String boxLabel = Registry.get(className + t.name());
			if(boxLabel != null){
				radio.setBoxLabel(boxLabel);
			}
		}
		return editor;
	}
	public static <T extends Enum<?>> RadioGroup createRadioGroup(Class<T> clazz, ConfField conf, List<T> list) {
		return createRadioGroup(clazz, conf, false, list);
	}

	public static <T extends Enum<T>> RadioGroup createRadioGroup(Class<T> clazz, ConfField conf, T[] array) {
		RadioGroup radioGroup = createRadioGroup(clazz, conf, Arrays.asList(array)); 
		radioGroup.setData("converter", new RadioGroupConverter<T>(clazz, radioGroup));
		return radioGroup;
	}

	public static ColumnConfig createColumnEditor(String columnName, String columnLabel) {
		return createColumnEditor(columnName, columnLabel, 200);
	}

	public static ColumnConfig createColumnEditor(ConfField confField) {
		return createColumnEditor(confField.getName(), confField.getLabel(), 200);
	}

	public static ColumnConfig createColumnEditor(ConfField confField,	int columnWidth) {
		return createColumnEditor(confField.getName(), confField.getLabel(), columnWidth);
	}

	public static ColumnConfig createColumnEditorCheckBox(ConfField confField,	int columnWidth) {
		return createColumnEditorCheckBox(confField.getName(), confField.getLabel(), columnWidth);
	}

	public static ColumnConfig createColumnEditor(String columnName, String columnLabel, int columnWidth) {
		ColumnConfig column = createColumn(columnName, columnLabel,	columnWidth);
		TextField<String> field = new TextField<String>();
		field.setName(columnName);
		column.setEditor(new CellEditor(field));
		return column;
	}

	public static ColumnConfig createColumnEditorCheckBox(String columnName, String columnLabel, int columnWidth) {
		ColumnConfig column = createCheckColumn(columnName, columnLabel, columnWidth);
		column.setEditor(new CellEditor(new CheckBox()));
		return column;
	}

	public static ColumnConfig createColumnEditor(Field<? extends Object> field, ConfField confField) {
		return createColumnEditor(field, confField, 200);
	}

	public static ColumnConfig createColumnEditor(FieldType fieldType , ConfField confField, int columnWidth) {
		return createColumnEditor(fieldType , confField, columnWidth, null);
	}

	public static ColumnConfig createColumnEditor(FieldType fieldType , ConfField confField, String format) {
		return createColumnEditor(fieldType , confField, 200,format);
	}

	public static ColumnConfig createColumnEditor(FieldType fieldType , ConfField confField) {
		return createColumnEditor(fieldType , confField, 200);
	}

	public static ColumnConfig createColumnEditor(FieldType fieldType , ConfField confField, int columnWidth, String format) {
		Field<? extends Object> field = null;
		switch (fieldType) {
		case DATE:
			field= createDateField2(confField, format);
			break;
		case DATE2:
			field= createDateField(confField, format);
			break;
		case COMBOBOX:
			field= createComboBox(confField);
			break;
		case SIMPLECOMBOBOX:
			field= createSimpleComboBox(confField);
			break;
		}
		return createColumnEditor(field, confField, columnWidth);

	}
	public static ColumnConfig createColumnEditor(Field<? extends Object> field, ConfField confField, int columnWidth) {
		return createColumnEditor(field, confField.getName(), confField.getLabel(), columnWidth);
	}

	public static ColumnConfig createColumnEditor(Field<? extends Object> field, String columnName, String columnLabel, int columnWidth) {
		ColumnConfig column = createColumn(columnName, columnLabel,	columnWidth);
		if(field != null){
			column.setEditor(new CellEditor(field));
		}
		return column;
	}

	public static TextField<String> createTextField(ConfField confField) {
		return createTextField(confField, false);
	}

	public static TextField<String> createPasswordField(ConfField confField) {
		TextField<String> password = createTextField(confField, false);
		password.setPassword(true);
		return password;
	}

	public static TextField<String> createTextField(ConfField confField, boolean readOnly) {
		return createTextField(String.class, confField, readOnly);
	}

	public static <T> TextField<T> createTextField(Class<T> class1, ConfField confField) {
		return createTextField(class1, confField, false);
	}
	public static <T extends Number> NumberField createNumberField(ConfField confField) {
		return createNumberField(null, confField, false);
	}

	public static <T extends Number> NumberField createNumberField(Class<T> class1, ConfField confField) {
		return createNumberField(class1, confField, false);
	}

	public static <T extends Number> NumberField createNumberField(Class<T> class1, ConfField confField, boolean readOnly) {
		NumberField numberField = new NumberField();
		numberField.setName(confField.getName());
		numberField.setFieldLabel(confField.getLabel());
		if(class1 != null){
			numberField.getPropertyEditor().setType(class1);
		}
		numberField.setReadOnly(readOnly);
		return numberField;
	}

	public static <T> TextField<T> createTextField(Class<T> class1, ConfField confField, boolean readOnly) {
		TextField<T> textField = new TextField<T>();
		textField.setName(confField.getName());
		textField.setFieldLabel(confField.getLabel());
		textField.setReadOnly(readOnly);
		return textField;
	}

	public static TextArea createTextArea(ConfField confField) {
		return createTextArea(confField, false);
	}

	public static TextArea createTextArea(ConfField confField, boolean readOnly) {
		TextArea textArea = new TextArea();
		textArea.setName(confField.getName());
		textArea.setFieldLabel(confField.getLabel());
		textArea.setReadOnly(readOnly);
		return textArea;
	}

	public static CheckBox createCheckBox(ConfField confField) {
		return createCheckBox(confField.getName(), confField.getLabel(),confField.getLabel());
	}

	public static CheckBox createCheckBoxForm(ConfField confField) {
		return createCheckBoxForm(confField.getName(), confField.getLabel());
	}

	public static CheckBox createCheckBox(String columnName, String columnLabel) {
		CheckBox checkBox = new CheckBox();
		checkBox.setName(columnName);
		checkBox.setBoxLabel(columnLabel);
		return checkBox;
	}

	public static CheckBox createCheckBoxForm(String columnName, String columnLabel) {
		CheckBox checkBox = new CheckBox();
		checkBox.setFieldLabel(columnLabel);
		checkBox.setName(columnName);
		return checkBox;
	}

	public static CheckBox createCheckBox(String columnName, String columnLabel, String fieldLabel) {
		CheckBox checkBox = new CheckBox();
		checkBox.setName(columnName);
		checkBox.setFieldLabel(columnLabel);
		checkBox.setBoxLabel(columnLabel);
		return checkBox;
	}

	public static DateField createDateField(ConfField confField) {
		return createDateField(confField, null);
	}

	public static DateField createDateField(ConfField confField, boolean readOnly, boolean editable) {
		return createDateField(confField, null, readOnly);
	}

	public static DateField createDateField(ConfField confField, boolean readOnly) {
		return createDateField(confField, null, readOnly);
	}

	public static DateField createDateField(ConfField confField, String format) {
		return createDateField(confField, format, false);
	}

	public static DateField createDateField(ConfField confField, String format, boolean readOnly) {
		return createDateField(confField,format,readOnly,true);
	}

	public static DateField createDateField(ConfField confField, String format, boolean readOnly, boolean editable) {
		DateField textField = new DateField();
		textField.setName(confField.getName());
		textField.setFieldLabel(confField.getLabel());
		if (format != null) {
			textField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(format));
		}
		textField.setReadOnly(readOnly);
		textField.setEditable(editable);
		textField.setEnabled(editable);
		textField.getDatePicker().setEnabled(editable);
		return textField;
	}

	public static TextField<Date> createDateField2(ConfField confField, String format) {
		return createDateField2(confField, format, false);
	}

	public static TextField<Date> createDateField2(ConfField confField, String format, String mask) {
		TextField<Date> textField = createDateField2(confField, format, false);
		new TextFieldMask<Date>(textField, mask);
		return textField;
	}

	public static TextField<Date> createDateField2(ConfField confField, String format, boolean readOnly) {
		TextField<Date> textField = createTextField(Date.class, confField,	readOnly);
		if (format != null) {
			textField.setPropertyEditor(new DateTimePropertyEditor(format));
		}
		return textField;
	}

	//	public static TextField createTextFieldMask() {
	//		
	//		return null;
	//	}

	public static ColumnConfig createColumn(ConfField confField, int columnWidth) {
		return createColumn(confField.getName(), confField.getLabel(), columnWidth);
	}

	public static ColumnConfig createColumn(String columnName, String columnLabel,int columnWidth) {
		ColumnConfig column = new ColumnConfig();
		column.setId(columnName);
		column.setHeader(columnLabel);
		column.setWidth(columnWidth);
		return column;
	}

	public static ColumnConfig createCheckColumn(String columnName, String columnLabel,int columnWidth) {
		ColumnConfig column = new CheckColumnConfig();
		column.setId(columnName);
		column.setHeader(columnLabel);
		column.setWidth(columnWidth);
		return column;
	}

	public static <T> ColumnConfig createColumnEditor(Class<T> class0,	String columnName, String columnLabel, int columnWidth) {
		ColumnConfig column = new ColumnConfig();
		TextField<T> field = new TextField<T>();
		field.setName(columnName);
		column.setId(columnName);
		column.setHeader(columnLabel);
		column.setEditor(new CellEditor(field));
		column.setWidth(columnWidth);
		return column;
	}

	public static ColumnConfig createColumnEditorNumeric(ConfField conf, int columnWidth) {
		return createColumnEditorNumeric(conf.getName(), conf.getLabel(), columnWidth);
	}
	public static ColumnConfig createColumnEditorNumeric(String columnName, String columnLabel, int columnWidth) {
		return createColumnEditorNumeric(Integer.class, columnName,	columnLabel, columnWidth);
	}

	public static <T> ColumnConfig createColumnEditorNumeric(Class<? extends Number> class1, String columnName, String columnLabel, int columnWidth) {
		ColumnConfig column = new ColumnConfig();
		NumberField field = new NumberField();
		field.setName(columnName);
		field.setPropertyEditorType(class1);
		column.setId(columnName);
		column.setHeader(columnLabel);
		column.setEditor(new CellEditor(field));
		column.setWidth(columnWidth);
		return column;
	}

	public static <T> ColumnConfig createColumnNumeric(Class<? extends Number> class1, String columnName, String columnLabel, int columnWidth) {
		ColumnConfig column = new ColumnConfig();
		NumberField field = new NumberField();
		field.setName(columnName);
		field.setPropertyEditorType(class1);
		column.setId(columnName);

		column.setNumberFormat(field.getPropertyEditor().getFormat());
		column.setHeader(columnLabel);
		column.setEditor(new CellEditor(field));
		column.setWidth(columnWidth);
		return column;
	}

	public static ColumnConfig createColumnEditor(String columnName, String columnLabel, int columnWidth, GridCellRenderer<BaseModelData> renderer) {
		ColumnConfig column = new ColumnConfig();
		TextField<String> field = new TextField<String>();
		field.setName(columnName);
		column.setId(columnName);
		column.setHeader(columnLabel);
		column.setEditor(new CellEditor(field));
		column.setWidth(columnWidth);
		column.setRenderer(renderer);
		return column;
	}

//	public static ColumnConfig createColumnEditorComboBox(ConfField conf) {
//		return createColumnEditorComboBox(conf.getName(), conf.getLabel(), conf.getValueField(), conf.getDisplayField());
//	}

	public static ComboBox<BaseModelData> createComboBox(ConfField conf) {
		return createComboBox(conf.getName(), conf.getLabel(), conf.getValueField(), conf.getDisplayField());
	}

	public static  <T extends BaseModelData> ComboBox<T> createComboBox(Class<T> class1, ConfField conf) {
		return createComboBox(class1, conf.getName(), conf.getLabel(), conf.getValueField(), conf.getDisplayField());
	}

	public static FormPanel createFormPanel(String heading, int labelWidth,int defaultWidth) {
		FormPanel panel = new FormPanel();
		setFormPanel(panel, heading, labelWidth, defaultWidth);
		return panel;
	}

	public static FormPanel createFormPanel(int labelWidth,int defaultWidth) {
		return createFormPanel(null, labelWidth, defaultWidth);
	}

	public static void setFormPanel(FormPanel panel, int labelWidth,int defaultWidth) {
		setFormPanel(panel, null, labelWidth, defaultWidth);
	}

	public static void setFormPanel(FormPanel panel, String heading, int labelWidth,int defaultWidth) {
		FormLayout layout = new FormLayout();
		layout.setLabelWidth(labelWidth);
		layout.setDefaultWidth(defaultWidth);
		layout.setLabelAlign(LabelAlign.RIGHT);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setLayout(layout);
		if(heading != null){
			panel.setHeading(heading);
		}else{
			panel.setHeaderVisible(false);
		}
		panel.setBorders(false);
	}

	public static void addListener(BaseObservable observable, Listener<? extends BaseEvent> listener, EventType ...eventType) {
		for (int i = 0; i < eventType.length; i++) {
			observable.addListener(eventType[i], listener);
		}
	}

	public static void removeListener(BaseObservable observable, Listener<? extends BaseEvent> listener, EventType ...eventType) {
		for (int i = 0; i < eventType.length; i++) {
			observable.removeListener(eventType[i], listener);
		}
	}

	public static boolean containsListener(BaseObservable observable, Listener<? extends BaseEvent> listener, EventType eventType) {
		return observable != null && observable.getListeners(eventType).contains(listener);
	}

	public static boolean containsListener(Component observable, Listener<? extends BaseEvent> listener, EventType eventType) {
		return observable != null && observable.getListeners(eventType).contains(listener);
	}

	public static void setWindow(Window window, Layout layout, int width, int height) {
		window.setSize(width, height);
		window.setLayout(layout);
		window.setResizable(true);
		window.setMaximizable(true);
		window.setMinimizable(true);
		window.setModal(false);
		window.setClosable(true);
	}

	public static<T extends BaseModelData> Map<String, Object> unionProperties(List<T> list){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!list.isEmpty()){
			for (T t : list) {
				setProperties(t.getProperties(), map);
			}
		}
		return map;
	}

	public static void setProperties(Map<String, Object> source, Map<String, Object> target) {
		for (String property : source.keySet()) {
			if(!target.containsKey(property)){
				target.put(property, source.get(property));
			}
		}
	}

	public static <T extends ModelData> int compare(Store<T> store, T m1, T m2, String property) {
		StoreSorter<T> storeSorter = new StoreSorter<T>();
		return storeSorter.compare(store, m1, m2, property);
	}

	public static boolean sortDirIsDesc(SortInfo sortInfo) {
		boolean sortDir = true;
		if (sortInfo != null && sortInfo.getSortField() != null) {
			sortDir = !(sortInfo.getSortDir() == Style.SortDir.DESC);
		}
		return sortDir;
	}

	public static boolean sortDirIsDesc(PagingLoadConfig config) {
		return sortDirIsDesc(config.getSortInfo());
	}


	public static void setPanelWithoutBorder(ContentPanel panel, Layout layout) {
		panel.addListener(Events.Render, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				((ContentPanel)be.getSource()).	getBody().setBorders(false);
			}
		});
		panel.setHeaderVisible(false);
		panel.setBorders(false);
		if(layout != null){
			panel.setLayout(layout);
		}
	}

	public static void setPanelWithoutBorder(ContentPanel panel) {
		setPanelWithoutBorder(panel, null);
	}

	public static ContentPanel createPanelWithoutBorder(Layout layout) {
		ContentPanel panel = new ContentPanel();
		setPanelWithoutBorder(panel, layout);
		return panel;
	}

	public static ContentPanel createPanelWithoutBorder() {
		return createPanelWithoutBorder(null);
	}

	public static MenuItem createMenuItem(String text, String icon){
		return new MenuItem(text, IconHelper.create(icon));
	}

	public static MenuItem createMenuItem(String text, String icon, String id){
		MenuItem menuItem = createMenuItem(text, icon);
		menuItem.setId(id);
		return menuItem;
	}

//	public static void setWindowModal(Window window, String title, Layout layout, int width, int height) {
//		setWindowModal(window, title, layout, width, height);
//	}
//
//	public static void setWindowModal(Window window, String title, Layout layout, int width, int height) {
//		setWindowModal(window, title, layout, width, height);
//	}

	public static void setWindowModal(Window window, String title, AbstractImagePrototype icon, Layout layout, int width, int height) {
		window.setHeading(title);
		window.setIcon(icon);

		window.setLayout(layout);
		window.setSize(width, height);

		window.setResizable(false);
		window.setMaximizable(false);
		window.setMinimizable(true);
		window.setModal(true);
		window.setClosable(true);

	}

	public static MenuItem createMenuItem(String title, String icon, Window window){
		MenuItem item = new MenuItem(title);
		item.setIconStyle(icon);
		return item;
	}

	public static MenuItem createMenuItem(String title, String icon, Window window, String id){
		MenuItem item = new MenuItem(title);
		item.setIconStyle(icon);
		item.setId(id);
		item.setData("window", window);
		return item;
	}

	public static Shortcut createShortcutWithContextMenu(String title, String icon, Menu menu){
		Shortcut scut = createShortcut(title,icon);
		scut.setContextMenu(menu);
		return scut;
	}
	
	public static Shortcut createShortcut(String title, String icon){
		Shortcut scut = new Shortcut();
		scut.setText(title);
		scut.setId(icon);
		return scut;
	}
	
	public static Shortcut createShortcut(String title, String icon, ComponentProvider<? extends Window> provider){
		Shortcut scut = new Shortcut();
		scut.setText(title);
		scut.setId(icon);
		scut.setData("provider", provider);
		return scut;
	}
	
	public static Shortcut createShortcut(String title, AbstractImagePrototype icon, ComponentProvider<? extends Window> provider){
		Shortcut scut = new Shortcut();
		scut.setText(title);
		scut.setIcon(icon);
		scut.setData("provider", provider);
		return scut;
	}
	
	public static void copyProperties(ModelData dest, ModelData orig, String ... properties){
		for (int i = 0; i < properties.length; i++) {
			dest.set(properties[i], orig.get(properties[i]));
		}
	}

	public static void removeProperties(ModelData model, String ... properties){
		for (int i = 0; i < properties.length; i++) {
			model.remove(properties[i]);
		}
	}

	public static void removePropertiesExceto(ModelData model, String ... properties){
		Set<String> removes = model.getProperties().keySet();
		removes.removeAll(Arrays.asList(properties));
		removeProperties(model, removes.toArray(new String[removes.size()]));
	}

	public static void copyProperties(ModelData dest, Record orig, String ... properties){
		for (int i = 0; i < properties.length; i++) {
			dest.set(properties[i], orig.get(properties[i]));
		}
	}

	public static void copyProperties(Record dest, ModelData orig, String ... properties){
		for (int i = 0; i < properties.length; i++) {
			dest.set(properties[i], orig.get(properties[i]));
		}
	}

	public static void copyProperties(Record dest, Record orig, String ... properties){
		for (int i = 0; i < properties.length; i++) {
			dest.set(properties[i], orig.get(properties[i]));
		}
	}

	public native static void reloadPage() /*-{
	$wnd.location.href=$wnd.location.href;
	}-*/;

	public native static String getHref() /*-{
	return $wnd.location.href;
	}-*/;
	public native static void goUrl(String url) /*-{
	$wnd.location.href = url;
	}-*/;

	public static String setLocale(String url, String locale){
		String key = "locale";
		int indexQ = url.indexOf("?");
		String result = "";
		if(indexQ != -1){
			int indexLocale = url.indexOf(key);
			if(indexLocale != -1){
				String s = url.substring(indexLocale).split("&")[0];
				result = url.replace(s, key + "=" + locale);
			}else{
				result = url.substring(0, indexQ + 1) + (key + "=" + locale);
				if(url.length() > indexQ + 1){
					result += "&" + url.substring(indexQ + 1);
				}
			}
		}else{
			result = url + "?" + key + "=" + locale;
		}
		return result;
	}

	public static boolean equalWithNull(ModelData obj1, Record obj2, String property) {
		if (obj1 == obj2) {
			return true;
		} else if (obj1 == null || obj2 == null) {
			return false;
		} else {
			return Util.equalWithNull(obj1.get(property), obj2.get(property));
		}
	}

	public static boolean equalWithNull(ModelData obj1, ModelData obj2, String property) {
		if (obj1 == obj2) {
			return true;
		} else if (obj1 == null){
			return true;
		} else if (obj2 == null) {
			return false;
		} else {
			return Util.equalWithNull(obj1.get(property), obj2.get(property));
		}
	}


	public static Object getOriginalValue(Record record, String property){
		Object object = null;
		if(record != null){
			ModelData data = record.getModel();
			Map<String, Object> map = record.getChanges();
			if(map.containsKey(property)){
				object = map.get(property);
			}else if(data != null){
				object = data.get(property);
			}
		}
		return object;
	}

	public static BaseModelData getOriginalValues(Record record, String ... properties){
		BaseModelData modelData = new BaseModelData();
		for (int i = 0; i < properties.length; i++) {
			String string = properties[i];
			modelData.set(string, getOriginalValue(record, string));

		}
		return modelData;
	}

	public static void reject(Record record, String property){
		if(record != null){
			Map<String, Object> map = record.getChanges();
			if(map.containsKey(property)){
				record.set(property, map.get(property));
			}
		}
	}

	public static final SummaryType<Double> AVG_POND = new SummaryType<Double>() {
		@Override
		public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
			if (v == null) {
				v = 0d;
			}
			Object obj = m.get(field);
			if (obj != null) {
				return ((Double) v) + ((Number) obj).doubleValue();
			}
			return ((Double) v);
		}
	};

	public static void commitValueRecord(Record record, String property, Boolean selected, boolean edit) {
		if(edit){
			record.beginEdit();
		}
		record.getModel().set(property, selected);
		record.set(property, null);
		record.set(property, selected);
		if(edit){
			record.endEdit();
		}
	}
	
	public static boolean fieldNuloOuVazio(TextField<String> field, String mensagem){
		boolean invalido = field.getValue() == null || field.getValue().equals("");
		if (invalido){
			WebMessageBox.alert(mensagem);
			field.focus();
		}
		return invalido;
	}
	public static boolean fieldNuloOuVazio(NumberField field, String mensagem){
		boolean invalido = field.getValue() == null || field.getValue().equals("");
		if (invalido){
			WebMessageBox.alert(mensagem);
			field.focus();
		}
		return invalido;
	}

}