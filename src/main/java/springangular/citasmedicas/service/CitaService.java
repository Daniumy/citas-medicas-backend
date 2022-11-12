package springangular.citasmedicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springangular.citasmedicas.entityDto.CitaDTO;
import springangular.citasmedicas.exception.BadRequestException;
import springangular.citasmedicas.mapper.CitaMapper;
import springangular.citasmedicas.mapper.DiagnosticoMapper;
import springangular.citasmedicas.mapper.MedicoMapper;
import springangular.citasmedicas.mapper.PacienteMapper;
import springangular.citasmedicas.model.Cita;
import springangular.citasmedicas.model.Diagnostico;
import springangular.citasmedicas.model.Medico;
import springangular.citasmedicas.model.Paciente;
import springangular.citasmedicas.rabbitmq.Publisher;
import springangular.citasmedicas.repository.CitaRepository;
import springangular.citasmedicas.repository.DiagnosticoRepository;
import springangular.citasmedicas.exception.NotFoundException;
import springangular.citasmedicas.repository.MedicoRepository;
import springangular.citasmedicas.repository.PacienteRepository;

@Service
public class CitaService {

  @Autowired
  CitaRepository citaRepository;

  @Autowired
  MedicoRepository medicoRepository;

  @Autowired
  private DiagnosticoRepository diagnosticoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Autowired
  private Publisher publisher;

  public List<CitaDTO> getAll() {
    List<CitaDTO> citasDto = new ArrayList<>();
    citaRepository.findAll().forEach(cita -> {
      CitaDTO citaDTO = CitaMapper.INSTANCE.citaToCitaDto(cita);
      citasDto.add(citaDTO);
    });
    return citasDto;
  }

  public CitaDTO getById(Long id) {
    Optional<Cita> cita = citaRepository.findById(id);
    if (cita.isPresent()) {
      return CitaMapper.INSTANCE.citaToCitaDto(cita.get());
    }
    throw new NotFoundException("Medico not found: " +id);
  }

  public void add(CitaDTO newCita) {
      Optional<Diagnostico> diagnostico = Optional.empty();
      Optional<Medico> medico = medicoRepository.findById(newCita.getMedico().getId());
      Optional<Paciente> paciente = pacienteRepository.findById(newCita.getPaciente().getId());

      if (medico.isEmpty()) {
        throw new NotFoundException("Medico not found: " + newCita.getMedico().getId());
      }

      if(paciente.isEmpty()) {
        throw new NotFoundException("Paciente not found: " + newCita.getPaciente().getId());
      }


      if (newCita.getDiagnostico() != null) {
        System.out.println("newCita.getDiagnostico() no es null es: " + newCita.getDiagnostico());
        diagnostico = diagnosticoRepository.findById(newCita.getDiagnostico().getId());
      }

      Cita cita = new Cita(null, newCita.getMotivoCita(),medico.get(), diagnostico.orElse(null), newCita.getFechaHora(), paciente.get() );

      // add the new appointment and update the ID for the return data
      newCita.setId(citaRepository.save(cita).getId());
      newCita.setMedico(MedicoMapper.INSTANCE.medicoToMedicoChildDTO(medico.get()));
      newCita.setPaciente(PacienteMapper.INSTANCE.pacienteToPacienteChildDTO(paciente.get()));

      citaRepository.save(cita);

      // update diagnostico
      if (diagnostico.isPresent()) {
        System.out.println("diagnostico.isPresent() es true");
        diagnostico.get().setCita(cita);
        diagnosticoRepository.save(diagnostico.get());
      }

  }

  public void delete(Long id) {
    if (!citaRepository.existsById(id)) {
      throw new NotFoundException("Cita not found : " + id);
    }

    citaRepository.deleteById(id);
  }

  public void update(CitaDTO newCita) {
    if (newCita.getId() == null) {
      throw new BadRequestException("Id cant be null : " + newCita.getId());
    }
    if (citaRepository.findById(newCita.getId()).isEmpty()) {
      throw new NotFoundException("Cita not found : " + newCita.getId());
    }

    Optional<Medico> medico = medicoRepository.findById(newCita.getMedico().getId());
    Optional<Paciente> paciente = pacienteRepository.findById(newCita.getPaciente().getId());


    if(paciente.isEmpty()) {
      throw new NotFoundException("Paciente not found: " + newCita.getPaciente().getId());
    }

    if (medico.isEmpty()) {
      throw new NotFoundException("Medico not found: " + newCita.getMedico().getId());
    }


    Optional<Diagnostico> diagnostico = Optional.empty();
    if (newCita.getDiagnostico() != null) {
      diagnostico = diagnosticoRepository.findById(newCita.getDiagnostico().getId());
    }

    Cita cita = new Cita(newCita.getId(), newCita.getMotivoCita(),medico.get(), diagnostico.orElse(null), newCita.getFechaHora(), paciente.get());

    citaRepository.save(cita);

    if (diagnostico.isPresent()) {
      diagnostico.get().setCita(cita);
      diagnosticoRepository.save(diagnostico.get());
      newCita.setDiagnostico(DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoChildDTO(diagnostico.get()));
    }

    newCita.setMedico(MedicoMapper.INSTANCE.medicoToMedicoChildDTO(medico.get()));
    newCita.setPaciente(PacienteMapper.INSTANCE.pacienteToPacienteChildDTO(paciente.get()));
  }
}

