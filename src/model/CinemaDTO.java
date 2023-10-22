package model;

import java.util.Objects;

public class CinemaDTO {
    private int cinemaID;

    private int cinemaNo;
    private String movieTitle;
    private int totalSeats;
    private int remainSeats;
    private String startTime;
    private String finishTime;

    public CinemaDTO(int cinemaNo, String movieTitle, int totalSeats, int remainSeats, String startTime, String finishTime) {
        this.cinemaNo = cinemaNo;
        this.movieTitle = movieTitle;
        this.totalSeats = totalSeats;
        this.remainSeats = remainSeats;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public CinemaDTO(int cinemaID, int cinemaNo, String movieTitle, int totalSeats, int remainSeats, String startTime, String finishTime) {
        this.cinemaID = cinemaID;
        this.cinemaNo = cinemaNo;
        this.movieTitle = movieTitle;
        this.totalSeats = totalSeats;
        this.remainSeats= remainSeats;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public int getCinemaNo() {
        return cinemaNo;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRemainSeats() {
        return remainSeats;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setRemainSeats(int remainSeats) {
        this.remainSeats = this.totalSeats - remainSeats;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CinemaDTO)){
            return false;
        }
        CinemaDTO cinema = (CinemaDTO) obj;
        return cinemaID == cinema.getCinemaID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaID);
    }

    @Override
    public String toString() {
        return "CinemaDTO{" +
                "cinemaID=" + cinemaID +
                ", cinemaNo=" + cinemaNo +
                ", movieTitle='" + movieTitle + '\'' +
                ", totalSeats=" + totalSeats +
                ", remainSeats=" + remainSeats +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
