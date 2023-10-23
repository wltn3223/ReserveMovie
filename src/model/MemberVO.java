package model;

import java.lang.reflect.Member;
import java.util.Objects;

public class MemberVO {
    private  String iD;
    private String passwd;
    private String name;
    private String phoneNum;
    private String email;
    private char sex;

    public MemberVO(String iD, String passwd, String name, String phoneNum, String email, char sex) {
        this.iD = iD;
        this.passwd = passwd;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sex = sex;
    }

    public String getiD() {
        return iD;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public char getSex() {
        return sex;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Member)){
            return false;
        }
        MemberVO memberVO = (MemberVO) obj;
        return this.iD.equals(memberVO.getiD());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD);
    }

    @Override
    public String toString() {
        return
                "아이디='" + iD + '\'' +
                ", 비밀번호='" + passwd + '\'' +
                ", 이름='" + name + '\'' +
                ", 전화번호='" + phoneNum + '\'' +
                ", 이메일='" + email + '\'' +
                ", 성별=" + sex;
    }
}
