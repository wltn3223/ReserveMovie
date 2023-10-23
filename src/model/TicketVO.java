package model;

import java.util.Objects;

public class TicketVO {
    private int ticketNo;
    private int playingMovieNo;
    private int seatNo;
    private static final int ticketPrice = 12000;

    public TicketVO(int ticketNo, int playingMovieNo, int seatNo) {
        this.ticketNo = ticketNo;
        this.playingMovieNo = playingMovieNo;
        this.seatNo = seatNo;
    }

    public TicketVO(int playingMovieNo, int seatNo) {
        this.playingMovieNo = playingMovieNo;
        this.seatNo = seatNo;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public int getPlayingMovieNo() {
        return playingMovieNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TicketVO)){
            return false;
        }
        TicketVO ticketVO = (TicketVO) o;
        return ticketNo == ticketVO.ticketNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo);
    }
}
