package com.pp.test.service;


import java.util.List;
import java.util.Map;

import com.myapp.common.service.BaseServiceImpl;
import com.pp.test.bo.Alarmifo;
import com.pp.test.bo.Fuzai;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.PatrolRecord;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.Test;
import com.pp.test.dao.InspectionDao;
import com.pp.test.dao.LoadFuzaiDao;
import com.pp.test.dao.PartolRecordDao;
import com.pp.test.dao.RunTimeDateDao;

public class LoadFuzaiServiceImpl extends BaseServiceImpl implements LoadFuzaiService{
	
	
	private LoadFuzaiDao loadFuzaiDao;                
	
	private RunTimeDateDao runtimedatedao;
	
	private PartolRecordDao partolrecorddao;
	
	private InspectionDao inspectiondao;

	public void setInspectiondao(InspectionDao inspectiondao) {
		this.inspectiondao = inspectiondao;
	}
	
	public void setPartolrecorddao(PartolRecordDao partolrecorddao) {
		this.partolrecorddao = partolrecorddao;
	}
	
	public void setLoadFuzaiDao(LoadFuzaiDao loadFuzaiDao) {
		this.loadFuzaiDao = loadFuzaiDao;
	}

	public void setRuntimedatedao(RunTimeDateDao runtimedatedao) {
		this.runtimedatedao = runtimedatedao;
	}
	
	public Fuzai loadData() throws Exception {
		// TODO Auto-generated method stub 

		return  this.loadFuzaiDao.loadData();
		
	}

	public List<RunTimeDate> runDate() throws Exception {
		return this.runtimedatedao.runDate();
	}

	
	public int plan(String startdate) throws Exception{
		return this.inspectiondao.plan(startdate);
	}

	public int planok(String end) throws Exception{
		return this.inspectiondao.planok(end);
	}

	public int planyear(String startyear, String endyear) throws Exception {
		return this.inspectiondao.planyear(startyear,endyear);
	}

	public int planendyear(String startyear, String endyear) throws Exception {
		return this.inspectiondao.planendyear(startyear,endyear);
	}

	public int planweek(String date,String enddate) throws Exception {
		return this.inspectiondao.planweek(date,enddate);
	}

	public int planend(String date,String enddate) throws Exception {
		return this.inspectiondao.planend(date,enddate);
	}

	
	public int baojday(String preMonday, String format) throws Exception {
		return this.inspectiondao.baojday(preMonday,format);
	}

	public List<Alarmifo> baobiao() throws Exception {
		return this.inspectiondao.baobiao();
	}

	public List<String> baozhx() throws Exception {
		return this.inspectiondao.baozhx();
	}

	public List<Test> baoz(String start, String end,String s) throws Exception {
		return this.inspectiondao.baoz(start,end,s);
	}

}
