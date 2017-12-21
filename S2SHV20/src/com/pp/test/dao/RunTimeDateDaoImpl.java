package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.RunTimeDate;

public class RunTimeDateDaoImpl extends BaseDaoImpl<RunTimeDate,Integer> implements RunTimeDateDao {

	public List<RunTimeDate> runDate() throws Exception {
		String sql = "select * from runtime";
		return (List<RunTimeDate>) this.queryForList(sql, RunTimeDate.class);
	}

	

}