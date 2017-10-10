package com.third.facade.testdata.builder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {
	private final static String path = "./data/";

	public static List<String[]> readFile(final String fileName,
			final int arrayLength)
	{
		List<String[]> results = new ArrayList<String[]>();

		try
		{
			// Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(path + fileName);
            
			// Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx"))
			{
				workbook = new XSSFWorkbook(fis);
			} else if (fileName.toLowerCase().endsWith("xls"))
			{
				workbook = new HSSFWorkbook(fis);
			}

			// Get the nth sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0);

			// every sheet has rows, iterate over them
			Iterator<Row> rowIterator = sheet.iterator();

			int j = 0;
			while (rowIterator.hasNext())
			{
				if(j==0)
				{
					j++;
					continue;
				}
				
				String[] rowData = new String[arrayLength];
				Row row = rowIterator.next();

				// Every row has columns, get the column iterator and iterate
				// over them
				Iterator<Cell> cellIterator = row.cellIterator();
				int i = 0;
				while (cellIterator.hasNext() && i < arrayLength)
				{
					// Get the Cell object
					Cell cell = cellIterator.next();

					// check the cell type and process accordingly
					switch (cell.getCellType())
					{
					case Cell.CELL_TYPE_STRING:
						rowData[i] = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						rowData[i] = Double
								.toString(cell.getNumericCellValue());
					}

					i++;
				} 
				
				results.add(rowData);
			} 

			fis.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		results.remove(0);
		
		return results;

	}

}
