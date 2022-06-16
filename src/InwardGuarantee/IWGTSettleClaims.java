package InwardGuarantee;

import java.io.FileInputStream;
import java.io.IOException;
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

public class IWGTSettleClaims extends SeMethods{
	@Test
	public void RegisterClaim() throws IOException, InterruptedException {

// login Page
		loginpage();
// IWGT Module
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Inward Guarantee");
		click(Import);
		log4.info("Inward Guarantee");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IWGT Claims");
		click(Function);
		log5.info("IWGT Claims");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300308F05030702111");
		click(FunctionGroup);
		log6.info("Amend Claim");
		// Frame
		switchToFramest("work");
// Catalog
		IWGTCatalog();
// Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\Module\\IWGT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("IWGTClaims");
		workbook.close();
// Main
// Settlement
		WebElement Settlement = locateElement("id", "D");
		click(Settlement);
// Take charges separately?
		try {
			Logger log101 = Logger.getLogger("Take charges separately");
			Row row1111 = sheet.getRow(20);
			Cell cell1111 = row1111.getCell(2);
			String Role1 = cell1111.getStringCellValue();
			WebElement Our = locateElement("name", "SEPARATE_CHG_FLG");
			selectDropDownUsingText(Our, Role1);
			log101.info(Our.getAttribute("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// Charges
		WebElement Charges2 = locateElement("id", "J");
		click(Charges2);
// Paid By
		Logger log0611 = Logger.getLogger("Paid By");
		Row row0611 = sheet.getRow(18);
		Cell cell0611 = row0611.getCell(2);
		String Paid = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111, Paid);
		log0611.info(Method1111.getAttribute("value"));
// PaidAt
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(18);
		Cell cell07 = row07.getCell(4);
		String PaidAt = cell07.getStringCellValue();
		switch (PaidAt) {
		case "TRANSACTION":
			WebElement Method3 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3, PaidAt);
			log07.info(Method3.getAttribute("value"));
			Row row11111 = sheet.getRow(18);
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
// Payment
		WebElement Payment = locateElement("id", "H");
		click(Payment);
		Payment();
// Advice

		WebElement Advice = locateElement("id", "E");
		click(Advice);
		Advice();
// Notes
		WebElement Notes = locateElement("id", "F");
		click(Notes);
		Notes();
// Diary
		WebElement Diary = locateElement("id", "G");
		click(Diary);
		Diary();
// confirm

		Confirm();
// supervisor Release
		IWGTSupervisorRelease();

}
}