package com.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.serviceDao.DianXiaoService;

@Controller
public class DianXiaoController {

		//进行DianXiaoService的注入
		@Resource(name="dianXiaoService")
		private DianXiaoService dianXiaoService;
		
		
		/**
		 * 电销员工进行跟踪记录的查询
		 * @param currentPage  当前页
		 * @param pagesize     页面显示的条数
		 * @param id  电销员工的id
		 * @param startdate 电销员工分配数据的日期
		 * @return
		 */
		@RequestMapping("/selecCustomInfo")
		@ResponseBody
		public Map<String,Object>  selecCustomInfo(int offset,int limit,Integer id,String startdate){
			int currentPage=offset/limit+1;
			Map<String, Object> selecCustomInfo = dianXiaoService.selecCustomInfo(currentPage, limit, id, startdate);
			return selecCustomInfo;  
		}
}
