package com.ssafy.patpat.user.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private long userId;
    @Size(min=1, max=50)
    private String provider;

    @Size(min=3, max=100)
    private String email;

    private String username;

    private String ageRange;

    private String profileImageUrl;

    private String providerId;


}
