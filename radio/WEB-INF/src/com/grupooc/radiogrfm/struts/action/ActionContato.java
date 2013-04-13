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

import com.grupooc.radiogrfm.dao.ModelContato;
import com.grupooc.radiogrfm.struts.bean.BeanContato;
import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.struts.bean.BeanUsuario;
import com.grupooc.radiogrfm.struts.form.FormContato;
import com.grupooc.radiogrfm.utils.GeraRelatorio;
import com.grupooc.radiogrfm.utils.Utils;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionContato extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			
			request.setAttribute("ls_contato", ModelContato.getInstance().getContatos(
					Integer.parseInt(beanEmpresa.getEpncodg())));
			fw.setPath("/contatoLista.do");
			
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
			
			request.getSession().removeAttribute("formContato");
						
			fw.setPath("/contatoNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormContato formContato = (FormContato)form;
		
		try {
			
			String ctncodg = request.getParameter("ctncodg");
			BeanContato contato = ModelContato.getInstance().getContato(Integer.parseInt(ctncodg));
									
			BeanUtils.copyProperties(formContato, contato);
			
			fw.setPath("/contatoEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormContato formContato = (FormContato)form;
		ActionMessages erros = new ActionMessages();
		
		
		if (!ValidaObjeto.validaString(formContato.getCtcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do Contato � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formContato.getCtcmatr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Matr�cula � obrigat�ria."));
		}
		
		if (!ValidaObjeto.validaString(formContato.getCtcfunc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Fun��o � obrigat�ria."));
		}
		
		if (!ValidaObjeto.validaData(formContato.getCtdnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data de Nascimento � inv�lida."));
		}
		
		if (!ValidaObjeto.validaString(formContato.getCtcfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Fone fixo � obrigat�rio."));
		}
		
		
		if (!erros.isEmpty()){
			fw.setPath("/contatoEditar.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				BeanContato contato = new BeanContato();
				
				BeanUtils.copyProperties(contato,formContato);
				
				ModelContato.getInstance().update(contato);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formContato");
				
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
		FormContato formContato = (FormContato)form;
		ActionMessages erros = new ActionMessages();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		
		if (!ValidaObjeto.validaString(formContato.getCtcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do Contato � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formContato.getCtcmatr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Matr�cula � obrigat�ria."));
		}
		
		if (!ValidaObjeto.validaString(formContato.getCtcfunc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Fun��o � obrigat�ria."));
		}
		
		if (!ValidaObjeto.validaData(formContato.getCtdnasc())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data de Nascimento � inv�lida."));
		}
		
		if (!ValidaObjeto.validaString(formContato.getCtcfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Fone fixo � obrigat�rio."));
		}
		
		if (!erros.isEmpty() || beanEmpresa == null){
			fw.setPath("/contatoNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanContato contato = new BeanContato();
				
				BeanUtils.copyProperties(contato,formContato);
				
				contato.setCtncgep(beanEmpresa.getEpncodg());
				
				ModelContato.getInstance().inserir(contato);
				
				fw = lista(mapping, form, request, response);
				
				request.getSession().removeAttribute("formContato");
				
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
			String ctncodg = request.getParameter("ctncodg");
			request.getSession().setAttribute("contato", ModelContato.getInstance().getContato(Integer.parseInt(ctncodg)));
			fw.setPath("/contatoPage.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward relatorioHanking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionMessages erros = new ActionMessages();
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		BeanUsuario usuario = (BeanUsuario)request.getSession().getAttribute("usuario");
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		
		if (!ValidaObjeto.validaData(data1)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial é inválida."));
		}
		if (!ValidaObjeto.validaData(data2)){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Final é inválida."));
		}
		
		if (erros.isEmpty()){
			if (Utils.strBRToDate(data1).after(Utils.strBRToDate(data2))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Inicial não pode ser maior que a final."));
			}
		}
		
		
		if (!erros.isEmpty() || usuario == null){
			fw.setPath("/relatHanking.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				
				map.put("EMPRESA", new Integer(beanEmpresa.getEpncodg()));
				map.put("DATA_INICIO", Utils.strBRToDate(data1));
				map.put("DATA_FIM", Utils.strBRToDate(data2));
				
				switch (request.getParameter("tipo").charAt(0)) {
					case '1':
						map.put("REPORT_NAME", "hanking_vendas");
						break;
					case '2':
						map.put("REPORT_NAME", "hanking_vendas_graf");
						break;
					case '3':
						map.put("REPORT_NAME", "hanking_compras");
						break;
					case '4':
						map.put("REPORT_NAME", "hanking_compras_graf");
						break;
					case '5':
						map.put("REPORT_NAME", "vendas_geral");
						break;
					case '6':
						map.put("REPORT_NAME", "hanking_vendas_agencia");
						break;
					case '7':
						map.put("REPORT_NAME", "vendas_geral_agencia");
						break;
					case '8':
						map.put("REPORT_NAME", "vendas_geral_contato");
						break;
	
					default:
						map.put("REPORT_NAME", "hanking_vendas");
						break;
				}
								
				GeraRelatorio.geracao(request, response, map, false);
				
				fw = null;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fw;
	}

	
}
