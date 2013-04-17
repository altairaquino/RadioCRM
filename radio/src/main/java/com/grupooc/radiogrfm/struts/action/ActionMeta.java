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

import com.grupooc.radiogrfm.dao.ModelMeta;
import com.grupooc.radiogrfm.struts.bean.BeanMeta;
import com.grupooc.radiogrfm.struts.form.FormMeta;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionMeta extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			String ctncodg = request.getParameter("ctncodg");
			request.setAttribute("ls_meta", ModelMeta.getInstance().getMetasDoContato(Integer.parseInt(ctncodg)));
			fw.setPath("/metaLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMeta formHorario = (FormMeta)form;
		
		try {
			
			String mtncodg = request.getParameter("mtncodg");
			BeanMeta horario = ModelMeta.getInstance().getMeta(Integer.parseInt(mtncodg));
			
			BeanUtils.copyProperties(formHorario, horario);
			
			fw.setPath("/metaEditar.do");
			
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
			
			request.getSession().removeAttribute("formMeta");
			
			fw.setPath("/metaNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormMeta formHorario = (FormMeta)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaInteiro(formHorario.getMtnano())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Ano informado é inválido."));
		}
				
						
		if (!erros.isEmpty()){
			fw.setPath("/metaNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanMeta meta = new BeanMeta();
				
				BeanUtils.copyProperties(meta,formHorario);
				
				ModelMeta.getInstance().inserir(meta);
				
				fw.setPath("/actionMeta.do?m=lista&ctncodg="+meta.getMtncgct());
				
				request.getSession().removeAttribute("formMeta");
				
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
		FormMeta formHorario = (FormMeta)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaInteiro(formHorario.getMtnano())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Ano informado é inválido."));
		}
				
		if (!erros.isEmpty()){
			fw.setPath("/metaEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanMeta meta = new BeanMeta();
				
				BeanUtils.copyProperties(meta,formHorario);
				
				ModelMeta.getInstance().update(meta);				
				
				fw.setPath("/actionMeta.do?m=lista&ctncodg="+meta.getMtncgct());
				
				request.getSession().removeAttribute("formMeta");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return fw;
	}
	
		
}
