package mainFrameDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import commonDBConn.DBConn;


public class MainDBDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MainDBDAO() throws ClassNotFoundException, SQLException{
		con= new DBConn().getConnection();
	}
	//���γ���ҷ�����
	public MainDBVO mainSelect() throws SQLException {
		String sql = "Select * from maindata";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		MainDBVO fvo = null;
		while(rs.next()){
			String notice = rs.getString("notice");
			String meetingSchedule = rs.getString("meetingSchedule");
			fvo = new MainDBVO(notice, meetingSchedule);
		}
		return fvo;
	}
	//���� ����
	public boolean noticeUpdate(String nextNotice) throws SQLException {//ä�÷α� ������Ʈ
		String sql = "update maindata set notice = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, nextNotice);
		int rsl = pstmt.executeUpdate();
		if(rsl==0) {
			return false;
		}
		return true;
	} 
	//�������� ����
	public boolean meetingScheduleUpdate(String nextMeetingSchedule) throws SQLException {//ä�÷α� ������Ʈ
		String sql = "update maindata set meetingSchedule = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, nextMeetingSchedule);
		int rsl = pstmt.executeUpdate();
		if(rsl==0) {
			return false;
		}
		return true;
	} 
}
