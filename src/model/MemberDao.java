package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {
    public ArrayList<MemberDTO> selectMemberList() throws Exception {
        ArrayList<MemberDTO> memberDTOArrayList = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO memberDTO;
        try {
            String query = "Select * from member";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("MEMBER_ID");
                String passwd = rs.getString("MEMBER_PASSWD");
                String name = rs.getString("MEMBER_NAME");
                String phoneNum = rs.getNString("MEMBER_PHONENUM");
                String email = rs.getString("MEMBER_EMAIL");
                char sex = rs.getString("MEMBER_SEX").charAt(0);
                memberDTO = new MemberDTO(id, passwd, name, phoneNum, email, sex);
                memberDTOArrayList.add(memberDTO);
            }

        } catch (Exception e) {
            System.out.println("회원 목록 sql 에러발생");
            return memberDTOArrayList;
        } finally {
                con.close();
                pstmt.close();
                rs.close();


        }
        return memberDTOArrayList;
    }

    // id를 통해 멤버 찾음
    public MemberDTO findMember(String memberId) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO memberDTO = null;
        try {
            String query = "Select * from member where MEMBER_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String id = rs.getString("MEMBER_ID");
            String passwd = rs.getString("MEMBER_PASSWD");
            String name = rs.getString("MEMBER_NAME");
            String phoneNum = rs.getNString("MEMBER_PHONENUM");
            String email = rs.getString("MEMBER_EMAIL");
            char sex = rs.getString("MEMBER_SEX").charAt(0);
            memberDTO = new MemberDTO(id, passwd, name, phoneNum, email, sex);

        } catch (Exception e) {
            System.out.println("회원찾기 sql 에러");
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return memberDTO;
    }

    // 회원가입시 멤버정보 table에 추가
    public void insertMember(MemberDTO memberDTO)  throws  Exception{
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "insert into member values(?,?,?,?,?,?) ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, memberDTO.getiD());
            pstmt.setString(2, memberDTO.getPasswd());
            pstmt.setString(3, memberDTO.getName());
            pstmt.setString(4, memberDTO.getPhoneNum());
            pstmt.setString(5, memberDTO.getEmail());
            pstmt.setString(6, String.valueOf(memberDTO.getSex()));
            int count = pstmt.executeUpdate();
            if (count != 1) {
                System.out.println("회원 정보 저장 실패");
            }
        } catch (Exception e) {
            System.out.println("회원 삽입 sql오류발생");
        }finally {
            con.close();
            pstmt.close();

        }
    }

    // 계정삭제 id로
    public void deleteMember(String memberid) throws  Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = "delete from member where MEMBER_ID = ?";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, memberid);
            int count = pstmt.executeUpdate();
            if (count != 1) {
                System.out.println("회원 정보 삭제 실패");
            }
        } catch (Exception e) {
            System.out.println("회원 삭제 sql에러발생");
        }finally {
            con.close();
            pstmt.close();

        }

    }

    public void updateMember(MemberDTO memberDTO) throws  Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "update member set MEMBER_ID = ?, MEMBER_PASSWD = ?, MEMBER_NAME =?, MEMBER_PHONENUM = ?, MEMBER_EMAIL = ?, MEMBER_SEX = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, memberDTO.getiD());
            pstmt.setString(2, memberDTO.getPasswd());
            pstmt.setString(3, memberDTO.getName());
            pstmt.setString(4, memberDTO.getPhoneNum());
            pstmt.setString(5, memberDTO.getEmail());
            pstmt.setString(6, String.valueOf(memberDTO.getSex()));
            int count = pstmt.executeUpdate();
            if (count != 1) {
                System.out.println("회원 정보 수정 실패");
            }
        } catch (Exception e) {
            System.out.println("회원 정보 수정 sql 오류 발생");
        } finally {
                con.close();
                pstmt.close();
        }
    }

    // 아이디 번호 중복 여부
    public boolean findID(String memberId) throws  Exception{
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        boolean findFlag = false;
        try {
            String query = "Select * from member where MEMBER_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, memberId);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                findFlag = true;
            }

        } catch (Exception e) {
            System.out.println("회원 id 중복 찾기 sql 에러발생");
        }finally {
            con.close();
            pstmt.close();

        }

        return findFlag;
    }

    // 이메일 번호 중복 여부
    public boolean findEmail(String email) throws  Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        boolean findFlag = false;
        try {
            String query = "Select * from member where MEMBER_EMAIL = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                findFlag = true;
            }

        } catch (Exception e) {
            System.out.println("이메일 중복 sql 에러발생");
        }finally {
            con.close();
            pstmt.close();
        }
        return findFlag;
    }

    // 스마트폰 번호 중복 여부
    public boolean findPhone(String phoneNum) throws  Exception{
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        boolean findFlag = false;
        try {
            String query = "Select * from member where MEMBER_PHONENUM = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, phoneNum);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                findFlag = true;
            }

        } catch (Exception e) {
            System.out.println("폰 중복 찾기 sql 에러발생");
        }finally {
            con.close();
            pstmt.close();

        }

        return findFlag;
    }
}
