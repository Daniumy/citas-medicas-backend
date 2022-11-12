package springangular.citasmedicas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springangular.citasmedicas.entityDto.MedicoDTO;
import springangular.citasmedicas.entityDto.PacienteChildDTO;
import springangular.citasmedicas.exception.BadRequestException;
import springangular.citasmedicas.exception.NotFoundException;
import springangular.citasmedicas.mapper.MedicoMapper;
import springangular.citasmedicas.model.Cita;
import springangular.citasmedicas.model.Medico;
import springangular.citasmedicas.model.Paciente;
import springangular.citasmedicas.rabbitmq.Publisher;
import springangular.citasmedicas.repository.CitaRepository;
import springangular.citasmedicas.repository.MedicoRepository;
import springangular.citasmedicas.repository.PacienteRepository;

@Service
public class MedicoService {
  @Autowired
  MedicoRepository medicoRepository;

  @Autowired
  CitaRepository citaRepository;

  @Autowired
  PacienteRepository pacienteRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private Publisher publisher;


  public List<MedicoDTO> getAll() {
    List<MedicoDTO> medicosDTO = new ArrayList<>();
    medicoRepository.findAll().forEach(medico -> {
      medicosDTO.add(MedicoMapper.INSTANCE.medicoToMedicoDTO(medico));
    });
    return medicosDTO;
  }

  public MedicoDTO getById(Long id) {
    Optional<Medico> medico = medicoRepository.findById(id);
    if (medico.isEmpty()) {
      throw new NotFoundException("Medico no encontrado" + id);
    }
    return MedicoMapper.INSTANCE.medicoToMedicoDTO(medico.get());
  }

  public Optional<MedicoDTO> findByUsuario(String usuario) {
    Optional<Medico> medico = medicoRepository.findByUsuario(usuario);
    if (medico.isEmpty()) {
      throw new NotFoundException("Medico no encontrado" + usuario);
    }
    return Optional.of(MedicoMapper.INSTANCE.medicoToMedicoDTO(medico.get()));
  }

  public void add(MedicoDTO newMedico) {

    // encriptar pass
    newMedico.setClave(passwordEncoder.encode(newMedico.getClave()));

    Medico medico = new Medico(null, newMedico.getNombre(), newMedico.getApellidos(), newMedico.getUsuario(), newMedico.getClave(), newMedico.getNumColegiado(), new ArrayList<>(),new ArrayList<>());

    if (newMedico.getPacientes() != null) {
      List<Paciente> pacientes = new ArrayList<>();
      for (PacienteChildDTO pacienteDTO : newMedico.getPacientes()) {
        if (pacienteDTO.getId() != null) {
          Optional<Paciente> paciente = pacienteRepository.findById(pacienteDTO.getId());
          if (paciente.isPresent()) {
            pacientes.add(paciente.get());
          }
        }
      }
      medico.setPacientes(pacientes);
    }

    newMedico.setId(medicoRepository.save(medico).getId());
  }

  public void delete(Long id) {
    if (!medicoRepository.existsById(id)) {
      throw new NotFoundException("Medico no encontrado" + id);
    }
    medicoRepository.deleteById(id);
  }

  public void update(MedicoDTO newMedico) {
    if (newMedico.getId() == null) {
      throw new BadRequestException("El id del medico no puede ser nulo");
    }
    Optional<Medico> medico = medicoRepository.findById(newMedico.getId());
    if (medico.isEmpty()) {
      throw new NotFoundException("Medico no encontrado" + newMedico.getId());
    }

    Medico medicoUpdated = new Medico(newMedico.getId(), newMedico.getNombre(), newMedico.getApellidos(), newMedico.getUsuario(), newMedico.getClave(), newMedico.getNumColegiado(), new ArrayList<>(), new ArrayList<>());

    if (newMedico.getPacientes() != null) {
      List<Paciente> pacientes = new ArrayList<>();
      for (PacienteChildDTO pacienteDTO : newMedico.getPacientes()) {
        if (pacienteDTO.getId() != null) {
          Optional<Paciente> paciente = pacienteRepository.findById(pacienteDTO.getId());
          paciente.ifPresent(pacientes::add);
        }
      }

      medicoUpdated.setPacientes(pacientes);
    }
    if (newMedico.getCitas() != null) {
      List<Cita> citas = new ArrayList<>();
      newMedico.getCitas().forEach(citaDTO -> {
        Optional<Cita> cita = citaRepository.findById(citaDTO.getId());
        if (cita.isPresent()) {
          citas.add(cita.get());
        }
      });
      medicoUpdated.setCitas(citas);
    }
    newMedico.setId(medicoRepository.save(medicoUpdated).getId());
  }
}
