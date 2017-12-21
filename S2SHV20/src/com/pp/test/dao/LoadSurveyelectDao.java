package com.pp.test.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myapp.common.dao.IBaseDao;
import com.pp.test.bo.AirSurveyelect;
import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.Nownh;
import com.pp.test.bo.Power;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.Surveyelect;
import com.pp.test.bo.Test;
import com.pp.test.bo.baoj;

public interface LoadSurveyelectDao extends IBaseDao{
	 ArrayList<Surveyelect> loadData(String date, String flag)throws Exception;
	 ArrayList<AirSurveyelect> loadAirData(String date, String flag)throws Exception;
	List<String> loadmac(String req)throws Exception;
	List<Dianlang> loadnh(String proMonth, String lastMonth)throws Exception;
	List<Nownh> loadnownh(String time)throws Exception;
	List<Dianlang> loadyearnh(String format, String format2)throws Exception;
	List loadalarmdata(String req)throws Exception;
	List<String> loadairmac(String req)throws Exception;
	List<Power> loadPower(String format, String format2) throws Exception;
	
}
