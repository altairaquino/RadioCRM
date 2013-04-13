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

import com.grupooc.radiogrfm.dao.ModelCidade;
import com.grupooc.radiogrfm.dao.ModelCliente;
import com.grupooc.radiogrfm.dao.ModelContato;
import com.grupooc.radiogrfm.dao.ModelContrato;
import com.grupooc.radiogrfm.dao.ModelTipoLogradouro;
import com.grupooc.radiogrfm.struts.bean.BeanCliente;
import com.grupooc.radiogrfm.struts.bean.BeanEmpresa;
import com.grupooc.radiogrfm.struts.form.FormCliente;
import com.grupooc.radiogrfm.utils.CNPJ;
import com.grupooc.radiogrfm.utils.CPF;
import com.grupooc.radiogrfm.utils.GeraRelatorio;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionCliente extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			
			request.setAttribute("ls_contato", ModelCliente.getInstance().getClientes(Integer.parseInt(beanEmpresa.getEpncodg())));
			fw.setPath("/contatoLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward clientesDoContato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			String ctncodg = request.getParameter("ctncodg");
			request.setAttribute("ls_cliente", ModelCliente.getInstance().getClientesDoContato(Integer.parseInt(ctncodg),
					Integer.parseInt(beanEmpresa.getEpncodg())));
			fw.setPath("/clientesContato.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}	
	
	public ActionForward pesquisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			
			String clcnome = request.getParameter("clcnome");
			request.setAttribute("ls_cliente", ModelCliente.getInstance().getClientesPorNomeDoc(clcnome,
					Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().removeAttribute("formCliente");
			fw.setPath("/clientePesquisa.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward pesquisaContrato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			String clcnome = request.getParameter("clcnome");
			request.setAttribute("ls_cliente", ModelCliente.getInstance().getClientesPorNomeDoc(clcnome,
					Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().removeAttribute("formCliente");
			fw.setPath("/clientePesquisaContrato.do");
			
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
			String clncodg = request.getParameter("clncodg");
			request.setAttribute("cliente", ModelCliente.getInstance().getCliente(Integer.parseInt(clncodg)));
			fw.setPath("/clienteDados.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward novo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {		
			
			request.getSession().removeAttribute("formCliente");
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			request.getSession().setAttribute("ls_contato", ModelContato.getInstance().getContatos(Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado("PE"));
						
			fw.setPath("/clienteNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormCliente formCliente = (FormCliente)form;
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			
			String clncodg = request.getParameter("clncodg");
			
			BeanCliente cliente = ModelCliente.getInstance().getCliente(Integer.parseInt(clncodg));
			
			request.getSession().setAttribute("ls_tipologradouro", ModelTipoLogradouro.getInstance().getTiposLogradouro());
			request.getSession().setAttribute("ls_contato", ModelContato.getInstance().getContatos(Integer.parseInt(beanEmpresa.getEpncodg())));
			request.getSession().setAttribute("ls_estado", ModelCidade.getInstance().getEstados());
			request.getSession().setAttribute("ls_cidade", ModelCidade.getInstance().getCidadesDoEstado(cliente.getClcufcd()));
									
			BeanUtils.copyProperties(formCliente, cliente);
			
			fw.setPath("/clienteEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormCliente formCliente = (FormCliente)form;
		ActionMessages erros = new ActionMessages();
		
		
		if (!ValidaObjeto.validaString(formCliente.getClcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome da ag�ncia � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formCliente.getClcdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default",(formCliente.getClncgtp().equals("1")?"CPF":"CNPJ")+" � obrigat�rio."));
		}else{
			if(!CNPJ.validar(ValidaObjeto.removeCharOfInteger(formCliente.getClcdocm())) && !CPF.validar(ValidaObjeto.removeCharOfInteger(formCliente.getClcdocm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default",(formCliente.getClncgtp().equals("1")?"CPF":"CNPJ")+" informado � inv�lido."));
			}
		}
		
		/*
		if (!ValidaObjeto.validaString(formCliente.getClccont())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do contato direto � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaData(formCliente.getClddnct())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data de Nascimento do contato � obrigat�rio."));
		}
				
		if (!ValidaObjeto.validaString(formCliente.getClcfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Telefone � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formCliente.getClcmail()) && formCliente.getClcmail().indexOf("@") == -1){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","E-mail � obrigat�rio ou foi informado inv�lido."));
		}
		*/
		
		if (formCliente.getClncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","A Escolha da cidade � obrigat�ria."));
		}
		if (!ValidaObjeto.validaString(formCliente.getCllendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Endere�o � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formCliente.getClccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","CEP � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formCliente.getClcbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Bairro � obrigat�rio."));
		}
		
		if (!erros.isEmpty()){
			fw.setPath("/clienteEditar.do");
			saveErrors(request, erros);
		}else{
		
			try {
				
				BeanCliente contato = new BeanCliente();
				
				BeanUtils.copyProperties(contato,formCliente);
				
				ModelCliente.getInstance().update(contato);
				
				fw.setPath("/actionCliente.do?m=mostraDados&clncodg="+formCliente.getClncodg());
				
				request.getSession().removeAttribute("formCliente");
				request.getSession().removeAttribute("ls_tipopessoa");
				request.getSession().removeAttribute("ls_contato");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("ls_estado");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		ActionForward fw = new ActionForward();
		FormCliente formCliente = (FormCliente)form;
		ActionMessages erros = new ActionMessages();		
				
		if (!ValidaObjeto.validaString(formCliente.getClcnome())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do cliente � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formCliente.getClcdocm())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default",(formCliente.getClncgtp().equals("1")?"CPF":"CNPJ")+" � obrigat�rio."));
		}else{
			if(!CNPJ.validar(ValidaObjeto.removeCharOfInteger(formCliente.getClcdocm())) && !CPF.validar(ValidaObjeto.removeCharOfInteger(formCliente.getClcdocm()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default",(formCliente.getClncgtp().equals("1")?"CPF":"CNPJ")+" informado � inv�lido."));
			}
		}
		/*
		if (!ValidaObjeto.validaString(formCliente.getClccont())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Nome do contato (cliente) � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaData(formCliente.getClddnct())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data de Nascimento do contato � obrigat�rio."));
		}
				
		if (!ValidaObjeto.validaString(formCliente.getClcfone())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Telefone � obrigat�rio."));
		}
		
		if (!ValidaObjeto.validaString(formCliente.getClcmail()) && formCliente.getClcmail().indexOf("@") == -1){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","E-mail � obrigat�rio ou foi informado inv�lido."));
		}
		*/
		if (formCliente.getClncgcd().equals("-1")){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","A Escolha da cidade � obrigat�ria."));
		}
		if (!ValidaObjeto.validaString(formCliente.getCllendr())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Endere�o � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formCliente.getClccep())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","CEP � obrigat�rio."));
		}
		if (!ValidaObjeto.validaString(formCliente.getClcbair())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Bairro � obrigat�rio."));
		}
				
		if (!erros.isEmpty() || beanEmpresa == null){
			fw.setPath("/clienteNovo.do");			
		}else{
			try {
				
				BeanCliente cliente = new BeanCliente();
				
				BeanUtils.copyProperties(cliente,formCliente);
				cliente.setClncgep(beanEmpresa.getEpncodg());
				
				ModelCliente.getInstance().inserir(cliente);
											
				request.getSession().removeAttribute("formCliente");
				request.getSession().removeAttribute("ls_tipopessoa");
				request.getSession().removeAttribute("ls_contato");
				request.getSession().removeAttribute("ls_tipologradouro");
				request.getSession().removeAttribute("ls_estado");
				
				fw = novo(mapping, form, request, response);
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Cliente Cadastrado com Sucesso."));
				
			} catch (Exception e) {
				e.printStackTrace();
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Erro ao Cadastrar Cliente!!!"));
			}
		}
		saveErrors(request, erros);
		
		
		return fw;
	}
	
	public ActionForward opcoes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {
			String clncodg = request.getParameter("clncodg");
			request.getSession().setAttribute("cliente", ModelCliente.getInstance().getCliente(Integer.parseInt(clncodg)));
			fw.setPath("/clientePage.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward contratosDoCliente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {
			String clncodg = request.getParameter("clncodg");
			request.setAttribute("cliente", ModelCliente.getInstance().getCliente(Integer.parseInt(clncodg)));
			request.setAttribute("ls_contrato", ModelContrato.getInstance().contratosDoCliente(Integer.parseInt(clncodg)));
			fw.setPath("/contratoCliente.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward relatorioClientes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BeanEmpresa beanEmpresa = (BeanEmpresa)request.getSession().getAttribute("empresa");
		try {
			
			Map<Object,Object> map = new HashMap<Object, Object>();
			
			
			map.put("REPORT_NAME", "clientes_ativos");
			map.put("EMPRESA", new Integer(beanEmpresa.getEpncodg()));
			
			GeraRelatorio.geracao(request, response, map, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
