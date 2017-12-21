package com.pp.test.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.AirSurveyelect;
import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.Electric;
import com.pp.test.bo.Nownh;
import com.pp.test.bo.Power;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.Surveyelect;
import com.pp.test.bo.Test;
import com.pp.test.bo.baoj;

public class LoadSurveyelectDaoImpl extends BaseDaoImpl<Electric, Integer> implements LoadSurveyelectDao{

	public ArrayList<Surveyelect> loadData(String date,String flag) throws Exception {
		// TODO Auto-generated method stub
		String strsql="from Surveyelect where date =? and machine=?";
		Object[] values=new String[]{date,flag};
		return (ArrayList<Surveyelect>) this.find(strsql, values);
	}

	/*public ArrayList<AirSurveyelect> loadAirData(String date) throws Exception {
		// TODO Auto-generated method stub
		String strsql="from AirSurveyelect where date =?";
		Object[] values=new String[]{date};
		return (ArrayList<AirSurveyelect>) this.find(strsql,values);
	}*/

	public List<String> loadmac(String req) throws Exception {
		// TODO Auto-generated method stub
		String strsql="select machine from electric where date =? group by machine";
		return (List<String>) this.queryForList(strsql, new String[]{req}, String.class);
	}

	public List<Dianlang> loadnh(String proMonth, String lastMonth) throws Exception {
		// TODO Auto-generated method stub
		String strsql="from Dianlang where date>=? and date<=?";
		return (List<Dianlang>) this.find(strsql,new String[]{proMonth,lastMonth});
	}
	

	public List<Nownh> loadnownh(String time) throws Exception {
		// TODO Auto-generated method stub
		String strsql="from Nownh where date=?";
		return this.find(strsql,new String[]{time});
	}

	public List<Dianlang> loadyearnh(String format, String format2)
			throws Exception {
		// TODO Auto-generated method stub
		String strsql="from Dianlang where DAY(date)='01' and date>=? and date<=? ";
		return (List<Dianlang>) this.find(strsql,new String[]{format,format2});
	}

	public List loadalarmdata(String req) throws Exception {
		// TODO Auto-generated method stub
		//String strsql="select TagName as tagName,AlarmValue as alarmValue,LimitValue as limitValue,EventTime as eventTime,EventType as eventType,AlarmText as alarmText from Alarm where EventTime >=?";
		//return (List<Alarmifo>) this.queryForList(strsql,new String[]{req},Alarmifo.class);
		
		String req2=req+" 15:59:59";
		
    	Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	ca.setTime(format.parse(req));
    	ca.set(Calendar.DATE, ca.get(Calendar.DATE)-1); 
    	String reqP=format.format(ca.getTime());
    	String req1=reqP+" 16:00:00";
		//System.out.println("utcTime::::::"+req1);
		String strsql="from Alarmifo where EventTime >? and EventTime<? order by EventTime asc";
		//String strsql="from Alarmifo where ?<from_unixtime(EventTime) and from_unixtime(EventTime)<?";
		return (List<Alarmifo>) this.find(strsql,new String[]{req1,req2});
	}

	public List<String> loadairmac(String req) throws Exception {
		// TODO Auto-generated method stub
		String strsql="select machine from airsureye where date =? group by machine";
		return (List<String>) this.queryForList(strsql, new String[]{req}, String.class);
	}

	public ArrayList<AirSurveyelect> loadAirData(String date, String flag)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("time="+date+"//"+flag);
		String strsql="from AirSurveyelect where date =?and machine=?";
		Object[] values=new String[]{date,flag};
		return (ArrayList<AirSurveyelect>) this.find(strsql,values);

	}

	public List<Power> loadPower(String format, String format2)
			throws Exception {
		// TODO Auto-generated method stub
		String strsql="from Power where date>=? and date<=?";
		return (List<Power>) this.find(strsql,new String[]{format,format2});
	}
}
