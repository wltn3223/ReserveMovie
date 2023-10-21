package Controller;



import model.MemberDao;
import model.MemberVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static view.Mainview.mainview;

public class MainController {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static MemberDao memberDao = new MemberDao();
    public static void main(String[] args) {
        mainview();
        String s = null;
        try {
            s =br.readLine();
            System.out.println(s);
            if (s.equals("1")){
                login();
            }
            else {
                join();
            }
        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }
    public static void login() throws Exception{
        System.out.println("로그인 메뉴 입니다.");
        System.out.println("아이디 입력:");
        String id = br.readLine();
        MemberVO member = memberDao.findMember(id);
        if(member == null){
            System.out.println("없는 아이디 입니다.");
            return;
        }
        System.out.println("패스워드 입력");
        String passwd = br.readLine();
        if(!passwd.equals(member.getPasswd())){
            System.out.println("비밀 번호를 잘못 입력하셨습니다.");
            return;
        }
            memberDao.MemberList();
            System.out.println("로그인 성공");

    }
    public static void join() throws  Exception{
        System.out.println("회원 가입메뉴입니다.");
        System.out.println("아이디 입력");
        String id = br.readLine();
        if(memberDao.findID(id)){
            System.out.println("중복되는 아이디입니다.");
            return;
        }
        System.out.println("패스워드입력");
        String passwd= br.readLine();
        System.out.println("이름입력");
        String name = br.readLine();
        System.out.println("스마트폰번호입력");
        String phoneNum = br.readLine();
        if(memberDao.findPhone(phoneNum)){
            System.out.println("중복되는 번호입니다.");
            return;
        }
        System.out.println("이메일입력");
        String email = br.readLine();
        if(memberDao.findEmail(email)){
            System.out.println("중복되는 이메일입니다.");
            return;
        }
        System.out.println("성별입력(M,W)");
        String sex = br.readLine().toUpperCase();
        if (!(sex.equalsIgnoreCase("W") || sex.equalsIgnoreCase("M") )){
            System.out.println("잘못된 성별을 입력하셨습니다. 다시 입력해주세요");
            return;
        }
        System.out.println("회원가입 완료");
        MemberVO memberVO = new MemberVO(id,passwd,name,phoneNum,email,sex.charAt(0));
        memberDao.insertMember(memberVO);

    }
    public static void updateinfo(){

    }
    public static void resignation(){

    }
}
