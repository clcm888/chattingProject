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
	//로그인(ID, 비밀번호 일치유무) - DB(Select)
	public MemberDBVO loginCheck(String id) {
		//check = 1 : 로그인 성공
		//check = 0 : 비밀번호 오류
		//check = -1 : 아이디 오류
		
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
	//아이디 중복검사(조건을 바꿔서 비밀번호를 찾을때도 사용)
	public int checkOverlapID(String id) {
		
		String sql = "SELECT mid FROM memberdata WHERE mid = ?";
		String getId = "";
		int check = 0; //0: 아이디 미입력, 1: 아이디 존재, -1: 존재하지 않는 아이디
		
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
	//가입회원 등록 - DB(Insert)
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
	//비밀번호 찾기
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
				showMessage =  "아이디를 입력해주세요.";
			else if(check == -1) 
				showMessage =  "아이디가 존재하지 않습니다.\n아이디를 확인해주세요.";
			else if(check == 1) {
				if(txtName.contains(" "))
					showMessage = "이름에 공백이 입력되었습니다.";
				else if(txtName.equals(getName))
					showMessage = "비밀번호 찾기 성공!\n" + getId + "님의 비밀번호는 \"" + getPw + "\" 입니다.";
				else {
					if(txtName.equals("")) {
						showMessage = "이름을 입력해주세요.";
					} else
						showMessage = "회원님의 아이디와 이름이 일치하지 않습니다.";
				}
			} else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return showMessage;
	}
	public boolean logUpdate(String id,String log) throws SQLException {//비밀번호변경

		String sql = "update memberdata set log = ? where id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, log);
		
		int rsl = pstmt.executeUpdate();
		if(rsl==0) {
			//System.out.println("변경실패");
			return false;
		}
		//System.out.println("변경성공");
		return true;
	}
}
