package chattingSystem;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import memberDB.MemberDBVO;
import noticeMain.HeaderOrigin;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ChatGUI extends JFrame implements Runnable ,ActionListener{
	//GUI 필드
	private JPanel contentPane;
	private JTextField transTextField;
	private JTextPane chatTextPane;
	private JLabel userName; // 접속 유저이름
	private JLabel alluser; // 접속 자 수
	private JButton chattingRoomExit;
	//Client에서넘어온 필드
	private DataOutputStream dos;
	private DataInputStream dis;
	private String nickname;
	private String id;
	private MemberDBVO login;
	private ObjectInputStream ois;
	JFileChooser fileChooser;
	FileSendClient fsc;
	private boolean threadSW;
	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	
	public ChatGUI(MemberDBVO login,DataInputStream dis,DataOutputStream dos,ObjectInputStream ois) throws IOException {
		this.login =login;
		this.dos = dos;
		this.dis = dis;
		this.nickname = login.getName();
		this.id = login.getId();
		this.ois = ois;
		contentpane();
		transButton();
		transTextField();
		chatTextpane();
		filetransferbt();
		setLocationRelativeTo(null); //화면 중앙 팝업 명령어
		
		userName.setText(nickname+"님 환영합니다");
		
		JButton button = new JButton("그림 전송");
		button.setForeground(Color.white);
		button.setFont(new Font("새굴림", Font.PLAIN, 15)); //*
		button.setBackground(new Color(92, 198, 181));
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(429, 546, 93, 30);
		contentPane.add(button);
		threadSW=true;
		Thread th1 = new Thread(this);
		th1.start();
		dos.writeInt(4);
		dos.writeUTF("입장 출력");
		dos.writeUTF(1+nickname+"님이 입장하셨습니다.");
	}

	public void contentpane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 626);
		setTitle("채팅");
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(bgcolor);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void transButton() {
		JButton transButton = new JButton("\uC804\uC1A1");
		transButton.setForeground(Color.white);
		transButton.setFont(new Font("새굴림", Font.PLAIN, 15)); //*
		transButton.setBackground(new Color(92, 198, 181));
		transButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		transButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == transButton) {
					String messege = transTextField.getText();
					try {
						dos.writeInt(4);
						dos.writeUTF(id);
						dos.writeUTF(2+messege);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					transTextField.setText("");
				}
			}
		});
		transButton.setToolTipText("\uC804\uC1A1");
		transButton.setBounds(328, 508, 97, 68);
		contentPane.add(transButton);
	}

	public void filetransferbt() {
		JButton filetransferbt = new JButton("파일 전송");
		filetransferbt.setForeground(Color.white);
		filetransferbt.setFont(new Font("새굴림", Font.PLAIN, 15)); //*
		filetransferbt.setBackground(new Color(92, 198, 181));
		filetransferbt.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		filetransferbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////////////////////////////////////////////////////////////
				fileChooser = new JFileChooser(); // 파일탐색기 생성
				fileChooser.showOpenDialog(null); // 탐색기 열음
				File file = fileChooser.getSelectedFile(); // 선택한 파일을 file에 넣음
				String filepath = file.getAbsolutePath(); // 선택한 파일의 경로(이걸 sendClient로 보내줄 예정)
				System.out.println(file);
				try {
					fsc.sendFile(filepath, file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		filetransferbt.setBounds(429, 508, 93, 30);
		contentPane.add(filetransferbt);
	}

	public void transTextField() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(13, 507, 311, 69);
		contentPane.add(scrollPane);

		transTextField = new JTextField();
		scrollPane.setViewportView(transTextField);
		transTextField.setColumns(10);
		transTextField.setBackground(bgcolor);
		transTextField.setFont(new Font("새굴림", Font.PLAIN, 13)); //*
		transTextField.setBorder(BorderFactory.createLineBorder(Color.white));
	}
	public void chatTextpane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(13, 73, 509, 426);
		scrollPane.getViewport().setBackground(bgcolor);
		contentPane.add(scrollPane);

		chatTextPane = new JTextPane();
		
		scrollPane.getViewport().setBackground(bgcolor);
		chatTextPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Document doc = chatTextPane.getDocument();
				chatTextPane.setCaretPosition(doc.getLength());
			}
		});
		chatTextPane.setBackground(new Color(114,234,221));
//		chatTextPane.setBackground(bgcolor);
		chatTextPane.setBorder(BorderFactory.createLineBorder(Color.white));
		chatTextPane.setFont(new Font("새굴림", Font.PLAIN, 13)); //*
		chatTextPane.setEditable(false);
		scrollPane.setViewportView(chatTextPane);

		userName = new JLabel();/////////////////////////////////////
		userName.setHorizontalAlignment(SwingConstants.TRAILING);
		userName.setForeground(Color.white);
		userName.setBounds(213, 15, 181, 30);
		contentPane.add(userName);

		alluser = new JLabel("접속자 수 띄우는 곳");
		alluser.setForeground(Color.white);
		alluser.setBounds(11, 15, 158, 30);
		contentPane.add(alluser);

		chattingRoomExit = new JButton("채팅방 나가기");
		chattingRoomExit.addActionListener(this);
		chattingRoomExit.setForeground(Color.white);
		chattingRoomExit.setFont(new Font("새굴림", Font.PLAIN, 15)); //*
		chattingRoomExit.setBackground(new Color(92, 198, 181));
		chattingRoomExit.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		chattingRoomExit.setBounds(399, 15, 122, 30);
		contentPane.add(chattingRoomExit);

	}

	public ImageIcon imageCrossText(String chat) throws IOException {
		ArrayList<String> messageArray = new ArrayList<String>();
		messageArray = messageEnter(chat);
		int size =0;
		if(messageArray.size()==0) {
			size=1;
		}else {
			size= messageArray.size();
		}
		BufferedImage speechBubbleImage = ImageIO.read(new File("src/image/chatText.png"));
		Image resizeImage = speechBubbleImage.getScaledInstance(199, 32*size, Image.SCALE_SMOOTH);
		BufferedImage resizeSpeechBubbleImage = new BufferedImage(199, 32*size, BufferedImage.TYPE_INT_BGR);
		Graphics g = resizeSpeechBubbleImage.getGraphics();
		g.drawImage(resizeImage, 0, 0, null);
		g.setFont(new Font("굴림", Font.BOLD, 13));
		g.setColor(Color.BLACK);
		for (int i = 0; i < messageArray.size(); i++) {
			g.drawString(messageArray.get(i), 10, 22*(i+1));
		}
		g.dispose();
		return new ImageIcon(resizeSpeechBubbleImage);
	}
	//채팅출력창 자동 출바꿈 
	public void lineEnter() {
		try {
			Document doc = chatTextPane.getDocument();
			doc.insertString(doc.getLength(), System.getProperty("line.separator"), null);
			chatTextPane.setCaretPosition(doc.getLength());
		} catch (BadLocationException exc) {
			exc.printStackTrace();
		}
	}
	//채팅출력창 오른쪽정력
	public void textRight() {
		StyledDocument sdoc = chatTextPane.getStyledDocument();
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		try {
			sdoc.insertString(sdoc.getLength(), "", right);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdoc.setParagraphAttributes(sdoc.getLength(), 1, right, false);
	}
	//채팅출력창 왼쪽정렬
	public void textLeft() {
		StyledDocument sdoc = chatTextPane.getStyledDocument();
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		try {
			sdoc.insertString(sdoc.getLength(), "", left);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdoc.setParagraphAttributes(sdoc.getLength(), 1, left, false);
	}
	//내 채팅을 띄우는 메소드
	public void mychat(String message) {
		textRight();
		try {
			chatTextPane.insertIcon(imageCrossText(message));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineEnter();
	}
	//상대 채팅을 띄우는 메소드
	public void youchat(String message) {
		textLeft();
		try {
			chatTextPane.insertIcon(imageCrossText(message));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineEnter();
	}
	//출입과 퇴장을 채팅창에 출력하는 메소드
	public void systemChat(String message) {
		textRight();
		Document doc = chatTextPane.getDocument();
		try {
			doc.insertString(doc.getLength(), message, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineEnter();
	}
	//리시브 Thread
	@Override

	public void run() {
		try {
			while (dis != null) {
				if(!threadSW) {
					break;
				}
			 // 서버가 보낸 ~닉네임~ 채팅을 받아서 출력
				String id = dis.readUTF();
				String message = dis.readUTF();
				String userCount = dis.readUTF();//////////////////////////////////////////////////
				alluser.setText("현재 접속인원 : "+userCount);
				int num=Integer.parseInt(message.substring(0, 1));
				message = message.substring(1);
				if(num==1) {
					systemChat(message);
				}else if(num==2){
					if(id.equals(this.id)) {
						mychat(id+" : "+message);
					}else {
						youchat(id+" : "+message);
					}
				}else {
					if(id.equals(this.id)) {}
					else {
						systemChat(id+" : "+message);
					}
				}
			}// while-end 
		}catch (IOException e) {
				//	e.printStackTrace();	
		} 
	}// run
	//메세지를 한줄씩 끊어주는 메소드
	public ArrayList<String> messageEnter(String message){
		ArrayList<String> messageArray = new ArrayList<String>();
		String MBox="";
		int en = 0;
		int ko = 0;
		int etc = 0;
		char[] string = message.toCharArray();
		for(int i=0; i<string.length; i++) {
			if(string[i]>='A' && string[i]<='z') {
				en++;
			}else if(string[i]>'\uAC00' && string[i]<='\uD7A3') {
				ko++;
				ko++;
			}else {
				etc++;
			}
			MBox=MBox+string[i];
			if(en+ko+etc>=23) {
				messageArray.add(MBox);
				MBox="";
				en=0;
				ko=0;
				etc=0;
			}
		}
		if(!MBox.equals("")) {
			messageArray.add(MBox);
		}
		return messageArray;
	}
	public void actionPerformed(ActionEvent e1) {
		if(e1.getSource()==chattingRoomExit)
			threadSW=false;
			try {
				dos.writeInt(4);
				dos.writeUTF(id);
				dos.writeUTF(3+nickname+"님이 나가셨습니다.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
			setVisible(false);
			new HeaderOrigin(login,dis,dos,ois,1).setVisible(true);
		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
}
