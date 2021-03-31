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
	//�г�(����, �̹���)
	private JPanel contentPane, logoImage;
	//�ؽ�Ʈ�ʵ�(���̵�, �̸�)
	private JTextField txtId, txtName;
	//��ư(�˻�, ã��)
	private JButton btnCheck, btnSearch;
	//��(��������, ���̵�, �̸�)
	private JLabel labelSearchPw, labelId, labelName;
	
	boolean isClickedButton = false;
	String checkID = "", confirmID = "";
	
	private Color bgcolor = new Color(92, 198, 181) ; //����
	
	//������
	public SearchPwMain(DataInputStream dis,DataOutputStream dos) {
		//Stream
		this.dis=dis;
		this.dos=dos;
		//GUI
		setContentPane();//�����г�
//		setImageJPanel();//��� �̹��� �г�
		setJLabel();//��
		setJTextField();//�ؽ�Ʈ�ʵ�	
		setJButton();//��ư
		setLocationRelativeTo(null);
	}
	//�����ǳ�
	public void setContentPane() {
		setTitle("��й�ȣ ã��");
		setBounds(150, 150, 530, 300);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBackground(bgcolor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	//�̹��� �ǳ�
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
	//��
	public void setJLabel() {
		//��й�ȣ ã�� ��
		labelSearchPw = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		labelSearchPw.setFont(new Font("������", Font.BOLD, 32));
		labelSearchPw.setForeground(Color.white);
		labelSearchPw.setHorizontalAlignment(SwingConstants.CENTER);
		labelSearchPw.setBounds(137, 45, 236, 46);
		contentPane.add(labelSearchPw);
		
		//���̵� ��
		labelId = new JLabel("\uC544\uC774\uB514 :");
		labelId.setFont(new Font("������", Font.BOLD, 15));
		labelId.setForeground(Color.white);
		labelId.setHorizontalAlignment(SwingConstants.RIGHT);
		labelId.setBounds(73, 126, 75, 30);
		contentPane.add(labelId);
		
		//�̸� ��
		labelName = new JLabel("\uC774\uB984 :");
		labelName.setFont(new Font("������", Font.BOLD, 15));
		labelName.setForeground(Color.white);
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		labelName.setBounds(73, 176, 75, 30);
		contentPane.add(labelName);
	}
	//�ؽ�Ʈ�ʵ�
	public void setJTextField() {
		//���̵� �Է�â
		txtId = new JTextField(15);
		txtId.setBounds(163, 126, 180, 30);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		//�̸� �Է�â
		txtName = new JTextField(15);
		txtName.setColumns(10);
		txtName.setBounds(163, 176, 180, 30);
		contentPane.add(txtName);
	}
	//��ư
	public void setJButton() {
		//�α��� ��ȿ�� �˻� ��ư
		btnCheck = new JButton("\uAC80\uC0AC");
		btnCheck.setFont(new Font("������", Font.PLAIN, 15));
		btnCheck.setBounds(361, 126, 80, 30);
		btnCheck.addActionListener(this);
		contentPane.add(btnCheck);
		btnCheck.setBackground(bgcolor);
		btnCheck.setForeground(Color.white);
		btnCheck.setBorder(BorderFactory.createLineBorder(Color.white));
		
		//ã�� ��ư
		btnSearch = new JButton("\uCC3E\uAE30");
		btnSearch.setFont(new Font("������", Font.PLAIN, 15));
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
				//������ ����
				dos.writeInt(1);
				dos.writeUTF(txtId.getText());
				dos.writeUTF(2+"int checkOverlapID(String id)");
				//�������� ����
				if(dis != null) {
					checkid = dis.readInt();
				}
				//�ý���
				if(checkid == 0) {//if in
					JOptionPane.showMessageDialog(this, "���̵� �Է����ּ���.");
					isClickedButton = false;
				} else if(checkid == -100) {
					JOptionPane.showMessageDialog(this, "���̵� ������ �ԷµǾ����ϴ�.");
					isClickedButton = false;				
				} else if(checkid == -1) {
					JOptionPane.showMessageDialog(this, "���̵� �������� �ʽ��ϴ�.\n���̵� Ȯ�����ּ���.");
					isClickedButton = false;
				} else if(checkid == 1) {
					JOptionPane.showMessageDialog(this, "���̵� �����մϴ�.\n��� �������ּ���.");
					isClickedButton = true;
					checkID = txtId.getText();
				} else {
					JOptionPane.showMessageDialog(this, "�� �� ���� ������ �߻��Ͽ����ϴ�.\n�����ڿ��� �������ּ���.");
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
						//������ ����
						dos.writeInt(1);//"String SeacrhPW(String txtId, String txtName)"
						dos.writeUTF(txtId.getText());
						dos.writeUTF(3+txtName.getName());
						//�������� ����
						if(dis != null) {
							message = dis.readUTF();
						}
						//�ý���
						JOptionPane.showMessageDialog(this, message);
						if(message.contains("��й�ȣ ã�� ����!")) {
						this.dispose();
						this.setVisible(false);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//try out
				} else {
					JOptionPane.showMessageDialog(this, "���̵� �˻縦 �ǽ����ּ���.");					
				}//if 2 out
			} else {
				JOptionPane.showMessageDialog(this, "���̵� �˻縦 �ǽ����ּ���.");	
			}//if 1 out
											
		} //btnSearch END
	}//actionPerformed END		
}