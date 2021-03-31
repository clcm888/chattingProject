package loginMain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class SearchPwMain extends JFrame implements ActionListener {
	
	DataInputStream dis;
	DataOutputStream dos;
	//패널(메인, 이미지)
	private JPanel contentPane, logoImage;
	//텍스트필드(아이디, 이름)
	private JTextField txtId, txtName;
	//버튼(검사, 찾기)
	private JButton btnCheck, btnSearch;
	//라벨(페이지라벨, 아이디, 이름)
	private JLabel labelSearchPw, labelId, labelName;
	
	boolean isClickedButton = false;
	String checkID = "", confirmID = "";
	
	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	//생성자
	public SearchPwMain(DataInputStream dis,DataOutputStream dos) {
		//Stream
		this.dis=dis;
		this.dos=dos;
		//GUI
		setContentPane();//메인패널
//		setImageJPanel();//상단 이미지 패널
		setJLabel();//라벨
		setJTextField();//텍스트필드	
		setJButton();//버튼
		setLocationRelativeTo(null);
	}
	//메인판넬
	public void setContentPane() {
		setTitle("비밀번호 찾기");
		setBounds(150, 150, 530, 300);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(bgcolor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	//이미지 판넬
	public void setImageJPanel() {
		logoImage = new JPanel() {
			Image logoImg = new ImageIcon(SearchPwMain.class.getResource("../image/logoImg.png")).getImage();
			@Override
			public void paint(Graphics g) {
				g.drawImage(logoImg, 0, 0, null);
			}
		};
		logoImage.setBounds(70, 40, 504, 160);
		contentPane.add(logoImage);
	}
	//라벨
	public void setJLabel() {
		//비밀번호 찾기 라벨
		labelSearchPw = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		labelSearchPw.setFont(new Font("새굴림", Font.BOLD, 32));
		labelSearchPw.setForeground(Color.white);
		labelSearchPw.setHorizontalAlignment(SwingConstants.CENTER);
		labelSearchPw.setBounds(137, 45, 236, 46);
		contentPane.add(labelSearchPw);
		
		//아이디 라벨
		labelId = new JLabel("\uC544\uC774\uB514 :");
		labelId.setFont(new Font("새굴림", Font.BOLD, 15));
		labelId.setForeground(Color.white);
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		labelId.setBounds(73, 126, 75, 30);
		contentPane.add(labelId);
		
		//이름 라벨
		labelName = new JLabel("\uC774\uB984 :");
		labelName.setFont(new Font("새굴림", Font.BOLD, 15));
		labelName.setForeground(Color.white);
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelName.setBounds(73, 176, 75, 30);
		contentPane.add(labelName);
	}
	//텍스트필드
	public void setJTextField() {
		//아이디 입력창
		txtId = new JTextField(15);
		txtId.setBounds(163, 126, 180, 30);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		//이름 입력창
		txtName = new JTextField(15);
		txtName.setColumns(10);
		txtName.setBounds(163, 176, 180, 30);
		contentPane.add(txtName);
	}
	//버튼
	public void setJButton() {
		//로그인 유효성 검사 버튼
		btnCheck = new JButton("\uAC80\uC0AC");
		btnCheck.setFont(new Font("새굴림", Font.PLAIN, 15));
		btnCheck.setBounds(361, 126, 80, 30);
		btnCheck.addActionListener(this);
		contentPane.add(btnCheck);
		btnCheck.setBackground(bgcolor);
		btnCheck.setForeground(Color.white);
		btnCheck.setBorder(BorderFactory.createLineBorder(Color.white));
		
		//찾기 버튼
		btnSearch = new JButton("\uCC3E\uAE30");
		btnSearch.setFont(new Font("새굴림", Font.PLAIN, 15));
		btnSearch.setBounds(361, 176, 80, 30);
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);
		btnSearch.setBackground(bgcolor);
		btnSearch.setForeground(Color.white);
		btnSearch.setBorder(BorderFactory.createLineBorder(Color.white));
	}
	//ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnCheck) {
			int checkid = 0;
			try {//try in
				//서버에 전송
				dos.writeInt(1);
				dos.writeUTF(txtId.getText());
				dos.writeUTF(2+"int checkOverlapID(String id)");
				//서버에서 받음
				if(dis != null) {
					checkid = dis.readInt();
				}
				//시스템
				if(checkid == 0) {//if in
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
					isClickedButton = false;
				} else if(checkid == -100) {
					JOptionPane.showMessageDialog(this, "아이디에 공백이 입력되었습니다.");
					isClickedButton = false;				
				} else if(checkid == -1) {
					JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다.\n아이디를 확인해주세요.");
					isClickedButton = false;
				} else if(checkid == 1) {
					JOptionPane.showMessageDialog(this, "아이디가 존재합니다.\n계속 진행해주세요.");
					isClickedButton = true;
					checkID = txtId.getText();
				} else {
					JOptionPane.showMessageDialog(this, "알 수 없는 에러가 발생하였습니다.\n관리자에게 문의해주세요.");
				}//if out
			} catch (IOException e1) {
				e1.printStackTrace();
			}//try out
		}//btnCheck END
		
		if(e.getSource() == btnSearch) {
			if(isClickedButton) {//if 1 in
				confirmID = txtId.getText();
				if(confirmID.equals(checkID)) {//if 2 in
					try {//try in
						String message = null;
						//서버에 전송
						dos.writeInt(1);//"String SeacrhPW(String txtId, String txtName)"
						dos.writeUTF(txtId.getText());
						dos.writeUTF(3+txtName.getName());
						//서버에서 받음
						if(dis != null) {
							message = dis.readUTF();
						}
						//시스템
						JOptionPane.showMessageDialog(this, message);
						if(message.contains("비밀번호 찾기 성공!")) {
						this.dispose();
						this.setVisible(false);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//try out
				} else {
					JOptionPane.showMessageDialog(this, "아이디 검사를 실시해주세요.");					
				}//if 2 out
			} else {
				JOptionPane.showMessageDialog(this, "아이디 검사를 실시해주세요.");	
			}//if 1 out
											
		} //btnSearch END
	}//actionPerformed END		
}