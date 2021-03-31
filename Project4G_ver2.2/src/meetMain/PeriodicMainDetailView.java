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
	
	private Color bgcolor = new Color(92, 198, 181) ; //배경색
	
	//텍스트필드
	public void setJTextField() {
	}
	//라벨
	public void setJLabel() {
		//글상세보기
		labelViewContent = new JLabel("\uAE00 \uC0C1\uC138\uBCF4\uAE30");
		labelViewContent.setHorizontalAlignment(SwingConstants.CENTER);
		labelViewContent.setFont(new Font("새굴림", Font.BOLD, 20));
		labelViewContent.setForeground(Color.white);
		labelViewContent.setBounds(188, 12, 151, 35);
		contentPane.add(labelViewContent);
		//제목
		labelTitle = new JLabel("\uC81C\uBAA9");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(10, 50, 50, 35);
		labelTitle.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelTitle.setForeground(Color.white);
		contentPane.add(labelTitle);
		//작성 제목
		txtTitle = new JLabel();
		txtTitle.setBounds(65, 50, 457, 35);
		txtTitle.setFont(new Font("새굴림", Font.PLAIN, 15));
		txtTitle.setForeground(Color.white);
		contentPane.add(txtTitle);
		//작성자
		labelWriter = new JLabel("\uC791\uC131\uC790");
		labelWriter.setHorizontalAlignment(SwingConstants.CENTER);
		labelWriter.setBounds(10, 90, 50, 35);
		labelWriter.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelWriter.setForeground(Color.white);
		contentPane.add(labelWriter);
		//아이디
		labelID = new JLabel();
		labelID.setBounds(65, 90, 175, 35);
		labelID.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelID.setForeground(Color.white);
		contentPane.add(labelID);
		//작성일
		labelDate = new JLabel("\uC791\uC131\uC77C\uC790");
		labelDate.setHorizontalAlignment(SwingConstants.CENTER);
		labelDate.setBounds(245, 90, 60, 35);
		labelDate.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelDate.setForeground(Color.white);
		contentPane.add(labelDate);
		//작성일자
		labelWriteDay = new JLabel("\uC791\uC131\uC77C");
		labelWriteDay.setBounds(310, 90, 212, 35);
		labelWriteDay.setFont(new Font("새굴림", Font.PLAIN, 13));
		labelWriteDay.setForeground(Color.white);
		contentPane.add(labelWriteDay);
	
		//글내용
		labelContent = new JLabel("\uAE00\uB0B4\uC6A9");
		labelContent.setHorizontalAlignment(SwingConstants.CENTER);
		labelContent.setBounds(10, 130, 50, 35);
		labelContent.setFont(new Font("새굴림", Font.PLAIN, 15));
		labelContent.setForeground(Color.white);
		contentPane.add(labelContent);
		//작성내용
		txtContent = new JLabel();
		txtContent.setBounds(10, 170, 512, 143);
		txtContent.setFont(new Font("새굴림", Font.PLAIN, 15));
		txtContent.setForeground(Color.white);
		txtContent.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		contentPane.add(txtContent);
		
	}
	//버튼
	public void setJButton() {
		//수정버튼
		btnModify= new JButton("수정");
		btnModify.setBounds(284, 353, 70, 30);
		btnModify.setForeground(Color.WHITE);
		btnModify.setFont(new Font("새굴림", Font.PLAIN, 15));
		btnModify.setBackground(new Color(92, 198, 181)); //*
		btnModify.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		contentPane.add(btnModify);
		//삭제버튼
		btnDelete = new JButton("\uC0AD\uC81C");
		btnDelete.setBounds(368, 353, 70, 30);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("새굴림", Font.PLAIN, 15));
		btnDelete.setBackground(new Color(92, 198, 181)); //*
		btnDelete.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		contentPane.add(btnDelete);
		//취소버튼
		btnCancel = new JButton("\uCDE8\uC18C");
		btnCancel.setBounds(452, 353, 70, 30);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("새굴림", Font.PLAIN, 15));
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
		setLocationRelativeTo(null); //화면 중앙 팝업 명령어
		
		//텍스트박스
		setJTextField();
		
		//라벨
		setJLabel();
		//제목 텍스트필드
		txtTitle.setText(" " + noticeArray.get(i).getTitle());
		//내용 텍스트필드
		txtContent.setText(" " + noticeArray.get(i).getContents());
		//아이디
		labelID.setText(" " + noticeArray.get(i).getWriter());
		//작성일자
		labelWriteDay.setText(" " + noticeArray.get(i).getTime());

		//버튼
		setJButton();
		//수정버튼
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
		//삭제버튼
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
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
					JOptionPane.showMessageDialog(null, "삭제 완료!", "", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					setVisible(false);
				} else {
					System.out.println("취소");
				}
			}
		});
		//취소버튼
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
			}
		});
		
	}
}
