package com.ssafy.patpat.common.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "PatPatAPI";

    private final String secret;
    private final long tokenAccessValidityInMilliseconds;

    private final UserRepository userRepository;

    private final long tokenRefreshValidityInMilliseconds;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.access-token-validity-in-seconds}") long tokenAccessValidityInMilliseconds,
                         @Value("${jwt.refresh-token-validity-in-seconds}") long tokenRefreshValidityInMilliseconds,
                         UserRepository userRepository){
        this.secret = secret;
        this.tokenAccessValidityInMilliseconds = tokenAccessValidityInMilliseconds;
        this.tokenRefreshValidityInMilliseconds = tokenRefreshValidityInMilliseconds;
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**Access token 생성 algorithm */
    public String createAccessToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenAccessValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**Refresh token 생성 algorithm */
    public String createRefreshToken(){
        String email = SecurityUtil.getCurrentEmail().get();
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenRefreshValidityInMilliseconds);

        return Jwts.builder()
                .setId(email)
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /** access token 재발급 체크 */
    public boolean checkRefreshToken(String refreshToken){

        com.ssafy.patpat.user.entity.User user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail).get();
        if(user.getRefreshToken() == null){
            LOGGER.info("refreshToken이 존재하지 않습니다.");
            return false;
        }
        String userRefreshToken = user.getRefreshToken();

        if(!userRefreshToken.equals(refreshToken)){
            LOGGER.info("refreshToken이 맞지 않습니다.");
            return false;
        }

        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(userRefreshToken.substring(7));
            LOGGER.info("refreshToken이 만료되지 않았습니다.");
            return true;
        }catch (ExpiredJwtException e) {
            LOGGER.info("refreshToken이 만료되었습니다. 다시 로그인 해주세요.");
            return false;
        }catch (Exception e){
            LOGGER.error("refreshToken 재발급중 에러각 발생했습니다.: {}", e.getMessage());
            return false;
        }
    }

    /**인증 정보 조회 */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        User principal =new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**token 유효성 검증 */
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            LOGGER.info("잘못된 JWT 서명입니다.");
        }catch(ExpiredJwtException e){
            LOGGER.info("만료된 JWT 토큰입니다.");
        }catch(UnsupportedJwtException e){
            LOGGER.info("지원하지 않는 JWT 토큰입니다.");
        }catch(IllegalArgumentException e){
            LOGGER.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}