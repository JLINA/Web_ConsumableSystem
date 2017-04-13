package com.hwua.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Users;
import com.hwua.service.UserService;

import net.sf.json.JSONArray;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 注册用户
	 * @param user
	 * @param code
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(String username,String password,String phone, String code, HttpSession session, HttpServletResponse response) {
		System.out.println("用户注册："+username+" "+password+" "+phone+" "+code);
		String str = (String) session.getAttribute("code");
		System.out.println(str);
		String json = null;
		if(code.equals(str)){
			List<Users> list = userService.allusers(new SelectAll(username));
			System.out.println("````````````"+list);
			if(list.size()!=0){
				json="3";
			}else{
				SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
				userService.addUser(new Users(username, password, "普通用户", phone, sdf.format(new Date()), sdf.format(new Date()), "正在使用"));
				json="1";
			}
			
		}else{
			json="2";
		}
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * 用户登录
	 * @param user
	 * @param code
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void givejurisdiction(String username, String password,String code,HttpSession session, HttpServletResponse response) {
		System.out.println("用户登录："+username+" "+password+" "+code);
		String str = (String) session.getAttribute("code");
		System.out.println(str);
		Users users= new Users();
		String json = null;
		int x=0;
		if(code.equals(str)){
			System.out.println("login");
			users = userService.findUser(username, password);
			System.out.println(users);
			System.out.println(users);
			if(users.getRole()==null){
				json="4|";
			}else{
				
				System.out.println("使用状态："+users.getState());
				if(("正在使用").equals(users.getState())){
					System.out.println("1");
					x=1;
					session.setAttribute("user", users);
					json="1|";
				}else{
					json="3|";
					
				}
			}
			
		}else{
			json="2|";
		
		}
		System.out.println("登录成功");
		response.setContentType("text/html;charset=utf-8");
		if(x==1){
			try {
				response.getWriter().write(json);
				json = JSONArray.fromObject(users).toString();
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write(json);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 用户注销
	 * @param session
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.POST)
	public void exit(HttpSession session,HttpServletResponse response) {
		session.removeAttribute("user");
		System.out.println("exit");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("1");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 修改用户
	 * @param session
	 */
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public void update(String ids,String username,String password,String role,String phone,String state,HttpSession session,HttpServletResponse response) {
		System.out.println(ids);
		String[] id = ids.split(",");
		ArrayList<String> list = new ArrayList<>();
		System.out.println("修改："+id.length);
		for(String i:id){
			Users user = new Users(Long.parseLong(i), username, password, role, phone, state);
			System.out.println("修改："+user);
			Users info = (Users) session.getAttribute("user");
			if(user.getId()!=0){
				userService.update(user,info);
			}
			
		}
		
	
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("1");
			
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	/**
	 * 删除用户
	 * @param session
	 */
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public void delete(String ids,HttpServletResponse response,HttpSession session) {
		System.out.println(ids);
		String[] id = ids.split(",");
		ArrayList<String> list = new ArrayList<>();
		for(String i:id){
			list.add(i);
			System.out.println("删除:"+i);
		}
		Users info = (Users) session.getAttribute("user");
		userService.delete(list,info);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("1");
			
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	/**
	 * 查找
	 * @param count
	 * @param pageNum
	 * @param str
	 * @param response
	 */
	@RequestMapping(value = "/selectusers", method = RequestMethod.POST)
	public void select(int count ,int pageNum,String str ,HttpServletResponse response){
		System.out.println("查找11："+str+" "+count+" "+pageNum);
		long l = userService.selectcount(new SelectAll(str));
		int max = pageNum*count;
		int min = (pageNum-1)*count;
		int zs=0;
		if((l%count)==0){
		 zs = (int) (l/count);
		}else{
			 zs = (int) ((l/count)+1);
		}
		List<Users> list = userService.allusers(new SelectAll(max, min, str));
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
	
	@RequestMapping(value = "/selectuser", method = RequestMethod.POST)
	public void selectuser(String id,HttpServletResponse response){
		System.out.println("查找："+id);
		Users user = userService.selectbyid(Long.parseLong(id));
		response.setContentType("text/html;charset=utf-8");
		try {
			String json = JSONArray.fromObject(user).toString();
			response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	@RequestMapping(value = "/se", method = RequestMethod.POST)
	public void se(HttpServletResponse response){
		List<Users> list = userService.se();
		
		try {
			String json = JSONArray.fromObject(list).toString();
			response.getWriter().write(json);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/seo", method = RequestMethod.GET)
	public String seo(Model model) throws IOException{
		List<Users> list = userService.se();
		model.addAttribute("list", list);
		return "/NewFile1.jsp";
		
		
	}
	@RequestMapping(value = "/seo1", method = RequestMethod.GET)
	public String seo1(Model model) throws IOException{
		List<Users> list = userService.se();
		model.addAttribute("list", list);
		return "/index.jsp";
		
		
	}
	
	
}
