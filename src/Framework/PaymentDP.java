package Framework;

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

public class PaymentDP extends SeMethods{
	@Test
	public void IMCOPayment() throws IOException, InterruptedException{	
//login page
		
		loginpage();
//payment		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Import Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IMCO Settlement");
		click(Function);
		log5.info("IMCO Settlement");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300294F05030701913");
		click(FunctionGroup);
		log6.info("Payment (D/P)");
	
//catalog page	
		Catalog();
// Main
		
//Excel Sheet get Value
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
//Charges
		WebElement Charges = locateElement("id", "D");
		click(Charges);
		Charges();
// Payment
		WebElement Payment = locateElement("id", "F");
		click(Payment);
// Payment Debit
		WebElement Debit = locateElement("id", "do_PaymentDebitHeader_Tab");
		click(Debit);
// add
		WebElement add = locateElement("id", "PaymentDebit_ADD");
		click(add);
		acceptAlert();
//Debit Value Date
		Logger log81 = Logger.getLogger("Debit Value Date");
		Row row1111 = sheet.getRow(3);
		Cell cell1111 = row1111.getCell(6);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString11 = cell1111.getDateCellValue();
		WebElement ele11 = locateElement("name", "CPYT_DR_VAL_DATE");
		type(ele11, formatter11.format(numberAsString11));
		log81.info(ele11.getAttribute("value"));
		acceptAlert();
// Account Type
		Logger log10 = Logger.getLogger("Account Type");
		Row row11 = sheet.getRow(5);
		Cell cell11 = row11.getCell(2);
		String Role = cell11.getStringCellValue();
		WebElement Account = locateElement("name", "CPYT_DR_AC_TYPE");
		selectDropDownUsingText(Account, Role);
		log10.info(Account.getAttribute("value"));
// Account Owner ID
		Row row11111 = sheet.getRow(5);
		Cell cell11111 = row11111.getCell(4);
		int Amount11 = (int) cell11111.getNumericCellValue();
		if ((Integer.toString(Amount11) == null) || (Amount11 == 0)) {
			WebElement Our1 = locateElement("name", "CPYT_DR_AC_OWNER_BTN");
			click(Our1);
			Set<String> window = driver.getWindowHandles();
			Iterator<String> itererator2 = window.iterator();
			String mainWin = itererator2.next();
			String newAdwin = itererator2.next();
			driver.switchTo().window(newAdwin);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
			driver.switchTo().window(mainWin);
			System.out.println(driver.getTitle());
			switchToFramest("work");
		} else {
// Account Owner ID
			Logger log1011 = Logger.getLogger("Account Owner ID");
			WebElement Our11 = locateElement("name", "CPYT_DR_ID");
			type(Our11, Integer.toString(Amount11));
			log1011.info(Our11.getAttribute("value"));
// Account No.
			Logger log10111 = Logger.getLogger("Send Amendment by");
			Row row1111111 = sheet.getRow(5);
			Cell cell5 = row1111111.getCell(6);
			int Amount1111 = (int) cell5.getNumericCellValue();
			WebElement Our111 = locateElement("name", "CPYT_DR_AC");
			type(Our111, Integer.toString(Amount1111));
			log10111.info(Our111.getAttribute("value"));
		}
// Payment Save Button
		WebElement Save = locateElement("id", "PaymentDebit_SAVE");
		click(Save);
// Payment Credit
		WebElement Credit = locateElement("id", "do_PaymentCreditHeader_Tab");
		click(Credit);
// Credit Add
		WebElement CreditAdd = locateElement("id", "PaymentCredit_ADD");
		click(CreditAdd);
// Credit Value Date
		Logger log811 = Logger.getLogger("Debit Value Date");
		Row row111111 = sheet.getRow(9);
		Cell cell56 = row111111.getCell(2);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString111 = cell56.getDateCellValue();
		WebElement ele111 = locateElement("name", "CPYT_CR_VAL_DATE");
		type(ele111, format.format(numberAsString111));
		log811.info(ele111.getAttribute("value"));
		acceptAlert();
// Account Type 
		Logger log101 = Logger.getLogger("Account Type");
		Row row116 = sheet.getRow(7);
		Cell cell116 = row116.getCell(2);
		String Role1 = cell116.getStringCellValue();
		WebElement Account1 = locateElement("name", "CPYT_CR_AC_TYPE");
		selectDropDownUsingText(Account1, Role1);
		log101.info(Account1.getAttribute("value"));
// Account Owner ID
		Row row113 = sheet.getRow(7);
		Cell cell3 = row113.getCell(4);
		int Amount = (int) cell3.getNumericCellValue();
		if ((Integer.toString(Amount) == null) || (Amount == 0)) {
			WebElement Our1 = locateElement("name", "CPYT_ASSGN_ID_BTN");
			click(Our1);
			Set<String> window = driver.getWindowHandles();
			Iterator<String> itererator2 = window.iterator();
			String mainWin = itererator2.next();
			String newAdwin = itererator2.next();
			driver.switchTo().window(newAdwin);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
			driver.switchTo().window(mainWin);
			System.out.println(driver.getTitle());
			switchToFramest("work");
		} else {
// Account Owner ID
			Logger log1011 = Logger.getLogger("Account Owner ID");
			WebElement Our11 = locateElement("name", "CPYT_ASSGN_ID");
			type(Our11, Integer.toString(Amount));
			log1011.info(Our11.getAttribute("value"));
// Account No.
			Logger log10111 = Logger.getLogger("Send Amendment by");
			Row row1111111 = sheet.getRow(7);
			Cell cell5 = row1111111.getCell(6);
			int Amount1111 = (int) cell5.getNumericCellValue();
			WebElement Our111 = locateElement("name", "CPYT_CR_AC");
			type(Our111, Integer.toString(Amount1111));
			log10111.info(Our111.getAttribute("value"));
		}
//Payment Advice Message			
			Logger log9911 = Logger.getLogger("Payment Advice Message");
			Row row2611 = sheet.getRow(9);
			Cell cell2611 = row2611.getCell(4);
			String Mail1 = cell2611.getStringCellValue();
			switch(Mail1){
			case "None":
			case "Fedwire":
			case "Mail":
			WebElement Method1 = locateElement("name","CPYT_PAY_ADV_MSG");
			selectDropDownUsingText(Method1,Mail1);
			log9911.info(Method1.getAttribute("value"));
			break;
			case "MT103":
				WebElement Method11 = locateElement("name","CPYT_PAY_ADV_MSG");
				selectDropDownUsingText(Method11,Mail1);
				log9911.info(Method11.getAttribute("value"));
//103 Swift message				
				WebElement Swift = locateElement("id","do_PaymentMT103_Tab");
				click(Swift);
				WebElement ID = locateElement("name","X103_ADV_BKID_B2");
				type(ID,"ABNADEHHCGN");
//Ordering Customer [50]				
				WebElement Ordering = locateElement("name","X103_ORDCU_ID_50A");
				type(Ordering,"BNLIITRRCNX");
//Beneficiary Customer
				WebElement Beneficiary = locateElement("name","X103_BENECU_ID_59A");
				type(Beneficiary,"C000087");
//	Sender Charges[71F]	
				Logger log8111 = Logger.getLogger("	Sender Charges[71F]	");
				Row row211 = sheet.getRow(11);
				Cell cell211 = row211.getCell(2);
				double Amount1 = cell211.getNumericCellValue();
				WebElement ele = locateElement("name", "X103_SENDCHGAMT71F");
				click(ele);
				WebElement ele811 = locateElement("name", "X103_SENDCHGAMT71F");
				type(ele811, Double.toString(Amount1));
				log8111.info(ele811.getAttribute("value"));
//main			
				WebElement main = locateElement("id", "do_PaymentCredit_M_Tab");
				click(main);
				break;
			case "MT400":
				WebElement Method111 = locateElement("name","CPYT_PAY_ADV_MSG");
				selectDropDownUsingText(Method111,Mail1);
				log9911.info(Method111.getAttribute("value"));
//400 Swift message				
				WebElement Swift1 = locateElement("id","do_PaymentMT400_Tab");
				click(Swift1);
				WebElement message1 = locateElement("name","X400_ADV_BK_ID_BTN");
				click(message1);
				
				WebElement main1 = locateElement("id", "do_PaymentCredit_M_Tab");
				click(main1);
				break;
			}
			
//Payment Cover Message			
			Logger log06 = Logger.getLogger("Payment Cover Message	");
			Row row06 = sheet.getRow(9);
			Cell cell06 = row06.getCell(6);
			String Ship = cell06.getStringCellValue();
			switch(Ship){
			case "None":
			case "Mail":
				WebElement Method11 = locateElement("name","CPYT_PAY_COV_MSG");
				selectDropDownUsingText(Method11,Ship);
				log06.info(Method11.getAttribute("value"));
				break;
			case "MT202":
			case "MT202COV":
				acceptAlert();
				WebElement Method111 = locateElement("name","CPYT_PAY_COV_MSG");
				selectDropDownUsingText(Method111,Ship);
				log06.info(Method111.getAttribute("value"));
//PaymentMT202 
				WebElement main = locateElement("id", "do_PaymentMT202_Tab");
				click(main);
//Receiver Bank[B2]				
				WebElement ID = locateElement("name","X202_ADV_BKID_B2");
				type(ID,"ABNADEHHCGN");
//Beneficiary Institution [58]				
				WebElement Ordering = locateElement("name","X202_BENE_BKID_58A");
				type(Ordering,"BDGLCH22XXX");
//Beneficiary Customer
				break;
			}
		
// Payment Save Button
		WebElement Save1 = locateElement("id", "PaymentCredit_SAVE");
		click(Save1);
// Advice

		WebElement Advice = locateElement("id", "G");
		click(Advice);
		Advice();
// Diary
		WebElement Diary = locateElement("id", "J");
		click(Diary);
		Diary();
// confirm
		//acceptAlert();
		Confirm();
// supervisor Release
		//IMCO_SupervisorRelease();
	}
}
