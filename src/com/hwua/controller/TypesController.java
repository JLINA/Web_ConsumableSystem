package com.hwua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwua.pojo.Types;
import com.hwua.service.TypesService;

import net.sf.json.JSONArray;

@Controller
public class TypesController {

	@Autowired
	private TypesService typesService;
	
	public TypesService getTypesService() {
		return typesService;
	}
	public void setTypesService(TypesService typesService) {
		this.typesService = typesService;
	}

	@RequestMapping(value = "/addtypes", method = RequestMethod.POST)
	public void addtypes(String typeid,String typeids, HttpServletResponse response){
		String i = typesService.addtypes(new Types(typeid, typeids));
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(i);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@RequestMapping(value = "/gettypes", method = RequestMethod.POST)
	public void gettypes(String typeids, HttpServletResponse response){
		System.out.println(typeids);
		List<Types> list = typesService.find(new Types(null, typeids));
		try {
			response.setContentType("text/html;charset=utf-8");
			String json = JSONArray.fromObject(list).toString();
			response.getWriter().write(json);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	
}
