package springangular.citasmedicas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CITAS")
public class Cita {
	@Id
	@SequenceGenerator(name = "CITA_ID_SEQ", sequenceName = "CITA_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CITA_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MOTIVO_CITA", nullable = false)
    private String motivoCita;

    @ManyToOne
    @JoinColumn(name= "MEDICO_ID")
    private Medico medico;

    @OneToOne(mappedBy = "cita")
    private Diagnostico diagnostico;

    @Column(name = "FECHA_HORA", nullable = false)
    private Date fechaHora;

    @ManyToOne
    @JoinColumn(name= "PACIENTE_ID")
    private Paciente paciente;

    public Cita() {
    }

    public Cita(Long iD,String motivoCita, Medico medico, Diagnostico diagnostico, Date fechaHora, Paciente paciente) {
        super();
        id = iD;
        this.motivoCita = motivoCita;
        this.diagnostico = diagnostico;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
    }
	public String getMotivoCita() {
		return motivoCita;
	}

	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long iD) {
		id = iD;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

  public Date getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(Date fechaHora) {
    this.fechaHora = fechaHora;
  }

  public Medico getMedico() {
    return medico;
  }

  public void setMedico(Medico medico) {
    this.medico = medico;
  }

  public Paciente getPaciente() {
    return paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }
}
