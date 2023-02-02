package com.ssafy.patpat.common.security.jwt;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import com.ssafy.patpat.common.redis.RedisService;
import com.ssafy.patpat.common.redis.RefreshRedis;
import com.ssafy.patpat.common.redis.RefreshRedisRepository;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenProvider implements InitializingBean {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "PatPatAPI";
    public static final String ACCESSTOKEN_HEADER = "AccessToken";
    public static final String REFRESHTOKEN_HEADER = "RefreshToken";

    private final String secret;
    private final long tokenAccessValidityInMilliseconds;

    private final UserRepository userRepository;

    private final long tokenRefreshValidityInMilliseconds;
    private Key key;
    private final RedisService redisService;


    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.access-token-validity-in-seconds}") long tokenAccessValidityInMilliseconds,
                         @Value("${jwt.refresh-token-validity-in-seconds}") long tokenRefreshValidityInMilliseconds,
                         UserRepository userRepository,
                         RedisService redisService){
        this.secret = secret;
        this.tokenAccessValidityInMilliseconds = tokenAccessValidityInMilliseconds;
        this.tokenRefreshValidityInMilliseconds = tokenRefreshValidityInMilliseconds;
        this.userRepository = userRepository;
        this.redisService = redisService;
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
    public String createRefreshToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenRefreshValidityInMilliseconds);

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
        LOGGER.info("accessToken이 생성되었습니다.");

        return token;
    }

    /** access token 재발급 체크 */
    public boolean checkRefreshToken(String refreshToken){

        String token = resolveToken(refreshToken);

        Optional<String> email = Optional.ofNullable(redisService.getValues(token));

        if(!email.isPresent()){
            LOGGER.info("refreshToken이 존재하지 않습니다.");
            return false;
        }

        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
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

    /**리프레쉬 정보 조회 -일단 보류
    public String getTokenEmail(String token) {
        String email = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getId();

        return email;
    }*/

    /**token 유효성 검증 */
    public boolean validateToken(String token) throws Exception{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
    }

    /**토큰 정보 추출 */
    public String resolveToken(String bearerToken){

//        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public Long getExpiration(String accessToken){
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }
}
