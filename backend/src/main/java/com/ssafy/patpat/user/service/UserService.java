package com.ssafy.patpat.user.service;

import com.ssafy.patpat.common.jwt.TokenProvider;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.user.dto.TokenDto;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.dto.UserResponseDto;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto login(UserDto userDto){
        Optional<User> userOptional = userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail());
        User user;
        if(userOptional.orElse(null) == null) {
            user = signup(userDto);
        }else{
            user = userOptional.get();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getMethod() + user.getUserid());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto token = new TokenDto();

        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        user.builder()
                .refreshToken(refreshToken)
                .build();

        userRepository.save(user);

        return token;
    }

    @Transactional
    public User signup(UserDto userDto){

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .email(userDto.getEmail())
                .method(userDto.getMethod())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        user.setPassword(passwordEncoder.encode(user.getMethod() + user.getUserid()));

        System.out.println(user.getMethod());
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentNickname().flatMap(userRepository::findOneWithAuthoritiesByEmail);
    }
}
