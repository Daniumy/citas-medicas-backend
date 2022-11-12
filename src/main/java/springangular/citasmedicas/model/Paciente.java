package springangular.citasmedicas.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PACIENTES")
@PrimaryKeyJoinColumn(name = "ID")
public class Paciente extends Usuario{
  @Column(name = "NSS", nullable = false)
  private String nSS;

  @Column(name = "NUM_TARJETA", nullable = false)
  private String numTarjeta;

  @Column(name = "TELEFONO", nullable = false)
  private String telefono;

  @Column(name = "DIRECCION", nullable = false)
  private String direccion;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "PACIENTES_MEDICOS", joinColumns = @JoinColumn(name = "PACIENTE_ID"), inverseJoinColumns = @JoinColumn(name = "MEDICO_ID"))
  private List<Medico> medicos;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Cita> citas;

  public Paciente() {
  }

  public Paciente(Long id, String nombre, String apellidos, String usuario, String clave, String NSS, String numTarjeta, String telefono,
      String direccion, List<Medico> medicos, List<Cita> citas) {
    super(id, nombre, apellidos, usuario, clave);
    this.nSS = NSS;
    this.numTarjeta = numTarjeta;
    this.telefono = telefono;
    this.direccion = direccion;
    this.medicos = medicos;
    this.citas = citas;
  }

  public String getnSS() {
    return nSS;
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

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public List<Medico> getMedicos() {
    return medicos;
  }

  public void setMedicos(List<Medico> medicos) {
    this.medicos = medicos;
  }

  public List<Cita> getCitas() {
    return citas;
  }

  public void setCitas(List<Cita> citas) {
    this.citas = citas;
  }
}
