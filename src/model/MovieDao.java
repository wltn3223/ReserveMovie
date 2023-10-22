package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovieDao {
    public void insertMovie(MovieVO movieVO) throws Exception{
        Connection con = null;
        con = DBUtil.getConnection();
        String query = "insert into movie values(?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,movieVO.getTitle());
        pstmt.setString(2, movieVO.getRunTime());
        pstmt.setString(3, movieVO.getDirector());
        pstmt.setString(4, movieVO.getCountry());
        pstmt.setString(5, movieVO.getGenre());
        pstmt.setString(6, movieVO.getReleaseDate());
        int i = pstmt.executeUpdate();
        System.out.println(i !=0 ? "추가 성공":"추가 실패");
        con.close();
        pstmt.close();
    }
    public void movieList() throws Exception{
        Connection con = null;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        MovieVO movieVO = null;
        String query = "select * from movie";
        PreparedStatement pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()){
            String title = rs.getString("MOVIE_TITLE");
            String runTime = rs.getString("MOVIE_RUNNINGTIME");
            String director = rs.getString("MOVIE_DIRECTOR");
            String conuntry = rs.getString("MOVIE_COUNTRY");
            String genre = rs.getString("MOVIE_GENRE");
            String releaseDate =  rs.getString("MOVIE_RELEASE_DATE ");
            movieVO = new MovieVO(title,runTime,director,conuntry,genre,releaseDate);
            System.out.println(movieVO);

        }
        con.close();
        pstmt.close();
        rs.close();
    }
    public void updateMovie(MovieVO movieVO) throws Exception{
        Connection con = null;
        con = DBUtil.getConnection();
        String query = "update moive set MOVIE_TITLE = ?, MOVIE_RUNNINGTIME = ?, MOVIE_DIRECTOR = ?, MOVIE_COUNTRY = ?,  MOVIE_GENRE = ?, MOVIE_RELEASE_DATE =?" +
                "where MOVIE_TITLE = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,movieVO.getTitle());
        pstmt.setString(2, movieVO.getRunTime());
        pstmt.setString(3, movieVO.getDirector());
        pstmt.setString(4, movieVO.getCountry());
        pstmt.setString(5, movieVO.getGenre());
        pstmt.setString(6, movieVO.getReleaseDate());
        pstmt.setString(7,movieVO.getTitle());
        int i = pstmt.executeUpdate();
        System.out.println(i !=0 ? "수정 성공":"수정 실패");
        con.close();
        pstmt.close();
    }
    public void deleteMovie(String title) throws Exception{
        Connection con = null;
        con = DBUtil.getConnection();

        MovieVO movieVO = findMoive(title);
        if (movieVO == null){
            return;
        }
        String query = "delete from movie where MOVIE_TITLE = ?  ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,movieVO.getTitle());
        int i = pstmt.executeUpdate();
        System.out.println(i !=0 ? "삭제 성공":"삭제 실패");
        con.close();
        pstmt.close();
    }
    public MovieVO findMoive(String title) throws Exception{
        Connection con = null;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        MovieVO movieVO = null;
        String query = "select * from movie where  ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,title);
        rs = pstmt.executeQuery();
        while (rs.next()){
            title = rs.getString("MOVIE_TITLE");
            String runTime = rs.getString("MOVIE_RUNNINGTIME");
            String director = rs.getString("MOVIE_DIRECTOR");
            String conuntry = rs.getString("MOVIE_COUNTRY");
            String genre = rs.getString("MOVIE_GENRE");
            String releaseDate =  rs.getString("MOVIE_RELEASE_DATE ");
            movieVO = new MovieVO(title,runTime,director,conuntry,genre,releaseDate);
            System.out.println(movieVO);

        }
        con.close();
        pstmt.close();
        rs.close();
        return movieVO;
    }
}
