package com.hwua.dao;

import com.hwua.pojo.Types;
import java.util.List;

public interface TypesMapperDao {
	
   void insert(Types types);
   
   List<Types> select(Types types);
}