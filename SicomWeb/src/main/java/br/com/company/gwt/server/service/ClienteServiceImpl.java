package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.ClienteService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoAgencia;
import br.com.company.gwt.server.dao.DaoCidade;
import br.com.company.gwt.server.dao.DaoCliente;
import br.com.company.gwt.server.dao.DaoTipoLogradouro;
import br.com.company.gwt.server.entities.Cliente;
import br.com.company.gwt.server.entities.TipoPessoa;
import br.com.company.gwt.shared.dto.DTOCliente;

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
	
	@Override
	public DTOCliente salvar(DTOCliente dtoCliente) throws Exception{
		try {
			Cliente cliente = null;
			if (dtoCliente.getId() == null) {
				cliente = new Cliente();
			}else{
				cliente = daoCliente.findByPrimaryKey(dtoCliente.getId());
			}
			
			cliente.setNome(dtoCliente.getNome());
			cliente.setRazaoSocial(dtoCliente.getRazaoSocial());
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
		}
		
		return dtoCliente;
	}
	
	private DTOCliente parseToDTOCliente(Cliente cliente){
		DTOCliente dto = new DTOCliente();
		dto.setId(cliente.getId());
		dto.setNome(cliente.getNome());
		dto.setDocumento(cliente.getDocumento());
		return dto;
	}

}