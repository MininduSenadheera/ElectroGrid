package org.relishnepal.relisnp.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class State implements Serializable {

	private static final long serialVersionUID = 7717892776162949153L;
	private int noticeId;
	private String nmessage;
	private String duration;
	private String aparty;
	private String heading;
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNmessage() {
		return nmessage;
	}
	public void setNmessage(String nmessage) {
		this.nmessage = nmessage;
	}
	
	public String getDuration(){
		return duration;
	}
	
	public void setDuration(String duration){
		this.duration=duration;
	}
	
	public String getAparty(){
		return aparty;
	}
	
	public void setAparty(String aparty){
		this.aparty=aparty;
	}
	
	public String getHeading(){
		return heading;
	}
	
	public void setHeading(String heading){
		this.heading=heading;
	}
	
	
	
	
	

}
