package com.crm.serviceDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.ConsultRecord;
import com.crm.entity.Custom;
import com.crm.entity.CustomInfo;

public interface SaleTaskService {
	/**
	 * 查询所有的电销员工
	 * @return
	 */
	public Map<String,Object> getTmk(int currentPage,int pagesize);
	
	/**
	 * 增加客户（excel表的插入）
	 * @param custom  客户对象
	 * @return
	 */
	public boolean addCutom(Custom custom);
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
	public Map<String,Object> selectCustom(int currentPage,int pagesize,String name,String phoneno,String inviteName,String createDate);

	
	/**
	 * 通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	public Custom selectByPrimaryKey(Integer id);
	
	/**
	 * 修改客户
	 * @param custom  客户对象
	 */
	public void editCustom(Custom custom);
	
	/**
	 * 得到初始状态下的所有的数据条数
	 * @return
	 */
	public int getAllNum();
	
	/**
	 * 得到所有的原始客户数据
	 * @return
	 */
	public List<Custom> getPristineCustom();
	
	/**
	 * 插入销售客户跟踪表（分配的时候进行使用）
	 * @param customInfo 销售客户跟踪对象
	 * @return
	 */
	public int insertCustomInfo(CustomInfo customInfo);
	
	/**
	 * 得到所有的咨询师所需要的数据的总条数
	 * @return
	 */
	public int getConsultNum();
	
	/**
	 * 得分配给咨询师所需要的数据
	 * @return
	 */
	public List<Custom> getConsultCustom();
	
	/**
	 * 将数据插入到咨询师跟踪表
	 * @param consultRecord
	 * @return
	 */
	public int insertConsultRecord(ConsultRecord consultRecord);
	
	/**
	 * 查询所有的咨询师
	 * @return
	 */
	public Map<String,Object> getConsult(int currentPage,int pagesize);
	
	/**
	 * 分配电销员工之后修改状态
	 * @param customId 客户的id
	 * @return
	 */
	public int updateCustom(Integer customId);
	
	/**
	 * 分配咨询师之后修改状态
	 * @param customId 客户的id
	 * @return
	 */
	public int updateCustom2(Integer customId);
	
	/**
	 * 查询所有的客户进行数据的导出
	 * @return
	 */
	public List<Custom> getAllCostom();

}
