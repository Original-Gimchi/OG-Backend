package og.bumawiki.bumawiki.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import og.bumawiki.bumawiki.global.config.redis.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static og.bumawiki.bumawiki.global.jwt.JwtProperties.JWT_ACCESS;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final RedisService redisService;

    @Value("${spring.security.jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email, Long time){
        Claims claims = Jwts.claims();
        claims.put("email", email);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.ES256)
                .compact();
    }

    public String createAccessToken(String email){
        return createToken(email, JwtProperties.Access_Token_Valid_Time);
    }

    public String createRefreshToken(String email){
        return createToken(email, JwtProperties.Refresh_Token_Valid_Time);
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(JWT_ACCESS);
    }

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date getExpiredTime(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody().getExpiration();
    }

    public Claims extractAllClaims(String token) {
        try {
            if(redisService.hasKeyBlackList(token)) {
                throw new RuntimeException();
//                throw new CustomException(ErrorCode.USER_NOT_LOGIN);
            }

            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(secretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException();
//            throw new CustomException(ErrorCode.EXPIRED_TOKEN);
        }
    }
}
