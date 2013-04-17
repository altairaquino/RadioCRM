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

import com.grupooc.radiogrfm.dao.ModelMeses;
import com.grupooc.radiogrfm.dao.ModelMeta;
import com.grupooc.radiogrfm.dao.ModelMetaMes;
import com.grupooc.radiogrfm.struts.bean.BeanMetaMes;
import com.grupooc.radiogrfm.struts.form.FormMetaMes;
import com.grupooc.radiogrfm.utils.Utils;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionMetaMes extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			String mtncodg = request.getParameter("mtncodg");
			request.getSession().setAttribute("meta", ModelMeta.getInstance().getMeta(Integer.parseInt(mtncodg)));
			request.setAttribute("ls_metames", ModelMetaMes.getInstance().getMetasMensaisDaMeta(Integer.parseInt(mtncodg)));
			fw.setPath("/metaMesLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMetaMes formHorario = (FormMetaMes)form;
		
		try {
			
			String mmncodg = request.getParameter("mmncodg");
			BeanMetaMes metaMes = ModelMetaMes.getInstance().getMetaMes(Integer.parseInt(mmncodg));
			request.getSession().setAttribute("ls_meses", ModelMeses.getInstance().getMeses());
			
			BeanUtils.copyProperties(formHorario, metaMes);
			
			fw.setPath("/metaMesEditar.do");
			
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
			
			request.getSession().removeAttribute("formMetaMes");
			request.getSession().setAttribute("ls_meses", ModelMeses.getInstance().getMeses());
			
			fw.setPath("/metaMesNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMetaMes formMetaMes = (FormMetaMes)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formMetaMes.getMmnvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor da Meta esta incorreto."));
		}
				
						
		if (!erros.isEmpty()){
			fw.setPath("/metaMesNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanMetaMes metaMes = new BeanMetaMes();
				
				BeanUtils.copyProperties(metaMes,formMetaMes);
				
				ModelMetaMes.getInstance().inserir(metaMes);
				
				fw.setPath("/actionMetaMes.do?m=lista&mtncodg="+metaMes.getMmncgmt());
				
				request.getSession().removeAttribute("formMetaMes");
				request.getSession().removeAttribute("ls_meses");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMetaMes formMetaMes = (FormMetaMes)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formMetaMes.getMmnvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor da Meta esta incorreto."));
		}
				
		if (!erros.isEmpty()){
			fw.setPath("/metaEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanMetaMes metaMes = new BeanMetaMes();
				
				BeanUtils.copyProperties(metaMes,formMetaMes);
				
				ModelMetaMes.getInstance().update(metaMes);				
				
				fw.setPath("/actionMetaMes.do?m=lista&mtncodg="+metaMes.getMmncgmt());
				
				request.getSession().removeAttribute("formMetaMes");
				request.getSession().removeAttribute("ls_meses");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return fw;
	}
	
		
}
