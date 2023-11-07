package service;

import model.MemberDao;

public class AdminMemberService {
    private final MemberDao memberDao = new MemberDao();
    public void printMemberList() throws Exception{
        System.out.println("현재 회원 목록");
        memberDao.selectMemberList().forEach(System.out::println);
    }

    public void printMemberMoneyList() throws Exception{
        System.out.println("현재 회원 구매 목록");
        memberDao.selectMemberMoneyList().forEach(System.out::println);
    }
}
