package com.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.ConsultRecord;
import com.crm.entity.Custom;

public interface ConsultRecordMapper {
	
	/**
	 * 将数据插入到咨询师跟踪表
	 * @param consultRecord
	 * @return
	 */
	public int insertConsultRecord(ConsultRecord consultRecord);
	
	/**
	 * 新增咨询师客户跟踪表的记录
	 * @param consultRecord
	 * @return
	 */
	public int newAddConsultRecord(ConsultRecord consultRecord);
	
	/**
	 * 咨询师进行查询
	 * @param name  客户的名字
	 * @param phoneno  客户的手机号码
	 * @param consultdate  分配的日期
	 * @return
	 */
	public List<ConsultRecord> selectConsultRecord(@Param("id")Integer id,@Param("name")String name,@Param("phoneno")String phoneno,@Param("consultdate")String consultdate);
	
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
	
    int deleteByPrimaryKey(Integer id);

    int insert(ConsultRecord record);

    int insertSelective(ConsultRecord record);

    int updateByPrimaryKeySelective(ConsultRecord record);

    int updateByPrimaryKey(ConsultRecord record);
}