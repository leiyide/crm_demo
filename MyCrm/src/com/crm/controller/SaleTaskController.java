package com.crm.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.crm.entity.ConsultRecord;
import com.crm.entity.Custom;
import com.crm.entity.CustomInfo;
import com.crm.serviceDao.SaleTaskService;
import com.crm.utils.ExcelUtils;
import com.crm.utils.JsonMapper;

@Controller
public class SaleTaskController {

	//进行service层d注入
	@Resource(name="saleTaskService")
	public SaleTaskService saleTaskService;

	/**
	 * 查询所有的tmk员工
	 * @return
	 */
	@RequestMapping("/getTmk")
	@ResponseBody //将map集合转为json对象
	public Object getTmk(int offset, int limit) {
		int currentPage=offset/limit+1;
		Map<String, Object> selectAllEmployee = saleTaskService.getTmk(currentPage, limit);
		return selectAllEmployee;
	}

	/**
	 * 增加客户
	 * @param custom  接收前端 页面传进来的对象
	 * @return
	 */
	@RequestMapping("/addCustom")
	public ModelAndView addCustom(Custom custom) {
		//new 一个ModelAndView
		ModelAndView mav=new ModelAndView();
		try {
			//得到response对象
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			//设置编码格式
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			//添加增加的信息
			boolean addCutom = saleTaskService.addCutom(custom);
			if(addCutom==false) {
				out.print("<script language=\"javascript\">alert('增加失败!请重新进行输入');window.location.href='addemployee.jsp'</script>");
			}
			else {
				out.print("<script language=\"javascript\">alert('增加成功');window.location.href='index.jsp'</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping("/selectCustom")
	@ResponseBody
	public Object selectCustom(int offset, int limit,String name,String phoneno,String invitename,String createdate) {
		int currentPage=offset/limit+1;
		Map<String, Object> selectAllEmployee = saleTaskService.selectCustom(currentPage, limit, name, phoneno, invitename, createdate);
		return selectAllEmployee;
	}

	/**
	 * 通过主键进行查询
	 * @param id  客户表对应的主键
	 * @return
	 */
	@RequestMapping("/selectByPKey")
	@ResponseBody
	public Custom selectByPKey(Integer id) {
		return saleTaskService.selectByPrimaryKey(id);
	}

	/**
	 * 修改 客户的信息
	 */
	@RequestMapping("/editCustom")
	@ResponseBody  //转为json数据
	public String editCustom(String custom) {
		//此处将json转为对象
		JsonMapper json=new JsonMapper();
		Custom fromJson = json.fromJson(custom, Custom.class);
		saleTaskService.editCustom(fromJson);
		return "1";
	}

	/**
	 * 销售助理分配任务给电销员工
	 * @param tmkId 获得选中的数据对应id
	 * @return
	 */
	@RequestMapping("/fenpeiTask")
	@ResponseBody
	public String fenpeiTask(@RequestParam("tmkId[]")Integer[] tmkId) {
		//得到所有客户信息的条数
		int allNum = saleTaskService.getAllNum();
		int length = tmkId.length;
		int a=allNum/length;
		int b=allNum%length;
		//进行数据的第一轮平均分配
		if(b==0) {
			//得到所有初始信息对象的集合
			List<Custom> pristineCustom = saleTaskService.getPristineCustom();
			int i=0;
			for (Custom custom : pristineCustom) {
				for(;i<length;) {
					//创建咨询跟踪表对象
					CustomInfo customInfo=new CustomInfo();
					customInfo.setCustomid(custom.getId());
					customInfo.setFollowmanid(tmkId[i]);
					customInfo.setStatu(custom.getCustomstatu());
					customInfo.setStartdate(new Date());
					//将数据插入到咨询跟踪表中
					saleTaskService.insertCustomInfo(customInfo);
					//修改初始状态
					saleTaskService.updateCustom(custom.getId());
					break;
				}
				i=i+1;
				if(i==length) {
					i=0;
				}
			}
			//不能平均分配
		}else if(b!=0){
			//得到所有初始信息对象的集合
			List<Custom> pristineCustom = saleTaskService.getPristineCustom();
			int m=0;
			//记录次数
			int k=0;
			//跳出总循环
			int h=0;
			for (Custom custom : pristineCustom) {
				//随机生成的
				if(allNum-(k*length)==b) {
					//生成一个随机数
					Random random=new Random();
					int j;
					j=random.nextInt(length);
					//创建咨询跟踪表对象
					CustomInfo customInfo=new CustomInfo();
					customInfo.setCustomid(custom.getId());
					customInfo.setFollowmanid(tmkId[j]);
					customInfo.setStatu(custom.getCustomstatu());
					customInfo.setStartdate(new Date());
					//将数据插入到咨询跟踪表中
					saleTaskService.insertCustomInfo(customInfo);
					//修改初始状态
					saleTaskService.updateCustom(custom.getId());
					continue;
				}	
				for(;m<length;) {
					//创建咨询跟踪表对象
					CustomInfo customInfo=new CustomInfo();
					customInfo.setCustomid(custom.getId());
					customInfo.setFollowmanid(tmkId[m]);
					customInfo.setStatu(custom.getCustomstatu());
					customInfo.setStartdate(new Date());
					//将数据插入到咨询跟踪表中
					saleTaskService.insertCustomInfo(customInfo);
					//修改初始状态
					saleTaskService.updateCustom(custom.getId());
					break;
				}
				m=m+1;
				if(m==length) {
					m=0;
					k=k+1;
				}
			}
		}
		return "1";
	}

	/**
	 * 查询所有的咨询师员工
	 * @return
	 */
	@RequestMapping("/getConsult")
	@ResponseBody //将map集合转为json对象
	public Object getConsult(int offset, int limit) {
		int currentPage=offset/limit+1;
		Map<String, Object> selectAllEmployee = saleTaskService.getConsult(currentPage, limit);
		return selectAllEmployee;
	}
	/**
	 * 销售助理分配任务给咨询师
	 * @param tmkId  获得选中数据对应的id
	 * @return
	 */
	@RequestMapping("/fenpeiTaskConsult")
	@ResponseBody
	public String fenpeiTaskConsult(@RequestParam("consultId[]")Integer[] consultId) {
		//得到所有客户信息的条数
		int allNum = saleTaskService.getConsultNum();
		int length = consultId.length;
		int a=allNum/length;
		int b=allNum%length;
		//进行数据的第一轮平均分配
		if(b==0) {
			//得到所有初始信息对象的集合
			List<Custom> pristineCustom = saleTaskService.getConsultCustom();
			int i=0;
			for (Custom custom : pristineCustom) {
				for(;i<length;) {
					//创建咨询师跟踪表对象
					ConsultRecord consultRecord=new ConsultRecord();
					consultRecord.setCustomid(custom.getId());
					consultRecord.setConsultmanid(consultId[i]);
					consultRecord.setConsultstatu(custom.getCustomstatu());
					consultRecord.setConsultdate(new Date());
					//将数据插入到咨询师跟踪表中
					saleTaskService.insertConsultRecord(consultRecord);
					//修改初始状态
					saleTaskService.updateCustom2(custom.getId());
					break;
				}
				i=i+1;
				if(i==length) {
					i=0;
				}
			}
			//不能平均分配
		}else if(b!=0){
			//得到所有初始信息对象的集合
			List<Custom> pristineCustom = saleTaskService.getConsultCustom();
			int m=0;
			//记录次数
			int k=0;
			//跳出总循环
			int h=0;
			for (Custom custom : pristineCustom) {
				//生成一个随机数
				Random random=new Random();
				int j;
				j=random.nextInt(length);
				if(allNum-(k*length)==b) {
					//创建咨询师跟踪表对象
					ConsultRecord consultRecord=new ConsultRecord();
					consultRecord.setCustomid(custom.getId());
					consultRecord.setConsultmanid(consultId[j]);
					consultRecord.setConsultstatu(custom.getCustomstatu());
					consultRecord.setConsultdate(new Date());
					//将数据插入到咨询师跟踪表中
					saleTaskService.insertConsultRecord(consultRecord);
					//修改初始状态
					saleTaskService.updateCustom2(custom.getId());
					continue;
				}
				for(;m<length;) {
					//创建咨询跟踪表对象
					//创建咨询师跟踪表对象
					ConsultRecord consultRecord=new ConsultRecord();
					consultRecord.setCustomid(custom.getId());
					consultRecord.setConsultmanid(consultId[m]);
					consultRecord.setConsultstatu(custom.getCustomstatu());
					consultRecord.setConsultdate(new Date());
					//将数据插入到咨询师跟踪表中
					saleTaskService.insertConsultRecord(consultRecord);
					//修改初始状态
					saleTaskService.updateCustom2(custom.getId());
					break;
				}
				m=m+1;
				if(m==length) {
					m=0;
					k=k+1;
				}
			}
		}
		return "1";
	}

	/**
	 * 查询得到所有的数据，进行数据的导出
	 * @return
	 */
	@RequestMapping("/getAllCostom")
	@ResponseBody
	public void getAllCostom(){
		//得到response对象
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		try {
			List<Custom> prjList = new ArrayList<Custom>();
			//得到所有的客户数据
			List<Custom> allCostom = saleTaskService.getAllCostom();
			for (Custom custom : allCostom) {
				prjList.add(custom);
			}
			//将数据进行导出
			ExcelUtils.downloadExcelFile(prjList,response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将excel表中的数据插入到数据库中
	 * @param filename  前端传过来的文件名
	 */
	@RequestMapping(value="/getInsert",method = RequestMethod.POST)
	public String getInsert(MultipartFile myFile) {
		try {
			String fileOriginalName = myFile.getOriginalFilename();
			InputStream input=myFile.getInputStream();
			Workbook workbook = ExcelUtils.getWorkbook(input,fileOriginalName);
			// 拿到第一个sheet
			Sheet sheetAt = workbook.getSheetAt(0);
			// 循环行，从0开始
			for (int i = 1; i < sheetAt.getLastRowNum(); i++)
			{
				Custom custom=new Custom();
				Row row = sheetAt.getRow(i);
				custom.setName(ExcelUtils.getCellValue(row.getCell(1)));
				custom.setEducation(ExcelUtils.getCellValue(row.getCell(2)));
				custom.setPhoneno(ExcelUtils.getCellValue(row.getCell(3)));
				custom.setQq(Integer.parseInt(ExcelUtils.getCellValue(row.getCell(4))));
				custom.setEmail(ExcelUtils.getCellValue(row.getCell(5)));
				custom.setCustomstatu(ExcelUtils.getCellValue(row.getCell(6)));
				SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd");
				//定义日期格式
				String str = sdf.format(new Date());
				custom.setCreatedate(sdf.parse(str));
				custom.setInvitename(ExcelUtils.getCellValue(row.getCell(8)));
				//将数据插入到数据库中
				saleTaskService.addCutom(custom);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/costomerlist.jsp";
	}
}
