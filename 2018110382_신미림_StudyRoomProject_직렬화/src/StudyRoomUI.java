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
	// roomCount ������ ���Ͽ� �����ϴ� �޼ҵ�
	public void writeRoomCount(int roomCount) {
		OutputStream out = null;
		
		try {
			// ���Ͽ� String ���� �Է��� BufferedOutputStream ����
			out = new BufferedOutputStream(new FileOutputStream("RoomCount.txt"));
			
			// �Է��� ������ writeData�� ����
			String writeData = Integer.toString(roomCount);
			// ������ String ���� Byte �迭�� ����
			byte[] b = writeData.getBytes();
			// ���Ͽ� �ش� ���� �ۼ�
			out.write(b);
		}
		catch (IOException ioe) {
			System.out.println("[ roomCount Data ���忡 ������ �߻��Ͽ����ϴ� ]");
		}
		finally {
			try {
				out.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	// Room ������ ���Ͽ� �����ϴ� �޼ҵ�
	public void writeRoomData(ArrayList<Room> list, int roomCount) {
		
		OutputStream out = null;
		ObjectOutputStream oout = null;
		
		
		
		try {
			// ���Ͽ� String ���� �Է��� BufferedOutputStream ����
			out = new BufferedOutputStream(new FileOutputStream("RoomData.txt"));
			oout = new ObjectOutputStream(out);
			
			oout.writeObject(list);
			
			
			
			
		
		}
		catch (IOException ioe) {
			System.out.println("[ Room Data ���忡 ������ �߻��Ͽ����ϴ� ]");
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
	
	// ���� ������ ���Ͽ� �����ϴ� �޼ҵ�
	public void writeIncomeData(Management studyroom) {
		OutputStream out = null;
		
		try {
			// ���Ͽ� String ���� �Է��� BufferedOutputStream ����
			out = new BufferedOutputStream(new FileOutputStream("IncomeData.txt"));
			
			// �Է��� �������� writeData�� ����
			String writeData = "[ ���� ���� ]\n";
			for (int i = 0; i < 12; i++) {
				writeData += "# " + (i + 1) + "�� : " + studyroom.getMonthIncome(i + 1) + "\n";
			}
			writeData += "[ �� ���� ]\n# " + studyroom.getTotalIncome();
			
			// ������ String ���� Byte �迭�� ����
			byte[] b = writeData.getBytes();
			// ���Ͽ� �ش� ���� �ۼ�
			out.write(b);
		}
		catch (IOException ioe) {
			System.out.println("[ Income Data ���忡 ������ �߻��Ͽ����ϴ� ]");
		}
		finally {
			try {
				out.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	// ���� roomCount �����͸� �ҷ��� �����ϴ� �޼ҵ�
	public void readRoomCount(Management studyroom) {
		FileReader fileReader = null;
		
		try {
			// FileReader �� BufferedReader ��ü ���� �� ���� ����
			fileReader = new FileReader("RoomCount.txt");
			BufferedReader roomCountReader = new BufferedReader(fileReader);
			
			// ���� ������ data�� ���� �� �ٽ� roomCount ������ �����ϱ�
			String data = roomCountReader.readLine();
			int roomCount = Integer.parseInt(data);
			
			// setRoomCount �޼ҵ带 �̿��Ͽ� roomCount ����
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
			// FileReader �� BufferedReader ��ü ���� �� ���� ����
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
		// ���� ���͵�� ����(roomCount) ����
		dataFile.readRoomCount(studyroom);
		// ���� ���͵�� �� ���� ����(roomData) ����
		dataFile.readRoomData(studyroom);
		
		boolean key = true;
		boolean user = false;
		boolean manager = false;
		int mode; // ���� ��带 �����ϱ� ���� ����
		int menu; // �޴��� �����ϱ� ���� ����
		
		System.out.println("JAVA studyroom �Դϴ� :)");
		
		while (key) {
			try {
				System.out.println("\n******* MODE *******");
				System.out.println("1. User\n2. Manager\n3. Exit");
				System.out.println("********************");
				
				mode = scan.nextInt();
				
				switch (mode) {
				// 1��(User) ����
				case 1:
					user = true;
					break;
				// 2��(Manager) ����
				case 2:
					manager = true;
					break;
				// 3��(Exit) ����
				case 3:
					System.out.println("\n�̿��� �ּż� �����մϴ� :)");
					key = false;
					break;
				default:
					System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
				}
			}
			// �߸��� ��� ����
			catch (java.util.InputMismatchException e) {
				System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
				scan.next();
			}
			
			// user mode
			while (user) {
				try {
					System.out.println("\n******* MENU *******");
					System.out.println("1. Check In\n2. Check Out\n3. Search Empty Room\n4. Return To Main\n5. Exit");
					System.out.println("********************");
					
					menu = scan.nextInt();
					
					int roomNum; // ���͵�� ��ȣ(Room Number) �Է� ����
					int roomSize; // ���͵�� ũ��(Room Size) �Է� ����
					String phoneNum;
					int year, month, date, amPm, hour, min;
					
					switch (menu) {
					// 1��(Check In) ���� - �Խ�
					case 1:
						System.out.print("\nRoom Number : ");
						roomNum = scan.nextInt();
						scan.nextLine();
						System.out.print("Name : ");
						String name = scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check In ����
						boolean checkIn = studyroom.checkIn(roomNum, name, phoneNum);
						
						// Check In ����
						if (checkIn == true) {
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "����" : "����";
							
							System.out.println("[ �Խ� ó���� �Ϸ�Ǿ����ϴ� ]");
							System.out.println("# " + roomNum + "�� ���͵��");
							System.out.println("# �Խ� �ð� : " + year + "�� " + month + "�� " + date + "�� " + sAmPm + " " + hour + "�� " + min + "��");
						}
						// Check In ����
						else System.out.println("[ �Խ��� �Ұ����� ���͵���Դϴ� ]");
						
						break;
						
					// 2��(Check Out) ���� - ���
					case 2:
						System.out.print("\nRoom Number : ");
						roomNum = scan.nextInt();
						scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check Out ����
						int checkOut = studyroom.checkOut(roomNum, phoneNum);
						int payment = studyroom.getPayment(roomNum);
						
						// Check Out ����
						if (checkOut == 2) {
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "����" : "����";
							
							System.out.println("[ ��� ó���� �Ϸ�Ǿ����ϴ� ]");
							System.out.println("# " + roomNum + "�� ���͵��");
							System.out.println("# ��� �ð� : " + year + "�� " + month + "�� " + date + "�� " + sAmPm + " " + hour + "�� " + min + "��");
							System.out.println("# ���� �ݾ� : " + payment + "��");
						}
						// Check Out ����
						else if (checkOut == 1)
							System.out.println("[ ��ȭ��ȣ�� ��ġ���� �ʽ��ϴ� ]");
						else
							System.out.println("[ ����� �Ұ����� ���͵���Դϴ� ]");
						
						break;
						
					// 3��(Search Empty Room) ���� - �� ���͵�� ã��
					case 3:
						System.out.print("Room Size : ");
						roomSize = scan.nextInt();
						
						List<Room> emptyRoom;
						
							// emptyRoom(�� ���͵�� �迭) ��ȯ -> List
							emptyRoom = studyroom.searchEmptyRoom(roomSize);
							
							// �߸��� �Է�							
							if(roomSize != 1 && roomSize != 4 && roomSize != 6) {
								System.out.println("[ ���͵���� 1�ν�, 4�ν�, 6�νǸ� �����մϴ� ]");
							}
							// �� ���͵���� ���� ���
							else if(!emptyRoom.isEmpty()) {
								
								System.out.print("[ �̿� ������ "+ roomSize +"�ν� ���͵�� : "); 
								for(Room room:emptyRoom){
									System.out.print(room.getRoomNum() + " ");
									
								}
								System.out.println(" ]");
							}
							// �� ���͵���� ���� ��� (�� List �޾��� ���)
							else {
								System.out.println("[ �̿� ������ "+roomSize+"�ν� ���͵���� �����ϴ� ]");
							}
							
					// 4��(Return To Main) ���� - �������� ���ư���
					case 4:
						user = false;
						break;
						
					// 5��(Exit) ���� - ���α׷� ����
					case 5:
						System.out.println("\n�̿��� �ּż� �����մϴ� :)");
						key = false;
						user = false;
						break;
						
					// �߸��� �޴� ����
					default:
						System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
					}
				}
				
				// �߸��� �޴� ����
				catch (java.util.InputMismatchException e) {
					System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
					scan.next();
				}
			}
			
			// manager mode
			while (manager) {
				try {
					int password; // ��й�ȣ �Է� ����
					
					System.out.print("password : ");
					password = scan.nextInt();
					
					// ��й�ȣ ��ġ
					if (studyroom.getPassword() == password) {
						boolean success = true;
						
						while (success) {
							try {
								System.out.println("\n******* MENU *******");
								System.out.println("1. Create Room\n2. Delete Room\n3. Search Room\n4. Check Income\n5. Return To Main\n6. Exit");
								System.out.println("********************");
								
								menu = scan.nextInt();
								
								int roomSize; // ���͵�� ũ��(roomSize) �Է� ����
								int roomNum; // ���͵�� ��ȣ(roomNum) �Է� ����
								int input;
								boolean result;
								
								switch (menu) {
								// 1��(Create Room) ���� - ���͵�� ����
								case 1:
									try {
										System.out.print("Room Size : ");
										roomSize = scan.nextInt();
										System.out.print("Room Number : ");
										roomNum = scan.nextInt();
										System.out.print("Room Price In Minutes : ");
										int price = scan.nextInt();
										
										
										// �߸��� �Է�
										if(roomSize != 1 && roomSize != 4 && roomSize != 6 ) {
											System.out.println("[ ���͵���� 1�ν�, 4�ν�, 6�νǸ� ������ �� �ֽ��ϴ� ]");
										}
										else {
											//  ���͵�� ����
											result = studyroom.addRoom(roomSize, roomNum, price);
											if (result) {
												System.out.println("[ " + roomNum + "�� ���͵���� �����Ǿ����ϴ� ]");
											}
											else {
												System.out.println("[ ������ ��ȣ�� ���͵���� �ֽ��ϴ� ]");
											}
										}
										
									}
									// ���� ������ ������ �ʰ��Ͽ��� ���
									catch (java.lang.ArrayIndexOutOfBoundsException e) {
										System.out.println("[ �� �̻� ���͵���� ������ �� �����ϴ� ]");
									}
									
									break;
									
								// 2��(Delete Room) ���� - ���͵�� ����
								case 2:
									System.out.print("Room Number : ");
									roomNum = scan.nextInt();
									
									result = studyroom.deleteRoom(roomNum);
									
									// ���͵�� ���� ����
									if (result == true)
										System.out.println("[ " + roomNum + "�� ���͵���� �����Ǿ����ϴ� ]");
									// ���͵�� ���� ����
									else
										System.out.println("[ ���͵���� ������ �� �����ϴ� ]");
									
									break;
									
								// 3��(Search Room) ���� - ���͵�� ã��
								case 3:
									System.out.println("1. Empty Room\n2. Using Room");
									input = scan.nextInt();
									
									switch (input) {
									// 1��(Empty Room) ���� - ��� �ִ� ���͵�� ã��
									case 1:
										// emptyRoom(�� ���͵�� List)�� ��ȯ
										List<Room> emptyRoom1 = studyroom.searchEmptyRoom(1);
										List<Room> emptyRoom4 = studyroom.searchEmptyRoom(4);
										List<Room> emptyRoom6 = studyroom.searchEmptyRoom(6);
										


										
										
										System.out.println("[ �̿� ������ ���͵�� ]");
										
										// ��� �ִ� 1�ν� ���͵���� ���� ���
										if (!emptyRoom1.isEmpty()) {
											System.out.print("# 1�ν� : ");
											for(Room room:emptyRoom1) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// ��� �ִ� 1�ν� ���͵���� ���� ��� (�� List�� �޾��� ���)
										else System.out.println("# 1�ν� : null");
										
										// ��� �ִ� 4�ν� ���͵���� ���� ���
										if (!emptyRoom4.isEmpty()) {
											System.out.print("# 4�ν� : ");
											for(Room room:emptyRoom4) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// ��� �ִ� 4�ν� ���͵���� ���� ��� (�� List�� �޾��� ���)
										else System.out.println("# 4�ν� : null");
										
										// ��� �ִ� 6�ν� ���͵���� ���� ���
										if (!emptyRoom6.isEmpty()) {
											System.out.print("# 6�ν� : ");
											for(Room room:emptyRoom6) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// ��� �ִ� 6�ν� ���͵���� ���� ��� (�� List�� �޾��� ���)
										else System.out.println("# 6�ν� : null");
										
										break;
									
									// 2��(Using Room) ���� - �̿� ���� ���͵�� ã��
									case 2:
										// usingRoom(�̿� ���� ���͵�� List)�� ��ȯ
										List<Room> usingRoom1 = studyroom.searchUsingRoom(1);
										List<Room> usingRoom4 = studyroom.searchUsingRoom(4);
										List<Room> usingRoom6 = studyroom.searchUsingRoom(6);
										
										System.out.println("[ �̿� ���� ���͵�� ]");
										
										// �̿� ���� 1�ν� ���͵���� ���� ���
										if (!usingRoom1.isEmpty()) {
											System.out.print("# 1�ν� : ");
											for(Room room:usingRoom1) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// �̿� ���� 1�ν� ���͵���� ���� ��� (�� List�� �޾��� ���)
										else System.out.println("# 1�ν� : null");
										
										// �̿� ���� 4�ν� ���͵���� ���� ���
										if (!usingRoom4.isEmpty()) {
											System.out.println("# 4�ν� : ");
											for(Room room:usingRoom4) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// �̿� ���� 4�ν� ���͵���� ���� ��� (�� List�� �޾��� ���)
										else System.out.println("# 4�ν� : null");
										
										// �̿� ���� 6�ν� ���͵���� ���� ���
										if (!usingRoom6.isEmpty()) {
											System.out.println("# 6�ν� : ");
											for(Room room:usingRoom6) {
												System.out.print(room.getRoomNum()+" ");
											}
											System.out.println();
										}
										// �̿� ���� 6�ν� ���͵���� ���� ��� (�� List�� �޾��� ���)
										else System.out.println("# 6�ν� : null");
										
										break;
										
									// �߸��� ����
									default:
										System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
									}
									
									break;
									
								// 4��(Check Income) ���� - ���� Ȯ��
								case 4:
									System.out.println("1. Daily Income\n2. Month Income\n3. Total Income");
									input = scan.nextInt();
									
									int month, date;
									
									switch (input) {
									// 1��(Daily Income) ���� - �Ϻ� ����
									case 1:
										System.out.print("Month : ");
										month = scan.nextInt();
										System.out.print("Date : ");
										date = scan.nextInt();
										
										int dailyIncome = studyroom.getDailyIncome(month, date);
										System.out.println("[ " + month + "�� " + date + "�� ���� : " + dailyIncome + "�� ]");
										
										break;
										
									// 2��(Month Income) ���� - ���� ����
									case 2:
										System.out.print("Month : ");
										month = scan.nextInt();
										
										int monthIncome = studyroom.getMonthIncome(month);
										System.out.println("[ " + month + "�� ���� : " + monthIncome + "�� ]");
										
										break;
										
									// 3��(Total Income) ���� - �� ����
									case 3:
										int totalIncome = studyroom.getTotalIncome();
										System.out.println("[ �� ���� : " + totalIncome + "�� ]");
										
										break;
										
									// �߸��� ����
									default:
										System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
									}
									
									break;
									
								// 5��(Return To Main) ���� - �������� ���ư���
								case 5:
									manager = false;
									success = false;
									break;
									
								// 6��(Exit) ���� - ���α׷� ����
								case 6:
									System.out.println("\n�̿��� �ּż� �����մϴ� :)");
									key = false;
									manager = false;
									success = false;
									break;
									
								// �߸��� �޴� ����
								default:
									System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
								}
							}
							
							// �߸��� �޴� ����
							catch (java.util.InputMismatchException e) {
								System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
								scan.next();
							}
						}
					}
					
					// ��й�ȣ ����ġ
					else {
						System.out.println("[ ��й�ȣ�� Ʋ�Ƚ��ϴ� ]");
						manager = false;
					}
				}
				catch (java.util.InputMismatchException e) {
					System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
					scan.next();
					manager = false;
				}
			}
		}
		
		scan.close();
		
		// roomCount ���� ������ ����
		dataFile.writeRoomCount(studyroom.getRoomCount());
		// Room ���� ������ ����
		dataFile.writeRoomData(studyroom.getRoom(), studyroom.getRoomCount());
		// Income ���� ������ ����
		dataFile.writeIncomeData(studyroom);
	}
}