package com.pp.test.dao;

import java.util.List;
import java.util.Map;

import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Test;

public interface InspectionDao {
	int plan(String startdate) throws Exception;
	
	int planok(String end) throws Exception;
	
	int planyear(String startyear,String endyear) throws Exception;
	int planendyear(String startyear,String endyear) throws Exception;
	
	int planweek(String date,String enddate)throws Exception;
	int planend(String date,String enddate)throws Exception;

	
	int baojday(String preMonday, String format) throws Exception;
	
	List<Alarmifo> baobiao() throws Exception;
	
	List<String> baozhx() throws Exception;


	List<Test> baoz(String start, String end,String s) throws Exception;
}
