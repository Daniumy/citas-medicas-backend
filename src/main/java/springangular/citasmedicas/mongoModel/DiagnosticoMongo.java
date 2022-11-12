package springangular.citasmedicas.mongoModel;

import org.springframework.data.mongodb.core.mapping.Document;
import springangular.citasmedicas.model.Cita;

import javax.persistence.*;

@Document(collection = "diagnosticos")
public class DiagnosticoMongo {
    @Id
    private String _id;

    private String valoracionEspecialista;

    private String enfermedad;

    private CitaMongo cita;

    public DiagnosticoMongo() {
    }

    public DiagnosticoMongo(String id, String valoracionEspecialista, String enfermedad, CitaMongo cita) {
        super();
        this._id = id;
        this.valoracionEspecialista = valoracionEspecialista;
        this.enfermedad = enfermedad;
        this.cita = cita;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getValoracionEspecialista() {
        return valoracionEspecialista;
    }

    public void setValoracionEspecialista(String valoracionEspecialista) {
        this.valoracionEspecialista = valoracionEspecialista;
    }

    public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public CitaMongo getCita() {
		return cita;
	}

	public void setCita(CitaMongo cita) {
		this.cita = cita;
	}
}
