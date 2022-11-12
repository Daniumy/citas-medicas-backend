package springangular.citasmedicas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springangular.citasmedicas.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
  public Optional<Paciente> findByUsuario(String usuario);
}
