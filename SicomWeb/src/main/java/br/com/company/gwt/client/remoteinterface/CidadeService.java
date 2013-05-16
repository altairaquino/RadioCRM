package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.client.dto.DTOCidade;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/cidadeService")
public interface CidadeService extends RemoteService {

	List<DTOCidade> listAll();
	
	List<DTOCidade> listCidadesByUF(String nome, String uf);
	
}