package darkchoco.reactspring.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtService {

	static final long EXPIRATIONTIME = 86400000;  // 1 day in ms
	static final String PREFIX = "Bearer";
	// Generate secret key. Only for the demonstration
	// You should read it from the application configuration
	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	// Generate JWT token
	public String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)
                .compact();
  }

	// Get a token from request Authorization header, 
	// parse a token and get username
	public String getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
	
		if (token != null)
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

		return null;
	}
}
