package pl.agh.edu.master_diet.security.oauth2.user;


import pl.agh.edu.master_diet.core.model.common.AuthProvider;
import pl.agh.edu.master_diet.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public final class OAuth2UserInfoFactory {

    private OAuth2UserInfoFactory() {

    }

    public static OAuth2UserInfo getOAuth2UserInfo(final String registrationId,
                                                   final Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(
                    "Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
