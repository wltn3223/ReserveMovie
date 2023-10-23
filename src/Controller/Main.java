package Controller;

import model.*;

import service.*;
import view.Mainview;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        MemberloginService memberloginService = new MemberloginService();
        MemberTicketService memberTicketService = new MemberTicketService();
        AdminCinemaService adminCinemaService = new AdminCinemaService();
        AdminPlayingMovieService adminPlayingMovieService = new AdminPlayingMovieService();
        AdminMovieService adminMovieService = new AdminMovieService();
        MemberVO memberVO = null;
        boolean flag = false;
        boolean adminmode = false;
        while (!flag) {
            try {
                String menuChoice = Mainview.getMenuChoice();
                switch (menuChoice) {

                    case "0":
                        System.out.println("시스템을 종료합니다.");
                        return;
                    case "1":
                        memberVO = memberloginService.login();
                        if (memberVO != null) {
                            flag = true;
                        }
                        break;
                    case "2":
                        memberloginService.join();
                        break;
                    case "3":
                        memberloginService.updatepassword();
                        break;
                    case "4":
                        memberloginService.resignation();
                        break;
                    case "admin1234":
                        System.out.println("관리자로그인");
                        flag = true;
                        adminmode = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
            }
        }

        if (adminmode) {
            while (true) {
                try {
                    String adminMenu = Mainview.getAdminMenu();
                    switch (adminMenu) {
                        case "0":
                            System.out.println("시스템을 종료합니다.");
                            return;
                        case "1":
                            boolean stopFlag = false;
                            while (!stopFlag){
                            String movieAdminMenu = Mainview.getMovieAdminMenu();
                                switch (movieAdminMenu){
                                    case "0":
                                        stopFlag = true;
                                    break;
                                    case "1":
                                        adminMovieService.insertMovie();
                                    break;
                                    case "2":
                                        adminMovieService.printMovie();
                                        break;
                                    case "3":
                                        adminMovieService.updateMovie();
                                        break;
                                    case "4":
                                        adminMovieService.deleteMovie();
                                        break;
                                }
                            }
                            break;
                        case "2":
                            boolean stopFlag2 = false;
                            while (!stopFlag2){
                            String cinemaAdminMenu = Mainview.getCinemaAdminMenu();
                                switch (cinemaAdminMenu){
                                    case "0":
                                        stopFlag2 = true;
                                        break;
                                    case "1":
                                        adminCinemaService.addCinema();
                                        break;
                                    case "2":
                                        adminCinemaService.printCinemaList();
                                        break;
                                    case "3":
                                        adminCinemaService.updateCinemaSeats();
                                        break;
                                    case "4":
                                        adminCinemaService.deleteCinema();
                                        break;
                                }
                            }
                            break;
                        case "3":
                            boolean stopFlag3 = false;
                            while (!stopFlag3){
                            String playingMovieAdminMenu = Mainview.getPlayingMovieAdminMenu();
                                switch (playingMovieAdminMenu){
                                    case "0":
                                        stopFlag3 = true;
                                        break;
                                    case "1":
                                        adminPlayingMovieService.addPlayingMovie();
                                        break;
                                    case "2":
                                        adminPlayingMovieService.printPlayingMovieList();
                                        break;
                                    case "3":
                                        adminPlayingMovieService.updatePlayingMovie();
                                        break;
                                    case "4":
                                        adminPlayingMovieService.deletePlayingMovie();
                                        break;
                                }
                            }
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
                }

            }

        }

            flag = false;
        while (!flag){
            try {
                String userReserveMenu = Mainview.getUserReserveMenu();
                switch (userReserveMenu){
                    case "0":
                        flag = true;
                        break;
                    case "1":
                        memberTicketService.reserveMovie(memberVO.getiD());
                        break;
                    case "2":
                        memberTicketService.viewTicketList(memberVO.getiD());
                        break;
                    case "3":
                        memberTicketService.cancle(memberVO.getiD());
                        break;
                    case "4":
                        memberTicketService.changeSeat(memberVO.getiD());
                        break;

                }

            }catch (Exception e){
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            }
        }
        System.out.println("시스템을 종료합니다.");


    }
}
