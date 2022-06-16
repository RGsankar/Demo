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

public class IWGTApplyRejectAmendment extends SeMethods {
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
		WebElement FunctionGroup = locateElement("name", "G49082300284F05030702097");
		click(FunctionGroup);
		log6.info("Apply/Reject Amendment");
// Frame
		switchToFramest("work");
// Catalog
		IWGTCatalog();
// Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\Module\\IWGT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("IssueAmend");
		workbook.close();
// Main
//Accept/Reject
		Logger log0611 = Logger.getLogger("Accept/Reject");
		Row row0611 = sheet.getRow(14);
		Cell cell0611 = row0611.getCell(2);
		String Accept = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "ACPT_REJ");
		selectDropDownUsingText(Method1111, Accept);
		log0611.info(Method1111.getAttribute("value"));
//Reject Reason
		Logger log22 = Logger.getLogger("Reject Reason");
		Row row1122 = sheet.getRow(14);
		Cell cell22 = row1122.getCell(4);
		String Currency11 = cell22.getStringCellValue();
		WebElement Financial11 = locateElement("name","REJ_REASON");
		type(Financial11,Currency11);
		log22.info(Financial11.getAttribute("value"));
//Sender to Receiver Information (MT787:72Z)		
		Logger Bill1 = Logger.getLogger("Sender to Receiver Information (MT787:72Z)");
		Row row191 = sheet.getRow(14);
		Cell cell191 = row191.getCell(6);
		String Bank = cell191.getStringCellValue();
		WebElement Waive = locateElement("name","SEND_TO_RCV_INFO_MT787");
		type(Waive,Bank);
		Bill1.info(Waive.getAttribute("value"));
//File Identification[23X]
		Logger log221 = Logger.getLogger("Applicable Rules");
		Row row11221 = sheet.getRow(16);
		Cell cell221 = row11221.getCell(2);
		String Currency111 = cell221.getStringCellValue();
		WebElement Financial111 = locateElement("name","FILE_23X_CODE");
		selectDropDownUsingText(Financial111,Currency111);
		log221.info(Financial111.getAttribute("value"));
//File Identification Details 
		Logger log20 = Logger.getLogger("Issued/Advised By");
		Row row13111 = sheet.getRow(16);
		Cell cell13111 = row13111.getCell(4);
		String Release = cell13111.getStringCellValue();
		WebElement Documents = locateElement("name","FILE_23X_NARR");
		type(Documents,Release);
		log20.info(Documents.getAttribute("value"));

// Advice
		WebElement Advice = locateElement("id", "D");
		click(Advice);
		Advice();
// Notes
		WebElement Notes = locateElement("id", "E");
		click(Notes);
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