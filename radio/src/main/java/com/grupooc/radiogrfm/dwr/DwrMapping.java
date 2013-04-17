package com.grupooc.radiogrfm.dwr;

import java.util.Iterator;
import java.util.List;

import com.grupooc.radiogrfm.dao.ModelCidade;
import com.grupooc.radiogrfm.struts.bean.BeanCidade;



public class DwrMapping {
	
	public static String getCidades(String UF, String campo){
		String ret = "<select name=\""+campo+"\" style=\"width: 375px\">\n";
		try {
			
			List<BeanCidade> list = ModelCidade.getInstance().getCidadesDoEstado(UF);
			Iterator<BeanCidade> iter = list.iterator();
			
			while (iter.hasNext()){
				BeanCidade cidade = iter.next();
				ret += "<option value=\""+cidade.getCdncodg()+"\">"+cidade.getCdcdesc()+"</option>\n";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		ret += "</select>\n";
		
		return ret;
	}
	
	
}
