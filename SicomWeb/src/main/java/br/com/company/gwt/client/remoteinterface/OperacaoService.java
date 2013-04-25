package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOOperacao;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/operacaoService")
public interface OperacaoService extends RemoteService{
	
	List<DTOOperacao> listaOperacaoUsuario(Integer usuarioId) throws Exception;

	List<DTOOperacao> listaOperacoes();
	
	Boolean removeOperacao(Integer usuarioId, Integer operacaoId);
	
	Boolean adicionaOperacao(Integer usuarioId, Integer operacaoId);
	
}