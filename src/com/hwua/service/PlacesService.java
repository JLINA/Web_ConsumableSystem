package com.hwua.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hwua.dao.PlacesMapperDao;
import com.hwua.dao.TypesMapperDao;
import com.hwua.pojo.Places;
import com.hwua.pojo.Types;

@Transactional
public class PlacesService {

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	public List<Places> find(){
		PlacesMapperDao dao = sqlSession.getMapper(PlacesMapperDao.class);
		List<Places> list = dao.select();
		return list;	
		}
	
}
