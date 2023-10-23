package service;

import model.MemberDao;

public class AdminMemberService {
    private final MemberDao memberDao = new MemberDao();
    public void printMemberList() throws Exception{
        memberDao.selectMemberList().forEach(System.out::println);
    }
}
