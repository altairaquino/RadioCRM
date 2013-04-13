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

import com.grupooc.radiogrfm.dao.ModelContrato;
import com.grupooc.radiogrfm.dao.ModelInsercao;
import com.grupooc.radiogrfm.dao.ModelPrograma;
import com.grupooc.radiogrfm.struts.bean.BeanInsercao;
import com.grupooc.radiogrfm.struts.bean.BeanPrograma;
import com.grupooc.radiogrfm.struts.form.FormInsercao;
import com.grupooc.radiogrfm.utils.Utils;
import com.grupooc.radiogrfm.utils.ValidaObjeto;

public class ActionInsercao extends DispatchAction {

	public ActionForward lista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {			
			String crncodg = request.getParameter("crncodg");
			request.setAttribute("ls_insercao", ModelInsercao.getInstance().getInsercoesDoContrato(Integer.parseInt(crncodg)));
			request.setAttribute("valor", ModelInsercao.getInstance().valorInsercoesDoContrato(Integer.parseInt(crncodg)));
			fw.setPath("/insercaoLista.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return fw;
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormInsercao formInsercao = (FormInsercao)form;
		
		try {
			
			String inncodg = request.getParameter("inncodg");
			BeanInsercao insercao = ModelInsercao.getInstance().getInsercao(Integer.parseInt(inncodg));
			
			request.getSession().setAttribute("ls_programa", ModelPrograma.getInstance().getProgramasComValor());
			
			BeanUtils.copyProperties(formInsercao, insercao);
			
			fw.setPath("/insercaoEditar.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward desativar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		
		try {
			
			String inncodg = request.getParameter("inncodg");
			String crncodg = request.getParameter("crncodg");
			
			if (inncodg != null){
				ModelInsercao.getInstance().desativar(Integer.parseInt(inncodg));
			}
												
			fw.setPath("/actionInsercao.do?m=lista&crncodg="+crncodg);
			
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
			
			request.getSession().removeAttribute("formInsercao");
			request.getSession().setAttribute("ls_programa", ModelPrograma.getInstance().getProgramasComValor());
			
			fw.setPath("/insercaoNovo.do");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fw;
	}
	
	public ActionForward cadastro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward fw = new ActionForward();
		FormInsercao formInsercao = (FormInsercao)form;
		ActionMessages erros = new ActionMessages();
				
		BeanPrograma programa = ModelPrograma.getInstance().getPrograma(Integer.parseInt(formInsercao.getInncgpg()));
		
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formInsercao.getInndesc()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor de desconto não é inválido."));
		}
		
		if (!ValidaObjeto.validaData(formInsercao.getInddata())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Informada é inválido."));
		}
		
		if (!ValidaObjeto.validaInteiro(formInsercao.getInnqtd())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Informada é inválido."));
		}
		
		if (erros.isEmpty()){
			if (!ModelContrato.getInstance().temCredito(
					Integer.parseInt(formInsercao.getInncgcr())
					, (
							 Float.parseFloat(Utils.converteFloatBR(programa.getPgyvalr()))
							 * 
							 Integer.parseInt(formInsercao.getInnqtd()))
					  - Float.parseFloat(Utils.converteFloatBR(formInsercao.getInndesc()))
					 )
			     ){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","O valor do crédito restante do contrato não cobre a inserção atual."));
			}
			if (!ModelContrato.getInstance().estaPeriodoVigencia(Integer.parseInt(formInsercao.getInncgcr()), 
					Utils.strBRToDate(formInsercao.getInddata()))){
				erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Inserção não pode está fora da data de vigência do contrato."));
			}
		}
										
		if (!erros.isEmpty()){
			fw.setPath("/insercaoNovo.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanInsercao insercao = new BeanInsercao();
				
				BeanUtils.copyProperties(insercao,formInsercao);
				
				ModelInsercao.getInstance().inserir(insercao);
				
				fw.setPath("/actionInsercao.do?m=lista&crncodg="+insercao.getInncgcr());
				
				request.getSession().removeAttribute("ls_programa");
				request.getSession().removeAttribute("formInsercao");
				
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
		FormInsercao formInsercao = (FormInsercao)form;
		ActionMessages erros = new ActionMessages();
				
		if (!ValidaObjeto.validaFloat(Utils.converteFloatBR(formInsercao.getInndesc()))){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Valor de desconto não é inválido."));
		}
		
		if (!ValidaObjeto.validaData(formInsercao.getInddata())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Informada é inválido."));
		}
		
		if (!ValidaObjeto.validaInteiro(formInsercao.getInnqtd())){
			erros.add(ActionMessages.GLOBAL_MESSAGE ,new ActionMessage("errors.default","Data Informada é inválido."));
		}
		
						
		if (!erros.isEmpty()){
			fw.setPath("/insercaoEditar.do");
			saveErrors(request, erros);
		}else{
			try {
				
				BeanInsercao insercao = new BeanInsercao();
				
				BeanUtils.copyProperties(insercao,formInsercao);
				
				ModelInsercao.getInstance().update(insercao);				
				
				fw.setPath("/actionInsercao.do?m=lista&crncodg="+insercao.getInncgcr());
				
				request.getSession().removeAttribute("ls_programa");
				request.getSession().removeAttribute("formInsercao");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return fw;
	}
	
		
}
