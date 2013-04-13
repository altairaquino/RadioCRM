/*
 * Generated by JasperReports - 23/03/13 12:24
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;

import net.sf.jasperreports.engine.*;
import java.util.*;
import net.sf.jasperreports.engine.data.*;


/**
 *
 */
public class clientes_ativos_1364052253448_573578 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_EMPRESA = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_SUBREPORT_DIR = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_IMAGE_PATH = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillField field_CLNCGTL = null;
    private JRFillField field_CLCNOME = null;
    private JRFillField field_CLDDNCT = null;
    private JRFillField field_CLNCGTP = null;
    private JRFillField field_CLNCGCD = null;
    private JRFillField field_CLCINMU = null;
    private JRFillField field_CLCNUMR = null;
    private JRFillField field_CLCAPEL = null;
    private JRFillField field_CLCCONT = null;
    private JRFillField field_CLCRAMO = null;
    private JRFillField field_CLCTPDC = null;
    private JRFillField field_CLNCGCT = null;
    private JRFillField field_CLNCGEP = null;
    private JRFillField field_CLLENDR = null;
    private JRFillField field_CLNCODG = null;
    private JRFillField field_CLCFONE = null;
    private JRFillField field_CLCINES = null;
    private JRFillField field_CLCCELL = null;
    private JRFillField field_CLCDCTL = null;
    private JRFillField field_CLCCEP = null;
    private JRFillField field_CLCDCTP = null;
    private JRFillField field_CLCMAIL = null;
    private JRFillField field_CLCOERG = null;
    private JRFillField field_CLCDCCD = null;
    private JRFillField field_CLCCOMP = null;
    private JRFillField field_CLLATIV = null;
    private JRFillField field_CLCBAIR = null;
    private JRFillField field_CLMOBS = null;
    private JRFillField field_CLCUFRG = null;
    private JRFillField field_CLCUFCD = null;
    private JRFillField field_CLCRG = null;
    private JRFillField field_CLCDOCM = null;
    private JRFillField field_CLCMTCT = null;
    private JRFillField field_CLCSEXO = null;
    private JRFillField field_CLCNMCT = null;
    private JRFillField field_CLCNMEP = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;
    private JRFillVariable variable_ATIVOS_COUNT = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_EMPRESA = (JRFillParameter)pm.get("EMPRESA");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_SUBREPORT_DIR = (JRFillParameter)pm.get("SUBREPORT_DIR");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_IMAGE_PATH = (JRFillParameter)pm.get("IMAGE_PATH");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_CLNCGTL = (JRFillField)fm.get("CLNCGTL");
        field_CLCNOME = (JRFillField)fm.get("CLCNOME");
        field_CLDDNCT = (JRFillField)fm.get("CLDDNCT");
        field_CLNCGTP = (JRFillField)fm.get("CLNCGTP");
        field_CLNCGCD = (JRFillField)fm.get("CLNCGCD");
        field_CLCINMU = (JRFillField)fm.get("CLCINMU");
        field_CLCNUMR = (JRFillField)fm.get("CLCNUMR");
        field_CLCAPEL = (JRFillField)fm.get("CLCAPEL");
        field_CLCCONT = (JRFillField)fm.get("CLCCONT");
        field_CLCRAMO = (JRFillField)fm.get("CLCRAMO");
        field_CLCTPDC = (JRFillField)fm.get("CLCTPDC");
        field_CLNCGCT = (JRFillField)fm.get("CLNCGCT");
        field_CLNCGEP = (JRFillField)fm.get("CLNCGEP");
        field_CLLENDR = (JRFillField)fm.get("CLLENDR");
        field_CLNCODG = (JRFillField)fm.get("CLNCODG");
        field_CLCFONE = (JRFillField)fm.get("CLCFONE");
        field_CLCINES = (JRFillField)fm.get("CLCINES");
        field_CLCCELL = (JRFillField)fm.get("CLCCELL");
        field_CLCDCTL = (JRFillField)fm.get("CLCDCTL");
        field_CLCCEP = (JRFillField)fm.get("CLCCEP");
        field_CLCDCTP = (JRFillField)fm.get("CLCDCTP");
        field_CLCMAIL = (JRFillField)fm.get("CLCMAIL");
        field_CLCOERG = (JRFillField)fm.get("CLCOERG");
        field_CLCDCCD = (JRFillField)fm.get("CLCDCCD");
        field_CLCCOMP = (JRFillField)fm.get("CLCCOMP");
        field_CLLATIV = (JRFillField)fm.get("CLLATIV");
        field_CLCBAIR = (JRFillField)fm.get("CLCBAIR");
        field_CLMOBS = (JRFillField)fm.get("CLMOBS");
        field_CLCUFRG = (JRFillField)fm.get("CLCUFRG");
        field_CLCUFCD = (JRFillField)fm.get("CLCUFCD");
        field_CLCRG = (JRFillField)fm.get("CLCRG");
        field_CLCDOCM = (JRFillField)fm.get("CLCDOCM");
        field_CLCMTCT = (JRFillField)fm.get("CLCMTCT");
        field_CLCSEXO = (JRFillField)fm.get("CLCSEXO");
        field_CLCNMCT = (JRFillField)fm.get("CLCNMCT");
        field_CLCNMEP = (JRFillField)fm.get("CLCNMEP");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
        variable_ATIVOS_COUNT = (JRFillVariable)vm.get("ATIVOS_COUNT");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)("D:/workspaces/workspaceAltair/radio/imagens/");//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(".//");//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.Object)(((java.lang.String)field_CLLATIV.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)("CLIENTES "+ (((java.lang.String)field_CLLATIV.getValue()).equals("T")?"ATIVOS":"INATIVOS"));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.Object)(((java.lang.String)parameter_IMAGE_PATH.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.Object)(((java.lang.Integer)parameter_EMPRESA.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.sql.Connection)(((java.sql.Connection)parameter_REPORT_CONNECTION.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_SUBREPORT_DIR.getValue()) + "cabecalho.jasper");//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCNOME.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCTPDC.getValue())+":");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCDOCM.getValue()));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCFONE.getValue()));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCDCTL.getValue()).toUpperCase()+" "+((java.lang.String)field_CLLENDR.getValue())+", "+((java.lang.String)field_CLCNUMR.getValue())+" - "+((java.lang.String)field_CLCDCCD.getValue())+" - "+((java.lang.String)field_CLCUFCD.getValue())+" - "+((java.lang.String)field_CLCCEP.getValue())+" - "+((java.lang.String)field_CLCBAIR.getValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCNMCT.getValue()));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.String)("P�gina " + ((java.lang.Integer)variable_PAGE_NUMBER.getValue()) + " de ");//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.String)("" + ((java.lang.Integer)variable_PAGE_NUMBER.getValue())+".");//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=27$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)("D:/workspaces/workspaceAltair/radio/imagens/");//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(".//");//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.Object)(((java.lang.String)field_CLLATIV.getOldValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)("CLIENTES "+ (((java.lang.String)field_CLLATIV.getOldValue()).equals("T")?"ATIVOS":"INATIVOS"));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.Object)(((java.lang.String)parameter_IMAGE_PATH.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.Object)(((java.lang.Integer)parameter_EMPRESA.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.sql.Connection)(((java.sql.Connection)parameter_REPORT_CONNECTION.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_SUBREPORT_DIR.getValue()) + "cabecalho.jasper");//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCNOME.getOldValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCTPDC.getOldValue())+":");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCDOCM.getOldValue()));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCFONE.getOldValue()));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCDCTL.getOldValue()).toUpperCase()+" "+((java.lang.String)field_CLLENDR.getOldValue())+", "+((java.lang.String)field_CLCNUMR.getOldValue())+" - "+((java.lang.String)field_CLCDCCD.getOldValue())+" - "+((java.lang.String)field_CLCUFCD.getOldValue())+" - "+((java.lang.String)field_CLCCEP.getOldValue())+" - "+((java.lang.String)field_CLCBAIR.getOldValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCNMCT.getOldValue()));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.String)("P�gina " + ((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()) + " de ");//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.String)("" + ((java.lang.Integer)variable_PAGE_NUMBER.getOldValue())+".");//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=27$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)("D:/workspaces/workspaceAltair/radio/imagens/");//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(".//");//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.Object)(((java.lang.String)field_CLLATIV.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)("CLIENTES "+ (((java.lang.String)field_CLLATIV.getValue()).equals("T")?"ATIVOS":"INATIVOS"));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.Object)(((java.lang.String)parameter_IMAGE_PATH.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.Object)(((java.lang.Integer)parameter_EMPRESA.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.sql.Connection)(((java.sql.Connection)parameter_REPORT_CONNECTION.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_SUBREPORT_DIR.getValue()) + "cabecalho.jasper");//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCNOME.getValue()));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCTPDC.getValue())+":");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCDOCM.getValue()));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCFONE.getValue()));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCDCTL.getValue()).toUpperCase()+" "+((java.lang.String)field_CLLENDR.getValue())+", "+((java.lang.String)field_CLCNUMR.getValue())+" - "+((java.lang.String)field_CLCDCCD.getValue())+" - "+((java.lang.String)field_CLCUFCD.getValue())+" - "+((java.lang.String)field_CLCCEP.getValue())+" - "+((java.lang.String)field_CLCBAIR.getValue()));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)(((java.lang.String)field_CLCNMCT.getValue()));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.String)("P�gina " + ((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()) + " de ");//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.String)("" + ((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue())+".");//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.util.Date)(new Date());//$JR_EXPR_ID=27$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}
