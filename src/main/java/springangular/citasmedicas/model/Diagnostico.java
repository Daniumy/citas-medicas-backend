package springangular.citasmedicas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity
@Table(name = "DIAGNOSTICOS")
public class Diagnostico {
    @Id
    @SequenceGenerator(name = "DIAGNOSTICO_ID_SEQ", sequenceName = "DIAGNOSTICO_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "DIAGNOSTICO_ID_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALORACION_ESPECIALISTA", nullable = false)
    private String valoracionEspecialista;

    @Column(name = "ENFERMEDAD", nullable = false)
    private String enfermedad;

    @OneToOne()
    @JoinColumn(name="CITA_ID")
    private Cita cita;

    public Diagnostico() {
    }

    public Diagnostico(Long id, String valoracionEspecialista, String enfermedad, Cita cita) {
        super();
        this.id = id;
        this.valoracionEspecialista = valoracionEspecialista;
        this.enfermedad = enfermedad;
        this.cita = cita;
    }

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

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}
}
