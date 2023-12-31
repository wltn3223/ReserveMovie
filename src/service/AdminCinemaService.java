package service;

import model.CinemaDao;
import model.CinemaVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdminCinemaService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final CinemaDao cinemaDao = new CinemaDao();

    public void addCinema() throws Exception {
        System.out.println("추가할 상영관 좌석 수 입력(최대 50,최소 20)");
        String data = br.readLine().trim();
        int seats = 0;
        try {

            seats = Integer.parseInt(data);
            if (seats >= 50 || seats < 20) {
                System.out.println("20~50사이의 숫자를 입력해주세요 다시 시도해주세요");
                return;
            }

        } catch (Exception e) {
            System.out.println("올바른 숫자를 입력해주세요 다시 시도해주세요");
            return;
        }
        CinemaVO cinemaVO = new CinemaVO(seats, seats);
        cinemaDao.insertCinema(cinemaVO);
    }

    public void deleteCinema() throws Exception {
        printCinemaList();
        try {
        System.out.println("삭제할 상영관 번호 입력 ");
        int cinemaNo = Integer.parseInt(br.readLine().trim());
        CinemaVO cinema = cinemaDao.findCinema(cinemaNo);
        cinemaDao.deleteCinema(cinemaNo);
        if (cinema == null) {
            System.out.println("잘못된 상영관 번호를 입력하셨습니다.");

        }

        }catch (Exception e){
            System.out.println("올바른 숫자를 입력해주세요.");
        }

    }



    public void printCinemaList() throws Exception {
        System.out.println("현재 사용가능한 상영관 목록 출력");
        if (cinemaDao.selectCinemaList() == null) {
            System.out.println("현재 사용가능한 상영관이 존재하지 않습니다.");
        } else {
            cinemaDao.selectCinemaList().stream().forEach(System.out::println);
        }
    }
    public CinemaVO findCinema(int cinemaNo) throws  Exception{
        CinemaVO cinema = cinemaDao.findCinema(cinemaNo);
        return cinema;
    }

}
