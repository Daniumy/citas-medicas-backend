package springangular.citasmedicas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springangular.citasmedicas.entityDto.MedicoDTO;
import springangular.citasmedicas.mongoModel.MedicoMongo;
import springangular.citasmedicas.service.CitaService;
import springangular.citasmedicas.service.MedicoService;

@RestController
@CrossOrigin
public class MedicoController {
  @Autowired
  MedicoService medicoService;

  @Autowired
  CitaService citaService;


  @GetMapping("/medicos")
  public List<MedicoDTO> getAll() {
    return medicoService.getAll();
  }

  @GetMapping("/medicos/{id}")
  public MedicoDTO getById(@PathVariable Long id) {
    return medicoService.getById(id);
  }

  @PostMapping("/medicos")
  public MedicoDTO add(@Validated @RequestBody MedicoDTO newMedico) {
    medicoService.add(newMedico);
    return newMedico;
  }

  @DeleteMapping("/medicos/{id}")
  public Long delete(@PathVariable Long id) {
    medicoService.delete(id);
    return id;
  }

  @PutMapping("/medicos")
  public MedicoDTO update(@Validated @RequestBody MedicoDTO newMedico) {
    medicoService.update(newMedico);
    return newMedico;
  }

}
