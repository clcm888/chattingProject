package mainFrameDB;

import java.io.Serializable;

public class MainDBVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8358283287379064624L;
	private String notice;
	private String meetingSchedule;
	public MainDBVO(String notice, String meetingSchedule) {
		super();
		this.notice = notice;
		this.meetingSchedule = meetingSchedule;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getMeetingSchedule() {
		return meetingSchedule;
	}
	public void setMeetingSchedule(String meetingSchedule) {
		this.meetingSchedule = meetingSchedule;
	}
}
