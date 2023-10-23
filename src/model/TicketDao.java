package model;

import Controller.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDao {
    public void insertTicket(TicketVO ticketVO) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();

        PreparedStatement pstmt = null;
        String query = "insert into ticket(MEMBER_ID, PM_NO,SEAT_NO,TICKET_PRICE) values(?,?,?,?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,ticketVO.getMemberId());
            pstmt.setInt(2,ticketVO.getPlayingMovieNo());
            pstmt.setInt(3,ticketVO.getSeatNo());
            pstmt.setInt(4,ticketVO.getTicketPrice());
            int i = pstmt.executeUpdate();
            System.out.println(i !=0 ? "영화 예매 성공":"영화 예매 실패");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            con.close();
            pstmt.close();

        }
    }
    public void deleteTicket(int ticketNo) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        PreparedStatement pstmt = null;

        try {
            String query = "delete from ticket where TICKET_NO = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ticketNo);
            int i = pstmt.executeUpdate();
            System.out.println("예매 취소 성공");

        }catch (SQLException e){
            System.out.println("예매 취소 오류 발생");
        }finally {
            con.close();
            pstmt.close();

        }
    }
    public void updateTicket(int seatNo, int ticketNo) throws  Exception{

        Connection con;
        con = DBUtil.getConnection();

        PreparedStatement pstmt = null;
        String query = "update ticket set SEAT_NO = ? where TICKET_NO = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,seatNo);
            pstmt.setInt(2,ticketNo);
            int i = pstmt.executeUpdate();
            System.out.println("좌석 변경 성공");

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
        PreparedStatement pstmt = null;
        TicketVO ticketVO = null;
        try {
            String query = "select * from ticket where TICKET_NO  = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,ticketNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String memberId = rs.getString("MEMBER_ID");
                int cinemaNo = rs.getInt("TICKET_NO");
                int pmNo = rs.getInt("PM_NO");
                int seatNo =  rs.getInt("SEAT_NO");
                ticketVO = new TicketVO(memberId,cinemaNo,pmNo,seatNo);
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
    public ArrayList<TicketVO> findTicketList(String memberid) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        ArrayList<TicketVO> arrayList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            String query = "select * from ticket where MEMBER_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,memberid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String memberId = rs.getString("MEMBER_ID");
                int cinemaNo = rs.getInt("TICKET_NO");
                int pmNo = rs.getInt("PM_NO");
                int seatNo =  rs.getInt("SEAT_NO");
                TicketVO ticketVO = new TicketVO(memberId,cinemaNo,pmNo,seatNo);
                arrayList.add(ticketVO);
            }

        }catch (SQLException e){
            System.out.println("내 전체 예매 내역 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return arrayList;
    }
    public ArrayList<TicketVO> findTicketList() throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        ArrayList<TicketVO> arrayList = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            String query = "select * from ticket";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String memberId = rs.getString("MEMBER_ID");
                int cinemaNo = rs.getInt("TICKET_NO");
                int pmNo = rs.getInt("PM_NO");
                int seatNo =  rs.getInt("SEAT_NO");
                TicketVO ticketVO = new TicketVO(memberId,cinemaNo,pmNo,seatNo);
                arrayList.add(ticketVO);
            }

        }catch (SQLException e){
            System.out.println("전체 예매 내역 조회 오류 발생");
            return null;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return arrayList;
    }
    public int findseat(int pmNo,int seatNo) throws  Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        TicketVO ticketVO = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            String query = "select count(*) from ticket a where PM_NO  = ?  and  SEAT_NO = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,pmNo);
            pstmt.setInt(2,seatNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
               count  = rs.getInt("count(*)");
            }

        }catch (SQLException e){
            System.out.println("좌석 중복 조회오류 발생");
            return 0;
        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return count;
    }
    public boolean checkseat(int seat, int cinemaNo) throws Exception{
        Connection con;
        con = DBUtil.getConnection();
        ResultSet rs = null;
        int totalseat = 0;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            String query = "select * from CINEMA where C_NO = ? ";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,cinemaNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                totalseat  = rs.getInt("C_TOTAL_SEAT");
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            con.close();
            pstmt.close();
            rs.close();

        }
        return  seat<=totalseat;


    }
}
