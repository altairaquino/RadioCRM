package com.grupooc.radiogrfm.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.grupooc.radiogrfm.dao.ModelEmpresa;
import com.grupooc.radiogrfm.dao.ModelOperacao;
import com.grupooc.radiogrfm.dao.ModelUsuario;
import com.grupooc.radiogrfm.struts.bean.BeanUsuario;
import com.grupooc.radiogrfm.struts.form.FormLogin;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionLogin extends DispatchAction {

	
	public ActionForward autenticaUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		ActionMessages erros = new ActionMessages();
		FormLogin formLogin = (FormLogin)form;
		
		String senha = formLogin.getSenha();
		String login = formLogin.getLogin();
		
		if (!ValidaObjeto.validaString(senha) && !ValidaObjeto.validaString(login)){
			erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Login ou senha Invalidos ou usu�rio inabilitado."));
		}
		
		if (erros.isEmpty()){
			if(ModelUsuario.getInstance().autenticaUsuario(login, senha)){
				
				BeanUsuario usuario = ModelUsuario.getInstance().getBeanUsuarioPorLogin(login);
				
				request.getSession().setAttribute("usuario", usuario);
				request.getSession().setAttribute("menu", ModelOperacao.getInstance().getMenu(Integer.parseInt(usuario.getUsncodg())));
				request.getSession().setAttribute("empresa", ModelEmpresa.getInstance().getEmpresa(Integer.parseInt(usuario.getUsncgep())));
				
				//limpa os dados do formLogin
				request.getSession().removeAttribute("formLogin");
				
				fw.setPath("/home.do");
			}else{
				erros.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("errors.default","Login ou senha Invalidos ou usu�rio inabilitado."));
				saveErrors(request, erros);
				fw.setPath("/login.do");
			}
		}else{
			saveErrors(request, erros);
			fw.setPath("/login.do");
		}		
		
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
