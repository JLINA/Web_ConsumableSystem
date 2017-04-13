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

import com.hwua.dao.TypesMapperDao;
import com.hwua.pojo.Types;

public class TypesTest {

	public static void main(String[] args) throws ParseException, UnknownHostException {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		Date date = sdf.parse( " 2016-12-7 19:20:00 " );
		SqlSessionTemplate template  = 
				ac.getBean("sqlSession", SqlSessionTemplate.class);
				TypesMapperDao dao = template.getMapper(TypesMapperDao.class);
//				List<Types> list = dao.select(new Types());
//				System.out.println(list);
				//dao.insert(new Types("05010600", "投影机"));
				List<Types> list = dao.select(new Types(null,"投影幕布"));
				System.out.println(list.size());
				System.out.println(list);
			
				
				
	}
}
