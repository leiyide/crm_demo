package com.crm.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.crm.entity.Employee;
import com.crm.entity.Rights;
import com.crm.mapper.EmployeeMapper;
import com.crm.mapper.RightsMapper;
import com.crm.serviceDao.LoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

//此处是接口的注入，且名字是接口名的首字母改为小写，内部会自己查找
@Component("loginService")
public class LoginServiceImpl implements LoginService {
	//进行jobRightMapper注入
	@Resource(name="employeeMapper")
	private EmployeeMapper employeeMapper;

	//注入Rights
	@Resource(name="rightsMapper")
	private RightsMapper rightsMapper;

	/**
	 * 员工进行登录
	 * @param username 登录账号
	 * @param pwd      登录密码
	 * @return
	 */
	@Override
	public Employee emploeeLogin(String username, String pwd) {
		return employeeMapper.employeeLogin(username, pwd);
	}

	/**
	 * 员工登录之后进行获得到所有的权限（获得菜单栏）
	 * @param jobInfoId
	 * @return
	 */
	public List<Rights> getRight(int jobInfoId,int id){
		return rightsMapper.getRight(jobInfoId,id);
	}

}
