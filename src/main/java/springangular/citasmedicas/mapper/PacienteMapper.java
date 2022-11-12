package springangular.citasmedicas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springangular.citasmedicas.entityDto.PacienteChildDTO;
import springangular.citasmedicas.entityDto.PacienteDTO;
import springangular.citasmedicas.model.Paciente;
import springangular.citasmedicas.mongoModel.PacienteMongo;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
  PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

  PacienteDTO pacienteToPacienteDTO(Paciente paciente);

  PacienteChildDTO pacienteToPacienteChildDTO(Paciente paciente);

}

