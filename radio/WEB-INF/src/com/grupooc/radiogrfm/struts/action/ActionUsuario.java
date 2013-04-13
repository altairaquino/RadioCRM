package com.grupooc.radiogrfm.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupooc.radiogrfm.dao.ModelUsuario;
import com.grupooc.radiogrfm.struts.bean.BeanUsuario;
import com.grupooc.radiogrfm.struts.form.FormUsuario;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionUsuario extends DispatchAction {

	
	public ActionForward alteraSenha(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormUsuario formUsuario = (FormUsuario)form;
		
		String senha = formUsuario.getUscpswd();
		String senh2 = formUsuario.getUscpsw2();
		String senh3 = formUsuario.getUscpsw3();
		
		BeanUsuario usuario = (BeanUsuario)request.getSession().getAttribute("usuario");
		
		if (usuario == null){
			fw.setPath("/login.do");
		}else{
		
			fw.setPath("/usuarioAlteraSenha.do");
					
			if (!ValidaObjeto.validaString(senha)){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Senha Atual é requerida."));
			}
			
			if (!ValidaObjeto.validaString(senh2)){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nova Senha é requerida."));
			}
			
			if (!ValidaObjeto.validaString(senh3)){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Repetição de Nova Senha é requerida."));
			}
			
			if (!senh2.equals(senh3)){
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Nova Senha e Repetição não conferem."));
			}
			
			if (!erros.isEmpty()){
				
			}else{
				if (ModelUsuario.getInstance().autenticaUsuario(usuario.getUsclogn(), senha)){
					ModelUsuario.getInstance().alteraSenha(Integer.parseInt(usuario.getUsncodg()),senh2);
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Sua Senha foi alterada com sucesso."));
				}else{
					erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","A senha não corresponde ao usuário atual."));
				}
			}
			
			saveErrors(request, erros);
			
		}
			
		request.getSession().removeAttribute("formUsuario");
		
		return fw;
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		fw.setPath("/login.do");
		
		request.getSession().invalidate();
		
		return fw;
	}
	
	

}
