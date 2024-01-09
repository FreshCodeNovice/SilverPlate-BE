package com.plate.silverplate.user.service;

import com.plate.silverplate.user.domain.dto.OAuth2Attribute;
import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.user.domain.entity.UserProfile;
import com.plate.silverplate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OauthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 기본 OAuth2UserService 객체 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        // OAuth2UserService를 사용하여 OAuth2User 정보를 가져온다.
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // 클라이언트 등록 ID(google, naver, kakao)와 사용자 이름 속성을 가져온다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2Attribute 객체를 만든다.
        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // OAuth2Attribute의 속성값들을 Map으로 반환 받는다.
        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

        // 사용자 email(또는 id) 정보를 가져온다.
        String email = (String) memberAttribute.get("email");
        // 이메일로 가입된 회원인지 조회한다.
        Optional<User> findUser = userRepository.findByEmail(email);

        log.error((String) memberAttribute.get("id"));

        if (findUser.isEmpty()) {
            // 회원이 존재하지 않는 경우
            memberAttribute.put("exist", false);
            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    memberAttribute, "email");
        }

        UserProfile oldProfile = findUser.get().getUserProfile();
        UserProfile newProfile = UserProfile.builder()
                .nickName(oAuth2Attribute.getName())
                .provider(oAuth2Attribute.getProvider())
                .imageUrl(oAuth2Attribute.getPicture())
                .build();

        // 프로필이 변경되었으면 갱신
        if (!oldProfile.equals(newProfile)) {
            findUser.get().updateUserProfile(newProfile);
            userRepository.save(findUser.get());
        }

        // 회원이 존재할 경우
        memberAttribute.put("exist", true);
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(findUser.get().getRole())),
                memberAttribute, "email");
    }

    @Transactional
    public void save(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String provider = oAuth2User.getAttribute("provider");
        String name = oAuth2User.getAttribute("name");
        String imageUrl = oAuth2User.getAttribute("picture");

        User user = User.createUser(email, name, provider, imageUrl);
        userRepository.save(user);
    }
}


