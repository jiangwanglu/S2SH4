package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.User;
import com.pp.test.bo.baoj;

public class baojDaoImpl extends BaseDaoImpl<User,Integer> implements baojDao {

	public List<com.pp.test.bo.baoj> baojday(String date, String date1)
			throws Exception {
		
		String c = "df";
		String sql = "select * from baoj where eventTime > ?  and eventTime < date1 ";
		return null;
	}

	public List<com.pp.test.bo.baoj> newbaoj() throws Exception {
		String sql = "select * from baoj where id  order by eventTime desc LIMIT 5";
		return (List<com.pp.test.bo.baoj>)this.queryForList(sql, baoj.class);
	}

	public List<com.pp.test.bo.baoj> pbaoj(String date) throws Exception {
		String a = date+" 00:00:00";
		String b = date+" 23:59:59";
		String c = "df";
		String sql = "select * from baoj where EventTime > ? and EventTime < ? and location = ?";
		return (List<com.pp.test.bo.baoj>)this.queryForList(sql, new String[]{a,b,c},baoj.class);
	}
}