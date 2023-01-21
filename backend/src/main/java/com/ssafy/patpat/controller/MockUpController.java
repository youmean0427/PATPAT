package com.ssafy.patpat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MockUpController {
    /** 동물 관련 기능
     * root -> "/protect"
     * */
    @GetMapping("/main")
    public String selectMainProtectList(){
        return "메인에서 안락사 기준 정렬 강아지 리스트";
    }
    @GetMapping("/{shelterId}")
    public String selectProtectListByShelter(){
        return "해당 보호소가 가진 강아지 리스트";
    }
    @PostMapping("/")
    public String insertProtect(){
        return "보호소에서 강아지 등록"+
                "일괄등록 or 전체 등록";
    }
    @PostMapping("/{protectId}")
    public String updateProtect(){
        return "보호소에서 강아지 수정";
    }
    @DeleteMapping("/{protectId}")
    public String deleteProtect(){
        return "보호소에서 강아지 삭제";
    }
    /** 신고 관련 기능
     * root -> "/report"
     * */
    @GetMapping("/")
    public String selectReportList(){
        return "전체 신고 리스트(임보, 실종)"+"성별,견종 필터기능 | 임보,실종 필터 혹은 전체 보여주기";
    }
    @GetMapping("/user")
    public String selectReportListByUser(){
        return "해당 유저의 신고 리스트(임보, 실종)";
    }
    @GetMapping("/recommend/{reportId}")
    public String selectReportListByMissing(){
        return "실종 지역 근방 유사견종 개체수 + 유사견종 리스트";
    }
    @GetMapping("/{reportId}")
    public String detailReport(){
        return "실종,임보 상세";
    }
    @PostMapping("/")
    public String insertReport(){
        return "신고 등록(임보, 실종)";
    }
    @PostMapping("/{reportId}")
    public String updateReport(){
        return "신고 수정(임보, 실종)";
    }

    /**
     * 봉사 관련 기능
     *
     * root -> "/volunteer"
     * */
//봉사 공고 관련
    @GetMapping("/notice")
    public String selectNoticeListByUser(){
        return "신청 가능한 봉사공고 리스트 받아오기(게시판)";
    }
    @GetMapping("/notice/{shelterId}")
    public String selectNoticeListByShelter(){
        return "특정 보호소에서 올린 신청 가능한 봉사공고 리스트 받아오기" +
                "만약 바디에 shelterId 값의 여부를 확인한 뒤 있다면 보호소 입장에서 해당 년 월에 내가 올린 모든 공고 리스트 불러오기";
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
    @GetMapping("/reservation")
    public String selectReservationList(){
        return "나의 봉사 신청 내역 리스트(대기,완료,방문확인)";
    }
    @GetMapping("/reservation/{noticeId}")
    public String selectReservationListByNotice(){
        return "봉사공고 기반 지원자들의 지원서 받아오기";
    }
    @PostMapping("/reservation")
    public String insertReservation(){
        return "봉사 지원서 등록하기";
    }
    @PutMapping("/reservation/{reservationId}")
    public String updateReservation(){
        return "봉사 지원서 수정하기 수락,거절,방문,노쇼, 취소 상태 수정";
    }
    /**
     * 게시판 관련 기능
     * root - board
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
    @PostMapping("/{boardId}")
    public String updateBoard(){
        return "게시글 수정";
    }
    @DeleteMapping("/{boardId}")
    public String deleteBoard(){
        return "게시글 삭제";
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
    @PutMapping("/{consultingId}")
    public String updateConsulting(){
        return "상담 수정하기(상태)";
    }
    @DeleteMapping("/{consultingId}")
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
        return "시도, 구군, 보호소별 존재하는 모든 견종 리스트";
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
    @GetMapping("/favorite")
    public String selectFavoriteProtectListByUser(){
        return "즐겨찾기를 누른 강아지 리스트";
    }
    @PutMapping("/favorite/{protectId}")
    public String updateFavorite(){
        return "관심동물 등록,해제";
    }
    @PutMapping("/")
    public String updateMypage(){
        return "내 정보 수정(소속)";
    }
    @DeleteMapping("/")
    public String deleteMypage(){
        return "회원탈퇴";
    }
    //관심동물 선택,해제 uri 만들기

    /**
    * 알람 관련 기능
    *
    * root -> "/alarm"
    * */
}

