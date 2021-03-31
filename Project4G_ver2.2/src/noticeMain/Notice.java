package noticeMain;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;
import mainFrameDB.MainDBVO;
import memberDB.MemberDBVO;

import javax.swing.JTextArea;



public class Notice extends JPanel implements MouseListener {
	
	MemberDBVO login;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	//	JPanel upperPanel; //회원 규칙 판넬
	JPanel lowerPanel; //정모 소식 판넬
	JTextArea noticeTxtArea1, noticeTxtArea2;
	JButton noticeBtn1, noticeBtn2;
	private String noticeTxt, meetingTxt, notice, meeting;

	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	public Notice() {
		
	}
	
	public Notice(MemberDBVO login,DataInputStream dis,DataOutputStream dos,ObjectInputStream ois){
		this.login=login;
		this.dis=dis;
		this.dos=dos;
		this.ois=ois;
		try {
			dos.writeInt(2);
			dos.writeUTF("id");
			dos.writeUTF(1+"MainDBVO mainSelect()");
			if(dis != null) {
				notice = dis.readUTF();
				meeting = dis.readUTF();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setContentPane();
		announceContent();
		periodicMeetContent();
	}

	public JPanel getNotice() {
		return lowerPanel;
	}

	public void setContentPane() {
		//하단 JPanel
		lowerPanel = new JPanel();
		lowerPanel.setBounds(0, 121, 534, 640);
		lowerPanel.setLayout(null);
		lowerPanel.setBackground(bgcolor);
	}

	//공지사항 내용
	public void announceContent() {
		
		noticeBtn1 = new JButton("+");
		noticeBtn1.setBounds(470, 10, 60, 39);
		noticeBtn1.setFont(new Font("새굴림", Font.PLAIN, 20));
		noticeBtn1.setForeground(Color.white);
		noticeBtn1.setBackground(bgcolor);
		noticeBtn1.setBorder(new LineBorder(Color.white));
		noticeBtn1.addMouseListener(this);
		lowerPanel.add(noticeBtn1);
		if(login.getAdmin() != 1) {
			noticeBtn1.setVisible(false);
		}
		JLabel noticeLabel1 = new JLabel("  \uD68C\uC6D0 \uADDC\uCE59");
		noticeLabel1.setFont(new Font("새굴림", Font.BOLD, 20));
		noticeLabel1.setForeground(Color.white);
		noticeLabel1.setBounds(5, 10, 459, 39);
		noticeLabel1.setBorder(new LineBorder(Color.white));
		noticeLabel1.setBackground(bgcolor);
		lowerPanel.add(noticeLabel1);

		noticeTxtArea1 = new JTextArea();
		noticeTxtArea1.setLocation(10, 55);
		noticeTxt = notice;
		noticeTxtArea1.setText(noticeTxt);
		noticeTxtArea1.setLineWrap(true);
		noticeTxtArea1.setBounds(5, 56, 525, 312);
		noticeTxtArea1.setForeground(Color.white);;
		noticeTxtArea1.setFont(new Font("새굴림", Font.PLAIN, 15));
		noticeTxtArea1.setBorder(new LineBorder(Color.white));
		noticeTxtArea1.setBackground(bgcolor);
		noticeTxtArea1.setEnabled(false);
		lowerPanel.add(noticeTxtArea1);
	}
	//정모일정내용 	 	 	
	public void periodicMeetContent() {
		
		noticeBtn2 = new JButton("+");
		noticeBtn2.setBounds(470, 385, 60, 39);
		noticeBtn2.setFont(new Font("새굴림", Font.PLAIN, 20));
		noticeBtn2.setForeground(Color.white);
		noticeBtn2.setBackground(bgcolor);
		noticeBtn2.setBorder(new LineBorder(Color.white));
		noticeBtn2.addMouseListener(this);
		lowerPanel.add(noticeBtn2);
		if(login.getAdmin() != 1) {
			noticeBtn2.setVisible(false);
		}
		JLabel noticeLabel2 = new JLabel("  \uC815\uBAA8 \uC77C\uC815");
		noticeLabel2.setFont(new Font("새굴림", Font.BOLD, 20));
		noticeLabel2.setForeground(Color.white);
		noticeLabel2.setBounds(5, 385, 459, 39);
		noticeLabel2.setBorder(new LineBorder(Color.white));
		noticeLabel2.setBackground(bgcolor);
		lowerPanel.add(noticeLabel2);

		noticeTxtArea2 = new JTextArea();
		meetingTxt = meeting;
		noticeTxtArea2.setText(meetingTxt);
		noticeTxtArea2.setBounds(5, 431, 525, 188);
		noticeTxtArea2.setFont(new Font("새굴림", Font.PLAIN, 15));
		noticeTxtArea2.setLineWrap(true);
		noticeTxtArea2.setBorder(new LineBorder(Color.white));
		noticeTxtArea2.setForeground(Color.white);
		noticeTxtArea2.setBackground(bgcolor);
		noticeTxtArea2.setEnabled(false);
		lowerPanel.add(noticeTxtArea2);	
	}

	//Action 메소드
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == noticeBtn1 ) {
			//공지사항수정 팝업창
			new AnnounceContentEditingPage(notice,dis,dos);
		}

		if(arg0.getSource() == noticeBtn2 ) {
			// 정모일정내용 수정 팝업창
			new PeriodicMeetEditingPage(meeting,dis,dos);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
