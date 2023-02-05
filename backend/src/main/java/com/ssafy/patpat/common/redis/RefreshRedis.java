package com.ssafy.patpat.common.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter
@RedisHash(value = "refresh")
public class RefreshRedis {
    @Id
    String email;

    String token;

    @TimeToLive(unit = TimeUnit.MICROSECONDS)
    Integer expiration;


    @Builder
    public RefreshRedis(String email, String token, Integer expiration){
        this.email = email;
        this.token = token;
        this.expiration = expiration;
    }

    public static RefreshRedis createToken(String email, String token, Integer expiration) {
        return new RefreshRedis(email, token, expiration);
    }

    public void reissue(String token) {
        this.token = token;
    }
}