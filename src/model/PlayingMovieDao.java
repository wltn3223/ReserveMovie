package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayingMovieDao {
    public void insertPlayingMovie(PlayingMovieVO playingMovieVO){

    }
    public void deletePlayingMovie(int pmNo) throws Exception{
        if (findPlayingMovie(pmNo) == null){
            System.out.println("없는 번호 입니다.");
            return;
        }
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = "delete from PLAYINGMOIVE where PM_NO = ?";
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, pmNo);
            int count = pstmt.executeUpdate();
            System.out.println("상영할 영화 삭제 완료");

        } catch (Exception e) {
            System.out.println("상영할 영화 삭제 sql에러발생");
        }finally {
            con.close();
            pstmt.close();

        }

    }
    public  ArrayList<PlayingMovieVO> SelectPlayingMovieList() throws Exception{
        ArrayList< PlayingMovieVO> playingMovieList = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO memberVO;
        try {
            String query = "Select * from PLAYINGMOVIE";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("MOVIE_TITLE");
                int cinemaNo = rs.getInt("C_NO");
                int playingmovieNo = rs.getInt("PM_NO");
                String startTime = rs.getString("PM_START_TIME");
                String finishTime = rs.getString("PM_FINISH_TIME");
                PlayingMovieVO playingMovieVO = new PlayingMovieVO(title,cinemaNo,playingmovieNo,startTime,finishTime);
                playingMovieList.add(playingMovieVO);
            }

        } catch (Exception e) {
            System.out.println("상영할 영화 목록 sql 에러발생");
            return playingMovieList;
        } finally {
            con.close();
            pstmt.close();
            rs.close();


        }
        return playingMovieList;

    }
    public void UpdatePlayingMovie(int pmNo) throws Exception{


    }

    public PlayingMovieVO findPlayingMovie(int pmNo) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        PlayingMovieVO playingMovieVO = null;
        PreparedStatement pstmt = null;
        try {
            String query = "select * from movie where PM_NO = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,pmNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String movieTitle = rs.getString("MOVIE_TITLE");
                int cNo = rs.getInt("C_NO");
                pmNo = rs.getInt("PM_NO");
                String startTime = rs.getString("PM_START_TIME");
                String finishTime =  rs.getString("PM_FINISH_TIME");
                playingMovieVO = new PlayingMovieVO(movieTitle,cNo,pmNo,startTime,finishTime);

            }

        }catch (SQLException e){
            System.out.println("상열할 영화 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return playingMovieVO;

    }


}
