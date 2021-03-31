package noticeMain;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import chattingSystem.ChatGUI;
import loginMain.LoginMain;

import meetMain.PeriodicMain;

import memberDB.MemberDBVO;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;


public class HeaderOrigin extends JFrame implements MouseListener{
	
	MemberDBVO login;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	int page;
	
	JPanel panel, headerPane, contentPane;
	JLabel currentTime, accLabel;
	JButton logoutButton, noticeButton, periodicMeetButton, rapidlyMeetButton, afterMeetButton, chattingButton;

	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	public HeaderOrigin(MemberDBVO login,DataInputStream dis,DataOutputStream dos,ObjectInputStream ois,int page)  {
		this.login=login;
		this.dis=dis;
		this.dos=dos;
		this.ois=ois;
		this.page=page;
		
		setheaderPane();
		setCurrentTime();
		setLogoutButton();
		setNoticeButton();
		setpPeriodicMeetButton();
		setRapidlyMeetButton();
		setAfterMeetButton();
		setChattingButton();
		setAccount();
		setLocationRelativeTo(null); //화면 중앙 팝업 명령어
		
		switch (page) {
		case 1:
			
			panel = new Notice(login,dis,dos,ois).getNotice();
			contentPane.add(panel);
			break;
		case 2:
			try {
				panel = new PeriodicMain(login,dis,dos,ois,page).getPeriodicMain();
				contentPane.add(panel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				panel = new PeriodicMain(login,dis,dos,ois,page).getPeriodicMain();
				contentPane.add(panel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				panel = new PeriodicMain(login,dis,dos,ois,page).getPeriodicMain();
				contentPane.add(panel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}

	public void setheaderPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 800);
		setVisible(true);
		setTitle("근손실을 위하여");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(bgcolor);
		
		// 상단 JPanel
		//		new HeaderOrigin(login).setVisible(true);
		//				upperPanel = new JPanel();
		
		headerPane = new JPanel();
		headerPane.setBounds(0, 0, 534, 100);
		headerPane.setBackground(bgcolor);
		setVisible(true);
		contentPane.add(headerPane);
	}
	

	
	public void setCurrentTime() {
		int si = new Date().getHours();
		int boon = new Date().getMinutes();
		headerPane.setLayout(null);

		currentTime = new JLabel( String.valueOf(si).format("%02d", si) + " : " + String.valueOf(boon).format("%02d", boon));
		currentTime.setHorizontalAlignment(SwingConstants.CENTER);
		currentTime.setBounds(10, 9, 66, 30);
		currentTime.setFont(new Font("새굴림", Font.BOLD, 14));
		headerPane.add(currentTime);
		currentTime.setForeground(Color.white);
	}
	
	public void setAccount() {
		accLabel = new JLabel();
		accLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		accLabel.setFont(new Font("새굴림", Font.BOLD, 14));
		accLabel.setForeground(Color.white);
		accLabel.setBounds(280, 9, 150, 30);
			if(login.getAdmin() == 1) {
				accLabel.setText(login.getName()+" (관리자) 님" );
			}else {
				accLabel.setText(login.getName()+" (회원) 님" );}
			
		headerPane.add(accLabel);
	}
	
	public void setLogoutButton() {
		logoutButton = new JButton("로그아웃");
		logoutButton.setFont(new Font("새굴림", Font.PLAIN, 13));
		logoutButton.setForeground(Color.white);
		logoutButton.setBackground(bgcolor);
		logoutButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		logoutButton.setBounds(434, 9, 93, 30);
		logoutButton.setToolTipText("로그아웃");
		logoutButton.addMouseListener(this);
		headerPane.add(logoutButton);
		
	}
	
	
	public void setNoticeButton() {
		noticeButton = new JButton("\uACF5\uC9C0\uC0AC\uD56D");
		noticeButton.setLocation(5, 71);
		noticeButton.setFont(new Font("새굴림", Font.PLAIN, 14));
		noticeButton.setForeground(Color.white);
		noticeButton.setBackground(bgcolor);
		noticeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		noticeButton.setSize(106, 29);
		noticeButton.setToolTipText("공지사항");
		noticeButton.addMouseListener(this);
		headerPane.add(noticeButton);
	}
	
	public void setpPeriodicMeetButton() {
		periodicMeetButton = new JButton("정모");
		periodicMeetButton.setLocation(110, 71);
		periodicMeetButton.setFont(new Font("새굴림", Font.PLAIN, 14));
		periodicMeetButton.setForeground(Color.white);
		periodicMeetButton.setBackground(bgcolor);
		periodicMeetButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		periodicMeetButton.setSize(106, 29);
		periodicMeetButton.setToolTipText("정모 게시판");
		periodicMeetButton.addMouseListener(this);
		headerPane.add(periodicMeetButton);
	}
	
	
	public void setRapidlyMeetButton() {
		rapidlyMeetButton = new JButton("번개");
		rapidlyMeetButton.setLocation(215, 71);
		rapidlyMeetButton.setFont(new Font("새굴림", Font.PLAIN, 14));
		rapidlyMeetButton.setForeground(Color.white);
		rapidlyMeetButton.setBackground(bgcolor);
		rapidlyMeetButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		rapidlyMeetButton.setToolTipText("번개 게시판");
		rapidlyMeetButton.setSize(106, 29);
		rapidlyMeetButton.addMouseListener(this);
		headerPane.add(rapidlyMeetButton);
	}

	public void setAfterMeetButton() {
		afterMeetButton = new JButton("후기");
		afterMeetButton.setLocation(320, 71);
		afterMeetButton.setFont(new Font("새굴림", Font.PLAIN, 14));
		afterMeetButton.setForeground(Color.white);
		afterMeetButton.setBackground(bgcolor);
		afterMeetButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		afterMeetButton.setToolTipText("후기 게시판");
		afterMeetButton.setSize(106, 29);
		afterMeetButton.addMouseListener(this);
		headerPane.add(afterMeetButton);
	}

	public void setChattingButton() {
		chattingButton = new JButton("채팅");
		chattingButton.setLocation(425, 71);
		chattingButton.setFont(new Font("새굴림", Font.PLAIN, 14));
		chattingButton.setForeground(Color.white);
		chattingButton.setBackground(bgcolor);
		chattingButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		chattingButton.setToolTipText("채팅 창");
		chattingButton.setSize(106, 29);
		chattingButton.addMouseListener(this);
		headerPane.add(chattingButton);
	}
	
	
	

	//이벤트 메소드
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == logoutButton) {
			this.setVisible(false);
			panel.setVisible(false);
			this.dispose();
			new LoginMain(dis,dos,ois).setVisible(true); //로그인페이지
		}
		
		if(e.getSource() == noticeButton) {
			
			if(page !=1) {
				HeaderOrigin page1 =new HeaderOrigin(login,dis,dos,ois,1);
				page1.setVisible(true);
				this.dispose();
				this.setVisible(false);
				panel.setVisible(false);
			}

		}

		if(e.getSource() == periodicMeetButton) {

			if(page !=2) {
				this.dispose();
				this.setVisible(false);
				HeaderOrigin page2 =new HeaderOrigin(login,dis,dos,ois,2);
				page2.setVisible(true);
				panel.setVisible(false);
				
			}

		}

		if(e.getSource() == rapidlyMeetButton) {
			
			if(page !=3) {
				HeaderOrigin page3 =new HeaderOrigin(login,dis,dos,ois,3);
				page3.setVisible(true);
				this.dispose();
				this.setVisible(false);
				panel.setVisible(false);
			}

		}

		if(e.getSource() == afterMeetButton) {
			
			if(page !=4) {
				HeaderOrigin page4 =new HeaderOrigin(login,dis,dos,ois,4);
				page4.setVisible(true);
				this.dispose();
				this.setVisible(false);
				panel.setVisible(false);
			}
		}
		
		if(e.getSource() == chattingButton) {
			//채팅창 팝업(?)
			try {
				new ChatGUI(login,dis,dos,ois).setVisible(true);;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
			this.setVisible(false);
			panel.setVisible(false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
