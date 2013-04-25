/**
 * 
 */
package br.com.company.gwt.client.remoteinterface;

import java.util.List;

import br.com.company.gwt.shared.dto.DTOOperacao;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OperacaoServiceAsync {
	
	void listaOperacaoUsuario(Integer usuarioId, AsyncCallback<List<DTOOperacao>> callback);
	void listaOperacoes(AsyncCallback<List<DTOOperacao>> callback);
	void removeOperacao(Integer usuarioId, Integer operacaoId,	AsyncCallback<Boolean> callback);
	void adicionaOperacao(Integer usuarioId, Integer operacaoId, AsyncCallback<Boolean> callback);
	
}