package service;


import model.MovieDao;
import model.MovieVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class adminService {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final MovieDao movieDao = new MovieDao();


    public void insertMovie() throws Exception {
        String title;
        String runTime;
        String director;
        String country;
        String genre;
        String date;

        while (true) {
            System.out.println("추가할 영화 이름 입력.");
            title = br.readLine().trim();
            MovieVO movieVO = movieDao.findMoive(title);
            if (movieVO != null) {
                System.out.println("중복된 아이디입니다.");
                continue;
            }
            break;
        }
        System.out.println("영화 상영 길이 입력");
        runTime = br.readLine().trim();
        if(runTime.isEmpty()){
            System.out.println("잘못입력하셨습니다.");
            return;
        }
        System.out.println("영화 감독 입력");
        director = br.readLine().trim();
        if(director.isEmpty()){
            System.out.println("잘못입력하셨습니다.");
            return;
        }
        System.out.println("영화 개봉 국가 입력");
        country = br.readLine().trim();
        if(country.isEmpty()){
            System.out.println("잘못입력하셨습니다.");
            return;
        }
        System.out.println("영화 장르 입력");
        genre = br.readLine().trim();
        if( genre.isEmpty()){
            System.out.println("잘못입력하셨습니다.");
            return;
        }
        while (true) {
            System.out.println("영화 개봉 날짜 입력(예2017/04/24)");
            date = br.readLine().trim();
            if (date.split("/").length != 3) {
                System.out.println("잘못입력하셨습니다. 다시입력해주세요");
                continue;
            }
            break;
        }
        MovieVO movieVO = new MovieVO(title,runTime,director,country,genre,date);
        movieDao.insertMovie(movieVO);

    }

    public void deleteMovie() throws Exception {
        System.out.println("삭제할 영화 제목 입력");
        String title = br.readLine().trim();
        if (movieDao.findMoive(title) == null){
            System.out.println("존재 하지 않는 영화 입니다.");
            return;
        }
        movieDao.deleteMovie(title);
    }

    public void updateMovie() throws Exception {
        String runTime;
        String director;
        String country;
        String genre;
        String date;
        System.out.println("수정할 영화 제목 입력");
        String title = br.readLine().trim();
        MovieVO movieVO = movieDao.findMoive(title);
        if(movieVO  == null){
            System.out.println("존재 하지 않는 영화 입니다.");
            return;
        }
        System.out.println("영화 상영 길이 입력");
        runTime = br.readLine().trim();
        if (runTime.isEmpty()){
            runTime = movieVO.getRunTime();
        }
        System.out.println("영화 감독 입력");
        director = br.readLine().trim();
        if (director.isEmpty()){
            director= movieVO.getDirector();
        }
        System.out.println("영화 개봉 국가 입력");
        country = br.readLine().trim();
        if (country.isEmpty()){
            country = movieVO.getCountry();
        }
        System.out.println("영화 장르 입력");
        genre = br.readLine().trim();
        if (genre.isEmpty()){
            genre = movieVO.getGenre();
        }
        System.out.println("수정할 개봉 날짜입력(예2017/04/24)");
        date = br.readLine().trim();
        if (date.isEmpty()){
            date = movieVO.getReleaseDate();
        }
        if (date.split("/").length != 3){
            System.out.println("날짜 형식을 잘못입력하셨습니다.");
            return;
        }


        movieDao.updateMovie(new MovieVO(title,runTime,director,country,genre,date));



    }

    public void printMovie() throws Exception {
        System.out.println("현재 상영중인 영화 목록");
        movieDao.selectMovieList().stream().forEach(System.out::println);

    }


}
