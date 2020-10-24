package com.study.SpringBootOAuth2LoginAPI.oauth2;

import com.study.SpringBootOAuth2LoginAPI.model.User;
import com.study.SpringBootOAuth2LoginAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return null;
    }

    /**
     * 기능: 인증이 완료된 사용자 정보를 데이터베이스에 저장
     *
     */
    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        String providerName = authorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        OAuth2User oAuth2User = (OAuth2User) principal.getPrincipal();
        String oAuthId = String.valueOf(oAuth2User.getAttributes().get("id"));
        String name = (String) ((LinkedHashMap) ((LinkedHashMap) oAuth2User.getAttribute("kakao_account")).get("profile")).get("nickname");

        User user = User.of(oAuthId, name, providerName, accessToken.getTokenValue());
        userRepository.save(user);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }
}
