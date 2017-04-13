package com.hwua.test;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hwua.dao.MaterialMapperDao;
import com.hwua.pojo.Inputcount;
import com.hwua.pojo.Material;
import com.hwua.pojo.SelectAll;

public class Materialtest {

	@SuppressWarnings("null")
	public static void main(String[] args) throws ParseException, UnknownHostException {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
		Date date = sdf.parse( " 2016-12-7 19:20:00 " );
		SqlSessionTemplate template  = 
				ac.getBean("sqlSession", SqlSessionTemplate.class);
		MaterialMapperDao dao = template.getMapper(MaterialMapperDao.class);
		Material material = new Material("84000800", "3000000", "灵敏电流计", "SG-1", "*", 335.0, "华东师大二附中工厂", "1984.01", "1984.01", "1984-01-01", "414", "报废", 0, 0, "", "208+3");
		dao.insert(material);
	}
}
