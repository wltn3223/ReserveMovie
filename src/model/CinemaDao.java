package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CinemaDao {
    public void insertCinema(CinemaDTO cinemaDTO) throws Exception{
        Connection con;
        con = DBUtil.getConnection();

        PreparedStatement pstmt = null;
        String query = "insert into cinema values(cinema_seq.nextval,?,?,?,?,?,?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cinemaDTO.getCinemaNo());
            pstmt.setString(2, cinemaDTO.getMovieTitle());
            pstmt.setInt(3, cinemaDTO.getTotalSeats());
            pstmt.setInt(4, cinemaDTO.getRemainSeats());
            pstmt.setString(5, cinemaDTO.getStartTime());
            pstmt.setString(6, cinemaDTO.getFinishTime());
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "상영관추가 성공":"상영관추가 실패");

        }catch (SQLException e){
            System.out.println(e);
        }finally {
            con.close();
            pstmt.close();
        }
    }
    public void updateCinema(int cinemaId, CinemaDTO cinema) throws Exception{
        CinemaDTO cinemaDTO = cinema;
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String query = "update cinema set C_NO = ?, MOVIE_TITLE = ?, C_TOTAL_SEATS  = ?, C_REMAIN_SEATS = ?,  C_START_TIME = ?, C_FINISH_TIME  =?" +
                "where C_ID = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cinemaDTO.getCinemaNo());
            pstmt.setString(2, cinemaDTO.getMovieTitle());
            pstmt.setInt(3, cinemaDTO.getTotalSeats());
            pstmt.setInt(4, cinemaDTO.getRemainSeats());
            pstmt.setString(5, cinemaDTO.getStartTime());
            pstmt.setString(6, cinemaDTO.getFinishTime());
            pstmt.setInt(7, cinemaId);
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "수정 성공":"수정 실패");

        }catch (SQLException e){
            System.out.println("상영관 정보 수정 오류 발생");
        }finally {

            con.close();
            pstmt.close();
        }


    }
    public ArrayList<CinemaDTO> selectCinemaList() throws  Exception{
        ArrayList<CinemaDTO> cinemaDTOArrayList = new ArrayList<>();
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        MovieVO movieVO;
        PreparedStatement pstmt = null;
        String query = "select * from CINEMA";
        try {
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int cinemaId = rs.getInt("C_ID");
                int cinemaNo = rs.getInt("C_NO");
                String movieTitle = rs.getString("MOVIE_TITLE");
                int totalSeats = rs.getInt("C_REMAIN_SEATS");
                int remainSeats = rs.getInt("C_TOTAL_SEATS");
                String startTime =  rs.getString("C_START_TIME");
                String finishTime =  rs.getString("C_FINISH_TIME");
                CinemaDTO cinemaDTO = new CinemaDTO(cinemaId,cinemaNo,movieTitle,totalSeats,remainSeats,startTime,finishTime);

                cinemaDTOArrayList.add(cinemaDTO);

            }

        }catch (SQLException e){
            System.out.println("상영관 목록 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return cinemaDTOArrayList;
    }
    public void deleteCinema(int cinemaId) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        CinemaDTO cinemaDTO = findCinema(cinemaId);
        if (cinemaDTO == null){
            System.out.println("잘못된 상영관 ID입니다.");
            return;
        }
        try {
            String query = "delete from CINEMA where C_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,cinemaId);
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "삭제 성공":"삭제 실패");

        }catch (SQLException e){
            System.out.println("영화 삭제 오류 발생");
        }finally {
            con.close();
            pstmt.close();

        }

    }
    public CinemaDTO findCinema(int cinemaId) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        CinemaDTO cinemaDTO  = null;
        PreparedStatement pstmt = null;
        try {
            String query = "select * from CINEMA where C_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,cinemaId);
            rs = pstmt.executeQuery();
            while (rs.next()){
                cinemaId = rs.getInt("C_ID");
                int cinemaNo = rs.getInt("C_NO");
                String movieTitle = rs.getString("MOVIE_TITLE");
                int totalSeats = rs.getInt("C_REMAIN_SEATS");
                int remainSeats = rs.getInt("C_TOTAL_SEATS");
                String startTime =  rs.getString("C_START_TIME");
                String finishTime =  rs.getString("C_FINISH_TIME");
               cinemaDTO = new CinemaDTO(cinemaId,cinemaNo,movieTitle,totalSeats,remainSeats,startTime,finishTime);
            }

        }catch (SQLException e){
            System.out.println("특정 상영관 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return cinemaDTO;
    }
}

