package com.hwua.dao;

import java.util.List;
import java.util.Map;

import com.hwua.pojo.Execlimp;
import com.hwua.pojo.Inputcount;
import com.hwua.pojo.Material;
import com.hwua.pojo.SelectAll;


public interface MaterialMapperDao {
	
	void insert(Material material);
	
    List<Material> select(Material material); 
    
    List<Material> selectsy(SelectAll selectAll); 
    
    int selectcountsy(SelectAll selectAll);
    
    int selectcount(Material material);
    
    Material selectone(long id);
    
    void updateone(Material material);
    
    void delete(long id);
    
    void updatesurplus(Inputcount inputcount );
    
    
    //µ¼³öExcel
    List<Material> export(Execlimp execlimp); 
    
}