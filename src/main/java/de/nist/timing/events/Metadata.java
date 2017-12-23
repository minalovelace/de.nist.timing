package de.nist.timing.events;

import java.util.Date;

public class Metadata {

	private Date date;
	private String ip;
	
	public Metadata(Date date, String ip) {
		this.date = date;
		this.ip = ip;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
