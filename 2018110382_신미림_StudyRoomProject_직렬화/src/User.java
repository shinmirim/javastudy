import java.io.Serializable;

public class User implements Serializable{
	private String name; // ����� �̸�
	private String phoneNum; // ����� ��ȭ��ȣ
	
	User() {
		this.name = " ";
		this.phoneNum = " ";
	}
	
	// ����� �̸�(name)�� �޴� �޼ҵ�
	void setName(String name) {
		this.name = name;
	}
	
	// ����� �̸�(name)�� ��ȯ�ϴ� �޼ҵ�
	String getName() {
		return name;
	}
	
	// ����� ��ȭ��ȣ(phoneNum)�� �޴� �޼ҵ�
	void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	// ����� ��ȭ��ȣ(phoneNum)�� ��ȯ�ϴ� �޼ҵ�
	String getPhoneNum() {
		return phoneNum;
	}
}