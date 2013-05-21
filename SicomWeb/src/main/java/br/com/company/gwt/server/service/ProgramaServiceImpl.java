package br.com.company.gwt.server.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.company.gwt.client.remoteinterface.ProgramaService;
import br.com.company.gwt.server.InputServletImpl;
import br.com.company.gwt.server.dao.DaoPrograma;
import br.com.company.gwt.server.dao.DaoProgramacaoPrograma;
import br.com.company.gwt.server.entities.Programa;
import br.com.company.gwt.server.entities.ProgramacaoPrograma;
import br.com.company.gwt.shared.dto.DTOPrograma;
import br.com.company.gwt.shared.dto.DTOProgramacao;
import br.com.company.gwt.shared.enums.DiaSemana;

@Named("programaService")
public class ProgramaServiceImpl extends InputServletImpl implements ProgramaService{
	
	@Inject private DaoPrograma daoPrograma;
	@Inject private DaoProgramacaoPrograma daoProgramacaoPrograma;
	
	private static DateFormat timeFormat;
	
	static{
		timeFormat = new SimpleDateFormat("kk:mm");
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
		List<DTOPrograma> programas = new ArrayList<DTOPrograma>();
		try {
			for (Programa programa : daoPrograma.getProgramaByNome(query)) {
				programas.add(parseToDTOPrograma(programa));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return programas;
	}
	
	private DTOPrograma parseToDTOPrograma(Programa programa){
		DTOPrograma dto = new DTOPrograma();
		dto.setId(programa.getId());
		dto.setNome(programa.getNome());
		dto.setValorPatrocinio(programa.getValorPatrocinio());
		dto.setAtivo(programa.isAtivo());
		
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

	@Transactional
	@Override
	public DTOPrograma salvar(DTOPrograma dtoPrograma) throws Exception {
		try {
			Programa programa = null;
			if (dtoPrograma.getId() == null){
				programa = new Programa();
			}else{
				programa = daoPrograma.findByPrimaryKey(dtoPrograma.getId());
			}
			
			programa.setId(dtoPrograma.getId());
			programa.setNome(dtoPrograma.getNome());
			programa.setValorPatrocinio(dtoPrograma.getValorPatrocinio());
			programa.setAtivo(dtoPrograma.getAtivo());
			programa.getProgramacao().clear();
			
			List<ProgramacaoPrograma> programacoes = new ArrayList<ProgramacaoPrograma>();
			for (DTOProgramacao dto : dtoPrograma.getProgramacao()) {
				ProgramacaoPrograma programacao = null;
				if (dto.getId() == null){
					programacao = new ProgramacaoPrograma();
					programacao.setPrograma(programa);
				}else{
					programacao = daoProgramacaoPrograma.findByPrimaryKey(dto.getId());
					programacoes.add(programacao);
					continue;
				}
				
				programacao.setDiaSemana(DiaSemana.valueOf(dto.getDiaSemana()));
				programacao.setHoraInicio(timeFormat.parse(dto.getHoraInicio()));
				programacao.setHoraTermino(timeFormat.parse(dto.getHoraTermino()));
				programacoes.add(programacao);
			}
			
			programa.setProgramacao(programacoes);
			daoPrograma.store(programa);
			
			dtoPrograma.setId(programa.getId());
			
		} catch (Exception e) {
			
		}
		return dtoPrograma;
	}

}