package noticeboardDB;

import java.io.Serializable;

public class NoticeboardDBVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3229371902560796885L;
	private int type;
	private String writer;
	private String time;
	private String title;
	private String contents;
	private String reply;
	//String photo;
	
	
	public NoticeboardDBVO(int type,String writer,String time, String title, String contents, String reply) {
		super();
		this.type = type;
		this.writer = writer;
		this.time = time;
		this.title = title;
		this.contents = contents;
		this.reply = reply;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
}
