package springangular.citasmedicas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springangular.citasmedicas.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
  public Optional<Medico> findByUsuario(String usuario);

}
