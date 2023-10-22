package Controller;

import model.MemberDTO;
import view.Mainview;


import static service.MemberService.*;


public class MainController {
    static MemberDTO member = null;

    public static void main(String[] args) {
        try {
            boolean flag = false;
            while (!flag) {
                String choice = Mainview.getMenuChoice();
                switch (choice) {
                    case "0":
                        System.out.println("종료합니다.");
                        return;
                    case "1":
//                        member = login();
                        if (member != null) {
                            flag = true;
                        }
                        break;
                    case "2":
//                        join();
                        break;
                    case "3":
//                        updatepassword();
                        break;
                    case "4":
//                        resignation();
                        break;
                    default:
                        System.out.println("잘못된 값을 입력하셨습니다.");
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
