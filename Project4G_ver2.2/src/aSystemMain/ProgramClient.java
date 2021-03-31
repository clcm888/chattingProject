package aSystemMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import loginMain.LoginMain;
import memberDB.MemberDBVO;


public class ProgramClient {
	
	private static final int port = 8000;//포트입력
	private static final String ip = "127.0.1.1";
	
	//생성자
	public ProgramClient() {
		Socket socket=null;
		DataInputStream inputStream=null;
		DataOutputStream outputStream = null;
		ObjectInputStream ois =null;
		try {
			socket = new Socket(ip,port);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			System.out.println("???");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("접속 소켓 정보를 확인하세요");
		}
		new LoginMain(inputStream,outputStream,ois).setVisible(true);
	}
	//메인
	public static void main(String[] args) {
		new ProgramClient();
	}
	

}