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
import springangular.citasmedicas.entityDto.CitaDTO;
import springangular.citasmedicas.mongoModel.CitaMongo;
import springangular.citasmedicas.service.CitaService;

@RestController
@CrossOrigin
public class CitaController {
  @Autowired
  private CitaService citaService;


  @GetMapping("/citas")
  public List<CitaDTO> getAll() {
    return citaService.getAll();
  }

  @GetMapping("/citas/{id}")
  public CitaDTO getById(@PathVariable Long id) {
    return citaService.getById(id);
  }

  @PostMapping("/citas")
  public CitaDTO add(@Validated @RequestBody CitaDTO newCita) {
    System.out.println("newCita es: " + newCita);
    citaService.add(newCita);
    return newCita;
  }

  // Replace entire cita
  @PutMapping("/citas")
  public CitaDTO update(@Validated @RequestBody CitaDTO newCita) {
    citaService.update(newCita);
    return newCita;
  }

  @DeleteMapping("/citas/{id}")
  public Long delete(@PathVariable Long id) {
    citaService.delete(id);
    return id;
  }


}
