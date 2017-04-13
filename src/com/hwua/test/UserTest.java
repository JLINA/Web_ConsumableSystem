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

import com.hwua.dao.UsersMapperDao;
import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Users;

public class UserTest {

	public static void main(String[] args) throws ParseException, UnknownHostException {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		Date date = sdf.parse( " 2016-12-7 19:20:00 " );
		SqlSessionTemplate template  = 
				ac.getBean("sqlSession", SqlSessionTemplate.class);
				UsersMapperDao dao = template.getMapper(UsersMapperDao.class);
				//for(int x=0;x<9;x++){
				//	  dao.insert(new Users("www"+x, "123456", "����Ա", "1237877495"+x, sdf.format(new Date()), sdf.format(new Date()), "����ʹ��"));
				         
				//}
		         // List<Users> list = dao.select(new SelectAll("��"));
	           // System.out.println(list);
			//	dao.update(new Users(136, "tttt", "654321", "��������Ա", "18778679138", "��ͣ��"));
				System.out.println("������IP = " + InetAddress.getLocalHost());
	}
}
