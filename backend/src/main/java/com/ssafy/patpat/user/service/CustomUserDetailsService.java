package com.ssafy.patpat.user.service;

import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.ssafy.patpat.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        System.out.println(nickname);
        return userRepository.findOneWithAuthoritiesByNickname(nickname)
                .map(user -> createUser(nickname, user))
                .orElseThrow(() -> new UsernameNotFoundException(nickname + "->데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String nickname, User user) {
        if (!user.isActivated()) {
            throw new RuntimeException(nickname + "-> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getNickname(),
                user.getEmail(),
                grantedAuthorities);
    }
}
