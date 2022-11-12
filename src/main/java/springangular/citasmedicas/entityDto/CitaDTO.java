package springangular.citasmedicas.entityDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class CitaDTO implements Serializable {
  private Long id;

  @NotEmpty
  private String motivoCita;

  @NotNull
  private MedicoChildDTO medico;
  private DiagnosticoChildDTO diagnostico;

  @NotNull
  private PacienteChildDTO paciente;

  @NotNull
  private Date fechaHora;
  public CitaDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMotivoCita() {
    return motivoCita;
  }

  public void setMotivoCita(String motivoCita) {
    this.motivoCita = motivoCita;
  }

  public DiagnosticoChildDTO getDiagnostico() {
    return diagnostico;
  }

  public void setDiagnostico(DiagnosticoChildDTO diagnostico) {
    this.diagnostico = diagnostico;
  }

  public Date getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(Date fechaHora) {
    this.fechaHora = fechaHora;
  }

  public MedicoChildDTO getMedico() {
    return medico;
  }

  public void setMedico(MedicoChildDTO medico) {
    this.medico = medico;
  }

  public PacienteChildDTO getPaciente() {
    return paciente;
  }

  public void setPaciente(PacienteChildDTO paciente) {
    this.paciente = paciente;
  }

  @Override
  public String toString() {
    return "CitaDTO{" +
            "id=" + id +
            ", motivoCita='" + motivoCita + '\'' +
            ", medico=" + medico +
            ", diagnostico=" + diagnostico +
            ", paciente=" + paciente +
            ", fechaHora=" + fechaHora +
            '}';
  }
}
