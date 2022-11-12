package springangular.citasmedicas.model;

import javax.persistence.*;


@Entity
@Table(name = "USUARIOS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
  @Id
  @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
  @Column(name = "ID")
  private Long id;

  @Column(name = "NOMBRE", nullable = false)
  private String nombre;

  @Column(name = "APELLIDOS", nullable = false)
  private String apellidos;

  @Column(name = "USUARIO", nullable = false)
  private String usuario;

  @Column(name = "CLAVE", nullable = false)
  private String clave;

  public Usuario() {
  }

  public Usuario(Long id, String nombre, String apellidos, String usuario, String clave) {
    this.id = id;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.usuario = usuario;
    this.clave = clave;
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
