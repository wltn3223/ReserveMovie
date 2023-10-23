package model;

import java.util.Objects;

public class PlayingMovie {
    private String movieTitle;
    private int cinemaNo;
    private int playingMovieNo;
    private String startTime;
    private String finishTime;

    public PlayingMovie(String movieTitle, int cinemaNo, int playingMovieNo, String startTime, String finishTime) {
        this.movieTitle = movieTitle;
        this.cinemaNo = cinemaNo;
        this.playingMovieNo = playingMovieNo;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public PlayingMovie(String movieTitle, int cinemaNo, String startTime, String finishTime) {
        this.movieTitle = movieTitle;
        this.cinemaNo = cinemaNo;
        this.startTime = startTime;
        this.finishTime = finishTime;
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
        if(!(o instanceof PlayingMovie)){
            return false;
        }
        PlayingMovie pmn = (PlayingMovie) o;
        return this.playingMovieNo == pmn.getPlayingMovieNo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingMovieNo);
    }
}
