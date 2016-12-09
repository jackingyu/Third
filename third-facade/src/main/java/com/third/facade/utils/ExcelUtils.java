package com.third.facade.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelUtils
{
	/**
	 * 导出到Excel方法。
	 * 
	 * @param workbookName
	 *           工作簿名称
	 * @param sheetName
	 *           工作表名称
	 * @param headerNames
	 *           列名数组
	 * @param dataList
	 *           数据列表，列表中每个元素为一行数据，数组中的每个元素为一列所对应的数据，均为String类型
	 * @param request
	 *           HttpServletRequest
	 * @param response
	 *           HttpServletResponse
	 */
	public static void exportToExcel(String workbookName, String sheetName, String[] headerNames, List<String[]> dataList,
			HttpServletRequest request, HttpServletResponse response)
	{
		Workbook wb = new HSSFWorkbook();
		UUID uuid = UUID.randomUUID();
		String fileName = System.getProperty("java.io.tmpdir") + "/" + uuid.toString() + ".xls";
		File file = new File(fileName);
		FileOutputStream fileOut = null;
		try
		{
			fileOut = new FileOutputStream(file);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		Sheet sheet = wb.createSheet(sheetName);
		int rowIndex = 0;
		Row headerRow = sheet.createRow(rowIndex++);
		int colIndex = 0;
		for (String headerName : headerNames)
		{
			Cell headerCell = headerRow.createCell(colIndex++);
			headerCell.setCellValue(headerName);
		}

		if (dataList != null && dataList.size() > 0)
		{
			for (String[] data : dataList)
			{
				Row dataRow = sheet.createRow(rowIndex++);
				colIndex = 0;
				for (String column : data)
				{
					Cell dataCell = dataRow.createCell(colIndex++);
					dataCell.setCellValue(column);
				}
			}
		}
		try
		{
			wb.write(fileOut);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fileOut != null)
			{
				try
				{
					fileOut.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		String contentType = "application/vnd.ms-excel";
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			request.setCharacterEncoding("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		long fileLength = file.length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename=\"" + workbookName + "\"");
		response.setHeader("Content-Length", String.valueOf(fileLength));

		try
		{
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
			{
				bos.write(buff, 0, bytesRead);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (bis != null)
			{
				try
				{
					bis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (bos != null)
			{
				try
				{
					bos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (file != null && file.exists())
			{
				file.delete();
			}
		}
	}

	/**
	 * 导出内容成生excel文件
	 * 
	 * @param workbookName
	 * @param sheetName
	 * @param headerNames
	 * @param dataList
	 * @param request
	 * @param response
	 * @return
	 */
	public static String makeExcel(String workbookName, String sheetName, String[] headerNames, List<String[]> dataList,
			HttpServletRequest request, HttpServletResponse response)
	{
		Workbook wb = new HSSFWorkbook();
		UUID uuid = UUID.randomUUID();
		String fileSitePath = "/exports";// Config.getParameter("excel.export.path");
		String fileName = workbookName + "_" + uuid + ".xls";
		String filePath = request.getSession().getServletContext().getRealPath(fileSitePath) + File.separator;
		File f = new File(filePath);
		if (!f.exists())
		{
			f.mkdirs();
		}
		File file = new File(filePath + fileName);
		FileOutputStream fileOut = null;
		try
		{
			fileOut = new FileOutputStream(file);
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		Sheet sheet = wb.createSheet(sheetName);
		int rowIndex = 0;
		Row headerRow = sheet.createRow(rowIndex++);
		int colIndex = 0;
		for (String headerName : headerNames)
		{
			Cell headerCell = headerRow.createCell(colIndex++);
			headerCell.setCellValue(headerName);
		}

		if (dataList != null && dataList.size() > 0)
		{
			for (String[] data : dataList)
			{
				Row dataRow = sheet.createRow(rowIndex++);
				colIndex = 0;
				for (String column : data)
				{
					Cell dataCell = dataRow.createCell(colIndex++);
					dataCell.setCellValue(column);
				}
			}
		}
		try
		{
			wb.write(fileOut);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (fileOut != null)
			{
				try
				{
					fileOut.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return fileSitePath + "/" + fileName;
	}
}
