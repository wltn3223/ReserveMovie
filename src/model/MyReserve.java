package model;

public class MyReserve {
    int ticketNum;
    String member_id;
    String movieTitle;
    int seatNum;
    String startTime;
    String finishTime;
    int price;

    public MyReserve(int ticketNum, String member_id, String movieTitle, int seatNum, String startTime, String finishTime, int price) {
        this.ticketNum = ticketNum;
        this.member_id = member_id;
        this.movieTitle = movieTitle;
        this.seatNum = seatNum;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.price = price;
    }

    @Override
    public String toString() {
        return "티켓번호=" + ticketNum +
                ", 내 ID='" + member_id + '\'' +
                ", 영화 제목='" + movieTitle + '\'' +
                ", 좌석 번호=" + seatNum +
                ", 시작 시간='" + startTime + '\'' +
                ", 종료 시간='" + finishTime + '\'' +
                ", 가격=" + price ;
    }
}
