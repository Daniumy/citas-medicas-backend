package springangular.citasmedicas.entityDto;

import javax.validation.constraints.NotEmpty;

public class PacienteChildDTO extends UsuarioDTO{
  @NotEmpty
  private String nSS;

  @NotEmpty
  private String numTarjeta;

  @NotEmpty
  private String telefono;

  @NotEmpty
  private String direccion;

  public PacienteChildDTO() {}

  public String getnSS() {
    return this.nSS;
  }

  public void setnSS(String nSS) {
    this.nSS = nSS;
  }

  public String getNumTarjeta() {
    return numTarjeta;
  }

  public void setNumTarjeta(String numTarjeta) {
    this.numTarjeta = numTarjeta;
  }

  public String getTelefono() {
    return telefono;
  }

}
