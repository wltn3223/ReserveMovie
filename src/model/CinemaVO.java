package model;

import java.util.Objects;

public class CinemaVO {

    private int cinemaNo;
    private int totalSeats;
    private int remainSeats;

    public CinemaVO(int cinemaNo, int totalSeats, int remainSeats) {
        this.cinemaNo = cinemaNo;
        this.totalSeats = totalSeats;
        this.remainSeats = remainSeats;
    }

    public CinemaVO(int totalSeats, int remainSeats) {
        this.totalSeats = totalSeats;
        this.remainSeats = remainSeats;
    }

    public int getCinemaNo() {
        return cinemaNo;
    }


    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRemainSeats() {
        return remainSeats;
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
        return  cinemaNo == cinema.getCinemaNo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinemaNo);
    }

    @Override
    public String toString() {
        return "CinemaVO{" +
                "cinemaNo=" + cinemaNo +
                ", totalSeats=" + totalSeats +
                ", remainSeats=" + remainSeats +
                '}';
    }
}
