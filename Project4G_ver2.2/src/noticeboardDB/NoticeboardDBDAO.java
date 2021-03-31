package noticeboardDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import commonDBConn.DBConn;
import memberDB.MemberDBVO;
import memberDB.MemberDBVO;


public class NoticeboardDBDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public NoticeboardDBDAO() throws ClassNotFoundException, SQLException{
		con= new DBConn().getConnection();
	}
	//게시판전체불러오기
	public ArrayList<NoticeboardDBVO> noticeboardSelect() throws SQLException {
		ArrayList<NoticeboardDBVO> array = new ArrayList<NoticeboardDBVO>();
		String sql = "Select * from noticeboarddata";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			int type = rs.getInt("type");
			String writer = rs.getString("writer");
			String time = rs.getString("time");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String reply = rs.getString("reply");
			//String photo = rs.getString("photo");
			NoticeboardDBVO fvo = new NoticeboardDBVO(type,writer,time,title,contents,reply);//,photo
			array.add(fvo); 
		}
		return array;
	}
	//정모게시판 전체 가져오기
	public ArrayList<NoticeboardDBVO> board1Select(ArrayList<NoticeboardDBVO> array){
		ArrayList<NoticeboardDBVO> board1 = new ArrayList<NoticeboardDBVO>();
		for (NoticeboardDBVO board : array) {
			if(board.getType() == 1) {
				board1.add(board);
			}
		}
		return board1;
		
	}
	//번개게시판 전체 가져오기
	public ArrayList<NoticeboardDBVO> board2Select(ArrayList<NoticeboardDBVO> array){
		ArrayList<NoticeboardDBVO> board2 = new ArrayList<NoticeboardDBVO>();
		for (NoticeboardDBVO board : array) {
			if(board.getType() == 2) {
				board2.add(board);
			}
		}
		return board2;
		
	}
	//후기게시판 전체 가져오기
	public ArrayList<NoticeboardDBVO> board3Select(ArrayList<NoticeboardDBVO> array){
		ArrayList<NoticeboardDBVO> board3 = new ArrayList<NoticeboardDBVO>();
		for (NoticeboardDBVO board : array) {
			if(board.getType() == 3) {
				board3.add(board);
			}
		}
		return board3;
		
	}
	//게시판 하나객체 불러오기
	public NoticeboardDBVO boardClick(ArrayList<NoticeboardDBVO> array,int index) {
		NoticeboardDBVO board=array.get(index);
		return board;
	}
	//게시판 글쓰기
	public boolean postWrite(int type,String id,String title,String contents) throws SQLException {
		// TODO Auto-generated method stub
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMddHHmmss");
		Date date = new Date();
		String time = format1.format(date);
		String sql="insert into noticeboarddata values(?,?,?,?,?,?)";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, type);
		pstmt.setString(2, id);
		pstmt.setString(3, time);	
		pstmt.setString(4, title);
		pstmt.setString(5, contents); 
		pstmt.setString(6, "");
				
		int rsl = pstmt.executeUpdate();
		if(rsl==0) {
			System.out.println("가입실패");
			return false;
		}
		System.out.println("가입성공");
		return true;
	}
	//게시글 수정하기
	public boolean postContentsUpdate(String writer,String title,String time, String nextContents) throws SQLException {//비밀번호변경

		String sql = "update noticeboarddata set contents = ?,title = ? where writer = ?  and time = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, nextContents);
		pstmt.setString(2, title);
		pstmt.setString(3, writer);
		pstmt.setString(4, time);
		int rsl = pstmt.executeUpdate();
		if(rsl==0) {
			//System.out.println("변경실패");
			return false;
		}
		//System.out.println("변경성공");
		return true;
	}
	//게시글 삭제
	public boolean postDelete(String writer,String title,String time) throws SQLException {
		
		String sql = "delete from noticeboarddata where writer = ? and title = ? and time = ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, writer);
		pstmt.setString(2, title);
		pstmt.setString(3, time);
		int rsl=pstmt.executeUpdate();
		if(rsl==0) {
			System.out.println("삭제실패");
			return false;
		}
		System.out.println("삭제완료");
		return true;
	}
}

