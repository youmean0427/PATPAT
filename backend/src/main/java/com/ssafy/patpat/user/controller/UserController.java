package com.ssafy.patpat.user.controller;

import com.google.gson.JsonObject;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.error.ErrorCode;
import com.ssafy.patpat.common.error.ErrorDto;
import com.ssafy.patpat.common.error.LogoutException;
import com.ssafy.patpat.common.security.filter.JwtFilter;
import com.ssafy.patpat.common.security.jwt.TokenProvider;
import com.ssafy.patpat.user.dto.*;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.service.GoogleService;
import com.ssafy.patpat.user.service.KakaoService;
import com.ssafy.patpat.user.service.NaverService;
import com.ssafy.patpat.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Api(tags = {"06. User"},description = "유저 관련 서비스")
@Slf4j
public class UserController {

    private final UserService userService;
    /**
     * 찜 동물 리스트
     * @return
     */
    @GetMapping("/favorite")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "찜 동물 조회", notes = "찜 동물 리스트 조회 / offSet, limit")
    public ResponseEntity<Object> selectFavoriteList(@RequestParam("offSet") Integer offSet, @RequestParam("limit") Integer limit){
        //서비스 호출 코드
        ResponseListDto list = userService.getFavoriteDogs(offSet, limit);
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }
    /**
     * 찜 등록
     * @return
     */
    @GetMapping("/favorite/{protectId}")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "찜 등록", notes = "찜 동물 등록 / protectId")
    public ResponseEntity<Object> insertFavorite(@PathVariable Long protectId){
        //서비스 호출 코드
        if(userService.insertFavoriteDogs(protectId)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 찜 해제
     * @return
     */
    @DeleteMapping("/favorite/{protectId}")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "찜 해제", notes = "찜 동물 해제")
    public ResponseEntity<Object> deleteFavorite(@PathVariable Long protectId){
        //서비스 호출 코드
        if(userService.deleteFavoriteDogs(protectId)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }


//    @GetMapping("/login/kakao")
//    public ResponseEntity<UserResponseDto> kakaoLogin(@RequestParam("code") String code) throws Exception{
//
//
//        UserResponseDto dto = kakaoService.login(code);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + dto.getTokenDto().getAccessToken());
//        httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + dto.getTokenDto().getRefreshToken());
//
//        return new ResponseEntity<>(dto, httpHeaders, HttpStatus.OK);
//    }
//
//    @GetMapping("/login/naver")
//    public ResponseEntity<UserResponseDto> naverLogin(@RequestParam("code") String code) throws Exception{
//
//
//        UserResponseDto dto = naverService.login(code);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + dto.getTokenDto().getAccessToken());
//        httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + dto.getTokenDto().getRefreshToken());
//
//        return new ResponseEntity<>(dto, httpHeaders, HttpStatus.OK);
//    }
//
//    @GetMapping("/login/google")
//    public ResponseEntity<UserResponseDto> googleLogin(@RequestParam("code") String code) throws Exception{
//
//
//        UserResponseDto dto = googleService.login(code);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + dto.getTokenDto().getAccessToken());
//        httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + dto.getTokenDto().getRefreshToken());
//
//        return new ResponseEntity<>(dto, httpHeaders, HttpStatus.OK);
//    }

    @GetMapping("/login/{provider}")
    public ResponseEntity<UserResponseDto> googleLogin(@PathVariable("provider") String provider, @RequestParam("code") String code) throws Exception{

        UserResponseDto dto = userService.login(provider, code);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + dto.getTokenDto().getAccessToken());
        httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + dto.getTokenDto().getRefreshToken());

        return new ResponseEntity<>(dto, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/logout")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ResponseMessage> logout(HttpServletRequest request) throws Exception{
        String accessToken = request.getHeader(JwtFilter.ACCESSTOKEN_HEADER);
        String refreshToken = request.getHeader(JwtFilter.REFRESHTOKEN_HEADER);
        TokenDto tokenDto = new TokenDto(accessToken, refreshToken);
        ResponseMessage responseMessage = userService.logout(tokenDto);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/refresh")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Object> refresh(HttpServletRequest request) {

        String refreshToken = request.getHeader(JwtFilter.REFRESHTOKEN_HEADER);
        try{
            TokenDto tokenDto = userService.refresh(refreshToken);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + tokenDto.getAccessToken());
            httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + tokenDto.getRefreshToken());
            return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
        }catch (LogoutException e){
            ErrorDto error = new ErrorDto(ErrorCode.PLZ_RELOGIN.getMessage(), ErrorCode.PLZ_RELOGIN.getCode());

            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }


    }

    @GetMapping("/reissue")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<TokenDto> reissue(HttpServletRequest request) throws LogoutException {

        String refreshToken = request.getHeader(JwtFilter.REFRESHTOKEN_HEADER);
        TokenDto tokenDto = userService.reissue(refreshToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + tokenDto.getAccessToken());
        httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + tokenDto.getRefreshToken());
        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/info")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ResponseMessage> updateUserInfo(UserDto userDto, @RequestPart(required = false)MultipartFile profileFile) throws Exception{
        log.info(String.valueOf(profileFile));
        ResponseMessage responseMessage = userService.updateUser(userDto, profileFile);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/info")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ResponseMessage> deleteUserInfo(@RequestParam("userId") Long userId, HttpServletRequest request) throws Exception{
        String accessToken = request.getHeader(JwtFilter.ACCESSTOKEN_HEADER);
        String refreshToken = request.getHeader(JwtFilter.REFRESHTOKEN_HEADER);
        TokenDto tokenDto = new TokenDto(accessToken, refreshToken);
        ResponseMessage responseMessage = userService.deleteUser(tokenDto,userId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Object> getMyUserInfo(){
        UserDto user = userService.getUserWithAuthorities();
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("fail"));
        }
        else{
            return ResponseEntity.ok(user);
        }
    }

    /**
     * 사진 넣을 용도
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "사진 등록", notes = "임시용 사진 넣기")
    public ResponseEntity<Object> insertImage(@RequestPart List<MultipartFile> profileFile) throws Exception{
        //서비스 호출 코드
        userService.insertImage(profileFile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));

    }

}
