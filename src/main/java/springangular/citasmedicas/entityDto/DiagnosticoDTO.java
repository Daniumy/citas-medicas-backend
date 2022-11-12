package springangular.citasmedicas.entityDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DiagnosticoDTO implements Serializable {
	private Long id;
	
	@NotEmpty
	private String valoracionEspecialista;
	
	@NotEmpty
	private String enfermedad;
	
	@NotNull
	private CitaDTO cita;

	public DiagnosticoDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CitaDTO getCita() {
		return cita;
	}

	public void setCita(CitaDTO cita) {
		this.cita = cita;
	}

	@Override
	public String toString() {
		return "DiagnosticoDTO{" +
				"id=" + id +
				", valoracionEspecialista='" + valoracionEspecialista + '\'' +
				", enfermedad='" + enfermedad + '\'' +
				", cita=" + cita +
				'}';
	}
}
