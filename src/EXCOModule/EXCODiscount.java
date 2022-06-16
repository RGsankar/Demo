package EXCOModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class EXCODiscount extends SeMethods{
	@Test
	public void EXCOpayment() throws IOException, InterruptedException {

// login Page

		loginpage();
// IPLC Module

		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Export Collection");
		click(Import);
		log4.info("Export Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "EXCO Settlement");
		click(Function);
		log5.info("EXCO Settlement");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300315F05030701932");
		click(FunctionGroup);
		log6.info("Discound");
// Catalog
		EXCOCatalog();
		acceptAlert();
// Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\EXCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Discound");
		workbook.close();
// MAin
		acceptAlert();
// Collecting Bank Reference
		Logger log051 = Logger.getLogger("Collecting Bank Reference");
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(2);
		String invalid = cell.getStringCellValue();
		if (invalid == "" || invalid.isEmpty()) {
			WebElement Narrative = locateElement("name", "COLL_BK_REF");
			type(Narrative, invalid);
			log051.info(Narrative.getAttribute("value"));
		} else {
			WebElement Collecting = locateElement("name", "COLL_BK_REF");
			Clear(Collecting);
			WebElement Narrative = locateElement("name", "COLL_BK_REF");
			type(Narrative, invalid);
			log051.info(Narrative.getAttribute("value"));
		}
// Drawer Reference
		Logger log0511 = Logger.getLogger("Drawer Reference");
		Row row1 = sheet.getRow(3);
		Cell cell1 = row1.getCell(4);
		String invalid1 = cell1.getStringCellValue();
		if (invalid1 == "" || invalid1.isEmpty()) {
			WebElement Narrative = locateElement("name", "CUST_REF");
			type(Narrative, invalid1);
			log0511.info(Narrative.getAttribute("value"));
		} else {
			WebElement Collecting = locateElement("name", "CUST_REF");
			Clear(Collecting);
			WebElement Narrative = locateElement("name", "CUST_REF");
			type(Narrative, invalid1);
			log0511.info(Narrative.getAttribute("value"));
		}
// Settlement
		WebElement Settlement = locateElement("id", "E");
		click(Settlement);
// Take charges separately?
		try {
			Logger log101 = Logger.getLogger("Take charges separately");
			Row row1111 = sheet.getRow(3);
			Cell cell1111 = row1111.getCell(6);
			String Role1 = cell1111.getStringCellValue();
			WebElement Our = locateElement("name", "STL_INSTR_FLG");
			selectDropDownUsingText(Our, Role1);
			log101.info(Our.getAttribute("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// Charges
		WebElement Charges2 = locateElement("id", "D");
		click(Charges2);
// Paid By
		Logger log0611 = Logger.getLogger("Paid By");
		Row row0611 = sheet.getRow(5);
		Cell cell0611 = row0611.getCell(2);
		String Paid = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111, Paid);
		log0611.info(Method1111.getAttribute("value"));
		// PaidAt
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(5);
		Cell cell07 = row07.getCell(4);
		String PaidAt = cell07.getStringCellValue();
		switch (PaidAt) {
		case "TRANSACTION":
			WebElement Method3 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3, PaidAt);
			log07.info(Method3.getAttribute("value"));
			Row row11111 = sheet.getRow(5);
			Cell cell11111 = row11111.getCell(6);
			int Amount11 = (int) cell11111.getNumericCellValue();
			if ((Integer.toString(Amount11) == null) || (Amount11 == 0)) {
				WebElement Our111 = locateElement("name", "CHG_GETAC_BTN");
				click(Our111);
				Set<String> window = driver.getWindowHandles();
				Iterator<String> itererator2 = window.iterator();
				String mainWin = itererator2.next();
				String newAdwin = itererator2.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
				switchToFramest("work");
			} else {
				Logger log09 = Logger.getLogger("Paid At");
				WebElement Method6 = locateElement("name", "CHG_FLD_LOCAL_CUST_AC_NO");
				type(Method6, "763915060");
				log09.info(Method6.getAttribute("value"));
			}
			break;
		case "DEFERRED":
		case "WAIVED":
			WebElement Method31 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method31, PaidAt);
			log07.info(Method31.getAttribute("value"));
			break;
		}
		
//Discount
		WebElement Discound = locateElement("id", "W");
		click(Discound);
//Finance Percentage
		Logger log8111 = Logger.getLogger("Finance Percentage");
		Row row2111 = sheet.getRow(11);
		Cell cell2111 = row2111.getCell(6);
		int TeNor = (int) cell2111.getNumericCellValue();
		WebElement Financia11 = locateElement("id", "CFNC_N_PCT");
		Clear(Financia11);
		WebElement ele8111 = locateElement("id", "CFNC_N_PCT");
		type(ele8111, Integer.toString(TeNor));
		log8111.info(ele8111.getAttribute("value"));
//End Date
		Logger log8 = Logger.getLogger("End Date");
		Row row111 = sheet.getRow(7);
		Cell cell111 = row111.getCell(2);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("name", "CFNC_D_DUE_DT");
		type(ele1, formatter1.format(numberAsString1));
		log8.info(ele1.getAttribute("value"));
//Grace Days
		Logger log9111 = Logger.getLogger("Grace Days");
		Row row311 = sheet.getRow(7);
		Cell cell311 = row311.getCell(4);
		String Month1 = cell311.getStringCellValue();
		WebElement Financia1 = locateElement("name", "CFNC_C_GRACE_FLG");
		selectDropDownUsingText(Financia1, Month1);
		log9111.info(Financia1.getAttribute("value"));
		
		Logger log81111 = Logger.getLogger("Grace Days");
		Row row21111 = sheet.getRow(7);
		Cell cell21111 = row21111.getCell(6);
		int TeNor1 = (int) cell21111.getNumericCellValue();
		WebElement Financia111 = locateElement("id", "CFNC_C_GRACE_DAYS");
		Clear(Financia111);
		WebElement ele81111 = locateElement("id", "CFNC_C_GRACE_DAYS");
		type(ele81111, Integer.toString(TeNor1));
		log81111.info(ele81111.getAttribute("value"));
//INC/EXC
		Logger log11 = Logger.getLogger("Hold docs until arrival of goods");
		Row row52 = sheet.getRow(9);
		Cell cell3311 = row52.getCell(2);
		int TeNor11 = (int) cell3311.getNumericCellValue();
		
		WebElement Our111111 = locateElement("name","INC_EXC");
		selectDropDownUsingText(Our111111, Integer.toString(TeNor11));
		log11.info(Our111111.getAttribute("value"));	
		
//Interest Calculation Type		
		Logger log10111 = Logger.getLogger("Store and Insure Goods");
		Row row33 = sheet.getRow(9);
		Cell cell33 = row33.getCell(4);
		String Charges12 = cell33.getStringCellValue();
		WebElement Our1111 = locateElement("name","CFNC_C_INT_MODE");
		selectDropDownUsingText(Our1111,Charges12);
		log10111.info(Our1111.getAttribute("value"));
//Interest Days Basis
		Logger log101 = Logger.getLogger("Interest Days Basis");
		Row row131 = sheet.getRow(9);
		Cell cell131 = row131.getCell(6);
		String Charges = cell131.getStringCellValue();
		WebElement Our1 = locateElement("name","CFNC_I_BASIC_DAYS");
		selectDropDownUsingText(Our1,Charges);
		log101.info(Our1.getAttribute("value"));
//Base Rate	
		Logger log811 = Logger.getLogger("LC_AMT");
		Row row211 = sheet.getRow(11);
		Cell cell211 = row211.getCell(2);
		double Amount = cell211.getNumericCellValue();
		WebElement ele = locateElement("xpath", ".//*[@id='CFNC_N_LIBOR_RT']");
		click(ele);
		WebElement ele811 = locateElement("xpath", ".//*[@id='CFNC_N_LIBOR_RT']");
		type(ele811, Double.toString(Amount));
		log811.info(ele811.getAttribute("value"));
//Margin
		Logger log81 = Logger.getLogger("LC_AMT");
		Row row21 = sheet.getRow(11);
		Cell cell2 = row21.getCell(4);
		double Amount1 = cell2.getNumericCellValue();
		WebElement ele16 = locateElement("xpath", ".//*[@id='CFNC_N_MARGIN_RT']");
		click(ele16);
		WebElement ele83 = locateElement("xpath", ".//*[@id='CFNC_N_MARGIN_RT']");
		type(ele83, Double.toString(Amount1));
		log81.info(ele83.getAttribute("value"));
// Payment
		WebElement Payment = locateElement("id", "F");
		click(Payment);
		Payment();
// Advice

		WebElement Advice = locateElement("id", "G");
		click(Advice);
		Advice();
// Notes
		WebElement Notes = locateElement("id", "H");
		click(Notes);
		Notes();
// Diary
		WebElement Diary = locateElement("id", "I");
		click(Diary);
		Diary();
// confirm

		Confirm();
// supervisor Release
		EXCOSupervisorRelease();

}
}