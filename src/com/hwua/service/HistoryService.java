package com.hwua.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hwua.dao.HistoryMapperDao;
import com.hwua.dao.UsersMapperDao;
import com.hwua.pojo.History;
import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Users;

@Transactional
public class HistoryService {

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//зЂВс
	public void addUser(Users user){
		HistoryMapperDao dao = sqlSession.getMapper(HistoryMapperDao.class);
	}
	
	public long selectcount(SelectAll selectAll){
		HistoryMapperDao dao = sqlSession.getMapper(HistoryMapperDao.class);
		return dao.selectcount(selectAll);
	}
	
	public List<History> select(SelectAll selectAll){
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		UsersMapperDao dao2 = sqlSession.getMapper(UsersMapperDao.class);
	    List<History> list = dao1.select(selectAll);
	    ArrayList<History> historys = new ArrayList<>();
	    for(History h:list){
	    	Users users = dao2.selectbyid(h.getUserId());
	    	h.setUser(users);
	    	historys.add(h);
	    }
		return historys;
	}
}
