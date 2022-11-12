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
import springangular.citasmedicas.entityDto.DiagnosticoDTO;
import springangular.citasmedicas.mongoModel.DiagnosticoMongo;
import springangular.citasmedicas.service.DiagnosticoMongoService;
import springangular.citasmedicas.service.DiagnosticoService;

@RestController
public class DiagnosticoController {
  @Autowired
  private DiagnosticoService diagnosticoService;

  @Autowired
  private DiagnosticoMongoService diagnosticoMongoService;

  @GetMapping("/diagnosticos")
  public List<DiagnosticoMongo> getAll() {
    return diagnosticoMongoService.getAll();
  }

  @GetMapping("/diagnosticos/{id}")
  public DiagnosticoMongo getById(@PathVariable String id) {
    return diagnosticoMongoService.getById(id);
  }

  @PostMapping("/diagnosticos")
  public DiagnosticoDTO add(@Validated @RequestBody DiagnosticoDTO newDiagnostico) {
    diagnosticoService.add(newDiagnostico);
    return newDiagnostico;
  }

  @DeleteMapping("/diagnosticos/{id}")
  public Long delete(@PathVariable Long id) {
    diagnosticoService.delete(id);
    return id;
  }

  @PutMapping("/diagnosticos")
  public DiagnosticoDTO update(@Validated @RequestBody DiagnosticoDTO newDiagnostico) {
    diagnosticoService.update(newDiagnostico);
    return newDiagnostico;
  }
}
