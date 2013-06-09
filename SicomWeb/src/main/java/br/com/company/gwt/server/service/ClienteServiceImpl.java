package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

import br.com.company.gwt.client.remoteinterface.ClienteService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoAgencia;
import br.com.company.gwt.server.dao.DaoCidade;
import br.com.company.gwt.server.dao.DaoCliente;
import br.com.company.gwt.server.dao.DaoTipoLogradouro;
import br.com.company.gwt.server.entities.Agencia;
import br.com.company.gwt.server.entities.Cidade;
import br.com.company.gwt.server.entities.Cliente;
import br.com.company.gwt.server.entities.TipoLogradouro;
import br.com.company.gwt.server.entities.TipoPessoa;
import br.com.company.gwt.shared.dto.DTOAgencia;
import br.com.company.gwt.shared.dto.DTOCidade;
import br.com.company.gwt.shared.dto.DTOCliente;
import br.com.company.gwt.shared.dto.DTOTipoLogradouro;

@Named("clienteService")
public class ClienteServiceImpl extends InputServletImpl implements ClienteService{
	
	@Inject private DaoCliente daoCliente;
	@Inject private DaoCidade daoCidade;
	@Inject private DaoAgencia daoAgencia;
	@Inject private DaoTipoLogradouro daoTipoLogradouro;

	@Override
	public List<DTOCliente> listAll() {
		List<DTOCliente> clientes = new ArrayList<DTOCliente>();
		try {
			for (Cliente cliente : daoCliente.loadAll()) {
				clientes.add(parseToDTOCliente(cliente));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	@Override
	public List<DTOCliente> pesquisa(String query) {
		List<DTOCliente> clientes = new ArrayList<DTOCliente>();
		try {
			for (Cliente cliente : daoCliente.getClienteByNome(query)) {
				clientes.add(parseToDTOCliente(cliente));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	@Transactional
	@Override
	public DTOCliente salvar(DTOCliente dtoCliente) throws Exception{
		try {
			Cliente cliente = null;
			if (dtoCliente.getId() == null) {
				cliente = new Cliente();
				/*
				Cliente clienteTemp = daoCliente.getClienteByDocumento(dtoCliente.getDocumento()); 
				if (clienteTemp != null){
					throw new Exception("Já existe cliente cadastrado com este documento: ["+dtoCliente.getDocumento()+ "].");
				}
				*/
			}else{
				cliente = daoCliente.findByPrimaryKey(dtoCliente.getId());
			}
			
			cliente.setNome(dtoCliente.getNome());
			cliente.setRazaoSocial(dtoCliente.getRazaoSocial());
			cliente.setSegmento(dtoCliente.getSegmento());
			cliente.setDocumento(dtoCliente.getDocumento());
			cliente.setTipoPessoa(TipoPessoa.valueOf(dtoCliente.getTipoPessoa()));
			cliente.setDataNascimento(dtoCliente.getDataNascimento());
			cliente.setAgencia(daoAgencia.findByPrimaryKey(dtoCliente.getAgencia().getId()));
			cliente.setEmail(dtoCliente.getEmail());
			cliente.setFone(dtoCliente.getFone());
			cliente.setTipoLogradouro(daoTipoLogradouro.findByPrimaryKey(dtoCliente.getTipoLogradouro().getId()));
			cliente.setLogradouro(dtoCliente.getLogradouro());
			cliente.setComplemento(dtoCliente.getComplemento());
			cliente.setCep(dtoCliente.getCep());
			cliente.setBairro(dtoCliente.getBairro());
			cliente.setCidade(daoCidade.findByPrimaryKey(dtoCliente.getCidade().getId()));
			cliente.setNomeContato(dtoCliente.getNomeContato());
			cliente.setFoneContato(dtoCliente.getFoneContato());
			cliente.setCellContato(dtoCliente.getCellContato());
			cliente.setDataNascimentoContato(dtoCliente.getDataNascimentoContato());
			cliente.setNomeProprietario(dtoCliente.getNomeProprietario());
			cliente.setDataNascimentoProprietario(dtoCliente.getDataNascimentoProprietario());
			cliente.setAtivo(dtoCliente.getAtivo());
			
			daoCliente.store(cliente);
			
			dtoCliente.setId(cliente.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		return dtoCliente;
	}
	
	private DTOCliente parseToDTOCliente(Cliente cliente){
		DTOCliente dto = new DTOCliente();
		dto.setId(cliente.getId());
		dto.setNome(cliente.getNome());
		dto.setRazaoSocial(cliente.getRazaoSocial());
		dto.setTipoPessoa(cliente.getTipoPessoa().name());
		dto.setSegmento(cliente.getSegmento());
		dto.setFone(cliente.getFone());
		dto.setEmail(cliente.getEmail());
		dto.setDocumento(cliente.getDocumento());
		dto.setDataNascimento(cliente.getDataNascimento());
		Agencia agencia = cliente.getAgencia();
		if (agencia != null){
			DTOAgencia dtoAgencia = new DTOAgencia();
			dtoAgencia.setId(agencia.getId());
			dtoAgencia.setNome(agencia.getNome());
			dto.setAgencia(dtoAgencia);
		}
		TipoLogradouro tipoLogradouro = cliente.getTipoLogradouro();
		if (tipoLogradouro != null){
			DTOTipoLogradouro tipo = new DTOTipoLogradouro();
			tipo.setId(tipoLogradouro.getId());
			tipo.setNome(tipoLogradouro.getNome());
			dto.setTipoLogradouro(tipo);
		}
		dto.setLogradouro(cliente.getLogradouro());
		dto.setComplemento(cliente.getComplemento());
		dto.setCep(cliente.getCep());
		dto.setBairro(cliente.getBairro());

		Cidade cidade = cliente.getCidade();
		if (cidade != null){
			DTOCidade dtoCidade = new DTOCidade();
			dtoCidade.setId(cidade.getId());
			dtoCidade.setNome(cidade.getNome());
			dto.setCidade(dtoCidade);
		}
		dto.setNomeContato(cliente.getNomeContato());
		dto.setFoneContato(cliente.getFoneContato());
		dto.setCellContato(cliente.getCellContato());
		dto.setDataNascimentoContato(cliente.getDataNascimentoContato());
		dto.setNomeProprietario(cliente.getNomeProprietario());
		dto.setDataNascimentoProprietario(cliente.getDataNascimentoProprietario());
		dto.setAtivo(cliente.isAtivo());
		
		return dto;
	}
	
	@Override
	public PagingLoadResult<DTOCliente> loadPagingList(PagingLoadConfig config) {
		List<DTOCliente> sublist = new ArrayList<DTOCliente>();
		List<Cliente> entities = (ArrayList<Cliente>) loadSubList(config.getOffset(), config.getLimit(), (String)config.get("query"));
		try {
			for (Cliente cliente : entities) {
				
				sublist.add(parseToDTOCliente(cliente));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (PagingLoadResult<DTOCliente>)new BasePagingLoadResult<DTOCliente>(sublist);
	}
	
	private List<Cliente> loadSubList(Integer offset, Integer limit, String query) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			if (query != null){
				clientes.addAll(daoCliente.loadSubList(offset, limit, query.toUpperCase()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

}