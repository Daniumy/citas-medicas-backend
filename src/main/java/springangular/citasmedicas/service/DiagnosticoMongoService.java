package springangular.citasmedicas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springangular.citasmedicas.entityDto.DiagnosticoDTO;
import springangular.citasmedicas.exception.BadRequestException;
import springangular.citasmedicas.exception.NotFoundException;
import springangular.citasmedicas.mapper.CitaMapper;
import springangular.citasmedicas.mongoModel.CitaMongo;
import springangular.citasmedicas.mongoModel.DiagnosticoMongo;
import springangular.citasmedicas.rabbitmq.Publisher;
import springangular.citasmedicas.repository.DiagnosticoMongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoMongoService {

  @Autowired
  DiagnosticoMongoRepository diagnosticoMongoRepository;

  @Autowired
  Publisher publisher;

  public List<DiagnosticoMongo> getAll() {
    List<DiagnosticoMongo> diagnosticosMongo = new ArrayList<DiagnosticoMongo>();
    diagnosticoMongoRepository.findAll().forEach(diagnostico -> {
      diagnosticosMongo.add(diagnostico);
    });

    return diagnosticosMongo;
  }

  public DiagnosticoMongo getById(String id) {
    Optional<DiagnosticoMongo> diagnostico = diagnosticoMongoRepository.findById(id);
    if (diagnostico.isEmpty()) {
      throw new NotFoundException("Diagnostico not found: " + id);
    }
    return diagnostico.get();
  }

  public void delete(String id) {
    if (!diagnosticoMongoRepository.existsById(id)) {
      throw new NotFoundException("Diagnostico not found : " + id);
    }
    diagnosticoMongoRepository.deleteById(id);
  }

  public void add (DiagnosticoDTO newDiagnostico) {
    DiagnosticoMongo diagnostico = new DiagnosticoMongo(null,newDiagnostico.getValoracionEspecialista(),newDiagnostico.getEnfermedad(),CitaMapper.INSTANCE.citaDtoToCitaMongo(newDiagnostico.getCita()));

    newDiagnostico.setId(Long.parseLong(diagnosticoMongoRepository.save(diagnostico).getId()));
    /*newDiagnostico.setCita(CitaMapper.INSTANCE.citaMongoToCitaDto(cita.get()));*/
  }

  public void update(DiagnosticoDTO newDiagnostico) {
    if (newDiagnostico.getId() == null) {
      throw new BadRequestException("Diagnostico id is null: " + newDiagnostico.getId());
    }

    if (diagnosticoMongoRepository.findById(Long.toString(newDiagnostico.getId())).isEmpty()) {
      throw new NotFoundException("Diagnostico not found: " + newDiagnostico.getId());
    }

    DiagnosticoMongo diagnostico = new DiagnosticoMongo(null, newDiagnostico.getValoracionEspecialista(), newDiagnostico.getEnfermedad(), CitaMapper.INSTANCE.citaDtoToCitaMongo(newDiagnostico.getCita()) );

    diagnosticoMongoRepository.save(diagnostico);

    /*newDiagnostico.setCita(CitaMapper.INSTANCE.citaMongoToCitaDto(cita.get()));*/

    /*if (cita.isPresent()) {
      cita.get().setDiagnostico(diagnostico);
      citaMongoRepository.save(cita.get());
    }*/
  }

}
