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
	
	private JPanel contentPane, logoImage;//�г�(�⺻�г�, �̹����г�)
	
	private JTextField txtID, txtName, txtPhone1, txtPhone2;//�ؽ�Ʈ�ʵ�(���̵�, ��й�ȣ, ��й�ȣ Ȯ��, �̸� , ��ȭ��ȣ1, 2)
	private JPasswordField txtPW, txtPWCheck;
	private JButton btnIDCheck, btnJoin, btnReset;//��ư(���̵�üũ, ȸ������, �ʱ�ȭ)
	private JLabel labelJoin, labelID, labelPW, labelPWCheck, labelConfirmPW, labelName, labelPhone, labelGender, labelDash1, labelDash2;
	//��(ȸ������, ���̵�, ��й�ȣ, ��й�ȣȮ��, ��й�ȣ ����, �̸�, ��ȭ��ȣ, ����, ��ȭ��ȣ �뽬)
	private JRadioButton radioMan, radioWomen;//����(��, ��)
	private final ButtonGroup buttonGroup = new ButtonGroup();//���� ��ư�׷�
	private JComboBox txtPhone0;//��ȭ��ȣ �޺��ڽ�
	private String checkID = "", confirmID = "";
	boolean isClickedOverlapButton = false;
	private Color bgcolor = new Color(92, 198, 181) ; //����
	
	//������
	public SignInMain(DataInputStream dis,DataOutputStream dos) {
		//Stream
		this.dis=dis;
		this.dos=dos;
		
		//GUI
		setContentPane();//�����г�
//		setImageJPanel();//��� �̹��� �г�
		setJLabel();//�� ����
		setJTextField();//�ؽ�Ʈ�ʵ�
		setJRadioButton();//������ư
		setJButton();//��ư
		setJComboBox();//�޺��ڽ�
		setLocationRelativeTo(null);
		 
	}//Constructor END
	//���� �ǳ�
	public void setContentPane() {
		setTitle("ȸ������");
		contentPane = new JPanel();
		setBounds(700, 100, 530, 640);
		setResizable(false);
		contentPane.setBackground(bgcolor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	//�����̹���
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
	//��
	public void setJLabel() {
		//ȸ������ ��
		labelJoin = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		labelJoin.setFont(new Font("������", Font.BOLD, 32));
		labelJoin.setForeground(Color.white);
		labelJoin.setHorizontalAlignment(SwingConstants.CENTER);
		labelJoin.setBounds(160, 27, 190, 60);
		contentPane.add(labelJoin);
		
		//���̵� ��
		labelID = new JLabel("\uC544\uC774\uB514 :");
		labelID.setHorizontalAlignment(SwingConstants.RIGHT);
		labelID.setFont(new Font("������", Font.BOLD, 15));
		labelID.setForeground(Color.white);
		labelID.setBounds(54, 140, 85, 30);
		contentPane.add(labelID);
		
		//��й�ȣ ��
		labelPW = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		labelPW.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPW.setFont(new Font("������", Font.BOLD, 15));
		labelPW.setForeground(Color.white);
		labelPW.setBounds(54, 190, 85, 30);
		contentPane.add(labelPW);
		
		//��й�ȣ Ȯ�� ��
		labelPWCheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778 :");
		labelPWCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPWCheck.setFont(new Font("������", Font.BOLD, 15));
		labelPWCheck.setForeground(Color.white);
		labelPWCheck.setBounds(12, 240, 127, 30);
		contentPane.add(labelPWCheck);		
		
		//��й�ȣ ���� ��
		labelConfirmPW = new JLabel("��й�ȣ ���Է�");
		labelConfirmPW.setHorizontalAlignment(SwingConstants.CENTER);
		labelConfirmPW.setFont(new Font("������", Font.PLAIN, 12));
		labelConfirmPW.setForeground(Color.white);
		labelConfirmPW.setBounds(388, 190, 105, 30);
		contentPane.add(labelConfirmPW);
		
		//�̸� ��
		labelName = new JLabel("\uC774\uB984 :");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelName.setFont(new Font("������", Font.BOLD, 15));
		labelName.setForeground(Color.white);
		labelName.setBounds(54, 290, 85, 30);
		contentPane.add(labelName);
		
		//��ȭ��ȣ ��
		labelPhone = new JLabel("\uC804\uD654\uBC88\uD638 :");
		labelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPhone.setFont(new Font("������", Font.BOLD, 15));
		labelPhone.setForeground(Color.white);
		labelPhone.setBounds(54, 340, 85, 30);
		contentPane.add(labelPhone);
		
		//���� ��
		labelGender = new JLabel("\uC131\uBCC4 :");
		labelGender.setHorizontalAlignment(SwingConstants.RIGHT);
		labelGender.setFont(new Font("������", Font.BOLD, 15));
		labelGender.setForeground(Color.white);
		labelGender.setBounds(54, 390, 85, 30);
		contentPane.add(labelGender);
		
		//��ȭ��ȣ -
		labelDash1 = new JLabel("-");
		labelDash1.setHorizontalAlignment(SwingConstants.CENTER);
		labelDash1.setFont(new Font("������", Font.PLAIN, 15));
		labelDash1.setForeground(Color.white);
		labelDash1.setBounds(218, 340, 15, 35);
		contentPane.add(labelDash1);
		
		//��ȭ��ȣ -
		labelDash2 = new JLabel("-");
		labelDash2.setHorizontalAlignment(SwingConstants.CENTER);
		labelDash2.setFont(new Font("������", Font.PLAIN, 15));
		labelDash2.setForeground(Color.white);
		labelDash2.setBounds(288, 340, 15, 35);
		contentPane.add(labelDash2);

	}
	//�ؽ�Ʈ�ʵ�
	public void setJTextField() {
		//���̵� �ؽ�Ʈ�ʵ�
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setFont(new Font("������", Font.PLAIN, 14));
		txtID.setBounds(160, 140, 198, 30);
		contentPane.add(txtID);
		
		//��й�ȣ �ؽ�Ʈ�ʵ�
		txtPW = new JPasswordField();
		txtPW.setColumns(10);
		txtPW.setFont(new Font("������", Font.PLAIN, 14));
		txtPW.setBounds(160, 190, 198, 30);
		txtPW.addKeyListener(this);
		contentPane.add(txtPW);
		
		//��й�ȣ Ȯ�� �ؽ�Ʈ�ʵ�
		txtPWCheck = new JPasswordField();
		txtPWCheck.setFont(new Font("������", Font.PLAIN, 14));
		txtPWCheck.setColumns(10);
		txtPWCheck.setBounds(160, 240, 198, 30);
		txtPWCheck.addKeyListener(this);
		contentPane.add(txtPWCheck);
		
		//��ȭ��ȣ ���ڸ�
		txtPhone1 = new JTextField();
		txtPhone1.setColumns(10);
		txtPhone1.setFont(new Font("������", Font.PLAIN, 14));
		txtPhone1.setBounds(233, 340, 55, 30);
		contentPane.add(txtPhone1);
		
		//��ȭ��ȣ ���ڸ�
		txtPhone2 = new JTextField();
		txtPhone2.setColumns(10);
		txtPhone2.setFont(new Font("������", Font.PLAIN, 14));
		txtPhone2.setBounds(303, 340, 55, 30);
		contentPane.add(txtPhone2);
		
		//�̸� �ؽ�Ʈ�ʵ�
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setFont(new Font("������", Font.PLAIN, 14));
		txtName.setBounds(160, 290, 198, 30);
		contentPane.add(txtName);
	}
	//������ư
	public void setJRadioButton() {
		//����_�� ������ư
		radioMan = new JRadioButton("\uB0A8\uC790");
		buttonGroup.add(radioMan);
		radioMan.setBackground(bgcolor);
		radioMan.setForeground(Color.white);
		radioMan.setFont(new Font("������", Font.PLAIN, 15));
		radioMan.setBounds(179, 390, 60, 30);
		radioMan.addActionListener(this);
		contentPane.add(radioMan);
		
		//����_�� ������ư
		radioWomen = new JRadioButton("\uC5EC\uC790");
		buttonGroup.add(radioWomen);
		radioWomen.setBackground(bgcolor);
		radioWomen.setForeground(Color.white);
		radioWomen.setFont(new Font("������", Font.PLAIN, 15));
		radioWomen.setBounds(279, 390, 60, 30);
		radioWomen.addActionListener(this);
		contentPane.add(radioWomen);
	}	
	//��ư
	public void setJButton() {
		//���̵� �ߺ��˻� ��ư
		btnIDCheck = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		btnIDCheck.setFont(new Font("������", Font.PLAIN, 12));
		btnIDCheck.setBounds(388, 140, 105, 30);
		contentPane.add(btnIDCheck);
		btnIDCheck.addActionListener(this);
		btnIDCheck.setBackground(bgcolor);
		btnIDCheck.setForeground(Color.white);
		btnIDCheck.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		//ȸ������ ��ư
		btnJoin = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnJoin.setFont(new Font("������", Font.PLAIN, 15));
		btnJoin.setBounds(273, 460, 85, 30);
		contentPane.add(btnJoin);
		btnJoin.addActionListener(this);
		btnJoin.setBackground(bgcolor);
		btnJoin.setForeground(Color.white);
		btnJoin.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
		//���� ��ư
		btnReset = new JButton("\uCD08\uAE30\uD654");
		btnReset.setFont(new Font("������", Font.PLAIN, 15));
		btnReset.setBounds(163, 460, 85, 30);
		contentPane.add(btnReset);
		btnReset.addActionListener(this);
		btnReset.setBackground(bgcolor);
		btnReset.setForeground(Color.white);
		btnReset.setBorder(BorderFactory.createLineBorder(Color.white));
		
	}
	//�޺��ڽ�
	public void setJComboBox() {
		//��ȭ��ȣ 010~
		txtPhone0 = new JComboBox();
		txtPhone0.setFont(new Font("������", Font.PLAIN, 14));
		txtPhone0.setForeground(Color.white);
		txtPhone0.setBackground(bgcolor);
		txtPhone0.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD", "010", "011", "016", "017", "018", "019", "050", "080", "\uC5C6\uC74C"}));
		txtPhone0.setBounds(163, 340, 55, 30);
		contentPane.add(txtPhone0);
		txtPhone0.addActionListener(this);
	}
	
	//�޼��� ��
	//����(���� - 1, ���� - 0)
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
		
		if(stringGender.equals("����")) {
			gender = 1;
		} else if (stringGender.equals("����")){
			gender = 0;
		}
		
		return gender;
	}
	//��� �˻�
	public String checkPW(String pw, String checkPw) {
		
		String label;
		if(!pw.equals("")) {
			if(pw.equals(checkPw)) {
				label = "��й�ȣ ��ġ";
			} else {
				label = "��й�ȣ ����ġ";
			}
		} else {
			label = "��й�ȣ ���Է�";
		}
		return label;
	}
	
	//ȸ������ 
	public void SignIn(String id, char[] pw, String name, String phone0, String phone1, String phone2, int gender, String labelConfirmPW) {
		if(id.equals("")) 
			JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���.");
		else if(id.contains(" ")) 
			JOptionPane.showMessageDialog(this, "���̵� ������ �ԷµǾ����ϴ�.");
		else if(new String(pw).equals(""))
			JOptionPane.showMessageDialog(this, "��й�ȣ�� �Է����ּ���.");
		else if(new String(pw).contains(" ")) 
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ������ �ԷµǾ����ϴ�.");
		else if(name.equals("")) 
			JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���.");
		else if(name.contains(" ")) 
			JOptionPane.showMessageDialog(this, "�̸��� ������ �ԷµǾ����ϴ�.");
		else if(gender != 1 && gender != 0) 
			JOptionPane.showMessageDialog(this, "������ �������ּ���.");
		else if(phone0.equals("����") || phone1.equals("") || phone1.length() != 4 || phone2.equals("") || phone2.length() != 4) 
			JOptionPane.showMessageDialog(this, "�ùٸ� ��ȭ��ȣ�� �Է����ּ���.");
		else if(phone1.contains(" ") || phone2.contains(" ")) 
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� ������ �ԷµǾ����ϴ�.");
		else if(labelConfirmPW.equals("��й�ȣ ����ġ")) 
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");					
		else {
			
			int result =0;
			try {
				//������  ����
				dos.writeInt(1);
				dos.writeUTF(txtID.getText());
				dos.writeUTF(4+new String(txtPW.getPassword())+","+txtName.getText()+","+(String)txtPhone0.getSelectedItem()+","+txtPhone1.getText()+","+txtPhone2.getText()+","+gender);
				//�������� ����
				if(dis != null) {
					result = dis.readInt();
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			if(result > 0) {
				this.dispose();
				this.setVisible(false);
				JOptionPane.showMessageDialog(this, "ȸ������ ����!\n" + txtName.getText() + "���� �湮�� ȯ���մϴ�.");
			}
		}
	}
	//ȭ�� �ʱ�ȭ
	public void ClearData() {
		txtID.setText("");
		txtPW.setText("");
		txtPWCheck.setText("");
		txtName.setText("");
		txtPhone1.setText("");
		txtPhone2.setText("");
		txtPhone0.setSelectedItem("����");
		buttonGroup.clearSelection();
	}

	//ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnIDCheck) {
			int check = 0;
			try {
				//������ ����
				dos.writeInt(1);
				dos.writeUTF(txtID.getText());
				dos.writeUTF(2+"int checkOverlapID(String id)");
				//�������� ����
				if(dis != null) {
					check = dis.readInt();
				}
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			//int check = 0; //0: ���̵� ���Է�, 1: ���̵� ����, -1: �������� �ʴ� ���̵�, -100: �����Է�
			if(check == 0) {
				JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���.");
				isClickedOverlapButton = false;
			} else if(check == -100) {
				JOptionPane.showMessageDialog(this, "���̵� ������ �ԷµǾ����ϴ�.");
				isClickedOverlapButton = false;
			} else if(check == 1) {
				JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���̵��Դϴ�.\n�ٸ� ���̵� �Է����ּ���.");
				isClickedOverlapButton = false;
			} else if(check == -1) {
				JOptionPane.showMessageDialog(this, "����� �� �ִ� ���̵��Դϴ�.");
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
							JOptionPane.showMessageDialog(this, "���̵� �ߺ��˻縦 �ǽ����ּ���.");							
					} else
						JOptionPane.showMessageDialog(this, "���̵� �ߺ��˻縦 �ǽ����ּ���.");
				} catch(NumberFormatException phoneNumberError) {
					JOptionPane.showMessageDialog(this, "�ùٸ� ��ȭ��ȣ�� �Է����ּ���.");
				}
			
		}
		
		if(e.getSource() == btnReset) {
			ClearData();
		}
		
	} //actionPerformed END
	//KeyListener(��й�ȣ ��ġ���� Ȯ�� �� ����)
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