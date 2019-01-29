package com.crm.serviceDao;

import java.util.List;
import java.util.Map;

import com.crm.entity.Employee;
import com.crm.entity.Rights;
public interface LoginService {

	/**
	 * 员工进行登录
	 * @param username 登录账号
	 * @param pwd      登录密码
	 * @return
	 */
	public Employee emploeeLogin(String username,String pwd);
	
	/**
	 * 员工登录之后进行获得到所有的权限（获得菜单栏）
	 * @param jobInfoId
	 * @return
	 */
	public List<Rights> getRight(int jobInfoId,int id);
	
	
}
