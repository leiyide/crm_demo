package com.crm.utils;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crm.entity.Custom;


/**
 * poi解析Excel工具
 * @author oracle
 */
public class ExcelUtils
{
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
//	public static String NO_DEFINE = "no_define";// 未定义的字段
//	public static String DEFAULT_DATE_PATTERN = "yyyy年MM月dd日";// 默认日期格式
//	public static int DEFAULT_COLOUMN_WIDTH = 17;
	public static Logger log = Logger.getLogger(ExcelUtils.class);

	/**
	 * 获取Workbook
	 * @param fileName 文件名
	 * @return Workbook
	 * @throws Exception 异常
	 */
	public static Workbook getWorkbook(InputStream input,String fileName) throws Exception
	{
		Workbook wb = null;
		// 获取文件后缀名
		String filePostFixName = getPostfix(fileName);
		// 根据文件格式(2003或者2007)来初始化
		if (filePostFixName.equals(OFFICE_EXCEL_2010_POSTFIX))
		{
			wb = new XSSFWorkbook(input);
		}
		else if (filePostFixName.equals(OFFICE_EXCEL_2003_POSTFIX))
		{
			wb = new HSSFWorkbook(input);
		}
		return wb;
	}

	@SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell)
	{
		String cellValue = "";
		// 数据格式
		DataFormatter formatter = new DataFormatter();
		if (cell != null)
		{
			// 单元格类型
			switch (cell.getCellType())
			{
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					// 取值
					cellValue = formatter.formatCellValue(cell);
				}
				else
				{
					// 数字
					double value = cell.getNumericCellValue();
					int intValue = (int) value;
					cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}

	/**
	 * 获取文件后缀名
	 * @param path 文件路径
	 * @return 文件后缀名
	 */
	public static String getPostfix(String path)
	{
		if (path == null)
		{
			return "";
		}
		if (path.contains("."))
		{
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
		return "";
	}

	/**
	 * 导出Excel 2007 OOXML (.xlsx)格式
	 * @param title 标题行
	 * @param headMap 属性-列头
	 * @param jsonArray 数据集
	 * @param datePattern 日期格式，传null值则默认 年月日
	 * @param colWidth 列宽 默认 至少17个字节
	 * @param out 输出流
	 */
	@SuppressWarnings("deprecation")
	public static void exportExcelX(OutputStream out, List<Custom> prjList)
	{
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 表头样式
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Font titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBoldweight((short) 700);
		titleStyle.setFont(titleFont);
		// 列头样式
		CellStyle headerStyle = workbook.createCellStyle();
		// headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setFont(headerFont);
		// 单元格样式
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font cellFont = workbook.createFont();
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellStyle.setFont(cellFont);
		// 生成一个(带标题)表格
		XSSFSheet sheet = workbook.createSheet("用户基础信息表");
		// 行
		XSSFRow titleRow = sheet.createRow(0);
		XSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue("客户编号");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(1);
		titleCell.setCellValue("客户姓名");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(2);
		titleCell.setCellValue("教育水平");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(3);
		titleCell.setCellValue("手机");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(4);
		titleCell.setCellValue("qq");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(5);
		titleCell.setCellValue("邮箱");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(6);
		titleCell.setCellValue("客户状态");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(7);
		titleCell.setCellValue("创建日期");
		titleCell.setCellStyle(headerStyle);
		titleCell = titleRow.createCell(8);
		titleCell.setCellValue("邀请人姓名");
		titleCell.setCellStyle(headerStyle);
		// 遍历集合数据，产生数据行
		if (prjList != null && prjList.size() > 0)
		{
			for (int i = 0; i < prjList.size(); i++)
			{
				// 数据行
				XSSFRow dataRow = sheet.createRow(i + 1);
				// 拿到一条数据，存放在本次创建的行中
				Custom prj = prjList.get(i);
				// 列
				XSSFCell newCell = dataRow.createCell(0);
				newCell.setCellValue(prj.getId());
				newCell.setCellStyle(cellStyle);

				newCell = dataRow.createCell(1);
				newCell.setCellValue(prj.getName());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(2);
				newCell.setCellValue(prj.getEducation());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(3);
				newCell.setCellValue(prj.getPhoneno());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(4);
				newCell.setCellValue(prj.getQq());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(5);
				newCell.setCellValue(prj.getEmail());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(6);
				newCell.setCellValue(prj.getCustomstatu());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(7);
				newCell.setCellValue(prj.getCreatedate());
				newCell.setCellStyle(cellStyle);
				
				newCell = dataRow.createCell(8);
				newCell.setCellValue(prj.getInvitename());
				newCell.setCellStyle(cellStyle);
			}
		}
		try
		{
			workbook.write(out);
			workbook.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void downloadExcelFile(List<Custom> list, HttpServletResponse response)
	{
		try
		{
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			exportExcelX(os, list);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((new Date().getTime() + ".xlsx").getBytes(), "iso-8859-1"));
			response.setContentLength(content.length);
			ServletOutputStream outputStream = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			byte[] buff = new byte[8192];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
			{
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception
	{
		OutputStream outXlsx = new FileOutputStream("E:\\aa\\bb\\custom.xlsx");
		System.out.println("正在导出xlsx....");
		List<Custom> prjList = new ArrayList<Custom>();
		Custom prj = new Custom();
		prj.setId(2017);
		prj.setName("哈哈");;
		prj.setEducation("本科");
		prj.setPhoneno("123456");
		prj.setQq(1235456);
		prj.setEmail("123456.@qq.com");
		prj.setCustomstatu("1");
		prj.setCreatedate(new Date());
		prj.setInvitename("李然");
		prjList.add(prj);
		exportExcelX(outXlsx, prjList);
		outXlsx.close();

//		log.info("解析文件开始...");
//		System.out.println("解析文件开始...");
//		String fileName = "D:\\temp\\1108.xls";
//		Workbook wb = getWorkbook(fileName);
//		// 拿到第一个sheet
//		Sheet sheet = wb.getSheetAt(0);
//		// 循环行，从0开始
//		for (int i = 1; i < 8; i++)
//		{
//			UserInfo user = new UserInfo();
//			log.info("文件行数：" + (i + 1));
//			// 行
//			Row row = sheet.getRow(i);
//			user.setUserId(getCellValue(row.getCell(0)));
//			user.setUserName(getCellValue(row.getCell(1)));
//
//			// 循环列，从0开始
//			for (int j = 0; j < 8; j++)
//			{
//				if (row != null)
//				{
//					String cellValue = getCellValue(row.getCell(j));
//					// System.out.println(cellValue);
//					System.out.println("第" + (i + 1) + "行第" + (j + 1) + "列的值：" + cellValue);
//				}
//			}
//		}
//		log.info("解析文件结束.");
//		wb.close();
}

}
