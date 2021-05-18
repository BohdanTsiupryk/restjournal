package bts.journal.config.security;

import bts.journal.config.model.TokenData;
import bts.journal.model.UserRole;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String jwtSecret;

    @Autowired
    private Gson gson;

    public String generateToken(TokenData data) {
        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        String subject = gson.toJson(data);

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.debug("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(getTokenFromRequest(token)).getBody();
        String subject = claims.getSubject();
        TokenData tokenData = gson.fromJson(subject, TokenData.class);

        return tokenData.getLogin();
    }

    public UserRole getRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(getTokenFromRequest(token)).getBody();
        String subject = claims.getSubject();
        TokenData tokenData = gson.fromJson(subject, TokenData.class);

        return tokenData.getRole();
    }

    private String getTokenFromRequest(String request) {
        if (StringUtils.hasText(request) && request.startsWith("Bearer ")) {
            return request.substring(7);
        }
        return null;
    }
}
