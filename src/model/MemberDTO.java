package model;

import java.lang.reflect.Member;
import java.util.Objects;

public class MemberDTO {
    private  String iD;
    private String passwd;
    private String name;
    private String phoneNum;
    private String email;
    private char sex;

    public MemberDTO(String iD, String passwd, String name, String phoneNum, String email, char sex) {
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

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Member)){
            return false;
        }
        MemberDTO memberDTO = (MemberDTO) obj;
        return this.iD.equals(memberDTO.getiD());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD);
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "iD='" + iD + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                '}';
    }
}
