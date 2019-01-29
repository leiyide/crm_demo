package com.crm.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.crm.entity.ConsultRecord;
import com.crm.entity.CustomInfo;
import com.crm.mapper.CustomInfoMapper;
import com.crm.serviceDao.DianXiaoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Component("DianXiaoService")
public class DianXiaoServiceImpl implements DianXiaoService {

	//进行customInfoMapper的注入
	@Resource(name="customInfoMapper")
	private CustomInfoMapper customInfoMapper;
	
	/**
	 * 电销员工进行跟踪记录的查询
	 * @param currentPage  当前页
	 * @param pagesize     页面显示的条数
	 * @param id  电销员工的id
	 * @param startdate 电销员工分配数据的日期
	 * @return
	 */
	@Override
	public Map<String,Object>  selecCustomInfo(int currentPage,int pagesize,Integer id,String startdate){
		//调用jar包中提供的分页方法进行分页
				//分页,第一个参数是当前是页数，第二个是页面显示数据的条数
				PageHelper.startPage(currentPage,pagesize);
				//判断userName是否为空
				if(!StringUtil.isEmpty(startdate)) {
					startdate="%"+startdate+"%";
				}
				//调用mapper中的方法
				List<CustomInfo> selecCustomInfo = customInfoMapper.selecCustomInfo(id, startdate);
//				redisTemplate.opsForValue().set("0", "新增");
//				redisTemplate.opsForValue().set("1", "紧跟");
//				redisTemplate.opsForValue().set("2", "已经报名");
//				redisTemplate.opsForValue().set("3", "死单");
//				redisTemplate.opsForValue().set("4", "报名后退费");
//				for (CustomInfo customInfo : selecCustomInfo) {
//					Object object2 = redisTemplate.opsForValue().get(customInfo.getConsultstatu());
//					consultRecord.setConsultstatu((String)object2);
//				}	
				//根据查询的数据进行分页
				PageInfo<CustomInfo> page=new PageInfo<CustomInfo>(selecCustomInfo);
				//声明一个map集合，进行数据的装入
				Map<String,Object> map=new HashMap<String,Object>();
				//进行数据的装入
				map.put("rows", page.getList());
				map.put("total", page.getTotal());
				return map;
	}
}
