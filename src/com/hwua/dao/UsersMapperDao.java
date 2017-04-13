package com.hwua.dao;

import java.util.List;

import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Users;

public interface UsersMapperDao {
	
    void insert(Users user);
    
    List<Users> select(SelectAll selectAll);
    
    long selectcount(SelectAll selectAll);
    
    void update(Users user);
    
    void delete (long id);
    
    Users selectbyid(long id);
    
    List<Users> se();
}