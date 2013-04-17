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

import com.grupooc.radiogrfm.dao.ModelPrograma;
import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.struts.bean.BeanPrograma;
import com.grupooc.radiogrfm.struts.form.FormPrograma;
import com.grupooc.radiogrfm.utils.Utils;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionPrograma extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			
			request.setAttribute("ls_programa", ModelPrograma.getInstance().getProgramas(
					Integer.parseInt(beanEmpresa.getEpncodg())));
			fw.setPath("/programaLista.do");
			
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
			
			request.getSession().removeAttribute("formPrograma");
						
			fw.setPath("/programaNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormPrograma formPrograma = (FormPrograma)form;
		
		try {
			
			String pgncodg = request.getParameter("pgncodg");
			BeanPrograma programa = ModelPrograma.getInstance().getPrograma(Integer.parseInt(pgncodg));
			
						
			BeanUtils.copyProperties(formPrograma, programa);
			
			fw.setPath("/programaEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormPrograma formPrograma = (FormPrograma)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaString(formPrograma.getPgcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do programa � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formPrograma.getPgyvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor informado � inv�lido."));
		}
		

		if (!ValidaObjeto.validaInteiro(formPrograma.getPgndurc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Dura��o do programa � inv�lido."));
		}		
		
		if (!erros.isEmpty()){
			fw.setPath("/programaEditar.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				BeanPrograma programa = new BeanPrograma();
				
				BeanUtils.copyProperties(programa,formPrograma);
				
				ModelPrograma.getInstance().update(programa);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formPrograma");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormPrograma formPrograma = (FormPrograma)form;
		ActionMessages erros = new ActionMessages();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		
		
		if (!ValidaObjeto.validaString(formPrograma.getPgcdesc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do programa � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formPrograma.getPgyvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor informado � inv�lido."));
		}
		

		if (!ValidaObjeto.validaInteiro(formPrograma.getPgndurc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Dura��o do programa � inv�lido."));
		}		
		
		if (!erros.isEmpty() || beanEmpresa == null){
			fw.setPath("/programaNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanPrograma programa = new BeanPrograma();
				
				BeanUtils.copyProperties(programa,formPrograma);
				
				programa.setPgncgep(beanEmpresa.getEpncodg());
				
				ModelPrograma.getInstance().inserir(programa);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formPrograma");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return fw;
	}
	
	public ActionForward opcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {
			String pgncodg = request.getParameter("pgncodg");
			request.getSession().setAttribute("programa", ModelPrograma.getInstance().getPrograma(Integer.parseInt(pgncodg)));
			fw.setPath("/programaPage.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
}
