package EXCOModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class EXCOAcceptance extends SeMethods{
	@Test
	public void EXCOAccept () throws IOException, InterruptedException{		
		
//login Page
		
		loginpage();
//IPLC Module		
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import  = locateElement("name", "Export Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "EXCO Acceptance");
		click(Function);
		log5.info("EXCO Acceptance");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300252F05030701931");
		click(FunctionGroup);
		log6.info("Amend/Discharge");
//Catalog
		EXCOCatalog();
		
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\EXCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Accept");
		workbook.close();  
//MAin
//Collecting Bank Reference
		Logger log051 = Logger.getLogger("Collecting Bank Reference");
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(2);
		String invalid = cell.getStringCellValue();
		if(invalid == "" || invalid.isEmpty() ){
			WebElement Narrative = locateElement("name", "COLL_BK_REF");
			type(Narrative, invalid);
			log051.info(Narrative.getAttribute("value"));
		}
		else{
			WebElement Collecting = locateElement("name", "COLL_BK_REF");
			Clear(Collecting);
			WebElement Narrative = locateElement("name", "COLL_BK_REF");
			type(Narrative, invalid);
			log051.info(Narrative.getAttribute("value"));
		}
//Drawer Reference	
		Logger log10 = Logger.getLogger("Drawer Reference");
		Row row11 = sheet.getRow(3);
		Cell cell11 = row11.getCell(4);
		String Role = cell11.getStringCellValue();
		if(Role == "" || Role.isEmpty() ){
			WebElement Our1 = locateElement("name", "CUST_REF");
			type(Our1, Role);
			log10.info(Our1.getAttribute("value"));
		}
		else{
			WebElement Our = locateElement("name", "CUST_REF");
			Clear(Our);
			WebElement Our1 = locateElement("name", "CUST_REF");
			type(Our1, Role);
			log10.info(Our1.getAttribute("value"));
		}
//Acceptance Date
		Logger log71 = Logger.getLogger("Acceptance Date");
		Row row111 = sheet.getRow(3);
		Cell cell111 = row111.getCell(6);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		driver.findElement(By.name("ACPT_DT")).clear();
		WebElement ele11 = locateElement("name","ACPT_DT");
		type(ele11,formatter1.format(numberAsString1));
		log71.info(ele11.getAttribute("value"));
//Narrative
		WebElement ele88 = locateElement("id", "C");
		click(ele88);
				
//Sender To Receiver Instructions(MT412 Tag 72)
		WebElement Receiver = locateElement("id", "BK_TO_BK_INFO");
		type(Receiver,"Welcome");
// Advice

		WebElement Advice = locateElement("id", "D");
		click(Advice);
		Advice();
// Diary
		WebElement Diary = locateElement("id", "F");
		click(Diary);
		Diary();
// confirm
		Confirm();
// supervisor Release
		EXCOSupervisorRelease();
	}
}
