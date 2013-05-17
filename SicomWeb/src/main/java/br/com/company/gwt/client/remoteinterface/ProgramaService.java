package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOPrograma;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/programaService")
public interface ProgramaService extends RemoteService {

	List<DTOPrograma> listAll();

	List<DTOPrograma> pesquisa(String query);

	DTOPrograma salvar(DTOPrograma dtoPrograma) throws Exception;
	
}