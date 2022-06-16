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

public class EXCOAmendDischarge extends SeMethods {

	@Test
	public void EXCOAmend () throws IOException, InterruptedException{		
		
//login Page
		
		loginpage();
//IPLC Module		
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import  = locateElement("name", "Export Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "EXCO Amendment");
		click(Function);
		log5.info("EXCO Amendment");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300314F05030701930");
		click(FunctionGroup);
		log6.info("Amend/Discharge");
//Catalog
		EXCOCatalog();
		
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\EXCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Amend");
		workbook.close();  
//MAin

//Amendment Date
		Logger log71 = Logger.getLogger("Amendment Date");
		Row row111 = sheet.getRow(3);
		Cell cell111 = row111.getCell(4);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("name","AMD_DT");
		Clear(ele1);
		WebElement ele11 = locateElement("name","AMD_DT");
		type(ele11,formatter1.format(numberAsString1));
		log71.info(ele11.getAttribute("value"));
		
//Collecting Bank Reference
		Row row = sheet.getRow(3);
		Cell cell = row.getCell(2);
		String invalid = cell.getStringCellValue();
		invalid = (invalid != null ? invalid : "");
		Logger log051 = Logger.getLogger("Drawer Reference");
		WebElement Reference1 = locateElement("name", "COLL_BK_REF");
		Clear(Reference1);
		WebElement Reference = locateElement("name", "COLL_BK_REF");
		type(Reference, invalid);
		log051.info(Reference.getAttribute("value"));
//New Collection Amount
		Logger log811 = Logger.getLogger("New Collection Amount");
		Row row211 = sheet.getRow(3);
		Cell cell211 = row211.getCell(6);
		double Amount = cell211.getNumericCellValue();
		WebElement ele = locateElement("xpath", ".//*[@id='COLL_TRX_CCY_AMT']");
		click(ele);
		WebElement ele811 = locateElement("xpath", ".//*[@id='COLL_TRX_CCY_AMT']");
		type(ele811, Double.toString(Amount));
		log811.info(ele811.getAttribute("value"));
//Send Amendment by
		Logger log911 = Logger.getLogger("Send Amendment by");
		Row row31 = sheet.getRow(5);
		Cell cell31 = row31.getCell(4);
		String Month = cell31.getStringCellValue();
		WebElement Financia = locateElement("name","SEND_AMD_BY");
		selectDropDownUsingText(Financia,Month);
		log911.info(Financia.getAttribute("value"));
//	Discharge Flag
		Logger log21 = Logger.getLogger("Close Flag");
		Row row21 = sheet.getRow(7);
		Cell cell21 = row21.getCell(2);
		String Role11 = cell21.getStringCellValue();
		WebElement Flag = locateElement("name", "DISCHG_FLG");
		selectDropDownUsingText(Flag, Role11);
		log21.info(Flag.getAttribute("value"));
//Amend Type
		Logger log101 = Logger.getLogger("Amendment by");
		Row row1111 = sheet.getRow(7);
		Cell cell1111 = row1111.getCell(4);
		String Role1 = cell1111.getStringCellValue();
		WebElement Our1 = locateElement("name", "AMD_TYPE");
		selectDropDownUsingText(Our1, Role1);
		log101.info(Our1.getAttribute("value"));		
// Collection Type
		Logger log9111 = Logger.getLogger("Collection Type");
		Row row311 = sheet.getRow(5);
		Cell cell311 = row311.getCell(2);
		String Month1 = cell311.getStringCellValue();
		WebElement Financia1 = locateElement("name", "COLL_TYPE");
		selectDropDownUsingText(Financia1, Month1);
		log9111.info(Financia1.getAttribute("value"));
//Deliver Documents Against
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
//Tenor drop down
			try {
				Logger log95 = Logger.getLogger("Tenor Month/day");
				Row row3111 = sheet.getRow(11);
				Cell cell3111 = row3111.getCell(2);
				String Month11 = cell3111.getStringCellValue();
				switch (Month11) {
				case "After date of Bill of Exchange":
				case "After customs clearance of goods":
				case "After goods pass food and drug administration":
				case "First presentation":
				case "After arrival of goods":
				case "After invoice date":
				case "After sight":
				case "After date of transport document":
				case "Fixed Maturity":
					WebElement Financia11 = locateElement("id", "NEW_TENOR_TYPE");
					selectDropDownUsingText(Financia11, Month11);
					log95.info(Financia11.getAttribute("value"));
					break;
				case "See Below":
					WebElement Finance = locateElement("id", "NEW_TENOR_DETAILS");
					selectDropDownUsingText(Finance, Month11);
					log95.info(Finance.getAttribute("value"));
//Tenor Details
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
//Tenor StartDate
			try {
				Logger lo = Logger.getLogger("Tenor StartDate");
				Row row11111 = sheet.getRow(9);
				Cell cell11111 = row11111.getCell(6);
				SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
				Date StartDate = cell11111.getDateCellValue();
				WebElement date = locateElement("id", "NEW_TENOR_START_DT");
				type(date, formatter11.format(StartDate));
				lo.info(date.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//Tenor Maturity Date
			try {
				Logger info = Logger.getLogger("Tenor Maturity Date");
				Row row12 = sheet.getRow(11);
				Cell cell2 = row12.getCell(4);
				SimpleDateFormat Maturity = new SimpleDateFormat("yyyy-MM-dd");
				Date StartDate1 = cell2.getDateCellValue();
				WebElement date1 = locateElement("id", "NEW_DUE_DT");
				type(date1, Maturity.format(StartDate1));
				info.info(date1.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//TeNor
			try {
				Logger log8111 = Logger.getLogger("LC_AMT");
				Row row2111 = sheet.getRow(9);
				Cell cell2111 = row2111.getCell(2);
				int TeNor = (int) cell2111.getNumericCellValue();
				WebElement Financia11 = locateElement("id", "NEW_TENOR_DAYS");
				Clear(Financia11);
				WebElement ele8111 = locateElement("id", "NEW_TENOR_DAYS");
				type(ele8111, Integer.toString(TeNor));
				log8111.info(ele8111.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//Tenor Month/day
			try {
				Logger log91111 = Logger.getLogger("Tenor Month/day");
				Row row3111 = sheet.getRow(9);
				Cell cell3111 = row3111.getCell(4);
				String Month11 = cell3111.getStringCellValue();
				WebElement Financia11 = locateElement("id", "NEW_DAY_MON_FLG");
				selectDropDownUsingText(Financia11, Month11);
				log91111.info(Financia11.getAttribute("value"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
//Instruction
		WebElement Instruction  = locateElement("id","C");
		click(Instruction);				
// Protest For
		Logger log1011 = Logger.getLogger("Protest For");
		Row row131 = sheet.getRow(11);
		Cell cell131 = row131.getCell(6);
		String Charges = cell131.getStringCellValue();
		WebElement Our11 = locateElement("name", "PROT_FOR");
		selectDropDownUsingText(Our11, Charges);
		log1011.info(Our11.getAttribute("value"));
		
//Hold docs until arrival of goods
		
		Logger log11 = Logger.getLogger("Hold docs until arrival of goods");
		Row row52 = sheet.getRow(13);
		Cell cell3311 = row52.getCell(2);
		String Charges1211 = cell3311.getStringCellValue();
		WebElement Our111111 = locateElement("id","HOLD_DOC");
		selectDropDownUsingText(Our111111,Charges1211);
		log11.info(Our111111.getAttribute("value"));	
// Consignee of the goods

		Logger Bill1 = Logger.getLogger("Consignee of the goods");
		Row row191 = sheet.getRow(13);
		Cell cell191 = row191.getCell(4);
		String Bank = cell191.getStringCellValue();
		WebElement Waive = locateElement("name", "GOODS_CONS_TO");
		selectDropDownUsingText(Waive, Bank);
		Bill1.info(Waive.getAttribute("value"));
//Bill Instruction
		Logger Bill2 = Logger.getLogger("Bill Instruction");
		Row row9 = sheet.getRow(13);
		Cell cell2211 = row9.getCell(6);
		String Instruction1 = cell2211.getStringCellValue();
		WebElement Finan = locateElement("id", "BILL_INSTR");
		selectDropDownUsingText(Finan, Instruction1);
		Bill2.info(Finan.getAttribute("value"));
//Store and Insure Goods
		Logger log221 = Logger.getLogger("Store and Insure Goods");
		Row row11221 = sheet.getRow(15);
		Cell cell221 = row11221.getCell(2);
		String Currency111 = cell221.getStringCellValue();
		WebElement Financial111 = locateElement("name","STORE_INSURE_GOODS");
		selectDropDownUsingText(Financial111,Currency111);
		log221.info(Financial111.getAttribute("value"));
// Documents/Goods
		WebElement Goods = locateElement("id", "D");
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
		Row row261 = sheet.getRow(15);
		Cell cell261 = row261.getCell(4);
		String Mail = cell261.getStringCellValue();
		WebElement Method = locateElement("name", "MAIL_METHOD_1ST");
		selectDropDownUsingText(Method, Mail);
		log991.info(Method.getAttribute("value"));
// Mail Method 2st
		Logger log9911 = Logger.getLogger("Mail Method 1st");
		Row row2611 = sheet.getRow(15);
		Cell cell2611 = row2611.getCell(6);
		String Mail1 = cell2611.getStringCellValue();
		WebElement Method1 = locateElement("name", "MAIL_METHOD_2ND");
		selectDropDownUsingText(Method1, Mail1);
		log9911.info(Method1.getAttribute("value"));
// Goods ETA Date
		Logger Date = Logger.getLogger("Goods ETA Date");
		Row row11111 = sheet.getRow(17);
		Cell cell11111 = row11111.getCell(6);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString11 = cell11111.getDateCellValue();
		WebElement ele1111 = locateElement("name", "GOODS_ETA_DT");
		type(ele1111, formatter11.format(numberAsString11));
		Date.info(ele1111.getAttribute("value"));
// Ship From
		Logger log06 = Logger.getLogger("Ship From");
		Row row06 = sheet.getRow(17);
		Cell cell06 = row06.getCell(2);
		String Ship = cell06.getStringCellValue();
		WebElement Method11 = locateElement("name", "LOAD_PORT");
		type(Method11, Ship);
		log06.info(Method11.getAttribute("value"));
// Ship To
		Logger log061 = Logger.getLogger("Ship To");
		Row row061 = sheet.getRow(17);
		Cell cell061 = row061.getCell(4);
		String Ship1 = cell061.getStringCellValue();
		WebElement Method111 = locateElement("name", "DEST_PORT");
		type(Method111, Ship1);
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
			Row row111111 = sheet.getRow(19);
			Cell cell111111 = row111111.getCell(6);
			int Amount11 = (int) cell111111.getNumericCellValue();
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