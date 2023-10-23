package service;

import model.CinemaDao;
import model.CinemaVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdminCinemaService {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final CinemaDao cinemaDao = new CinemaDao();
    public void addCinema() throws Exception{
        System.out.println("추가할 상영관 좌석 수 입력");
        int seats = Integer.parseInt(br.readLine().trim());
        CinemaVO cinemaVO = new CinemaVO(seats,seats);
        cinemaDao.insertCinema(cinemaVO);
        System.out.println("추가완료");
    }

    public void deleteCinema() throws  Exception{
        printCinemaList();
        System.out.println("삭제할 상영관 번호 입력 ");
        int cinemaNo = Integer.parseInt(br.readLine().trim());
        CinemaVO cinema = cinemaDao.findCinema(cinemaNo);
        if (cinema == null){
            System.out.println("잘못된 상영관 번호를 입력하셨습니다.");
            return;
        }
        cinemaDao.deleteCinema(cinemaNo);

    }
    public void updateCinemaSeats() throws  Exception{
        printCinemaList();
        System.out.println("수정할 상영관 번호 입력 ");
        int cinemaNo = Integer.parseInt(br.readLine().trim());
        System.out.println("수정할 좌석 수 입력");
        int seats = Integer.parseInt(br.readLine().trim());
        cinemaDao.updateCinemaSeat(cinemaNo,seats);


    }
    public void printCinemaList() throws Exception{
        cinemaDao.selectCinemaList().forEach(System.out::println);
    }

}
