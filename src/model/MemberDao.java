package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MemberDao {
    public void MemberList(){
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO memberVO = null;
        try {
            String query = "Select * from member";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
            String id = rs.getString("MEMBER_ID");
            String passwd = rs.getString("MEMBER_PASSWD");
            String name = rs.getString("MEMBER_NAME");
            String phoneNum = rs.getNString("MEMBER_PHONENUM");
            String email = rs.getString("MEMBER_EMAIL");
            char sex = rs.getString("MEMBER_SEX").charAt(0);
            memberVO = new MemberVO(id, passwd, name, phoneNum, email,sex);
            System.out.println(memberVO);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
                pstmt.close();
                rs.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // id를 통해 멤버 찾음
    public MemberVO findMember(String memberId) {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO memberVO = null;
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
            memberVO = new MemberVO(id, passwd, name, phoneNum, email,sex);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
            con.close();
            pstmt.close();
            rs.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        return memberVO;
    }
    // 회원가입시 멤버정보 table에 추가
    public void insertMember(MemberVO memberVO){
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "insert into member values(?,?,?,?,?,?) ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,memberVO.getiD());
            pstmt.setString(2,memberVO.getPasswd());
            pstmt.setString(3,memberVO.getName());
            pstmt.setString(4,memberVO.getPhoneNum());
            pstmt.setString(5,memberVO.getEmail());
            pstmt.setString(6,String.valueOf(memberVO.getSex()));
            int count = pstmt.executeUpdate();
            if(count != 1){
                System.out.println("회원 정보 저장 오류 발생");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // 계정삭제 id로
    public void deleteMember(String memberid){
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = "delete from member where MEMBER_ID = ?";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,memberid);
            int count = pstmt.executeUpdate();
            if(count != 1){
                System.out.println("회원 정보 삭제 오류 발생");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void updateMember(MemberVO memberVO ){
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "update member set MEMBER_ID = ?, MEMBER_PASSWD = ?, MEMBER_NAME =?, MEMBER_PHONENUM = ?, MEMBER_EMAIL = ?, MEMBER_SEX = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,memberVO.getiD());
            pstmt.setString(2,memberVO.getPasswd());
            pstmt.setString(3,memberVO.getName());
            pstmt.setString(4,memberVO.getPhoneNum());
            pstmt.setString(5,memberVO.getEmail());
            pstmt.setString(6,String.valueOf(memberVO.getSex()));
            int count = pstmt.executeUpdate();
            if(count != 1){
                System.out.println("회원 정보 수정 오류 발생");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
                pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // 아이디 번호 중복 여부
    public boolean findID(String memberId) {
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
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return findFlag;
    }
    // 이메일 번호 중복 여부
    public boolean findEmail(String email){
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
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return findFlag;
    }
    // 스마트폰 번호 중복 여부
    public boolean findPhone(String phoneNum){
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        boolean findFlag = false;
        try {
            String query = "Select * from member where MEMBER_PHONENUM = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,phoneNum);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                findFlag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return findFlag;
    }
}
