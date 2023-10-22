package model;

import java.util.Objects;

public class CinemaVO {
    private int cinemaID;

    private int cinemaNo;
    private String moiveTitle;
    private int totalSeats;
    private int remainSeats;
    private String startTime;
    private String finishTime;

    public CinemaVO(int cinemaID, int cinemaNo, String moiveTitle, int totalSeats, int remainSeats, String startTime, String finishTime) {
        this.cinemaID = cinemaID;
        this.cinemaNo = cinemaNo;
        this.moiveTitle = moiveTitle;
        this.totalSeats = totalSeats;
        this.remainSeats = remainSeats;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public int getCinemaNo() {
        return cinemaNo;
    }

    public String getMoiveTitle() {
        return moiveTitle;
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
        if(!(obj instanceof CinemaVO)){
            return false;
        }
        CinemaVO cinema = (CinemaVO) obj;
        return cinemaID == cinema.cinemaID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaID);
    }
}
