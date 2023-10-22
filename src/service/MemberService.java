package service;

import model.MemberDao;
import model.MemberDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MemberService {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static MemberDao memberDao = new MemberDao();

    public static MemberDTO login() throws Exception {
        System.out.println("로그인 메뉴 입니다.");
        System.out.println("아이디 입력:");
        String id = br.readLine();
        MemberDTO member = memberDao.findMember(id);
        if (member == null) {
            System.out.println("없는 아이디 입니다.");
            return null;
        }
        System.out.println("패스워드 입력");
        String passwd = br.readLine();
        if (!passwd.equals(member.getPasswd())) {
            System.out.println("비밀 번호를 잘못 입력하셨습니다.");
            return null;
        }
        System.out.println("성공");
        return member;

    }

    public static void join() throws Exception {
        System.out.println("회원 가입메뉴입니다.");
        System.out.println("아이디 입력");
        String id = br.readLine();
        if (memberDao.findID(id)) {
            System.out.println("중복되는 아이디입니다.");
            return;
        }
        System.out.println("패스워드입력");
        String passwd = br.readLine();
        System.out.println("이름입력");
        String name = br.readLine();
        System.out.println("스마트폰번호입력");
        String phoneNum = br.readLine();
        if (memberDao.findPhone(phoneNum)) {
            System.out.println("중복되는 번호입니다.");
            return;
        }
        System.out.println("이메일입력");
        String email = br.readLine();
        if (memberDao.findEmail(email)) {
            System.out.println("중복되는 이메일입니다.");
            return;
        }
        System.out.println("성별입력(M,W)");
        String sex = br.readLine().toUpperCase();
        if (!(sex.equalsIgnoreCase("W") || sex.equalsIgnoreCase("M"))) {
            System.out.println("잘못된 성별을 입력하셨습니다. 다시 입력해주세요");
            return;
        }
        System.out.println("회원가입 완료");
        MemberDTO memberDTO = new MemberDTO(id, passwd, name, phoneNum, email, sex.charAt(0));
        memberDao.insertMember(memberDTO);

    }

    public static void updatepassword() throws Exception {
        System.out.println("비밀번호 변경메뉴입니다.");
        String password = null;
        MemberDTO memberDTO = null;

            while (true) {
                memberDTO = login();
                System.out.println("수정할 password 입력");
                password = br.readLine().trim();
                if ("".equals(password)) {
                    System.out.println("올바른 password를 입력해주세요");
                    continue;
                }
                memberDTO.setPasswd(password);
                break;
            }

            memberDao.updateMember(memberDTO);
            System.out.println("비밀번호변경완료");





    }

    public static void resignation() throws Exception {
        MemberDTO memberDTO = login();
        memberDao.deleteMember(memberDTO.getiD());


    }
}
