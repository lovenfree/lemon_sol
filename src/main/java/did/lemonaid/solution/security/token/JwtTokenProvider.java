package did.lemonaid.solution.security.token;


import did.lemonaid.solution.common.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-validity-in-seconds}")
    private int jwtExpirationInMs;

    private Key key;

    private static final String AUTHORITIES_KEY = "auth";

    @Override
    public void afterPropertiesSet(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date expiryDate = new Date(now + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        User principal = new User(claims.getSubject(),"",authorities);

//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
      return new RestAuthenticationToken(principal, token, authorities);
    }


    public boolean validateToken(ServletRequest request, String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;

        } catch (SecurityException e) {
            //Invalid JWT signature
            log.error("[SecurityException] "+e);
            request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
        } catch (MalformedJwtException e) {
            log.error("[MalformedJwtException] "+e);
            request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            //Expired JWT token
            log.error("[ExpiredJwtException] "+e);
            request.setAttribute("exception", ErrorCode.JWT_EXPIRED_EXCEPTION);
        } catch (UnsupportedJwtException e) {
            //Unsupported JWT token
            log.error("[UnsupportedJwtException] "+e);
            request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
        } catch (IllegalArgumentException e) {
            //JWT token compact of handler are invalid.
            log.error("[IllegalArgumentException] "+e);
            request.setAttribute("exception", ErrorCode.JWT_INVALID_TOKEN);
        }
        return false;
    }


}



