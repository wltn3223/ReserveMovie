package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayingMovieDao {
    public void insertPlayingMovie(PlayingMovieVO playingMovieVO) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            String query = "insert into PLAYINGMOVIE(MOVIE_TITLE,C_NO,PM_START_TIME,PM_FINISH_TIME) values(?,?,TO_DATE(?, 'YYYYMMDDHH24MISS'),TO_DATE(?, 'YYYYMMDDHH24MISS')) ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, playingMovieVO.getMovieTitle());
            pstmt.setInt(2, playingMovieVO.getCinemaNo());
            pstmt.setString(3, playingMovieVO.getStartTime());
            pstmt.setString(4, playingMovieVO.getFinishTime());
            pstmt.executeUpdate();
            System.out.println("상영할 영화 추가 완료");

        } catch (Exception e) {
            System.out.println("상영할 영화 추가 에러 발생");
        } finally {
            con.close();
            pstmt.close();

        }

    }

    public void deletePlayingMovie(int pmNo) throws Exception {
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
        } finally {
            con.close();
            pstmt.close();

        }

    }

    public ArrayList<PlayingMovieVO> SelectPlayingMovieList() throws Exception {
        ArrayList<PlayingMovieVO> playingMovieList = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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
                PlayingMovieVO playingMovieVO = new PlayingMovieVO(title, cinemaNo, playingmovieNo, startTime, finishTime);
                playingMovieList.add(playingMovieVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return playingMovieList;
        } finally {
            con.close();
            pstmt.close();
            if (rs != null) {
                rs.close();
            }


        }
        return playingMovieList;

    }

    public void UpdatePlayingMovie(PlayingMovieVO playingMovieVO) throws Exception {
        PlayingMovieVO playingMovie = playingMovieVO;
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String query = "update PLAYINGMOVIE set MOVIE_TITLE = ?, C_NO = ?,  PM_START_TIME = TO_DATE(?, 'YYYYMMDDHH24MISS'), PM_FINISH_TIME  = TO_DATE(?, 'YYYYMMDDHH24MISS')" +
                "where PM_NO = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,playingMovie.getMovieTitle());
            pstmt.setInt(2, playingMovie.getCinemaNo());
            pstmt.setString(3, playingMovie.getStartTime());
            pstmt.setString(4, playingMovie.getFinishTime());
            pstmt.setInt(5, playingMovie.getPlayingMovieNo());
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "상영할 영화 수정 성공":"상영할 영화 수정 실패");

        }catch (SQLException e){
            System.out.println("상영할 영화 수정 에러 발생");
        }finally {

            con.close();
            pstmt.close();
        }

    }

    public PlayingMovieVO findPlayingMovie(int pmNo) throws Exception {
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        PlayingMovieVO playingMovieVO = null;
        PreparedStatement pstmt = null;
        try {
            String query = "select * from PLAYINGMOVIE where PM_NO = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pmNo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String movieTitle = rs.getString("MOVIE_TITLE");
                int cNo = rs.getInt("C_NO");
                pmNo = rs.getInt("PM_NO");
                String startTime = rs.getString("PM_START_TIME");
                String finishTime = rs.getString("PM_FINISH_TIME");
                playingMovieVO = new PlayingMovieVO(movieTitle, cNo, pmNo, startTime, finishTime);
            }

        } catch (SQLException e) {
            System.out.println("상영할 영화 조회 오류 발생");
            return null;
        } finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return playingMovieVO;

    }

    public boolean checkOverlapTime(PlayingMovieVO pVO) throws Exception {
        Connection con;
        con = DBUtil.getConnection();
        boolean overLap = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "select count(*)  from PLAYINGMOVIE where C_NO = ? and " +
                    "TO_DATE(?, 'YYYYMMDDHH24MISS') <= PM_FINISH_TIME" +
                    " and  TO_DATE(?, 'YYYYMMDDHH24MISS') >= PM_START_TIME";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pVO.getCinemaNo());
            pstmt.setString(2, pVO.getStartTime());
            pstmt.setString(3, pVO.getFinishTime());
            rs = pstmt.executeQuery();
            if (rs.next()){
                int count = rs.getInt("count(*)");
                overLap = count != 0;
            }



        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            con.close();
            pstmt.close();

        }
        return overLap;


    }



}
