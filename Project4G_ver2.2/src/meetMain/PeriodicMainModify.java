package meetMain;


import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import memberDB.MemberDBVO;

import noticeboardDB.NoticeboardDBVO;

import javax.swing.JLabel;
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
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.JButton;

public class PeriodicMainModify extends JFrame/* implements ActionListener*/ {

   private JPanel contentPane, imageJPanel;
   private JTextField txtContent, txtTitle;
   private JLabel labelViewContent, labelTitle, labelWriter, labelID, labelDate, labelWriteDay, labelContent;
   private JButton btnModify, btnConfirm, btnDelete, btnCancel;
   private int i, page;
   private NoticeboardDBVO nvo;
   private String writer;
   private String time;
   private String title;
   
   static MemberDBVO login;
   static ArrayList<NoticeboardDBVO> noticeArray;
   
   private Color bgcolor = new Color(92, 198, 181) ; //����
   
   //�ؽ�Ʈ�ʵ�
   public void setJTextField() {
      //�ۼ�����
      txtContent = new JTextField();
      txtContent.setBounds(10, 188, 520, 143);
      txtContent.setBackground(new Color(114,234,221));
      txtContent.setBorder(BorderFactory.createLineBorder(Color.white, 1));
      contentPane.add(txtContent);
   }
   //��
   public void setJLabel() {
      //�ۻ󼼺���
      labelViewContent = new JLabel("�� �����ϱ�");
      labelViewContent.setFont(new Font("������", Font.BOLD, 20));
      labelViewContent.setForeground(Color.white);
      labelViewContent.setBounds(15, 10, 190, 35);
      contentPane.add(labelViewContent);
      //����
      labelTitle = new JLabel("\uC81C\uBAA9");
      labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
      labelTitle.setFont(new Font("������", Font.PLAIN, 15));
      labelTitle.setForeground(Color.white);
      labelTitle.setBounds(10, 68, 50, 35);
      contentPane.add(labelTitle);
    //�ۼ� ����
          txtTitle = new JTextField();
          txtTitle.setBounds(65, 68, 460, 35);
          txtTitle.setFont(new Font("������", Font.PLAIN, 15));
          txtTitle.setForeground(Color.black);
          txtTitle.setBackground(new Color(114,234,221));
          txtTitle.setBorder(BorderFactory.createLineBorder(Color.white, 1));
          contentPane.add(txtTitle);
          //�ۼ���
          labelWriter = new JLabel("\uC791\uC131\uC790");
          labelWriter.setHorizontalAlignment(SwingConstants.CENTER);
          labelWriter.setBounds(10, 108, 50, 35);
          labelWriter.setFont(new Font("������", Font.PLAIN, 15));
          labelWriter.setForeground(Color.white);
          contentPane.add(labelWriter);
          //���̵�
          labelID = new JLabel();
          labelID.setBounds(65, 108, 175, 35);
          labelID.setFont(new Font("������", Font.PLAIN, 15));
          labelID.setForeground(Color.white);
          contentPane.add(labelID);
          //�ۼ���
          labelDate = new JLabel("\uC791\uC131\uC77C\uC790");
          labelDate.setHorizontalAlignment(SwingConstants.CENTER);
          labelDate.setFont(new Font("������", Font.PLAIN, 15));
          labelDate.setForeground(Color.white);
          labelDate.setBounds(245, 108, 60, 35);
          contentPane.add(labelDate);
          //�ۼ�����
          labelWriteDay = new JLabel("\uC791\uC131\uC77C");
          labelWriteDay.setBounds(310, 108, 215, 35);
          labelWriteDay.setFont(new Font("������", Font.PLAIN, 15));
          labelWriteDay.setForeground(Color.white);
          contentPane.add(labelWriteDay);
          //�۳���
          labelContent = new JLabel("\uAE00\uB0B4\uC6A9");
          labelContent.setHorizontalAlignment(SwingConstants.CENTER);
          labelContent.setBounds(10, 148, 50, 35);
          labelContent.setFont(new Font("������", Font.PLAIN, 15));
          labelContent.setForeground(Color.white);
          contentPane.add(labelContent);
          //�ۼ�����
          txtContent = new JTextField();
          txtContent.setBounds(10, 188, 520, 143);
          txtContent.setFont(new Font("������", Font.PLAIN, 15));
          txtContent.setForeground(Color.white);
          txtContent.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
          contentPane.add(txtContent);
      
   }
   //��ư
   public void setJButton() {
      //������ư
      btnModify= new JButton("�Ϸ�");
      btnModify.setBounds(376, 352, 70, 30);
      btnModify.setFont(new Font("������", Font.PLAIN, 15));
      btnModify.setBackground(bgcolor);
      btnModify.setForeground(Color.white);
      btnModify.setBorder(BorderFactory.createLineBorder(Color.white));
      contentPane.add(btnModify);
      //��ҹ�ư
      btnCancel = new JButton("\uCDE8\uC18C");
      btnCancel.setBounds(460, 352, 70, 30);
      btnCancel.setFont(new Font("������", Font.PLAIN, 15));
      btnCancel.setBackground(bgcolor);
      btnCancel.setBorder(BorderFactory.createLineBorder(Color.white));
      btnCancel.setForeground(Color.white);
      contentPane.add(btnCancel);      
   }
   
   public PeriodicMainModify(MemberDBVO login, DataInputStream dis,DataOutputStream dos,ObjectInputStream ois, ArrayList<NoticeboardDBVO> noticeArray, int i, int page) throws ClassNotFoundException, SQLException {
      
      writer = login.getName();
      time =noticeArray.get(i).getTime();
      
      setBounds(100, 100, 550, 450);
      contentPane = new JPanel();
      setTitle("�Խñ� ���� ������");
      contentPane.setBackground(bgcolor);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      setLocationRelativeTo(null); //ȭ�� �߾� �˾� ��ɾ�
      contentPane.setLayout(null);
      
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
      
    //����Ȯ�ι�ư
    		btnModify.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				if (e.getSource() == btnModify) {
    					try {
    						
    						try {
    							dos.writeInt(5);
    							dos.writeUTF(writer+","+txtTitle.getText());
    							dos.writeUTF(1+time+txtContent.getText());
    						} catch (IOException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}try {
    							System.out.println(dis.read());
    							noticeArray.get(i).setTitle(txtTitle.getText());
    							noticeArray.get(i).setContents(txtContent.getText());
    						} catch (IOException e2) {
    							// TODO Auto-generated catch block
    							e2.printStackTrace();
    								
    						}
    						new PeriodicMainDetailView(login,  dis, dos, ois, noticeArray, i, page).setVisible(true);
    						dispose();
    						setVisible(false);
    					} catch (SQLException | ClassNotFoundException e1) {
    						e1.printStackTrace();
    					}
    				}
    			}
    		});
      
      //��ҹ�ư
      btnCancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               new PeriodicMainDetailView(login, dis, dos, ois, noticeArray, i, page).setVisible(true);
               dispose();
               setVisible(false);            
            } catch (ClassNotFoundException | SQLException e1) {
               e1.printStackTrace();
            }      
         }
      });
      
   }
}


