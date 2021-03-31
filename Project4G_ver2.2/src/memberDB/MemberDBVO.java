package memberDB;

import java.io.Serializable;

public class MemberDBVO implements Serializable{

	/*
	 memberDB.
		MemberDBDAO{
			������()
			//ȸ��DB��ü�������� 	memberSelect() 
			//ȸ������		signUp(String id,String name,String pw,int admin,String chatlog)
			//��й�ȣã����	idNameFind(ArrayList<MemberDBVO> array,String id,String name) //(���� boolean)
			//��ü��й�ȣ����	ObjectPwUpdate(MemberDBVO member,String nextPw)
			//��й�ȣ����		pwUpdate(String id,String nextPw)
			//ä�÷α׺���		chatlogUpdate(MemberDBVO member,String nextChatlog)
			//ȸ������		memberDelete(MemberDBVO member)
		}
		memberDBVO.{
			������(id,name,pw,admin,chatlog)
			getset()....
		}
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3335933641328161080L;
	private String id;
	private String name;
	private String pw;
	private String phone;
	private int gender;
	private int admin;
	String chatlog;
	
	public MemberDBVO() {}

	public MemberDBVO(String id, String name, String pw, String phone, int gender, int admin, String chatlog) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.gender = gender;
		this.admin = admin;
		this.chatlog = chatlog;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getAdmin() {
		return admin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getChatlog() {
		return chatlog;
	}
	public void setChatlog(String chatlog) {
		this.chatlog = chatlog;
	}
	
	
	
	
}
