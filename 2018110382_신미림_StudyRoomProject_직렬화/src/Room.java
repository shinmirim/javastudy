import java.io.Serializable;
import java.util.Calendar;

public class Room implements Serializable{
	static final long serialVersionUID=100;
	private int roomSize; // ���͵�� ũ�� (���� �ο�)
	private int roomNum; // ���͵�� ��ȣ
	private int price; // ���� (�� ����)
	private boolean isEmpty = true; // ���͵�� ���� (����� �� true)
	private long checkInTime; // üũ�� �ð�
	private long checkOutTime; // üũ�ƿ� �ð�
	private int payment; // ���� �ݾ�
	
	User user = new User();
	
	// ���͵�� ũ��(roomSize)�� ���͵�� ��ȣ(roomNum)�� �޴� ������ �޼ҵ�
	Room(int roomSize, int roomNum, int price) {
		this.roomSize = roomSize;
		this.roomNum = roomNum;
		this.price = price;
	}
	
	// ���� �����͸� �����ϴ� �޼ҵ�
	void setRoomData(boolean isEmpty, long checkInTime, String name, String phoneNum) {
		setIsEmpty(isEmpty);
		this.checkInTime = checkInTime;
		user.setName(name);
		user.setPhoneNum(phoneNum);
	}
	
	// ���͵�� ũ��(roomSize)�� ��ȯ�ϴ� �޼ҵ�
	int getRoomSize() {
		return roomSize;
	}
	
	// ���͵�� ��ȣ(roomNum)�� ��ȯ�ϴ� �޼ҵ�
	int getRoomNum() {
		return roomNum;
	}
	
	// ���͵�� ����(price)�� ��ȯ�ϴ� �޼ҵ�
	int getPrice() {
		return price;
	}
	
	int getPayment() {
		return payment;
	}
	
	// ���͵�� ����(isEmpty)�� ��ȯ�ϴ� �޼ҵ�
	boolean isEmpty() {
		return isEmpty;
	}
	
	// ���͵�� ����(isEmpty)�� �����ϴ� �޼ҵ�
	void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	// üũ�� �ð�(checkInTime)�� �����ϴ� �޼ҵ�
	void setCheckInTime() {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		
		checkInTime = date.getTimeInMillis();
	}
	
	// üũ�� �ð�(checkInTime)�� ��ȯ�ϴ� �޼ҵ�
	long getCheckInTime() {
		return checkInTime;
	}
	
	// üũ�ƿ� �ð�(checkOutTime)�� �����ϴ� �޼ҵ�
	void setCheckOutTime() {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		
		checkOutTime = date.getTimeInMillis();
	}
	
	// üũ�ƿ� �ð�(checkOutTime)�� ��ȯ�ϴ� �޼ҵ�
	long getCheckOutTime() {
		return checkOutTime;
	}
	
	// ���� �ݾ�(payment)�� ����ϴ� �޼ҵ�
	int payment() {
		long differ = (checkOutTime - checkInTime) / 1000 / 60;
		payment = (int) (differ) * price;
		
		if(payment == 0) {
			payment = price;
		}
		
		return payment;
	}
	
	// üũ�� �޼ҵ� (User Ŭ���� ��ü ����)
	void checkIn(String name, String phoneNum) {
		user.setName(name);
		user.setPhoneNum(phoneNum);
		
		isEmpty = false;
		setCheckInTime();
	}
	
	// üũ�ƿ� �޼ҵ�
	void checkOut() {
		isEmpty = true;
		user.setName("");
		user.setPhoneNum("");
		this.checkInTime = 0;
		this.checkOutTime = 0;
	}
	
	// User Ŭ���� ��ü�� ��ȯ�ϴ� �޼ҵ�
	User getUser() {
		return user;
	}
	
	
	@Override
	public String toString() {
		return "<"+roomSize+","+roomNum+","+price+","+isEmpty+">";
	}
	

}