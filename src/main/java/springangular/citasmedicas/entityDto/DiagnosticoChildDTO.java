package springangular.citasmedicas.entityDto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class DiagnosticoChildDTO implements Serializable {
	private Long id;
	
	@NotEmpty
	private String valoracionEspecialista;
	
	@NotEmpty
	private String enfermedad;
	
	public DiagnosticoChildDTO() {}

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
}
