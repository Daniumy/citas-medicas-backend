package springangular.citasmedicas.entityDto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UsuarioDTO implements Serializable {
  private Long id;

  @NotEmpty
  private String nombre;

  @NotEmpty
  private String apellidos;

  @NotEmpty
  private String usuario;

  @NotEmpty
  private String clave;

  public UsuarioDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }
}
