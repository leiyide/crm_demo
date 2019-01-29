package com.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.ConsultRecord;
import com.crm.entity.CustomInfo;

public interface CustomInfoMapper {
	
	/**
	 * 插入销售客户跟踪表（分配的时候进行使用）
	 * @param customInfo 销售客户跟踪对象
	 * @return
	 */
	public int insertCustomInfo(CustomInfo customInfo);
	
	/**
	 * 电销员工进行跟踪记录的查询
	 * @param id  电销员工的id
	 * @param startdate 电销员工分配数据的日期
	 * @return
	 */
	public List<CustomInfo> selecCustomInfo(@Param("id")Integer id,@Param("startdate")String startdate);
	
    int deleteByPrimaryKey(Integer id);

    int insert(CustomInfo record);

    int insertSelective(CustomInfo record);

    CustomInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomInfo record);

    int updateByPrimaryKey(CustomInfo record);
}