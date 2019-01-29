package com.crm.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.crm.entity.Employee;
import com.crm.serviceDao.AdminService;
import com.crm.utils.JsonMapper;

@Controller
public class AdminController {

	//注入LoginService,在resource注解中要使用name属性
	@Resource(name="adminService")
	private AdminService adminService;

	/**
	 * 增加员工
	 * @param employee
	 * @return
	 */
	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee(Employee employee) {
		//new 一个ModelAndView
		ModelAndView mav=new ModelAndView();
		try {
			//得到response对象
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			//设置编码格式
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			//添加增加的信息
			boolean addEmployee = adminService.addEmployee(employee);

			if(addEmployee==false) {
				out.print("<script language=\"javascript\">alert('增加失败!请重新进行输入');window.location.href='addemployee.jsp'</script>");
			}
			else {
				out.print("<script language=\"javascript\">alert('增加成功');window.location.href='index.jsp'</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 得到分页
	 * @param offset 页码
	 * @param limit  每页显示的条数
	 * @param userName  员工的名字
	 * @return
	 */
	@RequestMapping("/getFenYe")
	@ResponseBody   //map对象直接转为json传入到页面上
	public Object getFenYe(int offset, int limit, String username) {
		int currentPage=offset/limit+1;
		Map<String, Object> selectAllEmployee = adminService.selectAllEmployee(currentPage, limit, username);
		return selectAllEmployee;
	}
	/**
	 * 通过id进行查询
	 * @param id  employee的id
	 * @return
	 */
	@RequestMapping("/selectByPrimaryKey")
	@ResponseBody
	public Employee selectByPrimaryKey(Integer id) {
		Employee selectByPrimaryKey = adminService.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@RequestMapping("/editEmployee")
	@ResponseBody  //转为json数据
	public String editEmployee(String employee) {
		//此处将json转为对象
		JsonMapper json=new JsonMapper();
		Employee fromJson = json.fromJson(employee, Employee.class);
		adminService.editEmployee(fromJson);
		return "1";
	}

	/**
	 * 根据id进行制定数据的删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteByPrimaryKey")
	@ResponseBody  //转为json数据
	public String deleteByPrimaryKey(Integer id) {
		adminService.deleteByPrimaryKey(id);
		return "1";
	}

	/**
	 * 根据id初始化密
	 * @param id
	 */
	@RequestMapping("/startPass")
	@ResponseBody  //转为json数据
	public String startPass(Integer id) {
		System.out.println(id);
		adminService.startPass(id);
		return "1";
	}
}
