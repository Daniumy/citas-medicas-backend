package springangular.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springangular.citasmedicas.model.Diagnostico;


@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

}
