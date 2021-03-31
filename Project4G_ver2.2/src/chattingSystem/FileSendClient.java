package chattingSystem;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileSendClient {
	
	FileInputStream fis;
	String nickname;
	InputStream input;
	Socket socket;
	byte[] bytes = new byte[65536];	

	public FileSendClient(Socket socket, String nickname) throws IOException {
		this.socket = socket;
		this.nickname = nickname;
	}

	public void sendFile(String filepath, File file) throws IOException {
		
		OutputStream os = socket.getOutputStream();
		File file1 = new File(filepath);
		fis = new FileInputStream(file1);
		DataOutputStream dos = new DataOutputStream(os);
		int count;
		while ((count = fis.read(bytes)) > 0) {
			dos.write(bytes, 0, count);
		System.out.println(socket.getInetAddress() + " " + socket.getPort());
        }
	}
}
