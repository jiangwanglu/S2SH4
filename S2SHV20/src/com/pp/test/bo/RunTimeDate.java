package com.pp.test.bo;

import java.io.Serializable;

public class RunTimeDate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String localid;
	private String local;
	private int dianliu;
	private int timep;
	private int timen;
	private int counttime;
	
	
	public int getCounttime() {
		return counttime;
	}
	public void setCounttime(int counttime) {
		this.counttime = counttime;
	}
	public int getTimen() {
		return timen;
	}
	public void setTimen(int timen) {
		this.timen = timen;
	}
	public int getTimep() {
		return timep;
	}
	public void setTimep(int timep) {
		this.timep = timep;
	}
	public int getDianliu() {
		return dianliu;
	}
	public void setDianliu(int dianliu) {
		this.dianliu = dianliu;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocalid() {
		return localid;
	}
	public void setLocalid(String localid) {
		this.localid = localid;
	}
}
