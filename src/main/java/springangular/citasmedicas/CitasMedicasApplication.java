package springangular.citasmedicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springangular.citasmedicas.repository.DiagnosticoMongoRepository;
import springangular.citasmedicas.repository.DiagnosticoRepository;

@EnableMongoRepositories/*(basePackageClasses = DiagnosticoMongoRepository.class)*/
@EnableJpaRepositories/*(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = DiagnosticoMongoRepository.class))*/
@SpringBootApplication
public class CitasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasMedicasApplication.class, args);
	}

}
