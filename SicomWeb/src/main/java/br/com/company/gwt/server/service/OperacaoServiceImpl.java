package br.com.company.gwt.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.OperacaoService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.legacy.bean.BeanOperacao;
import br.com.company.gwt.server.legacy.model.ModelOperacao;
import br.com.company.gwt.shared.dto.DTOOperacao;

@Named("operacaoService")
public class OperacaoServiceImpl extends InputServletImpl implements OperacaoService{
	
	@Inject private ModelOperacao modelOperacao;

	@Override
	public List<DTOOperacao> listaOperacaoUsuario(Integer usuarioId) throws Exception {
		List<DTOOperacao> operacoes = new ArrayList<DTOOperacao>();
		for(BeanOperacao operacao : modelOperacao.getOperacoesPaiDoUsuario(usuarioId)){
			DTOOperacao dto = new DTOOperacao();
			dto.setOperacaoId(Integer.parseInt(operacao.getOpncodg()));
			dto.setDescricao(operacao.getOpcdesc());
			operacoes.add(dto);
		}
		return operacoes;
	}

	@Override
	public List<DTOOperacao> listaOperacoes() {
		List<DTOOperacao> operacoes = new ArrayList<DTOOperacao>();
		for(BeanOperacao operacao : modelOperacao.getOperacoes()){
			DTOOperacao dto = new DTOOperacao();
			dto.setOperacaoId(Integer.parseInt(operacao.getOpncodg()));
			dto.setDescricao(operacao.getOpcdesc());
			operacoes.add(dto);
		}
		return operacoes;
	}

	@Override
	public Boolean removeOperacao(Integer usuarioId, Integer operacaoId) {
		modelOperacao.removeOperacao(usuarioId, operacaoId);
		return Boolean.TRUE;
	}

	@Override
	public Boolean adicionaOperacao(Integer usuarioId, Integer operacaoId) {
		modelOperacao.adicionaOperacao(usuarioId, operacaoId);
		return Boolean.TRUE;
	}

}