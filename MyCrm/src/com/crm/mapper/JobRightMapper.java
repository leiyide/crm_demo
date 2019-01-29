package com.crm.mapper;

import com.crm.entity.JobRight;

public interface JobRightMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobRight record);

    int insertSelective(JobRight record);

    JobRight selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobRight record);

    int updateByPrimaryKey(JobRight record);
}