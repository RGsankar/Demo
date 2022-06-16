package Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
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

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class IMCOAmend extends SeMethods{
	/*public RemoteWebDriver GetDriver()
	{
		return driver;
	}*/
	public Date numberAsString1;
	@Test
	public void IMCOAMD () throws IOException, InterruptedException, ATUTestRecorderException, ParseException{		
//login Page
		
		loginpage();
//IMPL Amendment
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Import Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IMCO Amendment");
		click(Function);
		log5.info("IMCO Amendment");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300293F05030701643");
		click(FunctionGroup);
		log6.info("Amend/Discharge");
//catalog page	
			Catalog();
//Excel Sheet get Value
				FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheet("Amend");
				workbook.close();
// Main

//Amendment Date
		//acceptAlert();
		Logger log71 = Logger.getLogger("Amendment Date");
		Row row111 = sheet.getRow(3);
		Cell cell111 = row111.getCell(2);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("id","AMD_DT");
		Clear(ele1);
		WebElement ele11 = locateElement("id","AMD_DT");
		type(ele11,formatter1.format(numberAsString1));
		log71.info(ele11.getAttribute("value"));
		
//Amendment Type
		Logger log10 = Logger.getLogger("Collection Currency and Amount");
		Row row11 = sheet.getRow(3);
		Cell cell11 = row11.getCell(6);
		String Role = cell11.getStringCellValue();
		WebElement Our = locateElement("name", "AMD_TYPE");
		selectDropDownUsingText(Our, Role);
		log10.info(Our.getAttribute("value"));
		
//Remitting Party Reference		
		Logger log711 = Logger.getLogger("Remitting Party Reference");
		Row row1 = sheet.getRow(3);
		Cell cell1 = row1.getCell(4);
		String Referenceno = cell1.getStringCellValue();
		WebElement Reference = locateElement("id","REMIT_BK_REF");
		Clear(Reference);
		WebElement Reference1 = locateElement("name", "COLL_NO");
		type(Reference1, Referenceno);
		log711.info(Reference1.getAttribute("value"));
//Send Amendment by
		Logger log101 = Logger.getLogger("Send Amendment by");
		Row row1111 = sheet.getRow(5);
		Cell cell1111 = row1111.getCell(2);
		String Role1 = cell1111.getStringCellValue();
		WebElement Our1 = locateElement("name", "SEND_AMD_BY");
		selectDropDownUsingText(Our1, Role1);
		log101.info(Our1.getAttribute("value"));
//Close Flag
		
		Logger log21 = Logger.getLogger("Close Flag");
		Row row21 = sheet.getRow(5);
		Cell cell21 = row21.getCell(4);
		String Role11 = cell21.getStringCellValue();
		WebElement Flag = locateElement("name", "CLS_FLG");
		selectDropDownUsingText(Flag, Role11);
		log21.info(Flag.getAttribute("value"));
//Release Order Reference		
		Logger log211 = Logger.getLogger("Release Order Reference");
		Row row2111 = sheet.getRow(5);
		Cell cell2111 = row2111.getCell(6);
		int TeNor = (int) cell2111.getNumericCellValue();
		WebElement ele8111 = locateElement("name","RLS_ORDER_REF");
		type(ele8111, Integer.toString(TeNor));
		log211.info(ele8111.getAttribute("value"));
//Amendment Number(correspondence)
		Logger log2111 = Logger.getLogger("Amendment Number(correspondence)");
		Row row21111 = sheet.getRow(7);
		Cell cell21111 = row21111.getCell(2);
		int Number = (int) cell21111.getNumericCellValue();
		WebElement ele81111 = locateElement("name","NO_OF_AMD_B");
		Clear(ele81111);
		WebElement ele811111 = locateElement("name","NO_OF_AMD_B");
		type(ele811111, Integer.toString(Number));
		log2111.info(ele811111.getAttribute("value"));
//New Collection Amount		
		Logger log811 = Logger.getLogger("New Collection Amount");
		Row row211 = sheet.getRow(7);
		Cell cell211 = row211.getCell(4);
		double Amount = cell211.getNumericCellValue();
		WebElement ele = locateElement("name", "NEW_COL_AMT_NCOL_CCY");
		click(ele);
		WebElement ele811 = locateElement("name", "NEW_COL_AMT_NCOL_CCY");
		type(ele811, Double.toString(Amount));
		log811.info(ele811.getAttribute("value"));
//New Deliver Documents Against		
		Logger log55 = Logger.getLogger("New Deliver Documents Against");
		Row row55 =sheet.getRow(7);
		Cell cell55 = row55.getCell(6);
		String Deliver = cell55.getStringCellValue();
		switch(Deliver){
		case "D/P":
			WebElement Financia = locateElement("name","NEW_DELVR_DOC_AGST");
			selectDropDownUsingText(Financia,Deliver);
			log55.info(Financia.getAttribute("value"));
			break;
		case "D/A":
		case "D/A and Aval":
			WebElement Financia1 = locateElement("name","NEW_DELVR_DOC_AGST");
			selectDropDownUsingText(Financia1,Deliver);
			log55.info(Financia1.getAttribute("value"));
			Logger log95 = Logger.getLogger("Tenor Month/day");
			Row row311 = sheet.getRow(11);
			Cell cell311 = row311.getCell(2);
			String Month1 = cell311.getStringCellValue();
			switch (Month1){
			case"After date of Bill of Exchange":
			case"After customs clearance of goods":
			case"After goods pass food and drug administration":
			case"First presentation":
			case"After arrival of goods":
			case"After invoice date":
			case"After sight":
			case"After date of transport document":
			case"See Below":
				WebElement Finance = locateElement("id","NEW_TENOR_EVENT");
				selectDropDownUsingText(Finance,Month1);
				log95.info(Finance.getAttribute("value"));
				break;
			case"Fixed Maturity":
				WebElement Finance1 = locateElement("id","NEW_TENOR_EVENT");
				selectDropDownUsingText(Finance1,Month1);
				log95.info(Finance1.getAttribute("value"));
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
				break;
			}
//New Tenor Start Date
			try {
				Logger lo = Logger.getLogger("Tenor StartDate");
				Row row11111 = sheet.getRow(9);
				Cell cell11111 = row11111.getCell(2);
				SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
				Date StartDate = cell11111.getDateCellValue();
				WebElement date = locateElement("id","NEW_TENOR_START_DT");
				type(date,formatter11.format(StartDate));
				lo.info(date.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//New Tenor day
			try {
				Logger log8111 = Logger.getLogger("New Tenor day");
				Row row21113 = sheet.getRow(9);
				Cell cell21113 = row21113.getCell(4);
				int TeNor1 = (int) cell21113.getNumericCellValue();
				WebElement Financia11 = locateElement("id","NEW_TENOR_DAYS");
				Clear(Financia11);
				WebElement ele81113 = locateElement("id","NEW_TENOR_DAYS");
				type(ele81113, Integer.toString(TeNor1));
				log8111.info(ele81113.getAttribute("value"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Tenor Month/day			
			try {
				Logger log911 = Logger.getLogger("Tenor Month/day");
				Row row31 = sheet.getRow(9);
				Cell cell31 = row31.getCell(6);
				String Month = cell31.getStringCellValue();
				WebElement Finance1 = locateElement("id","NEW_DAY_MON_FLG");
				selectDropDownUsingText(Finance1,Month);
				log911.info(Finance1.getAttribute("value"));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

//Parties
		WebElement Finance1 = locateElement("id","B");
		click(Finance1);
//Reference Number
		Logger log1011 = Logger.getLogger("Amendment Charges For");
		WebElement Number1 = locateElement("id","PRES_BK_REF");
		type(Number1,"45646441");
		log1011.info(Number1.getAttribute("value"));
//Instruction
		WebElement Instruction  = locateElement("id","C");
		click(Instruction);
//Amendment Charges For
		Logger log88 = Logger.getLogger("Amendment Charges For");
		Row row131 = sheet.getRow(11);
		Cell cell131 = row131.getCell(6);
		String Charges = cell131.getStringCellValue();
		WebElement Our11 = locateElement("id","CHG_FLG");
		selectDropDownUsingText(Our11,Charges);
		log88.info(Our11.getAttribute("value"));
//Waive Instruction				
		Logger log10111 = Logger.getLogger("Waive Instruction");
		Row row1311 = sheet.getRow(13);
		Cell cell1311 = row1311.getCell(2);
		String Charges1 = cell1311.getStringCellValue();
		WebElement Our111 = locateElement("id","WAIVE_INSTRUCTION");
		selectDropDownUsingText(Our111,Charges1);
		log10111.info(Our111.getAttribute("value"));
//Protest For
		Logger log20 = Logger.getLogger("Protest For");
		Row row13111 = sheet.getRow(13);
		Cell cell13111 = row13111.getCell(4);
		String Release = cell13111.getStringCellValue();
		WebElement Documents = locateElement("name","PROT_FOR");
		selectDropDownUsingText(Documents,Release);
		log20.info(Documents.getAttribute("value"));
//Bill Instruction
		Logger Bill = Logger.getLogger("Bill Instruction");
		Row row19 = sheet.getRow(13);
		Cell cell19 = row19.getCell(6);
		String Instruction1 = cell19.getStringCellValue();
		WebElement Our1111 = locateElement("name","BILL_INSTR");
		selectDropDownUsingText(Our1111,Instruction1);
		Bill.info(Our1111.getAttribute("value"));
//Remitting Bank Charges Currency and Amount			
		Logger log8111 = Logger.getLogger("Remitting Bank Charges Currency and Amount");
		Row row32 = sheet.getRow(15);
		Cell cell32 = row32.getCell(2);
		int Amount1 = (int) cell32.getNumericCellValue();
		if((Integer.toString(Amount1)==null)||(Amount1==0)){
			//double Amount1 = cell32.getNumericCellValue();
			WebElement ele3 = locateElement("id", "REMIT_BK_CHG_AMT");
			click(ele3);
			WebElement ele83 = locateElement("id", "REMIT_BK_CHG_AMT");
			type(ele83, Double.toString(Amount1));
			log8111.info(ele83.getAttribute("value"));
		}
		else{
			WebElement ele3 = locateElement("id", "REMIT_BK_CHG_AMT");
			click(ele3);
			WebElement ele83 = locateElement("id", "REMIT_BK_CHG_AMT");
			type(ele83, Double.toString(Amount1));
			log8111.info(ele83.getAttribute("value"));
		
//Remitting Bank Charges Currency	
		Logger log22 = Logger.getLogger("Collection Currency and Amount");
		Row row1122 = sheet.getRow(15);
		Cell cell22 = row1122.getCell(4);
		String Currency11 = cell22.getStringCellValue();
		WebElement Financial11 = locateElement("id","REMIT_BK_CHG_CCY");
		selectDropDownUsingText(Financial11,Currency11);
		log22.info(Financial11.getAttribute("value"));
		
//Waive instructions			
		Logger Bill1 = Logger.getLogger("Waive instructions");
		Row row191 = sheet.getRow(17);
		Cell cell191 = row191.getCell(2);
		String Bank = cell191.getStringCellValue();
		WebElement Waive = locateElement("name","WAIVE_REMT_BK_CHG_FLG");
		selectDropDownUsingText(Waive,Bank);
		Bill1.info(Waive.getAttribute("value"));
		
//Remitting Bank Charges For			
		Logger log101111 = Logger.getLogger("Remitting Bank Charges For	");
		Row row33 = sheet.getRow(15);
		Cell cell33 = row33.getCell(6);
		String Charges12 = cell33.getStringCellValue();
		WebElement Our11111 = locateElement("id","REMIT_BK_CHG_FLG");
		selectDropDownUsingText(Our11111,Charges12);
		log101111.info(Our11111.getAttribute("value"));
		}
//Amendment
		WebElement Amendment  = locateElement("id","I");
		click(Amendment);
//Narrative (MT999: 79)
		try {
			WebElement Narrative  = locateElement("id","NARR");
			type(Narrative,"test");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//Documents/Goods
		WebElement Goods  = locateElement("id","D");
		click(Goods);
//Store and Insure Goods
		Logger Bill11 = Logger.getLogger("Store and Insure Goods");
		Row row1911 = sheet.getRow(17);
		Cell cell1911 = row1911.getCell(6);
		String Bank1 = cell1911.getStringCellValue();
		WebElement Our3 = locateElement("name", "STORE_INSURE_GOODS");
		selectDropDownUsingText(Our3, Bank1);
		Bill11.info(Our3.getAttribute("value"));
		
//Hold docs
		Logger san1 = Logger.getLogger("Hold docs");
		Row Hold1 = sheet.getRow(17);
		Cell Hold11 = Hold1.getCell(4);
		String docs = Hold11.getStringCellValue();
		WebElement Hold = locateElement("name", "HOLD_DOC");
		selectDropDownUsingText(Hold, docs);
		san1.info(Hold.getAttribute("value"));
//Consignee of the goods		
		Logger Bill5 = Logger.getLogger("Consignee of the goods");
		Row row15 = sheet.getRow(19);
		Cell cell9 = row15.getCell(2);
		String Trust = cell9.getStringCellValue();
		WebElement Our4 = locateElement("name","GOODS_CONS_TO");
		selectDropDownUsingText(Our4,Trust);
		Bill5.info(Our4.getAttribute("value"));
//Document

//Draft			
		Logger Document = Logger.getLogger("Draft");
		WebElement Draft = locateElement("name", "DRAFT_1");
		type(Draft,"5");
		Document.info(Draft.getAttribute("value"));
		WebElement Draft1 = locateElement("name", "DRAFT_2");
		type(Draft1,"5");
		Document.info(Draft1.getAttribute("value"));
//INVOICE			
		Logger Document1 = Logger.getLogger("INVOICE");
		WebElement INVOICE = locateElement("name", "INVOICE_1");
		type(INVOICE,"5");
		Document1.info(INVOICE.getAttribute("value"));
		WebElement INVOICE1 = locateElement("name", "INVOICE_2");
		type(INVOICE1,"5");
		Document1.info(INVOICE1.getAttribute("value"));
//BL/AWB			
		Logger Document11 = Logger.getLogger("BL/AWB");
		WebElement Invoices = locateElement("name", "BL_AWB_1");
		type(Invoices,"5");
		Document11.info(Invoices.getAttribute("value"));
		WebElement Invoices1 = locateElement("name", "BL_AWB_2");
		type(Invoices1,"5");
		Document11.info(Invoices1.getAttribute("value"));
//CERTIFICATE			
		Logger Document111 = Logger.getLogger("CERTIFICATE");
		WebElement BLAWB = locateElement("name", "CERTIFICATE_1");
		type(BLAWB,"5");
		Document111.info(BLAWB.getAttribute("value"));
		WebElement BLAWB1 = locateElement("name", "CERTIFICATE_2");
		type(BLAWB1,"5");
		Document111.info(BLAWB1.getAttribute("value"));
//CertOrigin			
		Logger Document2 = Logger.getLogger("INSP_CERT");
		WebElement CertOrigin = locateElement("name", "INSP_CERT_1");
		type(CertOrigin,"5");
		Document2.info(CertOrigin.getAttribute("value"));
		WebElement CertOrigin1 = locateElement("name", "INSP_CERT_2");
		type(CertOrigin1,"5");
		Document2.info(CertOrigin1.getAttribute("value"));
//PackList			
		Logger Document21 = Logger.getLogger("PACK_LIST");
		WebElement PackList = locateElement("name", "PACK_LIST_1");
		type(PackList,"5");
		Document21.info(PackList.getAttribute("value"));
		WebElement PackList1 = locateElement("name", "PACK_LIST_2");
		type(PackList1,"5");
		Document21.info(PackList1.getAttribute("value"));
//INSURANCE			
		Logger Document211 = Logger.getLogger("INSURANCE");
		WebElement INSURANCE = locateElement("name", "INSURANCE_1");
		type(INSURANCE,"5");
		Document211.info(INSURANCE.getAttribute("value"));
		WebElement INSURANCE1 = locateElement("name", "INSURANCE_2");
		type(INSURANCE1,"5");
		Document211.info(INSURANCE1.getAttribute("value"));
//Vessel Cert
		Logger Document3 = Logger.getLogger("INSURANCE");
		WebElement Vessel = locateElement("name", "VESSEL_CERT_1");
		type(Vessel,"5");
		Document211.info(Vessel.getAttribute("value"));
		WebElement Vessel1 = locateElement("name", "VESSEL_CERT_2");
		type(Vessel1,"5");
		Document3.info(Vessel1.getAttribute("value"));
//Freight Inv
		Logger Document31 = Logger.getLogger("INSURANCE");
		WebElement Freight = locateElement("name", "FREIGHT_INV_1");
		type(Freight,"5");
		Document211.info(Freight.getAttribute("value"));
		WebElement Freight1 = locateElement("name", "FREIGHT_INV_2");
		type(Freight1,"5");
		Document31.info(Freight1.getAttribute("value"));
//Beneficiary Cert
		Logger Document311 = Logger.getLogger("INSURANCE");
		WebElement Beneficiary = locateElement("name", "BENEF_CERT_1");
		type(Beneficiary,"5");
		Document211.info(Beneficiary.getAttribute("value"));
		WebElement Beneficiary1 = locateElement("name", "BENEF_CERT_2");
		type(Beneficiary1,"5");
		Document311.info(Beneficiary1.getAttribute("value"));
//OTHERS
		Logger Document4 = Logger.getLogger("INSURANCE");
		WebElement OTHERS = locateElement("name", "OTHERS_1");
		type(OTHERS,"5");
		Document211.info(OTHERS.getAttribute("value"));
		WebElement OTHERS1 = locateElement("name", "OTHERS_2");
		type(OTHERS1,"5");
		Document4.info(OTHERS1.getAttribute("value"));
//Consignee of the goods		
		Logger log99 = Logger.getLogger("Consignee of the goods");
		Row row26 = sheet.getRow(19);
		Cell cell26 = row26.getCell(2);
		String Consignee = cell26.getStringCellValue();
		WebElement goods = locateElement("name","GOODS_CONS_TO");
		selectDropDownUsingText(goods,Consignee);
		log99.info(goods.getAttribute("value"));
//Mail Method 1st
		Logger log991 = Logger.getLogger("Mail Method 1st");
		Row row261 = sheet.getRow(21);
		Cell cell261 = row261.getCell(4);
		String Mail = cell261.getStringCellValue();
		WebElement Method = locateElement("name","MAIL_METHOD_1ST");
		selectDropDownUsingText(Method,Mail);
		log991.info(Method.getAttribute("value"));
//Mail Method 2st
		Logger log9911 = Logger.getLogger("Mail Method 1st");
		Row row2611 = sheet.getRow(21);
		Cell cell2611 = row2611.getCell(6);
		String Mail1 = cell2611.getStringCellValue();
		WebElement Method1 = locateElement("name","MAIL_METHOD_2ND");
		selectDropDownUsingText(Method1,Mail1);
		log9911.info(Method1.getAttribute("value"));
//Goods ETA Date
		Logger Date = Logger.getLogger("Goods ETA Date");
		Row row136 = sheet.getRow(21);
		Cell cell11111 = row136.getCell(2);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString11 = cell11111.getDateCellValue();
		WebElement ele111 = locateElement("name","GOODS_ETA_DT");
		type(ele111,formatter11.format(numberAsString11));
		Date.info(ele111.getAttribute("value"));
//Ship From	
		Logger log06 = Logger.getLogger("Ship From");
		Row row06 = sheet.getRow(23);
		Cell cell06 = row06.getCell(4);
		String Ship = cell06.getStringCellValue();
		WebElement Method11 = locateElement("name","SHIP_FM_CNTY_CODE");
		selectDropDownUsingText(Method11,Ship);
		log06.info(Method11.getAttribute("value"));
//Ship To			
		Logger log061 = Logger.getLogger("Ship To");
		Row row061 = sheet.getRow(23);
		Cell cell061 = row061.getCell(6);
		String Ship1 = cell061.getStringCellValue();
		WebElement Method111 = locateElement("name","SHIP_TO_CNTY_CODE");
		selectDropDownUsingText(Method111,Ship1);
		log061.info(Method111.getAttribute("value"));
//Transport Doc No.		
		Logger log223 = Logger.getLogger("Transport Doc No.");
		Row row23 = sheet.getRow(19);
		Cell cell98 = row23.getCell(6);
		int Shipped = (int) cell98.getNumericCellValue();
		WebElement ele65 = locateElement("name","TRNSPT_DOC_NO");
		Clear(ele65);
		WebElement ele25 = locateElement("name","TRNSPT_DOC_NO");
		type(ele25, Integer.toString(Shipped));
		log223.info(ele25.getAttribute("value"));
//Goods Shipped By
		Logger log0611 = Logger.getLogger("Ship To");
		Row row0611 = sheet.getRow(19);
		Cell cell0611 = row0611.getCell(4);
		String Ship11 = cell0611.getStringCellValue();
		WebElement Transport = locateElement("name","SHIPPED_BY");
		Clear(Transport);
		WebElement Transport1 = locateElement("name","SHIPPED_BY");
		type(Transport1,Ship11);
		log0611.info(Transport1.getAttribute("value"));
//Goods Description	
		WebElement Description = locateElement("name","GOODS_DESC");
		Clear(Description);
		WebElement Description1 = locateElement("name","GOODS_DESC");
		type(Description1,"Test");		
//Charges
		WebElement Charges2 = locateElement("id","E");
		click(Charges2);
//Paid By	
		Logger log06111 = Logger.getLogger("Paid By");
		Row row06111 = sheet.getRow(23);
		Cell cell06111 = row06111.getCell(2);
		String Paid = cell06111.getStringCellValue();
		WebElement Method1111 = locateElement("name","CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111,Paid);
		log06111.info(Method1111.getAttribute("value"));
//PaidAt	
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(25);
		Cell cell07 = row07.getCell(2);
		String PaidAt = cell07.getStringCellValue();
		switch(PaidAt){
		case "TRANSACTION":
			WebElement Method3 = locateElement("name","CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3,PaidAt);
			log07.info(Method3.getAttribute("value"));
			Logger log09 = Logger.getLogger("Paid At");
			WebElement Method6 = locateElement("name","CHG_FLD_LOCAL_CUST_AC_NO");
			type(Method6,"763915060");
			log09.info(Method6.getAttribute("value"));
			/*WebElement Method5 = locateElement("name","CHG_GETAC_BTN");
			click(Method5);
			 Set<String> windowId = driver.getWindowHandles();   
		        Iterator<String> itererator = windowId.iterator();   
		        String mainWinID = itererator.next();
		        String  newAdwinID = itererator.next();
		        driver.switchTo().window(newAdwinID);
		        System.out.println(driver.getTitle());
		        driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
		        driver.switchTo().window(mainWinID);
		        System.out.println(driver.getTitle());
		        switchToFramest("work");*/
			break;
		case "DEFERRED":
		case "WAIVED":	
			WebElement Method31 = locateElement("name","CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method31,PaidAt);
			log07.info(Method31.getAttribute("value"));
			break;
		}
//Note
		WebElement Note = locateElement("id","G");
		click(Note);
		Logger log05 = Logger.getLogger("Note");
		WebElement Note1 = locateElement("id","NOTES");
		Clear(Note1);
		WebElement Note11 = locateElement("id","NOTES");
		type(Note11,"Welcome");
		log05.info(Note11.getAttribute("value"));
//Diary
		WebElement Diary = locateElement("id","H");
		click(Diary);
//Narrative		
		Logger log051 = Logger.getLogger("Diary - Narrative");
		WebElement Narrative1 = locateElement("name","DIARY_NARRATIVE");
		type(Narrative1,"Testing Do");
		log051.info(Narrative1.getAttribute("value"));
//Related Reference
		Logger log0511 = Logger.getLogger("Diary - Related Reference");
		WebElement Related = locateElement("name","DIARY_RELATED_REF");
		type(Related,"5658921");
		log0511.info(Related.getAttribute("value"));
		
//Advice 
		 Logger log211111 = Logger.getLogger("Open");			
		 WebElement Advice  = locateElement("id","F");
		 click(Advice);
		 log211111.info("Advice Tab");
		 Logger log2011 = Logger.getLogger("Open");	
		 WebElement Related1 = locateElement("xpath","//*[@id='ext-gen91']");
		 click(Related1);
		 //driver.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
		 log2011.info("Bank ");
//Bank			
		 driver.switchTo().frame("frame.AdivceForBankCust");
//Type of Message			 
		 Logger log222 = Logger.getLogger("Type of Message");	
		 Row Message = sheet.getRow(25);
		 Cell Type = Message.getCell(6);
		 String Mail11 = Type.getStringCellValue();
		// String Mail11 = "MT199";
		 Thread.sleep(3000);
		 WebElement Method9 = locateElement("name","MESG_TYPE_BANK");
		 selectDropDownUsingText(Method9,Mail11);
		 log222.info(Method9.getAttribute("value"));
		 driver.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[4]/td[2]/input[2]")).click();//CUBK Button
//Alert Message				
		 acceptAlert();
//Narrative					
	     try {
			Logger log121 = Logger.getLogger("Narrative (Mail)");
			 WebElement Narrative11  = locateElement("id","BANK_NARR_MAIL");
			 type(Narrative11,"Test1");
			 log121.info(Narrative11.getAttribute("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//Narrative (MT n99 Tag 79)
	     try {
			 Logger log1211 = Logger.getLogger("Narrative (Mail)");
			 WebElement Narrative11  = locateElement("id","BANK_NARR_TAG_79");
			 type(Narrative11,"Test1");
			 log1211.info(Narrative11.getAttribute("value"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	      driver.switchTo().frame("frame.AdivceForBankCust");
//ID		      
	        Logger ID = Logger.getLogger("ID");
		    WebElement Narrative111  = locateElement("name","SEND_TO_BANK_ID");
		    ID.info(Narrative111.getAttribute("value"));
//Name			
		    Logger ID1 = Logger.getLogger("Name");
		    WebElement Name  = locateElement("name","SEND_TO_BANK_NM");
		    ID1.info(Name.getAttribute("value"));
//Address
		    Logger ID11 = Logger.getLogger("Name");
		    WebElement Address  = locateElement("name","SEND_TO_BANK_ADD1");
		    ID11.info(Address.getAttribute("value"));
//Customer
			driver.findElement(By.xpath("//*[@id='B']")).click();
			Logger log131 = Logger.getLogger("Type of Message");
			Row row28 = sheet.getRow(25);
			Cell cell002 = row28.getCell(4);
			String cus = cell002.getStringCellValue();
			//String cus = "Email";
			WebElement Our2 = locateElement("id", "MESG_TYPE_CUST");
			selectDropDownUsingText(Our2, cus);
			log131.info(Our2.getAttribute("value"));
			driver.findElement(By.xpath("//*[@id='B_div']/table/tbody/tr[4]/td[2]/input[2]")).click();
//Alert message close				
		    acceptAlert();
//Narrative Mail
		    Logger log1211 = Logger.getLogger("Narrative (Mail)");
			WebElement Narrative11  = locateElement("id","CUST_NARR_TAG_79");
			type(Narrative11,"Test1");
			log1211.info(Narrative11.getAttribute("value"));
		    
				Set<String> window = driver.getWindowHandles(); 
				Iterator<String> itererator2 = window.iterator();
				String mainWin = itererator2.next();
				String newAdwin = itererator2.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='9']/td[2]/a")).click();
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
//Frame		        
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
//ID
			Logger ID2 = Logger.getLogger("ID");
			WebElement Narrative1111 = locateElement("name", "SEND_TO_CUST_ID");
			ID2.info(Narrative1111.getAttribute("value"));
//Name
			Logger ID3 = Logger.getLogger("Name");
			WebElement Name1 = locateElement("name", "SEND_TO_CUST_NM");
			ID3.info(Name1.getAttribute("value"));
//Address
			Logger ID111 = Logger.getLogger("Name");
			WebElement Address1 = locateElement("name", "SEND_TO_CUST_ADD1");
			ID111.info(Address1.getAttribute("value"));
//Save Button	 	  
		   switchToFramest("work");
		   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
		   driver.findElement(By.xpath("//*[@id='AdivceForBankCustClose']")).click();

//confirmation button 
		   Confirm();
 //supervisor Release
	       
	       IMCO_SupervisorRelease();
	
	}
}