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

public class IWGTIssueAdviseAmendment extends SeMethods {
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
		WebElement FunctionGroup = locateElement("name", "G49082300284F05030702092");
		click(FunctionGroup);
		log6.info("Issue/Advise Amendment");
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
//Beneficiary Consent Required
		try {
			Logger log101111 = Logger.getLogger("Beneficiary Consent Required");
			Row row331 = sheet.getRow(3);
			Cell cell331 = row331.getCell(2);
			String Charges121 = cell331.getStringCellValue();
			WebElement Our11111 = locateElement("name","BENE_CONST_REQ");
			selectDropDownUsingText(Our11111,Charges121);
			log101111.info(Our11111.getAttribute("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		WebElement Guarantee = locateElement("name", "GTEE_TITLE");
		type(Guarantee, "Guarantee");
// Signature
		WebElement Signature = locateElement("name", "SIGNATURE");
		type(Signature, "Signature");
// Signature
		WebElement Signature1 = locateElement("name", "SIGNATURE2");
		type(Signature1, "Signature");
// Charges
		WebElement Charges2 = locateElement("id", "D");
		click(Charges2);
// Paid By
		Logger log0611 = Logger.getLogger("Paid By");
		Row row0611 = sheet.getRow(3);
		Cell cell0611 = row0611.getCell(4);
		String Paid = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111, Paid);
		log0611.info(Method1111.getAttribute("value"));
// PaidAt
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(3);
		Cell cell07 = row07.getCell(6);
		String PaidAt = cell07.getStringCellValue();
		switch (PaidAt) {
		case "TRANSACTION":
			WebElement Method3 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3, PaidAt);
			log07.info(Method3.getAttribute("value"));
			Row row11111 = sheet.getRow(5);
			Cell cell11111 = row11111.getCell(2);
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
				Thread.sleep(2000);
			}
			break;
		case "DEFERRED":
		case "WAIVED":
			WebElement Method31 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method31, PaidAt);
			log07.info(Method31.getAttribute("value"));
			break;
		}
// Acknowledgement
		Thread.sleep(2000);
		WebElement Acknowledgement = locateElement("id", "E");
		click(Acknowledgement);
// Acknowledgment (MT768)
		Logger log071 = Logger.getLogger("Acknowledgment (MT768)");
		Row row071 = sheet.getRow(5);
		Cell cell071 = row071.getCell(4);
		String PaidAt1 = cell071.getStringCellValue();
		switch (PaidAt1) {
		case "Yes":
			WebElement Method3 = locateElement("name", "SEND_MT768_FLG");
			selectDropDownUsingText(Method3, PaidAt1);
			log071.info(Method3.getAttribute("value"));
// Account identification
			Logger log10111 = Logger.getLogger("Account identification");
			Row row33 = sheet.getRow(5);
			Cell cell33 = row33.getCell(6);
			int Interest = (int) cell33.getNumericCellValue();
			if ((Integer.toString(Interest) == null) || (Interest == 0)) {
				WebElement ele811111 = locateElement("id", "ACCT_ID_MT768");
				click(ele811111);
			} else {
				WebElement ele811111 = locateElement("id", "ACCT_ID_MT768");
				type(ele811111, Integer.toString(Interest));
				log10111.info(ele811111.getAttribute("value"));
			}
// Amount of charges
			Logger log811 = Logger.getLogger("Amount of charges");
			Row row211 = sheet.getRow(7);
			Cell cell211 = row211.getCell(2);
			int Amount = (int) cell211.getNumericCellValue();
			if ((Integer.toString(Amount) == null) || (Amount == 0)) {
				WebElement ele = locateElement("xpath", ".//*[@id='CHG_AMT_MT768']");
				click(ele);
			} else {
				WebElement ele = locateElement("xpath", ".//*[@id='CHG_AMT_MT768']");
				click(ele);
				WebElement ele811 = locateElement("xpath", ".//*[@id='CHG_AMT_MT768']");
				type(ele811, Integer.toString(Amount));
				log811.info(ele811.getAttribute("value"));
			}

// Currency
			Logger log9 = Logger.getLogger("Currency");
			Row row1 = sheet.getRow(7);
			Cell cell1 = row1.getCell(4);
			String Currency = cell1.getStringCellValue();
			WebElement Financial = locateElement("name", "CHG_CCY_MT768");
			selectDropDownUsingText(Financial, Currency);
			log9.info(Financial.getAttribute("value"));
// Account with bank ID
			WebElement Drawer = locateElement("name", "AC_WT_BK_ID_BTN");
			click(Drawer);
			Set<String> windowId2 = driver.getWindowHandles();
			Iterator<String> itererator2 = windowId2.iterator();
			String mainWinID2 = itererator2.next();
			String newAdwinID2 = itererator2.next();
			driver.switchTo().window(newAdwinID2);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
			driver.switchTo().window(mainWinID2);
			System.out.println(driver.getTitle());
			// Frame
			switchToFramest("work");
// Sender to Receiver Information (72)
			WebElement Receiver = locateElement("name", "SEND_TO_RCV_INFO");
			type(Receiver, "Guarantee");
// Charges Details Instructing Bank
			WebElement Instructing = locateElement("name", "CHARGES_MAIL");
			type(Instructing, "Welcome");
// Details of Charges (71B)
			/*
			 * WebElement Charges = locateElement("name", "DET_CHG_MT768");
			 * type(Charges,"Testing Do");
			 */
// Charges Details Beneficiary
			WebElement Beneficiary = locateElement("name", "DTAILS_MAIL");
			type(Beneficiary, "Beneficiary");
			break;
		case "No":
			WebElement Method31 = locateElement("name", "SEND_MT768_FLG");
			selectDropDownUsingText(Method31, PaidAt1);
			log071.info(Method31.getAttribute("value"));
			break;
		}
// Advice
		WebElement Advice = locateElement("id", "F");
		click(Advice);
		Advice();
// Diary
		WebElement Diary = locateElement("id", "H");
		click(Diary);
		Diary();
// confirm
		Confirm();
// Supervisor Release
		IWGTSupervisorRelease();
}
}