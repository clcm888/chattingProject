package meetMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainFrameDB.MainDBVO;
import memberDB.MemberDBVO;
import noticeboardDB.NoticeboardDBDAO;
import noticeboardDB.NoticeboardDBVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class PeriodicMainDetailView extends JFrame/* implements ActionListener*/ {

	private JPanel contentPane, imageJPanel;
	private JLabel labelViewContent, labelTitle, labelWriter, labelID, labelDate, labelWriteDay, labelContent, txtContent, txtTitle;
	private JButton btnModify, btnDelete, btnCancel;
	private static int i, page;
	private NoticeboardDBDAO ndao;
	private NoticeboardDBVO nvo;
	
	static MemberDBVO login;
	static MainDBVO main;
	static ArrayList<NoticeboardDBVO> noticeArray;
	
	private Color bgcolor = new Color(92, 198, 181) ; //����
	
	//�ؽ�Ʈ�ʵ�
	public void setJTextField() {
	}
	//��
	public void setJLabel() {
		//�ۻ󼼺���
		labelViewContent = new JLabel("\uAE00 \uC0C1\uC138\uBCF4\uAE30");
		labelViewContent.setHorizontalAlignment(SwingConstants.CENTER);
		labelViewContent.setFont(new Font("������", Font.BOLD, 20));
		labelViewContent.setForeground(Color.white);
		labelViewContent.setBounds(188, 12, 151, 35);
		contentPane.add(labelViewContent);
		//����
		labelTitle = new JLabel("\uC81C\uBAA9");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(10, 50, 50, 35);
		labelTitle.setFont(new Font("������", Font.PLAIN, 15));
		labelTitle.setForeground(Color.white);
		contentPane.add(labelTitle);
		//�ۼ� ����
		txtTitle = new JLabel();
		txtTitle.setBounds(65, 50, 457, 35);
		txtTitle.setFont(new Font("������", Font.PLAIN, 15));
		txtTitle.setForeground(Color.white);
		contentPane.add(txtTitle);
		//�ۼ���
		labelWriter = new JLabel("\uC791\uC131\uC790");
		labelWriter.setHorizontalAlignment(SwingConstants.CENTER);
		labelWriter.setBounds(10, 90, 50, 35);
		labelWriter.setFont(new Font("������", Font.PLAIN, 15));
		labelWriter.setForeground(Color.white);
		contentPane.add(labelWriter);
		//���̵�
		labelID = new JLabel();
		labelID.setBounds(65, 90, 175, 35);
		labelID.setFont(new Font("������", Font.PLAIN, 15));
		labelID.setForeground(Color.white);
		contentPane.add(labelID);
		//�ۼ���
		labelDate = new JLabel("\uC791\uC131\uC77C\uC790");
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate.setBounds(245, 90, 60, 35);
		labelDate.setFont(new Font("������", Font.PLAIN, 15));
		labelDate.setForeground(Color.white);
		contentPane.add(labelDate);
		//�ۼ�����
		labelWriteDay = new JLabel("\uC791\uC131\uC77C");
		labelWriteDay.setBounds(310, 90, 212, 35);
		labelWriteDay.setFont(new Font("������", Font.PLAIN, 13));
		labelWriteDay.setForeground(Color.white);
		contentPane.add(labelWriteDay);
	
		//�۳���
		labelContent = new JLabel("\uAE00\uB0B4\uC6A9");
		labelContent.setHorizontalAlignment(SwingConstants.CENTER);
		labelContent.setBounds(10, 130, 50, 35);
		labelContent.setFont(new Font("������", Font.PLAIN, 15));
		labelContent.setForeground(Color.white);
		contentPane.add(labelContent);
		//�ۼ�����
		txtContent = new JLabel();
		txtContent.setBounds(10, 170, 512, 143);
		txtContent.setFont(new Font("������", Font.PLAIN, 15));
		txtContent.setForeground(Color.white);
		txtContent.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		contentPane.add(txtContent);
		
	}
	//��ư
	public void setJButton() {
		//������ư
		btnModify= new JButton("����");
		btnModify.setBounds(284, 353, 70, 30);
		btnModify.setForeground(Color.WHITE);
		btnModify.setFont(new Font("������", Font.PLAIN, 15));
		btnModify.setBackground(new Color(92, 198, 181)); //*
		btnModify.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		contentPane.add(btnModify);
		//������ư
		btnDelete = new JButton("\uC0AD\uC81C");
		btnDelete.setBounds(368, 353, 70, 30);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("������", Font.PLAIN, 15));
		btnDelete.setBackground(new Color(92, 198, 181)); //*
		btnDelete.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		contentPane.add(btnDelete);
		//��ҹ�ư
		btnCancel = new JButton("\uCDE8\uC18C");
		btnCancel.setBounds(452, 353, 70, 30);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("������", Font.PLAIN, 15));
		btnCancel.setBackground(new Color(92, 198, 181)); //*
		btnCancel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		contentPane.add(btnCancel);		
	}
	
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeriodicMainDetailView frame = new PeriodicMainDetailView(login, main, noticeArray, i, page);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the frame.
	 */
	public PeriodicMainDetailView(MemberDBVO login,DataInputStream dis,DataOutputStream dos,ObjectInputStream ois, ArrayList<NoticeboardDBVO> noticeArray, int i, int page) throws ClassNotFoundException,SQLException {
		
		setBounds(100, 100, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(bgcolor);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); //ȭ�� �߾� �˾� ��ɾ�
		
		//�ؽ�Ʈ�ڽ�
		setJTextField();
		
		//��
		setJLabel();
		//���� �ؽ�Ʈ�ʵ�
		txtTitle.setText(" " + noticeArray.get(i).getTitle());
		//���� �ؽ�Ʈ�ʵ�
		txtContent.setText(" " + noticeArray.get(i).getContents());
		//���̵�
		labelID.setText(" " + noticeArray.get(i).getWriter());
		//�ۼ�����
		labelWriteDay.setText(" " + noticeArray.get(i).getTime());

		//��ư
		setJButton();
		//������ư
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PeriodicMainModify(login,dis, dos, ois, noticeArray, i, page).setVisible(true);;	
				} catch(ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
				setVisible(false);
			}
			private NoticeboardDBVO NoticeboardDBVO() {
				return null;
			}
		});
		//������ư
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "���� �Ͻðڽ��ϱ�?", "", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {	
					try {
						dos.writeInt(5);
						dos.writeUTF(noticeArray.get(i).getWriter());
						dos.writeUTF(5+noticeArray.get(i).getTime()+noticeArray.get(i).getTitle());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						System.out.println(dis.readUTF());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "���� �Ϸ�!", "", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					setVisible(false);
				} else {
					System.out.println("���");
				}
			}
		});
		//��ҹ�ư
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
			}
		});
		
	}
}
