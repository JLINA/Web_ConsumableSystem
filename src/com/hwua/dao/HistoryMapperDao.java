package com.hwua.dao;

import com.hwua.pojo.History;
import com.hwua.pojo.SelectAll;

import java.util.List;

public interface HistoryMapperDao {
    
	void insert(History history);
	
	long selectcount(SelectAll selectAll);
	
	List<History> select(SelectAll selectAll);
}