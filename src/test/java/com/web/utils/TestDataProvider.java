package com.web.utils;

import org.testng.annotations.DataProvider;

import CommonLibs.utils.ExcelDataDriven;

public class TestDataProvider {
	@DataProvider
	public Object[][] getDataFromExcel() throws Exception {
		String excelFileName = System.getProperty("user.dir")+ "/TestDataInputFile/TestData.xlsx";
		String sheetName = "Sheet1";
		ExcelDataDriven excelDriver = new ExcelDataDriven();
		excelDriver.openWorkbook(excelFileName);
		Object[][] data;
		int rowCount = excelDriver.getRowCount(sheetName);
		int cellCount = excelDriver.cellCountFromARow(sheetName, 1);
		data = new Object[rowCount+1][cellCount];
		
		for (int row=0;row<=rowCount;row++) {
			for(int cell=0;cell<cellCount;cell++) {
				data[row][cell] = excelDriver.getCellData(sheetName, row, cell);
				
			}
		}
		return data;
				
		
		
	}

}
