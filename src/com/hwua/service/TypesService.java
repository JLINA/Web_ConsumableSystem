package com.hwua.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hwua.dao.TypesMapperDao;
import com.hwua.pojo.Types;

@Transactional
public class TypesService {

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	public String addtypes(Types types){
		TypesMapperDao dao = sqlSession.getMapper(TypesMapperDao.class);
		List<Types> list = dao.select(new Types());
		String x = "1";
		for(Types t:list){
			if(t.getTypeid().equals(types.getTypeid())){
				x="0";
				break;
			}
		}
		if(x.equals("1")){
			dao.insert(types);
		}
		return x;
	}
	
	public List<Types> find(Types types){
		TypesMapperDao dao = sqlSession.getMapper(TypesMapperDao.class);
		List<Types> list = dao.select(types);
		return list;	
		}
	
}
