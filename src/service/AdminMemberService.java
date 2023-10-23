package service;

import model.MemberDao;

public class AdminMemberService {
    private final MemberDao memberDao = new MemberDao();
    public void printMemberList() throws Exception{
        System.out.println("현재 회원 목록");
        memberDao.selectMemberList().forEach(System.out::println);
    }
}
