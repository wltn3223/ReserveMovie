package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDao {
    public void insertTicket(TicketVO ticketVO) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();

        PreparedStatement pstmt = null;
        String query = "insert into ticket(PM_NO,SEAT_NO,TICKET_PRICE) values(?,?,?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ticketVO.getPlayingMovieNo());
            pstmt.setInt(2,ticketVO.getTicketNo());
            pstmt.setInt(3,ticketVO.getTicketPrice());
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "영화 예매 성공":"영화 예매 실패");

        }catch (SQLException e){
            System.out.println("영화 예매 오류 발생");
        }finally {
            con.close();
            pstmt.close();

        }
    }
    public void deleteTicket(int ticketNo) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        TicketVO ticketVO = findTicket(ticketNo);
        if (ticketVO== null){
            System.out.println("잘못된 예매 번호 입니다.");
            return;
        }
        try {
            String query = "delete from ticket where TICKET_NO = ? ";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ticketVO.getTicketNo());
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "예매 취소 성공":"예매 취소 실패");

        }catch (SQLException e){
            System.out.println("예매 취소 오류 발생");
        }finally {
            con.close();
            pstmt.close();

        }
    }
    public void updateTicket(int ticketNo,int seatNo) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        TicketVO ticketVO = findTicket(ticketNo);
        if (ticketVO == null){
            System.out.println("예매 번호를 잘못 입력하셨습니다.");
            return;
        }
        PreparedStatement pstmt = null;
        String query = "update ticket set SEAT_NO = ? where TICKET_NO = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,seatNo);
            pstmt.setInt(2,ticketNo);
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "수정 성공":"수정 실패");

        }catch (SQLException e){
            System.out.println("좌석 변경 오류 발생");
        }finally {
            con.close();
            pstmt.close();

        }

    }
    public TicketVO findTicket(int ticketNo) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        TicketVO ticketVO = null;
        PreparedStatement pstmt = null;
        try {
            String query = "select * from ticket where TICKET_NO = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ticketNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int cinemaNo = rs.getInt("TICKET_NO");
                int pmNo = rs.getInt("PM_NO");
                int seatNo =  rs.getInt("SEAT_NO");
                ticketVO = new TicketVO(cinemaNo,pmNo,seatNo);
            }

        }catch (SQLException e){
            System.out.println("내 예매 내역 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return ticketVO;
    }
}
