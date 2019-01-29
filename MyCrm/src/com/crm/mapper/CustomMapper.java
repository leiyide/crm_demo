package com.crm.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.Custom;

public interface CustomMapper {
	/**
	 * 增加客户
	 * @param custom  客户对象
	 * @return
	 */
	public int addCutom(Custom custom);
	
	/**
	 * 查询客户 
	 * @param name  客户的名字
	 * @param phoneno  客户的电话号码
	 * @param inviteName  邀请人的名字
	 * @param createDate  创建的日期
	 * @return
	 */
	public List<Custom> selectCustom(@Param("name")String name,@Param("phoneno")String phoneno,@Param("invitename")String inviteName,@Param("createdate")String createDate);
	
	/**
	 * 通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	public Custom selectByPrimaryKey(Integer id);
	
	/**
	 * 修改客户
	 * @param custom  客户对象
	 */
	public void editCustom(Custom custom);

	/**
	 * 查询用户为为新增未上门的所有条数
	 * @return
	 */
	public int getAllNum();
	
	/**
	 * 得到所有的新增未上门原始客户数据
	 * @return
	 */
	public List<Custom> getPristineCustom();
	
	/**
	 * 得到所有的咨询师所需要的数据的总条数
	 * @return
	 */
	public int getConsultNum();
	
	/**
	 * 得分配给咨询师所需要的数据
	 * @return
	 */
	public List<Custom> getConsultCustom();
	
	/**
	 * 分配之后修改状态
	 * @param customId 客户的id
	 * @return
	 */
	public int updateCustom(@Param("id")Integer customId);
	
	/**
	 * 分配之后修改状态
	 * @param customId 客户的id
	 * @return
	 */
	public int updateCustom2(@Param("id")Integer customId);
	
	/**
	 * 查询所有的客户进行数据的导出
	 * @return
	 */
	public List<Custom> getAllCostom();
	
	/**
	 * 将excel表中的数据插入到数据库中
	 * @return
	 */
	public List<Custom> insertAllCostom(Custom custom);
	
	int deleteByPrimaryKey(Integer id);

	int insert(Custom record);

	int insertSelective(Custom record);

	int updateByPrimaryKeySelective(Custom record);

	int updateByPrimaryKey(Custom record);

}