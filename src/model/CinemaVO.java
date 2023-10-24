package model;

import java.util.Objects;

public class CinemaVO {

    private int cinemaNo;
    private int totalSeats;
    

  
    public CinemaVO(int cinemaNo, int totalSeats) {
		super();
		this.cinemaNo = cinemaNo;
		this.totalSeats = totalSeats;
	}

	public CinemaVO(int totalSeats) {
        this.totalSeats = totalSeats;
      
    }

    public int getCinemaNo() {
        return cinemaNo;
    }


    public int getTotalSeats() {
        return totalSeats;
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
        return "상영관번호=" + cinemaNo +
                ", 총좌석수=" + totalSeats;
    }
}
