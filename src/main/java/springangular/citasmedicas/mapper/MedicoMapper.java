package springangular.citasmedicas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springangular.citasmedicas.entityDto.MedicoChildDTO;
import springangular.citasmedicas.entityDto.MedicoDTO;
import springangular.citasmedicas.model.Medico;
import springangular.citasmedicas.mongoModel.MedicoMongo;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
  MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);
  MedicoDTO medicoToMedicoDTO(Medico medico);

  MedicoChildDTO medicoToMedicoChildDTO(Medico medico);
}
