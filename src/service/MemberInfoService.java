package service;

import model.MemberDao;
import model.MemberDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MemberService {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final MemberDao memberDao = new MemberDao();

    public MemberDTO login() throws Exception {
        System.out.println("로그인 메뉴 입니다.");
        System.out.println("아이디 입력:");
        String id = br.readLine().trim();
        MemberDTO member = memberDao.findMember(id);
        if (member == null) {
            System.out.println("없는 아이디 입니다.");
            return null;
        }
        System.out.println("패스워드 입력");
        String passwd = br.readLine().trim();
        if (!passwd.equals(member.getPasswd())) {
            System.out.println("비밀 번호를 잘못 입력하셨습니다.");
            return null;
        }
        System.out.println("성공");
        return member;

    }

    public void join() throws Exception {
        System.out.println("회원 가입메뉴입니다.");
        ArrayList <String> info = new ArrayList<>();
        while (true) {
            System.out.println("아이디 입력");
            info.add(br.readLine().trim());
            if (memberDao.findID(info.get(0))) {
                System.out.println("중복되는 아이디입니다.");
                continue;
            }
            break;
        }
        System.out.println("패스워드입력");
        info.add(br.readLine().trim());

        System.out.println("이름입력");
        info.add(br.readLine().trim());

        while (true) {
            System.out.println("스마트폰번호 입력");
            info.add(br.readLine().trim());
            if (memberDao.findPhone(info.get(3))) {
                System.out.println("중복되는 번호입니다.");
                continue;
            }

            break;
        }

        while (true) {
            System.out.println("이메일입력");
            info.add(br.readLine().trim());
            if (memberDao.findEmail(info.get(4))) {
                System.out.println("중복되는 이메일입니다.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("성별입력(M,W)");
            info.add(br.readLine().trim().toUpperCase());
            if (!(info.get(5).equals("W") || info.get(5).equals("M"))) {
                info.remove(5);
                System.out.println("잘못된 성별을 입력하셨습니다. 다시 입력해주세요");
                continue;
            }
            break;
        }
        if(blankCheck(info)){
            System.out.println("입력할 정보에 공백을 입력하셨습니다 다시 시도해주세요");
            return;
        }
        System.out.println("회원가입 완료");
        MemberDTO memberDTO = new MemberDTO(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4),info.get(5).charAt(0));
        memberDao.insertMember(memberDTO);

    }

    public void updatepassword() throws Exception {
        System.out.println("비밀번호 변경메뉴입니다.");
        String password;
        MemberDTO memberDTO;

        while (true) {
            memberDTO = login();
            System.out.println("수정할 password 입력");
            password = br.readLine().trim();
            if (password.isEmpty()) {
                System.out.println("올바른 password를 입력해주세요");
                continue;
            }
            memberDTO.setPasswd(password);
            break;
        }

        memberDao.updateMember(memberDTO);
        System.out.println("비밀번호변경완료");


    }

    public void resignation() throws Exception {
        MemberDTO memberDTO = login();
        if(memberDTO != null) {
            memberDao.deleteMember(memberDTO.getiD());
            System.out.println("회원 탈퇴 성공");
        }
        System.out.println("회원 실패 성공");


    }

    private boolean blankCheck(ArrayList<String> list){
        boolean blankflag = false;
        for (String data:list){
            if (data.isEmpty()){
                blankflag = true;
                break;
            }
        }
        return blankflag;
    }


}
