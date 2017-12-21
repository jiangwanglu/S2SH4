package com.pp.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myapp.common.service.BaseServiceImpl;
import com.pp.test.bo.AirElectric;
import com.pp.test.bo.Electric;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.baoj;
import com.pp.test.dao.LoadDataDao;
import com.pp.test.dao.baojDao;

public class LoadDataServiceImpl extends BaseServiceImpl implements LoadDataService{
	
	
	private LoadDataDao loadDataDao;                
	
	private baojDao baojdao;

	
	public baojDao getBaojdao() {
		return baojdao;
	}
	public void setBaojdao(baojDao baojdao) {
		this.baojdao = baojdao;
	}
	public void setLoadDataDao(LoadDataDao loadDataDao) {
		this.loadDataDao = loadDataDao;
	}
	
	public List<String> loadmacData(String pro, String nex) throws Exception{
		// TODO Auto-generated method stub
		return this.loadDataDao.loadmacData(pro, nex);
	}

	public ArrayList<Electric> loadData(String pro, String nex, String flag) throws Exception{
		// TODO Auto-generated method stub
		return this.loadDataDao.loadData(pro, nex,flag);
	}

	public ArrayList<AirElectric> loadAirData(String pro, String nex,
			String index) throws Exception {
		// TODO Auto-generated method stub
		return this.loadDataDao.loadAirData(pro, nex,index);
	}

	public List loadPower() throws Exception {
		// TODO Auto-generated method stub
		return this.loadDataDao.loadpower();
	}

	public List<RunTimeDate> runDate() throws Exception {
		return this.loadDataDao.runDate();
	}
	

	public List<baoj> baojday(String format, String day) throws Exception {
		return this.baojdao.baojday(format, day);
	}







	
	

}
