package com.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.Employee;

public interface EmployeeMapper {
	
	/**
	 * 员工进行登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	public Employee employeeLogin(@Param("username")String name,@Param("pass")String pwd);
	
	/**
	 * 管理员增加员工
	 * @param employee 员工信息对象
	 * @return
	 */
	public int addEmployee(Employee employee);
	
	/**
	 * 根据用户名进行查询得到
	 * @param emploeeName 员工的名称
	 * @return
	 */
	public List<Employee> selectAllEmployee(@Param("username")String userName);
	/**
	 * 通过id进行查询
	 * @param id  employee的id
	 * @return
	 */
	 public Employee selectByPrimaryKey(@Param("id")Integer id);
	 
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
	public int deleteByPrimaryKey(Integer id);	
	/**
	 * 根据id初始化密
	 * @param id
	 */
	public void startPass(Integer id);
	
	/**
	 * 查询所有的电销员工
	 * @return
	 */
	public List<Employee> getTmk1();

	/**
	 * 得到所有的咨询师
	 * @return
	 */
	public List<Employee> getConsult();
	
    int insert(Employee record);

    int insertSelective(Employee record);


    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}