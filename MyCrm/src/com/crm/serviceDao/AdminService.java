package com.crm.serviceDao;

import java.util.List;
import java.util.Map;

import com.crm.entity.Employee;

public interface AdminService {
	/**
	 * 管理员增加员工
	 * @param employee 员工信息对象
	 * @return
	 */
	public boolean addEmployee(Employee employee);
	
	/**
	 * 在查询的过程中进行分页，使用map类型的数据
	 * @param currentPage  当前页
	 * @param pagesize     页面显示的条数
	 * @param userName     员工的名称
	 * @return   一个map集合
	 */
	public Map<String,Object> selectAllEmployee(int currentPage,int pagesize,String userName);
	
	/**
	 * 通过id进行查询
	 * @param id  employee的id
	 * @return
	 */
	 public Employee selectByPrimaryKey(Integer id);
	/**
	 * 编辑员工信息
	 * @param employee  员工对象
	 */
	public void editEmployee(Employee employee);
	
	/**
	 * 根据id进行制定数据的删除
	 * @param id
	 * @return
	 */
	public void deleteByPrimaryKey(Integer id);
	/**
	 * 根据id初始化密
	 * @param id
	 */
	public void startPass(Integer id);
	
}
