package test.com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class ExcelProcess {
	public static Object[][] processExcel(String filePath, int sheetId) throws IOException {
		
		//数据流读入excel
		File file = new File(System.getProperty("user.dir") + filePath);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		
		//读取特定表单并计算行列数
		HSSFSheet sheet = wb.getSheetAt(sheetId);
		int numberOfRow = sheet.getPhysicalNumberOfRows();
		int numberOfCell = sheet.getRow(0).getLastCellNum();
		
		//处理表单数据
		Object[][] dttData = new Object[numberOfRow][numberOfCell];
		for(int i=0; i<numberOfRow; i++){
			if(null == sheet.getRow(i) || "".equals(sheet.getRow(i))) {
				continue;
			}
			for(int j=0; j<numberOfCell; j++) {
				 if(null==sheet.getRow(i).getCell(j)||"".equals(sheet.getRow(i).getCell(j))){
					 continue;
				 }
				 HSSFCell cell = sheet.getRow(i).getCell(j);
				 cell.setCellType(CellType.STRING);
				 dttData[i][j] = cell.getStringCellValue();
			}
		}
		
		return dttData;
	}
}
