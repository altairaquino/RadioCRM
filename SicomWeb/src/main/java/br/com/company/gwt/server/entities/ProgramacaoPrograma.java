package br.com.company.gwt.server.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.company.gwt.shared.enums.DiaSemana;

@Entity
@Table(name="programacao_programa")
public class ProgramacaoPrograma {

	@Id
	@SequenceGenerator(name = "seq", sequenceName = "sq_programacaoprograma_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Programa programa;
	
	@Column(name="dia_semana", nullable=false)
	@Enumerated(EnumType.STRING)
	private DiaSemana diaSemana;
	
	@Column(name="hora_inicio", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Column(name="hora_termino", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date horaTermino;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}

}