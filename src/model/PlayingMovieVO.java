package model;

import java.util.Objects;

public class PlayingMovieVO {
    private String movieTitle;
    private int cinemaNo;
    private int playingMovieNo;
    private  int remainseat;
    private String startTime;
    private String finishTime;

    public int getRemainseat() {
        return remainseat;
    }

    public PlayingMovieVO(String movieTitle, int cinemaNo, int totalseat, String startTime, String finishTime) {
        this.movieTitle = movieTitle;
        this.cinemaNo = cinemaNo;
        this.remainseat = totalseat;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public PlayingMovieVO(String movieTitle, int cinemaNo, String startTime, String finishTime) {
        this.movieTitle = movieTitle;
        this.cinemaNo = cinemaNo;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    public PlayingMovieVO(String movieTitle, int cinemaNo, int remainseat, int playingMovieNo, String startTime, String finishTime) {
        this.movieTitle = movieTitle;
        this.cinemaNo = cinemaNo;
        this.playingMovieNo = playingMovieNo;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.remainseat = remainseat;
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public int getCinemaNo() {
        return cinemaNo;
    }

    public int getPlayingMovieNo() {
        return playingMovieNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof PlayingMovieVO)){
            return false;
        }
        PlayingMovieVO pmn = (PlayingMovieVO) o;
        return this.playingMovieNo == pmn.getPlayingMovieNo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingMovieNo);
    }

    @Override
    public String toString() {
        return "PM_no : " + playingMovieNo +
                " 영화제목 :" + movieTitle + '\'' +
                " 상영장소: " + cinemaNo + "관 " +
                "현재 남은 좌석: " + remainseat +"석" +
                ", 시작시간='" + startTime + '\'' +
                ", 종료시간='" + finishTime + '\'';

    }
}
