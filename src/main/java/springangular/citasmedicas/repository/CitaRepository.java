package springangular.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springangular.citasmedicas.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
