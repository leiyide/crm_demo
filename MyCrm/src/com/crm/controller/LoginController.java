package com.crm.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.crm.entity.Employee;
import com.crm.entity.Rights;
import com.crm.serviceDao.LoginService;


@Controller
public class LoginController {

	//注入LoginService,在resource注解中要使用name属性
	@Resource(name="loginService")
	private LoginService loginService;

	/**
	 * 登录时控制器
	 * @return
	 */
	@RequestMapping("/employeeLogin")
	public ModelAndView employeeLogin(String username,String pass) {
		//得到request对象
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//new 一个ModelAndView
		ModelAndView mav=new ModelAndView();
		//放入父级节点的集合
		List<Rights> parent=new ArrayList<Rights>();
		//放入子节点的集合
		List<Rights> child=new ArrayList<Rights>();
		//放入所有的父子节点关系集合
		Map<Integer,List<Rights>> allpc=new HashMap<Integer,List<Rights>>();
		try {
			//设置请求的编码
			request.setCharacterEncoding("UTF-8");
			//调用登录的方法
			Employee emploeeLogin = loginService.emploeeLogin(username, pass);
			
			if(emploeeLogin!=null&&emploeeLogin.getJobinfoid()!=null) {
				//获得session对象
				HttpSession session = request.getSession();
				//将页面传来的信息，放在实体类中，
				session.setAttribute("username", username);
				//将id放在session中
				session.setAttribute("id", emploeeLogin.getId());
				//获取权限得到所有的一级标题
				List<Rights> right = loginService.getRight(emploeeLogin.getJobinfoid(),emploeeLogin.getId());
				//遍历得到所有的一级标题
				for (Rights rights : right) {
					if(rights.getRid()==rights.getPid()) {
						parent.add(rights);
					}
				}
				session.setAttribute("parent", parent);
				//获取权限得到所有的二级标题
				for (Rights rights : right) {
						if(rights.getRid()!=rights.getPid()) {
							 child.add(rights);
					}
				}
				//将一级标题与二级标题进行对应
				for(Rights prights:parent) {
					//中间的list
					List<Rights> middle=new ArrayList<Rights>();
					for(Rights crights:child) {
						if(crights.getRid()!=crights.getPid()&&prights.getRid()==crights.getPid()) {
							middle.add(crights);
						}
					}
					allpc.put(prights.getRid(),middle);
				}
				session.setAttribute("allpc", allpc);		
				//实现页面的跳转
				mav.setViewName("/main.jsp");
			}else {	
				mav.setViewName("/login.jsp");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mav;
	}
}


