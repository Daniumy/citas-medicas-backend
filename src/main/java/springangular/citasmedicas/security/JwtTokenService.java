package springangular.citasmedicas.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
  private final Algorithm hmac512;
  private final JWTVerifier verifier;
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  public JwtTokenService(@Value("${jwt.secret}") final String secret) {
    this.hmac512 = Algorithm.HMAC512(secret);
    this.verifier = JWT.require(this.hmac512).withSubject("User Details").withIssuer("Dani's security").build();
  }

  public String generateToken(String username) {
    return JWT.create()
        .withSubject("User Details")
        .withClaim("username", username)
        .withIssuedAt(new Date(System.currentTimeMillis()))
        .withIssuer("Dani's security")
        .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100))
        .sign(this.hmac512);
  }

  public String validateTokenAndGetUsername(final String token) {
    try {
      return verifier.verify(token).getClaim("username").asString();
    } catch (final JWTVerificationException verificationEx) {
      return null;
    }
  }


}
