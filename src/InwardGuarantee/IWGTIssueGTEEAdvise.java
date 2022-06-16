package InwardGuarantee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class IWGTIssueGTEEAdvise extends SeMethods{
	@Test
	public void EXCOREG () throws IOException, InterruptedException{		
		
//login Page
		
		loginpage();
//IPLC Module		
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import  = locateElement("name", "Inward Guarantee");
		click(Import);
		log4.info("Inward Guarantee");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IWGT Issuance");
		click(Function);
		log5.info("IWGT Issuance");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300279F05030703937");
		click(FunctionGroup);
		log6.info("IssueGTEE Advise");
//Frame 
		 switchToFramest("work");
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\Module\\IWGT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("IssueGTEEAdvise");
		workbook.close();  

//Main		
//Guarantee Ref. No.
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(2);
		String invalid = cell.getStringCellValue();
		Logger log051 = Logger.getLogger("Guarantee Ref. No.");
		WebElement Reference = locateElement("name", "GTEE_REF_NUM");
		type(Reference, invalid);
		log051.info(Reference.getAttribute("value"));
//Guarantee Amount
		Logger log9 = Logger.getLogger("Guarantee Amount");
		Row row1 = sheet.getRow(3);
		Cell cell1 = row1.getCell(4);
		String Currency = cell1.getStringCellValue();
		WebElement Financial = locateElement("name", "GTEE_CCY");
		selectDropDownUsingText(Financial, Currency);
		log9.info(Financial.getAttribute("value"));
// Amount
		Logger log811 = Logger.getLogger("LC_AMT");
		Row row211 = sheet.getRow(3);
		Cell cell211 = row211.getCell(6);
		double Amount = cell211.getNumericCellValue();
		WebElement ele = locateElement("xpath", ".//*[@id='GTEE_AMT']");
		click(ele);
		WebElement ele811 = locateElement("xpath", ".//*[@id='GTEE_AMT']");
		type(ele811, Double.toString(Amount));
		log811.info(ele811.getAttribute("value"));
// Copy and past
		WebElement locOfOrder = locateElement("name", "C_MAIN_REF");
		Actions act = new Actions(driver);
		act.moveToElement(locOfOrder).doubleClick().build().perform();
// now apply copy command
		Logger log16 = Logger.getLogger("Reference Number");
		WebElement san2 = locateElement("name", "C_MAIN_REF");
		san2.sendKeys(Keys.chord(Keys.CONTROL, "c"));

// Excel Sheet Write Value

		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook1.createSheet("Reference Number");
		FileOutputStream outputStream = new FileOutputStream(new File("E:\\Testing\\Baseline\\Ref No\\IWGT.xlsx"));
		System.out.println(san2.getAttribute("value"));

		sheet1.createRow(6).createCell(4).setCellValue(san2.getAttribute("value"));
		sheet1.createRow(5).createCell(4).setCellValue("IWGT Reference Number :");

		workbook1.write(outputStream);
		workbook1.close();
		log16.info(san2.getAttribute("value"));
//Issue Date
		Logger log8 = Logger.getLogger("Issue Date");
		Row row111 = sheet.getRow(5);
		Cell cell111 = row111.getCell(4);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("id", "INWARD_RCV_DT");
		type(ele1, formatter1.format(numberAsString1));
		log8.info(ele1.getAttribute("value"));
//Type of Guarantee
		Logger log911 = Logger.getLogger("Type of Guarantee");
		Row row31 = sheet.getRow(5);
		Cell cell31 = row31.getCell(2);
		String Month = cell31.getStringCellValue();
		WebElement Financia = locateElement("name","GTEE_TYPE");
		selectDropDownUsingText(Financia,Month);
		log911.info(Financia.getAttribute("value"));
//Expiry/Review
		Logger log1011 = Logger.getLogger("Expiry/Review");
		Row row1311 = sheet.getRow(5);
		Cell cell1311 = row1311.getCell(6);
		String Charges1 = cell1311.getStringCellValue();
		WebElement Our11 = locateElement("name","FXD_EXPIRY");
		selectDropDownUsingText(Our11,Charges1);
		log1011.info(Our11.getAttribute("value"));
//Expiry Place
		Logger log101 = Logger.getLogger("Expiry Place");
		Row row131 = sheet.getRow(7);
		Cell cell131 = row131.getCell(2);
		String Charges = cell131.getStringCellValue();
		WebElement Our1 = locateElement("name","EXPIRY_PLC");
		type(Our1,Charges);
		log101.info(Our1.getAttribute("value"));
//Expiry/Review Date
		Logger info = Logger.getLogger("Expiry/Review Date");
		Row row12 = sheet.getRow(7);
		Cell cell2 = row12.getCell(4);
		SimpleDateFormat Maturity = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate1 = cell2.getDateCellValue();
		WebElement date1 = locateElement("name", "EXPIRY_DT");
		type(date1, Maturity.format(StartDate1));
		info.info(date1.getAttribute("value"));
//Transaction Date
		Logger lo = Logger.getLogger("Transaction Date");
		Row row1111 = sheet.getRow(7);
		Cell cell1111 = row1111.getCell(6);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate = cell1111.getDateCellValue();
		WebElement date11 = locateElement("name", "REG_DT");
		Clear(date11);
		WebElement date = locateElement("name", "REG_DT");
		type(date, formatter11.format(StartDate));
		lo.info(date.getAttribute("value"));
//Issued/Advised By
		Logger log20 = Logger.getLogger("Issued/Advised By");
		Row row13111 = sheet.getRow(9);
		Cell cell13111 = row13111.getCell(2);
		String Release = cell13111.getStringCellValue();
		WebElement Documents = locateElement("name","ISSUE_BY");
		selectDropDownUsingText(Documents,Release);
		log20.info(Documents.getAttribute("value"));
//Applicable Rules		
		Logger log22 = Logger.getLogger("Applicable Rules");
		Row row1122 = sheet.getRow(9);
		Cell cell22 = row1122.getCell(4);
		String Currency11 = cell22.getStringCellValue();
		WebElement Financial11 = locateElement("name","APLB_RULE");
		selectDropDownUsingText(Financial11,Currency11);
		log22.info(Financial11.getAttribute("value"));
//Validity		
		Logger Bill1 = Logger.getLogger("Validity");
		Row row191 = sheet.getRow(9);
		Cell cell191 = row191.getCell(6);
		String Bank = cell191.getStringCellValue();
		WebElement Waive = locateElement("name","AUTO_RENEW");
		selectDropDownUsingText(Waive,Bank);
		Bill1.info(Waive.getAttribute("value"));
//Further Identification (Instructing Bank)		
		Logger Bill = Logger.getLogger("Further Identification (Instructing Bank)");
		Row row19 = sheet.getRow(11);
		Cell cell19 = row19.getCell(2);
		String Collection = cell19.getStringCellValue();
		switch(Collection){
		case "Request":
			WebElement Documents1 = locateElement("name","FURTHER_IDENTITY");
			selectDropDownUsingText(Documents1,Collection);
			Bill.info(Documents1.getAttribute("value"));
//Method of Issue
			Logger log101111 = Logger.getLogger("Method of Issue");
			Row row331 = sheet.getRow(11);
			Cell cell331 = row331.getCell(4);
			String Charges121 = cell331.getStringCellValue();
			WebElement Our11111 = locateElement("name","MTHD_OF_ISS");
			selectDropDownUsingText(Our11111,Charges121);
			log101111.info(Our11111.getAttribute("value"));
			break;
		case "Issue":
			WebElement Documents11 = locateElement("name","FURTHER_IDENTITY");
			selectDropDownUsingText(Documents11,Collection);
			Bill.info(Documents11.getAttribute("value"));
//Counter Guarantee?
			Logger log11 = Logger.getLogger("Counter Guarantee");
			Row row52 = sheet.getRow(11);
			Cell cell3311 = row52.getCell(6);
			String Charges1211 = cell3311.getStringCellValue();
			WebElement Our111111 = locateElement("id","COUNTR_GTEE");
			selectDropDownUsingText(Our111111,Charges1211);
			log11.info(Our111111.getAttribute("value"));	
//Counter Guarantee Expiry Date
			Logger Date = Logger.getLogger("Counter Guarantee Expiry Date");
			Row row11111 = sheet.getRow(13);
			Cell cell11111 = row11111.getCell(2);
			SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd");
			Date numberAsString11 = cell11111.getDateCellValue();
			WebElement ele1111 = locateElement("name", "CONTR_GTEE_EXP");
			type(ele1111, formatter111.format(numberAsString11));
			Date.info(ele1111.getAttribute("value"));
			
//Counter Indemnity Held
			Logger log06 = Logger.getLogger("Counter Indemnity Held");
			Row row06 = sheet.getRow(13);
			Cell cell06 = row06.getCell(4);
			String Ship = cell06.getStringCellValue();
			WebElement Method11 = locateElement("name", "COUNTR_INDMNTY_HELD");
			selectDropDownUsingText(Method11, Ship);
			log06.info(Method11.getAttribute("value"));
//Counter Guarantee Reference		
			Logger log10111 = Logger.getLogger("Counter Guarantee Reference");
			Row row33 = sheet.getRow(13);
			Cell cell33 = row33.getCell(6);
			int Interest = (int) cell33.getNumericCellValue();
			WebElement ele811111 = locateElement("name","CONTR_GTEE_REF");
			type(ele811111, Integer.toString(Interest));
			log10111.info(ele811111.getAttribute("value"));
//Counter Indemnity Required?	
			Logger log061 = Logger.getLogger("Counter Indemnity Required?");
			Row row061 = sheet.getRow(15);
			Cell cell061 = row061.getCell(2);
			String Ship1 = cell061.getStringCellValue();
			WebElement Method111 = locateElement("name", "COUNTR_INDMNTY_REQ");
			selectDropDownUsingText(Method111, Ship1);
			log061.info(Method111.getAttribute("value"));
			break;
		}
// Charging Policy
		Logger Bill11 = Logger.getLogger("Charging Policy");
		Row row1911 = sheet.getRow(17);
		Cell cell1911 = row1911.getCell(2);
		String Collection1 = cell1911.getStringCellValue();
		switch (Collection1) {
		case "All in Advance":
			WebElement Documents1 = locateElement("name", "CHG_POLICY");
			selectDropDownUsingText(Documents1, Collection1);
			Bill11.info(Documents1.getAttribute("value"));
			break;
		case "Part in Advance":
			WebElement Documents11 = locateElement("name", "CHG_POLICY");
			selectDropDownUsingText(Documents11, Collection1);
			Bill11.info(Documents11.getAttribute("value"));
// Commission Date
			Logger log81 = Logger.getLogger("Remittance Date");
			Row row13 = sheet.getRow(17);
			Cell cell3 = row13.getCell(4);
			SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
			Date numberAsString11 = cell3.getDateCellValue();
			WebElement ele11 = locateElement("id", "COMM_DT");
			type(ele11, formatter3.format(numberAsString11));
			log81.info(ele11.getAttribute("value"));
// Current Guarantee Commission
			Logger log8111 = Logger.getLogger("LC_AMT");
			Row row2111 = sheet.getRow(17);
			Cell cell2111 = row2111.getCell(6);
			double Amount1 = cell2111.getNumericCellValue();
			WebElement ele2 = locateElement("xpath", ".//*[@id='CURRENT_COMM']");
			click(ele2);
			WebElement ele8111 = locateElement("xpath", ".//*[@id='CURRENT_COMM']");
			type(ele8111, Double.toString(Amount1));
			log8111.info(ele8111.getAttribute("value"));
			break;
		case "Weekly":
		case "Monthly":
		case "Quarterly":
		case "Half yearly":
		case "Yearly":
			WebElement Documents111 = locateElement("name", "CHG_POLICY");
			selectDropDownUsingText(Documents111, Collection1);
			Bill11.info(Documents111.getAttribute("value"));
			break;
		}
//Risk
		WebElement Risk  = locateElement("id","G");
		click(Risk);
//Risk Details
//Bank Liability Account		
		WebElement Details  = locateElement("name","ASSET_ACNO_BTN");
		click(Details);
			Set<String> windowId = driver.getWindowHandles();   
	        Iterator<String> itererator = windowId.iterator();   
	        String mainWinID = itererator.next();
	        String  newAdwinID = itererator.next();
	        driver.switchTo().window(newAdwinID);
	        System.out.println(driver.getTitle());
	        driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
	        driver.switchTo().window(mainWinID);
	        System.out.println(driver.getTitle());
//Frame		        
		switchToFramest("work");
//Customer Liability Account		
		WebElement Details1  = locateElement("name","APPL_AC_MRGN_BTN");
		click(Details1);
			Set<String> windowId1 = driver.getWindowHandles();   
	        Iterator<String> itererator1 = windowId1.iterator();   
	        String mainWinID1 = itererator1.next();
	        String  newAdwinID1 = itererator1.next();
	        driver.switchTo().window(newAdwinID1);
	        System.out.println(driver.getTitle());
	        driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
	        driver.switchTo().window(mainWinID1);
	        System.out.println(driver.getTitle());
//Frame		        
		switchToFramest("work");
// Parties
		WebElement Parties = locateElement("id", "B");
		click(Parties);
//Applicant
		WebElement Drawer = locateElement("name", "APPL_ID_BTN");
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
//Beneficiary
		Logger log991 = Logger.getLogger("Beneficiary");
		Row row261 = sheet.getRow(15);
		Cell cell261 = row261.getCell(4);
		String Mail = cell261.getStringCellValue();
		WebElement Method = locateElement("name", "BENE_CUST_BK");
		selectDropDownUsingText(Method, Mail);
		log991.info(Method.getAttribute("value"));
//Beneficiary CUBK		
		WebElement Drawer11 = locateElement("name", "BENE_ID_BTN");
		click(Drawer11);
		Set<String> windowId13 = driver.getWindowHandles();
		Iterator<String> itererator12 = windowId13.iterator();
		String mainWinID3 = itererator12.next();
		String newAdwinID3 = itererator12.next();
		driver.switchTo().window(newAdwinID3);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
		driver.switchTo().window(mainWinID3);
		System.out.println(driver.getTitle());
// Frame
		switchToFramest("work");
//Send to
		Logger log9911 = Logger.getLogger("Send to");
		Row row2611 = sheet.getRow(15);
		Cell cell2611 = row2611.getCell(6);
		String Mail1 = cell2611.getStringCellValue();
		WebElement Method1 = locateElement("name", "SEND_TO");
		selectDropDownUsingText(Method1, Mail1);
		log9911.info(Method1.getAttribute("value"));
//Send to CUBK	
		WebElement Drawer111 = locateElement("name", "SEND_TO_ID_BTN");
		click(Drawer111);
		Set<String> windowId11 = driver.getWindowHandles();
		Iterator<String> itererator11 = windowId11.iterator();
		String mainWinID11 = itererator11.next();
		String newAdwinID11 = itererator11.next();
		driver.switchTo().window(newAdwinID11);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='7']/td[2]/a")).click();
		driver.switchTo().window(mainWinID11);
		System.out.println(driver.getTitle());
// Frame
		switchToFramest("work");
//Instructing Bank
		WebElement Instructing = locateElement("name", "RCV_FM_BK_ID_BTN");
		click(Instructing);
		Set<String> windowId131 = driver.getWindowHandles();
		Iterator<String> itererator121 = windowId131.iterator();
		String mainWinID31 = itererator121.next();
		String newAdwinID31 = itererator121.next();
		driver.switchTo().window(newAdwinID31);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
		driver.switchTo().window(mainWinID31);
		System.out.println(driver.getTitle());
// Frame
		switchToFramest("work");
//Issuing Bank		
		WebElement Issuing = locateElement("name", "ISS_BK_ID_BTN");
		click(Issuing);
		Set<String> windowId111 = driver.getWindowHandles();
		Iterator<String> itererator111 = windowId111.iterator();
		String mainWinID111 = itererator111.next();
		String newAdwinID111 = itererator111.next();
		driver.switchTo().window(newAdwinID111);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@id='1']/td[2]/a")).click();
		driver.switchTo().window(mainWinID111);
		System.out.println(driver.getTitle());
// Frame
		switchToFramest("work");
		
// Details
		WebElement Details2 = locateElement("id", "C");
		click(Details2);
// Guarantee Title
		WebElement Guarantee = locateElement("name", "GTEE_TITLE");
		type(Guarantee, "Guarantee");
// Signature
		WebElement Signature = locateElement("name", "SIGNATURE");
		type(Signature, "Signature");
// Signature
		WebElement Signature1 = locateElement("name", "SIGNATURE2");
		type(Signature1, "Signature");
//Guarantee Details		
		WebElement Details6 = locateElement("name", "TEMP_DOC_REQ");
		type(Details6, "test");
// Sender to Receiver Information (MT760:72)
		WebElement Sender = locateElement("name", "TEMP_TAG_72");
		type(Sender, "Welcome");
// Charges
		WebElement Charges2 = locateElement("id", "D");
		click(Charges2);
// Paid By
		Logger log0611 = Logger.getLogger("Paid By");
		Row row0611 = sheet.getRow(19);
		Cell cell0611 = row0611.getCell(2);
		String Paid = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111, Paid);
		log0611.info(Method1111.getAttribute("value"));
// PaidAt
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(19);
		Cell cell07 = row07.getCell(4);
		String PaidAt = cell07.getStringCellValue();
		switch (PaidAt) {
		case "TRANSACTION":
			WebElement Method3 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3, PaidAt);
			log07.info(Method3.getAttribute("value"));
			Row row11111 = sheet.getRow(19);
			Cell cell11111 = row11111.getCell(6);
			int Amount11 = (int) cell11111.getNumericCellValue();
			if ((Integer.toString(Amount11) == null) || (Amount11 == 0)) {
				WebElement Our111 = locateElement("name", "CHG_GETAC_BTN");
				click(Our111);
				Set<String> window = driver.getWindowHandles();
				Iterator<String> itererator21 = window.iterator();
				String mainWin = itererator21.next();
				String newAdwin = itererator21.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
				switchToFramest("work");
				Thread.sleep(2000);
			} else {
				Thread.sleep(2000);
				Logger log09 = Logger.getLogger("Paid At");
				WebElement Method6 = locateElement("name", "CHG_FLD_LOCAL_CUST_AC_NO");
				type(Method6, "763915060");
				log09.info(Method6.getAttribute("value"));
				Thread.sleep(2000);
				WebElement Acknowledgement = locateElement("id", "E");
				click(Acknowledgement);
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
		Thread.sleep(2000);
		Logger log071 = Logger.getLogger("Acknowledgment (MT768)");
		Row row071 = sheet.getRow(21);
		Cell cell071 = row071.getCell(2);
		String PaidAt1 = cell071.getStringCellValue();
		switch (PaidAt1) {
		case "Yes":
			WebElement Method3 = locateElement("name", "SEND_MT768_FLG");
			selectDropDownUsingText(Method3, PaidAt1);
			log071.info(Method3.getAttribute("value"));
// Account identification
			Logger log10111 = Logger.getLogger("Account identification");
			Row row33 = sheet.getRow(21);
			Cell cell33 = row33.getCell(4);
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
			Logger log8111 = Logger.getLogger("Amount of charges");
			Row row2111 = sheet.getRow(21);
			Cell cell2111 = row2111.getCell(6);
			int Amount1 = (int) cell2111.getNumericCellValue();
			if ((Integer.toString(Amount1) == null) || (Amount1 == 0)) {
				WebElement ele2 = locateElement("xpath", ".//*[@id='CHG_AMT_MT768']");
				click(ele2);
			} else {
				WebElement ele4 = locateElement("xpath", ".//*[@id='CHG_AMT_MT768']");
				click(ele4);
				WebElement ele8111 = locateElement("xpath", ".//*[@id='CHG_AMT_MT768']");
				type(ele8111, Integer.toString(Amount1));
				log8111.info(ele8111.getAttribute("value"));
			}

// Currency
			Logger log91 = Logger.getLogger("Currency");
			Row row11 = sheet.getRow(23);
			Cell cell11 = row11.getCell(2);
			String Currency1 = cell11.getStringCellValue();
			WebElement Financial1 = locateElement("name", "CHG_CCY_MT768");
			selectDropDownUsingText(Financial1, Currency1);
			log91.info(Financial1.getAttribute("value"));
// Account with bank ID
			WebElement Drawer1 = locateElement("name", "AC_WT_BK_ID_BTN");
			click(Drawer1);
			Set<String> windowId21 = driver.getWindowHandles();
			Iterator<String> itererator21 = windowId21.iterator();
			String mainWinID21 = itererator21.next();
			String newAdwinID21 = itererator21.next();
			driver.switchTo().window(newAdwinID21);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='5']/td[2]/a")).click();
			driver.switchTo().window(mainWinID21);
			System.out.println(driver.getTitle());
			// Frame
			switchToFramest("work");
// Sender to Receiver Information (72)
			WebElement Receiver = locateElement("name", "SEND_TO_RCV_INFO");
			type(Receiver, "Guarantee");
// Charges Details Instructing Bank
			WebElement Instructing1 = locateElement("name", "CHARGES_MAIL");
			type(Instructing1, "Welcome");
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
		//Advice();
//Note
		WebElement Note = locateElement("id", "I");
		click(Note);
		Notes();
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