package og.bumawiki.bumawiki.global.jwt;

import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.global.config.redis.RedisService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidateService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    public String getEmail(String token) {
        return jwtTokenProvider.extractAllClaims(token).get("email", String.class);
    }

    public void validateRefreshToken(String token) {
        if (redisService.getData(getEmail(token)) == null) {
            throw new RuntimeException();
//            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}
