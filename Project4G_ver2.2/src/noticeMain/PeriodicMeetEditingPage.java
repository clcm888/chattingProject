package noticeMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import mainFrameDB.MainDBDAO;
import mainFrameDB.MainDBVO;
import memberDB.MemberDBVO;

public class PeriodicMeetEditingPage extends Notice {
	
	private JButton confirmButton;
	private JDialog jdialog;
	private Color bgcolor = new Color(92, 198, 181) ; //����
	
	public PeriodicMeetEditingPage(String meeting,DataInputStream dis,DataOutputStream dos) {
		jdialog = new JDialog();
		jdialog.setTitle("������ ������");
		jdialog.setVisible(true);
		jdialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		jdialog.getContentPane().setLayout(null);
		jdialog.setSize(550,450);
		jdialog.setLocationRelativeTo(null); //ȭ�� �߾� �˾� ��ɾ�
		jdialog.getContentPane().setBackground(bgcolor);

		JLabel titleLabel = new JLabel("�������� ����");
		titleLabel.setBounds(188, 10, 145, 40);
		titleLabel.setFont(new Font("������", Font.BOLD, 20));
		titleLabel.setForeground(Color.white);
		jdialog.getContentPane().add(titleLabel);

		JTextArea editingArea = new JTextArea();
		editingArea.setBounds(20, 56, 490, 265);
		String meetingTxt = meeting;
		editingArea.setText(meetingTxt);
		editingArea.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		editingArea.setFont(new Font("������", Font.PLAIN, 13));
		editingArea.setBackground(bgcolor);
		editingArea.setForeground(Color.white);
		editingArea.setLineWrap(true);
		jdialog.getContentPane().add(editingArea);
		

		confirmButton = new JButton("Ȯ��");
		confirmButton.setFont(new Font("������", Font.PLAIN, 15));
		confirmButton.setForeground(Color.white);
		confirmButton.setBackground(bgcolor);
		confirmButton.setBorder(new LineBorder(Color.white));
		confirmButton.setBounds(431, 350, 79, 28);
		jdialog.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String result="";
					String meetingInfo=editingArea.getText();
					dos.writeInt(2);
					dos.writeUTF(meetingInfo);
					dos.writeUTF(3+"meetingScheduleUpdate(String nextMeetingSchedule)");
					if(ois != null) {
						result = dis.readUTF();
					}
					if(!result.equals("N")) {
						pageOut(meetingInfo);
					}else {
						JOptionPane.showMessageDialog(jdialog, "�߸��������Դϴ�.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	public void pageOut(String meetingInfo) {
		jdialog.setVisible(false);
		jdialog.dispose();
		super.noticeTxtArea2.setText(meetingInfo);
	}
}







