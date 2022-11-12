package springangular.citasmedicas.mongoModel;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Document(collection = "pacientes")
public class PacienteMongo extends UsuarioMongo {
  private String nSS;

  private String numTarjeta;

  private String telefono;

  private String direccion;

 private List<MedicoMongo> medicos;

  private List<CitaMongo> citas;

  public PacienteMongo() {
  }

  public PacienteMongo(String _id, String nombre, String apellidos, String usuario, String clave, String NSS, String numTarjeta, String telefono,
                       String direccion, List<MedicoMongo> medicos, List<CitaMongo> citas) {
    super(_id, nombre, apellidos, usuario, clave);
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

  public List<MedicoMongo> getMedicos() {
    return medicos;
  }

  public void setMedicos(List<MedicoMongo> medicos) {
    this.medicos = medicos;
  }

  public List<CitaMongo> getCitas() {
    return citas;
  }

  public void setCitas(List<CitaMongo> citas) {
    this.citas = citas;
  }
}
