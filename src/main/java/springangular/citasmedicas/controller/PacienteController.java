package springangular.citasmedicas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springangular.citasmedicas.entityDto.PacienteDTO;
import springangular.citasmedicas.mongoModel.PacienteMongo;
import springangular.citasmedicas.service.PacienteService;

@RestController
public class PacienteController {
  @Autowired
  private PacienteService pacienteService;


  // Get all pacientes
  @GetMapping("/pacientes")
  public List<PacienteDTO> getAll() {
    return pacienteService.getAll();
  }

  // Get paciente by Id
  @GetMapping("/pacientes/{id}")
  public PacienteDTO getByID(@PathVariable Long id) {
    return pacienteService.getById(id);
  }

  // Add paciente
  @PostMapping("/pacientes")
  public PacienteDTO add(@Validated @RequestBody PacienteDTO newPaciente) {
    pacienteService.add(newPaciente);
    return newPaciente;
  }

  // Delete paciente by Id
  @DeleteMapping("/pacientes/{id}")
  public Long delete(@PathVariable Long id) {
    pacienteService.delete(id);

    return id;
  }

  // update paciente
  @PutMapping("/pacientes")
  public PacienteDTO replace(@Validated @RequestBody PacienteDTO newPaciente) {
    pacienteService.update(newPaciente);

    return newPaciente;
  }
}
