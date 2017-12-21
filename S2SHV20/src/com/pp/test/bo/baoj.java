package com.pp.test.bo;

import java.io.Serializable;

public class baoj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getAlarmvalue() {
		return alarmvalue;
	}
	public void setAlarmvalue(String alarmvalue) {
		this.alarmvalue = alarmvalue;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public String getEventtime() {
		return eventtime;
	}
	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}
	public String getAlarmtext() {
		return alarmtext;
	}
	public void setAlarmtext(String alarmtext) {
		this.alarmtext = alarmtext;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int id;
	private String tagname;
	private String alarmvalue;
	private String eventtype;
	private String eventtime;
	private String alarmtext;
	private String state;
	private String location;
	

}
