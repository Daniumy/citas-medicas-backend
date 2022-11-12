package springangular.citasmedicas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springangular.citasmedicas.entityDto.UsuarioDTO;
import springangular.citasmedicas.model.Usuario;
import springangular.citasmedicas.mongoModel.UsuarioMongo;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

	UsuarioDTO usuarioMongoToUsuarioDTO(UsuarioMongo usuario);
}
