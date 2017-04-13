package com.hwua.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hwua.pojo.Inputcount;
import com.hwua.pojo.Material;
import com.hwua.pojo.SelectAll;
import com.hwua.pojo.Users;
import com.hwua.service.MaterialService;
import com.hwua.util.ReadeExecl;

import net.sf.json.JSONArray;

@Controller
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	public MaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

	/**
	 * 条件批量查询 放大版
	 * 
	 * @param pageNow
	 * @param count
	 * @param response
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.POST)
	public void findall(int pageNum, int count, String name, String typeid, String typeids, String place,
			String stprice, String endprice, HttpServletResponse response) {
		System.out.println("!!!1" + pageNum + "2 " + count + "3 " + name + "4 " + typeid + "5 " + typeids + "6 " + place
				+ "7 " + stprice + "8 " + endprice);
		int max = pageNum * count;
		int min = (pageNum - 1) * count;

		SelectAll selectAll = new SelectAll(name, place, Double.parseDouble(stprice), Double.parseDouble(endprice));
		int z = materialService.findcountsy(selectAll, typeid, typeids);
		int zs = 0;
		if ((z % count) == 0) {
			zs = z / count;
		} else {
			zs = (z / count) + 1;
		}
		selectAll.setMax(max);
		selectAll.setMin(min);
		List<Material> list = materialService.findsy(selectAll, typeid, typeids);
		response.setContentType("text/html;charset=utf-8");
		try {

			String x = zs + "|";
			response.getWriter().write(x);
			String json = JSONArray.fromObject(list).toString();
			response.getWriter().write(json);
			json = "" + InetAddress.getLocalHost();
			String[] strings = json.split("/");
			response.getWriter().write("|" + strings[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 查询一个
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/findone", method = RequestMethod.POST)
	public void findone(String id, HttpServletResponse response) {
		System.out.println("++++" + id);
		long ids = 0;
		if (id == null) {

		} else {
			ids = Long.parseLong(id);
		}
		Material material = materialService.findone(ids);
		System.out.println(material);
		response.setContentType("text/html;charset=utf-8");
		try {
			String json = JSONArray.fromObject(material).toString();
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改一个
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/updateone", method = RequestMethod.POST)
	public void updateone(String id, String numbers, String typeid, String name, String type, String standstandard,
			String price, String factory, String leavefactorydate, String buydate, String indate, String place,
			String usesituation, String count, String surplus, String remarks, HttpServletResponse response,
			HttpSession session) {
		System.out.println(id + " " + price + " " + count + " " + surplus);
		Material material = new Material(Long.parseLong(id), numbers, typeid, name, type, standstandard,
				Double.parseDouble(price), factory, leavefactorydate, buydate, indate, place, usesituation,
				Long.parseLong(count), Long.parseLong(surplus), "", remarks);
		System.out.println("修改：" + material);
		Users info = (Users) session.getAttribute("user");
		 materialService.updateone(material,info);
		response.setContentType("text/html;charset=utf-8");
		String x = "1";
		try {
			response.getWriter().write(x);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/delete")
	public void delete(String ids, HttpServletResponse response, HttpSession session) {
		String[] arr = ids.split(",");
		ArrayList<String> list = new ArrayList<>();
		for (String i : arr) {
			list.add(i);
			System.out.println(i);
		}
		Users info = (Users) session.getAttribute("user");
		materialService.delete(list, info);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 添加
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/addmaterial", method = RequestMethod.POST)
	public void addmaterial(String numbers, String typeid, String name, String type, String standstandard, String price,
			String factory, String leavefactorydate, String buydate, String place, String usesituation, String surplus,
			String remarks, HttpServletResponse response, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd");
		System.out.println("22222222" + numbers + " " + price + " " + surplus + " " + typeid);
		Material material = new Material(numbers, typeid, name, type, standstandard, Double.parseDouble(price), factory,
				leavefactorydate, buydate, sdf.format(new Date()), place, usesituation, 0, Long.parseLong(surplus), "",
				remarks);
		System.out.println("添加：" + material);
		Users info = (Users) session.getAttribute("user");
		 materialService.addmaterial(material,info);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 入库出库
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/updatesurplus")
	public void updatesurplus(String id, int surplu, HttpServletResponse response, HttpSession session) {
		Material material = materialService.findone(Long.parseLong(id));
		System.out.println("库存开始数量：" + material.getSurplus() + " " + material.getCount());
		long surplus = material.getSurplus() + surplu;
		long counts = 0;

		System.out.println("库存实际数量:" + surplus + " " + counts);
		String x = "1";
		if (surplus < 0) {
			x = "2";
		} else {
			Users info = (Users) session.getAttribute("user");
			if (surplu < 0) {
				counts = material.getCount() + surplu;
				materialService.updatesurplus(new Inputcount(Long.parseLong(id), counts, surplus), info);
			} else {
				materialService.updatesurplus(new Inputcount(Long.parseLong(id), material.getCount(), surplus), info);
			}

		}

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(x);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

	/**
	 * 添加
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/type", method = RequestMethod.POST)
	public void type(HttpServletResponse response, HttpSession session) {

		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Excel导入
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/upexecl", method = RequestMethod.POST)
	public String upexecl(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String path1 = "";
		String path2 = "";
		// 检查form是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				String property = System.getProperty("evan.webapp");
				// 由CommonsMultipartFile继承而来,拥有上面的方法.
				MultipartFile file = multiRequest.getFile(iter.next());
				
				if (file != null) {
					String fileName = "demoUpload" + file.getOriginalFilename();
					// String path = "D:/" + fileName;
					path1 = property + "Upfile/" + fileName;
					path2 = "Upfile/" + fileName;
				
					File localFile = new File(path1);
					try {
						file.transferTo(localFile);

						List<Material> list = new ReadeExecl().reade(path1);

						Users info = (Users) session.getAttribute("user");
						
						System.out.println(info);
						
						for (Material m : list) {
							
							System.out.println(m);
							materialService.addmaterial(m, info);
						}

					} catch (Exception e) {

						e.printStackTrace();

					}
				}
			}
		}

		return "/viplist.jsp.jsp";
	}
	
	/**
	 * Excel导出
	 * @param typeid
	 * @param typeids
	 * @param place
	 * @param str
	 * @param stprice
	 * @param endprice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public String export(String typeid, String typeids, String place, String str, String stprice, String endprice,String filename,
			Model model,HttpSession session) {
		System.out.println("1 " + typeid + "2 " + typeids + "3 " + place + "4 " + str);
		if (stprice.equals("") || stprice == null) {
			stprice = "0";
		} else if (endprice.equals("") || endprice == null) {
			endprice = "9999999";
		}
		List<Material> list = materialService.export(typeid, typeids, place, str, Double.parseDouble(stprice),
				Double.parseDouble(endprice));
		model.addAttribute("list", list);
		session.setAttribute("filename", filename);
		return "/exportExcel.jsp";

	}

}
