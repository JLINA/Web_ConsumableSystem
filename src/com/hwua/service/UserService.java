package com.hwua.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class UserService {

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//ע��
	public void addUser(Users user){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		dao.insert(user);
	}
	
	//����
	public List<Users> allusers(SelectAll selectAll ){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		List<Users> list = dao.select(selectAll);
		return list;
		
	}
	
	public Users selectbyid(long id ){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
	    Users user = dao.selectbyid(id);
		return user;
		
		
	}   
	
	
	public long selectcount(SelectAll selectAll ){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		long count = dao.selectcount(selectAll);
		return count;
	}
	
	//�޸�
	public void update(Users user,Users info){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String time = sdf.format(new Date());
		Users users = dao.selectbyid(user.getId());
		if(user.getId()!=0){
		if(user.getRole()!=null){
			if(user.getRole().equals("1")){
				users.setRole("����Ա");
			}else{
				users.setRole("��ͨ�û�");
			}
			
		}else if(user.getState()!=null){
			if(user.getState().equals("1")){
				users.setState("����ʹ��");
			}else{
				users.setState("��ͣ��");
			}
		}else if(user.getPassword()!=null){
			users.setPassword(user.getPassword());
		}else if(user.getUsername()!=null){
			users.setUsername(user.getUsername());
		}else if(user.getPhone()!=null){
			users.setPhone(user.getPhone());
		}
		
		dao.update(users);
		}
		//dao1.insert(new History(time, info.getId(), "�޸�", "�û�id:"+users.getId()));
	}
	
	//ɾ��
	public void delete(List<String> ids,Users info){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String time = sdf.format(new Date());
		for(String id:ids){
			dao.delete(Long.parseLong(id));
		//	dao1.insert(new History(time, info.getId(), "ɾ��", "�û�id:"+id));
		}
	}
	
	//��¼
	public Users findUser(String username,String password){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		String s1 =""+username+password;
		List<Users> list = dao.select(new SelectAll());
		System.out.println(list);
		Users user = new Users();
		for(Users u:list){
			String s2 = ""+u.getUsername()+u.getPassword();
			if(s1.equals(s2)){
				
				SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
				String time = sdf.format(new Date());
				user=u;
				u.setLasttime(time);
				dao.update(u);
			//	dao1.insert(new History(time, u.getId(), "��¼", "--"));
				break;
			}
		}
		return user ;
		
	}
	
	public List<Users> se(){
		UsersMapperDao dao = sqlSession.getMapper(UsersMapperDao.class);
		List<Users> se = dao.se();
		return se;
	}
}
