package meetMain;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.IOP.ExceptionDetailMessage;

import mainFrameDB.MainDBVO;
import memberDB.MemberDBVO;
import noticeboardDB.NoticeboardDBDAO;
import noticeboardDB.NoticeboardDBVO;

public class PeriodicMainAdd extends JFrame {

	private JPanel contentPane, imageJPanel;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	
	private JTextField txtContent, txtTitle;
	private JLabel labelViewContent, labelTitle, labelWriter, labelID, labelDate, labelWriteDay, labelContent;
	private JButton btnConfirm, btnCancel;
	private int i, page;
	private MemberDBVO login;
	
	static MainDBVO main;
	static ArrayList<NoticeboardDBVO> noticeArray;
	
	private NoticeboardDBVO nvo;

	private Color bgcolor = new Color(92, 198, 181) ; //����

	public PeriodicMainAdd(MemberDBVO login, DataInputStream dis,DataOutputStream dos,ObjectInputStream ois, int page, ArrayList<NoticeboardDBVO> noticeArray) {
		this.login = login;
		this.dis=dis;
		this.dos=dos;
		this.ois=ois;

		setBounds(100, 100, 550, 450);
		setTitle("�Խñ� �߰� ������");
		setLocationRelativeTo(null); //ȭ�� �߾� �˾� ��ɾ�
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(bgcolor);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �ؽ�Ʈ�ڽ�
		setJTextField();

		// ��
		setJLabel();
		// ���̵�
		 labelID.setText(login.getId());

		// ��ư
		setJButton();
		// Ȯ�ι�ư
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = txtTitle.getText();
				String contents = txtContent.getText();
				if(contents.equals(""))
					contents = " ";
				if (title.trim().equals("")) {
					showMessage();
				}else {
				try {
					
	                  
					
						dos.writeInt(5);
						dos.writeUTF(login.getName()+","+contents);
						dos.writeUTF(page+title);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtTitle.setText("");
				txtContent.setText("");
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
				setVisible(false);
				}
			}
		});
		// ��ҹ�ư
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
			}
		});
	}
	
	// �ؽ�Ʈ�ʵ�
		public void setJTextField() {
			// ���� �ؽ�Ʈ�ʵ�
			txtTitle = new JTextField();
			txtTitle.setColumns(10);
			txtTitle.setBounds(72, 71, 452, 30);
			txtTitle.setBackground(bgcolor);
			txtTitle.setBorder(new LineBorder(Color.white));
			txtTitle.setFont(new Font("������", Font.PLAIN, 13));
			txtTitle.setForeground(Color.white);
			contentPane.add(txtTitle);
			// ���� �ؽ�Ʈ�ʵ�
			txtContent = new JTextField();
			txtContent.setColumns(10);
			txtContent.setBounds(17, 185, 507, 143);
			txtContent.setBackground(bgcolor);
			txtContent.setBorder(new LineBorder(Color.white));
			txtContent.setFont(new Font("������", Font.PLAIN, 13));
			txtContent.setForeground(Color.white);
			contentPane.add(txtContent);
		}

		// ��
		public void setJLabel() {
			// �ۻ󼼺���
			labelViewContent = new JLabel("\uAE00 \uCD94\uAC00\uD558\uAE30");
			labelViewContent.setFont(new Font("������", Font.BOLD, 20));
			labelViewContent.setForeground(Color.white);
			labelViewContent.setBounds(217, 10, 121, 35);
			contentPane.add(labelViewContent);
			// ����
			labelTitle = new JLabel("\uC81C\uBAA9");
			labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
			labelTitle.setFont(new Font("������", Font.PLAIN, 15));
			labelTitle.setForeground(Color.white);
			labelTitle.setBounds(17, 71, 50, 30);
			contentPane.add(labelTitle);
			// �ۼ���
			labelWriter = new JLabel("\uC791\uC131\uC790");
			labelWriter.setHorizontalAlignment(SwingConstants.CENTER);
			labelWriter.setFont(new Font("������", Font.PLAIN, 15));
			labelWriter.setForeground(Color.white);
			labelWriter.setBounds(12, 111, 50, 30);
			contentPane.add(labelWriter);
			// ���̵�
			labelID = new JLabel();
			labelID.setBounds(72, 111, 150, 30);
			labelID.setFont(new Font("������", Font.PLAIN, 15));
			labelID.setForeground(Color.white);
			contentPane.add(labelID);
			// �ۼ���
			labelDate = new JLabel("\uC791\uC131\uC77C\uC790");
			labelDate.setBounds(252, 111, 60, 30);
			labelDate.setFont(new Font("������", Font.PLAIN, 13));
			labelDate.setForeground(Color.white);
			contentPane.add(labelDate);
			// �ۼ�����
			labelWriteDay = new JLabel("\uC791\uC131\uC77C");
			labelWriteDay.setBounds(322, 111, 185, 30);
			labelWriteDay.setFont(new Font("������", Font.PLAIN, 15));
			labelWriteDay.setForeground(Color.white);
			contentPane.add(labelWriteDay);
			// �۳���
			labelContent = new JLabel("\uAE00\uB0B4\uC6A9");
			labelContent.setHorizontalAlignment(SwingConstants.CENTER);
			labelContent.setBounds(12, 155, 50, 30);
			labelContent.setFont(new Font("������", Font.PLAIN, 13));
			labelContent.setForeground(Color.white);
			contentPane.add(labelContent);

		}

		// ��ư
		public void setJButton() {
			// Ȯ�ι�ư
			btnConfirm = new JButton("Ȯ��");
			btnConfirm.setBounds(368, 358, 70, 30);
			btnConfirm.setForeground(Color.WHITE);
			btnConfirm.setFont(new Font("������", Font.PLAIN, 15));
			btnConfirm.setBackground(new Color(92, 198, 181)); //*
			btnConfirm.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			contentPane.add(btnConfirm);
			// ��ҹ�ư
			btnCancel = new JButton("\uCDE8\uC18C");
			btnCancel.setBounds(452, 358, 70, 30);
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setFont(new Font("������", Font.PLAIN, 15));
			btnCancel.setBackground(new Color(92, 198, 181)); //*
			btnCancel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			contentPane.add(btnCancel);
		}

		/**
		 * Launch the application.
		 */
		/*public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						PeriodicMainAdd frame = new PeriodicMainAdd( login,  dis, dos, ois,  page,  noticeArray);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}*/
		public void showMessage() {
		      JOptionPane.showMessageDialog(this, "������ �Է����ּ���.");
		   }
}

