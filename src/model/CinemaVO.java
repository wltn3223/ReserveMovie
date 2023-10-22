package model;

import java.util.Objects;

public class CinemaVO {
    private int cinemaNo;
    private String moiveTitle;
    private int totalSeats;
    private int remainSeats;
    private String startTime;

    public CinemaVO(int cinemaNo, String moiveTitle, int totalSeats, int remainSeats, String startTime) {
        this.cinemaNo = cinemaNo;
        this.moiveTitle = moiveTitle;
        this.totalSeats = totalSeats;
        this.remainSeats = remainSeats;
        this.startTime = startTime;
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

    public void setRemainSeats(int remainSeats) {
        this.remainSeats = this.totalSeats - remainSeats;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CinemaVO)){
            return false;
        }
        CinemaVO cinema = (CinemaVO) obj;
        return cinemaNo == cinema.cinemaNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaNo);
    }
}
