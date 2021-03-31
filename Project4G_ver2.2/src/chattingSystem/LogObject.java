package chattingSystem;

public class LogObject {
	int who;
	String id;
	int log;
	String message;
	public LogObject(int who, String id, int log, String message) {
		super();
		this.who = who;
		this.id = id;
		this.log = log;
		this.message = message;
	}
	public int getWho() {
		return who;
	}
	public void setWho(int who) {
		this.who = who;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLog() {
		return log;
	}
	public void setLog(int log) {
		this.log = log;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
