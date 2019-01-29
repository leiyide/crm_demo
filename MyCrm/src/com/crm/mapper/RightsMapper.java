package com.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.Rights;

public interface RightsMapper {
	
	/**
	 * 得到所有的权限
	 * @param jobInfoId 职位id
	 * @return
	 */
	public List<Rights> getRight(@Param("jobInfoId")int jobInfoId,@Param("id")int id);
	
	
	
    int deleteByPrimaryKey(Integer rid);

    int insert(Rights record);

    int insertSelective(Rights record);

    Rights selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Rights record);

    int updateByPrimaryKey(Rights record);
}