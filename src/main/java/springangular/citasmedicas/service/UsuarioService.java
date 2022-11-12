package springangular.citasmedicas.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springangular.citasmedicas.entityDto.UsuarioDTO;
import springangular.citasmedicas.mapper.UsuarioMapper;
import springangular.citasmedicas.model.Usuario;
import springangular.citasmedicas.repository.UsuarioRepository;

@Service
public class UsuarioService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  public Optional<UsuarioDTO> findByUsuario(String usuario) {
    Optional<Usuario> usuarioDB = usuarioRepository.findByUsuario(usuario);
    if (usuarioDB.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuarioDB.get()));
  }
}
