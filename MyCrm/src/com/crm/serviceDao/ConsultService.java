package com.crm.serviceDao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.ConsultRecord;

public interface ConsultService {
	
	/**
	 * 新增咨询师客户跟踪表的记录
	 * @param consultRecord
	 * @return
	 */
	public boolean newAddConsultRecord(ConsultRecord consultRecord);
	
	
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
	public Map<String,Object> selectConsultRecord(int currentPage,int pagesize,Integer id,String name,String phoneno,String consultdate);
	
	/**
	 * 咨询师通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	public ConsultRecord selectByPrimary(Integer id);
	/**
	 * 咨询师做修改
	 * @param custom  客户对象
	 */
	public void editConsultRecord(ConsultRecord consultRecord);
}
