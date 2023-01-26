package com.ssafy.patpat.user.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.user.dto.FavoriteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 찜 동물 리스트
     * @return
     */
    @GetMapping("/favorite")
    public ResponseEntity<Object> selectFavoriteList(){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<FavoriteDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 찜 등록
     * @return
     */
    @GetMapping("/favorite/{protectId}")
    public ResponseEntity<Object> insertFavorite(@PathVariable int protectId){
        //서비스 호출 코드
        if(true){
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
    public ResponseEntity<Object> deleteFavorite(@PathVariable int protectId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
}
