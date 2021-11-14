import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Management {
	private ArrayList<Room> room = new ArrayList<Room>(); // 스터디룸 배열 -> ArrayList
	private int[][] income = new int[12][31]; // 한 달 수입 배열 = 변경x 리스트로 변경하는 것이 더 비효율적이라 생각
	private int roomCount = 0; // 스터디룸 개수
	private int password = 1234; // 관리자 모드 접속 비밀번호
	
	// Room 클래스 객체를 반환하는 메소드
	ArrayList<Room> getRoom() {
		return room;
	}
	
	// 스터디룸 개수(roomCount)를 설정하는 메소드
	void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
	
	// 스터디룸 개수(roomCount)를 반환하는 메소드
	int getRoomCount() {
		return room.size();
	}
	
	// 관리자 모드 접속 비밀번호(password)를 반환하는 메소드
	int getPassword() {
		return password;
	}
	
	// n인실 스터디룸을 추가하는 메소드
	boolean addRoom(int roomSize, int roomNum, int price) {
		boolean exist= false; // 스터디룸 번호(roomNum) 중복 확인용
		boolean result = false;
		
		// 동일한 번호(roomNum)를 가진 스터디룸이 있으면 exist를 true로 변환
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum) {
				exist = true;
				break;
			}
		}
			
				
		
		// exist가 false일 때만 스터디룸 생성
		if (!exist) {
			room.add(new Room(roomSize, roomNum, price));
			result = true;
		}
		
		// 스터디룸 생성이 정상적으로 이루어졌는지 반환
		return result;
	}
	
	// 이전 데이터를 복구하여 Room 클래스 객체(스터디룸)를 생성(복구)하는 메소드
	void setRoomData(ArrayList<Room> list) {
		
		room = list;
		
		/*
		 * room.add(i,new Room(roomSize, roomNum, price));
		 * room.get(i).setRoomData(isEmpty, checkInTime, name, phoneNum);
		 */
	}
	
	// n인실 스터디룸을 삭제하는 메소드
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
	
	// 빈 스터디룸을 찾아 반환하는 메소드
	List<Room> searchEmptyRoom(int roomSize) {
		List<Room> emptyRoom; // 빈 스터디룸 배열 -> List
		
			// 빈 스터디룸 배열 생성 -> ArrayList
			emptyRoom = new ArrayList<Room>();
			
			
			// 배열을 돌며 빈 스터디룸을 찾아 emptyRoom에 차례대로 스터디룸 번호 대입 -> ArrayList
			for (int i = 0; i < room.size(); i++)
				if (room.get(i).getRoomSize() == roomSize && room.get(i).isEmpty() == true) {
					emptyRoom.add(room.get(i));
				}  
			
			return emptyRoom;
			
		
	}
	
	// 이용 중인 스터디룸을 찾아 반환하는 메소드
	List<Room> searchUsingRoom(int roomSize) {
		List<Room> usingRoom; // 이용 중인 스터디룸 배열 -> List
		
			// 이용 중인 스터디룸 배열 생성 -> ArrayList
			usingRoom = new ArrayList<Room>();
			
			
			// 배열을 돌며 이용 중인 스터디룸을 찾아 usingRoom에 차례대로 스터디룸 번호 대입 -> ArrayList
			for (int i = 0; i < room.size(); i++) {
				if (room.get(i).getRoomSize() == roomSize && room.get(i).isEmpty() == false) {
					usingRoom.add(room.get(i));
				}
			}
				
			
			return usingRoom;
			
		
	}
	
	// 수입(income)을 설정하는 메소드, 수입 업데이트
	void setIncome(int payment) {
		// 날짜 확인
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		// 날짜에 맞는 배열에 수입 업데이트 
		income[month][date - 1] += payment;
	}
	
	// 하루 수입을 반환하는 메소드
	int getDailyIncome(int month, int date) {
		return income[month - 1][date - 1];
	}
	
	// 한 달 수입을 반환하는 메소드
	int getMonthIncome(int month) {
		int monthIncome = 0;
		
		for (int i = 0; i < 31; i++)
			monthIncome += income[month - 1][i];
		
		return monthIncome;
	}
	
	// 총 수입을 반환하는 메소드
	int getTotalIncome() {
		int totalIncome = 0;
		
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 31; j++)
				totalIncome += income[i][j];
		
		return totalIncome;
	}
	
	// 체크인 메소드
	boolean checkIn(int roomNum, String name, String phoneNum) {
		boolean result = false;
		
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum && room.get(i).isEmpty() == true) {
				room.get(i).checkIn(name, phoneNum); // User 클래스 객체 생성
				
				result = true;
			}
		}
		
		// 체크인이 정상적으로 이루어졌는지 반환
		return result;
	}
	
	// 체크아웃 메소드
	int checkOut(int roomNum, String phoneNum) {
		int result = 0; // 0 : 체크아웃 불가능, 1 : 전화번호 불일치, 2 : 체크아웃 성공
		int payment;
		for (int i = 0; i < room.size(); i++) {
			if (room.get(i).getRoomNum() == roomNum && room.get(i).isEmpty() == false) {
				if (phoneNum.equals(room.get(i).getUser().getPhoneNum())) {
					room.get(i).setCheckOutTime(); // 체크아웃 시간 확인
					payment = room.get(i).payment(); // 지불 금액 계산
					setIncome(payment); // 수입 업데이트
					room.get(i).checkOut(); // 체크아웃
					result = 2;
				}
				else result = 1;
			}
		}
		
		// 체크아웃이 정상적으로 이루어졌는지 반환
		return result;
	}
	
	// 지불 금액을 반환하는 메소드
	int getPayment(int roomNum) {
		int payment = 0;
		
		
		for (int i = 0; i < room.size(); i++)
			if (room.get(i).getRoomNum() == roomNum)
				payment = room.get(i).getPayment();
		
		return payment;
	}
}