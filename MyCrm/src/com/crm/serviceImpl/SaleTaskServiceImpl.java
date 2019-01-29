package com.crm.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.crm.entity.ConsultRecord;
import com.crm.entity.Custom;
import com.crm.entity.CustomInfo;
import com.crm.entity.Employee;
import com.crm.mapper.ConsultRecordMapper;
import com.crm.mapper.CustomInfoMapper;
import com.crm.mapper.CustomMapper;
import com.crm.mapper.EmployeeMapper;
import com.crm.serviceDao.SaleTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Component("saleTaskService")
public class SaleTaskServiceImpl implements SaleTaskService{

	//进行mapper的注入
	@Resource(name="employeeMapper")
	public EmployeeMapper employeeMapper;

	//进行客户mapper的注入
	@Resource(name="customMapper")
	public CustomMapper customMapper;

	//进行销售客户跟踪表的注入
	@Resource(name="customInfoMapper")
	public CustomInfoMapper customInfoMapper;

//	//引入Redis模板对象
//	@Resource(name="redisTemplate")
//	private RedisTemplate<String, Object> redisTemplate;

	//进行咨询师跟踪表的注入
	@Resource(name="consultRecordMapper")
	public ConsultRecordMapper consultRecordMapper;

	/**
	 * 查询所有的tmk并进行分页操作
	 * @return
	 */
	@Override
	public Map<String, Object> getTmk(int currentPage, int pagesize) {
		//调用jar包中提供的分页方法进行分页
		//分页,第一个参数是当前是页数，第二个是页面显示数据的条数
		PageHelper.startPage(currentPage,pagesize);
		//调用mapper中的方法
		List<Employee> tmk = employeeMapper.getTmk1();
//		//将状态存入redis中
//		redisTemplate.opsForSet().add("0", "离职");
//		redisTemplate.opsForSet().add("1", "我在职");
//		for (Employee employee : tmk) {
//			Set<Object> members = redisTemplate.opsForSet().members(employee.getWorkstatu());
//			for (Object object : members) {
//				employee.setWorkstatu((String)object);
//			}	
//		}	
		//根据查询的数据进行分页
		PageInfo<Employee> page=new PageInfo<Employee>(tmk);
		//声明一个map集合，进行数据的装入
		Map<String,Object> map=new HashMap<String,Object>();
		//进行数据的装入
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}

	/**
	 * 增加客户（excel表的插入）
	 */
	@Override
	public boolean addCutom(Custom custom) {
		if(custom!=null&&custom.getName()!=null&&custom.getName()!=""&&custom.getEducation()!=null&&custom.getEducation()!=""&&custom.getPhoneno()!=null&&custom.getPhoneno()!=""&&custom.getQq()!=null) {
			customMapper.addCutom(custom);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 查询客户
	 * @param currentPage 当前页
	 * @param pagesize    页面显示的条数 
	 * @param name        用户的名字
	 * @param phoneno     用户的手机号码
	 * @param inviteName  邀请人姓名
	 * @param createDate  创建的日期
	 * @return  map集合
	 */
	@Override
	public Map<String,Object> selectCustom(int currentPage,int pagesize,String name,String phoneno,String inviteName,String createDate){
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
		if(!StringUtil.isEmpty(inviteName)) {
			inviteName="%"+inviteName+"%";
		}
		if(!StringUtil.isEmpty(createDate)) {
			createDate="%"+createDate+"%";
		}

		//调用mapper中的方法
		List<Custom> selectCustom = customMapper.selectCustom(name, phoneno, inviteName, createDate);
//		//		//将状态存入redis中
//		redisTemplate.opsForSet().add("0", "未上门");
//		redisTemplate.opsForSet().add("1", "已上门");
//		redisTemplate.opsForSet().add("2", "销售跟进");
//		redisTemplate.opsForSet().add("3", "咨询跟进");
//		redisTemplate.opsForSet().add("4", "死单");
//		redisTemplate.opsForSet().add("5", "已报名");
//		for (Custom custom : selectCustom) {
//			Set<Object> members = redisTemplate.opsForSet().members(custom.getCustomstatu());
//			for (Object object : members) {
//				custom.setCustomstatu((String)object);
//			}	
//		}	
		//根据查询的数据进行分页
		PageInfo<Custom> page=new PageInfo<Custom>(selectCustom);
		//声明一个map集合，进行数据的装入
		Map<String,Object> map=new HashMap<String,Object>();
		//进行数据的装入
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}

	/**
	 * 通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	@Override
	public Custom selectByPrimaryKey(Integer id) {
		return customMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改 客户的信息
	 */
	@Override
	public void editCustom(Custom custom) {
		customMapper.editCustom(custom);
	}

	/**
	 * 得到所有的记录条数
	 * @return
	 */
	@Override
	public int getAllNum() {
		return customMapper.getAllNum();
	}

	/**
	 * 得到所有的原始客户数据
	 * @return
	 */
	public List<Custom> getPristineCustom(){
		return customMapper.getPristineCustom();
	}

	/**
	 * 插入销售客户跟踪表（分配的时候进行使用）
	 * @param customInfo 销售客户跟踪对象
	 * @return
	 */
	@Override
	public int insertCustomInfo(CustomInfo customInfo) {
		return customInfoMapper.insertCustomInfo(customInfo);
	}


	/**
	 * 得到所有的咨询师所需要的数据的总条数
	 * @return
	 */
	@Override
	public int getConsultNum() {
		return customMapper.getConsultNum();
	}

	/**
	 * 得分配给咨询师所需要的数据
	 * @return
	 */
	@Override
	public List<Custom> getConsultCustom(){
		return customMapper.getConsultCustom();
	}

	/**
	 * 将数据插入到咨询师跟踪表
	 * @param consultRecord
	 * @return
	 */
	@Override
	public int insertConsultRecord(ConsultRecord consultRecord) {
		return consultRecordMapper.insertConsultRecord(consultRecord);
	}

	/**
	 * 查询所有的咨询师
	 * @return
	 */
	@Override
	public Map<String,Object> getConsult(int currentPage,int pagesize){
		//调用jar包中提供的分页方法进行分页
		//分页,第一个参数是当前是页数，第二个是页面显示数据的条数
		PageHelper.startPage(currentPage,pagesize);
		//调用mapper中的方法
		List<Employee> consult = employeeMapper.getConsult();
//		//将状态存入redis中
//		redisTemplate.opsForSet().add("0", "离职");
//		redisTemplate.opsForSet().add("1", "在职");
//		for (Employee employee : consult) {
//			Set<Object> members = redisTemplate.opsForSet().members(employee.getWorkstatu());
//			for (Object object : members) {
//				employee.setWorkstatu((String)object);
//			}	
//		}	
		//根据查询的数据进行分页
		PageInfo<Employee> page=new PageInfo<Employee>(consult);
		//声明一个map集合，进行数据的装入
		Map<String,Object> map=new HashMap<String,Object>();
		//进行数据的装入
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}
	
	/**
	 * 分配电销员工之后修改状态
	 * @param customId 客户的id
	 * @return
	 */
	@Override
	public int updateCustom(Integer customId) {
		return customMapper.updateCustom(customId);
	}
	
	/**
	 * 分配咨询师之后修改状态
	 * @param customId 客户的id
	 * @return
	 */
	@Override
	public int updateCustom2(Integer customId) {
		return customMapper.updateCustom2(customId);
	}
	
	/**
	 * 查询所有的客户进行数据的导出
	 * @return
	 */
	@Override
	public List<Custom> getAllCostom(){
		return customMapper.getAllCostom();
	}
}
