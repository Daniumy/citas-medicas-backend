package springangular.citasmedicas.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springangular.citasmedicas.entityDto.UsuarioDTO;
import springangular.citasmedicas.security.JwtTokenService;
import springangular.citasmedicas.service.MedicoService;
import springangular.citasmedicas.service.PacienteService;
import springangular.citasmedicas.service.UsuarioService;

@CrossOrigin()
@RestController
public class UsuarioController {
  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private PacienteService pacienteService;

  @Autowired
  private MedicoService medicoService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenService jwtTokenService;

  @PostMapping("/login")
  public Map<String, Object> login(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {

    try {
      UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
              usuario, password
      );
      authenticationManager.authenticate(authInputToken);
    } catch (final BadCredentialsException ex) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    //devolver el token
    Map<String, Object> response = new HashMap<>();
    response.put("jwt",jwtTokenService.generateToken(usuario));

    if (pacienteService.findByUsuario(usuario).isPresent()) {
      response.put("rol", "paciente");
    } else if (medicoService.findByUsuario(usuario).isPresent()) {
      response.put("rol", "medico");
    }

    return response;
  }

  @GetMapping("session")
  public UsuarioDTO getSession() {
    Optional<UsuarioDTO> usuario = usuarioService.findByUsuario(
            SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
    );
    return usuario.get();
  }

}
