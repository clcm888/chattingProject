package aMainServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import chattingSystem.LogObject;
import mainFrameDB.MainDBDAO;
import mainFrameDB.MainDBVO;
import memberDB.MemberDBDAO;
import memberDB.MemberDBVO;
import noticeboardDB.NoticeboardDBDAO;
import noticeboardDB.NoticeboardDBVO;

public class ProgramServer {
	
	private static final int port = 8000;//��Ʈ�Է�
	
	ArrayList<ThreadServerClass> threadList = new ArrayList<ThreadServerClass>();
	Socket socket;
	DataOutputStream outputStream;
	
	//����
	public static void main(String[] args) throws IOException {
		new ProgramServer(port);
	}
	
	//������
	public ProgramServer(int portno) throws IOException {
		Socket s1 = null;
		ServerSocket ss1 = new ServerSocket(portno); 
		System.out.println("@@���� on@@");
		while (true) {//while in
			s1 = ss1.accept();
			ThreadServerClass tServer1 = null;
			try {//try in
				tServer1 = new ThreadServerClass(s1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}//try out
			tServer1.start();
			threadList.add(tServer1);
			System.out.println("������ �� : " + threadList.size() + " ��");
		}//while out
	}
	
	//������ ����Ŭ����
	class ThreadServerClass extends Thread {
		Socket socket1;
		DataInputStream dis;
		DataOutputStream dos;
		ObjectOutputStream oos;
		MemberDBDAO memDAO;
		MainDBDAO mainDAO;
		NoticeboardDBDAO noticeDAO;
		//������ ������
		public ThreadServerClass(Socket s1) throws IOException, ClassNotFoundException, SQLException { // ������ //ȫ�浿�� �Ѿ��2
			socket1 = s1;
			dis = new DataInputStream(s1.getInputStream());
			dos = new DataOutputStream(s1.getOutputStream());
			oos = new ObjectOutputStream(s1.getOutputStream());
			memDAO = new MemberDBDAO();
			mainDAO = new MainDBDAO();
			noticeDAO = new NoticeboardDBDAO();
		}
		//������ �κ�
		public void run() {//run in
			try {//try in
				while (dis != null) {
					//�޼��� �޴� �κ�
					int pageNum =dis.readInt();
					String id = dis.readUTF();
					String firstMessage = dis.readUTF();
					String userCount = Integer.toString(threadList.size());
					//�޼��� ���� ���� & �м�
					int codeNum=Integer.parseInt(firstMessage.substring(0,1));
					String message = firstMessage.substring(1);
					//�м� �������� ���ϴ� ���·� �ѷ��ֱ�	
					switch (pageNum) {//switch in
					case 1:
						if(codeNum==1) {//if case1 in
							MemberDBVO mem=memDAO.loginCheck(id);
							oos.writeObject(mem);
						}else if(codeNum==2) {
							int check = memDAO.checkOverlapID(id);
							dos.writeInt(check);
						}else if(codeNum==3) {
							String result = memDAO.SeacrhPW(id, message);
							dos.writeUTF(result);
						}else if(codeNum==4) {
							String[] item = message.split(",");
							int result =memDAO.SignInMember(id,item[0],item[1],Integer.parseInt(item[2]),Integer.parseInt(item[3]),Integer.parseInt(item[4]),Integer.parseInt(item[5]));
							dos.writeInt(result);
						}else {
							System.out.println("�������۵�");
						}//if 1 out
						break;
					case 2:
						if(codeNum==1) {//if case2 in
							MainDBVO mainset=mainDAO.mainSelect();
							dos.writeUTF(mainset.getNotice()); 
							dos.writeUTF(mainset.getMeetingSchedule());
						}else if(codeNum==2) {
							boolean result = mainDAO.noticeUpdate(id);
							if(result==true) {
								dos.writeUTF(id);
							}else {
								dos.writeUTF("N");
							}
						}else if(codeNum==3) {
							boolean result = mainDAO.meetingScheduleUpdate(id);
							if(result==true) {
								dos.writeUTF(id);
							}else {
								dos.writeUTF("N");
							}
						}else {
							System.out.println("�������۵�");
						}//if case2 out
						break;
					case 3:
						ArrayList<NoticeboardDBVO> noticeArray= new ArrayList<NoticeboardDBVO>();
						noticeArray = noticeDAO.noticeboardSelect();
						if(codeNum==1) {//if case3 in
							noticeArray = noticeDAO.board1Select(noticeArray);
							oos.writeObject(noticeArray);
						}else if(codeNum==2){
							noticeArray = noticeDAO.board2Select(noticeArray);
							oos.writeObject(noticeArray);
						}else if(codeNum==3){
							noticeArray = noticeDAO.board3Select(noticeArray);
							oos.writeObject(noticeArray);
						}else {
							System.out.println("�������۵�");
						}//if case3 out
						break;
					case 4:
						SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
						Calendar logset = Calendar.getInstance();
						String log = format.format(logset.getTime());
						
						if(codeNum==1) {//if case4 in
							sendChat(id);
							sendChat(firstMessage);
							sendChat(userCount);
						}else if(codeNum==2){
							sendChat(id);
							sendChat(firstMessage);
							sendChat(userCount);
						}else if(codeNum==3){
							sendChat(id);
							sendChat(firstMessage);
							sendChat(userCount);
						}else {
							System.out.println("�������۵�");
						}//if case4 out
						break;
					case 5:
						if(codeNum==1) {
							String time = message.substring(0,14);
							String contents = message.substring(14);
							String[] WaT = id.split(",");
							if(noticeDAO.postContentsUpdate(WaT[0], WaT[1], time, contents)) {
								dos.writeUTF("����");
							}else {
								dos.writeUTF("����");
							}	
						}
						if(codeNum==2 || codeNum==3 || codeNum==4) {//if case4 in
							String[] i=id.split(",");
							if(noticeDAO.postWrite(codeNum-1,i[0],message,i[1])) {
								dos.writeUTF("����");
							}else {
								dos.writeUTF("����");
							}	
						}
						if(codeNum==5) {
							String time = message.substring(0,14);
							String title = message.substring(14);
							if(noticeDAO.postDelete(id,title,time)) {
								dos.writeUTF("����");
							}else {
								dos.writeUTF("����");
							}
						}
						break;
					default:
						break;
					}//switch out
				}//while out
			} catch (Exception e) {
				// e.printstackTrace();
			} finally { 
				for (int i = 0; i < threadList.size(); i++) {
					if (socket1.equals(threadList.get(i).socket1)) {
						threadList.remove(i); /// ����� remove
						
					} // if
				} // for
				System.out.println("������ �� : " + threadList.size() + " ��");
			} //try out
		}// run out
		//��ü ������ �޼ҵ�
		public void sendChat(String chat)throws IOException {
			for (int i =0; i <threadList.size(); i++) {
				threadList.get(i).dos.writeUTF(chat);
			}
		}
	}//������ ����Ŭ���� out
}//���� Ŭ���� out


