package springangular.citasmedicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springangular.citasmedicas.entityDto.DiagnosticoDTO;
import springangular.citasmedicas.exception.BadRequestException;
import springangular.citasmedicas.exception.NotFoundException;
import springangular.citasmedicas.mapper.CitaMapper;
import springangular.citasmedicas.mapper.DiagnosticoMapper;
import springangular.citasmedicas.model.Cita;
import springangular.citasmedicas.model.Diagnostico;
import springangular.citasmedicas.mongoModel.DiagnosticoMongo;
import springangular.citasmedicas.rabbitmq.Publisher;
import springangular.citasmedicas.repository.CitaRepository;
import springangular.citasmedicas.repository.DiagnosticoMongoRepository;
import springangular.citasmedicas.repository.DiagnosticoRepository;

@Service
public class DiagnosticoService {
  @Autowired
  DiagnosticoRepository diagnosticoRepository;

  @Autowired
  DiagnosticoMongoRepository diagnosticoMongoRepository;

  @Autowired
  CitaRepository citaRepository;

  @Autowired
  Publisher publisher;

  public List<DiagnosticoDTO> getAll() {
    List<DiagnosticoDTO> diagnosticosDTO = new ArrayList<DiagnosticoDTO>();

    diagnosticoRepository.findAll().forEach(diagnostico -> {
      diagnosticosDTO.add(DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoDTO(diagnostico));
    });

    return diagnosticosDTO;
  }

  public DiagnosticoDTO getById(Long id) {
    Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(id);

    if (diagnostico.isEmpty()) {
      throw new NotFoundException("Diagnostico not found: " + id);
    }
    return DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoDTO(diagnostico.get());
  }

  public void delete(Long id) {
    if (!diagnosticoRepository.existsById(id)) {
      throw new NotFoundException("Diagnostico not found : " + id);
    }
    diagnosticoRepository.deleteById(id);
  }

  public void add (DiagnosticoDTO newDiagnostico) {
    Optional<Cita> cita = citaRepository.findById(newDiagnostico.getCita().getId());

    if (cita.isEmpty()) {
      throw new NotFoundException("Cita not found: " + newDiagnostico.getCita().getId());
    }

    Diagnostico diagnostico = new Diagnostico(null,newDiagnostico.getValoracionEspecialista(),newDiagnostico.getEnfermedad(),cita.get());

    newDiagnostico.setId(diagnosticoRepository.save(diagnostico).getId());
    newDiagnostico.setCita(CitaMapper.INSTANCE.citaToCitaDto(cita.get()));

    cita.get().setDiagnostico(diagnostico);
    citaRepository.save(cita.get());
    publisher.sendDiagnostico(newDiagnostico);
  }

  public void update(DiagnosticoDTO newDiagnostico) {
    if (newDiagnostico.getId() == null) {
      throw new BadRequestException("Diagnostico id is null: " + newDiagnostico.getId());
    }

    if (diagnosticoRepository.findById(newDiagnostico.getId()).isEmpty()) {
      throw new NotFoundException("Diagnostico not found: " + newDiagnostico.getId());
    }

    Optional<Cita> cita = citaRepository.findById(newDiagnostico.getCita().getId());

    Diagnostico diagnostico = new Diagnostico(null, newDiagnostico.getValoracionEspecialista(), newDiagnostico.getEnfermedad(), cita.orElse(null) );

    diagnosticoRepository.save(diagnostico);

    newDiagnostico.setCita(CitaMapper.INSTANCE.citaToCitaDto(cita.get()));

    if (cita.isPresent()) {
      cita.get().setDiagnostico(diagnostico);
      citaRepository.save(cita.get());
    }
  }

}
