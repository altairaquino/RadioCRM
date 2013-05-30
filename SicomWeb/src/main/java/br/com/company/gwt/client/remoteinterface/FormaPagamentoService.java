package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOFormaPagamento;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/formaPagamentoService")
public interface FormaPagamentoService extends RemoteService {

	List<DTOFormaPagamento> listAll();
	
}