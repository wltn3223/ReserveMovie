package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mainview {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String getMenuChoice() throws  Exception{
        StringBuffer sb = new StringBuffer("안녕하세요 미래 영화관입니다.");
        sb.append("\n무엇을 도와드릴까요.? 1. 로그인 2. 회원가입 3.비밀번호 변경 4. 회원 탈퇴 0. 종료");
        System.out.println(sb);
        return br.readLine();

    }


}
