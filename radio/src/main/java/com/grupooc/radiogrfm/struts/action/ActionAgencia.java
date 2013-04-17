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

import com.grupooc.radiogrfm.dao.ModelAgencia;
import com.grupooc.radiogrfm.dao.ModelCidade;
import com.grupooc.radiogrfm.dao.ModelTipoLogradouro;
import com.grupooc.radiogrfm.struts.bean.BeanAgencia;
import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.struts.form.FormAgencia;
import com.grupooc.radiogrfm.utils.CNPJ;
import com.grupooc.radiogrfm.utils.CPF;
import com.grupooc.radiogrfm.utils.Utils;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionAgencia extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {			
			
			request.setAttribute("ls_agencia", ModelAgencia.getInstance().getAgencias(Integer.parseInt(beanEmpresa.getEpncodg())));
			fw.setPath("/agenciaLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward listaClientes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		ActionForward fw = new ActionForward();
		String agncodg = request.getParameter("agncodg");
		try {
			if (agncodg != null){				
				request.setAttribute("ls_cliente", ModelAgencia.getInstance().getClienteDaAgencia(Integer.parseInt(agncodg)));
			}
			request.setAttribute("agncodg", agncodg);
			fw.setPath("/pages/clienteAgencia.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormAgencia formAgencia = (FormAgencia)form;
		
		try {
			
			String agncodg = request.getParameter("agncodg");
			BeanAgencia agencia = ModelAgencia.getInstance().getAgencia(Integer.parseInt(agncodg));
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(agencia.getAgcufcd()));
			
			BeanUtils.copyProperties(formAgencia, agencia);
			
			fw.setPath("/agenciaEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward dados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
				
		try {
			
			String agncodg = request.getParameter("agncodg");
			BeanAgencia agencia = ModelAgencia.getInstance().getAgencia(Integer.parseInt(agncodg));
			request.setAttribute("agencia", agencia);			
			
			fw.setPath("/agenciaDados.do");
			
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
			
			request.getSession().removeAttribute("formAgencia");
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());			
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado("PE"));			
			
			fw.setPath("/agenciaNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		ActionForward fw = new ActionForward();
		FormAgencia formAgencia = (FormAgencia)form;
		ActionMessages erros = new ActionMessages();
						
		if (!ValidaObjeto.validaString(formAgencia.getAgcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome da ag�ncia � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgccnpj())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default",(formAgencia.getAgncgtp().equals("2")?"CNPJ":"CPF") + " � obrigat�rio."));
		}else{
			
			if (formAgencia.getAgncgtp().equals("1")){
				if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formAgencia.getAgccnpj()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE , new ActionMessage("errors.default","CPF informado � inv�lido."));
				}
			}else{
				if (!CNPJ.validar(ValidaObjeto.removeCharOfInteger(formAgencia.getAgccnpj()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE , new ActionMessage("errors.default","CNPJ informado � inv�lido."));
				}
			}			
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcrzsc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Raz�o Social � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Telefone � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcmail()) && formAgencia.getAgcmail().indexOf("@") == -1){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","E-mail � obrigat�rio ou foi informado inv�lido."));
		}
		if (formAgencia.getAgncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","A Escolha da cidade � obrigat�ria."));
		}
		if (!ValidaObjeto.validaString(formAgencia.getAglendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Endere�o � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formAgencia.getAgccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","CEP � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formAgencia.getAgcbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Bairro � obrigat�rio."));
		}		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formAgencia.getAgncoms()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor da Comiss�o � invalido."));
		}
		
		
		
		
		if (!erros.isEmpty() || beanEmpresa == null){
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(formAgencia.getAgcufcd()));
			fw.setPath("/agenciaNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanAgencia agencia = new BeanAgencia();
				
				BeanUtils.copyProperties(agencia,formAgencia);
				
				agencia.setAgncgep(beanEmpresa.getEpncodg());
				
				ModelAgencia.getInstance().inserir(agencia);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formAgencia");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("ls_estado");
				request.getSession().removeAttribute("ls_cidade");
				
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
		FormAgencia formAgencia = (FormAgencia)form;
		ActionMessages erros = new ActionMessages();
		
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome da ag�ncia � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgccnpj())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default",(formAgencia.getAgncgtp().equals("2")?"CNPJ":"CPF") + " � obrigat�rio."));
		}else{			
			if (formAgencia.getAgncgtp().equals("1")){
				if (!CPF.validar(ValidaObjeto.removeCharOfInteger(formAgencia.getAgccnpj()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE , new ActionMessage("errors.default","CPF informado � inv�lido."));
				}
			}else{
				if (!CNPJ.validar(ValidaObjeto.removeCharOfInteger(formAgencia.getAgccnpj()))){
					erros.add(ActionMessages.GLOBAL_MESSAGE , new ActionMessage("errors.default","CNPJ informado � inv�lido."));
				}
			}
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcrzsc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Raz�o Social � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Telefone � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formAgencia.getAgcmail()) && formAgencia.getAgcmail().indexOf("@") == -1){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","E-mail � obrigat�rio ou foi informado inv�lido."));
		}
		if (formAgencia.getAgncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","A Escolha da cidade � obrigat�ria."));
		}		
		
		if (!ValidaObjeto.validaString(formAgencia.getAglendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Endere�o � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formAgencia.getAgccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","CEP � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formAgencia.getAgcbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Bairro � obrigat�rio."));
		}
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formAgencia.getAgncoms()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor da Comiss�o � invalido."));
		}
				
		if (!erros.isEmpty()){
			fw.setPath("/agenciaEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanAgencia agencia = new BeanAgencia();
				
				BeanUtils.copyProperties(agencia, formAgencia);
				
				ModelAgencia.getInstance().update(agencia);				
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formAgencia");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("ls_estado");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return fw;
	}	
		
}
