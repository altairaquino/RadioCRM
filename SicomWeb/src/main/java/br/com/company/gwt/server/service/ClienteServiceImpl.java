package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.ClienteService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.legacy.bean.BeanCliente;
import br.com.company.gwt.server.legacy.model.ModelCliente;
import br.com.company.gwt.shared.dto.DTOCliente;

@Named("clienteService")
public class ClienteServiceImpl extends InputServletImpl implements ClienteService{
	
	@Inject private ModelCliente modelCliente;

	@Override
	public List<DTOCliente> listAll() {
		List<DTOCliente> clientes = new ArrayList<DTOCliente>();
		try {
			for (BeanCliente bean : modelCliente.getClientes(1)) {
				DTOCliente cliente = new DTOCliente();
				cliente.setId(Integer.parseInt(bean.getClncodg()));
				cliente.setNome(bean.getClcnome());
				cliente.setDocumento(bean.getClcdocm());
				clientes.add(cliente);
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
			for (BeanCliente bean : modelCliente.getClientesPorNomeDoc(query, 1) ) {
				DTOCliente cliente = new DTOCliente();
				cliente.setId(Integer.parseInt(bean.getClncodg()));
				cliente.setNome(bean.getClcnome());
				cliente.setDocumento(bean.getClcdocm());
				clientes.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

}