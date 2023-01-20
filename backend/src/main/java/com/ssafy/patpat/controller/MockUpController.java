package com.ssafy.patpat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MockUpController {
    /** 동물 관련 기능
     * root -> "/dog"
     * */

    /**
     * 봉사 관련 기능
     *
     * root -> "/volunteer"
     * */
//봉사 공고 관련
    @GetMapping("/notice")
    public String selectNoticeListByUser(){
        return "신청 가능한 봉사공고 리스트 받아오기";
    }
    @GetMapping("/notice/all")
    public String selectNoticeListByShelter(){
        return "특정 보호소에서 올린 모든 봉사공고 리스트 받아오기";
    }
    @GetMapping("/notice/shelter")
    public String selectWaitNoticeListByShelter(){
        return "특정 보호소에서 대기중인 상태 봉사공고 리스트 받아오기";
    }
    @GetMapping("/notice/{noticeId}")
    public String selectNotice(){
        return "봉사공고 상세 받아오기";
    }
    @PostMapping("/notice")
    public String insertNotice(){
        return "봉사공고 등록하기";
    }
    @PutMapping("/notice/{noticeId}")
    public String updateNotice(){
        return "봉사공고 수정하기";
    }
    @DeleteMapping("/notice/{noticeId}")
    public String deleteNotice(){
        return "봉사공고 삭제하기";
    }
    // 봉사 신청
    @GetMapping("/application")
    public String selectapplicationList(){
        return "나의 봉사 신청 내역 리스트(대기,완료,방문확인)";
    }
    @GetMapping("/application/{noticeId}")
    public String selectapplicationListByNotice(){
        return "봉사공고 기반 지원자들의 지원서 받아오기";
    }
    @PostMapping("/application")
    public String insertApplication(){
        return "봉사 지원서 등록하기";
    }
    @PutMapping("/application/{applicationId}")
    public String updateApplication(){
        return "봉사 지원서 수정하기 수락,거절,방문,노쇼 상태 수정";
    }
    @DeleteMapping("/application/{applicationId}")
    public String deleteApplication(){
        return "봉사 예약 취소(개인이)";
    }
    /**
     * 게시판 관련 기능
     *
     * */
    @GetMapping("/")
    public String selectBoardListByUser(){
        return "해당 유저가 쓴 글 (전체 || 정보 공유 || 무료 나눔 || 입양 후기 )";
    }
    @GetMapping("/all")
    public String selectBoardList(){
        return "모든 게시글 (전체 || 정보 공유 || 무료 나눔 || 입양 후기 )";
    }
    @GetMapping("/{boardId}")
    public String selectBoard(){
        return "게시판 상세";
    }
    @PostMapping("/")
    public String insertBoard(){
        return "게시글 등록";
    }
    @PutMapping("/{boardId}")
    public String updateBoard(){
        return "게시글 수정";
    }
    @DeleteMapping("/{boardId}")
    public String deleteBoard(){
        return "게시글 삭제";
    }
    //댓글
    @GetMapping("/reply/{boardId}")
    public String selectReplyList(){
        return "댓글 리스트 가져오기";
    }
    @PostMapping("/reply/{boardId}")
    public String insertReply(){
        return "댓글 등록하기";
    }
    @PostMapping("/reply/{boardId}")
    public String updateReply(){
        return "댓글 수정하기";
    }
    @DeleteMapping("/reply/{boardId}")
    public String deleteReply(){
        return "댓글 삭제하기";
    }

    //대댓글
    @PostMapping("/rereply/{replyId}")
    public String insertRereply(){
        return "대댓글 등록하기";
    }
    @PutMapping("/rereply/{replyId}")
    public String updateRereply(){
        return "대댓글 수정하기";
    }
    @DeleteMapping("/rereply/{replyId}")
    public String deleteRereply(){
        return "대댓글 삭제하기";
    }

    /**
     * 상담 관련 기능
     *
     * root -> "/consulting"
     * */

    @GetMapping("/")
    public String selectConsultingList(){
        return "개인의 상담 내역 불러오기";
    }
    @GetMapping("/shelter")
    public String selectConsultingListByShelter(){
        return "보호소 입장에서 모든 상담 리스트 불러오기";
    }
    @PostMapping("/")
    public String insertConsulting(){
        return "상담 등록하기";
    }
    @PutMapping("/")
    public String updateConsulting(){
        return "상담 수정하기(상태)";
    }
    @DeleteMapping("/")
    public String deleteConsulting(){
        return "상담 삭제하기";
    }

    /**
     * 보호소 관련 기능
     *
     * root -> "/shelter"
     * */
    @GetMapping("/")
    public String selectShelterList(){
        return "보호소 검색하기 (시도, 구군, 견종, 이름 필터 선택 후 카운팅 값 까지 포함한 결과) || 시도, 구군 봉사 공고 기반 보호소 검색";
    }
    @GetMapping("/")
    public String selectDogTypeList(){
        return "시도, 구군 별 존재하는 모든 견종 리스트";
    }
    @GetMapping("/{shelterId}")
    public String selectShelter(){
        return "보호소 정보 보기(마이페이지 초기 데이터)";
    }
    @PostMapping("/")
    public String insertShelter(){
        return "보호소 등록 성공,실패";
    }
    @PostMapping("/auth")
    public String authenticateShelter(){
        return "보호소 인증 성공,실패";
    }
    @PutMapping("/")
    public String updateShelter(){
        return "보호소 수정 성공,실패";
    }
    @DeleteMapping("/")
    public String deleteShelter(){
        return "보호소 삭제 성공,실패";
    }

    /**
    * 유저 관련 기능
    *
    * root -> "/user"
    * */
    //로그인
    //로그아웃
    @PostMapping("/logout")
    public String logout(){
        return "로그아웃";
    }
    @GetMapping("/")
    public String selectMypage(){
        return "내 정보 리턴";
    }
    @PutMapping("/")
    public String updateMypage(){
        return "내 정보 수정(소속)";
    }
    @DeleteMapping("/")
    public String deleteMypage(){
        return "회원탈퇴";
    }

    /**
    * 알람 관련 기능
    *
    * root -> "/alarm"
    * */
}

