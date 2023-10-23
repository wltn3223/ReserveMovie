package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mainview {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getMenuChoice() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("안녕하세요 미래 영화관입니다.");
        sb.append("\n무엇을 도와드릴까요.? 1. 로그인 2. 회원가입 3.비밀번호 변경 4. 회원 탈퇴 0. 종료");
        System.out.println(sb);
        return br.readLine();

    }

    public static String getUserMenu() throws Exception {
        System.out.println("1. 영화 예매 2. 영화 예매 내역 확인3. 영화 예매 취소 4. 좌석 변경 0. 종료");
        return br.readLine();
    }

    public static String getAdminMenu() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("관리자 메뉴");
        sb.append("\n1. 영화 추가, 조회, 수정, 제거");
        sb.append("\n2. 상영관 추가, 조회, 좌석 수정, 상영관 제거");
        sb.append("\n3. 상영 영화 추가, 조회, 수정, 제거");
        sb.append("\n0.종료.");
        System.out.println(sb);
        return br.readLine();
    }

    public static String getMovieAdminMenu() throws Exception {
        System.out.println("1. 영화 추가 2. 영화 목록 조회 3. 영화 수정 4. 영화 삭제 0. 돌아가기");
        return br.readLine();
    }

    public static String getCinemaAdminMenu() throws Exception {
        System.out.println("1. 상영관 추가 2. 상영관 조회 3. 상영관 제거 0. 돌아가기");
        return br.readLine();
    }

    public static String getPlayingMovieAdminMenu() throws Exception {
        System.out.println("1. 상영 영화 추가 2. 상영 영화 조회 3. 상영 영화 수정 4. 상영할 영화 제거 0. 돌아가기");
        return br.readLine();
    }

    public static String getUserReserveMenu() throws Exception {
        System.out.println("1. 영화 예매 2. 영화 예매 내역 확인 3. 영화 예매 취소 4. 좌석 변경 0. 종료");
        return br.readLine();
    }




}
