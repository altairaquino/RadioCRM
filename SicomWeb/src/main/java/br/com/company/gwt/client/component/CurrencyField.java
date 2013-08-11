package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.i18n.client.NumberFormat;

public class CurrencyField extends NumberField {

    NumberFormat moneyFormat = NumberFormat.getFormat("R$ #,##0.00;(R$ #,##0.00)");

    NumberFormat numberFormat = NumberFormat.getDecimalFormat();

    public CurrencyField() {
        setFormat(moneyFormat);
        String formatchars = moneyFormat.format(10000.0);
        String basechars = "1234567890";
        for (int i = 0; i < formatchars.length(); i++) {
            if (!Character.isDigit(formatchars.charAt(i))) {
                basechars += formatchars.charAt(i);
            }
        }
        setBaseChars(basechars);
    }

    @Override
    protected void onBlur(ComponentEvent arg0) {
        if (getRawValue() != null && !getRawValue().equals("")) {
            Double rawvalue = 0d;
            try {
                rawvalue = moneyFormat.parse(getRawValue());
            } catch (NumberFormatException e) {
                try {
                    rawvalue = numberFormat.parse(getRawValue());
                } catch (NumberFormatException e2) {
                    rawvalue = null;
                }
            }
            if (rawvalue != null)
                setRawValue(moneyFormat.format(rawvalue));
            else throw new NumberFormatException();
        }
        super.onBlur(arg0);
    }

}