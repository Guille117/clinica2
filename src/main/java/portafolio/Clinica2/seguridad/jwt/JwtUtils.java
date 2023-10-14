package portafolio.Clinica2.seguridad.jwt;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    // crear token
    public String generarToken(String usu){
        return Jwts.builder()
                .subject(usu)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                //.signWith(obtLlave(), Algorithm.HMAC256)
                //.signWith(obtLlave(), Jwts.SIG.HS256)
                .signWith(obtLlave(), SignatureAlgorithm.HS256)
                .compact();
    }

    

//  validar firma
    public boolean tokenValido(String token){
        try{
            Jwts.parser()
            .setSigningKey(obtLlave())
            .build()
            .parseClaimsJws(token)
            .getPayload();
        return true;
        }catch(Exception e){
            log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;
        }

    }
    
    // Obtener el username del token
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // Obtener todos los claims del token
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(obtLlave())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // obtener firma de token
    public Key obtLlave(){
        byte[] cadena = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(cadena);
    }
}
