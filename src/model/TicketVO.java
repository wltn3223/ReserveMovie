package model;

import java.util.Objects;

public class TicketVO {
    private String memberId;
    private int ticketNO;
    private int cinemaId;
    private String movieTitle;
    private String seatNum;
    final private int seatPrice = 12000;

    public TicketVO(String memberId, int ticketNO, int cinemaId, String movieTitle, String seatNum) {
        this.memberId = memberId;
        this.ticketNO = ticketNO;
        this.cinemaId = cinemaId;
        this.movieTitle = movieTitle;
        this.seatNum = seatNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getTicketNO() {
        return ticketNO;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getSeatNum() {
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
        return ticketNO == ticketVO.ticketNO;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNO);
    }
}
