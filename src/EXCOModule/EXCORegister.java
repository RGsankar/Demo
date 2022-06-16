package EXCOModule;

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

public class EXCORegister extends SeMethods {
	@Test
	public void EXCOREG () throws IOException, InterruptedException{		
		
//login Page
		
		loginpage();
//IPLC Module		
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import  = locateElement("name", "Export Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "EXCO Registration");
		click(Function);
		log5.info("IMCO Registration");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300264F05030701901");
		click(FunctionGroup);
		log6.info("Create Collection");
//Frame 
		 switchToFramest("work");
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\EXCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("EXCOCollection");
		workbook.close();  

//Main		
//Drawer Reference		 
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(2);
		String invalid = cell.getStringCellValue();
		invalid = (invalid != null ? invalid : "");
		Logger log051 = Logger.getLogger("Drawer Reference");
		WebElement Reference1 = locateElement("name", "CUST_REF");
		Clear(Reference1);
		WebElement Reference = locateElement("name", "CUST_REF");
		type(Reference, invalid);
		log051.info(Reference.getAttribute("value"));
// Financial Information
		Logger log9 = Logger.getLogger("Collection Currency and Amount");
		Row row1 = sheet.getRow(3);
		Cell cell1 = row1.getCell(4);
		String Currency = cell1.getStringCellValue();
		WebElement Financial = locateElement("name", "COLL_CCY");
		selectDropDownUsingText(Financial, Currency);
		log9.info(Financial.getAttribute("value"));
// Amount
		Logger log811 = Logger.getLogger("LC_AMT");
		Row row211 = sheet.getRow(3);
		Cell cell211 = row211.getCell(6);
		double Amount = cell211.getNumericCellValue();
		/*WebElement ele = locateElement("xpath", ".//*[@id='COLL_TRX_CCY_AMT']");
		click(ele);
		WebElement ele811 = locateElement("xpath", ".//*[@id='COLL_TRX_CCY_AMT']");
		type(ele811, Double.toString(Amount));
		log811.info(ele811.getAttribute("value"));*/
		driver.findElement(By.name("COLL_TRX_CCY_AMT")).click();
		driver.findElement(By.name("COLL_TRX_CCY_AMT")).sendKeys(Double.toString(Amount));
		log811.info(Amount);
		driver.findElement(By.name("C_MAIN_REF")).click();
		try {
			String alert = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			log811.info("Amount field should not be negative!"+ alert);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sss = driver.findElement(By.name("COLL_TRX_CCY_AMT")).getAttribute("value");
		System.out.println(sss);
		double san22 = Double.parseDouble(sss.replace(",", ""));
		if(san22 < 0) {
		System.out.println("welcome");
		log811.info("Collection field accept Negative value :" + san22);
	}

		
///Copy and past
		WebElement locOfOrder = locateElement("name", "C_MAIN_REF");
		Actions act = new Actions(driver);
		act.moveToElement(locOfOrder).doubleClick().build().perform();
		// now apply copy command
		Logger log16 = Logger.getLogger("Reference Number");
		WebElement san2 = locateElement("name", "C_MAIN_REF");
		san2.sendKeys(Keys.chord(Keys.CONTROL, "c"));

//Excel Sheet Write Value
			 
		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook1.createSheet("Reference Number");
		FileOutputStream outputStream = new FileOutputStream(new File("E:\\Testing\\Baseline\\Ref No\\EXCO.xlsx"));
	    System.out.println(san2.getAttribute("value"));
			 
		sheet1.createRow(6).createCell(4).setCellValue(san2.getAttribute("value"));
		sheet1.createRow(5).createCell(4).setCellValue("EXCO Reference Number :");
				
	    workbook1.write(outputStream);
	    workbook1.close();		
	    log16.info(san2.getAttribute("value"));

//Collection Type
		Logger log911 = Logger.getLogger("Collection Type");
		Row row31 = sheet.getRow(5);
		Cell cell31 = row31.getCell(2);
		String Month = cell31.getStringCellValue();
		WebElement Financia = locateElement("name","COLL_TYPE");
		selectDropDownUsingText(Financia,Month);
		log911.info(Financia.getAttribute("value"));
// Issue Date
		Logger log8 = Logger.getLogger("Remittance Date");
		Row row111 = sheet.getRow(5);
		Cell cell111 = row111.getCell(4);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("id", "REMT_DT");
		type(ele1, formatter1.format(numberAsString1));
		log8.info(ele1.getAttribute("value"));
// Deliver Documents Against
		Logger log91 = Logger.getLogger("Deliver Documents Against	");
		Row row3 = sheet.getRow(5);
		Cell cell3 = row3.getCell(6);
		String Currency1 = cell3.getStringCellValue();
		switch (Currency1) {
		case "D/P":
			WebElement Financial1 = locateElement("id", "DELVR_DOC_AGST");
			selectDropDownUsingText(Financial1, Currency1);
			log91.info(Financial1.getAttribute("value"));
			System.out.println(Currency1);
			break;
		case "D/A":
		case "D/A and Aval":
			WebElement Financia5 = locateElement("id", "DELVR_DOC_AGST");
			selectDropDownUsingText(Financia5, Currency1);
			log91.info(Financia5.getAttribute("value"));
			System.out.println(Currency1);

			// Tenor drop down
			try {
				Logger log95 = Logger.getLogger("Tenor Month/day");
				Row row311 = sheet.getRow(9);
				Cell cell311 = row311.getCell(2);
				String Month1 = cell311.getStringCellValue();
				switch (Month1) {
				case "After date of Bill of Exchange":
				case "After customs clearance of goods":
				case "After goods pass food and drug administration":
				case "First presentation":
				case "After arrival of goods":
				case "After invoice date":
				case "After sight":
				case "After date of transport document":
				case "Fixed Maturity":
					WebElement Financia1 = locateElement("id", "TENOR_TYPE");
					selectDropDownUsingText(Financia1, Month1);
					log95.info(Financia1.getAttribute("value"));
					break;
				case "See Below":
					WebElement Finance = locateElement("id", "TENOR_TYPE");
					selectDropDownUsingText(Finance, Month1);
					log95.info(Finance.getAttribute("value"));
					// Tenor Details
					Logger log60 = Logger.getLogger("TENOR_DETAILS");
					WebElement Details = locateElement("id", "TENOR_DETAILS");
					type(Details, "test");
					log60.info(Details.getAttribute("value"));
					break;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Tenor StartDate
			try {
				Logger lo = Logger.getLogger("Tenor StartDate");
				Row row1111 = sheet.getRow(7);
				Cell cell1111 = row1111.getCell(6);
				SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
				Date StartDate = cell1111.getDateCellValue();
				WebElement date = locateElement("id", "TENOR_START_DT");
				type(date, formatter11.format(StartDate));
				lo.info(date.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Tenor Maturity Date
			try {
				Logger info = Logger.getLogger("Tenor Maturity Date");
				Row row12 = sheet.getRow(9);
				Cell cell2 = row12.getCell(4);
				SimpleDateFormat Maturity = new SimpleDateFormat("yyyy-MM-dd");
				Date StartDate1 = cell2.getDateCellValue();
				WebElement date1 = locateElement("id", "DUE_DT");
				type(date1, Maturity.format(StartDate1));
				info.info(date1.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TeNor
			try {
				Logger log8111 = Logger.getLogger("LC_AMT");
				Row row2111 = sheet.getRow(7);
				Cell cell2111 = row2111.getCell(2);
				int TeNor = (int) cell2111.getNumericCellValue();
				WebElement Financia1 = locateElement("id", "TENOR_DAYS");
				Clear(Financia1);
				WebElement ele8111 = locateElement("id", "TENOR_DAYS");
				type(ele8111, Integer.toString(TeNor));
				log8111.info(ele8111.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Tenor Month/day
			try {
				Logger log9111 = Logger.getLogger("Tenor Month/day");
				Row row311 = sheet.getRow(7);
				Cell cell311 = row311.getCell(4);
				String Month1 = cell311.getStringCellValue();
				WebElement Financia1 = locateElement("id", "DAY_MON_FLG");
				selectDropDownUsingText(Financia1, Month1);
				log9111.info(Financia1.getAttribute("value"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
//Interval (days)	
		Logger log01 = Logger.getLogger("Interval (days)");
		Row row2111 = sheet.getRow(9);
		Cell cell2111 = row2111.getCell(6);
		int TeNor = (int) cell2111.getNumericCellValue();
		WebElement Financia1 = locateElement("name","INTERVAL_DAYS");
		Clear(Financia1);
		WebElement ele8111 = locateElement("name","INTERVAL_DAYS");
		type(ele8111, Integer.toString(TeNor));
		log01.info(ele8111.getAttribute("value"));
	
//Maximum Number
		Logger log011 = Logger.getLogger("Maximum Number");
		Row row03 = sheet.getRow(11);
		Cell cell03 = row03.getCell(2);
		int TeNor1 = (int) cell03.getNumericCellValue();
		WebElement Number = locateElement("name","MAX_TRACER_NO");
		Clear(Number);
		WebElement ele81111 = locateElement("name","MAX_TRACER_NO");
		type(ele81111, Integer.toString(TeNor1));
		log011.info(ele81111.getAttribute("value"));
//Parties
		WebElement Parties  = locateElement("id","B");
		click(Parties);
//Drawer
		WebElement Drawer  = locateElement("name","DRWR_ID_BTN");
		click(Drawer);
		  Set<String> windowId = driver.getWindowHandles();   
	        Iterator<String> itererator = windowId.iterator();   
	        String mainWinID = itererator.next();
	        String  newAdwinID = itererator.next();
	        driver.switchTo().window(newAdwinID);
	        System.out.println(driver.getTitle());
	        driver.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
	        driver.switchTo().window(mainWinID);
	        System.out.println(driver.getTitle());
//Frame		        
		switchToFramest("work");
//Drawee		    
		WebElement Drawer11 = locateElement("name", "DRWE_ID_BTN");
		click(Drawer11);
			Set<String> windowId1 = driver.getWindowHandles();
			Iterator<String> itererator1 = windowId1.iterator();
			String mainWinID1 = itererator1.next();
			String newAdwinID1 = itererator1.next();
			driver.switchTo().window(newAdwinID1);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='4']/td[2]/a")).click();
			driver.switchTo().window(mainWinID1);
			System.out.println(driver.getTitle());
			// Frame
		
			switchToFramest("work");
//Account
		WebElement Account = locateElement("name", "AC2_NO");	
		type(Account,"7895612");
//Collecting Bank
		WebElement Drawer111 = locateElement("name", "COLL_BK_ID_BTN");
		click(Drawer111);
			Set<String> windowId11 = driver.getWindowHandles();
			Iterator<String> itererator11 = windowId11.iterator();
			String mainWinID11 = itererator11.next();
			String newAdwinID11 = itererator11.next();
			driver.switchTo().window(newAdwinID11);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
			driver.switchTo().window(mainWinID11);
			System.out.println(driver.getTitle());
// Frame
			switchToFramest("work");
//Instruction
		WebElement Instruction  = locateElement("id","C");
		click(Instruction);		
//Waive Instruction				
		Logger log1011 = Logger.getLogger("Waive Instruction");
		Row row1311 = sheet.getRow(11);
		Cell cell1311 = row1311.getCell(4);
		String Charges1 = cell1311.getStringCellValue();
		WebElement Our11 = locateElement("id","WAIVE_INSTRUCTION");
		selectDropDownUsingText(Our11,Charges1);
		log1011.info(Our11.getAttribute("value"));
//Protest For
		Logger log101 = Logger.getLogger("Protest For");
		Row row131 = sheet.getRow(11);
		Cell cell131 = row131.getCell(6);
		String Charges = cell131.getStringCellValue();
		WebElement Our1 = locateElement("name","PROT_FOR");
		selectDropDownUsingText(Our1,Charges);
		log101.info(Our1.getAttribute("value"));
//Release Documents Against
		Logger log20 = Logger.getLogger("Release Documents Against");
		Row row13111 = sheet.getRow(13);
		Cell cell13111 = row13111.getCell(2);
		String Release = cell13111.getStringCellValue();
		WebElement Documents = locateElement("id","DOC_INSTR");
		selectDropDownUsingText(Documents,Release);
		log20.info(Documents.getAttribute("value"));
		
//Collection interest
		Logger Bill = Logger.getLogger("Collection interest");
		Row row19 = sheet.getRow(13);
		Cell cell19 = row19.getCell(4);
		String Collection = cell19.getStringCellValue();
		switch(Collection){
		case "No":
			WebElement Documents1 = locateElement("name","COLL_INT_FLG");
			selectDropDownUsingText(Documents1,Collection);
			Bill.info(Documents1.getAttribute("value"));
			break;
		case "Yes":
			WebElement Documents11 = locateElement("name","COLL_INT_FLG");
			selectDropDownUsingText(Documents11,Collection);
			Bill.info(Documents11.getAttribute("value"));
//Interest Rate
			Logger log8111 = Logger.getLogger("Interest Rate");
			Row row21111 = sheet.getRow(13);
			Cell cell21111 = row21111.getCell(6);
			int TeNor11 = (int) cell21111.getNumericCellValue();
			WebElement ele2 = locateElement("id","INT_RT");
			type(ele2, Integer.toString(TeNor11));
			log8111.info(ele2.getAttribute("value"));
//Interest From
			Logger log22 = Logger.getLogger("Interest From");
			Row row1122 = sheet.getRow(15);
			Cell cell22 = row1122.getCell(2);
			String Currency11 = cell22.getStringCellValue();
			WebElement Financial11 = locateElement("id","INT_FM_PRD");
			selectDropDownUsingText(Financial11,Currency11);
			log22.info(Financial11.getAttribute("value"));
//Waive interest if refused			
			Logger Bill1 = Logger.getLogger("Waive interest if refused");
			Row row191 = sheet.getRow(15);
			Cell cell191 = row191.getCell(4);
			String Bank = cell191.getStringCellValue();
			WebElement Waive = locateElement("name","WAIVE_INT_REFUSED");
			selectDropDownUsingText(Waive,Bank);
			Bill1.info(Waive.getAttribute("value"));
//Interest Days Basis
			Logger log10111 = Logger.getLogger("Interest Days Basis");
			Row row33 = sheet.getRow(15);
			Cell cell33 = row33.getCell(6);
			int Interest = (int) cell33.getNumericCellValue();
			WebElement ele811111 = locateElement("id","INT_DAYS_BASIS");
			type(ele811111, Integer.toString(Interest));
			log10111.info(ele811111.getAttribute("value"));
//Interest To
			Logger log101111 = Logger.getLogger("Interest To");
			Row row331 = sheet.getRow(17);
			Cell cell331 = row331.getCell(2);
			String Charges121 = cell331.getStringCellValue();
			WebElement Our11111 = locateElement("id","INT_TO_PRD");
			selectDropDownUsingText(Our11111,Charges121);
			log101111.info(Our11111.getAttribute("value"));
			break;
		}
//Hold docs until arrival of goods
		
		Logger log11 = Logger.getLogger("Hold docs until arrival of goods");
		Row row52 = sheet.getRow(17);
		Cell cell3311 = row52.getCell(4);
		String Charges1211 = cell3311.getStringCellValue();
		WebElement Our111111 = locateElement("id","HOLD_DOC");
		selectDropDownUsingText(Our111111,Charges1211);
		log11.info(Our111111.getAttribute("value"));	
		
//Store and Insure Goods
		
		Logger log10111 = Logger.getLogger("Store and Insure Goods");
		Row row33 = sheet.getRow(17);
		Cell cell33 = row33.getCell(6);
		String Charges12 = cell33.getStringCellValue();
		WebElement Our1111 = locateElement("id","STORE_INSURE_GOODS");
		selectDropDownUsingText(Our1111,Charges12);
		log10111.info(Our1111.getAttribute("value"));
//Consignee of the goods
		
		Logger Bill1 = Logger.getLogger("Consignee of the goods");
		Row row191 = sheet.getRow(19);
		Cell cell191 = row191.getCell(2);
		String Bank = cell191.getStringCellValue();
		WebElement Waive = locateElement("name","GOODS_CONS_TO");
		selectDropDownUsingText(Waive,Bank);
		Bill1.info(Waive.getAttribute("value"));
		
//Collecting Bank Charges For
		
		Logger log22 = Logger.getLogger("Collecting Bank Charges For");
		Row row1122 = sheet.getRow(19);
		Cell cell22 = row1122.getCell(4);
		String Currency11 = cell22.getStringCellValue();
		WebElement Financial11 = locateElement("id","REMIT_BK_CHG_FLG");
		selectDropDownUsingText(Financial11,Currency11);
		log22.info(Financial11.getAttribute("value"));
		
//Waive Collecting Bank Charges
		
		Logger log221 = Logger.getLogger("Waive Collecting Bank Charges");
		Row row11221 = sheet.getRow(19);
		Cell cell221 = row11221.getCell(6);
		String Currency111 = cell221.getStringCellValue();
		WebElement Financial111 = locateElement("name","WAIVE_REMT_BK_CHG_FLG");
		selectDropDownUsingText(Financial111,Currency111);
		log221.info(Financial111.getAttribute("value"));
		
//Bill Instruction
		Logger Bill2 = Logger.getLogger("Bill Instruction");
		Row row9 = sheet.getRow(21);
		Cell cell2211 = row9.getCell(2);
		String Instruction1 = cell2211.getStringCellValue();
		WebElement Finan = locateElement("id","BILL_INSTR");
		selectDropDownUsingText(Finan,Instruction1);
		Bill2.info(Finan.getAttribute("value"));
		
//Documents/Goods
		WebElement Goods  = locateElement("id","D");
		click(Goods);
// Document

// Draft
		Logger Document = Logger.getLogger("Draft");
		WebElement Draft = locateElement("name", "DRAFT_1");
		type(Draft, "5");
		Document.info(Draft.getAttribute("value"));
		WebElement Draft1 = locateElement("name", "DRAFT_2");
		type(Draft1, "5");
		Document.info(Draft1.getAttribute("value"));
// INVOICE
		Logger Document1 = Logger.getLogger("INVOICE");
		WebElement INVOICE = locateElement("name", "INVOICE_1");
		type(INVOICE, "5");
		Document1.info(INVOICE.getAttribute("value"));
		WebElement INVOICE1 = locateElement("name", "INVOICE_2");
		type(INVOICE1, "5");
		Document1.info(INVOICE1.getAttribute("value"));
// BL/AWB
		Logger Document11 = Logger.getLogger("BL/AWB");
		WebElement Invoices = locateElement("name", "BL_AWB_1");
		type(Invoices, "5");
		Document11.info(Invoices.getAttribute("value"));
		WebElement Invoices1 = locateElement("name", "BL_AWB_2");
		type(Invoices1, "5");
		Document11.info(Invoices1.getAttribute("value"));
// CERTIFICATE
		Logger Document111 = Logger.getLogger("CERTIFICATE");
		WebElement BLAWB = locateElement("name", "CERTIFICATE_1");
		type(BLAWB, "5");
		Document111.info(BLAWB.getAttribute("value"));
		WebElement BLAWB1 = locateElement("name", "CERTIFICATE_2");
		type(BLAWB1, "5");
		Document111.info(BLAWB1.getAttribute("value"));
// CertOrigin
		Logger Document2 = Logger.getLogger("INSP_CERT");
		WebElement CertOrigin = locateElement("name", "INSP_CERT_1");
		type(CertOrigin, "5");
		Document2.info(CertOrigin.getAttribute("value"));
		WebElement CertOrigin1 = locateElement("name", "INSP_CERT_2");
		type(CertOrigin1, "5");
		Document2.info(CertOrigin1.getAttribute("value"));
// PackList
		Logger Document21 = Logger.getLogger("PACK_LIST");
		WebElement PackList = locateElement("name", "PACK_LIST_1");
		type(PackList, "5");
		Document21.info(PackList.getAttribute("value"));
		WebElement PackList1 = locateElement("name", "PACK_LIST_2");
		type(PackList1, "5");
		Document21.info(PackList1.getAttribute("value"));
// INSURANCE
		Logger Document211 = Logger.getLogger("INSURANCE");
		WebElement INSURANCE = locateElement("name", "INSURANCE_1");
		type(INSURANCE, "5");
		Document211.info(INSURANCE.getAttribute("value"));
		WebElement INSURANCE1 = locateElement("name", "INSURANCE_2");
		type(INSURANCE1, "5");
		Document211.info(INSURANCE1.getAttribute("value"));
// Vessel Cert
		Logger Document3 = Logger.getLogger("Vessel Cert");
		WebElement Vessel = locateElement("name", "VESSEL_CERT_1");
		type(Vessel, "5");
		Document211.info(Vessel.getAttribute("value"));
		WebElement Vessel1 = locateElement("name", "VESSEL_CERT_2");
		type(Vessel1, "5");
		Document3.info(Vessel1.getAttribute("value"));
// Freight Inv
		Logger Document31 = Logger.getLogger("Freight Inv");
		WebElement Freight = locateElement("name", "FREIGHT_INV_1");
		type(Freight, "5");
		Document211.info(Freight.getAttribute("value"));
		WebElement Freight1 = locateElement("name", "FREIGHT_INV_2");
		type(Freight1, "5");
		Document31.info(Freight1.getAttribute("value"));
// Beneficiary Cert
		Logger Document311 = Logger.getLogger("Beneficiary Cert");
		WebElement Beneficiary = locateElement("name", "BENEF_CERT_1");
		type(Beneficiary, "5");
		Document211.info(Beneficiary.getAttribute("value"));
		WebElement Beneficiary1 = locateElement("name", "BENEF_CERT_2");
		type(Beneficiary1, "5");
		Document311.info(Beneficiary1.getAttribute("value"));
// OTHERS
		Logger Document4 = Logger.getLogger("OTHERS");
		WebElement OTHERS = locateElement("name", "OTHERS_1");
		type(OTHERS, "5");
		Document211.info(OTHERS.getAttribute("value"));
		WebElement OTHERS1 = locateElement("name", "OTHERS_2");
		type(OTHERS1, "5");
		Document4.info(OTHERS1.getAttribute("value"));

// Mail Method 1st
		Logger log991 = Logger.getLogger("Mail Method 1st");
		Row row261 = sheet.getRow(21);
		Cell cell261 = row261.getCell(4);
		String Mail = cell261.getStringCellValue();
		WebElement Method = locateElement("name", "MAIL_METHOD_1ST");
		selectDropDownUsingText(Method, Mail);
		log991.info(Method.getAttribute("value"));
// Mail Method 2st
		Logger log9911 = Logger.getLogger("Mail Method 1st");
		Row row2611 = sheet.getRow(21);
		Cell cell2611 = row2611.getCell(6);
		String Mail1 = cell2611.getStringCellValue();
		WebElement Method1 = locateElement("name", "MAIL_METHOD_2ND");
		selectDropDownUsingText(Method1, Mail1);
		log9911.info(Method1.getAttribute("value"));
// Goods ETA Date
		Logger Date = Logger.getLogger("Goods ETA Date");
		Row row1111 = sheet.getRow(23);
		Cell cell1111 = row1111.getCell(6);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString11 = cell1111.getDateCellValue();
		WebElement ele1111 = locateElement("name", "GOODS_ETA_DT");
		type(ele1111, formatter11.format(numberAsString11));
		Date.info(ele1111.getAttribute("value"));
// Ship From
		Logger log06 = Logger.getLogger("Ship From");
		Row row06 = sheet.getRow(23);
		Cell cell06 = row06.getCell(2);
		String Ship = cell06.getStringCellValue();
		WebElement Method11 = locateElement("name", "SHIP_FM_CNTY_CODE");
		selectDropDownUsingText(Method11, Ship);
		log06.info(Method11.getAttribute("value"));
// Ship To
		Logger log061 = Logger.getLogger("Ship To");
		Row row061 = sheet.getRow(23);
		Cell cell061 = row061.getCell(4);
		String Ship1 = cell061.getStringCellValue();
		WebElement Method111 = locateElement("name", "SHIP_TO_CNTY_CODE");
		selectDropDownUsingText(Method111, Ship1);
		log061.info(Method111.getAttribute("value"));
// Goods Shipped By
		WebElement Shipped = locateElement("name", "SHIPPED_BY");
		type(Shipped, "Ship");
// Transport Doc No.
		WebElement Transport = locateElement("name", "TRNSPT_DOC_NO");
		type(Transport, "763990545");
// Goods Description
		WebElement Description = locateElement("name", "GOODS_DESC");
		type(Description, "Test");
// Charges
		WebElement Charges2 = locateElement("id", "E");
		click(Charges2);
// Paid By
		Logger log0611 = Logger.getLogger("Paid By");
		Row row0611 = sheet.getRow(25);
		Cell cell0611 = row0611.getCell(2);
		String Paid = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111, Paid);
		log0611.info(Method1111.getAttribute("value"));
// PaidAt
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(25);
		Cell cell07 = row07.getCell(4);
		String PaidAt = cell07.getStringCellValue();
		switch (PaidAt) {
		case "TRANSACTION":
			WebElement Method3 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3, PaidAt);
			log07.info(Method3.getAttribute("value"));
			Row row11111 = sheet.getRow(25);
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
// Advice

		WebElement Advice = locateElement("id", "F");
		click(Advice);
		Advice();
// Diary
		WebElement Diary = locateElement("id", "H");
		click(Diary);
		Diary();
// confirm
		// acceptAlert();
		Confirm();
// supervisor Release
		EXCOSupervisorRelease();

}
}

