package br.com.company.gwt.client.component;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.event.dom.client.KeyCodes;

public class TextFieldMask<D>{

	private final String mask;

	private int len;
	
	private TextField<D> textField;

	private String placeHolder = "_";
	private int partialPosition;
	private String[] buffer;
	private boolean ignore;
	private String focusText;
	private String[] tests;

	private Integer firstNonMaskPos;
	private int cursorBegin = -1;

	private int cursorEnd = -1;

	private static final Map<String, String> defs;

	static {

		defs = new HashMap<String, String>();
		defs.put("9", "[0-9]");
		defs.put("a", "[A-Za-z]");
		defs.put("*", "[A-Za-z0-9]");

	}

	private static boolean cotainDef(String key) {
		return (defs.get(key) != null);
	}

	private static String getDef(String key) {
		return defs.get(key);
	}

	public TextFieldMask(TextField<D> textField, String mask) {
		this.mask = mask;
		this.textField = textField;
		createListeners();
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

	private void buffer() {

		String[] aux = split(mask);

		buffer = new String[aux.length];

		for (int i = 0; i < aux.length; i++) {

			if (cotainDef(aux[i])) {
				buffer[i] = placeHolder;
			} else {
				buffer[i] = aux[i];
			}

		}

	}

	private int checkVal(boolean allow) {
		D value = textField.getValue();
		String test = "";
		
		if (value != null) {
			test = textField.getRawValue();
		}

		int lastMatch = -1;

		int a = 0;

		for (int i = 0, pos = 0; i < len; i++) {

			if (tests[i] != null) {

				buffer[i] = placeHolder;

				while (pos++ < test.length()) {

					String c = String.valueOf(test.charAt(pos - 1));

					if (c.matches(tests[i])) {

						buffer[i] = String.valueOf(c);
						lastMatch = i;
						break;
					}
				}
				if (pos > test.length()) {
					break;
				}
			} else if (i != partialPosition) {

				try {

					char d = test.charAt(pos);

					if (buffer[i].equals(String.valueOf(d))) {

						pos++;
						lastMatch = i;
					}

				} catch (Exception e) {
					continue;
				}
			}

			a = i;
		}

		if (!allow && lastMatch + 1 < partialPosition) {
			
			textField.setRawValue("");
			clearBuffer(0, len);

		} else if (allow || lastMatch + 1 >= partialPosition) {

			writeBuffer();

			if (!allow) {

				if (textField.getRawValue() != null) {
					textField.setRawValue(textField.getRawValue().substring(0, lastMatch + 1));
				}
			}
		}

		return a;

	}

	private void clearBuffer(int start, int end) {

		for (int i = start; i < end && i < len; i++) {
			if (tests[i] != null) {
				buffer[i] = placeHolder;
			}
		}

	}

	private void each() {

		for (int i = 0; i < tests.length; i++) {

			String c = tests[i];

			if (c == "?") {

				len--;
				partialPosition = i;

			} else if (cotainDef(c)) {

				tests[i] = getDef(c);

				if (firstNonMaskPos == null) {
					firstNonMaskPos = tests.length - 1;
				}

			} else {
				tests[i] = null;
			}

		}

	}

	private void maskField() {
		tests = new String[] {};
		partialPosition = mask.length();
		firstNonMaskPos = null;
		len = mask.length();

		tests = split(mask);

		each();
		buffer();

		ignore = false;

		focusText = "";

		if (textField.getValue() != null) {
			focusText = textField.getRawValue();
		}

		if (!textField.isReadOnly()) {

			checkVal(false);
		}

	}
	
	private Listener<ComponentEvent> listener = new Listener<ComponentEvent>() {
		
		@Override
		public void handleEvent(ComponentEvent be) {
			EventType type = be.getType();
			if (Events.Render == type) {
				onRender(be);
			} else if(Events.OnBlur == type){
				onBlur(be);
			}else if (Events.OnFocus == type) {
				onFocus(be);
			} if (Events.OnKeyDown == type) {
				onKeyDown((FieldEvent)be);
			} else if (Events.OnKeyPress == type) {
				onKeyPress((FieldEvent)be);
			}
		}
	};
	
	private void createListeners() {
		textField.addListener(Events.Render, listener);
		textField.addListener(Events.OnBlur, listener);
		textField.addListener(Events.OnFocus, listener);
		textField.addListener(Events.OnKeyDown, listener);
		textField.addListener(Events.OnKeyPress, listener);
	}
	
	public void removerListeners() {
		textField.removeListener(Events.Render, listener);
		textField.removeListener(Events.OnBlur, listener);
		textField.removeListener(Events.OnFocus, listener);
		textField.removeListener(Events.OnKeyDown, listener);
		textField.removeListener(Events.OnKeyPress, listener);
	}
	
	protected void onBlur(ComponentEvent be) {
		checkVal(false);
	}	
	protected void onFocus(ComponentEvent be) {

		focusText = "";

		if (textField.getValue() != null) {
			focusText = textField.getRawValue();
		}

		int pos = checkVal(false);
		writeBuffer();

		if (pos == mask.length()) {
			cursorBegin = 0;
			cursorEnd = pos;

			textField.select(0, pos);
		} else {

			cursorBegin = pos;
			cursorEnd = pos;

			textField.setCursorPos(pos);
		}
	}
	
	protected boolean getIgnoreKeyOnKeyDown(int key){
		return key < 16 || key > 16 && key < 32 || key > 32 && key < 41;
	}

	protected void onKeyDown(FieldEvent fe) {
		
		int k = fe.getKeyCode();

		ignore = getIgnoreKeyOnKeyDown(k);

		// delete selection before proceeding
		if (cursorBegin - cursorEnd != 0 && (!ignore || k == KeyCodes.KEY_BACKSPACE || k == KeyCodes.KEY_DELETE)) {
			clearBuffer(cursorBegin, cursorEnd);
		}

		// backspace, delete, and escape get special treatment
		if (k == KeyCodes.KEY_BACKSPACE || k == KeyCodes.KEY_DELETE) {

			shiftL(textField.getCursorPos() + (k == KeyCodes.KEY_DELETE ? 0 : -1));
			fe.stopEvent();
		} else if (k == KeyCodes.KEY_ESCAPE) {// escape
			textField.setRawValue(focusText);
			fe.stopEvent();
		}
	}
	
	protected boolean notIgnoreKeyOnKeyPress(int key){
		return (key >= 32 && key <= 125 || key > 186);
	}

	protected void onKeyPress(FieldEvent fe) {

		int k = fe.getKeyCode();

		if (ignore) {
			// Fixes Mac FF bug on backspace

			if (k == KeyCodes.KEY_BACKSPACE) {
				fe.stopEvent();
			}

			return;
		}

		if (fe.isControlKey() || fe.isAltKey()) {// Ignore

			fe.stopEvent();

		} else if (notIgnoreKeyOnKeyPress(k)) {// typeable characters

			int p = seekNext(textField.getCursorPos() - 1);

			if (p < len) {

				String c = String.valueOf((char) fe.getKeyCode());

				if (c.matches(tests[p])) {

					shiftR(p);
					buffer[p] = c;
					writeBuffer();
					int next = seekNext(p);

					textField.setCursorPos(next);

					cursorBegin = next;
					cursorEnd = next;
				}
			}
		}

		fe.stopEvent();
	}

	protected void onRender(ComponentEvent be) {
		maskField();
	}

	private int seekNext(int index) {

		while (++index <= len) {

			try {
				if (tests[index] != null) {
					break;
				}
			} catch (Exception e) {
				break;
			}
		}

		return index;

	}

	private void shiftL(int index) {

		for (int i = index; i >= 0; i--) {

			if (tests[i] != null) {

				index = i;
				break;
			}
		}

		for (int i = index; i < len; i++) {

			if (tests[i] != null) {

				buffer[i] = placeHolder;

				int j = seekNext(i);

				if (j < len && buffer[j].matches(tests[i])) {
					buffer[i] = buffer[j];
				} else {
					break;
				}
			}
		}

		writeBuffer();

		textField.setCursorPos(index);

	}

	private void shiftR(int index) {

		String c = placeHolder;

		for (int i = index; i < len; i++) {

			if (tests[i] != null) {

				int j = seekNext(i);
				String t = buffer[i];
				buffer[i] = c;
				if (j < len && t.matches(tests[j])) {
					c = t;
				} else {
					break;
				}
			}
		}

	}

	private String[] split(String text) {

		int length = text.length();

		String[] array = new String[length];

		for (int i = 0; i < length; i++) {
			array[i] = String.valueOf(text.charAt(i));
		}

		return array;
	}

	private void writeBuffer() {

		String valueAux = "";

		for (String element2 : buffer) {

			valueAux += element2;
		}

		textField.setRawValue(valueAux);

	}

}

