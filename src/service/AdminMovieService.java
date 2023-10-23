package service;


import model.MovieDao;
import model.MovieVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminMovieService {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final MovieDao movieDao = new MovieDao();


    public void insertMovie() throws Exception {
        String title;
        String date;
        ArrayList<String> info = new ArrayList<String>();

        while (true) {
            System.out.println("추가할 영화 이름 입력.");
            title = br.readLine().trim();
            MovieVO movieVO = movieDao.findMoive(title);
            if (movieVO != null) {
                System.out.println("중복된 아이디입니다.");
                continue;
            }
            info.add(title);
            break;
        }
        System.out.println("영화 상영 길이 입력");
        info.add(br.readLine().trim());

        System.out.println("영화 감독 입력");
       info.add(br.readLine().trim());

        System.out.println("영화 개봉 국가 입력");
        info.add(br.readLine().trim());

        System.out.println("영화 장르 입력");
        info.add(br.readLine().trim());

        while (true) {
            System.out.println("영화 개봉 날짜 입력(예2017-04-24)");
            date = br.readLine().trim();
            if (date.split("-").length != 3) {
                System.out.println("잘못입력하셨습니다. 다시입력해주세요");
                continue;
            }
            info.add(date);
            break;
        }
        if (blankCheck(info)){
            System.out.println("공백을 입력하셨습니다. 다시 시도해주세요");
            return;
        }
        System.out.println("영화 추가 완료");
        MovieVO movieVO = new MovieVO(info.get(0),info.get(1),info.get(2),info.get(3),info.get(4),info.get(5));
        movieDao.insertMovie(movieVO);

    }

    public void deleteMovie() throws Exception {
        printMovie();
        System.out.println("삭제할 영화 제목 입력");
        String title = br.readLine().trim();
        if (movieDao.findMoive(title) == null){
            System.out.println("존재 하지 않는 영화 입니다.");
            return;
        }
        movieDao.deleteMovie(title);
    }

    public void updateMovie() throws Exception {
        printMovie();
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
            date = movieVO.getReleaseDate().substring(0,9);
        }
        if (date.split("-").length != 3){
            System.out.println("날짜 형식을 잘못입력하셨습니다.");
            return;
        }


        movieDao.updateMovie(new MovieVO(title,runTime,director,country,genre,date));



    }

    public void printMovie() throws Exception {
        System.out.println("현재 개봉한 영화 목록");
        movieDao.selectMovieList().stream().forEach(System.out::println);

    }
    private boolean blankCheck(ArrayList<String> list){
        boolean blankflag = false;
        for (String data: list){
            if (data.isEmpty()){
                blankflag = true;
                break;
            }
        }
        return blankflag;
    }


}
