package com.crm.controller;

import java.io.PrintWriter;


import java.text.SimpleDateFormat;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.crm.entity.ConsultRecord;
import com.crm.entity.Custom;
import com.crm.serviceDao.ConsultService;
import com.crm.utils.JsonMapper;

@Controller
public class ConsultController {

	//进行咨询师操作的mapper的注入
	@Resource(name="consultService")
	public ConsultService consultService;

	/**
	 * 新增咨询师客户跟踪表的记录
	 * @param consultRecord
	 * @return
	 */
	@RequestMapping("/newAdd")
	public void newAddConsultRecord(String customid,String consultmanid,String consultstatu,String consultdate,String result) {		
		try {
			//咨询师跟踪记录表对象
			ConsultRecord consultRecord=new ConsultRecord();
			consultRecord.setCustomid(Integer.parseInt(customid));
			consultRecord.setConsultmanid(Integer.parseInt(consultmanid));
			consultRecord.setConsultstatu(consultstatu);
			SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd");
			consultRecord.setConsultdate(sdf.parse(consultdate));
			consultRecord.setResult(result);
			//得到response对象
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			//设置编码格式
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			//添加增加的信息
			boolean newAddConsultRecord = consultService.newAddConsultRecord(consultRecord);
			if(newAddConsultRecord==false) {
				out.print("<script language=\"javascript\">alert('增加失败!请重新进行输入');window.location.href='costomertask1.jsp'</script>");
			}
			else {
				out.print("<script language=\"javascript\">alert('增加成功');window.location.href='index.jsp'</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 咨询师进行数据的查询
	 * @param currentPage  当前页
	 * @param pagesize     页面显示的条数
	 * @param id           咨询师的id
	 * @param name         客户名称
	 * @param phoneno      客户的电话号码
	 * @param consultdate  分配的日期
	 * @return
	 */
	@RequestMapping("/selectConsultRecord")
	@ResponseBody
	public Object selectConsultRecord(int offset,int limit,Integer id,String name,String phoneno,String consultdate){
		int currentPage=offset/limit+1;
		Map<String, Object> selectAllEmployee = consultService.selectConsultRecord(currentPage, limit, id, name, phoneno, consultdate);
		return selectAllEmployee;  
	}
	
	/**
	 * 通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	@RequestMapping("/selectConsult")
	@ResponseBody
	public ConsultRecord selectConsult(Integer rid) {
		return consultService.selectByPrimary(rid);
	}
	
	/**
	 * 咨询师修改 客户的信息
	 */
	@RequestMapping("/editConsultRecord")
	@ResponseBody  //转为json数据
	public String editConsultRecord(String consultRecord) {
		//此处将json转为对象
		JsonMapper json=new JsonMapper();
		ConsultRecord fromJson = json.fromJson(consultRecord, ConsultRecord.class);
		consultService.editConsultRecord(fromJson);
		return "1";
	}
}
