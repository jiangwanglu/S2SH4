package com.pp.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pp.test.bo.AirSurveyelect;
import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.Nownh;
import com.pp.test.bo.Power;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.Surveyelect;
import com.pp.test.bo.Test;


public interface LoadSurveyelectService {
	
	ArrayList<Surveyelect> loadData(String date, String flag) throws Exception;
	ArrayList<AirSurveyelect> loadAirData(String date, String flag) throws Exception;
	List<String> loadmacData(String req) throws Exception;
	List<Dianlang> loadnh(String proMonth, String lastMonth)throws Exception;
	List<Nownh> loadnowng(String time)throws Exception;
	List<Dianlang> loadyearnh(String format, String format2)throws Exception;
	List loadAlarmData(String req)throws Exception;
	List<String> loadairmacData(String req)throws Exception;
	List<Power> loadpower(String format, String format2)throws Exception;
}
