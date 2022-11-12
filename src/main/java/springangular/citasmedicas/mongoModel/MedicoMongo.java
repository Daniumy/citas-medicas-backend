package springangular.citasmedicas.mongoModel;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Document(collection = "medicos")
public class MedicoMongo extends UsuarioMongo {

  private String numColegiado;

  private List<CitaMongo> citas;

   private List<PacienteMongo> pacientes;

  public MedicoMongo() {}

  public MedicoMongo(String _id, String nombre, String apellidos, String usuario, String clave, String numColegiado, List<CitaMongo> citas, List<PacienteMongo> pacientes) {
    super(_id, nombre, apellidos, usuario, clave);
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

  public List<CitaMongo> getCitas() {
    return citas;
  }

  public void setCitas(List<CitaMongo> citas) {
    this.citas = citas;
  }

  public List<PacienteMongo> getPacientes() {
    return pacientes;
  }

  public void setPacientes(List<PacienteMongo> pacientes) {
    this.pacientes = pacientes;
  }
}
