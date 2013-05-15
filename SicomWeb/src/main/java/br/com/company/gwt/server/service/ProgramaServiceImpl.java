package br.com.company.gwt.server.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.gwt.client.remoteinterface.ProgramaService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoPrograma;
import br.com.company.gwt.server.entities.Programa;
import br.com.company.gwt.server.entities.ProgramacaoPrograma;
import br.com.company.gwt.shared.dto.DTOPrograma;
import br.com.company.gwt.shared.dto.DTOProgramacao;

@Named("programaService")
public class ProgramaServiceImpl extends InputServletImpl implements ProgramaService{
	
	@Inject private DaoPrograma daoPrograma;
	
	private static DateFormat timeFormat;
	
	static{
		timeFormat = new SimpleDateFormat("hh:mm");
	}

	@Override
	public List<DTOPrograma> listAll() {
		List<DTOPrograma> programas = new ArrayList<DTOPrograma>();
		try {
			for (Programa programa : daoPrograma.loadAll()) {
				programas.add(parseToDTOPrograma(programa));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return programas;
	}
	
	@Override
	public List<DTOPrograma> pesquisa(String query) {
		List<DTOPrograma> clientes = new ArrayList<DTOPrograma>();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	private DTOPrograma parseToDTOPrograma(Programa programa){
		DTOPrograma dto = new DTOPrograma();
		dto.setId(programa.getId());
		dto.setNome(programa.getNome());
		dto.setValorPatrocinio(programa.getValorPatrocinio());
		
		ArrayList<DTOProgramacao> listProgramacao = new ArrayList<DTOProgramacao>();
		for (ProgramacaoPrograma programacao : programa.getProgramacao()) {
			DTOProgramacao dtoProgramacao = new DTOProgramacao();
			dtoProgramacao.setId(programacao.getId());
			dtoProgramacao.setDiaSemana(programacao.getDiaSemana().name());
			dtoProgramacao.setHoraInicio(timeFormat.format(programacao.getHoraInicio()));
			dtoProgramacao.setHoraTermino(timeFormat.format(programacao.getHoraTermino()));
			listProgramacao.add(dtoProgramacao);
		}
		
		dto.setProgramacao(listProgramacao);
		
		return dto;
	}

}