import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

class DataFile {
	// roomCount 정보를 파일에 저장하는 메소드
	public void writeRoomCount(int roomCount) {
		OutputStream out = null;
		
		try {
			// 파일에 String 값을 입력할 BufferedOutputStream 생성
			out = new BufferedOutputStream(new FileOutputStream("RoomCount.txt"));
			
			// 입력할 정보를 writeData에 저장
			String writeData = Integer.toString(roomCount);
			// 저장한 String 값을 Byte 배열로 변경
			byte[] b = writeData.getBytes();
			// 파일에 해당 내용 작성
			out.write(b);
		}
		catch (IOException ioe) {
			System.out.println("[ roomCount Data 저장에 오류가 발생하였습니다 ]");
		}
		finally {
			try {
				out.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	// Room 정보를 파일에 저장하는 메소드
	public void writeRoomData(ArrayList<Room> list, int roomCount) {
		
		OutputStream out = null;
		ObjectOutputStream oout = null;
		
		
		
		try {
			// 파일에 String 값을 입력할 BufferedOutputStream 생성
			out = new BufferedOutputStream(new FileOutputStream("RoomData.txt"));
			oout = new ObjectOutputStream(out);
			
			oout.writeObject(list);
			
			
			
			
		
		}
		catch (IOException ioe) {
			System.out.println("[ Room Data 저장에 오류가 발생하였습니다 ]");
		}
		finally {
			try {
				oout.close();
				out.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	// 매출 정보를 파일에 저장하는 메소드
	public void writeIncomeData(Management studyroom) {
		OutputStream out = null;
		
		try {
			// 파일에 String 값을 입력할 BufferedOutputStream 생성
			out = new BufferedOutputStream(new FileOutputStream("IncomeData.txt"));
			
			// 입력할 정보들을 writeData에 저장
			String writeData = "[ 월별 매출 ]\n";
			for (int i = 0; i < 12; i++) {
				writeData += "# " + (i + 1) + "월 : " + studyroom.getMonthIncome(i + 1) + "\n";
			}
			writeData += "[ 총 매출 ]\n# " + studyroom.getTotalIncome();
			
			// 저장한 String 값을 Byte 배열로 변경
			byte[] b = writeData.getBytes();
			// 파일에 해당 내용 작성
			out.write(b);
		}
		catch (IOException ioe) {
			System.out.println("[ Income Data 저장에 오류가 발생하였습니다 ]");
		}
		finally {
			try {
				out.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	// 이전 roomCount 데이터를 불러와 저장하는 메소드
	public void readRoomCount(Management studyroom) {
		FileReader fileReader = null;
		
		try {
			// FileReader 및 BufferedReader 객체 생성 및 파일 열기
			fileReader = new FileReader("RoomCount.txt");
			BufferedReader roomCountReader = new BufferedReader(fileReader);
			
			// 파일 내용을 data에 넣은 후 다시 roomCount 변수에 저장하기
			String data = roomCountReader.readLine();
			int roomCount = Integer.parseInt(data);
			
			// setRoomCount 메소드를 이용하여 roomCount 설정
			studyroom.setRoomCount(roomCount);
			roomCountReader.close();
		}
		catch (FileNotFoundException fnfe) {
			
		}
		catch (EOFException eofe) {
			
		}
		catch (IOException ioe) {
			
		}
		finally {
			try {
				fileReader.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	public void readRoomData(Management studyroom) {
		InputStream in = null;
		ObjectInputStream oin = null;
		
		try {
			// FileReader 및 BufferedReader 객체 생성 및 파일 열기
			in = new BufferedInputStream(new FileInputStream("RoomData.txt"));
			oin = new ObjectInputStream(in);
			ArrayList<Room> list = (ArrayList<Room>)oin.readObject();
			
			
			
			
			
			studyroom.setRoomData(list);
			
			
		}
		catch (FileNotFoundException fnfe) {
			
		}
		catch (EOFException eofe) {
			
		}
		catch (IOException ioe) {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				oin.close();
				in.close();
			}
			catch (Exception e) {
			}
		}
	}
}

public class StudyRoomUI {
	public static void main(String[] args) {
		Management studyroom = new Management();
		
		DataFile dataFile = new DataFile();
		Scanner scan = new Scanner(System.in);
		// 이전 스터디룸 개수(roomCount) 복구
		dataFile.readRoomCount(studyroom);
		// 이전 스터디룸 및 유저 정보(roomData) 복구
		dataFile.readRoomData(studyroom);
		
		boolean key = true;
		boolean user = false;
		boolean manager = false;
		int mode; // 접근 모드를 선택하기 위한 변수
		int menu; // 메뉴를 선택하기 위한 변수
		
		System.out.println("JAVA studyroom 입니다 :)");
		
		while (key) {
			try {
				System.out.println("\n******* MODE *******");
				System.out.println("1. User\n2. Manager\n3. Exit");
				System.out.println("********************");
				
				mode = scan.nextInt();
				
				switch (mode) {
				// 1번(User) 선택
				case 1:
					user = true;
					break;
				// 2번(Manager) 선택
				case 2:
					manager = true;
					break;
				// 3번(Exit) 선택
				case 3:
					System.out.println("\n이용해 주셔서 감사합니다 :)");
					key = false;
					break;
				default:
					System.out.println("[ 잘못 입력하셨습니다 ]");
				}
			}
			// 잘못된 모드 선택
			catch (java.util.InputMismatchException e) {
				System.out.println("[ 잘못 입력하셨습니다 ]");
				scan.next();
			}
			
			// user mode
			while (user) {
				try {
					System.out.println("\n******* MENU *******");
					System.out.println("1. Check In\n2. Check Out\n3. Search Empty Room\n4. Return To Main\n5. Exit");
					System.out.println("********************");
					
					menu = scan.nextInt();
					
					int roomNum; // 스터디룸 번호(Room Number) 입력 변수
					int roomSize; // 스터디룸 크기(Room Size) 입력 변수
					String phoneNum;
					int year, month, date, amPm, hour, min;
					
					switch (menu) {
					// 1번(Check In) 선택 - 입실
					case 1:
						System.out.print("\nRoom Number : ");
						roomNum = scan.nextInt();
						scan.nextLine();
						System.out.print("Name : ");
						String name = scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check In 실행
						boolean checkIn = studyroom.checkIn(roomNum, name, phoneNum);
						
						// Check In 성공
						if (checkIn == true) {
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
							
							System.out.println("[ 입실 처리가 완료되었습니다 ]");
							System.out.println("# " + roomNum + "번 스터디룸");
							System.out.println("# 입실 시간 : " + year + "년 " + month + "월 " + date + "일 " + sAmPm + " " + hour + "시 " + min + "분");
						}
						// Check In 실패
						else System.out.println("[ 입실이 불가능한 스터디룸입니다 ]");
						
						break;
						
					// 2번(Check Out) 선택 - 퇴실
					case 2:
						System.out.print("\nRoom Number : ");
						roomNum = scan.nextInt();
						scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check Out 실행
						int checkOut = studyroom.checkOut(roomNum, phoneNum);
						int payment = studyroom.getPayment(roomNum);
						
						// Check Out 성공
						if (checkOut == 2) {
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
							
							System.out.println("[ 퇴실 처리가 완료되었습니다 ]");
							System.out.println("# " + roomNum + "번 스터디룸");
							System.out.println("# 퇴실 시간 : " + year + "년 " + month + "월 " + date + "일 " + sAmPm + " " + hour + "시 " + min + "분");
							System.out.println("# 지불 금액 : " + payment + "원");
						}
						// Check Out 실패
						else if (checkOut == 1)
							System.out.println("[ 전화번호가 일치하지 않습니다 ]");
						else
							System.out.println("[ 퇴실이 불가능한 스터디룸입니다 ]");
						
						break;
						
					// 3번(Search Empty Room) 선택 - 빈 스터디룸 찾기
					case 3:
						System.out.print("Room Size : ");
						roomSize = scan.nextInt();
						
						List<Room> emptyRoom;
						
							// emptyRoom(빈 스터디룸 배열) 반환 -> List
							emptyRoom = studyroom.searchEmptyRoom(roomSize);
							
							// 잘못된 입력							
							if(roomSize != 1 && roomSize != 4 && roomSize != 6) {
								System.out.println("[ 스터디룸은 1인실, 4인실, 6인실만 존재합니다 ]");
							}
							// 빈 스터디룸이 있을 경우
							else if(!emptyRoom.isEmpty()) {
								
								System.out.print("[ 이용 가능한 "+ roomSize +"인실 스터디룸 : "); 
								for(Room room:emptyRoom){
									System.out.print(room.getRoomNum() + " ");
									
								}
								System.out.println(" ]");
							}
							// 빈 스터디룸이 없을 경우 (빈 List 받았을 경우)
							else {
								System.out.println("[ 이용 가능한 "+roomSize+"인실 스터디룸이 없습니다 ]");
							}
							
					// 4번(Return To Main) 선택 - 메인으로 돌아가기
					case 4:
						user = false;
						break;
						
					// 5번(Exit) 선택 - 프로그램 종료
					case 5:
						System.out.println("\n이용해 주셔서 감사합니다 :)");
						key = false;
						user = false;
						break;
						
					// 잘못된 메뉴 선택
					default:
						System.out.println("[ 잘못 입력하셨습니다 ]");
					}
				}
				
				// 잘못된 메뉴 선택
				catch (java.util.InputMismatchException e) {
					System.out.println("[ 잘못 입력하셨습니다 ]");
					scan.next();
				}
			}
			
			// manager mode
			while (manager) {
				try {
					int password; // 비밀번호 입력 변수
					
					System.out.print("password : ");
					password = scan.nextInt();
					
					// 비밀번호 일치
					if (studyroom.getPassword() == password) {
						boolean success = true;
						
						while (success) {
							try {
								System.out.println("\n******* MENU *******");
								System.out.println("1. Create Room\n2. Delete Room\n3. Search Room\n4. Check Income\n5. Return To Main\n6. Exit");
								System.out.println("********************");
								
								menu = scan.nextInt();
								
								int roomSize; // 스터디룸 크기(roomSize) 입력 변수
								int roomNum; // 스터디룸 번호(roomNum) 입력 변수
								int input;
								boolean result;
								
								switch (menu) {
								// 1번(Create Room) 선택 - 스터디룸 생성
								case 1:
									try {
										System.out.print("Room Size : ");
										roomSize = scan.nextInt();
										System.out.print("Room Number : ");
										roomNum = scan.nextInt();
										System.out.print("Room Price In Minutes : ");
										int price = scan.nextInt();
										
										
										// 잘못된 입력
										if(roomSize != 1 && roomSize != 4 && roomSize != 6 ) {
											System.out.println("[ 스터디룸은 1인실, 4인실, 6인실만 생성할 수 있습니다 ]");
										}
										else {
											//  스터디룸 생성
											result = studyroom.addRoom(roomSize, roomNum, price);
											if (result) {
												System.out.println("[ " + roomNum + "번 스터디룸이 생성되었습니다 ]");
											}
											else {
												System.out.println("[ 동일한 번호의 스터디룸이 있습니다 ]");
											}
										}
										
									}
									// 생성 가능한 범위를 초과하였을 경우
									catch (java.lang.ArrayIndexOutOfBoundsException e) {
										System.out.println("[ 더 이상 스터디룸을 생성할 수 없습니다 ]");
									}
									
									break;
									
								// 2번(Delete Room) 선택 - 스터디룸 삭제
								case 2:
									System.out.print("Room Number : ");
									roomNum = scan.nextInt();
									
									result = studyroom.deleteRoom(roomNum);
									
									// 스터디룸 삭제 성공
									if (result == true)
										System.out.println("[ " + roomNum + "번 스터디룸이 삭제되었습니다 ]");
									// 스터디룸 삭제 실패
									else
										System.out.println("[ 스터디룸을 삭제할 수 없습니다 ]");
									
									break;
									
								// 3번(Search Room) 선택 - 스터디룸 찾기
								case 3:
									System.out.println("1. Empty Room\n2. Using Room");
									input = scan.nextInt();
									
									switch (input) {
									// 1번(Empty Room) 선택 - 비어 있는 스터디룸 찾기
									case 1:
										// emptyRoom(빈 스터디룸 List)을 반환
										List<Room> emptyRoom1 = studyroom.searchEmptyRoom(1);
										List<Room> emptyRoom4 = studyroom.searchEmptyRoom(4);
										List<Room> emptyRoom6 = studyroom.searchEmptyRoom(6);
										


										
										
										System.out.println("[ 이용 가능한 스터디룸 ]");
										
										// 비어 있는 1인실 스터디룸이 있을 경우
										if (!emptyRoom1.isEmpty()) {
											System.out.print("# 1인실 : ");
											for(Room room:emptyRoom1) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// 비어 있는 1인실 스터디룸이 없을 경우 (빈 List을 받았을 경우)
										else System.out.println("# 1인실 : null");
										
										// 비어 있는 4인실 스터디룸이 있을 경우
										if (!emptyRoom4.isEmpty()) {
											System.out.print("# 4인실 : ");
											for(Room room:emptyRoom4) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// 비어 있는 4인실 스터디룸이 없을 경우 (빈 List을 받았을 경우)
										else System.out.println("# 4인실 : null");
										
										// 비어 있는 6인실 스터디룸이 있을 경우
										if (!emptyRoom6.isEmpty()) {
											System.out.print("# 6인실 : ");
											for(Room room:emptyRoom6) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// 비어 있는 6인실 스터디룸이 없을 경우 (빈 List을 받았을 경우)
										else System.out.println("# 6인실 : null");
										
										break;
									
									// 2번(Using Room) 선택 - 이용 중인 스터디룸 찾기
									case 2:
										// usingRoom(이용 중인 스터디룸 List)을 반환
										List<Room> usingRoom1 = studyroom.searchUsingRoom(1);
										List<Room> usingRoom4 = studyroom.searchUsingRoom(4);
										List<Room> usingRoom6 = studyroom.searchUsingRoom(6);
										
										System.out.println("[ 이용 중인 스터디룸 ]");
										
										// 이용 중인 1인실 스터디룸이 있을 경우
										if (!usingRoom1.isEmpty()) {
											System.out.print("# 1인실 : ");
											for(Room room:usingRoom1) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// 이용 중인 1인실 스터디룸이 없을 경우 (빈 List을 받았을 경우)
										else System.out.println("# 1인실 : null");
										
										// 이용 중인 4인실 스터디룸이 있을 경우
										if (!usingRoom4.isEmpty()) {
											System.out.println("# 4인실 : ");
											for(Room room:usingRoom4) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// 이용 중인 4인실 스터디룸이 없을 경우 (빈 List을 받았을 경우)
										else System.out.println("# 4인실 : null");
										
										// 이용 중인 6인실 스터디룸이 있을 경우
										if (!usingRoom6.isEmpty()) {
											System.out.println("# 6인실 : ");
											for(Room room:usingRoom6) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// 이용 중인 6인실 스터디룸이 없을 경우 (빈 List을 받았을 경우)
										else System.out.println("# 6인실 : null");
										
										break;
										
									// 잘못된 선택
									default:
										System.out.println("[ 잘못 입력하셨습니다 ]");
									}
									
									break;
									
								// 4번(Check Income) 선택 - 매출 확인
								case 4:
									System.out.println("1. Daily Income\n2. Month Income\n3. Total Income");
									input = scan.nextInt();
									
									int month, date;
									
									switch (input) {
									// 1번(Daily Income) 선택 - 일별 매출
									case 1:
										System.out.print("Month : ");
										month = scan.nextInt();
										System.out.print("Date : ");
										date = scan.nextInt();
										
										int dailyIncome = studyroom.getDailyIncome(month, date);
										System.out.println("[ " + month + "월 " + date + "일 매출 : " + dailyIncome + "원 ]");
										
										break;
										
									// 2번(Month Income) 선택 - 월별 매출
									case 2:
										System.out.print("Month : ");
										month = scan.nextInt();
										
										int monthIncome = studyroom.getMonthIncome(month);
										System.out.println("[ " + month + "월 매출 : " + monthIncome + "원 ]");
										
										break;
										
									// 3번(Total Income) 선택 - 총 매출
									case 3:
										int totalIncome = studyroom.getTotalIncome();
										System.out.println("[ 총 매출 : " + totalIncome + "원 ]");
										
										break;
										
									// 잘못된 선택
									default:
										System.out.println("[ 잘못 입력하셨습니다 ]");
									}
									
									break;
									
								// 5번(Return To Main) 선택 - 메인으로 돌아가기
								case 5:
									manager = false;
									success = false;
									break;
									
								// 6번(Exit) 선택 - 프로그램 종료
								case 6:
									System.out.println("\n이용해 주셔서 감사합니다 :)");
									key = false;
									manager = false;
									success = false;
									break;
									
								// 잘못된 메뉴 선택
								default:
									System.out.println("[ 잘못 입력하셨습니다 ]");
								}
							}
							
							// 잘못된 메뉴 선택
							catch (java.util.InputMismatchException e) {
								System.out.println("[ 잘못 입력하셨습니다 ]");
								scan.next();
							}
						}
					}
					
					// 비밀번호 불일치
					else {
						System.out.println("[ 비밀번호가 틀렸습니다 ]");
						manager = false;
					}
				}
				catch (java.util.InputMismatchException e) {
					System.out.println("[ 잘못 입력하셨습니다 ]");
					scan.next();
					manager = false;
				}
			}
		}
		
		scan.close();
		
		// roomCount 정보 데이터 저장
		dataFile.writeRoomCount(studyroom.getRoomCount());
		// Room 정보 데이터 저장
		dataFile.writeRoomData(studyroom.getRoom(), studyroom.getRoomCount());
		// Income 정보 데이터 저장
		dataFile.writeIncomeData(studyroom);
	}
}