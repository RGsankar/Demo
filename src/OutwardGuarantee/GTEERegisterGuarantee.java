package OutwardGuarantee;

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

public class GTEERegisterGuarantee extends SeMethods  {
	@Test
	public void GTEEREG () throws IOException, InterruptedException{		
		
//login Page
		
		loginpage();
//IPLC Module		
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import  = locateElement("name", "Outward Guarantee");
		click(Import);
		log4.info("Outward Guarantee");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "GTEE Issuance");
		click(Function);
		log5.info("GTEE Issuance");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300263F05030703161");
		click(FunctionGroup);
		log6.info("Register Guarantee");
//Frame 
		 switchToFramest("work");
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\Module\\GTEE.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Register");
		workbook.close();  

//Main		
//Form of Undertaking
		Logger log9 = Logger.getLogger("Form of Undertaking");
		Row row1 = sheet.getRow(3);
		Cell cell1 = row1.getCell(2);
		String Currency = cell1.getStringCellValue();
		WebElement Financial = locateElement("name", "FORM_OF_UNDERTAKING");
		selectDropDownUsingText(Financial, Currency);
		log9.info(Financial.getAttribute("value"));
//Expiry Place 
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(4);
		String invalid = cell.getStringCellValue();
		Logger log051 = Logger.getLogger("Expiry Place");
		WebElement Reference = locateElement("name", "EXPIRY_PLC");
		type(Reference, invalid);
		log051.info(Reference.getAttribute("value"));
//Applicable Rules
		Logger log22 = Logger.getLogger("Applicable Rules");
		Row row1122 = sheet.getRow(3);
		Cell cell22 = row1122.getCell(6);
		String Currency11 = cell22.getStringCellValue();
		WebElement Financial11 = locateElement("id","APLB_RULE");
		selectDropDownUsingText(Financial11,Currency11);
		log22.info(Financial11.getAttribute("value"));
//Expiry Type
		Row type = sheet.getRow(5);
		Cell cell2 = type.getCell(6);
		String Expiry = cell2.getStringCellValue();
		Logger log2 = Logger.getLogger("Expiry Type");
		switch (Expiry){
		case "COND":
			WebElement Expiry2 = locateElement("name","EXPIRY_TYPE");
			selectDropDownUsingText(Expiry2, Expiry);
			log2.info(Expiry2.getAttribute("value"));
//Expiry Condition/Event
			Row type1 = sheet.getRow(5);
			Cell cell21 = type1.getCell(2);
			String Expiry1 = cell21.getStringCellValue();
			WebElement Condition = locateElement("name","EXPIRY_COND");
			type(Condition,Expiry1);
//Expiry/Review Date
			Logger lo = Logger.getLogger("Transaction Date");
			Row row1111 = sheet.getRow(5);
			Cell cell1111 = row1111.getCell(4);
			SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
			Date StartDate = cell1111.getDateCellValue();
			WebElement date = locateElement("id", "EXPIRY_DT");
			type(date, formatter11.format(StartDate));
			lo.info(date.getAttribute("value"));
			break;
		case "FIXD":
			WebElement Expiry21 = locateElement("name","EXPIRY_TYPE");
			selectDropDownUsingText(Expiry21, Expiry);
			log2.info(Expiry21.getAttribute("value"));
//Expiry/Review Date
			Logger lo1 = Logger.getLogger("Transaction Date");
			Row row11111 = sheet.getRow(5);
			Cell cell11111 = row11111.getCell(4);
			SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd");
			Date StartDate1 = cell11111.getDateCellValue();
			WebElement date1 = locateElement("id", "EXPIRY_DT");
			type(date1, formatter111.format(StartDate1));
			lo1.info(date1.getAttribute("value"));
			break;
		case "OPEN":
			WebElement Expiry211 = locateElement("name","EXPIRY_TYPE");
			selectDropDownUsingText(Expiry211, Expiry);
			log2.info(Expiry211.getAttribute("value"));
			break;
			
		}
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
		FileOutputStream outputStream = new FileOutputStream(new File("E:\\Testing\\Baseline\\Ref No\\GTEE.xlsx"));
		System.out.println(san2.getAttribute("value"));

		sheet1.createRow(6).createCell(4).setCellValue(san2.getAttribute("value"));
		sheet1.createRow(5).createCell(4).setCellValue("IWGT Reference Number :");

		workbook1.write(outputStream);
		workbook1.close();
		log16.info(san2.getAttribute("value"));
//Purpose of Message
		Logger log20 = Logger.getLogger("Purpose of Message");
		Row row13111 = sheet.getRow(7);
		Cell cell13111 = row13111.getCell(2);
		String Release = cell13111.getStringCellValue();
		switch (Release){
		case "ACNF":
		case "ADVI":
			WebElement Documents = locateElement("id","PURP_OF_MESS");
			selectDropDownUsingText(Documents,Release);
			log20.info(Documents.getAttribute("value"));
//Transfer Indicator [48D]
			WebElement Transfer = locateElement("id","TRANS_INDICATOR");
			type(Transfer,"TRAN");
//Delivery of Original Undertaking [24E]
			WebElement Delivery = locateElement("id","DELIV_OF_ORIG_UNDERTAKING");
			type(Delivery,"Original Document");
			break;
		case "ISSC":
		case "ISSU":
			WebElement Documents1 = locateElement("id","PURP_OF_MESS");
			selectDropDownUsingText(Documents1,Release);
			log20.info(Documents1.getAttribute("value"));
			break;
			
		}
//Demand Indicator [48B]
		/*Logger Bill1 = Logger.getLogger("Demand Indicator [48B]");
		Row row191 = sheet.getRow(7);
		Cell cell191 = row191.getCell(4);
		String Bank = cell191.getStringCellValue();
		WebElement Waive = locateElement("name","DEMAND_INDICATOR");
		selectDropDownUsingText(Waive,Bank);
		Bill1.info(Waive.getAttribute("value"));
//Expiry/Review
		Logger log1011 = Logger.getLogger("Expiry/Review");
		Row row1311 = sheet.getRow(7);
		Cell cell1311 = row1311.getCell(6);
		String Charges1 = cell1311.getStringCellValue();
		WebElement Our11 = locateElement("id", "FXD_EXPIRY");
		selectDropDownUsingText(Our11, Charges1);
		log1011.info(Our11.getAttribute("value"));
//Automatic Renewal
		Logger Bill11 = Logger.getLogger("Validity");
		Row row1911 = sheet.getRow(9);
		Cell cell1911 = row1911.getCell(2);
		String Bank1 = cell1911.getStringCellValue();
		WebElement Automatic = locateElement("name","AUTO_RENEW");
		selectDropDownUsingText(Automatic,Bank1);
		Bill11.info(Automatic.getAttribute("value"));
		
//Final Maturity Date
		Logger info = Logger.getLogger("Final Maturity Date");
		Row row12 = sheet.getRow(9);
		Cell cell21 = row12.getCell(4);
		SimpleDateFormat Maturity = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate1 = cell21.getDateCellValue();
		WebElement date1 = locateElement("id", "MATURITY_DT");
		type(date1, Maturity.format(StartDate1));
		info.info(date1.getAttribute("value"));
//Draft Guarantee?
		Logger log201 = Logger.getLogger("Draft Guarantee?");
		Row row131111 = sheet.getRow(9);
		Cell cell131111 = row131111.getCell(6);
		String Release1 = cell131111.getStringCellValue();
		WebElement Documents = locateElement("id","DRAFT_GTEE");
		selectDropDownUsingText(Documents,Release1);
		log201.info(Documents.getAttribute("value"));
//Counter Guarantee?
		Logger log11 = Logger.getLogger("Counter Guarantee");
		Row row52 = sheet.getRow(11);
		Cell cell3311 = row52.getCell(2);
		String Charges1211 = cell3311.getStringCellValue();
		WebElement Our111111 = locateElement("id","COUNTR_GTEE");
		selectDropDownUsingText(Our111111,Charges1211);
		log11.info(Our111111.getAttribute("value"));	
//Counter Guarantee Expiry Date
		Logger Date = Logger.getLogger("Counter Guarantee Expiry Date");
		Row row11111 = sheet.getRow(11);
		Cell cell11111 = row11111.getCell(4);
		SimpleDateFormat formatter111 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString11 = cell11111.getDateCellValue();
		WebElement ele1111 = locateElement("name", "CONTR_GTEE_EXP");
		type(ele1111, formatter111.format(numberAsString11));
		Date.info(ele1111.getAttribute("value"));
		
//Counter Indemnity Held
		Logger log06 = Logger.getLogger("Counter Indemnity Held");
		Row row06 = sheet.getRow(11);
		Cell cell06 = row06.getCell(6);
		String Ship = cell06.getStringCellValue();
		WebElement Method11 = locateElement("name", "COUNTR_INDMNTY_HELD");
		selectDropDownUsingText(Method11, Ship);
		log06.info(Method11.getAttribute("value"));
//Counter Guarantee Reference		
		Logger log10111 = Logger.getLogger("Counter Guarantee Reference");
		Row row33 = sheet.getRow(13);
		Cell cell33 = row33.getCell(2);
		int Interest = (int) cell33.getNumericCellValue();
		WebElement ele811111 = locateElement("id","CONTR_GTEE_REF");
		type(ele811111, Integer.toString(Interest));
		log10111.info(ele811111.getAttribute("value"));
//Counter Indemnity Required?	
		Logger log061 = Logger.getLogger("Counter Indemnity Required?");
		Row row061 = sheet.getRow(13);
		Cell cell061 = row061.getCell(4);
		String Ship1 = cell061.getStringCellValue();
		WebElement Method111 = locateElement("name", "COUNTR_INDMNTY_REQ");
		selectDropDownUsingText(Method111, Ship1);
		log061.info(Method111.getAttribute("value"));*/
//Guarantee currency
		Logger log91 = Logger.getLogger("Guarantee Amount");
		Row row11 = sheet.getRow(13);
		Cell cell11 = row11.getCell(6);
		String Currency1 = cell11.getStringCellValue();
		WebElement Financial1 = locateElement("name", "GTEE_CCY");
		selectDropDownUsingText(Financial1, Currency1);
		log91.info(Financial1.getAttribute("value"));
// Amount
		Logger log811 = Logger.getLogger("LC_AMT");
		Row row211 = sheet.getRow(15);
		Cell cell211 = row211.getCell(2);
		double Amount = cell211.getNumericCellValue();
		WebElement ele = locateElement("xpath", ".//*[@id='GTEE_AMT']");
		click(ele);
		WebElement ele811 = locateElement("xpath", ".//*[@id='GTEE_AMT']");
		type(ele811, Double.toString(Amount));
		log811.info(ele811.getAttribute("value"));
// Parties
		WebElement Parties = locateElement("id", "B");
		click(Parties);
		
		WebElement Applicant2 = locateElement("name", "APPL_CUST_BK");
		type(Applicant2, "Bank");
// Applicant ID
		Logger log911 = Logger.getLogger("Applicant ID");
		Row row261 = sheet.getRow(15);
		Cell cell261 = row261.getCell(4);
		String Applicant = cell261.getStringCellValue();
		switch(Applicant){
		case "Customer":
			WebElement ApplicantID = locateElement("name", "APPL_CUST_BK");
			selectDropDownUsingText(ApplicantID, Applicant);
			log911.info(ApplicantID.getAttribute("value"));
			WebElement ApplicantID21 = locateElement("name", "APPL_ID");
			type(ApplicantID21, "C000081");
			Thread.sleep(1000);
			WebElement ApplicantID2 = locateElement("xpath", "//*[@id='APPL_BRCH_GTEE_1']/table/tbody/tr[6]/td[2]");
			click(ApplicantID2);
//Email Address			
			WebElement Email = locateElement("name", "APPL_EMAIL");
			type(Email, "sankar@gmail.com");
			break;
		case "Bank":
			WebElement ApplicantID1 = locateElement("name", "APPL_CUST_BK");
			selectDropDownUsingText(ApplicantID1, Applicant);
			log911.info(ApplicantID1.getAttribute("value"));
			WebElement ApplicantID211 = locateElement("name", "APPL_ID");
			type(ApplicantID211, "BK000034");
			break;
		}
//Beneficiary
		Logger log9111 = Logger.getLogger("Beneficiary");
		Row row2611 = sheet.getRow(15);
		Cell cell2611 = row2611.getCell(6);
		String Beneficiary = cell2611.getStringCellValue();
		switch(Beneficiary){
		case "Customer":
			WebElement ApplicantID = locateElement("name", "BENE_CUST_BK");
			selectDropDownUsingText(ApplicantID, Beneficiary);
			log9111.info(ApplicantID.getAttribute("value"));
			WebElement ApplicantID21 = locateElement("name", "BENE_ID");
			type(ApplicantID21, "C000096");
			Thread.sleep(1000);
			WebElement ApplicantID2 = locateElement("xpath", "//*[@id='APPL_BRCH_GTEE_1']/table/tbody/tr[6]/td[2]");
			click(ApplicantID2);
//Email Address			
			WebElement Email = locateElement("name", "BENE_EMAIL");
			type(Email, "sankar@gmail.com");
			break;
		case "Bank":
			WebElement ApplicantID1 = locateElement("name", "BENE_CUST_BK");
			selectDropDownUsingText(ApplicantID1, Beneficiary);
			log9111.info(ApplicantID1.getAttribute("value"));
			WebElement ApplicantID211 = locateElement("name", "BENE_ID");
			type(ApplicantID211, "BK000034");
			break;
		}
//Same as Applicant
		Logger log91111 = Logger.getLogger("Same as Applicant");
		Row row26111 = sheet.getRow(17);
		Cell cell26111 = row26111.getCell(2);
		String Same = cell26111.getStringCellValue();
		switch(Same){
		case "YES":
			WebElement ApplicantID = locateElement("name", "SAME_AS_APPL_FLG");
			selectDropDownUsingText(ApplicantID, Same);
			log91111.info(ApplicantID.getAttribute("value"));
			break;
		case "NO":
			WebElement ApplicantID1 = locateElement("name", "SAME_AS_APPL_FLG");
			selectDropDownUsingText(ApplicantID1, Same);
			log91111.info(ApplicantID1.getAttribute("value"));
			Logger log92 = Logger.getLogger("Beneficiary");
			
			WebElement ApplicantID8 = locateElement("name", "DOCS_PRESENTED_BY");
			type(ApplicantID8,"Bank");
			Row row6 = sheet.getRow(17);
			Cell cell6 = row6.getCell(4);
			String Customer  = cell6.getStringCellValue();
			switch(Customer ){
			case "Customer":
				WebElement ApplicantID11 = locateElement("name", "DOCS_PRESENTED_BY");
				selectDropDownUsingText(ApplicantID11, Customer );
				log92.info(ApplicantID11.getAttribute("value"));
				WebElement ApplicantID21 = locateElement("name", "INDEMN_ID");
				type(ApplicantID21, "C000089");
				Thread.sleep(1000);
				WebElement ApplicantID2 = locateElement("xpath", "//*[@id='APPL_BRCH_GTEE_1']/table/tbody/tr[6]/td[2]");
				click(ApplicantID2);
				break;
			case "Bank":
				WebElement ApplicantID111 = locateElement("name", "DOCS_PRESENTED_BY");
				selectDropDownUsingText(ApplicantID111, Customer);
				log92.info(ApplicantID111.getAttribute("value"));
				WebElement ApplicantID211 = locateElement("name", "INDEMN_ID");
				type(ApplicantID211, "PTSABMABXXX");
				WebElement ApplicantID2111 = locateElement("xpath", "//*[@id='APPL_BRCH_GTEE_1']/table/tbody/tr[6]/td[2]");
				click(ApplicantID2111);
				break;
			}
			break;		}
//Send to
		Logger log10 = Logger.getLogger("Beneficiary");
		Row row10 = sheet.getRow(17);
		Cell cell10 = row10.getCell(6);
		String Send = cell10.getStringCellValue();
		switch(Send){
		case "Customer":
			WebElement ApplicantID = locateElement("name", "SEND_TO");
			selectDropDownUsingText(ApplicantID, Send);
			log10.info(ApplicantID.getAttribute("value"));
			WebElement ApplicantID21 = locateElement("name", "SEND_TO_ID");
			type(ApplicantID21, "C000081");
			Thread.sleep(1000);
			WebElement ApplicantID2 = locateElement("xpath", "//*[@id='APPL_BRCH_GTEE_1']/table/tbody/tr[6]/td[2]");
			click(ApplicantID2);
			break;
		case "Bank":
			WebElement ApplicantID1 = locateElement("name", "SEND_TO");
			selectDropDownUsingText(ApplicantID1, Send);
			log10.info(ApplicantID1.getAttribute("value"));
			WebElement ApplicantID211 = locateElement("name", "SEND_TO_ID");
			type(ApplicantID211, "CITIUS33XXX");
			break;
		}
//Issuer [52] 
		WebElement Issuer = locateElement("name", "ISSUE_BK_ID");
		type(Issuer, "ABNADEHHCGN");
//Advising Bank [56]
		WebElement Advising = locateElement("name", "ADV_BK_ID");
		type(Advising, "UBBRBRSPXXX");
		WebElement Advising1 = locateElement("name", "ADV_BK_ID");
		click(Advising1);
//Guarantee Wording
		WebElement Guarantee = locateElement("id", "D");
		click(Guarantee);
		
//Instruction Date
		Logger lo = Logger.getLogger("Instruction Date");
		Row row1111 = sheet.getRow(19);
		Cell cell1111 = row1111.getCell(2);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate = cell1111.getDateCellValue();
		WebElement date11 = locateElement("id", "GOODS_ETA_DT");
		Clear(date11);
		WebElement date = locateElement("id", "GOODS_ETA_DT");
		type(date, formatter11.format(StartDate));
		lo.info(date.getAttribute("value"));
//Mail Method Applicant
		Logger log06 = Logger.getLogger("Mail Method Applicant");
		Row row06 = sheet.getRow(17);
		Cell cell06 = row06.getCell(2);
		String Mail = cell06.getStringCellValue();
		WebElement Method11 = locateElement("name", "MAIL_METHOD_1ST");
		type(Method11, Mail);
		log06.info(Method11.getAttribute("value"));
//Mail Method Beneficiary	
		Logger log061 = Logger.getLogger("Mail Method Beneficiary");
		Row row061 = sheet.getRow(21);
		Cell cell061 = row061.getCell(6);
		String Mail1 = cell061.getStringCellValue();
		WebElement Method111 = locateElement("name", "MAIL_METHOD_2ND");
		type(Method111, Mail1);
		log061.info(Method11.getAttribute("value"));
//Charges/Interest Instructions
		Logger log101111 = Logger.getLogger("Charges/Interest Instructions");
		Row row331 = sheet.getRow(21);
		Cell cell331 = row331.getCell(4);
		String Charges = cell331 == null ? null : cell331.getStringCellValue();
		if (Charges == null || Charges.isEmpty()){
			WebElement Our111111 = locateElement("xpath","//*[@id='D_div']/table/tbody/tr[2]/td[4]/input");
			click(Our111111);
			driver.manage().window().maximize();
			Set<String> windowId = driver.getWindowHandles();   
			Iterator<String> itererator = windowId.iterator();   
			String mainWinID = itererator.next();
			String  newAdwinID = itererator.next();
			driver.switchTo().window(newAdwinID);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='f_clause']/option[4]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[5]/td[3]/p[1]/input")).click();
			driver.findElement(By.xpath("  //*[@id='insert']")).click();
			driver.switchTo().window(mainWinID);
			System.out.println(driver.getTitle());
//Frame		        
			switchToFramest("work");
		}
		else{
		
			WebElement Our11111 = locateElement("name","SPCL_INSTR");
			type(Our11111,Charges);
			log101111.info(Our11111.getAttribute("value"));
		}
//Type of Message
		
		Logger Bill = Logger.getLogger("Type of Message");
		Row row19 = sheet.getRow(21);
		Cell cell19 = row19.getCell(2);
		String Collection = cell19.getStringCellValue();
		switch(Collection){
		case "MT760":
			WebElement Documents1 = locateElement("name","SW_FORM");
			selectDropDownUsingText(Documents1,Collection);
			Bill.info(Documents1.getAttribute("value"));
//Guarantee Details [MT760: 77C]
			Logger log12 = Logger.getLogger("Guarantee Details [MT760: 77C]");
			Row row3311 = sheet.getRow(21);
			Cell cell3311 = row3311.getCell(6);
			String Charges1 = cell3311 == null ? null : cell3311.getStringCellValue();
			if (Charges1 == null || Charges1.isEmpty()){
				WebElement Our111111 = locateElement("xpath","//*[@id='D_div']/table/tbody/tr[5]/td/table[2]/tbody/tr[2]/td[1]/input");
				click(Our111111);
				driver.manage().window().maximize();
				Set<String> windowId = driver.getWindowHandles();   
				Iterator<String> itererator = windowId.iterator();   
				String mainWinID = itererator.next();
				String  newAdwinID = itererator.next();
				driver.switchTo().window(newAdwinID);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='f_clause']/option[2]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[5]/td[3]/p[1]/input")).click();
				driver.findElement(By.xpath("//*[@id='insert']")).click();
				driver.switchTo().window(mainWinID);
				System.out.println(driver.getTitle());
	//Frame		        
				switchToFramest("work");
			}
			else{
			
				WebElement Our11111 = locateElement("name","GTEE_DETAILS");
				type(Our11111,Charges1);
				log12.info(Our11111.getAttribute("value"));
			}
//Sender to Receiver Information[MT760:72Z]	
			Logger log121 = Logger.getLogger("Guarantee Details [MT760: 77C]");
			Row row33111 = sheet.getRow(23);
			Cell cell33111 = row33111.getCell(2);
			String Charges11 = cell33111 == null ? null : cell33111.getStringCellValue();
			if (Charges11 == null || Charges11.isEmpty()){
				WebElement Our11111 = locateElement("name","BK_TO_BK_INFO");
				click(Our11111);
				log121.info(Our11111.getAttribute("value"));
			}
			else {
				WebElement Our11111 = locateElement("name","BK_TO_BK_INFO");
				type(Our11111,Charges11);
				log121.info(Our11111.getAttribute("value"));
			}
			break;
		case "MT799":
			WebElement Documents11 = locateElement("name","SW_FORM");
			selectDropDownUsingText(Documents11,Collection);
			Bill.info(Documents11.getAttribute("value"));
			break;
		case "MT999":
			WebElement Documents111 = locateElement("name","SW_FORM");
			selectDropDownUsingText(Documents111,Collection);
			Bill.info(Documents111.getAttribute("value"));
			break;
		case "Mail":
			WebElement Documents113 = locateElement("name","SW_FORM");
			selectDropDownUsingText(Documents113,Collection);
			Bill.info(Documents113.getAttribute("value"));
			break;
			
		}
}
}