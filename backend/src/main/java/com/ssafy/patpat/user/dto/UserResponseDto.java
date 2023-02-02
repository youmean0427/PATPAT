package com.ssafy.patpat.user.dto;

import com.ssafy.patpat.user.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private TokenDto tokenDto;
    private User userDto;
}
