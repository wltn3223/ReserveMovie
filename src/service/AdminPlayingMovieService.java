package service;

import model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminPlayingMovieService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final PlayingMovieDao playingMovieDao = new PlayingMovieDao();

    public void addPlayingMovie()throws  Exception{
        AdminMovieService adminMovieService = new AdminMovieService();
        AdminCinemaService adminCinemaService = new AdminCinemaService();
        MovieDao movieDao = new MovieDao();
        String movie;
        int cinemaNo;
        String startTime;
        String finishTime;


        while (true) {
            adminMovieService.printMovie();
            System.out.println("상영할 영화 제목 입력");
            movie = br.readLine();
            if (movieDao.findMoive(movie) == null) {
                System.out.println("저희 영화관에 없는 영화입니다.");
                continue;
            }
            break;
        }
        while (true) {
            adminCinemaService.printCinemaList();
            System.out.println("사용할 상영관 번호 입력");
            try {
                cinemaNo = Integer.parseInt(br.readLine().trim());
                if (adminCinemaService.findCinema(cinemaNo) == null) {
                    System.out.println("없는 상영관 번호입니다. 다시 시도해주세요.");
                    continue;
                }
                break;
            } catch (NumberFormatException E) {
                System.out.println("잘못된 형식을 입력하셨습니다. 다시시도해주세요");
                return;
            }
        }
        while (true) {
            System.out.println("영화 시작 일자와 시간을 입력해주세요(예:202310242200)");
            startTime = br.readLine().trim();
            System.out.println("영화 끝나는 일자와 시간을 입력해주세요(예:202310242200)");
            finishTime = br.readLine().trim();
            if (startTime.length() == 12 && finishTime.length() == 12){
                break;
            }
            System.out.println("올바른 형식으로 입력해주세요.");
        }
            PlayingMovieVO playingMovieVO = new PlayingMovieVO(movie,cinemaNo,startTime,finishTime);
            if(playingMovieDao.checkOverlapTime(playingMovieVO)){
                System.out.println("상영 시간이 중복됩니다. 다시시도해주세요");
                return;
            }
            playingMovieDao.insertPlayingMovie(playingMovieVO);

    }
    public void printPlayingMovieList() throws  Exception{
        System.out.println("현재 시네마에서 상영중인 영화목록 출력");
        ArrayList<PlayingMovieVO> playingMovieVOS = playingMovieDao.SelectPlayingMovieList();
        if (playingMovieVOS.isEmpty()) System.out.println("현재 상영중인 영화가없습니다.");
        playingMovieVOS.forEach(System.out::println);

    }
    public void deletePlayingMovie()throws  Exception{
        printPlayingMovieList();
        int no;
        System.out.println("제거할 영화 번호 입력");
        try {
        no = Integer.parseInt(br.readLine().trim());
        }catch (NumberFormatException e){
            System.out.println("올바른 형식을 입력해주세요 다시시도해주세요");
            return;
        }
        PlayingMovieVO playingMovie = playingMovieDao.findPlayingMovie(no);
        if (playingMovie == null){
            System.out.println("없는 번호입니다. 다시시도해주세요");
            return;
        }
        playingMovieDao.deletePlayingMovie(no);

    }
    public void updatePlayingMovie()throws  Exception{

        AdminCinemaService adminCinemaService = new AdminCinemaService();
        printPlayingMovieList();
        int no;
        int cinemaNo;
        String startTime;
        String finishTime;
        PlayingMovieVO playingMovie;
        System.out.println("수정할 상영 영화 번호 입력");
        try {
            no = Integer.parseInt(br.readLine().trim());
        }catch (NumberFormatException e){
            System.out.println("올바른 형식을 입력해주세요 다시시도해주세요");
            return;
        }
        playingMovie = playingMovieDao.findPlayingMovie(no);
        if (playingMovie == null){
            System.out.println("없는 번호입니다. 다시시도해주세요");
            return;
        }
        while (true) {
            adminCinemaService.printCinemaList();
            System.out.println("사용할 상영관 번호 입력");
            try {
                cinemaNo = Integer.parseInt(br.readLine().trim());
                if (adminCinemaService.findCinema(cinemaNo) == null) {
                    System.out.println("없는 상영관 번호입니다. 다시 시도해주세요.");
                    continue;
                }
                break;
            } catch (NumberFormatException E) {
                System.out.println("잘못된 형식을 입력하셨습니다. 다시시도해주세요");
                return;
            }
        }
        while (true) {
            System.out.println("영화 시작 일자와 시간을 입력해주세요(예:202310242200)");
            startTime = br.readLine().trim();
            System.out.println("영화 끝나는 일자와 시간을 입력해주세요(예:202310242200)");
            finishTime = br.readLine().trim();
            if (startTime.length() == 12 && finishTime.length() == 12){
                break;
            }
            System.out.println("올바른 형식으로 입력해주세요.");
        }
        PlayingMovieVO playingMovieVO = new PlayingMovieVO(playingMovie.getMovieTitle(), cinemaNo, playingMovie.getPlayingMovieNo(), startTime,finishTime);
        if(playingMovieDao.checkOverlapTime(playingMovieVO)){
            System.out.println("상영 시간이 중복됩니다. 다시시도해주세요");
            return;
        }
        playingMovieDao.UpdatePlayingMovie(playingMovieVO);
    }

}
