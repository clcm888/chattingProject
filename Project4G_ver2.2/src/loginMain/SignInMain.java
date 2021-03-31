package loginMain;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import memberDB.MemberDBDAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SignInMain extends JFrame implements ActionListener, KeyListener {
	
	DataInputStream dis;
	DataOutputStream dos;
	
	private JPanel contentPane, logoImage;//패널(기본패널, 이미지패널)
	
	private JTextField txtID, txtName, txtPhone1, txtPhone2;//텍스트필드(아이디, 비밀번호, 비밀번호 확인, 이름 , 전화번호1, 2)
	private JPasswordField txtPW, txtPWCheck;
	private JButton btnIDCheck, btnJoin, btnReset;//버튼(아이디체크, 회원가입, 초기화)
	private JLabel labelJoin, labelID, labelPW, labelPWCheck, labelConfirmPW, labelName, labelPhone, labelGender, labelDash1, labelDash2;
	//라벨(회원가입, 아이디, 비밀번호, 비밀번호확인, 비밀번호 컨펌, 이름, 전화번호, 성별, 전화번호 대쉬)
	private JRadioButton radioMan, radioWomen;//라디오(남, 여)
	private final ButtonGroup buttonGroup = new ButtonGroup();//라디오 버튼그룹
	private JComboBox txtPhone0;//전화번호 콤보박스
	private String checkID = "", confirmID = "";
	boolean isClickedOverlapButton = false;
	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	//생성자
	public SignInMain(DataInputStream dis,DataOutputStream dos) {
		//Stream
		this.dis=dis;
		this.dos=dos;
		
		//GUI
		setContentPane();//메인패널
//		setImageJPanel();//상단 이미지 패널
		setJLabel();//라벨 생성
		setJTextField();//텍스트필드
		setJRadioButton();//라디오버튼
		setJButton();//버튼
		setJComboBox();//콤보박스
		setLocationRelativeTo(null);
		 
	}//Constructor END
	//메인 판넬
	public void setContentPane() {
		setTitle("회원가입");
		contentPane = new JPanel();
		setBounds(700, 100, 530, 640);
		setResizable(false);
		contentPane.setBackground(bgcolor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	//메인이미지
	public void setImageJPanel() {
		logoImage = new JPanel() {
			Image logoImg = new ImageIcon(SignInMain.class.getResource("../image/logoImg.png")).getImage();
			@Override
			public void paint(Graphics g) {
				g.drawImage(logoImg, 0, 0, null);
			}
		};
		logoImage.setBounds(14, 50, 504, 160);
		contentPane.add(logoImage);
	}
	//라벨
	public void setJLabel() {
		//회원가입 라벨
		labelJoin = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		labelJoin.setFont(new Font("새굴림", Font.BOLD, 32));
		labelJoin.setForeground(Color.white);
		labelJoin.setHorizontalAlignment(SwingConstants.CENTER);
		labelJoin.setBounds(160, 27, 190, 60);
		contentPane.add(labelJoin);
		
		//아이디 라벨
		labelID = new JLabel("\uC544\uC774\uB514 :");
		labelID.setHorizontalAlignment(SwingConstants.RIGHT);
		labelID.setFont(new Font("새굴림", Font.BOLD, 15));
		labelID.setForeground(Color.white);
		labelID.setBounds(54, 140, 85, 30);
		contentPane.add(labelID);
		
		//비밀번호 라벨
		labelPW = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		labelPW.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPW.setFont(new Font("새굴림", Font.BOLD, 15));
		labelPW.setForeground(Color.white);
		labelPW.setBounds(54, 190, 85, 30);
		contentPane.add(labelPW);
		
		//비밀번호 확인 라벨
		labelPWCheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778 :");
		labelPWCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPWCheck.setFont(new Font("새굴림", Font.BOLD, 15));
		labelPWCheck.setForeground(Color.white);
		labelPWCheck.setBounds(12, 240, 127, 30);
		contentPane.add(labelPWCheck);		
		
		//비밀번호 컨펌 라벨
		labelConfirmPW = new JLabel("비밀번호 미입력");
		labelConfirmPW.setHorizontalAlignment(SwingConstants.CENTER);
		labelConfirmPW.setFont(new Font("새굴림", Font.PLAIN, 12));
		labelConfirmPW.setForeground(Color.white);
		labelConfirmPW.setBounds(388, 190, 105, 30);
		contentPane.add(labelConfirmPW);
		
		//이름 라벨
		labelName = new JLabel("\uC774\uB984 :");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelName.setFont(new Font("새굴림", Font.BOLD, 15));
		labelName.setForeground(Color.white);
		labelName.setBounds(54, 290, 85, 30);
		contentPane.add(labelName);
		
		//전화번호 라벨
		labelPhone = new JLabel("\uC804\uD654\uBC88\uD638 :");
		labelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPhone.setFont(new Font("새굴림", Font.BOLD, 15));
		labelPhone.setForeground(Color.white);
		labelPhone.setBounds(54, 340, 85, 30);
		contentPane.add(labelPhone);
		
		//성별 라벨
		labelGender = new JLabel("\uC131\uBCC4 :");
		labelGender.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGender.setFont(new Font("새굴림", Font.BOLD, 15));
		labelGender.setForeground(Color.white);
		labelGender.setBounds(54, 390, 85, 30);
		contentPane.add(labelGender);
		
		//전화번호 -
		labelDash1 = new JLabel("-");
		labelDash1.setHorizontalAlignment(SwingConstants.CENTER);
		labelDash1.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelDash1.setForeground(Color.white);
		labelDash1.setBounds(218, 340, 15, 35);
		contentPane.add(labelDash1);
		
		//전화번호 -
		labelDash2 = new JLabel("-");
		labelDash2.setHorizontalAlignment(SwingConstants.CENTER);
		labelDash2.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelDash2.setForeground(Color.white);
		labelDash2.setBounds(288, 340, 15, 35);
		contentPane.add(labelDash2);

	}
	//텍스트필드
	public void setJTextField() {
		//아이디 텍스트필드
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtID.setBounds(160, 140, 198, 30);
		contentPane.add(txtID);
		
		//비밀번호 텍스트필드
		txtPW = new JPasswordField();
		txtPW.setColumns(10);
		txtPW.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtPW.setBounds(160, 190, 198, 30);
		txtPW.addKeyListener(this);
		contentPane.add(txtPW);
		
		//비밀번호 확인 텍스트필드
		txtPWCheck = new JPasswordField();
		txtPWCheck.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtPWCheck.setColumns(10);
		txtPWCheck.setBounds(160, 240, 198, 30);
		txtPWCheck.addKeyListener(this);
		contentPane.add(txtPWCheck);
		
		//전화번호 앞자리
		txtPhone1 = new JTextField();
		txtPhone1.setColumns(10);
		txtPhone1.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtPhone1.setBounds(233, 340, 55, 30);
		contentPane.add(txtPhone1);
		
		//전화번호 뒷자리
		txtPhone2 = new JTextField();
		txtPhone2.setColumns(10);
		txtPhone2.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtPhone2.setBounds(303, 340, 55, 30);
		contentPane.add(txtPhone2);
		
		//이름 텍스트필드
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtName.setBounds(160, 290, 198, 30);
		contentPane.add(txtName);
	}
	//라디오버튼
	public void setJRadioButton() {
		//성별_남 라디오버튼
		radioMan = new JRadioButton("\uB0A8\uC790");
		buttonGroup.add(radioMan);
		radioMan.setBackground(bgcolor);
		radioMan.setForeground(Color.white);
		radioMan.setFont(new Font("새굴림", Font.PLAIN, 15));
		radioMan.setBounds(179, 390, 60, 30);
		radioMan.addActionListener(this);
		contentPane.add(radioMan);
		
		//성별_여 라디오버튼
		radioWomen = new JRadioButton("\uC5EC\uC790");
		buttonGroup.add(radioWomen);
		radioWomen.setBackground(bgcolor);
		radioWomen.setForeground(Color.white);
		radioWomen.setFont(new Font("새굴림", Font.PLAIN, 15));
		radioWomen.setBounds(279, 390, 60, 30);
		radioWomen.addActionListener(this);
		contentPane.add(radioWomen);
	}	
	//버튼
	public void setJButton() {
		//아이디 중복검사 버튼
		btnIDCheck = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		btnIDCheck.setFont(new Font("새굴림", Font.PLAIN, 12));
		btnIDCheck.setBounds(388, 140, 105, 30);
		contentPane.add(btnIDCheck);
		btnIDCheck.addActionListener(this);
		btnIDCheck.setBackground(bgcolor);
		btnIDCheck.setForeground(Color.white);
		btnIDCheck.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		//회원가입 버튼
		btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setFont(new Font("새굴림", Font.PLAIN, 15));
		btnJoin.setBounds(273, 460, 85, 30);
		contentPane.add(btnJoin);
		btnJoin.addActionListener(this);
		btnJoin.setBackground(bgcolor);
		btnJoin.setForeground(Color.white);
		btnJoin.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		//리셋 버튼
		btnReset = new JButton("\uCD08\uAE30\uD654");
		btnReset.setFont(new Font("새굴림", Font.PLAIN, 15));
		btnReset.setBounds(163, 460, 85, 30);
		contentPane.add(btnReset);
		btnReset.addActionListener(this);
		btnReset.setBackground(bgcolor);
		btnReset.setForeground(Color.white);
		btnReset.setBorder(BorderFactory.createLineBorder(Color.white));
		
	}
	//콤보박스
	public void setJComboBox() {
		//전화번호 010~
		txtPhone0 = new JComboBox();
		txtPhone0.setFont(new Font("새굴림", Font.PLAIN, 14));
		txtPhone0.setForeground(Color.white);
		txtPhone0.setBackground(bgcolor);
		txtPhone0.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD", "010", "011", "016", "017", "018", "019", "050", "080", "\uC5C6\uC74C"}));
		txtPhone0.setBounds(163, 340, 55, 30);
		contentPane.add(txtPhone0);
		txtPhone0.addActionListener(this);
	}
	
	//메서드 ↓
	//성별(남자 - 1, 여자 - 0)
	public int StrToInt() {
		
		int gender = -1;
		
		Enumeration<AbstractButton> enumers = buttonGroup.getElements();
		String stringGender = "";
		while(enumers.hasMoreElements()) {
			AbstractButton ab = enumers.nextElement();
			JRadioButton jb = (JRadioButton) ab;
			if(jb.isSelected()) {
				stringGender = jb.getText().trim();
			}
		}
		
		if(stringGender.equals("남자")) {
			gender = 1;
		} else if (stringGender.equals("여자")){
			gender = 0;
		}
		
		return gender;
	}
	//비번 검사
	public String checkPW(String pw, String checkPw) {
		
		String label;
		if(!pw.equals("")) {
			if(pw.equals(checkPw)) {
				label = "비밀번호 일치";
			} else {
				label = "비밀번호 불일치";
			}
		} else {
			label = "비밀번호 미입력";
		}
		return label;
	}
	
	//회원가입 
	public void SignIn(String id, char[] pw, String name, String phone0, String phone1, String phone2, int gender, String labelConfirmPW) {
		if(id.equals("")) 
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
		else if(id.contains(" ")) 
			JOptionPane.showMessageDialog(this, "아이디에 공백이 입력되었습니다.");
		else if(new String(pw).equals(""))
			JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.");
		else if(new String(pw).contains(" ")) 
			JOptionPane.showMessageDialog(this, "비밀번호에 공백이 입력되었습니다.");
		else if(name.equals("")) 
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요.");
		else if(name.contains(" ")) 
			JOptionPane.showMessageDialog(this, "이름에 공백이 입력되었습니다.");
		else if(gender != 1 && gender != 0) 
			JOptionPane.showMessageDialog(this, "성별을 선택해주세요.");
		else if(phone0.equals("선택") || phone1.equals("") || phone1.length() != 4 || phone2.equals("") || phone2.length() != 4) 
			JOptionPane.showMessageDialog(this, "올바른 전화번호를 입력해주세요.");
		else if(phone1.contains(" ") || phone2.contains(" ")) 
			JOptionPane.showMessageDialog(this, "전화번호에 공백이 입력되었습니다.");
		else if(labelConfirmPW.equals("비밀번호 불일치")) 
			JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");					
		else {
			
			int result =0;
			try {
				//서버에  전송
				dos.writeInt(1);
				dos.writeUTF(txtID.getText());
				dos.writeUTF(4+new String(txtPW.getPassword())+","+txtName.getText()+","+(String)txtPhone0.getSelectedItem()+","+txtPhone1.getText()+","+txtPhone2.getText()+","+gender);
				//서버에서 받음
				if(dis != null) {
					result = dis.readInt();
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			if(result > 0) {
				this.dispose();
				this.setVisible(false);
				JOptionPane.showMessageDialog(this, "회원가입 성공!\n" + txtName.getText() + "님의 방문을 환영합니다.");
			}
		}
	}
	//화면 초기화
	public void ClearData() {
		txtID.setText("");
		txtPW.setText("");
		txtPWCheck.setText("");
		txtName.setText("");
		txtPhone1.setText("");
		txtPhone2.setText("");
		txtPhone0.setSelectedItem("선택");
		buttonGroup.clearSelection();
	}

	//ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnIDCheck) {
			int check = 0;
			try {
				//서버에 전송
				dos.writeInt(1);
				dos.writeUTF(txtID.getText());
				dos.writeUTF(2+"int checkOverlapID(String id)");
				//서버에서 받음
				if(dis != null) {
					check = dis.readInt();
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			//int check = 0; //0: 아이디 미입력, 1: 아이디 존재, -1: 존재하지 않는 아이디, -100: 공백입력
			if(check == 0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
				isClickedOverlapButton = false;
			} else if(check == -100) {
				JOptionPane.showMessageDialog(this, "아이디에 공백이 입력되었습니다.");
				isClickedOverlapButton = false;
			} else if(check == 1) {
				JOptionPane.showMessageDialog(this, "이미 존재하는 아이디입니다.\n다른 아이디를 입력해주세요.");
				isClickedOverlapButton = false;
			} else if(check == -1) {
				JOptionPane.showMessageDialog(this, "사용할 수 있는 아이디입니다.");
				isClickedOverlapButton = true;
				checkID = txtID.getText();
			}
		}
		if(e.getSource() == btnJoin) {
				
				try {
					if(isClickedOverlapButton) {
						confirmID = txtID.getText();
						if(confirmID.equals(checkID)) {
							int gender = StrToInt();
							SignIn(txtID.getText(), txtPW.getPassword(), txtName.getText(), (String) txtPhone0.getSelectedItem(), txtPhone1.getText(), txtPhone2.getText(), gender, labelConfirmPW.getText());
						} else
							JOptionPane.showMessageDialog(this, "아이디 중복검사를 실시해주세요.");							
					} else
						JOptionPane.showMessageDialog(this, "아이디 중복검사를 실시해주세요.");
				} catch(NumberFormatException phoneNumberError) {
					JOptionPane.showMessageDialog(this, "올바른 전화번호를 입력해주세요.");
				}
			
		}
		
		if(e.getSource() == btnReset) {
			ClearData();
		}
		
	} //actionPerformed END
	//KeyListener(비밀번호 일치여부 확인 라벨 변경)
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == txtPW) {
			String check = checkPW(new String(txtPW.getPassword()), new String(txtPWCheck.getPassword()));
			labelConfirmPW.setText(check);
		}
		if(e.getSource() == txtPWCheck) {
			String check = checkPW(new String(txtPW.getPassword()), new String(txtPWCheck.getPassword()));
			labelConfirmPW.setText(check);
		}
		if(e.getSource() == txtID) {
			isClickedOverlapButton = false;
			System.out.println(isClickedOverlapButton);
		}
		
	} //KeyReleased END
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource() == txtID) {
			isClickedOverlapButton = false;
		}
	} //keyPressed END 
	@Override
	public void keyTyped(KeyEvent e) {} //KeyTyped END	
	
	

} //class END