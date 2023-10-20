package model;

import java.util.Objects;

public class MemberVO {
	private String Member_id;
	private String Member_passwd;
	private String Member_name;
	private String Member_PhoneNum;
	private char Member_sex;
	
	public MemberVO(String member_id, String member_passwd, String member_name, String member_PhoneNum,
			char member_sex) {
		super();
		Member_id = member_id;
		Member_passwd = member_passwd;
		Member_name = member_name;
		Member_PhoneNum = member_PhoneNum;
		Member_sex = member_sex;
	}

	public String getMember_id() {
		return Member_id;
	}

	public String getMember_passwd() {
		return Member_passwd;
	}

	public String getMember_name() {
		return Member_name;
	}

	public String getMember_PhoneNum() {
		return Member_PhoneNum;
	}

	public char getMember_sex() {
		return Member_sex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Member_id);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MovieVO)){
			return false;
		}
		MemberVO other = (MemberVO) obj;
		return  this.Member_id.equals(other.getMember_id());
	}
	
}
