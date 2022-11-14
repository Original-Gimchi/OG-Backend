package og.bumawiki.bumawiki.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import og.bumawiki.bumawiki.domain.user.domain.User;
import og.bumawiki.bumawiki.domain.user.facade.UserFacade;
import og.bumawiki.bumawiki.domain.user.web.dto.response.CookieResponseDto;
import og.bumawiki.bumawiki.global.config.redis.RedisService;
import og.bumawiki.bumawiki.global.jwt.JwtProvider;
import og.bumawiki.bumawiki.global.jwt.JwtValidateService;
import og.bumawiki.bumawiki.global.util.CookieProvider;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final UserFacade userFacade;

    private final JwtProvider jwtProvider;

    private final JwtValidateService jwtValidateService;

    private final CookieProvider cookieProvider;

    private final RedisService redisService;

    private final UserService userService;

    public CookieResponseDto bsmLogin(String authCode){
         User user = userService.bsm
    }
}
