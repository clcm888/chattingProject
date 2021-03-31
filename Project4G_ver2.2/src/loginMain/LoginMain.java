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
	
	private JPanel contentPane, logoImage;//�г�(�⺻�г�, �̹����г�)
	private JButton btnLogin, btnSignIn, btnSearchPW;//��ư(�α��ι�ư, ȸ�����Թ�ư, ��й�ȣã���ư)
	private JLabel labelID, labelPW;//���̺�(���̵��, ��й�ȣ��)
	private JTextField txtField_ID;//�ؽ�Ʈ����(���̵� �ؽ�Ʈ����, ��й�ȣ �ؽ�Ʈ����)
	private JPasswordField txtField_PW;
	private Color bgcolor = new Color(92, 198, 181) ; //����
	
	//������
	public LoginMain(DataInputStream dis,DataOutputStream dos,ObjectInputStream ois) {
		//Stream �ʱⰪ
		this.dis=dis;
		this.dos=dos;
		this.ois=ois;
		
		//GUI
		setContentPane();//�����г�
		setImageJPanel();//��� �̹��� �г�
		setJLabel();//���̺�
		setJTextField();//�ؽ�Ʈ�ʵ�
		setJButton();//��ư
		setLocationRelativeTo(null); //ȭ�� �߾� �˾� ��ɾ�
		
		//â Ȱ��ȭ����Ȱ��ȭ
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				returnEnable();
			}
			public void windowLostFocus(WindowEvent e) {
				returnDisable();
			}
		});
	}
	//�����г�
	public void setContentPane() {
		setTitle("�ټս��� ���Ͽ�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(bgcolor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	//�̹��� �г�
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
	//��
	public void setJLabel() {
		//���̵� ���̺�
		labelID = new JLabel("ID:");
		labelID.setHorizontalAlignment(SwingConstants.RIGHT);
		labelID.setBounds(81, 387, 40, 27);
		contentPane.add(labelID);
		labelID.setFont(new Font("������", Font.BOLD, 15)); //*
		labelID.setForeground(Color.white);
		
		//��й�ȣ ���̺�
		labelPW = new JLabel("PW:");
		labelPW.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPW.setBounds(81, 436, 40, 27);
		contentPane.add(labelPW);
		labelPW.setFont(new Font("������", Font.BOLD, 15)); //*
		labelPW.setForeground(Color.white);
		
	}
	//�ؽ�Ʈ�ʵ�
	public void setJTextField() {
		//���̵� �Է�â
		txtField_ID = new JTextField();
		txtField_ID.setFont(new Font("������", Font.PLAIN, 15));
		txtField_ID.setBackground(bgcolor);
		txtField_ID.setForeground(Color.white);
		txtField_ID.setBorder(BorderFactory.createLineBorder(Color.white));
		txtField_ID.setBounds(135, 387, 189, 30);
		txtField_ID.setColumns(10);
		contentPane.add(txtField_ID);
		
		//��й�ȣ �Է�â
		txtField_PW = new JPasswordField();
		txtField_PW.setFont(new Font("������", Font.PLAIN, 15));
		txtField_PW.setBackground(bgcolor);
		txtField_PW.setForeground(Color.white);
		txtField_PW.setBorder(BorderFactory.createLineBorder(Color.white));
		txtField_PW.setColumns(10);
		txtField_PW.setBounds(135, 437, 189, 30);
		contentPane.add(txtField_PW);
	}
	//��ư
	public void setJButton() {
		//�α��� ��ư
		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("������", Font.BOLD, 15));
		btnLogin.setBounds(344, 386, 96, 78);
		contentPane.add(btnLogin);
		btnLogin.setBackground(new Color(92, 198, 181)); //*
		btnLogin.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		//ȸ������ ��ư
		btnSignIn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnSignIn.setForeground(Color.white);
		btnSignIn.setBounds(135, 487, 144, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setFont(new Font("������", Font.BOLD, 15)); //*
		btnSignIn.setBackground(new Color(92, 198, 181));
		btnSignIn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		//��й�ȣã�� ��ư
		btnSearchPW = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		btnSearchPW.setForeground(Color.white);
		btnSearchPW.setFont(new Font("������", Font.BOLD, 15));
		btnSearchPW.setBounds(296, 487, 144, 44);
		contentPane.add(btnSearchPW);
		btnSearchPW.setBackground(new Color(92, 198, 181));
		btnSearchPW.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		//ActionListener
		btnLogin.addActionListener(this);
		btnSignIn.addActionListener(this);
		btnSearchPW.addActionListener(this);
	}
	//â ��Ȱ��ȭ
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
	//â Ȱ��ȭ
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
		//�α��� ��ư Ŭ����//////////////////////////////////////////////
		if(e.getSource() == btnLogin) {
			try {
				//ȸ����ü
				MemberDBVO login = null;
				//�Է¹��� ��
				String id = txtField_ID.getText();
				String pw = new String(txtField_PW.getPassword());
				//������  ����
				dos.writeInt(1);
				dos.writeUTF(id);
				dos.writeUTF(1+"MemberDBVO loginCheck(String id)");
				//�������� ����
				if(ois != null) {
					login=(MemberDBVO)ois.readObject();
				}
				//�α��� �ý���
				if(id.equals("")) {//if in
					JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���.");
				}else if(id.contains(" ")) {
					JOptionPane.showMessageDialog(this, "������ ���ԵǾ��ֽ��ϴ�.");
				}else if(login==null){
					JOptionPane.showMessageDialog(this, "�������� �ʴ� ���̵��Դϴ�.\n���̵� Ȯ�����ּ���.");
				}else {
					if(!pw.equals("")) {
						if(pw.contains(" ")) {
							JOptionPane.showMessageDialog(this, "������ ���ԵǾ��ֽ��ϴ�.");
						}else if(login.getPw().equals(pw)){
								JOptionPane.showMessageDialog(this, "�α��� ����!\n" + login.getName() + "���� �湮�� ȯ���մϴ�.");
								//////////////////////////////////////////////////////////
								//���� �� ���� ȭ��
								new HeaderOrigin(login,dis,dos,ois,1).setVisible(true);			
								//////////////////////////////////////////////////////////
								this.dispose();
								this.setVisible(false);
						} else
							JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					} else
						JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���.");
				}//if out	
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//ȸ�����Թ�ư Ŭ����/////////////////////////////////////////////
		if(e.getSource() == btnSignIn) {
			SignInMain sign = new SignInMain(dis,dos);
			returnDisable();
			sign.setVisible(true);
		}
		//��й�ȣ ã�� Ŭ����/////////////////////////////////////////////
		if(e.getSource() == btnSearchPW) {
			SearchPwMain searchPw = new SearchPwMain(dis,dos);
			returnDisable();
			searchPw.setVisible(true);
		}
	}//ActionListener END
}// class END
