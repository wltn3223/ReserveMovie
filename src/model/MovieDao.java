package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDao {
    public void insertMovie(MovieVO movieVO) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String query = "insert into movie values(?,?,?,?,?,?)";
        try {
        pstmt = con.prepareStatement(query);
        pstmt.setString(1,movieVO.getTitle());
        pstmt.setString(2, movieVO.getRunTime());
        pstmt.setString(3, movieVO.getDirector());
        pstmt.setString(4, movieVO.getCountry());
        pstmt.setString(5, movieVO.getGenre());
        pstmt.setString(6, movieVO.getReleaseDate());
        int i = pstmt.executeUpdate();
        System.out.println(i !=0 ? "추가 성공":"추가 실패");

        }catch (SQLException e){
            System.out.println("영화 삽입 오류 발생");
        }finally {
        con.close();
        pstmt.close();

        }
    }
    public ArrayList<MovieVO> movieList() throws Exception{
        ArrayList<MovieVO> movieVOArrayList = new ArrayList<>();
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        MovieVO movieVO;
        PreparedStatement pstmt = null;
        String query = "select * from movie";
        try {
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()){
            String title = rs.getString("MOVIE_TITLE");
            String runTime = rs.getString("MOVIE_RUNNINGTIME");
            String director = rs.getString("MOVIE_DIRECTOR");
            String conuntry = rs.getString("MOVIE_COUNTRY");
            String genre = rs.getString("MOVIE_GENRE");
            String releaseDate =  rs.getString("MOVIE_RELEASE_DATE");
            movieVO = new MovieVO(title,runTime,director,conuntry,genre,releaseDate);
            movieVOArrayList.add(movieVO);

        }

        }catch (SQLException e){
            System.out.println("영화 목록 조회 오류 발생");
            return null;
        }finally {
        con.close();
        pstmt.close();
        rs.close();

        }
        return movieVOArrayList;
    }
    public void updateMovie(MovieVO movieVO) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String query = "update movie set MOVIE_TITLE = ?, MOVIE_RUNNINGTIME = ?, MOVIE_DIRECTOR = ?, MOVIE_COUNTRY = ?,  MOVIE_GENRE = ?, MOVIE_RELEASE_DATE =?" +
                "where MOVIE_TITLE = ?";
        try {
        pstmt = con.prepareStatement(query);
        pstmt.setString(1,movieVO.getTitle());
        pstmt.setString(2, movieVO.getRunTime());
        pstmt.setString(3, movieVO.getDirector());
        pstmt.setString(4, movieVO.getCountry());
        pstmt.setString(5, movieVO.getGenre());
        pstmt.setString(6, movieVO.getReleaseDate());
        pstmt.setString(7,movieVO.getTitle());
        int i = pstmt.executeUpdate();
        System.out.println(i !=0 ? "수정 성공":"수정 실패");

        }catch (SQLException e){
            System.out.println("영화 수정 오류 발생");
        }finally {

        con.close();
        pstmt.close();
        }
    }
    public void deleteMovie(String title) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        MovieVO movieVO = findMoive(title);
        if (movieVO == null){
            System.out.println("없는 제목입니다.");
            return;
        }
        try {
        String query = "delete from movie where MOVIE_TITLE = ? ";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1,movieVO.getTitle());
        int i = pstmt.executeUpdate();
        System.out.println(i !=0 ? "삭제 성공":"삭제 실패");

        }catch (SQLException e){
            System.out.println("영화 삭제 오류 발생");
        }finally {
        con.close();
        pstmt.close();

        }

    }
    public MovieVO findMoive(String title) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        MovieVO movieVO = null;
        PreparedStatement pstmt = null;
        try {
        String query = "select * from movie where MOVIE_TITLE = ?";
       pstmt = con.prepareStatement(query);
        pstmt.setString(1,title);
        rs = pstmt.executeQuery();
        while (rs.next()){
            title = rs.getString("MOVIE_TITLE");
            String runTime = rs.getString("MOVIE_RUNNINGTIME");
            String director = rs.getString("MOVIE_DIRECTOR");
            String conuntry = rs.getString("MOVIE_COUNTRY");
            String genre = rs.getString("MOVIE_GENRE");
            String releaseDate =  rs.getString("MOVIE_RELEASE_DATE");
            movieVO = new MovieVO(title,runTime,director,conuntry,genre,releaseDate);

        }

        }catch (SQLException e){
            System.out.println("영화 조회 오류 발생");
            return null;
        }finally {
        con.close();
        pstmt.close();
        rs.close();

        }
        return movieVO;
    }
}
