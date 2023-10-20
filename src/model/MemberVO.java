package model;

import java.util.Objects;

public class MemberVO {
	private String memberId;
	private String memberPasswd;
	private String memberName;
	private String memberPhoneNum;
	
	public String getMemberId() {
		return memberId;
	}

	public String getMemberPasswd() {
		return memberPasswd;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberPhoneNum() {
		return memberPhoneNum;
	}

	public char getMemberSex() {
		return memberSex;
	}

	private char memberSex;
	
	
	public MemberVO(String memberId, String memberPasswd, String memberName, String memberPhoneNum, char memberSex) {
		super();
		this.memberId = memberId;
		this.memberPasswd = memberPasswd;
		this.memberName = memberName;
		this.memberPhoneNum = memberPhoneNum;
		this.memberSex = memberSex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(memberId);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MovieVO)){
			return false;
		}
		MemberVO other = (MemberVO) obj;
		return  this.memberId.equals(other.getMemberId());
	}
	
}
