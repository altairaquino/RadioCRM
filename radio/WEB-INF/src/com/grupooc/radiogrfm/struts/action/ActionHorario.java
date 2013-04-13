package com.grupooc.radiogrfm.struts.action;

import java.sql.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupooc.radiogrfm.dao.ModelDiaSemana;
import com.grupooc.radiogrfm.dao.ModelHorario;
import com.grupooc.radiogrfm.struts.bean.BeanHorario;
import com.grupooc.radiogrfm.struts.form.FormHorario;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionHorario extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			String pgncodg = request.getParameter("pgncodg");
			request.setAttribute("ls_horario", ModelHorario.getInstance().getHorariosDoPrograma(Integer.parseInt(pgncodg)));
			fw.setPath("/horarioLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormHorario formHorario = (FormHorario)form;
		
		try {
			
			String hrncodg = request.getParameter("hrncodg");
			BeanHorario horario = ModelHorario.getInstance().getHorario(Integer.parseInt(hrncodg));
			
			request.getSession().setAttribute("ls_diasemana", ModelDiaSemana.getInstance().getDiasDaSemana());
			
			BeanUtils.copyProperties(formHorario, horario);
			
			fw.setPath("/horarioEditar.do");
			
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
			
			request.getSession().removeAttribute("formHorario");
			request.getSession().setAttribute("ls_diasemana", ModelDiaSemana.getInstance().getDiasDaSemana());
			
			fw.setPath("/horarioNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormHorario formHorario = (FormHorario)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaHora(formHorario.getHrhinic())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Horário Inicial é inválido."));
		}
		
		if (!ValidaObjeto.validaHora(formHorario.getHrhterm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Horário Final é inválido."));
		}
		
		if (erros.isEmpty()){
			if (Time.valueOf(formHorario.getHrhinic()).after(Time.valueOf(formHorario.getHrhterm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Horário Inicial não pode ser maior que o Horário Final."));
			}
		}
				
		if (!erros.isEmpty()){
			fw.setPath("/horarioNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanHorario horario = new BeanHorario();
				
				BeanUtils.copyProperties(horario,formHorario);
				
				ModelHorario.getInstance().inserir(horario);
				
				fw.setPath("/actionHorario.do?m=lista&pgncodg="+horario.getHrncgpg());
				
				request.getSession().removeAttribute("ls_diasemana");
				request.getSession().removeAttribute("formHorario");
				
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
		FormHorario formHorario = (FormHorario)form;
		ActionMessages erros = new ActionMessages();
		
		
		
		if (!ValidaObjeto.validaHora(formHorario.getHrhinic())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Horário Inicial é inválido."));
		}
		
		if (!ValidaObjeto.validaHora(formHorario.getHrhterm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Horário Final é inválido."));
		}
		
		if (erros.isEmpty()){
			if (Time.valueOf(formHorario.getHrhinic()).after(Time.valueOf(formHorario.getHrhterm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Horário Inicial não pode ser maior que o Horário Final."));
			}
		}
				
		if (!erros.isEmpty()){
			fw.setPath("/horarioEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanHorario horario = new BeanHorario();
				
				BeanUtils.copyProperties(horario,formHorario);
				
				ModelHorario.getInstance().update(horario);				
				
				fw.setPath("/actionHorario.do?m=lista&pgncodg="+horario.getHrncgpg());
				
				request.getSession().removeAttribute("ls_diasemana");
				request.getSession().removeAttribute("formHorario");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return fw;
	}
	
		
}
