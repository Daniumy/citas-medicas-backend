package springangular.citasmedicas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import springangular.citasmedicas.mongoModel.DiagnosticoMongo;

@Repository
public interface DiagnosticoMongoRepository extends MongoRepository<DiagnosticoMongo, String> {

}
