package springangular.citasmedicas.entityDto;

import java.util.List;
import javax.validation.constraints.NotEmpty;

public class MedicoDTO extends UsuarioDTO {

  @NotEmpty
  private String numColegiado;

  private List<CitaDTO> citas;

  private List<PacienteChildDTO> pacientes;
  public MedicoDTO() {}

  public String getNumColegiado() {
    return numColegiado;
  }

  public void setNumColegiado(String numColegiado) {
    this.numColegiado = numColegiado;
  }

  public List<CitaDTO> getCitas() {
    return citas;
  }

  public void setCitas(List<CitaDTO> citas) {
    this.citas = citas;
  }

  public List<PacienteChildDTO> getPacientes() {
    return pacientes;
  }

  public void setPacientes(List<PacienteChildDTO> pacientes) {
    this.pacientes = pacientes;
  }

  @Override
  public String toString() {
    return "MedicoDTO{" +
            "numColegiado='" + numColegiado + '\'' +
            ", citas=" + citas +
            ", pacientes=" + pacientes +
            '}';
  }
}
