package service;


import model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MemberTicketService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final TicketDao ticketDao = new TicketDao();
    private final CinemaDao cinemaDao = new CinemaDao();
   public void reserveMovie(String memberId) throws  Exception{
       PlayingMovieDao playingMovieDao = new PlayingMovieDao();
       AdminPlayingMovieService adminPlayingMovieService = new AdminPlayingMovieService();
       int movieNo = 0;
       int seatNo = 0;
       System.out.println("좌석 예매");
       adminPlayingMovieService.printPlayingMovieList();
       try {
       System.out.println("예매할 영화 번호 선택");
       movieNo = Integer.parseInt(br.readLine().trim());
       System.out.println("좌석 번호 선택");
       seatNo = Integer.parseInt(br.readLine().trim());

       }catch (NumberFormatException e){
           System.out.println("잘못된 좌석번호를 입력하셨습니다. 다시 시도해주세요");
           return;
       }
       boolean checkseat = ticketDao.checkseat(seatNo,   movieNo);
       if (!checkseat){
           System.out.println("없는 좌석입니다. 다시 시도해주세요");
           return;
       }
       if (ticketDao.findseat(movieNo,seatNo) != 0){
           System.out.println("이미 예약된 좌석입니다. 다시시도해주세요");
           return;
       }
       TicketVO ticketVO = new TicketVO(memberId,movieNo,seatNo);
       ticketDao.insertTicket(ticketVO);
       PlayingMovieVO playingMovie = playingMovieDao.findPlayingMovie(ticketVO.getPlayingMovieNo());
       cinemaDao.minusCinemaSeat(playingMovie.getCinemaNo());

   }
    public void cancle(String memberid) throws  Exception{
        PlayingMovieDao playingMovieDao = new PlayingMovieDao();
        System.out.println("예매 취소");
        viewTicketList(memberid);
        ArrayList<TicketVO> ticketList = ticketDao.findTicketList(memberid);
        TicketVO ticket = null;
        if (ticketList.isEmpty()) return;
        System.out.println("삭제할 티켓 번호 입력");
        try {
        int no = Integer.parseInt(br.readLine().trim());
            ticket = ticketDao.findTicket(no);
            if (ticket == null){
                System.out.println("잘못된 번호를 입력하셨습니다. 다시 시도 해주세요");
                return;
            }
            ticketDao.deleteTicket(no);
        }catch (NumberFormatException e){
            System.out.println("숫자를 입력해주세요 다시시도해주세요.");
        }
        PlayingMovieVO playingMovie = playingMovieDao.findPlayingMovie(ticket.getPlayingMovieNo());
        cinemaDao.plusCinemaSeat(playingMovie.getCinemaNo());




    }
    public void changeSeat(String memberid) throws Exception{
        PlayingMovieDao playingMovieDao = new PlayingMovieDao();
        System.out.println("좌석 변경");
        viewTicketList(memberid);
        ArrayList<TicketVO> ticketList = ticketDao.findTicketList(memberid);
        if (ticketList.isEmpty()) return;
        System.out.println("좌석 변경할 티켓 번호 입력");
        try {
            int ticketNo = Integer.parseInt(br.readLine().trim());
            TicketVO ticket = ticketDao.findTicket(ticketNo);
            if (ticket == null){
                System.out.println("잘못된 번호를 입력하셨습니다. 다시 시도 해주세요");
                return;
            }
            System.out.println("변경할 좌석 입력");
            int seatNo = Integer.parseInt(br.readLine().trim());
            PlayingMovieVO playingMovie = playingMovieDao.findPlayingMovie(ticket.getPlayingMovieNo());
            boolean checkseat = ticketDao.checkseat(seatNo,playingMovie.getCinemaNo());
            if (!checkseat){
                System.out.println("없는 좌석입니다. 다시 시도해주세요");
                return;
            }
            if (ticketDao.findseat(ticket.getPlayingMovieNo(),seatNo) != 0){
                System.out.println("이미 예약된 좌석입니다. 다시시도해주세요");
                return;
            }
            ticketDao.updateTicket(seatNo, ticket.getTicketNo());

        }catch (NumberFormatException e){
            System.out.println("숫자를 입력해주세요 다시시도해주세요.");
        }
    }
    public void viewTicketList(String memberid) throws Exception{
        System.out.println("내 예매 내역");
        ArrayList<TicketVO> ticketList = ticketDao.findTicketList(memberid);
        if (ticketList.isEmpty()) System.out.println("현재 예매한 티켓이 없습니다.");
        ticketList.forEach(System.out::println);
    }




}
