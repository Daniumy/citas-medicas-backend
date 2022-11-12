package springangular.citasmedicas.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springangular.citasmedicas.service.UsuarioDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private UsuarioDetailsService usuarioDetailsService;

  @Autowired
  private JwtTokenService jwtTokenService;

  //this filter will be used and called by the main security config that we will define later on
  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,final FilterChain chain) throws ServletException, IOException {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null ) {
      chain.doFilter(request, response);
      return;
    }

    final String token = header.substring(7);
    if (token.isBlank())
    {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
    }
    else {
      final String username = jwtTokenService.validateTokenAndGetUsername(token);
      //if user cant be validated
      if (username == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
        chain.doFilter(request, response);
        return;
      }

      //if user is validated
      final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(username);
      final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          username, userDetails.getPassword(), userDetails.getAuthorities());
      if (SecurityContextHolder.getContext().getAuthentication() == null) SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }

}
