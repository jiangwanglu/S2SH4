package com.pp.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Test;

public class InspectionDaoImpl extends BaseDaoImpl<Inspection, Integer> implements InspectionDao{
	public int plan(String startdate) throws Exception {
		String sql = "select count(*) from inspection where d = ?";
		return (int) this.queryForInt(sql,new String[]{startdate});
	}

	public int planok(String end) throws Exception {
		String c = "1";
		String sql = "select count(*) from inspection where d = ? and c = ?";
		return (int) this.queryForInt(sql,new String[]{end,c});
	}


	public int planyear(String startyear, String endyear) throws Exception {
		String sql = "select count(*) from inspection where b > ? and b < ?";
		return (int) this.queryForInt(sql,new String[]{startyear,endyear});
	}

	public int planendyear(String startyear, String endyear) throws Exception {
		String sql = "select count(*) from inspection where b > ? and b < ?  and c = 1";
		return (int) this.queryForInt(sql,new String[]{startyear,endyear});
	}

	
	public int planweek(String date,String enddate) throws Exception {
		String sql = "select count(*) from inspection where b > ? and b < ?";
		return (int) this.queryForInt(sql,new String[]{enddate,date});
	}

	public int planend(String date,String enddate) throws Exception {
		String index = "1";
		String sql = "select count(*) from inspection where b > ? and b < ? and c = ?";
		return (int) this.queryForInt(sql,new String[]{enddate,date,index});
	}

	
	public int baojday(String preMonday, String format) throws Exception {
		String sql ="select count(*) from alarm where EventTime > ? and EventTime < ?";
		return this.queryForInt(sql, new String[]{preMonday,format});
	}

	public List<Alarmifo> baobiao() throws Exception {
		String sql = "select tagName,alarmvalue,limitvalue,eventtime,eventtype,alarmtext from alarm where EventTime order by eventTime desc LIMIT 5";
		return (List<Alarmifo>) this.queryForList(sql, Alarmifo.class);
	}

	public List<String> baozhx() throws Exception {
		String sql = "select TagName from alarm group by TagName";
		return (List<String>) this.queryForList(sql, String.class);
	}



	public List<Test> baoz(String start, String end,String s) throws Exception {
		String sql = "select count(*) from alarm where EventTime > ? and EventTime < ? and TagName = ?";
		
		return (List<Test>) this.queryForList(sql, new String[]{start,end});
	}
}