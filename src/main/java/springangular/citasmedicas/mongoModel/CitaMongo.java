package springangular.citasmedicas.mongoModel;

import org.springframework.data.mongodb.core.mapping.Document;
import springangular.citasmedicas.model.Diagnostico;

import javax.persistence.*;
import java.util.Date;

@Document(collection = "citas")
public class CitaMongo {

	@Id
    private String _id;

    private String motivoCita;

    private MedicoMongo medico;

    private DiagnosticoMongo diagnostico;

    private Date fechaHora;

    private PacienteMongo paciente;

    public CitaMongo() {
    }

    public CitaMongo(String iD, String motivoCita, MedicoMongo medico, DiagnosticoMongo diagnostico, Date fechaHora, PacienteMongo paciente) {
        super();
        _id = iD;
        this.motivoCita = motivoCita;
        this.diagnostico = diagnostico;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
    }
	public String getMotivoCita() {
		return motivoCita;
	}

	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}

	public String getId() {
		return _id;
	}

	public void setId(String iD) {
        _id = iD;
	}

	public DiagnosticoMongo getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(DiagnosticoMongo diagnostico) {
		this.diagnostico = diagnostico;
	}

  public Date getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(Date fechaHora) {
    this.fechaHora = fechaHora;
  }

  public MedicoMongo getMedico() {
    return medico;
  }

  public void setMedico(MedicoMongo medico) {
    this.medico = medico;
  }

  public PacienteMongo getPaciente() {
    return paciente;
  }

  public void setPaciente(PacienteMongo paciente) {
    this.paciente = paciente;
  }
}
