package springangular.citasmedicas.mapper;

import org.mapstruct.Mapper;
import springangular.citasmedicas.entityDto.CitaDTO;
import springangular.citasmedicas.model.Cita;
import springangular.citasmedicas.mongoModel.CitaMongo;

@Mapper(componentModel = "spring")
public interface CitaMapper {
  CitaMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(CitaMapper.class);
  CitaDTO citaToCitaDto(Cita cita);

  CitaDTO citaMongoToCitaDto(CitaMongo cita);

  CitaMongo citaDtoToCitaMongo(CitaDTO cita);
}
