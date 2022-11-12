package springangular.citasmedicas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springangular.citasmedicas.entityDto.DiagnosticoChildDTO;
import springangular.citasmedicas.entityDto.DiagnosticoDTO;
import springangular.citasmedicas.model.Diagnostico;
import springangular.citasmedicas.mongoModel.DiagnosticoMongo;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {
  DiagnosticoMapper INSTANCE = Mappers.getMapper(DiagnosticoMapper.class);

  DiagnosticoDTO diagnosticoToDiagnosticoDTO(Diagnostico diagnostico);

  DiagnosticoChildDTO diagnosticoToDiagnosticoChildDTO(Diagnostico diagnostico);

  DiagnosticoDTO diagnosticoMongoToDiagnosticoDTO(DiagnosticoMongo diagnostico);

  DiagnosticoChildDTO diagnosticoMongoToDiagnosticoChildDTO(DiagnosticoMongo diagnostico);

}
