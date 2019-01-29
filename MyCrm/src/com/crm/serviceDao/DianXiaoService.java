package com.crm.serviceDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.CustomInfo;

public interface DianXiaoService {

	/**
	 * 电销员工进行跟踪记录的查询
	 * @param currentPage  当前页
	 * @param pagesize     页面显示的条数
	 * @param id  电销员工的id
	 * @param startdate 电销员工分配数据的日期
	 * @return
	 */
	public Map<String,Object>  selecCustomInfo(int currentPage,int pagesize,Integer id,String startdate);


}
