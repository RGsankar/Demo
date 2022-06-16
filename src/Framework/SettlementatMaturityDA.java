package Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SettlementatMaturityDA extends SeMethods{
	@Test
	public void IMCOPayment() throws IOException, InterruptedException{	
//login page
		
		loginpage();
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Import Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IMCO Settlement");
		click(Function);
		log5.info("IMCO Settlement");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300294F05030701914");
		click(FunctionGroup);
		log6.info("Settlement at Maturity (D/A)");
	
//catalog page	
		Catalog();
// Main
// Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("PaymentDP");
		workbook.close();
// Payment Date
		Logger log8 = Logger.getLogger("Payment Date");
		Row row111 = sheet.getRow(3);
		Cell cell111 = row111.getCell(2);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("name", "PMT_DT");
		type(ele1, formatter1.format(numberAsString1));
		log8.info(ele1.getAttribute("value"));
// Settlement
		WebElement Settlement = locateElement("id", "E");
		click(Settlement);
// Take charges separately?
		try {
			Logger log10 = Logger.getLogger("Take charges separately");
			Row row11 = sheet.getRow(3);
			Cell cell11 = row11.getCell(4);
			String Role = cell11.getStringCellValue();
			WebElement Our = locateElement("name", "SEPARATE_CHG_FLG");
			selectDropDownUsingText(Our, Role);
			log10.info(Our.getAttribute("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// Charges
		WebElement Charges = locateElement("id", "D");
		click(Charges);
		Charges();
// Payment
		WebElement Payment = locateElement("id", "F");
		click(Payment);		
		Payment();
// Advice

		WebElement Advice = locateElement("id", "G");
		click(Advice);
		Advice();
//Notes
		WebElement Notes = locateElement("id", "I");
		click(Notes);
		Notes();
// Diary
		WebElement Diary = locateElement("id", "J");
		click(Diary);
		Diary();
// confirm
		
		Confirm();
// supervisor Release
		//IMCO_SupervisorRelease();
}
}