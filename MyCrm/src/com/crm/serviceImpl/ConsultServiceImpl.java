package com.crm.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.crm.entity.ConsultRecord;
import com.crm.entity.Custom;
import com.crm.mapper.ConsultRecordMapper;
import com.crm.mapper.CustomMapper;
import com.crm.serviceDao.ConsultService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
//此处是接口的注入，且名字是接口名的首字母改为小写，内部会自己查找
@Component("consultService")
public class ConsultServiceImpl implements ConsultService{

	//进行ConsultRecordMapper注入
	@Resource(name="consultRecordMapper")
	private ConsultRecordMapper consultRecordMapper;

	//对客户对象的mapper进行注入
	@Resource(name="customMapper")
	private CustomMapper customMapper;

//	//引入Redis模板对象
//	@Resource(name="redisTemplate")
//	private RedisTemplate<String, Object> redisTemplate;
	/**
	 * 新增咨询师客户跟踪表的记录
	 * @param consultRecord
	 * @return
	 */
	@Override
	public boolean newAddConsultRecord(ConsultRecord consultRecord) {
		Custom selectByPrimaryKey = customMapper.selectByPrimaryKey(consultRecord.getCustomid());
		if(consultRecord!=null&&selectByPrimaryKey==null&&consultRecord.getCustomid()!=null&&consultRecord.getConsultmanid()!=null&&consultRecord.getConsultstatu()!=""&&consultRecord.getConsultstatu()!=null&&consultRecord.getConsultdate()!=null) {
			consultRecordMapper.newAddConsultRecord(consultRecord);
			return true;
		}else {
			return false;
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
	@Override
	public Map<String,Object> selectConsultRecord(int currentPage,int pagesize,Integer id,String name,String phoneno,String consultdate){
		//调用jar包中提供的分页方法进行分页
		//分页,第一个参数是当前是页数，第二个是页面显示数据的条数
		PageHelper.startPage(currentPage,pagesize);
		//判断userName是否为空
		if(!StringUtil.isEmpty(name)) {
			name="%"+name+"%";
		}
		if(!StringUtil.isEmpty(phoneno)) {
			phoneno="%"+phoneno+"%";
		}
		if(!StringUtil.isEmpty(consultdate)) {
			consultdate="%"+consultdate+"%";
		}
		//调用mapper中的方法
		List<ConsultRecord> selectConsultRecord = consultRecordMapper.selectConsultRecord(id, name, phoneno, consultdate);
//		redisTemplate.opsForValue().set("0", "新增");
//		redisTemplate.opsForValue().set("1", "紧跟");
//		redisTemplate.opsForValue().set("2", "已经报名");
//		redisTemplate.opsForValue().set("3", "死单");
//		redisTemplate.opsForValue().set("4", "报名后退费");
//		for (ConsultRecord consultRecord : selectConsultRecord) {
//			Object object2 = redisTemplate.opsForValue().get(consultRecord.getConsultstatu());
//			consultRecord.setConsultstatu((String)object2);
//		}	
		//根据查询的数据进行分页
		PageInfo<ConsultRecord> page=new PageInfo<ConsultRecord>(selectConsultRecord);
		//声明一个map集合，进行数据的装入
		Map<String,Object> map=new HashMap<String,Object>();
		//进行数据的装入
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}

	/**
	 * 咨询师通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	@Override
	public ConsultRecord selectByPrimary(Integer id) {
		return consultRecordMapper.selectByPrimary(id);
	}

	/**
	 * 咨询师做修改
	 * @param custom  客户对象
	 */
	@Override
	public void editConsultRecord(ConsultRecord consultRecord) {
		consultRecordMapper.editConsultRecord(consultRecord);
	}

}
