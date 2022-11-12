package springangular.citasmedicas.entityDto;


import javax.validation.constraints.NotEmpty;

public class MedicoChildDTO extends UsuarioDTO {
  @NotEmpty
  private String numColegiado;

  public MedicoChildDTO() {}

  public String getNumColegiado() {
    return numColegiado;
  }

  public void setNumColegiado(String numColegiado) {
    this.numColegiado = numColegiado;
  }
}
