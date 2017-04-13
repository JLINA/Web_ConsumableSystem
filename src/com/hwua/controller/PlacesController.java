package com.hwua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwua.pojo.Places;
import com.hwua.pojo.Types;
import com.hwua.service.PlacesService;
import com.hwua.service.TypesService;

import net.sf.json.JSONArray;

@Controller
public class PlacesController {

	@Autowired
	private PlacesService placesService;
	
	

	public PlacesService getPlacesService() {
		return placesService;
	}

	public void setPlacesService(PlacesService placesService) {
		this.placesService = placesService;
	}

	@RequestMapping(value = "/addplace", method = RequestMethod.POST)
	public void addtypes(String typeid,String typeids, HttpServletResponse response){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@RequestMapping(value = "/getadress", method = RequestMethod.POST)
	public void gettypes( HttpServletResponse response){
		List<Places> list = placesService.find();
		try {
			response.setContentType("text/html;charset=utf-8");
			String json = JSONArray.fromObject(list).toString();
			response.getWriter().write(json);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	
}
