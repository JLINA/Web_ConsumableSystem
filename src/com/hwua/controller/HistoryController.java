package com.hwua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwua.pojo.History;
import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Users;
import com.hwua.service.HistoryService;

import net.sf.json.JSONArray;

@Controller
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	@RequestMapping(value = "/selecthistory", method = RequestMethod.POST)
	public void selecthistory(int count ,int pageNum,String str ,HttpServletResponse response){
		
		System.out.println("≤È’“¿˙ ∑£∫"+str+" "+count+" "+pageNum);
		long l = historyService.selectcount(new SelectAll(str));
		int max = pageNum*count;
		int min = (pageNum-1)*count;
		int zs=0;
		if((l%count)==0){
		 zs = (int) (l/count);
		}else{
			 zs = (int) ((l/count)+1);
		}
		List<History> list = historyService.select(new SelectAll(max, min, str));
		response.setContentType("text/html;charset=utf-8");
		try {
			String x= zs+"|";
			response.getWriter().write(x);
			String json = JSONArray.fromObject(list).toString();
			response.getWriter().write(json);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	
}
