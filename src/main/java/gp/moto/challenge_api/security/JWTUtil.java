package gp.moto.challenge_api.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;

import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String construirToken(String username){

        Date data_atual = new Date();

        JwtBuilder builder = Jwts.builder()
                .subject(username)
                .issuedAt(data_atual)
                .expiration(new Date(data_atual.getTime() + 3600000))
                .signWith(getSigningKey());
        return builder.compact();
    }

    public String extrairUsername(String token){

        JwtParser parser = Jwts.parser().verifyWith(getSigningKey()).build();

        return parser.parseSignedClaims(token).getPayload().getSubject();

    }

    public static String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    public static String getNameFromToken(String token) {
        try {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(payload);
            return jsonNode.get("sub").asText();
            
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }

    public static String getNameFromRequest (HttpServletRequest request){
        String token = extractTokenFromRequest(request);
        String name = getNameFromToken(token);
        return name;
    }


    public boolean validarToken(String token){

        try{
            JwtParser parser = Jwts.parser().verifyWith(getSigningKey()).build();

            parser.parseSignedClaims(token);

            return true;
        }catch (Exception e){
            return false;
        }


    }


}
