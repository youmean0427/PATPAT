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
@AllArgsConstructor
@RedisHash(value = "refresh")
public class RefreshRedis {
    @Id
    private String email;
    private String token;

    @TimeToLive(unit = TimeUnit.MICROSECONDS)
    private Integer expiration;


    public static RefreshRedis createToken(String email, String token, Integer expiration) {
        return new RefreshRedis(email, token, expiration);
    }

    public void reissue(String token) {
        this.token = token;
    }
}