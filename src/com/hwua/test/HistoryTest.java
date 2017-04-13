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

import com.hwua.dao.HistoryMapperDao;
import com.hwua.pojo.History;
import com.hwua.pojo.SelectAll;

public class HistoryTest {

	public static void main(String[] args) throws ParseException, UnknownHostException {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		Date date = sdf.parse( " 2016-12-7 19:20:00 " );
		SqlSessionTemplate template  = 
				ac.getBean("sqlSession", SqlSessionTemplate.class);
				HistoryMapperDao dao = template.getMapper(HistoryMapperDao.class);
//				long i = dao.selectcount(new SelectAll(5, 0, "1"));
//				System.out.println(i);
//				List<History> list = dao.select(new SelectAll(5,0,"1"));
//				System.out.println(list);
				for(int x=0 ;x<9;x++){
					dao.insert(new History(sdf.format(new Date()), 2, "ÐÞ¸Ä", "7"+x));
				}
				
				
	}
}
