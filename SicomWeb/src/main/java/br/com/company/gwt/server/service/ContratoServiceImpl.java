package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import br.com.company.gwt.client.remoteinterface.ContratoService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoAgencia;
import br.com.company.gwt.server.dao.DaoCliente;
import br.com.company.gwt.server.dao.DaoContrato;
import br.com.company.gwt.server.dao.DaoFormaPagamento;
import br.com.company.gwt.server.dao.DaoOrigemContrato;
import br.com.company.gwt.server.dao.DaoPrograma;
import br.com.company.gwt.server.dao.DaoTipoContrato;
import br.com.company.gwt.server.dao.DaoUsuario;
import br.com.company.gwt.server.entities.Agencia;
import br.com.company.gwt.server.entities.Cliente;
import br.com.company.gwt.server.entities.Contrato;
import br.com.company.gwt.server.entities.FormaPagamento;
import br.com.company.gwt.server.entities.OrigemContrato;
import br.com.company.gwt.server.entities.Programa;
import br.com.company.gwt.server.entities.ProgramaContrato;
import br.com.company.gwt.server.entities.TipoContrato;
import br.com.company.gwt.server.entities.Usuario;
import br.com.company.gwt.shared.dto.DTOAgencia;
import br.com.company.gwt.shared.dto.DTOCliente;
import br.com.company.gwt.shared.dto.DTOContrato;
import br.com.company.gwt.shared.dto.DTOFormaPagamento;
import br.com.company.gwt.shared.dto.DTOOrigemContrato;
import br.com.company.gwt.shared.dto.DTOPrograma;
import br.com.company.gwt.shared.dto.DTOTipoContrato;
import br.com.company.gwt.shared.enums.TipoPagamento;

@Named("contratoService")
public class ContratoServiceImpl extends InputServletImpl implements ContratoService{
	
	@Inject private DaoPrograma daoPrograma;
	@Inject private DaoContrato daoContrato;
	@Inject private DaoFormaPagamento daoFormaPagamento;
	@Inject private DaoTipoContrato daoTipoContrato;
	@Inject private DaoOrigemContrato daoOrigemContrato;
	@Inject private DaoCliente daoCliente;
	@Inject private DaoUsuario daoUsuario;
	@Inject private DaoAgencia daoAgencia;
	
	@Override
	public List<DTOContrato> listAll() {
		List<DTOContrato> contratos = new ArrayList<DTOContrato>();
		try {
			for (Contrato contrato : daoContrato.loadAll()) {
				contratos.add(parseToDTOContrato(contrato));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contratos;
	}
	
	
	private DTOContrato parseToDTOContrato(Contrato contrato){
		DTOContrato dto = new DTOContrato();
		dto.setId(contrato.getId());
		dto.setDataCadastro(contrato.getDataCadastro());
		dto.setDataInicio(contrato.getDataInicio());
		dto.setDataTermino(contrato.getDataTermino());
		dto.setInformacoesTexto(contrato.getInformacoesTexto());
		dto.setDataPagamento(contrato.getDataPagamento());
		dto.setPercentualPermuta(contrato.getPercentualPermuta());
		
		dto.setValor(contrato.getValor());
		dto.setTipoPagamento(contrato.getTipoPagamento().name());
		
		Cliente cliente = contrato.getCliente();
		
		if (cliente != null){
			dto.setNomeCliente(cliente.getNome());
			DTOCliente dtoCliente = new DTOCliente();
			dtoCliente.setId(cliente.getId());
			dtoCliente.setNome(cliente.getNome());
			dto.setCliente(dtoCliente);
		}
		
		FormaPagamento formaPagamento = contrato.getFormaPagamento();
		
		if (formaPagamento != null){
			DTOFormaPagamento dtoFormaPagamento = new DTOFormaPagamento();
			dtoFormaPagamento.setId(formaPagamento.getId());
			dtoFormaPagamento.setNome(formaPagamento.getNome());
			dtoFormaPagamento.setTemPermuta(formaPagamento.getTemPermuta());
			dto.setFormaPagamento(dtoFormaPagamento);
		}
		
		TipoContrato tipoContrato = contrato.getTipoContrato();
		
		if (tipoContrato != null){
			DTOTipoContrato dtoTipoContrato = new DTOTipoContrato();
			dtoTipoContrato.setId(tipoContrato.getId());
			dtoTipoContrato.setNome(tipoContrato.getNome());
			dto.setTipoContrato(dtoTipoContrato);
		}
		
		Agencia agencia = contrato.getAgencia();
		
		if (agencia != null){
			DTOAgencia dtoAgencia = new DTOAgencia();
			dtoAgencia.setId(agencia.getId());
			dtoAgencia.setNome(agencia.getNome());
			dto.setAgencia(dtoAgencia);
		}
		
		OrigemContrato origemContrato = contrato.getOrigemContrato();
		
		if (origemContrato != null){
			DTOOrigemContrato dtoOrigemContrato = new DTOOrigemContrato();
			dtoOrigemContrato.setId(origemContrato.getId());
			dtoOrigemContrato.setNome(origemContrato.getNome());
			dto.setOrigemContrato(dtoOrigemContrato);
		}
		
		List<ProgramaContrato> programas = contrato.getProgramas();
		
		for (ProgramaContrato programaContrato : programas) {
			Programa programa = programaContrato.getPrograma();
			DTOPrograma dtoPrograma = new DTOPrograma();
			dtoPrograma.setId(programa.getId());
			dtoPrograma.setNome(programa.getNome());
			dto.getProgramas().add(dtoPrograma);
		}
		
		return dto;
	}

	@Transactional
	@Override
	public DTOContrato salvar(DTOContrato dtoContrato) throws Exception {
		
		HttpSession session = getServletRequest().getSession();
		Usuario usuario = daoUsuario.findByPrimaryKey(((Usuario) session.getAttribute("user")).getId());
		try {
			Contrato contrato = null;
			if (dtoContrato.getId() == null){
				contrato = new Contrato();
			}else{
				contrato = daoContrato.findByPrimaryKey(dtoContrato.getId());
			}
			
			contrato.setId(dtoContrato.getId());
			
			Cliente cliente = daoCliente.findByPrimaryKey(dtoContrato.getCliente().getId());
			
			contrato.setCliente(cliente);
			contrato.setDataCadastro(dtoContrato.getDataCadastro());
			contrato.setDataInicio(dtoContrato.getDataInicio());
			contrato.setDataTermino(dtoContrato.getDataTermino());
			contrato.setInformacoesTexto(dtoContrato.getInformacoesTexto());
			if (contrato.getInformacoesTexto() == null){
				contrato.setInformacoesTexto("");
			}
			contrato.setFormaPagamento(daoFormaPagamento.findByPrimaryKey(dtoContrato.getFormaPagamento().getId()));
			Agencia agencia = daoAgencia.findByPrimaryKey(dtoContrato.getAgencia().getId());
			contrato.setAgencia(agencia);
			contrato.setValor(dtoContrato.getValor());
			contrato.setTipoPagamento(TipoPagamento.valueOf(dtoContrato.getTipoPagamento()));
			contrato.setTipoContrato(daoTipoContrato.findByPrimaryKey(dtoContrato.getTipoContrato().getId()));
			contrato.setOrigemContrato(daoOrigemContrato.findByPrimaryKey(dtoContrato.getOrigemContrato().getId()));
			contrato.setPercentualPermuta(dtoContrato.getPercentualPermuta());
			contrato.setDataPagamento(dtoContrato.getDataPagamento());
			contrato.setPercentualComissao(agencia.getComissao());
			contrato.getProgramas().clear();
			
			for (DTOPrograma dtoPrograma : dtoContrato.getProgramas()) {
				ProgramaContrato programaContrato = new ProgramaContrato();
				programaContrato.setPrograma(daoPrograma.findByPrimaryKey(dtoPrograma.getId()));
				programaContrato.setContrato(contrato);
				contrato.getProgramas().add(programaContrato);
			}
			
			contrato.setDataUltimaAlteracao(new Date());
			contrato.setUsuario(usuario);
			
			if (contrato.getTipoPagamento() == TipoPagamento.LIQUIDO){
				contrato.setPercentualComissao(0f);
			}
			
			daoContrato.store(contrato);
			
			dtoContrato.setId(contrato.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return dtoContrato;
	}

	@Override
	public List<DTOContrato> pesquisa(DTOCliente dtoCliente, Date date) throws Exception{
		List<DTOContrato> contratos = new ArrayList<DTOContrato>();
		try {
			
			if (dtoCliente == null){
				for (Contrato contrato : daoContrato.pesquisa(date)) {
					contratos.add(parseToDTOContrato(contrato));				
				}
			}else {
				Cliente cliente = daoCliente.findByPrimaryKey(dtoCliente.getId());
				for (Contrato contrato : daoContrato.pesquisa(cliente)) {
					contratos.add(parseToDTOContrato(contrato));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return contratos;
	}

	@Transactional
	@Override
	public Boolean cancelar(Integer contratoId){
		
		try {
			Contrato contrato = daoContrato.find(contratoId);
			contrato.setDataCancelamento(new Date());
			daoContrato.store(contrato);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}