import java.io.Serializable;

public class User implements Serializable{
	private String name; // 사용자 이름
	private String phoneNum; // 사용자 전화번호
	
	User() {
		this.name = " ";
		this.phoneNum = " ";
	}
	
	// 사용자 이름(name)을 받는 메소드
	void setName(String name) {
		this.name = name;
	}
	
	// 사용자 이름(name)을 반환하는 메소드
	String getName() {
		return name;
	}
	
	// 사용자 전화번호(phoneNum)를 받는 메소드
	void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	// 사용자 전화번호(phoneNum)를 반환하는 메소드
	String getPhoneNum() {
		return phoneNum;
	}
}