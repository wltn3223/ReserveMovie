package model;

import java.util.Objects;

public class TicketVO {
    private String memberId;
    private int ticketNo;
    private int cinemaId;
    private String movieTitle;
    private int seatNum;
    final private int seatPrice;

    public TicketVO(String memberId, int cinemaId, String movieTitle, int seatNum) {
        this.memberId = memberId;
        this.cinemaId = cinemaId;
        this.movieTitle = movieTitle;
        this.seatNum = seatNum;
        this.seatPrice = 12000;
    }


    public TicketVO(String memberId, int ticketNo, int cinemaId, String movieTitle, int seatNum, int seatPrice) {
        this.memberId = memberId;
        this.ticketNo = ticketNo;
        this.cinemaId = cinemaId;
        this.movieTitle = movieTitle;
        this.seatNum = seatNum;
        this.seatPrice = 12000;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public int getSeatPrice() {
        return seatPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TicketVO)){
            return false;
        }
        TicketVO ticketVO = (TicketVO) o;
        return ticketNo == ticketVO.ticketNo && seatNum == ticketVO.seatNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo);
    }
}
