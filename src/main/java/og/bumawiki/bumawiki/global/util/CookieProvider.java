package og.bumawiki.bumawiki.global.util;

import og.bumawiki.bumawiki.domain.user.web.dto.response.CookieResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static og.bumawiki.bumawiki.global.jwt.JwtProperties.*;

@Component
public class CookieProvider {

    public Cookie createCookie(String name, String value, long time) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        cookie.setMaxAge((int) time);
        cookie.setPath("/");
//        cookie.setDomain(COOKIE_DOMAIN);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req, String name) {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    public CookieResponseDto jwtToCookies(String accessToken, String refreshToken) {
        Cookie accessTokenCookie = createCookie("ACCESS-TOKEN", accessToken, Access_Token_Valid_Time);
        Cookie refreshTokenCookie = createCookie("REFRESH-TOKEN", refreshToken, Refresh_Token_Valid_Time);

        return CookieResponseDto.builder()
                .accessToken(accessTokenCookie)
                .refreshToken(refreshTokenCookie)
                .build();
    }
}
