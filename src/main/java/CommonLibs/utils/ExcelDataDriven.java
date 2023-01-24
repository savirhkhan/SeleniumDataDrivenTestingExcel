package CommonLibs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import net.bytebuddy.build.Plugin.Engine.Dispatcher.ForParallelTransformation.WithThrowawayExecutorService;

public class ExcelDataDriven {

	private InputStream fileReader;
	private Workbook excelWorkbok;

	public void openWorkbook(String excelFilename) throws Exception {

		excelFilename = excelFilename.trim();
		File file = new File(excelFilename);
		if (!file.exists()) {
			throw new Exception("File does not Exist");
		}
		fileReader = new FileInputStream(excelFilename);
		excelWorkbok = WorkbookFactory.create(fileReader);
	}

	public int getRowCount(String sheetname) throws Exception {

		sheetname = sheetname.trim();
		Sheet sheet = excelWorkbok.getSheet(sheetname);
		if (sheet == null) {
			throw new Exception("Invalid Sheet Name");
		}
		return sheet.getLastRowNum();
	}

	public int cellCountFromARow(String sheetname, int rowNumber) throws Exception {
		sheetname = sheetname.trim();
		Sheet sheet = excelWorkbok.getSheet(sheetname);
		if (sheet == null) {
			throw new Exception("Invalid Sheet Name");
		}
		if (rowNumber < 0) {
			throw new Exception("Invalid row Number");
		}
		Row row = sheet.getRow(rowNumber);
		if (row == null) {
			return 0;
		} else {
			return row.getLastCellNum();
		}

	}

	public String getCellData(String sheetname, int rowNumber, int cellNumber) throws Exception {
		sheetname = sheetname.trim();
		Sheet sheet = excelWorkbok.getSheet(sheetname);
		if (sheet == null) {
			throw new Exception("Invalid Sheet Name");
		}
		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid row or cell  Number");
		}
		Row row = sheet.getRow(rowNumber);
		if (row == null) {
			return "";
		}
		Cell cell = row.getCell(cellNumber);
		if (cell == null) {
			return "";
		}else {
			if(cell.getCellType() == CellType.NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			}else {
				return cell.getStringCellValue();
			}
		}
		

	}

}
