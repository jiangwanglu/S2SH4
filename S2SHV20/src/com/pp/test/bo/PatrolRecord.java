package com.pp.test.bo;

import java.io.Serializable;

public class PatrolRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String unitid;
	private String type;
	private String type1;
	private String type2;
	private String type3;
	private String type4;
	private String type5;
	private String type6;
	private String type7;
	private String type8;
	private String type9;
	private String date;
	private int day;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public String getType4() {
		return type4;
	}
	public void setType4(String type4) {
		this.type4 = type4;
	}
	public String getType5() {
		return type5;
	}
	public void setType5(String type5) {
		this.type5 = type5;
	}
	public String getType6() {
		return type6;
	}
	public void setType6(String type6) {
		this.type6 = type6;
	}
	public String getType7() {
		return type7;
	}
	public void setType7(String type7) {
		this.type7 = type7;
	}
	public String getType8() {
		return type8;
	}
	public void setType8(String type8) {
		this.type8 = type8;
	}
	public String getType9() {
		return type9;
	}
	public void setType9(String type9) {
		this.type9 = type9;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}
