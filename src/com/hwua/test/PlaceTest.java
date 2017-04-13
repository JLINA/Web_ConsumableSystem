package com.hwua.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hwua.dao.PlacesMapperDao;
import com.hwua.dao.TypesMapperDao;
import com.hwua.pojo.Places;
import com.hwua.pojo.Types;

public class PlaceTest {

	public static void main(String[] args) throws ParseException, UnknownHostException {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		Date date = sdf.parse( " 2016-12-7 19:20:00 " );
		SqlSessionTemplate template  = 
				ac.getBean("sqlSession", SqlSessionTemplate.class);
				PlacesMapperDao dao = template.getMapper(PlacesMapperDao.class);
				List<Places> list = dao.select();
				System.out.println(list);
			
				
				
	}
}
