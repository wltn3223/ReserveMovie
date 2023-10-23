package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CinemaDao {
    public void insertCinema(CinemaVO cinemaVO) throws Exception{
        Connection con;
        con = DBUtil.getConnection();

        PreparedStatement pstmt = null;
        String query = "insert into cinema(C_TOTAL_SEAT,C_REMAIN_SEAT) values(?,?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cinemaVO.getTotalSeats());
            pstmt.setInt(2, cinemaVO.getRemainSeats());
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "상영관 추가 성공":"상영관 추가 실패");

        }catch (SQLException e){
            System.out.println(e);
        }finally {
            con.close();
            pstmt.close();
        }
    }
    public void updateCinemaSeat(int cinemaNo,int peopleNo) throws Exception{
        if (findCinema(cinemaNo) == null){
            System.out.println("잘못된 상영관 번호입니다.");
            return;
        }
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String query = "update cinema set C_REMAIN_SEAT = C_REMAIN_SEAT - ? where C_NO = ? " ;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, peopleNo);
            pstmt.setInt(2, cinemaNo);
            pstmt.executeUpdate();
            System.out.println("잔여 좌석 수정 성공");

        }catch (SQLException e){
            System.out.println("상영관 정보 수정 오류 발생");
        }finally {

            con.close();
            pstmt.close();
        }


    }
    public ArrayList<CinemaVO> selectCinemaList() throws  Exception{
        ArrayList<CinemaVO> cinemaVOArrayList = new ArrayList<>();
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String query = "select * from CINEMA";
        try {
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int cinemaNo = rs.getInt("C_NO");
                int totalSeats = rs.getInt("C_TOTAL_SEAT");
                int remainSeats = rs.getInt("C_REMAIN_SEAT");
                CinemaVO cinemaVO = new CinemaVO(cinemaNo,totalSeats,remainSeats);

                cinemaVOArrayList.add(cinemaVO);


            }

        }catch (SQLException e){
            System.out.println("상영관 목록 조회 에러 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return cinemaVOArrayList;
    }
    public void deleteCinema(int cinemaNo) throws Exception{
        if (findCinema(cinemaNo) == null){
            System.out.println("잘못된 상영관 번호입니다.");
            return;
        }
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        CinemaVO cinemaVO = findCinema(cinemaNo);
        if (cinemaVO == null){
            System.out.println("잘못된 상영관 ID입니다.");
            return;
        }
        try {
            String query = "delete from CINEMA where C_NO = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,cinemaNo);
            pstmt.executeUpdate();
            System.out.println("상영관 삭제 성공");

        }catch (SQLException e){
            System.out.println("상영관 삭제 오류 발생");
        }finally {
            con.close();
            pstmt.close();

        }

    }
    public CinemaVO findCinema(int cinemaNo) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        CinemaVO cinemaVO = null;
        PreparedStatement pstmt = null;
        try {
            String query = "select * from CINEMA where C_NO  = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,cinemaNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                cinemaNo = rs.getInt("C_NO");
                int totalSeats = rs.getInt("C_TOTAL_SEAT");
                int remainSeats = rs.getInt("C_REMAIN_SEAT");
               cinemaVO = new CinemaVO(cinemaNo,totalSeats,remainSeats);
            }

        }catch (SQLException e){
            System.out.println("특정 상영관 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return cinemaVO;
    }
}

