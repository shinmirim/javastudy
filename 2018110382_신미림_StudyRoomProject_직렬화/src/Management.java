import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Management {
	private ArrayList<Room> room = new ArrayList<Room>(); // ���͵�� �迭 -> ArrayList
	private int[][] income = new int[12][31]; // �� �� ���� �迭 = ����x ����Ʈ�� �����ϴ� ���� �� ��ȿ�����̶� ����
	private int roomCount = 0; // ���͵�� ����
	private int password = 1234; // ������ ��� ���� ��й�ȣ
	
	// Room Ŭ���� ��ü�� ��ȯ�ϴ� �޼ҵ�
	ArrayList<Room> getRoom() {
		return room;
	}
	
	// ���͵�� ����(roomCount)�� �����ϴ� �޼ҵ�
	void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	
	// ���͵�� ����(roomCount)�� ��ȯ�ϴ� �޼ҵ�
	int getRoomCount() {
		return room.size();
	}
	
	// ������ ��� ���� ��й�ȣ(password)�� ��ȯ�ϴ� �޼ҵ�
	int getPassword() {
		return password;
	}
	
	// n�ν� ���͵���� �߰��ϴ� �޼ҵ�
	boolean addRoom(int roomSize, int roomNum, int price) {
		boolean exist= false; // ���͵�� ��ȣ(roomNum) �ߺ� Ȯ�ο�
		boolean result = false;
		
		// ������ ��ȣ(roomNum)�� ���� ���͵���� ������ exist�� true�� ��ȯ
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum) {
				exist = true;
				break;
			}
		}
			
				
		
		// exist�� false�� ���� ���͵�� ����
		if (!exist) {
			room.add(new Room(roomSize, roomNum, price));
			result = true;
		}
		
		// ���͵�� ������ ���������� �̷�������� ��ȯ
		return result;
	}
	
	// ���� �����͸� �����Ͽ� Room Ŭ���� ��ü(���͵��)�� ����(����)�ϴ� �޼ҵ�
	void setRoomData(ArrayList<Room> list) {
		
		room = list;
		
		/*
		 * room.add(i,new Room(roomSize, roomNum, price));
		 * room.get(i).setRoomData(isEmpty, checkInTime, name, phoneNum);
		 */
	}
	
	// n�ν� ���͵���� �����ϴ� �޼ҵ�
	boolean deleteRoom(int roomNum) {
		boolean result = false;
		
		
		
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum) {
				room.remove(i);
				result = true;
			}
		}
		
		return result;
	}
	
	// �� ���͵���� ã�� ��ȯ�ϴ� �޼ҵ�
	List<Room> searchEmptyRoom(int roomSize) {
		List<Room> emptyRoom; // �� ���͵�� �迭 -> List
		
			// �� ���͵�� �迭 ���� -> ArrayList
			emptyRoom = new ArrayList<Room>();
			
			
			// �迭�� ���� �� ���͵���� ã�� emptyRoom�� ���ʴ�� ���͵�� ��ȣ ���� -> ArrayList
			for (int i = 0; i < room.size(); i++)
				if (room.get(i).getRoomSize() == roomSize && room.get(i).isEmpty() == true) {
					emptyRoom.add(room.get(i));
				}  
			
			return emptyRoom;
			
		
	}
	
	// �̿� ���� ���͵���� ã�� ��ȯ�ϴ� �޼ҵ�
	List<Room> searchUsingRoom(int roomSize) {
		List<Room> usingRoom; // �̿� ���� ���͵�� �迭 -> List
		
			// �̿� ���� ���͵�� �迭 ���� -> ArrayList
			usingRoom = new ArrayList<Room>();
			
			
			// �迭�� ���� �̿� ���� ���͵���� ã�� usingRoom�� ���ʴ�� ���͵�� ��ȣ ���� -> ArrayList
			for (int i = 0; i < room.size(); i++) {
				if (room.get(i).getRoomSize() == roomSize && room.get(i).isEmpty() == false) {
					usingRoom.add(room.get(i));
				}
			}
				
			
			return usingRoom;
			
		
	}
	
	// ����(income)�� �����ϴ� �޼ҵ�, ���� ������Ʈ
	void setIncome(int payment) {
		// ��¥ Ȯ��
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		// ��¥�� �´� �迭�� ���� ������Ʈ 
		income[month][date - 1] += payment;
	}
	
	// �Ϸ� ������ ��ȯ�ϴ� �޼ҵ�
	int getDailyIncome(int month, int date) {
		return income[month - 1][date - 1];
	}
	
	// �� �� ������ ��ȯ�ϴ� �޼ҵ�
	int getMonthIncome(int month) {
		int monthIncome = 0;
		
		for (int i = 0; i < 31; i++)
			monthIncome += income[month - 1][i];
		
		return monthIncome;
	}
	
	// �� ������ ��ȯ�ϴ� �޼ҵ�
	int getTotalIncome() {
		int totalIncome = 0;
		
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 31; j++)
				totalIncome += income[i][j];
		
		return totalIncome;
	}
	
	// üũ�� �޼ҵ�
	boolean checkIn(int roomNum, String name, String phoneNum) {
		boolean result = false;
		
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum && room.get(i).isEmpty() == true) {
				room.get(i).checkIn(name, phoneNum); // User Ŭ���� ��ü ����
				
				result = true;
			}
		}
		
		// üũ���� ���������� �̷�������� ��ȯ
		return result;
	}
	
	// üũ�ƿ� �޼ҵ�
	int checkOut(int roomNum, String phoneNum) {
		int result = 0; // 0 : üũ�ƿ� �Ұ���, 1 : ��ȭ��ȣ ����ġ, 2 : üũ�ƿ� ����
		int payment;
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum && room.get(i).isEmpty() == false) {
				if (phoneNum.equals(room.get(i).getUser().getPhoneNum())) {
					room.get(i).setCheckOutTime(); // üũ�ƿ� �ð� Ȯ��
					payment = room.get(i).payment(); // ���� �ݾ� ���
					setIncome(payment); // ���� ������Ʈ
					room.get(i).checkOut(); // üũ�ƿ�
					result = 2;
				}
				else result = 1;
			}
		}
		
		// üũ�ƿ��� ���������� �̷�������� ��ȯ
		return result;
	}
	
	// ���� �ݾ��� ��ȯ�ϴ� �޼ҵ�
	int getPayment(int roomNum) {
		int payment = 0;
		
		
		for (int i = 0; i < room.size(); i++)
			if (room.get(i).getRoomNum() == roomNum)
				payment = room.get(i).getPayment();
		
		return payment;
	}
}