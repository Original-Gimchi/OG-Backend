package og.bumawiki.bumawiki.domain.user.service;

import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import og.bumawiki.bumawiki.domain.user.domain.User;
import og.bumawiki.bumawiki.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final BsmOauth bsmOauth;

    private final UserFacade userFacade;

    public User bsmOauth(String authCode) {
        String token;
        BsmResourceResponse resource;
        try {
            token = bsmOauth.getToken(authCode);
            resource = bsmOauth.getResource(authCode);
        } catch (BsmAuthTokenNotFoundException | BsmAuthCodeNotFoundException | IOException e) {
            throw new RuntimeException(e);
        } catch (BsmAuthInvalidClientException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
