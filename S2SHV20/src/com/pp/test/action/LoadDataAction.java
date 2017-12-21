package com.pp.test.action;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.sql.Insert;

import sun.font.EAttribute;
import net.sf.json.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.pp.test.bo.AirElectric;
import com.pp.test.bo.AirSurveyelect;
import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.Electric;
import com.pp.test.bo.Fuzai;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Nownh;
import com.pp.test.bo.PatrolRecord;
import com.pp.test.bo.Power;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.Surveyelect;
import com.pp.test.bo.Test;
import com.pp.test.bo.xue;
import com.pp.test.service.LoadDataService;
import com.pp.test.service.LoadFuzaiService;
import com.pp.test.service.LoadSurveyelectService;

public class LoadDataAction implements ServletRequestAware{
	
	private LoadDataService loadDataService;
	private LoadFuzaiService loadFuzaiService;
	private LoadSurveyelectService loadSurveyelectService;
	public String result;//ajax返回参数
	//private Electric electric;
	private HttpServletRequest request;
	
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	} 
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String open()throws Exception{
        return "success";  
    }
	public String loadmac()throws Exception{
		String req=request.getParameter("time");
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date tempdate=format.parse(req);
    	String date1=format.format(new Date(tempdate.getTime()-30*1000));
    	String date2=format.format(new Date(tempdate.getTime()+30*1000));
    	List<String> ls=this.loadDataService.loadmacData(date1, date2);
    	result = JSONArray.fromObject(ls).toString();
		return "load_success";
	}
    public String load()throws Exception{
    	String req=request.getParameter("time");
    	String flag=request.getParameter("flag");
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date tempdate=format.parse(req);
    	String date1=format.format(new Date(tempdate.getTime()-30*1000));
    	String date2=format.format(new Date(tempdate.getTime()+30*1000));
    	ArrayList<Electric> ls=new ArrayList<Electric>();
    	ls=this.loadDataService.loadData(date1, date2,flag);
    	result =JSONArray.fromObject(ls).toString();
        return "load_success";  
    }
    public String loadAir()throws Exception{
    	String req=request.getParameter("time");
    	String index=request.getParameter("flag");
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date tempdate=format.parse(req);
    	String date1=format.format(new Date(tempdate.getTime()-30*1000));
    	String date2=format.format(new Date(tempdate.getTime()+30*1000));
    	List ls=new ArrayList<AirElectric>();
    	ls=this.loadDataService.loadAirData(date1, date2,index);
    	result =JSONArray.fromObject(ls).toString();
    	
        return "load_success";  
    }
    
    public String fuzai()throws Exception{
    	Fuzai e=new Fuzai();
    	e=this.loadFuzaiService.loadData();
    	result = "{\"x\":\"" + e.getDianliu()+"\"}";
		return "fuzai_success";
    	
    }
    public String surveymac()throws Exception{
		String req=request.getParameter("time");
    	List<String> ls=this.loadSurveyelectService.loadmacData(req);
    	result = JSONArray.fromObject(ls).toString();
		return "survey_success";
	}
    public String surveyairmac()throws Exception{
		String req=request.getParameter("time");
    	List<String> ls=this.loadSurveyelectService.loadairmacData(req);
    	result = JSONArray.fromObject(ls).toString();
		return "survey_success";
	}
    public String surveynh()throws Exception{
    	String timeindex=request.getParameter("timeindex");
    	Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
    	ca.setTime(new Date()); //设置时间为当前时间 
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	Date lastMonth=new Date();
    	Date proMonth=new Date();
    	ca.add(Calendar.MONTH, Integer.parseInt(timeindex)); 
    	lastMonth = ca.getTime(); //结果
    	ca.add(Calendar.MONTH, Integer.parseInt(timeindex)-1);
    	proMonth = ca.getTime(); 
    	List<Dianlang> ls=this.loadSurveyelectService.loadnh(format.format(proMonth),format.format(lastMonth));
		for (int i = 0; i < ls.size()-1 ; i++) {
			Dianlang dl=ls.get(i);
			float allnh=ls.get(i+1).getNh()-dl.getNh();
			float airnh=ls.get(i+1).getNhair()-dl.getNhair();
			dl.setNh(allnh-airnh);
			dl.setNhair(airnh);
			ls.set(i, dl);
		}
		ls.remove(ls.size()-1);
		result = JSONArray.fromObject(ls).toString();
		return "survey_success";
    	
    }
    public String yearnh()throws Exception{
    	String timeindex=request.getParameter("timeindex");
    	Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
    	ca.setTime(new Date()); //设置时间为当前时间 
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	Date last=new Date();
    	Date pro=new Date();
    	ca.add(Calendar.YEAR, Integer.parseInt(timeindex)); //年份减1 
    	last = ca.getTime(); //结果
    	ca.add(Calendar.YEAR, Integer.parseInt(timeindex)-1);
    	pro = ca.getTime(); 
    	List<Dianlang> ls=this.loadSurveyelectService.loadyearnh(format.format(pro),format.format(last));
		List<Dianlang> rls=new ArrayList<Dianlang>();
		if(ls.size()>=1){
		for (int i = 1; i < ls.size() ; i++) {
			Dianlang dl=new Dianlang();
			float allnh=ls.get(i).getNh()-ls.get(i-1).getNh();
			float airnh=ls.get(i).getNhair()-ls.get(i-1).getNhair();
			dl.setNh(allnh-airnh);
			dl.setNhair(airnh);
			dl.setDate(ls.get(i).getDate());
			rls.add(dl);
		}}
		result = JSONArray.fromObject(rls).toString();
		return "survey_success";
    	
    }
    public String datenh()throws Exception{
    	String time=request.getParameter("time");
    	List<Nownh> ls= this.loadSurveyelectService.loadnowng(time); 		
		List<Nownh> rls=new ArrayList<Nownh>();
		if(ls.size()>0){
		for (int i = 1; i < ls.size() ; i++) {
			Nownh nh=new Nownh();
			float allnh=ls.get(i).getNh()-ls.get(i-1).getNh();
			float airnh=ls.get(i).getNhair()-ls.get(i-1).getNhair();
			nh.setNh(allnh-airnh);
			nh.setNhair(airnh);
			nh.setDate(ls.get(i).getDate());
			nh.setID(i);
			nh.setTime(ls.get(i).getTime());
			rls.add(nh);
		};};
		result = JSONArray.fromObject(rls).toString();
		return "survey_success";
    	
    }
    public String survey()throws Exception{ 
    	String req=request.getParameter("time");
    	String flag=request.getParameter("flag");
    	List ls= new ArrayList<Surveyelect>();
    	ls=this.loadSurveyelectService.loadData(req,flag);
    	result =JSONArray.fromObject(ls).toString();
		return "survey_success";
    	
    }
    
    public String airsurvey()throws Exception{ 
    	String req=request.getParameter("time");
    	String flag=request.getParameter("flag");
    	List ls= new ArrayList<AirSurveyelect>();
    	ls=this.loadSurveyelectService.loadAirData(req,flag);
    	result =JSONArray.fromObject(ls).toString();
		return "survey_success";
    	
    }
    public String alarminfo()throws Exception{ 
    	String req=request.getParameter("time");
    	List<Alarmifo> ls= new ArrayList<Alarmifo>();
    	//List<Alarmifo> lsp= new ArrayList<Alarmifo>();
    	ls=this.loadSurveyelectService.loadAlarmData(req);
    	/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date=null;
    	Calendar ca=Calendar.getInstance();
    	for(int i=0;i<ls.size();i++){
    		Alarmifo a=ls.get(i);
    		date = sdf.parse(a.getEventTime());
    		ca.setTime(date);
    		ca.add(Calendar.HOUR_OF_DAY, 8);
    		a.setEventTime(sdf.format(ca.getTime()));
    		lsp.add(a);
    	}*/
    	result =JSONArray.fromObject(ls).toString();
		return "survey_success"; 
    	
    }
    
    public String realnh()throws Exception{
    	Date d=new Date();
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	List<Nownh> ls= this.loadSurveyelectService.loadnowng(format.format(d)); 		
		float znh = 0,jnh = 0;
		if(ls.size()>0){
			znh=ls.get(ls.size()-1).getNh();
			jnh=ls.get(ls.size()-1).getNh()-ls.get(0).getNh();
		};
		result = "{\"x\":\"" + Math.round(znh)+"\",\"y\":\""+Math.round(jnh)+"\"}";
		return "survey_success";
    	
    }
    
    public String power()throws Exception{
    	String timeindex=request.getParameter("timeindex");
    	Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
    	ca.setTime(new Date()); //设置时间为当前时间 
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	Date lastMonth=new Date();
    	Date proMonth=new Date();
    	ca.add(Calendar.MONTH, Integer.parseInt(timeindex)); 
    	lastMonth = ca.getTime(); //结果
    	ca.add(Calendar.MONTH, Integer.parseInt(timeindex)-1);
    	proMonth = ca.getTime(); 
    	List<Power> ls=this.loadSurveyelectService.loadpower(format.format(proMonth),format.format(lastMonth));
		/*for (int i = 0; i < ls.size()-1 ; i++) {
			Dianlang dl=ls.get(i);
			float allnh=ls.get(i+1).getNh()-dl.getNh();
			float airnh=ls.get(i+1).getNhair()-dl.getNhair();
			dl.setNh(allnh-airnh);
			dl.setNhair(airnh);
			ls.set(i, dl);
		}
		ls.remove(ls.size()-1);*/
		result = JSONArray.fromObject(ls).toString();
		return "survey_success";
    	
    }
    


	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
		
	}

	public void setLoadFuzaiService(LoadFuzaiService loadFuzaiService) {
		this.loadFuzaiService = loadFuzaiService;
	}

	public void setLoadSurveyelectService(
		LoadSurveyelectService loadSurveyelectService) {
		this.loadSurveyelectService = loadSurveyelectService;
	}
	
    //运行时间
	public String runDate() throws Exception{
		String name = request.getParameter("id");
		List<RunTimeDate> list = this.loadFuzaiService.runDate();	//其他
		List<RunTimeDate> byq = new ArrayList<RunTimeDate>();		//变压器
		List<RunTimeDate> kt = new ArrayList<RunTimeDate>();		//空调
		Date da = new Date();
		Long lo;
		Long lo1;
		Long lo2;
		for(RunTimeDate l : list){
			if(l.getDianliu() == 1){
				lo = (long) l.getTimep();
				lo1 = (long) l.getCounttime();
				Long ti = da.getTime() / 1000;
				ti = (ti - lo) + lo1;
				l.setTimen(Integer.parseInt(String.valueOf((ti /  60) / 60)));
			}else{
					l.setTimen((l.getCounttime() /  60) / 60);
				
			}
			if(l.getLocalid().equals("DF-QD-01-1")||l.getLocalid().equals("DF-QD-01-2")||l.getLocalid().equals("DF-QD-01-3")||l.getLocalid().equals("DF-QD-01-4")||l.getLocalid().equals("DF-QD-01-5")){
				l.setTimen(l.getTimen() / 24);
				byq.add(l);
			}else if(l.getLocalid().equals("DF-QD-02-001")||l.getLocalid().equals("DF-KT-40-001")||l.getLocalid().equals("DF-KT-40-002")||l.getLocalid().equals("DF-KT-40-003")||l.getLocalid().equals("DF-KT-41-007")||l.getLocalid().equals("DF-KT-41-008")||l.getLocalid().equals("DF-KT-41-009")||l.getLocalid().equals("DF-KT-41-010")||l.getLocalid().equals("DF-KT-41-011")||l.getLocalid().equals("DF-KT-42-004")||l.getLocalid().equals("DF-KT-42-005")||l.getLocalid().equals("DF-KT-42-006")||l.getLocalid().equals("DF-KT-43-055")||l.getLocalid().equals("DF-KT-43-056")||l.getLocalid().equals("DF-KT-43-057")){
				kt.add(l);
			}
		}
		list.removeAll(byq);
		list.removeAll(kt);
		if(name.equals("0")){
			result = JSONArray.fromObject(list).toString();
		}else if(name.equals("1")){
			result = JSONArray.fromObject(byq).toString();
		}else if(name.equals("2")){
			result = JSONArray.fromObject(kt).toString();
		}
		return "fuzai_success";
	}
	//巡查完成率
	public String plan() throws Exception{
		List<RunTimeDate> li = new ArrayList<RunTimeDate>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<7;i++){
        	 RunTimeDate time = new RunTimeDate();
        	 Calendar c = Calendar.getInstance();  
        	 c.add(Calendar.DATE, -i);  
             Date monday = c.getTime();
             String startdate = sdf.format(monday);
             int start = this.loadFuzaiService.plan(startdate);
             int ok = this.loadFuzaiService.planok(startdate);
             double sta = start;
             double o = ok;
             sta = o / sta * 100;
             time.setId((int)sta);
             time.setLocal(startdate);
             li.add(time);
        }
        result = JSONArray.fromObject(li).toString();
		return "fuzai_success";
	}
	//年度巡查完成率
	public String planyear() throws Exception{
		Date dateyear = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, Integer.valueOf((f.format(dateyear)).split("-")[0]).intValue());
		Date currYearFirst = calendar.getTime();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.clear();
		calendar1.set(Calendar.YEAR, Integer.valueOf((f.format(dateyear)).split("-")[0]).intValue());
		calendar1.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast1 = calendar1.getTime();
		int startyear = loadFuzaiService.planyear(f.format(currYearFirst),f.format(currYearLast1));
		int endyear = loadFuzaiService.planendyear(f.format(currYearFirst),f.format(currYearLast1));
        double sta = startyear;
        double ta = startyear;
        double o = endyear;
        sta = o / sta * 100;
        o = (ta - o) / ta * 100;
        RunTimeDate time = new RunTimeDate();
        time.setId((int)sta);
        time.setLocal("巡检率");
        time.setTimen((int)o);
        time.setLocalid("未巡检");
        result = JSONArray.fromObject(time).toString();
		return "fuzai_success";
	}
	//月
	public String planweek() throws Exception{
		String date = request.getParameter("date")+"-01";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = df.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();
		int start = this.loadFuzaiService.planweek(df.format(lastDayOfMonth),date);
		int end = this.loadFuzaiService.planend(df.format(lastDayOfMonth),date);
        double sta = start;
        double en = end;
        double o = start;
        sta = en / sta * 100;	//已经巡检
        o = (o - en) / o * 100;
        RunTimeDate time = new RunTimeDate();
        time.setId((int)sta);
        time.setLocal("巡检率?"+date.split("-")[0]+"-"+date.split("-")[1]);
        time.setTimen((int)o);
        time.setLocalid("未巡检");
        result = JSONArray.fromObject(time).toString();
		return "fuzai_success";
	}
	
	//一天能耗
	public String nh() throws Exception{
    	String time=request.getParameter("time");
    	List<Nownh> ls= this.loadSurveyelectService.loadnowng(time);
        result = JSONArray.fromObject(ls).toString();
    	return "survey_success";
	}
	//一月能耗
	public String nh1() throws Exception{
		String timeindex=request.getParameter("timeindex");
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		ca.setTime(new Date()); //设置时间为当前时间 
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date lastMonth=new Date();
		Date proMonth=new Date();
		ca.add(Calendar.MONTH, Integer.parseInt(timeindex)); 
		lastMonth = ca.getTime(); //结果
		ca.add(Calendar.MONTH, Integer.parseInt(timeindex)-1);
		proMonth = ca.getTime(); 
		List<Dianlang> ls=this.loadSurveyelectService.loadnh(format.format(proMonth),format.format(lastMonth));	
		result = JSONArray.fromObject(ls).toString();
		return "survey_success";
	}
	//一年能耗
	public String nh2() throws Exception{
    	String timeindex=request.getParameter("timeindex");
    	Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
    	ca.setTime(new Date()); //设置时间为当前时间 
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	Date last=new Date();
    	Date pro=new Date();
    	ca.add(Calendar.YEAR, Integer.parseInt(timeindex)); //年份减1 
    	last = ca.getTime(); //结果
    	ca.add(Calendar.YEAR, Integer.parseInt(timeindex)-1);
    	pro = ca.getTime(); 
    	List<Dianlang> ls=this.loadSurveyelectService.loadyearnh(format.format(pro),format.format(last));
		result = JSONArray.fromObject(ls).toString();
		return "survey_success";
	}
	//报警
	public String baoj() throws Exception{
		String date = request.getParameter("date");
		Date da = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");   
		if(date.equals("0")){
			Calendar c = Calendar.getInstance();  
			c.add(Calendar.DATE, - 7);  
			Date monday = c.getTime();
			String preMonday = sdf.format(monday);
			int index = this.loadFuzaiService.baojday(preMonday, sdf.format(da));
			result = JSONArray.fromObject(index).toString();
		}else if(date.equals("1")){
		      //获取前月的第一天
		      Calendar   cal_1=Calendar.getInstance();//获取当前日期 
		      cal_1.add(Calendar.MONTH, -1);
		      cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		      String firstDay = sdf.format(cal_1.getTime());
		      //获取前月的最后一天
		      Calendar cale = Calendar.getInstance();   
		      cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
		      String lastDay = sdf.format(cale.getTime());
		      int index = this.loadFuzaiService.baojday(firstDay, lastDay);
		      result = JSONArray.fromObject(index).toString();
		}else if(date.equals("2")){
			Calendar c = Calendar.getInstance(); 
	        Date now = null; 
	        c.set(Calendar.MONTH, 0); 
	        c.set(Calendar.DATE, 1); 
	        now = sdf.parse(sdf.format(c.getTime())); 
	        Calendar d = Calendar.getInstance(); 
	        Date now1 = null; 
	        c.set(Calendar.MONTH, 11); 
	        c.set(Calendar.DATE, 31); 
	        now1 = sdf.parse(sdf.format(c.getTime()) + " 23:59:59"); 
		    int index = this.loadFuzaiService.baojday(sdf.format(now), sdf.format(now1));
		    result = JSONArray.fromObject(index).toString();
		}
		return "fuzai_success";
		
	}
	
	//表格
	public String baobiao() throws Exception{
		List<Alarmifo> list = this.loadFuzaiService.baobiao();
		result = JSONArray.fromObject(list).toString();
		return "fuzai_success";
	}
	
	//折线
	public String baozhe() throws Exception{
		List<String> list = this.loadFuzaiService.baozhx();
		List<Test> test = new ArrayList<Test>();
		String date = request.getParameter("date");
		Date da = new Date();
		int index = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");   
		if(date.equals("0")){
			Calendar c = Calendar.getInstance();  
			c.add(Calendar.DATE, - 7);  
			Date monday = c.getTime();
			String preMonday = sdf.format(monday);
			for(String s : list){
				System.out.println(s);
				test = this.loadFuzaiService.baoz(preMonday, sdf.format(da),s);
			}
			System.out.println(index);
			result = JSONArray.fromObject(index).toString();
		}/*else if(date.equals("1")){
		      //获取前月的第一天
		      Calendar   cal_1=Calendar.getInstance();//获取当前日期 
		      cal_1.add(Calendar.MONTH, -1);
		      cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		      String firstDay = sdf.format(cal_1.getTime());
		      //获取前月的最后一天
		      Calendar cale = Calendar.getInstance();   
		      cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
		      String lastDay = sdf.format(cale.getTime());
		      for(String s : list){
					index = this.loadFuzaiService.baoz(firstDay, lastDay,s);
				}
		      System.out.println(index);
		      result = JSONArray.fromObject(index).toString();
		}else if(date.equals("2")){
			Calendar c = Calendar.getInstance(); 
	        Date now = null; 
	        c.set(Calendar.MONTH, 0); 
	        c.set(Calendar.DATE, 1); 
	        now = sdf.parse(sdf.format(c.getTime())); 
	        Calendar d = Calendar.getInstance(); 
	        Date now1 = null; 
	        c.set(Calendar.MONTH, 11); 
	        c.set(Calendar.DATE, 31); 
	        now1 = sdf.parse(sdf.format(c.getTime()) + " 23:59:59"); 
	        for(String s : list){
				index = this.loadFuzaiService.baoz(sdf.format(now),  sdf.format(now1),s);
			}
	        System.out.println(index);
		    result = JSONArray.fromObject(index).toString();
		}*/
		return "fuzai_success";
	}
	public String baox() throws Exception{
		List<String> list = this.loadFuzaiService.baozhx();
		result = JSONArray.fromObject(list).toString();
		return "fuzai_success";
	}
}