package com.grupooc.radiogrfm.struts.action;

import java.util.HashMap;
import java.util.Map;

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
import com.grupooc.radiogrfm.dao.ModelCliente;
import com.grupooc.radiogrfm.dao.ModelCondPagamento;
import com.grupooc.radiogrfm.dao.ModelContrato;
import com.grupooc.radiogrfm.dao.ModelFormaPagamento;
import com.grupooc.radiogrfm.dao.ModelMidia;
import com.grupooc.radiogrfm.struts.bean.BeanContrato;
import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.struts.form.FormContrato;
import com.grupooc.radiogrfm.utils.GeraRelatorio;
import com.grupooc.radiogrfm.utils.Utils;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionContrato extends DispatchAction {

	public ActionForward aberto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa) request.getSession().getAttribute("empresa");
		try {		
			
			if (beanEmpresa != null){
				request.setAttribute("ls_contrato", ModelContrato.getInstance().getContratosAberto(Integer.parseInt(beanEmpresa.getEpncodg()) ));
			}
			fw.setPath("/contratoLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BeanEmpresa beanEmpresa = (BeanEmpresa) request.getSession().getAttribute("empresa");
		ActionForward fw = new ActionForward();
		try {			
			request.getSession().setAttribute("cliente", ModelCliente.getInstance().getCliente(Integer.parseInt(request.getParameter("clncodg"))));
			request.getSession().removeAttribute("formContrato");
			request.getSession().setAttribute("ls_formapagamento", ModelFormaPagamento.getInstance().getFormasDePagamento());
			request.getSession().setAttribute("ls_condicaopagamento", ModelCondPagamento.getInstance().getCondicoesDePagamento());
			request.getSession().setAttribute("ls_contatos", ModelContrato.getInstance().getContratosAberto(Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().setAttribute("ls_agencia", ModelAgencia.getInstance().getAgencias(Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().setAttribute("ls_midia", ModelMidia.getInstance().getMidias(
					Integer.parseInt(beanEmpresa.getEpncodg())));
						
			fw.setPath("/contratoNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		BeanEmpresa beanEmpresa = (BeanEmpresa) request.getSession().getAttribute("empresa");
		try {
			
			String crncodg = request.getParameter("crncodg");
			BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(crncodg));
									
			BeanUtils.copyProperties(formContrato, contrato);
			
			request.getSession().setAttribute("ls_formapagamento", ModelFormaPagamento.getInstance().getFormasDePagamento());
			request.getSession().setAttribute("ls_condicaopagamento", ModelCondPagamento.getInstance().getCondicoesDePagamento());
			request.getSession().setAttribute("ls_contatos", ModelContrato.getInstance().getContratosAberto(Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().setAttribute("ls_agencia", ModelAgencia.getInstance().getAgencias(Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().setAttribute("ls_midia", ModelMidia.getInstance().getMidias(
					Integer.parseInt(beanEmpresa.getEpncodg())));
			
			fw.setPath("/contratoEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward mostraDados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
				
		try {
			
			String crncodg = request.getParameter("crncodg");
			BeanContrato contrato = ModelContrato.getInstance().getContrato(Integer.parseInt(crncodg));
									
			request.setAttribute("contrato", contrato);
			
			fw.setPath("/contratoDados.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormContrato formContrato = (FormContrato)form;
		ActionMessages erros = new ActionMessages();
		
		
		if (!ValidaObjeto.validaData(formContrato.getCrdinic())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaData(formContrato.getCrdterm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � obrigat�ria."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(formContrato.getCrdinic()).after(Utils.strBRToDate(formContrato.getCrdterm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � menor que a inicial."));
			}
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCrnvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor informado do contrato � inv�lido."));
		}
		
		if (!ValidaObjeto.validaData(formContrato.getCrdcadt())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data de cadastro � obrigat�ria."));
		}
						
		if (!erros.isEmpty()){
			fw.setPath("/contratoEditar.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				BeanContrato contrato = new BeanContrato();
				
				BeanUtils.copyProperties(contrato,formContrato);
				
				ModelContrato.getInstance().update(contrato);
				
				fw = aberto(mapping, form, request, response);
				
				request.getSession().removeAttribute("formContrato");
				request.getSession().removeAttribute("ls_formapagamento");
				request.getSession().removeAttribute("ls_condicaopagamento");
				request.getSession().removeAttribute("ls_contatos");
				request.getSession().removeAttribute("ls_agencia");
				request.getSession().removeAttribute("ls_midia");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fw;
	}
	
	public ActionForward exclui(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new  ActionForward();
		
		try {
			String crncodg = request.getParameter("crncodg");
			BeanContrato contrato = new BeanContrato();
			contrato.setCrncodg(crncodg);
			ModelContrato.getInstance().excluir(contrato);
			fw.setPath("/home.do");
			request.setAttribute("msg", "Contrato Excluido com sucesso!");
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
		FormContrato formContrato = (FormContrato)form;
		ActionMessages erros = new ActionMessages();
		
		if (!ValidaObjeto.validaData(formContrato.getCrdinic())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaData(formContrato.getCrdterm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � obrigat�ria."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(formContrato.getCrdinic()).after(Utils.strBRToDate(formContrato.getCrdterm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final � menor que a inicial."));
			}
		}
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formContrato.getCrnvalr()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor informado do contrato � inv�lido."));
		}
		
		if (!ValidaObjeto.validaData(formContrato.getCrdcadt())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data de cadastro � obrigat�ria."));
		}
				
		if (!erros.isEmpty() || beanEmpresa == null){
			fw.setPath("/contratoNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanContrato contrato = new BeanContrato();
				
				BeanUtils.copyProperties(contrato,formContrato);
				
				contrato.setCrncgep(beanEmpresa.getEpncodg());
				
				ModelContrato.getInstance().inserir(contrato);
				
				fw = aberto(mapping, form, request, response);
				
				request.getSession().removeAttribute("formContrato");
				
				request.getSession().removeAttribute("formContrato");
				request.getSession().removeAttribute("ls_formapagamento");
				request.getSession().removeAttribute("ls_condicaopagamento");
				request.getSession().removeAttribute("ls_contatos");
				request.getSession().removeAttribute("ls_agencia");
				request.getSession().removeAttribute("ls_midia");
				
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
			String crncodg = request.getParameter("crncodg");
			request.getSession().setAttribute("contrato", ModelContrato.getInstance().getContrato(Integer.parseInt(crncodg)));
			fw.setPath("/contratoPage.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward imprimir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			map.put("EMPRESA", new Integer(request.getParameter("epncodg")));
			map.put("CRNCODG", new Integer(request.getParameter("crncodg")));
			map.put("REPORT_NAME", "contrato");
			
			GeraRelatorio.geracao(request, response, map, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
