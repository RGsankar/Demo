package InwardGuarantee;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class IWGTRegisterInwardAmendment extends SeMethods{
	@Test
	public void release() throws IOException, InterruptedException {

// login Page
		loginpage();
// IWGT Module
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Inward Guarantee");
		click(Import);
		log4.info("Inward Guarantee");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IWGT Amendment");
		click(Function);
		log5.info("IWGT Amendment");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300284F05030702089");
		click(FunctionGroup);
		log6.info("Register Inward Amendment");
// Frame
		switchToFramest("work");
// Catalog
		IWGTCatalog();
// Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\Module\\IWGT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Amend");
		workbook.close();
// Main
//Expiry Place		
		Logger log051 = Logger.getLogger("Expiry Place");
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(2);
		String invalid = cell.getStringCellValue();
		if(invalid == "" || invalid.isEmpty()){
			WebElement Reference = locateElement("name", "EXPIRY_PLC");
			click(Reference);
			log051.info(Reference.getAttribute("value"));
		}
		else{
			WebElement Reference = locateElement("name", "EXPIRY_PLC");
			Clear(Reference);
			WebElement Reference1 = locateElement("name", "EXPIRY_PLC");
			type(Reference1, invalid);
			log051.info(Reference1.getAttribute("value"));
		}
			
//Increase Amount
		Logger log811 = Logger.getLogger("Increase Amount");
		Row row211 = sheet.getRow(3);
		Cell cell211 = row211.getCell(4);
		double Amount = cell211.getNumericCellValue();
		if(Amount == 0 || (Double.toString(Amount) == null) ){
			WebElement ele = locateElement("xpath", ".//*[@id='INC_AMT']");
			click(ele);
			log811.info(ele.getAttribute("value"));
		}
		
		else {
			WebElement ele = locateElement("xpath", ".//*[@id='INC_AMT']");
			click(ele);
			WebElement ele811 = locateElement("xpath", ".//*[@id='INC_AMT']");
			type(ele811, Double.toString(Amount));
			log811.info(ele811.getAttribute("value"));
		}
//Decrease Amount	
		Logger log8 = Logger.getLogger("Decrease Amount");
		Row row1 = sheet.getRow(3);
		Cell cell2 = row1.getCell(6);
		double Amount1 = cell2.getNumericCellValue();
		if(Amount1 == 0 || (Double.toString(Amount1) == null) ){
			WebElement ele1 = locateElement("xpath", ".//*[@id='DEC_AMT']");
			click(ele1);
			log8.info(ele1.getAttribute("value"));
		}
		else{
			WebElement ele1 = locateElement("xpath", ".//*[@id='DEC_AMT']");
			click(ele1);
			WebElement ele8 = locateElement("xpath", ".//*[@id='DEC_AMT']");
			type(ele8, Double.toString(Amount1));
			log8.info(ele8.getAttribute("value"));
		}
// Parties
		WebElement Parties = locateElement("id", "B");
		click(Parties);
// Corresp Medium
		WebElement Medium = locateElement("name", "ISSUE_BK_CORR_MED");
		type(Medium, "SWIFT");
// Details
		WebElement Details = locateElement("id", "C");
		click(Details);
// Guarantee Title
		WebElement Guarantee = locateElement("name", "AMD_NON_STD_WORDNG");
		type(Guarantee, "Guarantee");
//Sender to Receiver Information (MT760:72)
	    WebElement Sender = locateElement("name", "X767_BKTOBK_INFO72");
		type(Sender, "Welcome");
// Advice

		WebElement Advice = locateElement("id", "D");
		click(Advice);
		Advice();
// Note
		WebElement Note = locateElement("id", "E");
		click(Note);
		Notes();
// Diary
		WebElement Diary = locateElement("id", "F");
		click(Diary);
		Diary();
// confirm
		Confirm();
// Supervisor Release
		IWGTSupervisorRelease();
}
}