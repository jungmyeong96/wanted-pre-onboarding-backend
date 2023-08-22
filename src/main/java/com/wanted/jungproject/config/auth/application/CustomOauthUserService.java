package com.wanted.jungproject.config.auth.application;

import com.wanted.jungproject.config.userDetail.domain.CustomUserDetails;
import com.wanted.jungproject.domain.user.domain.Role;
import com.wanted.jungproject.domain.user.domain.Users;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOauthUserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest getClientRegistration: " + userRequest.getClientRegistration());
        System.out.println("userRequest getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest getAdditionalParameters: " + userRequest.getAdditionalParameters());

        OAuth2User oauth2User = super.loadUser(userRequest);

        System.out.println("userRequest getAttributes: " + oauth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getClientId(); //google
        String providerId = oauth2User.getAttribute("sub");
        String username = provider + "_" + providerId; //google_
        String password = "아무거나";
        String email = oauth2User.getAttribute("email");
        Role role = Role.USER;

        Optional<Users> userEntity = usersRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            Users newUser = Users.builder()
                    .email(email)
                    .name(username)
                    .password(password)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            newUser.hashPassword(bCryptPasswordEncoder);
            usersRepository.save(newUser);
            return new CustomUserDetails(newUser, oauth2User.getAttributes());
        }
        return new CustomUserDetails(userEntity.get());
    }
}
