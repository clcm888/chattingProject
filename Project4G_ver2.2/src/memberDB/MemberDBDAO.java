package memberDB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import commonDBConn.DBConn;
import loginMain.LoginMain;
import loginMain.SearchPwMain;
import loginMain.SignInMain;

public class MemberDBDAO extends JFrame {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	MemberDBVO loginInfo;
	ArrayList<MemberDBVO> info;
	
	public MemberDBDAO() {
		conn = new DBConn().getConnection();
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//loginMain
	//�α���(ID, ��й�ȣ ��ġ����) - DB(Select)
	public MemberDBVO loginCheck(String id) {
		//check = 1 : �α��� ����
		//check = 0 : ��й�ȣ ����
		//check = -1 : ���̵� ����
		
		String sql = "SELECT * FROM memberdata WHERE mid=?";
		MemberDBVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberDBVO(
					rs.getString("mid"),
					rs.getString("mname"),
					rs.getString("pw"),
					rs.getString("phonenum"),
					rs.getInt("gender"),
					rs.getInt("madmin"),
					rs.getString("chatlog"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//signInMain
	//���̵� �ߺ��˻�(������ �ٲ㼭 ��й�ȣ�� ã������ ���)
	public int checkOverlapID(String id) {
		
		String sql = "SELECT mid FROM memberdata WHERE mid = ?";
		String getId = "";
		int check = 0; //0: ���̵� ���Է�, 1: ���̵� ����, -1: �������� �ʴ� ���̵�
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				getId = rs.getString("mid");
			}
			if(id.equals("")) 
				check = 0;
			else if(id.contains(" "))
				check = -100;
			else if(id.equals(getId)) 
				check = 1;
			else {
				check = -1;
				id = "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	///////////////////////////////////////////////////////////////////////////////
	//����ȸ�� ��� - DB(Insert)
	public int SignInMember(String id, String pw, String name, int phone0, int phone1, int phone2, int gender) {
		
		String sql = "INSERT INTO memberdata VALUES (?,?,?,?,?,0,?)";
		String blank = "N";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setInt(4, gender);
			pstmt.setString(5, Integer.toString(phone0) + Integer.toString(phone1) + Integer.toString(phone2));
			pstmt.setString(6, blank);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//searchPwMain
	//��й�ȣ ã��
	public String SeacrhPW(String txtId, String txtName) {
		
		String sql = "SELECT mid, mname, pw FROM memberdata WHERE mid = ?";
		String getId = "", getName = "", getPw = "";
		int check = 0;
		String showMessage = "";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				getId = rs.getString("mid");
				getName = rs.getString("mname");
				getPw = rs.getString("pw");
			}
			check = checkOverlapID(txtId);
			if(check == 0) 
				showMessage =  "���̵� �Է����ּ���.";
			else if(check == -1) 
				showMessage =  "���̵� �������� �ʽ��ϴ�.\n���̵� Ȯ�����ּ���.";
			else if(check == 1) {
				if(txtName.contains(" "))
					showMessage = "�̸��� ������ �ԷµǾ����ϴ�.";
				else if(txtName.equals(getName))
					showMessage = "��й�ȣ ã�� ����!\n" + getId + "���� ��й�ȣ�� \"" + getPw + "\" �Դϴ�.";
				else {
					if(txtName.equals("")) {
						showMessage = "�̸��� �Է����ּ���.";
					} else
						showMessage = "ȸ������ ���̵�� �̸��� ��ġ���� �ʽ��ϴ�.";
				}
			} else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return showMessage;
	}
	public boolean logUpdate(String id,String log) throws SQLException {//��й�ȣ����

		String sql = "update memberdata set log = ? where id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, log);
		
		int rsl = pstmt.executeUpdate();
		if(rsl==0) {
			//System.out.println("�������");
			return false;
		}
		//System.out.println("���漺��");
		return true;
	}
}
