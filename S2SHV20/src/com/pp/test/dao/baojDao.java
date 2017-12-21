package com.pp.test.dao;

import java.util.List;

public interface baojDao {
	List<com.pp.test.bo.baoj> baojday(String date,String date1) throws Exception;
	List<com.pp.test.bo.baoj> newbaoj() throws Exception;
	List<com.pp.test.bo.baoj> pbaoj(String date)throws Exception;
}
