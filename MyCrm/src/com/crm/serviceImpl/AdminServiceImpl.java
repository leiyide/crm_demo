package com.crm.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.crm.entity.Employee;
import com.crm.mapper.EmployeeMapper;
import com.crm.mapper.RightsMapper;
import com.crm.serviceDao.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Component("adminService")
public class AdminServiceImpl implements AdminService{

	//进行jobRightMapper注入
	@Resource(name="employeeMapper")
	private EmployeeMapper employeeMapper;

//	//引入Redis模板对象
//	@Resource(name="redisTemplate")
//	private RedisTemplate<String, Object> redisTemplate;
	/**
	 * 管理员增加员工
	 * @param employee 员工信息对象
	 * @return
	 */
	@Override
	public boolean addEmployee(Employee employee) {		
		if(employee!=null&&employee.getUsername()!=null&&employee.getUsername()!=""&&employee.getPass()!=null&&employee.getPass()!=""&&employee.getNickname()!=null&&employee.getNickname()!=""&&employee.getRealname()!=null&&employee.getRealname()!=""&&employee.getJobinfoid()!=null&&employee.getDepartmentid()!=null&&employee.getPhoneno()!=null&&employee.getPhoneno()!=""&&employee.getOfficetel()!=null&&employee.getOfficetel()!=""&&employee.getWorkstatu()!=null&&employee.getWorkstatu()!="") {
			employeeMapper.addEmployee(employee);	
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 在查询的过程中进行分页，使用map类型的数据
	 * @param currentPage  当前页
	 * @param pagesize     页面显示的条数
	 * @param userName     员工的名称
	 * @return   一个map集合
	 */
	@Override
	public Map<String, Object> selectAllEmployee(int currentPage, int pagesize, String userName) {
		//调用jar包中提供的分页方法进行分页
		//分页,第一个参数是当前是页数，第二个是页面显示数据的条数
		PageHelper.startPage(currentPage,pagesize);
		//判断userName是否为空
		if(!StringUtil.isEmpty(userName)) {
			userName="%"+userName+"%";
		}
		
		//调用mapper中的方法
		List<Employee> allEmployee = employeeMapper.selectAllEmployee(userName);
//		//将状态存入redis中
//		redisTemplate.opsForSet().add("0", "离职");
//		redisTemplate.opsForSet().add("1", "在职");
//		for (Employee employee : allEmployee) {
//			Set<Object> members = redisTemplate.opsForSet().members(employee.getWorkstatu());
//			for (Object object : members) {
//				employee.setWorkstatu((String)object);
//			}	
//		}	
		//根据查询的数据进行分页
		PageInfo<Employee> page=new PageInfo<Employee>(allEmployee);
		//声明一个map集合，进行数据的装入
		Map<String,Object> map=new HashMap<String,Object>();
		//进行数据的装入
		map.put("rows", page.getList());
		map.put("total", page.getTotal());
		return map;
	}

	/**
	 * 通过id进行查询
	 * @param id  employee的id
	 * @return
	 */
	public Employee selectByPrimaryKey(Integer id) {
		return  employeeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 编辑员工信息
	 * @param employee  员工对象
	 */
	@Override
	public void editEmployee(Employee employee) {
		employeeMapper.editEmployee(employee);
	}
	/**
	 * 根据id进行制定数据的删除
	 * @param id
	 * @return
	 */
	public void deleteByPrimaryKey(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据id初始化密
	 * @param id
	 */
	public void startPass(Integer id) {
		employeeMapper.startPass(id);
	}

}
