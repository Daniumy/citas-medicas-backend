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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MEDICOS")
@PrimaryKeyJoinColumn(name = "ID")
public class Medico extends Usuario{
  @Column(name = "NUM_COLEGIADO", nullable = false)
  @NotEmpty
  private String numColegiado;

  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Cita> citas;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "PACIENTES_MEDICOS", joinColumns = @JoinColumn(name = "MEDICO_ID"), inverseJoinColumns = @JoinColumn(name = "PACIENTE_ID"))
  private List<Paciente> pacientes;

  public Medico() {}

  public Medico(Long id, String nombre, String apellidos, String usuario, String clave, String numColegiado, List<Cita> citas, List<Paciente> pacientes) {
    super(id, nombre, apellidos, usuario, clave);
    this.numColegiado = numColegiado;
    this.citas = citas;
    this.pacientes = pacientes;
  }

  public String getNumColegiado() {
    return numColegiado;
  }

  public void setNumColegiado(String numColegiado) {
    this.numColegiado = numColegiado;
  }

  public List<Cita> getCitas() {
    return citas;
  }

  public void setCitas(List<Cita> citas) {
    this.citas = citas;
  }

  public List<Paciente> getPacientes() {
    return pacientes;
  }

  public void setPacientes(List<Paciente> pacientes) {
    this.pacientes = pacientes;
  }
}
