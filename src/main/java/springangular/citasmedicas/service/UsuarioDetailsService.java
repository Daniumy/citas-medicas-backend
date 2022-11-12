package springangular.citasmedicas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Optional;
import springangular.citasmedicas.model.Usuario;
import springangular.citasmedicas.repository.UsuarioRepository;

@Component
public class UsuarioDetailsService implements UserDetailsService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Usuario> usuarioRes = usuarioRepository.findByUsuario(username);

        if (usuarioRes.isEmpty())
            throw new UsernameNotFoundException("Usuario not found : " + username);

        Usuario usuario = usuarioRes.get();

        return new User(
                username,
                usuario.getClave(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
