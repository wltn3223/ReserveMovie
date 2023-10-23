package model;

import java.util.Objects;

public class TicketVO {
    private String memberId;
    private int ticketNo;
    private int playingMovieNo;
    private int seatNo;
    private static final int ticketPrice = 12000;

    public TicketVO(String memberId,int ticketNo, int playingMovieNo, int seatNo) {
        this.memberId = memberId;
        this.ticketNo = ticketNo;
        this.playingMovieNo = playingMovieNo;
        this.seatNo = seatNo;
    }

    public TicketVO(String memberId,int playingMovieNo, int seatNo) {
        this.memberId = memberId;
        this.playingMovieNo = playingMovieNo;
        this.seatNo = seatNo;
    }

    public String getMemberId() {
        return memberId;
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
        return ticketNo == ticketVO.getTicketNo() && memberId.equals(ticketVO.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo,memberId);
    }

    @Override
    public String toString() {
        return
                "티켓번호=" + ticketNo +
                ", 상영번호=" + playingMovieNo +
                ", 좌석번호=" + seatNo;
    }
}
