package com.grupooc.radiogrfm.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupooc.radiogrfm.dao.ModelMidia;
import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.struts.bean.BeanMidia;
import com.grupooc.radiogrfm.struts.form.FormMidia;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionMidia extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {			
			request.setAttribute("ls_midia", ModelMidia.getInstance().getMidias(
					Integer.parseInt(beanEmpresa.getEpncodg())));
			fw.setPath("/midiaLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			
			request.getSession().removeAttribute("formMidia");
			
			fw.setPath("/midiaNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMidia formMidia = (FormMidia)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaString(formMidia.getMdcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Descri��o � obrigat�ria."));
		}		
								
		if (!erros.isEmpty()){
			fw.setPath("/midiaNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanMidia midia = new BeanMidia();
				
				BeanUtils.copyProperties(midia,formMidia);
				
				ModelMidia.getInstance().inserir(midia);
				
				fw = lista(mapping, form, request, response);
								
				request.getSession().removeAttribute("formMidia");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fw;
	}
			
}
