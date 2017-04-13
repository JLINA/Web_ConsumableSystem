package com.hwua.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hwua.dao.HistoryMapperDao;
import com.hwua.dao.MaterialMapperDao;
import com.hwua.dao.TypesMapperDao;
import com.hwua.pojo.Execlimp;
import com.hwua.pojo.History;
import com.hwua.pojo.Inputcount;
import com.hwua.pojo.Material;
import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Types;
import com.hwua.pojo.Users;

@Transactional
public class MaterialService {

	
	
	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/**
	 * ²éÕÒ
	 * @param material
	 * @return
	 */
	public List<Material> find(Material material){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		List<Material> list = dao.select(material);
		return list;
		
	}
	
	/**
	 * ²éÕÒ¼ÇÂ¼Êý
	 * @param material
	 * @return
	 */
	public int findcount(Material material){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		int zs = dao.selectcount(material);
		return zs;
		
	}
	
	/**
	 * ²éÕÒÒ»¸ö
	 * @param id
	 * @return
	 */
	public Material findone(long id){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		Material m = dao.selectone(id);
		return m;
		
	}
	
	/**
	 * ÐÞ¸Ä
	 * @param material
	 */
	public void updateone(Material material,Users info){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String time = sdf.format(new Date());
		dao.updateone(material);
		//dao1.insert(new History(time, info.getId(), "ÐÞ¸Ä", "Æ÷²Äid:"+material.getId()));
		
	}
	
	/**
	 * É¾³ý
	 * @param ids
	 */
	public void delete(List<String> ids,Users info){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String time = sdf.format(new Date());
		for(String id:ids){
			dao.delete(Long.parseLong(id));
		//	dao1.insert(new History(time, info.getId(), "É¾³ý", "Æ÷²Äid:"+id));
		}
	}
	
	/**
	 * ²éÕÒ
	 * @param selectAll
	 * @return
	 */
	public List<Material> findsy(SelectAll selectAll,String typeid,String typeids){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		TypesMapperDao dao1 = sqlSession.getMapper(TypesMapperDao.class);
		System.out.println(selectAll.getMax()+"+++"+selectAll.getMin()+" "+typeid);
		ArrayList<Material> list= new ArrayList<>();
		if(typeids !=null &&(!typeids.equals(""))){
			if((typeid==null ||typeid.equals(""))&&!typeids.equals("1")){
				List<Types> types = dao1.select(new Types(null,typeids));
				for(Types t:types){
					selectAll.setType(t.getTypeid());
					List<Material> list2 = dao.selectsy(selectAll);
					
						if(list2!=null){
							for(Material m:list2){
							list.add(m);
						}
						
					}
				}
			}else{
				selectAll.setType(typeid);
				list = (ArrayList<Material>) dao.selectsy(selectAll);
				
				System.out.println(list);
			}
		}else{
			list = (ArrayList<Material>) dao.selectsy(selectAll);
		}
		return list;
		
	}
	
	/**
	 * ²éÕÒ¼ÇÂ¼Êý
	 * @param selectAll
	 * @return
	 */
	public int findcountsy(SelectAll selectAll,String typeid,String typeids){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		TypesMapperDao dao1 = sqlSession.getMapper(TypesMapperDao.class);
		System.out.println("00000 "+typeid+ " "+typeids);
		int x=0;
		if(typeids !=null &&(!typeids.equals(""))){
			if((typeid==null ||typeid.equals(""))&&!typeids.equals("1")){
				List<Types> types = dao1.select(new Types(null,typeids));
				int xx=1;
				for(Types t:types){
					selectAll.setType(t.getTypeid());
					System.out.println(xx+"typ:"+t.getTypeid());
					x = x + dao.selectcountsy(selectAll);
					xx++;
				}
				System.out.println("aa1"+typeids);
			}else{
				selectAll.setType(typeid);
				x = dao.selectcountsy(selectAll);
				System.out.println("aa2"+typeids);
			}
		}else{
			x = dao.selectcountsy(selectAll);
			System.out.println("aa3"+typeids);
		}
		
		
		return x;
		
	}
	
	/**
	 * Ìí¼ÓÆ÷²Ä
	 * @param material
	 */
	public void addmaterial(Material material,Users info){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String time = sdf.format(new Date());
		dao.insert(material);
		//dao1.insert(new History(time, info.getId(), "Ìí¼Ó", "Æ÷²ÄÃû×Ö:"+material.getName()));
	}
	
	/**
	 * Æ÷²ÄÈë¿â³ö¿â
	 * @param material
	 */
	public void updatesurplus(Inputcount inputcount,Users info){
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		HistoryMapperDao dao1 = sqlSession.getMapper(HistoryMapperDao.class);
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		String time = sdf.format(new Date());
		dao.updatesurplus(inputcount);
		//dao1.insert(new History(time, info.getId(), "Æ÷²Ä³ö¿â/Èë¿â", "Æ÷²Äid:"+inputcount.getId()));
	}
	
	
	public List<Material> export(String typeid,String typeids,String place,String str,double stprice,double endprice){
		TypesMapperDao dao1 = sqlSession.getMapper(TypesMapperDao.class);
		MaterialMapperDao dao = sqlSession.getMapper(MaterialMapperDao.class);
		ArrayList<Material> list = new ArrayList<>();
		Execlimp execlimp = new Execlimp();
		execlimp.setPlace(place);
		execlimp.setStprice(stprice);
		execlimp.setEndprice(endprice);
		execlimp.setStr(str);
		if(typeids.equals("")){
			list = (ArrayList<Material>) dao.export(execlimp);
			System.out.println(1);
		}else{
			if(typeid.equals("")){
				List<Types> types = dao1.select(new Types("", typeids));
				for(Types t:types){
					execlimp.setType(t.getTypeid());
					List<Material> list2 = dao.export(execlimp);
					if(list2!=null){
						for(Material m:list2){
						list.add(m);
						}
					}
				}
				System.out.println(2);
			}else{
				execlimp.setType(typeid);
				list = (ArrayList<Material>) dao.export(execlimp);
				System.out.println(3);
			}
		}
		
		return list;
	}
	
	
}
