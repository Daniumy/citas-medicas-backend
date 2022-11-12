package springangular.citasmedicas.mongoModel;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@Document(collection = "usuarios")
public class UsuarioMongo {
  @Id
  private String _id;

  private String nombre;

  private String apellidos;

  private String usuario;

  private String clave;

  public UsuarioMongo() {
  }

  public UsuarioMongo(String id, String nombre, String apellidos, String usuario, String clave) {
    this._id = id;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.usuario = usuario;
    this.clave = clave;
  }

  public String getId() {
    return _id;
  }

  public void setId(String id) {
    this._id = id;
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
