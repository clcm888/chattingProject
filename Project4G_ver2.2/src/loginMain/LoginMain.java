package loginMain;



import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import memberDB.MemberDBVO;
import noticeMain.HeaderOrigin;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class LoginMain extends JFrame implements ActionListener{
	
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	
	private JPanel contentPane, logoImage;//패널(기본패널, 이미지패널)
	private JButton btnLogin, btnSignIn, btnSearchPW;//버튼(로그인버튼, 회원가입버튼, 비밀번호찾기버튼)
	private JLabel labelID, labelPW;//레이블(아이디라벨, 비밀번호라벨)
	private JTextField txtField_ID;//텍스트상자(아이디 텍스트상자, 비밀번호 텍스트상자)
	private JPasswordField txtField_PW;
	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	//생성자
	public LoginMain(DataInputStream dis,DataOutputStream dos,ObjectInputStream ois) {
		//Stream 초기값
		this.dis=dis;
		this.dos=dos;
		this.ois=ois;
		
		//GUI
		setContentPane();//메인패널
		setImageJPanel();//상단 이미지 패널
		setJLabel();//레이블
		setJTextField();//텍스트필드
		setJButton();//버튼
		setLocationRelativeTo(null); //화면 중앙 팝업 명령어
		
		//창 활성화·비활성화
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				returnEnable();
			}
			public void windowLostFocus(WindowEvent e) {
				returnDisable();
			}
		});
	}
	//메인패널
	public void setContentPane() {
		setTitle("근손실을 위하여");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(bgcolor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	//이미지 패널
	public void setImageJPanel() {
		logoImage = new JPanel() {
			Image logoImg = new ImageIcon(LoginMain.class.getResource("../image/logoImg.png")).getImage();
			@Override
			public void paint(Graphics g) {
				g.drawImage(logoImg, 0, 0, null);
			}
		};
		logoImage.setBounds(14, 140, 504, 160);
		contentPane.add(logoImage);
	}
	//라벨
	public void setJLabel() {
		//아이디 레이블ㅇ
		labelID = new JLabel("ID:");
		labelID.setHorizontalAlignment(SwingConstants.RIGHT);
		labelID.setBounds(81, 387, 40, 27);
		contentPane.add(labelID);
		labelID.setFont(new Font("새굴림", Font.BOLD, 15)); //*
		labelID.setForeground(Color.white);
		
		//비밀번호 레이블
		labelPW = new JLabel("PW:");
		labelPW.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPW.setBounds(81, 436, 40, 27);
		contentPane.add(labelPW);
		labelPW.setFont(new Font("새굴림", Font.BOLD, 15)); //*
		labelPW.setForeground(Color.white);
		
	}
	//텍스트필드
	public void setJTextField() {
		//아이디 입력창
		txtField_ID = new JTextField();
		txtField_ID.setFont(new Font("새굴림", Font.PLAIN, 15));
		txtField_ID.setBackground(bgcolor);
		txtField_ID.setForeground(Color.white);
		txtField_ID.setBorder(BorderFactory.createLineBorder(Color.white));
		txtField_ID.setBounds(135, 387, 189, 30);
		txtField_ID.setColumns(10);
		contentPane.add(txtField_ID);
		
		//비밀번호 입력창
		txtField_PW = new JPasswordField();
		txtField_PW.setFont(new Font("새굴림", Font.PLAIN, 15));
		txtField_PW.setBackground(bgcolor);
		txtField_PW.setForeground(Color.white);
		txtField_PW.setBorder(BorderFactory.createLineBorder(Color.white));
		txtField_PW.setColumns(10);
		txtField_PW.setBounds(135, 437, 189, 30);
		contentPane.add(txtField_PW);
	}
	//버튼
	public void setJButton() {
		//로그인 버튼
		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("새굴림", Font.BOLD, 15));
		btnLogin.setBounds(344, 386, 96, 78);
		contentPane.add(btnLogin);
		btnLogin.setBackground(new Color(92, 198, 181)); //*
		btnLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		//회원가입 버튼
		btnSignIn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnSignIn.setForeground(Color.white);
		btnSignIn.setBounds(135, 487, 144, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setFont(new Font("새굴림", Font.BOLD, 15)); //*
		btnSignIn.setBackground(new Color(92, 198, 181));
		btnSignIn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		//비밀번호찾기 버튼
		btnSearchPW = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btnSearchPW.setForeground(Color.white);
		btnSearchPW.setFont(new Font("새굴림", Font.BOLD, 15));
		btnSearchPW.setBounds(296, 487, 144, 44);
		contentPane.add(btnSearchPW);
		btnSearchPW.setBackground(new Color(92, 198, 181));
		btnSearchPW.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		//ActionListener
		btnLogin.addActionListener(this);
		btnSignIn.addActionListener(this);
		btnSearchPW.addActionListener(this);
	}
	//창 비활성화
	public void returnDisable() {
		contentPane.setEnabled(false);
		btnLogin.setEnabled(false);
		btnSearchPW.setEnabled(false);
		btnSignIn.setEnabled(false);
		txtField_ID.setEnabled(false);
		txtField_PW.setEnabled(false);
		txtField_ID.setText("");
		txtField_PW.setText("");
	}
	//창 활성화
	public void returnEnable() {
		contentPane.setEnabled(true);
		btnLogin.setEnabled(true);
		btnSearchPW.setEnabled(true);
		btnSignIn.setEnabled(true);
		txtField_ID.setEnabled(true);
		txtField_PW.setEnabled(true);
		txtField_ID.setText("");
		txtField_PW.setText("");
	}
	//ActionPerformed
	public void actionPerformed(ActionEvent e) {
		//로그인 버튼 클릭시//////////////////////////////////////////////
		if(e.getSource() == btnLogin) {
			try {
				//회원객체
				MemberDBVO login = null;
				//입력받은 값
				String id = txtField_ID.getText();
				String pw = new String(txtField_PW.getPassword());
				//서버에  전송
				dos.writeInt(1);
				dos.writeUTF(id);
				dos.writeUTF(1+"MemberDBVO loginCheck(String id)");
				//서버에서 받음
				if(ois != null) {
					login=(MemberDBVO)ois.readObject();
				}
				//로그인 시스템
				if(id.equals("")) {//if in
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
				}else if(id.contains(" ")) {
					JOptionPane.showMessageDialog(this, "공백이 포함되어있습니다.");
				}else if(login==null){
					JOptionPane.showMessageDialog(this, "존재하지 않는 아이디입니다.\n아이디를 확인해주세요.");
				}else {
					if(!pw.equals("")) {
						if(pw.contains(" ")) {
							JOptionPane.showMessageDialog(this, "공백이 포함되어있습니다.");
						}else if(login.getPw().equals(pw)){
								JOptionPane.showMessageDialog(this, "로그인 성공!\n" + login.getName() + "님의 방문을 환영합니다.");
								//////////////////////////////////////////////////////////
								//성공 후 다음 화면
								new HeaderOrigin(login,dis,dos,ois,1).setVisible(true);			
								//////////////////////////////////////////////////////////
								this.dispose();
								this.setVisible(false);
						} else
							JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
					} else
						JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.");
				}//if out	
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//회원가입버튼 클릭시/////////////////////////////////////////////
		if(e.getSource() == btnSignIn) {
			SignInMain sign = new SignInMain(dis,dos);
			returnDisable();
			sign.setVisible(true);
		}
		//비밀번호 찾기 클릭시/////////////////////////////////////////////
		if(e.getSource() == btnSearchPW) {
			SearchPwMain searchPw = new SearchPwMain(dis,dos);
			returnDisable();
			searchPw.setVisible(true);
		}
	}//ActionListener END
}// class END
