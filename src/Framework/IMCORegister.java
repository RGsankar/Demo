package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class IMCORegister extends SeMethods{
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //Date Formatter	
	Date date = new Date();  //Current Date 
	public String OUR_ROLE; //Our Role
	public String Month1; // TeNOR Event
	public String COLL_TYPE_1; // Collection type 
	public String Currency; // Collection currency 
	public String C_MAIN_REF; //Main Reference
	public String COLL_NO; // Remitting Party Reference
	public String Amount; // Collection Amount 
	public String MT410_TAG_32K; //Tag 32K
	public String MT410_TAG_32A; //Tag 32A
	public String REMIT_BK_SWift_ADD; // Remitting Bank Swift Address 
	public String Maturity_date; // Maturity date
	public String TEMP_TENOR_32; //TAG31K
	
	@Test
	public void IMCOREG() throws IOException, InterruptedException, ATUTestRecorderException, ParseException {		
		/*IMCOAmend ssq = new IMCOAmend();
		ssq.IMCOAMD();
		
		System.out.println(ssq.numberAsString1);*/
//login Page
		
		loginpage();
		
//IPLC Module		
		
		RecoredStart(); // Record Start
		Logger log4 = Logger.getLogger("Module");
		WebElement Import  = locateElement("name", "Import Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IMCO Registration");
		click(Function);
		log5.info("IMCO Registration");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300226F05030701642");
		click(FunctionGroup);
		log6.info("Create Collection");
//Excel Sheet get Value
		FileInputStream file = new FileInputStream("E:\\Testing\\IMCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Collection"); 
		workbook.close();  
		
//Frame 
		 switchToFramest("work");
//Main 
//Copy and past
			Thread.sleep(2000);
			WebElement locOfOrder = locateElement("name", "C_MAIN_REF");
			Actions act = new Actions(driver);
			act.moveToElement(locOfOrder).doubleClick().build().perform();
			// now apply copy command
			Logger log16 = Logger.getLogger("Reference Number");
			WebElement san2 = locateElement("name", "C_MAIN_REF");
			san2.sendKeys(Keys.chord(Keys.CONTROL, "c"));
			C_MAIN_REF = driver.findElement(By.name("C_MAIN_REF")).getAttribute("value");
			if(C_MAIN_REF.length() > 16){
				log16 .info("Reference Number only 16 digital aceept but here more than digital accept" +C_MAIN_REF);
			}

//Excel Sheet Write Value
			FileOutputStream outputStream = new FileOutputStream(new File("E:\\Testing\\Baseline\\IMCO.xlsx"));
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			XSSFSheet sheet1 = workbook1.createSheet("Reference Number");
			Row row_3 = sheet1.createRow(3);	 
			row_3.createCell(3).setCellValue("Reference Number :");
			row_3.createCell(4).setCellValue(san2.getAttribute("value"));
		    workbook1.write(outputStream);
		    workbook1.close();		
		    log16.info(san2.getAttribute("value"));
//Mandatory,Optional and Protected field value
		    String Mandatory = "153, 204, 255";
		    String Optional = "255, 255, 255";
		    String Protected = "242, 242, 242";
//Registration Date
		  
		Logger log72 = Logger.getLogger("Registration Date");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String date13 = dateFormat.format(date);
		WebElement Date2 = locateElement("name", "REG_DT");
		String san5 = Date2.getAttribute("value");
		//Logger(Date2);
		if (date13.equals(san5)){
			log72.info("The Registration date should be always current date:" + san5);
		}
		else{
			log72.error("The Registration date should not accept future date:" + san5);
			takeSnap();
		}
// field Protected 
		color(Date2,Protected);
		
//Remitting Party Reference
		Logger log7 = Logger.getLogger("Remitting Party Reference");
		Row row = sheet.getRow(5);
		Cell cell = row.getCell(2);
		COLL_NO = getCellValueAsString(cell);
		WebElement Reference = locateElement("name", "COLL_NO");
		type(Reference,COLL_NO);
		driver.findElement(By.name("REL_ORDER_REF")).click();
		String ss = driver.findElement(By.name("COLL_NO")).getAttribute("value");
		log7.info(ss);
		if(ss.length() > 16){
			log7.error("Only 16(Char or Digits) are allowed :" +ss);
			takeSnap();
		}
		
		color(Reference,Mandatory);
		RecoredStop();
		Thread.sleep(1000);
		RecoredStart();
//Remittance Date
		Row row111 = sheet.getRow(5);
		Cell cell111 = row111.getCell(4);
		String REMT_DT = getCellValueAsString(cell111);
		WebElement REMT_DT1 = locateElement("name", "REMT_DT");
		REMT_DT1.clear();
		type(REMT_DT1,REMT_DT);
		driver.findElement(By.name("REL_ORDER_REF")).click();
		Thread.sleep(1000);
		acceptAlert();
		Date_After(REMT_DT1); 
		
//Our Role
		Logger log10 = Logger.getLogger("Collection Currency and Amount");
		Row row11 = sheet.getRow(7);
		Cell cell11 = row11.getCell(4);
		OUR_ROLE = cell11.getStringCellValue();
		WebElement Our = locateElement("id", "OUR_ROLE");
		selectDropDownUsingText(Our, OUR_ROLE);
		log10.info(Our.getAttribute("value"));
// Field Mandatory 		
		color(Our, Mandatory);
		
//Release Order Reference		
		Logger log33 = Logger.getLogger("Release Order Reference");
		Row row9 = sheet.getRow(4);
		Cell cel = row9.getCell(2);
		String REL_ORDER_REF2 = getCellValueAsString(cel);
		WebElement Order1 = locateElement("id","REL_ORDER_REF2");
		type(Order1,REL_ORDER_REF2);
		log33.info(Order1.getAttribute("value"));
		driver.findElement(By.name("OUR_ROLE")).click();// OUT SIDE CLICK
		String role = driver.findElement(By.id("REL_ORDER_REF2")).getAttribute("value");
		if(role.length() > 16){
			log33.error("Only 16(Char or Digits) are allowed :" +role);
		}
		
//Financial Information	
		
//Amount
		Row row211 = sheet.getRow(7);
		Cell cell211 = row211.getCell(2);
		Amount = getCellValueAsString(cell211);
		WebElement COLL_TRX_CCY_AMT = locateElement("id","COLL_TRX_CCY_AMT");
		click(COLL_TRX_CCY_AMT);
		type(COLL_TRX_CCY_AMT,Amount);
		// OUT SIDE CLICK
		driver.findElement(By.id("REL_ORDER_REF2")).click();
		acceptAlert();
		Amount(COLL_TRX_CCY_AMT);
// Field Mandatory
		color(COLL_TRX_CCY_AMT, Mandatory);

//Currency		
		Logger log9 = Logger.getLogger("Collection Currency and Amount");
		Row row1 = sheet.getRow(5);
		Cell cell1 = row1.getCell(6);
		Currency = cell1.getStringCellValue();
		WebElement COLL_CCY = locateElement("id","COLL_CCY");
		selectDropDownUsingText(COLL_CCY,Currency);
		log9.info(COLL_CCY.getAttribute("value"));
// Field Mandatory
		color(COLL_CCY, Mandatory);

//Currency and Balance (Local)		
		Logger bal = Logger.getLogger("Currency and Balance (Local)");
		String Local = driver.findElement(By.name("LOCAL_AMT")).getAttribute("value");
		double ss1 = Double.parseDouble(Local.replace(",", ""));
		switch(Currency){
		case "USD":
			String Amount11 = driver.findElement(By.name("COLL_TRX_CCY_AMT")).getAttribute("value");
			double amt = Double.parseDouble(Amount11.replace(",", ""));
			double st = 1.00;
			double sr = amt*st;
			System.out.println(sr);
			if (ss1 == sr){
				bal.info("Currency and Balance (Local)amount field equal USD:"+ss1);
			}
			else{
				bal.error("Currency and Balance (Local)amount field not equal USD:"+ss1);
			}
			break;
		case "AED":
			String Amount111 = driver.findElement(By.name("COLL_TRX_CCY_AMT")).getAttribute("value");
			double amt1 = Double.parseDouble(Amount111.replace(",", ""));
			double st1 = 0.27;
			double sr1 = amt1*st1;
			System.out.println(sr1);
			if (ss1 == sr1){
				bal.info("Currency and Balance (Local)amount field equal AED:"+sr1);
			}
			else{
				bal.error("Currency and Balance (Local)amount field not equal AED:"+sr1);
			}
			break;
		case "EUR":
			String Amount1111 = driver.findElement(By.name("COLL_TRX_CCY_AMT")).getAttribute("value");
			double amt11 = Double.parseDouble(Amount1111.replace(",", ""));
			double st11 = 1.287498;
			double sr11 = amt11*st11;
			System.out.println(sr11);
			if (ss1 == sr11){
				bal.info("Currency and Balance (Local)amount field equal EUR:"+sr11);
			}
			else{
				bal.error("Currency and Balance (Local)amount field not equal EUR:"+sr11 );
			}
			break;
		}
		
//Deliver Documents Against		
		Logger log91 = Logger.getLogger("Deliver Documents Against	");
		Row row3 = sheet.getRow(7);
		Cell cell3 = row3.getCell(6);
		String DELVR_DOC_AGST = cell3.getStringCellValue();
		WebElement Financia5 = locateElement("id","DELVR_DOC_AGST");
		selectDropDownUsingText(Financia5,DELVR_DOC_AGST);
		log91.info(Financia5.getAttribute("value"));
		System.out.println(DELVR_DOC_AGST);
		Thread.sleep(1000);
		switch(DELVR_DOC_AGST){
		case "D/P":
			
//COLOR CHECKING
			String TENOR_START_DT= driver.findElement(By.name("TENOR_START_DT")).getCssValue("background-color");
			String TENOR_DAYS = driver.findElement(By.name("TENOR_DAYS")).getCssValue("background-color");
			String DAY_MON_FLG = driver.findElement(By.name("DAY_MON_FLG")).getCssValue("background-color");
			String TENOR_EVENT = driver.findElement(By.name("TENOR_EVENT")).getCssValue("background-color");
			String DUE_DT = driver.findElement(By.name("DUE_DT")).getCssValue("background-color");
			String TENOR_DETAILS = driver.findElement(By.name("TENOR_DETAILS")).getCssValue("background-color");

			System.out.println("Css Value for background color is : " + TENOR_START_DT+TENOR_DAYS+DAY_MON_FLG+DUE_DT+TENOR_DETAILS);
			String BG = "242, 242, 242";
			if (TENOR_START_DT.contains(BG)&&TENOR_DAYS.contains(BG)&&DAY_MON_FLG.contains(BG)&&TENOR_EVENT.contains(BG)&&DUE_DT.contains(BG)&&TENOR_DETAILS.contains(BG)) { 
				log91.info("Tenor Start Date,Tenor Maturity Date,Tenor Details and Tenor fields are always Protected");
			} else {
				log91.error("Tenor Start Date,Tenor Maturity Date,Tenor Details and Tenor fields are should not Protected");
			}
			break;
		case "D/A":
		case "D/A and Aval":
			
//COLOR CHECKING
			String TENOR_START_DT1= driver.findElement(By.name("TENOR_START_DT")).getCssValue("background-color");
			String TENOR_EVENT1 = driver.findElement(By.name("TENOR_EVENT")).getCssValue("background-color");

			String BG1 = "153, 204, 255";
			if (TENOR_START_DT1.contains(BG1)&&TENOR_EVENT1.contains(BG1)) { 
				log91.info("Tenor Start Date,and Tenor Event fields are always Mandatory");
			} else {
				log91.error("Tenor Start Date,and Tenor Event fields are should not Mandatory");
			}
//Tenor drop down		
			Logger log95 = Logger.getLogger("Tenor Month/day");
			Row row311 = sheet.getRow(11);
			Cell cell311 = row311.getCell(4);
			Month1 = cell311.getStringCellValue();
			WebElement Financia = locateElement("id", "TENOR_EVENT");
			selectDropDownUsingText(Financia, Month1);
			log95.info(Financia.getAttribute("value"));
			switch (Month1) {
			case "After date of Bill of Exchange":
			case "After customs clearance of goods":
			case "After goods pass food and drug administration":
			case "First presentation":
			case "After arrival of goods":
			case "After invoice date":
			case "After sight":
			case "After date of transport document":

// Tenor StartDate
				Row row1111 = sheet.getRow(9);
				Cell cell1111 = row1111.getCell(6);
				String TENOR_START = getCellValueAsString(cell1111);
				WebElement TENOR_START_Dt = locateElement("name", "TENOR_START_DT");
				type(TENOR_START_Dt,TENOR_START);
				driver.findElement(By.name("REL_ORDER_REF")).click();
				Thread.sleep(1000);
				acceptAlert();
				Date_Before(TENOR_START_Dt); 
				
// TeNor
				Logger log8111 = Logger.getLogger("LC_AMT");
				Row row_Tenor = sheet.getRow(9);
				Cell cell_Tenor = row_Tenor.getCell(2);
				String TENOR_DAYS_1 = getCellValueAsString(cell_Tenor);
				WebElement TENOR_DAY_2 = locateElement("id", "TENOR_DAYS");
				Clear(TENOR_DAY_2);
				type(TENOR_DAY_2, TENOR_DAYS_1);
				Thread.sleep(2000);
				// OUT SIDE CLICK
				driver.findElement(By.id("REL_ORDER_REF2")).click();
				acceptAlert();
				String Amount11 = driver.findElement(By.name("TENOR_DAYS")).getAttribute("value");
				if (Integer.parseInt(Amount11) < 0) {
					log8111.error("Tenor Days field accept Negative value :" + Amount11);
					takeSnap();
				}
				if (Integer.parseInt(Amount11) >= 999) {
					log8111.error("Tenor Days field accept more than 999 digital value:" + Amount11);
					takeSnap();
				}
				
				String alphavalue = "[a-zA-Z]*";
				if(Amount11.matches(alphavalue)){
					acceptAlert();
					log8111.error(" field not accept Character:" + Amount11);
					takeSnap();
				}

				// Tenor Month/day
				try {
					Logger log911 = Logger.getLogger("Tenor Month/day");
					Row row31 = sheet.getRow(9);
					Cell cell31 = row31.getCell(4);
					String Month = cell31.getStringCellValue();
					String remi = driver.findElement(By.name("TENOR_START_DT")).getAttribute("value");
					String Mon = driver.findElement(By.name("TENOR_DAYS")).getAttribute("value");
					System.out.println(Mon);
					System.out.println(remi);
					
					WebElement Financia11 = locateElement("id", "DAY_MON_FLG");
					selectDropDownUsingText(Financia11, Month);
					switch (Month) {
					case "Days":
						LocalDate date2 = LocalDate.parse(remi).plusDays(Integer.parseInt(Mon));
						log911.info(date2 + Financia11.getAttribute("value"));
						String Maturity = driver.findElement(By.name("DUE_DT")).getAttribute("value");
						System.out.println(date2);
						if (date2.toString().equals(Maturity)) {
							log911.info("Tenor Maturity Date correct date" + date2);
						} else {
							log911.error("Tenor Maturity Date not correct date " + date2);
						}
						break;
					case "Months":
						LocalDate date21 = LocalDate.parse(remi).plusDays(30 * Integer.parseInt(Mon));
						String Maturity1 = driver.findElement(By.name("DUE_DT")).getAttribute("value");
						log911.info(date21 + Financia11.getAttribute("value"));
						System.out.println(date21);
						if (date21.toString().equals(Maturity1)) {

						} else {
							log911.error("Tenor Maturity Date not correct date  " + date21);
						}
						break;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "Fixed Maturity":
				// Tenor Maturity Date Mandatory
				Thread.sleep(1000);
				String TENOR_DETAILS_1 = driver.findElement(By.name("DUE_DT")).getCssValue("background-color");
				if (TENOR_DETAILS_1.contains(Mandatory)) {
					log91.info("Tenor Start Date,Tenor field is always Mandatory");
				} else {
					log91.error("Tenor Start Date,Tenor field is should not Mandatory");
				}
				// Tenor protected
				// COLOR CHECKING
				String TENOR_START_DT_2 = driver.findElement(By.name("TENOR_START_DT")).getCssValue("background-color");
				String TENOR_DAYS_2 = driver.findElement(By.name("TENOR_DAYS")).getCssValue("background-color");
				String DAY_MON_FLG_2 = driver.findElement(By.name("DAY_MON_FLG")).getCssValue("background-color");
				String TENOR_DETAILS_2 = driver.findElement(By.name("TENOR_DETAILS")).getCssValue("background-color");

				String BG_3 = "242, 242, 242";
				if (TENOR_START_DT_2.contains(BG_3) && TENOR_DAYS_2.contains(BG_3) && DAY_MON_FLG_2.contains(BG_3)&& TENOR_DETAILS_2.contains(BG_3)) {
					log91.info("Tenor Start Date,Tenor Details and Tenor fields are always Protected");
				} else {
					log91.error("Tenor Start Date,Tenor Details and Tenor fields are should not Protected");
				}
				break;
			case "See Below":
				
				// Tenor Details
				Logger log60 = Logger.getLogger("TENOR_DETAILS");
				WebElement Details = locateElement("id", "TENOR_DETAILS");
				type(Details, "test");
				log60.info(Details.getAttribute("value"));
				// COLOR CHECKING //Start date Optional
				String TENOR_START_DT11 = driver.findElement(By.name("TENOR_START_DT")).getCssValue("background-color");
				String BG11 = "255, 255, 255";
				if (TENOR_START_DT11.contains(BG11)) {
					log91.info("Tenor Start Date,Tenor field is always Optional");
				} else {
					log91.error("Tenor Start Date,Tenor field is should not Optional");
				}
				// Tenor Details Mandatory
				String TENOR_DETAILS_11 = driver.findElement(By.name("TENOR_DETAILS")).getCssValue("background-color");
				String BG_11 = "153, 204, 255";
				if (TENOR_DETAILS_11.contains(BG_11)) {
					log91.info("Tenor Detailsr field is always Mandatory");
				} else {
					log91.error("Tenor Detailsr field is should not Mandatory");
				}
				// Tenor protected
				String TENOR_DAYS_21 = driver.findElement(By.name("TENOR_DAYS")).getCssValue("background-color");
				String DAY_MON_FLG_21 = driver.findElement(By.name("DAY_MON_FLG")).getCssValue("background-color");
				String DUE_DT1 = driver.findElement(By.name("DUE_DT")).getCssValue("background-color");

				String BG_31 = "242, 242, 242";
				if (TENOR_DAYS_21.contains(BG_31) && DAY_MON_FLG_21.contains(BG_31) && DUE_DT1.contains(BG_31)) {
					log91.info("Tenor Maturity Date and Tenor Details fields are always Protected");
				} else {
					log91.error("Tenor Maturity Date and Tenor Details fields are should not Protected");
				}
				break;
			}
		}

// Tenor Maturity Date
		Logger TAG_32K = Logger.getLogger("TAG32(A/K)");
		if (!DELVR_DOC_AGST.equals("D/P")) {
			Row row12 = sheet.getRow(11);
			Cell cell22 = row12.getCell(2);
			String DAY_MON_FLG1 = getCellValueAsString(cell22);
			WebElement DAY_MON = locateElement("name", "DUE_DT");
			if (DAY_MON_FLG1 == null || DAY_MON_FLG1.isEmpty()) {
				DAY_MON.clear();
				System.out.println("Maturity date is Empty");
				driver.findElement(By.id("REL_ORDER_REF2")).click(); // OUT SIDE
//TAG32(K)
				MT410_TAG_32K = driver.findElement(By.name("MT410_TAG_32K")).getAttribute("value");
				if (!MT410_TAG_32K.equals("K")) {
					TAG_32K.error("TAG32(A/K) field should be display k But here Display A "+ MT410_TAG_32K);	
				}
//TAG32K				
				String TENOR_DAYS = driver.findElement(By.name("TENOR_DAYS")).getAttribute("value");
				String DAY_MON_FLG = driver.findElement(By.name("DAY_MON_FLG")).getAttribute("value");
				String TENOR_EVENT = driver.findElement(By.name("TENOR_EVENT")).getAttribute("value");
				String TEMP_TENOR_32K = driver.findElement(By.name("TEMP_TENOR_32K")).getAttribute("value");
				if(!TEMP_TENOR_32K.contains(TENOR_DAYS) && !TEMP_TENOR_32K.contains(DAY_MON_FLG) && !TEMP_TENOR_32K.contains(TENOR_EVENT)){
					TAG_32K.error("TAG32K value not matches");
				}
				
			} else {
				if (!DELVR_DOC_AGST.equals("D/P")) {
					if (!Month1.equals("See Below")) {
						DAY_MON.clear();
						type(DAY_MON, DAY_MON_FLG1);
						driver.findElement(By.name("REL_ORDER_REF")).click();
						Thread.sleep(1000);
						acceptAlert();
						Date_Before(DAY_MON);
						Thread.sleep(2000);
						Maturity_date = DAY_MON.getAttribute("value");
						// TAG32(A)
						MT410_TAG_32A = driver.findElement(By.name("MT410_TAG_32K")).getAttribute("value");
						if (!MT410_TAG_32A.equals("A")) {
							TAG_32K.error("TAG32(A/K) field should be display A But here Display K "+ MT410_TAG_32A);	
						}
					}
				}
			}
		}
		
		else {
			// TAG32(A/K)
			Thread.sleep(2000);
			MT410_TAG_32K = driver.findElement(By.name("MT410_TAG_32K")).getAttribute("value");
			if (!MT410_TAG_32K.equals("K")) {
				TAG_32K.error("TAG32(A/K) field should be display k But here Display A " + MT410_TAG_32K);
			}
			// TAG32K
			String TENOR_DAYS = driver.findElement(By.name("TENOR_DAYS")).getAttribute("value");
			String DAY_MON_FLG = driver.findElement(By.name("DAY_MON_FLG")).getAttribute("value");
			String TENOR_EVENT = driver.findElement(By.name("TENOR_EVENT")).getAttribute("value");
			String TEMP_TENOR_32K = driver.findElement(By.name("TEMP_TENOR_32K")).getAttribute("value");
			if (!TEMP_TENOR_32K.contains(TENOR_DAYS) && !TEMP_TENOR_32K.contains(DAY_MON_FLG) && !TEMP_TENOR_32K.contains(TENOR_EVENT)) {
				TAG_32K.error(TEMP_TENOR_32K+" TAG32K value not matches "+DAY_MON_FLG+TENOR_DAYS+TENOR_EVENT);
			} 
		}
//Tag32(A/K)		
		MT410_TAG_32K = driver.findElement(By.name("MT410_TAG_32K")).getAttribute("value");
//TAG31K		
		TEMP_TENOR_32 = driver.findElement(By.name("TEMP_TENOR_32K")).getAttribute("value");
//Collection Type
			Logger log911 = Logger.getLogger("Collection Type");
			Row row31 = sheet.getRow(13);
			Cell cell31 = row31.getCell(2);
			COLL_TYPE_1 = cell31.getStringCellValue();
			WebElement COLL_TYPE = locateElement("name","COLL_TYPE");
			selectDropDownUsingText(COLL_TYPE,COLL_TYPE_1);
			log911.info(COLL_TYPE.getAttribute("value"));
// Field Mandatory
			color(COLL_TYPE,Mandatory);
//Mailing contains
			Logger log9111 = Logger.getLogger("Mailing contains");
			Row row3111 = sheet.getRow(13);
			Cell cell3111 = row3111.getCell(4);
			String Month11 = cell3111.getStringCellValue();
			WebElement MAIL_CONT = locateElement("name","MAIL_CONT");
			selectDropDownUsingText(MAIL_CONT,Month11);
			log9111.info(MAIL_CONT.getAttribute("value"));
// Field Mandatory
			color(COLL_TYPE, Mandatory);

//Interval (days)	
			
			Logger log01 = Logger.getLogger("Interval (days)");
			Row row2111 = sheet.getRow(11);
			Cell cell2111 = row2111.getCell(6);
			CellType Inter = cell2111.getCellTypeEnum();
			WebElement INTERVAL_DAYS = locateElement("name", "INTERVAL_DAYS");
		if (Inter == CellType.STRING) {
			String date1 = cell2111.getStringCellValue();
			Clear(INTERVAL_DAYS);
			type(INTERVAL_DAYS, date1);
			log01.info(INTERVAL_DAYS.getAttribute("value"));
			driver.findElement(By.id("REL_ORDER_REF2")).click(); // OUT SIDE CLICK
			acceptAlert();
			String remi = driver.findElement(By.name("INTERVAL_DAYS")).getAttribute("value");
			if (remi.equals(date1)) {
				log01.info("The Remittance date fields String accept:" + remi);
			} else {
				log01.info("The Remittance Date fields String value not accepting:" + date1);
			}
		}
		if (Inter == CellType.NUMERIC) {
			int TeNor = (int) cell2111.getNumericCellValue();
			Clear(INTERVAL_DAYS);
			
			type(INTERVAL_DAYS, Integer.toString(TeNor));
			log01.info(INTERVAL_DAYS.getAttribute("value"));
			// OUT SIDE CLICK
			driver.findElement(By.id("REL_ORDER_REF2")).click();
			acceptAlert();
			String Inter1 = driver.findElement(By.name("INTERVAL_DAYS")).getAttribute("value");
			if (Integer.parseInt(Inter1) < 0) {
				log01.error("[W4498] INTERVAL_DAYS must be an integer, please check it!" + Inter1);
			}
			if (Integer.parseInt(Inter1) > 99) {
				log01.error("Interval (days) field accept more than 99 digital value " + Inter1);
			}

// First Tracer Date
			Thread.sleep(1000);
			String Mon = driver.findElement(By.name("NXT_TRCR_DT")).getAttribute("value");
			LocalDate date2 = LocalDate.now().plusDays(TeNor);
			System.out.println(date2);

			if (date2.toString().equals(Mon)) {
				log01.info("First Tracer Date correct date" + date2);
			} else {
				DayOfWeek Mon1 = DayOfWeek.of(date2.get(ChronoField.DAY_OF_WEEK));
				System.out.println("This date Week End " + Mon1);
				log01.error("First Tracer Date not correct date - This date Week End  " + date2 + Mon1);
			}
		}
		
//Maximum Number
			Logger log011 = Logger.getLogger("Maximum Number");
			Row row03 = sheet.getRow(13);
			Cell cell03 = row03.getCell(6);
			String MAX_TRACER_NO_1 = getCellValueAsString(cell03);
			WebElement MAX_TRACER_NO = locateElement("name", "MAX_TRACER_NO");
			Clear(MAX_TRACER_NO);
			type(MAX_TRACER_NO, MAX_TRACER_NO_1);
			// OUT SIDE CLICK
			driver.findElement(By.id("REL_ORDER_REF2")).click();
			acceptAlert();
			String Max = driver.findElement(By.name("MAX_TRACER_NO")).getAttribute("value");
			if (Integer.parseInt(Max) < 0) {
				log01.error("[W4498] MAX_TRACER_NO must be an integer, please check it!" + Max);
			}
			if (Integer.parseInt(Max) > 99) {
				log01.error("Maximum Number field accept more than 99 digital value " + Max);
			}
			String alphavalue = "[a-zA-Z]*";
			if(Max.matches(alphavalue)){
				acceptAlert();
				log01.error(" field not accept Character:" + Max);
				takeSnap();
			}
			
//Under Shipping Guarantee
		Logger log910 = Logger.getLogger("Under Shipping Guarantee");
		Row row13 = sheet.getRow(15);
		Cell cell13 = row13.getCell(2);
		String Month111 = cell13.getStringCellValue();
		WebElement DOC_STAT = locateElement("name", "DOC_STAT");
		selectDropDownUsingText(DOC_STAT, Month111);
		log910.info(DOC_STAT.getAttribute("value"));
// Field Mandatory
		color(DOC_STAT,Mandatory);
		
		switch (Month111) {
		case "YES":
			WebElement SG_BIN_1 = locateElement("xpath", "//*[@id='A_div']/table/tbody/tr[16]/td[4]/a/input[2]");
			verifyisEnabled(SG_BIN_1);
			click(SG_BIN_1);
// Field Mandatory
			WebElement SG_NO = locateElement("name", "SG_NO");
			String USG1 = "153, 204, 255";
			color(SG_NO,USG1);
			Thread.sleep(2000);
// SG NO
			switchToWindowset();
			break;
		case "NO":
			Logger Enabled = Logger.getLogger("Enabled 	SG NO and SG Amount fields");
			WebElement SG_BIN_11 = locateElement("xpath", "//*[@id='A_div']/table/tbody/tr[16]/td[4]/a/input[2]");
			WebElement SG_CCY = locateElement("name", "SG_CCY");
			if(SG_BIN_11.isEnabled()==true && SG_CCY.isEnabled()==true)
			{
				Enabled.error("The given element is Enabled");
			}
			break;
		}
		
//parties 
		WebElement Parties = locateElement("id", "B");
		click(Parties);
//Drawee			
			
		Logger parties = Logger.getLogger("Drawee");
		Row partie = sheet.getRow(31);
		Cell parti = partie.getCell(2);
		String part = parti == null ? null : parti.getStringCellValue();
		WebElement DRWE_ID = locateElement("name", "DRWE_ID");
		if (part == null || part.isEmpty()) {
			driver.findElement(By.name("DRW_ID_BTN")).click();
			acceptAlert();
			Thread.sleep(1000);
			Set<String> windowId = driver.getWindowHandles();
			Iterator<String> itererator = windowId.iterator();
			String mainWinID = itererator.next();
			String newAdwinID = itererator.next();
			driver.switchTo().window(newAdwinID);
			System.out.println(driver.getTitle());
			driver.findElement(By.xpath("//*[@id='5']/td[2]/a")).click();
			driver.switchTo().window(mainWinID);
			System.out.println(driver.getTitle());

			// Frame
			switchToFramest("work");
		} else {
			type(DRWE_ID, part);
			String par = driver.findElement(By.name("DRWE_ID")).getAttribute("value");
			parties.info(par);
			
			WebElement Finana11 = locateElement("name", "DRWE_REF"); //Out side click
			click(Finana11);
		}
		Thread.sleep(1000);
//Drawee ID field Optional	and CBUK button
		WebElement DRW_ID_BTN = locateElement("name", "DRW_ID_BTN");
		verifyisEnabled(DRW_ID_BTN);
		
		color(DRWE_ID,Optional);
//Drawee name field Mandatory and CBUK button check
		WebElement DRWE_NM = locateElement("name", "DRWE_NM");
		color(DRWE_NM,Mandatory);
		Thread.sleep(1000);
		
		WebElement DRWE_ADD_BTN = locateElement("name", "DRWE_ADD_BTN");// verify the CUBK Button 
		verifyisEnabled(DRWE_ADD_BTN);
		
		WebElement Finana11 = locateElement("name", "DRWE_REF"); //Out side click
		click(Finana11);
//Drawee Correspondence Medium
			Logger log01A = Logger.getLogger("Correspondence Medium");
			Row row6 = sheet.getRow(29);
			Cell cell6 = row6.getCell(4);
			String Medium = cell6.getStringCellValue();
			WebElement DRWE_CORR_MED = locateElement("name","DRWE_CORR_MED");
			Thread.sleep(2000);
			selectDropDownUsingText(DRWE_CORR_MED,Medium);
			log01A.info(DRWE_CORR_MED.getAttribute("value"));
//Correspondence Medium field i mandatory 
			color(DRWE_CORR_MED,Mandatory); 
			
			switch (Medium){
			case"Mail":
				Logger MailAddress = Logger.getLogger("Mail");
				Row Mail  = sheet.getRow(33);
				Cell Mail1  = Mail .getCell(4);
				String Mail2 = Mail1 == null ? null : Mail1.getStringCellValue();
				WebElement DRWE_MAIL_ADD = locateElement("name","DRWE_MAIL_ADD");
				if (Mail2 == null || Mail2.isEmpty()) {
					Clear(DRWE_MAIL_ADD);
					MailAddress.info("[W4206] DRWE_MAIL_ADD can't be empty, please check it!");
					Confirm();
				}
				else{
					Thread.sleep(1000);
					Clear(DRWE_MAIL_ADD);
					type(DRWE_MAIL_ADD,Mail2);
					String par = driver.findElement(By.name("DRWE_MAIL_ADD")).getAttribute("value");
					MailAddress.info(par);
				}
				String par = driver.findElement(By.name("DRWE_MAIL_ADD")).getAttribute("value");
				if (par.length() > 210) {
					MailAddress.error("[W5021] The value length [291] row [25] is greater than the range 210 max row 6." + par);
				}
//Mail Address field i mandatory and CUBK Button check				
				color(DRWE_MAIL_ADD,Mandatory); 
				WebElement DRWE_POST_ADD_BTN = locateElement("name","DRWE_POST_ADD_BTN");
				verifyisEnabled(DRWE_POST_ADD_BTN);
				break;
			case"Fax":
				Logger FaxAddress = Logger.getLogger("Drawee Fax address");
				Row Fax  = sheet.getRow(33);
				Cell Fax1  = Fax .getCell(6);
				String Fax11 = Fax1 == null ? null : Fax1.getStringCellValue();
				WebElement DRWE_FAX = locateElement("name","DRWE_FAX");
				if (Fax11 == null || Fax11.isEmpty()) {
					Clear(DRWE_FAX);
					Confirm();
					log910.info("[W4206] DRWE_FAX_ADD can't be empty, please check it!");
				}
				else{
					Thread.sleep(2000);
					Clear(DRWE_FAX);
					type(DRWE_FAX,Fax11);
					String par1 = driver.findElement(By.name("DRWE_FAX")).getAttribute("value");
					FaxAddress.info(par1);
				}
				String par1 = driver.findElement(By.name("DRWE_FAX")).getAttribute("value");
				if (par1.length() > 21) {
					log01A.error("[W5021] The value length [21] row [25] is greater than the range 210 max row 6." + par1);
				}
//FAX Address field i mandatory 
				color(DRWE_FAX,Mandatory); 
				break;
			case"Email":
				Logger EmailAddress = Logger.getLogger("Drawee Email address");
				Row Email  = sheet.getRow(35);
				Cell Email1  = Email .getCell(2);
				String Email11 = Email1 == null ? null : Email1.getStringCellValue();
				WebElement DRWE_EMAIL = locateElement("name","DRWE_EMAIL");
				if (Email11 == null || Email11.isEmpty()) {
					Clear(DRWE_EMAIL);
					Confirm();
					log910.info("[W4206] DRWE_EMAIL_ADD can't be empty, please check it!");
				}
				else{
					Thread.sleep(2000);
					Clear(DRWE_EMAIL);
					type(DRWE_EMAIL,Email11);
				}
				
				String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				String EmailAddress1 = driver.findElement(By.name("DRWE_EMAIL")).getAttribute("value");
				
				if (EmailAddress1.matches(EMAIL_REGEX)) {
					EmailAddress.info("is e-mail: " + EmailAddress1 + " :Valid = ");
				} else {
					EmailAddress.error("is e-mail: " + EmailAddress1 + " :not Valid = ");
				}
//Email Address field i mandatory 
				color(DRWE_EMAIL,Mandatory);
				break;
			case"None":
				
//Mail Address field i Optional 
				WebElement DRWE_MAIL_ADD_1 = locateElement("name","DRWE_MAIL_ADD");
				color(DRWE_MAIL_ADD_1,Optional);
				WebElement DRWE_FAX_1 = locateElement("name","DRWE_FAX");
				color(DRWE_FAX_1,Optional);
				WebElement DRWE_EMAIL_1 = locateElement("name","DRWE_EMAIL");
				color(DRWE_EMAIL_1,Optional);
				break;
			}
			
//Drawer		
			Thread.sleep(2000);
			Logger Drawer = Logger.getLogger("Drawee");
			Row Drawer1 = sheet.getRow(31);
			Cell parti1 = Drawer1.getCell(4);
			String part1 = parti1 == null ? null : parti1.getStringCellValue();
			WebElement DRWR_ID  = locateElement("name","DRWR_ID");
			if (part1 == null || part1.isEmpty()) {
				driver.findElement(By.name("DRWR_ID_BTN")).click();
				try {
					String alert = driver.switchTo().alert().getText();
					driver.switchTo().alert().accept();
					log011.info(alert);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Set<String> windowId = driver.getWindowHandles();
				Iterator<String> itererator = windowId.iterator();
				String mainWinID = itererator.next();
				String newAdwinID = itererator.next();
				driver.switchTo().window(newAdwinID);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='5']/td[2]/a")).click();
				driver.switchTo().window(mainWinID);
				System.out.println(driver.getTitle());
				String par = driver.findElement(By.name("DRWR_ID_BTN")).getAttribute("value");
				Drawer.info(par);
				
// Frame
				switchToFramest("work");
			} else {
				type(DRWR_ID ,part1);
				String par = driver.findElement(By.name("DRWR_ID")).getAttribute("value");
				Drawer.info(par);
			}
			WebElement DRWR_CORR = locateElement("name","DRWR_CORR_MED");
			click(DRWR_CORR); //OUT SIDE CLICK
			Thread.sleep(1000);
// Drawer ID field Optional and CBUK button
		WebElement DRWR_ID_BTN = locateElement("name", "DRWR_ID_BTN");
		verifyisEnabled(DRWR_ID_BTN);

		color(DRWR_ID, Optional);
// Drawer name field Mandatory and CBUK button check
		WebElement DRWR_NM = locateElement("name", "DRWR_NM");
		color(DRWR_NM, Mandatory);
		Thread.sleep(1000);

		WebElement DRWR_ADD_BTN = locateElement("name", "DRWR_ADD_BTN");
		verifyisEnabled(DRWR_ADD_BTN);

		WebElement AC2_NO = locateElement("name", "AC2_NO"); // Out side
		click(AC2_NO);

//Drawer Correspondence Medium	
			WebElement DRWR_CORR_MED = locateElement("name","DRWR_CORR_MED");
			Thread.sleep(2000);
			selectDropDownUsingText(DRWR_CORR_MED,Medium);
			log01A.info(DRWR_CORR_MED.getAttribute("value"));
//Correspondence Medium field i mandatory 
			color(DRWR_CORR_MED,Mandatory); 
			switch (Medium){
			case"Mail":
				Logger MailAddress = Logger.getLogger("Mail");
				Row Mail  = sheet.getRow(33);
				Cell Mail1  = Mail .getCell(4);
				String Mail2 = Mail1 == null ? null : Mail1.getStringCellValue();
				WebElement DRWR_MAIL_ADD = locateElement("name","DRWR_MAIL_ADD");
				if (Mail2 == null || Mail2.isEmpty()) {
					Clear(DRWR_MAIL_ADD);
					Confirm();
					MailAddress.info("[W4206] DRWR_MAIL_ADD can't be empty, please check it!");
				}
				else{
					Thread.sleep(2000);
					Clear(DRWR_MAIL_ADD);
					type(DRWR_MAIL_ADD,Mail2);
					String par = driver.findElement(By.name("DRWR_MAIL_ADD")).getAttribute("value");
					MailAddress.info(par);
				}
				String par = driver.findElement(By.name("DRWR_MAIL_ADD")).getAttribute("value");
				if (par.length() > 210) {
					MailAddress.info("[W5021] The value length [291] row [25] is greater than the range 210 max row 6." + par);
				}
//Mail Address field i mandatory and CUBK Button check				
				color(DRWR_MAIL_ADD,Mandatory); 
				WebElement DRWR_POST_ADD_BTN = locateElement("name","DRWR_POST_ADD_BTN");
				verifyisEnabled(DRWR_POST_ADD_BTN);
				break;
			case"Fax":
				Logger FaxAddress = Logger.getLogger("Drawee Fax address");
				Row Fax  = sheet.getRow(33);
				Cell Fax1  = Fax .getCell(6);
				String Fax11 = Fax1 == null ? null : Fax1.getStringCellValue();
				WebElement DRWR_FAX = locateElement("name","DRWR_FAX");
				if (Fax11 == null || Fax11.isEmpty()) {
					Clear(DRWR_FAX);
					log910.info("[W4206] DRWE_FAX_ADD can't be empty, please check it!");
					Confirm();
				}
				else{
					Thread.sleep(2000);
					Clear(DRWR_FAX);
					type(DRWR_FAX,Fax11);
					String par1 = driver.findElement(By.name("DRWR_FAX")).getAttribute("value");
					FaxAddress.info(par1);
				}
				String par1 = driver.findElement(By.name("DRWR_FAX")).getAttribute("value");
				if (par1.length() > 21) {
					log01A.info("[W5021] The value length [21] row [25] is greater than the range 210 max row 6." + par1);
				}
//FAX Address field i mandatory 
				color(DRWR_FAX,Mandatory); 
				break;
			case"Email":
				Logger EmailAddress = Logger.getLogger("Drawee Fax address");
				Row Email  = sheet.getRow(35);
				Cell Email1  = Email .getCell(2);
				String Email11 = Email1 == null ? null : Email1.getStringCellValue();
				WebElement DRWR_EMAIL = locateElement("name","DRWR_EMAIL");
				if (Email11 == null || Email11.isEmpty()) {
					Clear(DRWR_EMAIL);
					log910.info("[W4206] DRWE_EMAIL_ADD can't be empty, please check it!");
					Confirm();
				}
				else{
					Thread.sleep(2000);
					Clear(DRWR_EMAIL);
					type(DRWR_EMAIL,Email11);
				}
				
				String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				String EmailAddress1 = driver.findElement(By.name("DRWE_EMAIL")).getAttribute("value");
				
				if (EmailAddress1.matches(EMAIL_REGEX)) {
					EmailAddress.info("is e-mail: " + EmailAddress1 + " :Valid = ");
				} else {
					EmailAddress.error("is e-mail: " + EmailAddress1 + " :not Valid = ");
				}
//Email Address field i mandatory 
				color(DRWR_EMAIL,Mandatory);
				break;
			case"None":
//Mail Address field i Optional 
				WebElement DRWR_MAIL_ADD_1 = locateElement("name","DRWR_MAIL_ADD");
				color(DRWR_MAIL_ADD_1,Optional);
				WebElement DRWR_FAX_1 = locateElement("name","DRWR_FAX");
				color(DRWR_FAX_1,Optional);
				WebElement DRWR_EMAIL_1 = locateElement("name","DRWR_EMAIL");
				color(DRWR_EMAIL_1,Optional);
				break;
			}
			
//Remitting BanK
			WebElement REMIT_BK_ID  = locateElement("name","REMIT_BK_ID");
			WebElement REMIT_BK_NM  = locateElement("name","REMIT_BK_NM");
			switch(COLL_TYPE_1){
			case"Documentary Through Bank":
			case"Clean Through Bank":
				color(REMIT_BK_ID,Optional); //Remitting Bank ID Optional
				color(REMIT_BK_NM,Mandatory); //Remitting Bank name Mandatory
				break;
			case"Documentary Direct":
			case"Clean Direct":
				color(REMIT_BK_ID,Optional); //Remitting Bank ID Optional
				color(REMIT_BK_NM,Optional); //Remitting Bank name Mandatory
				break;
			}
			Logger Remitting = Logger.getLogger("REmitting Bank");
			Row Remitting1 = sheet.getRow(31);
			Cell Remitting11 = Remitting1.getCell(6);
			String Remitting111 = Remitting11 == null ? null : Remitting11.getStringCellValue();
			if (Remitting111 == null || Remitting111.isEmpty()) {
				driver.findElement(By.name("DRWR_ID_BTN")).click();
				acceptAlert();
				Thread.sleep(2000);
				Set<String> windowId = driver.getWindowHandles();
				Iterator<String> itererator = windowId.iterator();
				String mainWinID = itererator.next();
				String newAdwinID = itererator.next();
				driver.switchTo().window(newAdwinID);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='5']/td[2]/a")).click();
				driver.switchTo().window(mainWinID);
				System.out.println(driver.getTitle());
				String par = driver.findElement(By.name("REMIT_BK_ID")).getAttribute("value");
				Remitting.info(par);
				
// Frame
				switchToFramest("work");
			} else {
				type(REMIT_BK_ID ,Remitting111);
				String par = driver.findElement(By.name("REMIT_BK_ID")).getAttribute("value");
				Remitting.info(par);
				click(REMIT_BK_NM); //ONCLICK
			}
// Remtting bank ID CBUK button check
			WebElement REMIT_BK_ID_BTN = locateElement("name", "REMIT_BK_ID_BTN");
			verifyisEnabled(REMIT_BK_ID_BTN);
// Drawer name CBUK button check
			Thread.sleep(1000);
			WebElement REMIT_BK_ADD_BTN = locateElement("name", "REMIT_BK_ADD_BTN");
			verifyisEnabled(REMIT_BK_ADD_BTN);

			WebElement AC3_NO = locateElement("name", "AC3_NO"); // ON CLICK
			click(AC3_NO);
			
//Remitting Bank Correspondence Medium	
			Logger REMIT_BK = Logger.getLogger("Remitting Bank Correspondence Medium");
			Row REMIT = sheet.getRow(35);
			Cell REMIT1 = REMIT.getCell(4);
			String REMIT11 = REMIT1.getStringCellValue();
			WebElement REMIT_BK_COR_MED = locateElement("name","REMIT_BK_COR_MED"); //Correspondence Medium
			Thread.sleep(2000);
			selectDropDownUsingText(REMIT_BK_COR_MED,REMIT11);
			REMIT_BK.info(REMIT_BK_COR_MED.getAttribute("value"));
			WebElement REMIT_BK_SW_ADD = locateElement("name","REMIT_BK_SW_ADD"); // REMIT_BK_SW_ADD located
			String REMIT_BK_SW = REMIT_BK_SW_ADD.getAttribute("value");
			WebElement REMIT_MAIL_ADD = locateElement("name","REMIT_MAIL_ADD"); //REMIT_MAIL_ADD located
			switch (REMIT11){
			case"Mail":
				Logger MailAddress = Logger.getLogger("Mail");
				Row Mail  = sheet.getRow(33);
				Cell Mail1  = Mail .getCell(4);
				String Mail2 = Mail1 == null ? null : Mail1.getStringCellValue();
				if (Mail2 == null || Mail2.isEmpty()) {
					Clear(REMIT_MAIL_ADD);
					MailAddress.info("[W4206] DRWR_MAIL_ADD can't be empty, please check it!");
					Confirm();
				}
				else{
					Thread.sleep(2000);
					Clear(REMIT_MAIL_ADD);
					type(REMIT_MAIL_ADD,Mail2);
					String par = driver.findElement(By.name("REMIT_MAIL_ADD")).getAttribute("value");
					MailAddress.info(par);
				}
				String par = driver.findElement(By.name("REMIT_MAIL_ADD")).getAttribute("value");
				if (par.length() > 210) {
					MailAddress.error("[W5021] The value length [291] row [25] is greater than the range 210 max row 6." + par);
				}
//Mail Address field i mandatory and CUBK Button check				
				color(REMIT_MAIL_ADD,Mandatory); 
				color(REMIT_BK_SW_ADD,Optional); // swift address optional
				WebElement REMIT_POST_ADD_BTN = locateElement("name","REMIT_POST_ADD_BTN");
				verifyisEnabled(REMIT_POST_ADD_BTN);
				break;
			case"SWIFT":
				Logger SWIFTAddress = Logger.getLogger("Drawee Swift address");
				Row SWIFT  = sheet.getRow(35);
				Cell SWIFT1  = SWIFT .getCell(6);
				String SWIFT11 = SWIFT1 == null ? null : SWIFT1.getStringCellValue();
				if (SWIFT11 == null || SWIFT11.isEmpty()) {
					Clear(REMIT_BK_SW_ADD);
					log910.info("[W4206] Remitting BankThrough Bank Bic Code can't be empty, please check it!");
					Confirm();
					Thread.sleep(2000);
					type(REMIT_BK_SW_ADD,REMIT_BK_SW);
					
				}
				else{
					Thread.sleep(2000);
					Clear(REMIT_BK_SW_ADD);
					type(REMIT_BK_SW_ADD,SWIFT11);
					String par1 = driver.findElement(By.name("REMIT_BK_SW_ADD")).getAttribute("value");
					SWIFTAddress.info(par1);
				}
				
				REMIT_BK_SWift_ADD = driver.findElement(By.name("REMIT_BK_SW_ADD")).getAttribute("value");
				if (REMIT_BK_SWift_ADD.length() > 11) {
					log01A.error("SWIFT Tag/Address accept more than 11 character." + REMIT_BK_SWift_ADD);
				}
//Swift Address field i mandatory and CUBK Button check				
				color(REMIT_BK_SW_ADD,Mandatory); // swift address Mandatory
				color(REMIT_MAIL_ADD,Optional); 
				break;
			case"None":
//Mail Address and field i Optional 
				color(REMIT_MAIL_ADD,Optional);
				color(REMIT_BK_SW_ADD,Optional);
				break;
			}

//Presenting Bank			
		switch (OUR_ROLE) {
		case "Only Collecting Bank":
// Presenting Bank ID Protected and CBUK button check
			Logger Presenting_Bank = Logger.getLogger("Presenting Bank");
			WebElement PRES_BK_ID_BTN_1 = locateElement("name", "PRES_BK_ID_BTN");
			if(PRES_BK_ID_BTN_1.isEnabled()==true)
			{
				Presenting_Bank.error("To check the " + PRES_BK_ID_BTN_1.getAttribute("name")+" field is Disabled");
			}
		
			WebElement PRES_BK_ID_1 = locateElement("name", "PRES_BK_ID");
			color(PRES_BK_ID_1, Protected);
// Presenting Bank name Protected and CBUK button check
			Thread.sleep(1000);
			WebElement PRES_BK_ADD_BTN_1 = locateElement("name", "PRES_BK_ADD_BTN");
			if(PRES_BK_ADD_BTN_1.isEnabled()==true)
			{
				Presenting_Bank.error("To check the " + PRES_BK_ADD_BTN_1.getAttribute("name")+" field is Disabled");
			}
			
			WebElement PRES_BK_NM_1 = locateElement("name", "PRES_BK_NM");
			color(PRES_BK_NM_1, Protected);
			System.out.println("the Presenting Bank not available");
			break;
		case "First Collecting Bank":
			Logger Presenting1 = Logger.getLogger("Presenting Bank");
			Row Presenting11 = sheet.getRow(33);
			Cell Prese = Presenting11.getCell(2);
			String prbank = Prese == null ? null : Prese.getStringCellValue();
			WebElement PRES_BK_ID = locateElement("name", "PRES_BK_ID");
			if (prbank == null || prbank.isEmpty()) {
				driver.findElement(By.name("DRWR_ID_BTN")).click();
				acceptAlert();
				Thread.sleep(2000);
				Set<String> windowId = driver.getWindowHandles();
				Iterator<String> itererator = windowId.iterator();
				String mainWinID = itererator.next();
				String newAdwinID = itererator.next();
				driver.switchTo().window(newAdwinID);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='5']/td[2]/a")).click();
				driver.switchTo().window(mainWinID);
				System.out.println(driver.getTitle());
				String par = driver.findElement(By.name("PRES_BK_ID")).getAttribute("value");
				Presenting1.info(par);
				// Frame
				switchToFramest("work");
			} else {

				type(PRES_BK_ID, prbank);
				String par = driver.findElement(By.name("PRES_BK_ID")).getAttribute("value");
				Presenting1.info(par);
			}
			WebElement PRES_BK_REF = locateElement("name", "PRES_BK_REF"); // ONCLICK
			click(PRES_BK_REF);

// Presenting Bank ID optional and CBUK button check
			WebElement PRES_BK_ID_BTN = locateElement("name", "PRES_BK_ID_BTN");
			verifyisEnabled(PRES_BK_ID_BTN);
			color(PRES_BK_ID, Optional);
// Presenting Bank name mandatory and CBUK button check
			Thread.sleep(1000);
			WebElement PRES_BK_ADD_BTN = locateElement("name", "PRES_BK_ADD_BTN");
			verifyisEnabled(PRES_BK_ADD_BTN);
			WebElement PRES_BK_NM = locateElement("name", "PRES_BK_NM");
			color(PRES_BK_NM, Mandatory);
			click(AC3_NO); // Out side

// Presenting Bank Correspondence Medium

			Logger PRES_BK = Logger.getLogger("Presenting Bank Correspondence Medium");
			Row PRES = sheet.getRow(35);
			Cell PRES_BK_1 = PRES.getCell(4);
			String PRES_BK_2 = PRES_BK_1.getStringCellValue();
			WebElement PRES_BK_CORR_MED = locateElement("name", "PRES_BK_CORR_MED");
			Thread.sleep(2000);
			selectDropDownUsingText(PRES_BK_CORR_MED, PRES_BK_2);
			PRES_BK.info(REMIT_BK_COR_MED.getAttribute("value"));
			WebElement PRES_BK_SW_ADD = locateElement("name", "PRES_BK_SW_ADD"); // PRES_BK_SW_ADD located
			WebElement PRES_BK_MAIL_ADD = locateElement("name", "PRES_BK_MAIL_ADD"); // PRES_BK_SW_ADD located
			switch (PRES_BK_2) {
			case "Mail":
				Logger MailAddress = Logger.getLogger("	Presenting Bank Mail Adress");
				Row Mail = sheet.getRow(33);
				Cell Mail1 = Mail.getCell(4);
				String Mail2 = Mail1 == null ? null : Mail1.getStringCellValue();
				if (Mail2 == null || Mail2.isEmpty()) {
					Clear(PRES_BK_MAIL_ADD);
					MailAddress.info("[W4206] DRWR_MAIL_ADD can't be empty, please check it!");
					Confirm();
				} else {
					Thread.sleep(2000);
					Clear(PRES_BK_MAIL_ADD);
					type(PRES_BK_MAIL_ADD, Mail2);
					String par = driver.findElement(By.name("PRES_BK_MAIL_ADD")).getAttribute("value");
					MailAddress.info(par);
				}
				String par = driver.findElement(By.name("PRES_BK_MAIL_ADD")).getAttribute("value");
				if (par.length() > 210) {
					MailAddress.error("[W5021] The value length [291] row [25] is greater than the range 210 max row 6." + par);
				}
// Mail Address field i mandatory and CUBK Button check
				color(PRES_BK_MAIL_ADD, Mandatory);
				color(PRES_BK_SW_ADD, Optional); // swift address optional
				WebElement PRES_BK_POST_ADD_BTN = locateElement("name", "PRES_BK_POST_ADD_BTN");
				verifyisEnabled(PRES_BK_POST_ADD_BTN);
				break;
			case "SWIFT":
				Logger SWIFTAddress = Logger.getLogger("Presenting Bank Swift address");
				Row SWIFT = sheet.getRow(35);
				Cell SWIFT1 = SWIFT.getCell(6);
				String SWIFT11 = SWIFT1 == null ? null : SWIFT1.getStringCellValue();
				if (SWIFT11 == null || SWIFT11.isEmpty()) {
					Clear(PRES_BK_SW_ADD);
					log910.info("[W4206] Remitting BankThrough Bank Bic Code can't be empty, please check it!");
					Confirm();
				} else {
					Thread.sleep(2000);
					Clear(PRES_BK_SW_ADD);
					type(PRES_BK_SW_ADD, SWIFT11);
					String par1 = driver.findElement(By.name("PRES_BK_SW_ADD")).getAttribute("value");
					SWIFTAddress.info(par1);
				}
				String par1 = driver.findElement(By.name("PRES_BK_SW_ADD")).getAttribute("value");
				if (par1.length() > 11) {
					log01A.error("SWIFT Tag/Address accept more than 11 character" + par1);
				}
// Swift Address field i mandatory and CUBK Button check
				color(PRES_BK_SW_ADD, Mandatory); // swift address Mandatory
				color(PRES_BK_MAIL_ADD, Optional);
				break;
			case "None":
// Mail Address and field i Optional
				color(PRES_BK_MAIL_ADD, Optional);
				color(PRES_BK_SW_ADD, Optional);
				break;
			}

		}		  
		RecoredStop();
		Thread.sleep(2000);
		RecoredStart();
//Instruction
		WebElement Instruction = locateElement("id", "C");
		click(Instruction);
//Our Charges For
		Logger log101 = Logger.getLogger("Our Charges For");
		Row row131 = sheet.getRow(15);
		Cell cell131 = row131.getCell(4);
		String Charges = cell131.getStringCellValue();
		WebElement CHG_FLG = locateElement("id", "CHG_FLG");
		selectDropDownUsingText(CHG_FLG, Charges);
		log101.info(CHG_FLG.getAttribute("value"));
		color(CHG_FLG, Mandatory);
//Waive Instruction				
		Logger log1011 = Logger.getLogger("Waive Instruction");
		Row row1311 = sheet.getRow(15);
		Cell cell1311 = row1311.getCell(6);
		String WAIVE = cell1311.getStringCellValue();
		WebElement WAIVE_INSTRUCTION = locateElement("id", "WAIVE_INSTRUCTION");
		selectDropDownUsingText(WAIVE_INSTRUCTION, WAIVE);
		log1011.info(WAIVE_INSTRUCTION.getAttribute("value"));
		color(WAIVE_INSTRUCTION, Mandatory);
			
//Release Documents Against
		Logger log20 = Logger.getLogger("Release Documents Against");
		Row row13111 = sheet.getRow(17);
		Cell cell13111 = row13111.getCell(2);
		String Release = cell13111.getStringCellValue();
		WebElement DOC_INSTR = locateElement("id", "DOC_INSTR");
		selectDropDownUsingText(DOC_INSTR, Release);
		Release = DOC_INSTR.getAttribute("value");
		log20.info(DOC_INSTR.getAttribute("value"));
		switch (DELVR_DOC_AGST) {
		case "D/P":
			if (Release.equals("Acceptance of Draft")) {
				log20.info("Deliver Documents Against D/P, Release Documents Against "+Release+ " not equal" );
				Thread.sleep(1000);
				Confirm();
			}
			break;
		case "D/A":
		case "D/A and Aval":
			if (Release.equals("Payment")) {
				log20.info("Deliver Documents Against D/A, Release Documents Against "+Release+ " not equal" );
				Thread.sleep(1000);
				Confirm();
			}
			break;
		}
		color(DOC_INSTR,Mandatory);	
//Bill Instruction
		Logger Bill = Logger.getLogger("Bill Instruction");
		Row row19 = sheet.getRow(17);
		Cell cell19 = row19.getCell(4);
		String Instruction1 = cell19.getStringCellValue();
		WebElement BILL_INSTR = locateElement("name", "BILL_INSTR");
		selectDropDownUsingText(BILL_INSTR, Instruction1);
		Bill.info(BILL_INSTR.getAttribute("value"));
		color(BILL_INSTR,Optional);
			
//Remitting Bank Charges Currency and Amount		
		Row row21111 = sheet.getRow(17);
		Cell cell21111 = row21111.getCell(6);
		String Amount11 = getCellValueAsString(cell21111);
		WebElement REMIT_BK_CHG_AMT = locateElement("id", "REMIT_BK_CHG_AMT");
		WebElement REMIT_BK_CHG_CCY = locateElement("id", "REMIT_BK_CHG_CCY"); //	Remitting Bank Charges Currency 
		WebElement WAIVE_REMT_BK_CHG_FLG = locateElement("name", "WAIVE_REMT_BK_CHG_FLG"); //	Waive instructions
		WebElement REMIT_BK_CHG_FLG = locateElement("id", "REMIT_BK_CHG_FLG"); // 	Remitting Bank Charges For
		if (Amount11 == null || Amount11.equals("0")) {
			color(REMIT_BK_CHG_AMT,Optional);
			verifyisDisable(REMIT_BK_CHG_CCY);
			color(REMIT_BK_CHG_CCY,Protected);
			verifyisDisable(WAIVE_REMT_BK_CHG_FLG);
			color(WAIVE_REMT_BK_CHG_FLG,Protected);
			verifyisDisable(REMIT_BK_CHG_FLG);
			color(REMIT_BK_CHG_FLG,Protected);
		} else {
			click(REMIT_BK_CHG_AMT);
			type(REMIT_BK_CHG_AMT, (Amount11));
			WebElement SPCL_INSTR = locateElement("name", "SPCL_INSTR");
			click(SPCL_INSTR); //ONCLICK
			acceptAlert();
			Amount(REMIT_BK_CHG_AMT);
			
//Remitting Bank Charges Currency	
			Row row1122 = sheet.getRow(19);Cell cell221 = row1122.getCell(2);
			String Currency11 = cell221.getStringCellValue();
			selectDropDownUsingText(REMIT_BK_CHG_CCY, Currency11);
			Logger(REMIT_BK_CHG_CCY);
			color(REMIT_BK_CHG_CCY,Mandatory);
			verifyisDisable(REMIT_BK_CHG_CCY);
//Waive instructions			
			Row row191 = sheet.getRow(19);Cell cell191 = row191.getCell(6);
			String Bank = cell191.getStringCellValue();
			selectDropDownUsingText(WAIVE_REMT_BK_CHG_FLG,Bank);
			Logger(WAIVE_REMT_BK_CHG_FLG);
			color(WAIVE_REMT_BK_CHG_FLG,Mandatory);
			verifyisDisable(WAIVE_REMT_BK_CHG_FLG);
//Remitting Bank Charges For			
			Row row33 = sheet.getRow(19);Cell cell33 = row33.getCell(4);
			String Charges12 = cell33.getStringCellValue();
			selectDropDownUsingText(REMIT_BK_CHG_FLG,Charges12);
			Logger(REMIT_BK_CHG_FLG);
			color(REMIT_BK_CHG_FLG,Mandatory);
}
		Logger(REMIT_BK_CHG_AMT);
//Documents/Goods
		WebElement Goods = locateElement("id", "D");
		click(Goods);
			
//Release in Trust			
		Row row15 = sheet.getRow(21);
		Cell cell9 = row15.getCell(2);
		String Trust = cell9.getStringCellValue();
		WebElement REL_IN_TRUST_FLG = locateElement("name", "REL_IN_TRUST_FLG");
		selectDropDownUsingText(REL_IN_TRUST_FLG, Trust);
		color(REL_IN_TRUST_FLG,Mandatory);
		Logger(REL_IN_TRUST_FLG);
//Store and Insure Goods
		Row row1911 = sheet.getRow(21);
		Cell cell1911 = row1911.getCell(4);
		String STORE_INSUR = cell1911.getStringCellValue();
		WebElement STORE_INSURE_GOODS = locateElement("name", "STORE_INSURE_GOODS");
		selectDropDownUsingText(STORE_INSURE_GOODS, STORE_INSUR);
		color(STORE_INSURE_GOODS,Mandatory);
		Logger(STORE_INSURE_GOODS);
			
//	Hold docs
		Row Hold1 = sheet.getRow(21);
		Cell Hold11 = Hold1.getCell(6);
		String HOLD = Hold11.getStringCellValue();
		WebElement HOLD_DOC = locateElement("name", "HOLD_DOC");
		selectDropDownUsingText(HOLD_DOC, HOLD);
		color(HOLD_DOC,Mandatory);
		Logger(HOLD_DOC);
		
//Consignee of the goods		
		Row row26 = sheet.getRow(23);
		Cell cell26 = row26.getCell(2);
		String Consignee = cell26.getStringCellValue();
		WebElement GOODS_CONS_TO = locateElement("name","GOODS_CONS_TO");
		selectDropDownUsingText(GOODS_CONS_TO,Consignee);
		color(GOODS_CONS_TO,Optional);
		Logger(GOODS_CONS_TO);
//Document

//Draft			
		Logger Document = Logger.getLogger("Draft");
		WebElement Draft = locateElement("name", "DRAFT_1");
		type(Draft, "5");
		Document.info(Draft.getAttribute("value"));
		WebElement Draft1 = locateElement("name", "DRAFT_2");
		type(Draft1, "5");
		Document.info(Draft1.getAttribute("value"));
//INVOICE			
		Logger Document1 = Logger.getLogger("INVOICE");
		WebElement INVOICE = locateElement("name", "INVOICE_1");
		type(INVOICE, "5");
		Document1.info(INVOICE.getAttribute("value"));
		WebElement INVOICE1 = locateElement("name", "INVOICE_2");
		type(INVOICE1, "5");
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
			Logger Document3 = Logger.getLogger("Vessel Cert");
			WebElement Vessel = locateElement("name", "VESSEL_CERT_1");
			type(Vessel,"5");
			Document211.info(Vessel.getAttribute("value"));
			WebElement Vessel1 = locateElement("name", "VESSEL_CERT_2");
			type(Vessel1,"5");
			Document3.info(Vessel1.getAttribute("value"));
//Freight Inv
			Logger Document31 = Logger.getLogger("Freight Inv");
			WebElement Freight = locateElement("name", "FREIGHT_INV_1");
			type(Freight,"5");
			Document211.info(Freight.getAttribute("value"));
			WebElement Freight1 = locateElement("name", "FREIGHT_INV_2");
			type(Freight1,"5");
			Document31.info(Freight1.getAttribute("value"));
//Beneficiary Cert
			Logger Document311 = Logger.getLogger("Beneficiary Cert");
			WebElement Beneficiary = locateElement("name", "BENEF_CERT_1");
			type(Beneficiary,"5");
			Document211.info(Beneficiary.getAttribute("value"));
			WebElement Beneficiary1 = locateElement("name", "BENEF_CERT_2");
			type(Beneficiary1,"5");
			Document311.info(Beneficiary1.getAttribute("value"));
//OTHERS
			Logger Document4 = Logger.getLogger("OTHERS");
			WebElement OTHERS = locateElement("name", "OTHERS_1");
			type(OTHERS,"5");
			Document211.info(OTHERS.getAttribute("value"));
			WebElement OTHERS1 = locateElement("name", "OTHERS_2");
			type(OTHERS1,"5");
			Document4.info(OTHERS1.getAttribute("value"));

//Mail Method 1st
			Row row261 = sheet.getRow(23);
			Cell cell261 = row261.getCell(4);
			String Mail = cell261.getStringCellValue();
			WebElement MAIL_METHOD_1ST = locateElement("name","MAIL_METHOD_1ST");
			selectDropDownUsingText(MAIL_METHOD_1ST,Mail);
			color(MAIL_METHOD_1ST,Optional);
			Logger(MAIL_METHOD_1ST);
			
//Mail Method 2st
			Row row2611 = sheet.getRow(23);
			Cell cell2611 = row2611.getCell(6);
			String Mail1 = cell2611.getStringCellValue();
			WebElement MAIL_METHOD_2ND = locateElement("name","MAIL_METHOD_2ND");
			selectDropDownUsingText(MAIL_METHOD_2ND,Mail1);
			color(MAIL_METHOD_2ND,Optional);
			Logger(MAIL_METHOD_2ND);
//Goods ETA Date
			Row row1111 = sheet.getRow(25);
			Cell cell1111 = row1111.getCell(2);
			String GOODS_ETA = getCellValueAsString(cell1111);
			WebElement GOODS_ETA_DT = locateElement("name", "GOODS_ETA_DT");
			type(GOODS_ETA_DT,GOODS_ETA);
			driver.findElement(By.name("SHIPPED_BY")).click();
			Thread.sleep(1000);
			acceptAlert();
			Date_Before(GOODS_ETA_DT); 
			
//Ship From	
			Row row06 = sheet.getRow(25);
			Cell cell06 = row06.getCell(4);
			String Ship = cell06.getStringCellValue();
			WebElement SHIP_FM_CNTY_CODE = locateElement("name","SHIP_FM_CNTY_CODE");
			selectDropDownUsingText(SHIP_FM_CNTY_CODE,Ship);
			color(SHIP_FM_CNTY_CODE,Optional);
			Logger(SHIP_FM_CNTY_CODE);
			
//Ship To			
			Row row061 = sheet.getRow(25);
			Cell cell061 = row061.getCell(6);
			String Ship1 = cell061.getStringCellValue();
			WebElement SHIP_TO_CNTY_CODE = locateElement("name","SHIP_TO_CNTY_CODE");
			selectDropDownUsingText(SHIP_TO_CNTY_CODE,Ship1);
			color(SHIP_TO_CNTY_CODE,Optional);
			Logger(SHIP_TO_CNTY_CODE);
//Goods Shipped By
			WebElement Shipped = locateElement("name","SHIPPED_BY");
			type(Shipped,"Ship");
//Transport Doc No.			
			WebElement Transport = locateElement("name","TRNSPT_DOC_NO");
			type(Transport,"763990545");
//Goods Description		
			WebElement Description = locateElement("name","GOODS_DESC");
			type(Description,"Test");
//Charges
			WebElement Charges2 = locateElement("id","G");
			click(Charges2);
			//Charges();
//Paid By	
			Logger Charges1 = Logger.getLogger("Charges");
			Row row0611 = sheet.getRow(27);
			Cell cell0611 = row0611.getCell(2);
			String Paid = cell0611.getStringCellValue();
			WebElement CHG_FLD_ALL_CHARGE_FOR = locateElement("name","CHG_FLD_ALL_CHARGE_FOR");
			selectDropDownUsingText(CHG_FLD_ALL_CHARGE_FOR,Paid);
			WebElement CHG_FLD_LOCAL_CUST_AC_NO = locateElement("name","CHG_FLD_LOCAL_CUST_AC_NO");//AC/NO
			WebElement CHG_GETAC_BTN = locateElement("name","CHG_GETAC_BTN"); // A/c CUBK Button
			WebElement CHG_VALUE_DATE = locateElement("name","CHG_VALUE_DATE"); //Value Date
			WebElement CHG_FLD_LOCAL_CUST_CCY = locateElement("name","CHG_FLD_LOCAL_CUST_CCY");//Customer Pay Currency	
			WebElement CHG_FLD_COLLECT_CCY = locateElement("name","CHG_FLD_COLLECT_CCY"); //Bank Collection Currency
			WebElement CHG_FLD_ALL_BAL_CCY = locateElement("name","CHG_FLD_ALL_BAL_CCY"); //Unpaid Currency
			WebElement CHG_LOCAL_CUST_PAY_RATE = locateElement("name","CHG_LOCAL_CUST_PAY_RATE"); //Local Rate
			WebElement CHG_FOREIGN_CUST_PAY_RATE = locateElement("name","CHG_FOREIGN_CUST_PAY_RATE"); //Foreign Rate
			WebElement CHG_FLD_ALL_CHARGE_AT = locateElement("name","CHG_FLD_ALL_CHARGE_AT");//Paid At
			System.out.println(Paid);
			if(Paid.equals("DRAWER")){
				color(CHG_FLD_ALL_CHARGE_AT,Optional);
				color(CHG_FLD_ALL_CHARGE_FOR,Optional);
				color(CHG_FLD_LOCAL_CUST_AC_NO,Protected);
				color(CHG_VALUE_DATE,Protected);
				color(CHG_FOREIGN_CUST_PAY_RATE,Mandatory);
				color(CHG_LOCAL_CUST_PAY_RATE,Mandatory);
				color(CHG_FLD_LOCAL_CUST_CCY,Protected);
				color(CHG_FLD_COLLECT_CCY,Optional);
				color(CHG_FLD_ALL_BAL_CCY,Optional);
				verifyisDisable(CHG_GETAC_BTN);
			}
			String Customer_Pay_Currency= CHG_FLD_LOCAL_CUST_CCY.getAttribute("value");
			String Bank_Collection_Currency = CHG_FLD_COLLECT_CCY.getAttribute("value");
			String 	Local_Rate = CHG_LOCAL_CUST_PAY_RATE.getAttribute("value");
			String 	foreign_Rate = CHG_FOREIGN_CUST_PAY_RATE.getAttribute("value");
			//Customer Pay Currency
			if(Customer_Pay_Currency.equals(Currency)){
				System.out.println("Collection Currency and Customer Pay Currency equal");
			}
			else{
				Charges1.error("Collection Currency and Customer Pay Currency not equal");
			}
			//	Local Rate
			if(Customer_Pay_Currency.equals(Bank_Collection_Currency)){
				if(Local_Rate.equals(foreign_Rate)){
					System.out.println("Local Rate and foreign Rate equal");
				}
				else{
					Charges1.error("Local Rate and foreign Rate not equal");
				}
			}
			else{
				if(foreign_Rate.toString().equals("1") && Local_Rate.length() > 0){
					System.out.println("foreign Rate value 1 and Local Rate granter than 0");
				}
				else{
					Charges1.error("foreign Rate accept more than 1  and Local Rate granter than 0");
				}
			}
			//Value date
			Thread.sleep(1000);
			String Value_Date = CHG_VALUE_DATE.getAttribute("value");
			if (Value_Date.equals(date)){
				Charges1.info("The Value Date should be always current date:" + Value_Date);
			}
			else{
				Charges1.error("The Value Date should not current date:" + Value_Date);
			}
//Collection Commission Charge CCY/Amount
			Logger Commission_1 = Logger.getLogger("Charges_Commission");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");//Collection Commission
			WebElement CHG_FLD_ACTIVE_AMT_2 = locateElement("name","CHG_FLD_ACTIVE_AMT_2"); //Deferred Payment
			WebElement CHG_FLD_ACTIVE_AMT_3 = locateElement("name","CHG_FLD_ACTIVE_AMT_3");//Documents Free of Payment
			String Commission = CHG_FLD_ACTIVE_AMT_1.getAttribute("value");
			String Payment = CHG_FLD_ACTIVE_AMT_2.getAttribute("value");
			String FreeofPayment = CHG_FLD_ACTIVE_AMT_3.getAttribute("value");
			System.out.println(Commission);
			switch (DELVR_DOC_AGST ) {
			case "D/P":
				if(Release.equals("Free of Payment")){
					if(Double.parseDouble(FreeofPayment)  > 0 && Payment.contains("0.00") && Commission.contains("0.00")){
						System.out.println("Deliver Documents Against Free of Payment, Collection Commission amount based on the Collection Currency and Amount "+FreeofPayment+ " calculated" );
					}
					else{
						Commission_1.error("Deliver Documents Against Free of Payment, Collection Commission amount based on the Collection Currency and Amount "+FreeofPayment+ " Not calculated");
					}
				}
				
				else{
					double Commission1 = Double.parseDouble(Commission.replace(",", ""));
					if (Commission1 > 0  && Payment.contains("0.00") && FreeofPayment.contains("0.00")) {
						
						System.out.println("Deliver Documents Against D/P, Collection Commission amount based on the Collection Currency and Amount "+Commission+ " calculated" );
					}
					else{
						Commission_1.error("Deliver Documents Against D/P, Collection Commission amount based on the Collection Currency and Amount "+Commission+ " Not calculated");
					}
				}
				break;
			case "D/A":
			case "D/A and Aval":
				if (Release.equals("Free of Payment") ) {
					if(Payment.contains("0.00") && Commission.contains("0.00") && Double.parseDouble(FreeofPayment)  > 0 ){
						System.out.println("Deliver Documents Against D/A, Collection Commission amount based on the Collection Currency and Amount "+FreeofPayment+ " calculated");
					}
					else{
						Commission_1.error("Deliver Documents Against D/A, Collection Commission amount based on the Collection Currency and Amount "+FreeofPayment+ " Not calculated");
					}
				}
				else{
					double Commission1 = Double.parseDouble(Commission.replace(",", ""));
					double Commission11 = Double.parseDouble(Payment.replace(",", ""));
					if(Commission11  > 0 && Commission1 > 0 && FreeofPayment.contains("0.00")){
						System.out.println("Deliver Documents Against D/A, Collection Commission and Deferred Payment amount based on the Collection Currency and Amount calculated" );
					}
					else{
						Commission_1.error("Deliver Documents Against D/A, Collection Commission and Deferred Payment amount based on the Collection Currency and Amount not calculated" );
					}
				}
				break;
			}
			
//PaidAt	
			Logger Paid_At = Logger.getLogger("Paid At");
			Row row07 = sheet.getRow(27);
			Cell cell07 = row07.getCell(4);
			String PaidAt = cell07.getStringCellValue();
			selectDropDownUsingText(CHG_FLD_ALL_CHARGE_AT,PaidAt);
			WebElement CHG_FLD_LOCAL_COLLECT_CHG_TOTAL = locateElement("name","CHG_FLD_LOCAL_COLLECT_CHG_TOTAL");
			String CollectCCYAmount = CHG_FLD_LOCAL_COLLECT_CHG_TOTAL.getAttribute("value");
//	Collect CCY/Amount
			Logger Collect_CCY = Logger.getLogger("Collect CCY/Amount");
			WebElement CHG_FLD_COLLECT_AMT_1 = locateElement("name","CHG_FLD_COLLECT_AMT_1");
			WebElement CHG_FLD_COLLECT_AMT_2 = locateElement("name","CHG_FLD_COLLECT_AMT_2");
			WebElement CHG_FLD_COLLECT_AMT_3 = locateElement("name","CHG_FLD_COLLECT_AMT_3");
			WebElement CHG_FLD_COLLECT_AMT_4 = locateElement("name","CHG_FLD_COLLECT_AMT_4");
			WebElement CHG_FLD_COLLECT_AMT_5 = locateElement("name","CHG_FLD_COLLECT_AMT_5");
			WebElement CHG_FLD_COLLECT_AMT_6 = locateElement("name","CHG_FLD_COLLECT_AMT_6");
			WebElement CHG_FLD_COLLECT_AMT_7 = locateElement("name","CHG_FLD_COLLECT_AMT_7");
			WebElement CHG_FLD_COLLECT_AMT_8 = locateElement("name","CHG_FLD_COLLECT_AMT_8");
			ArrayList<String> list=new ArrayList<String>();
			list.add(CHG_FLD_COLLECT_AMT_1.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_2.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_3.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_4.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_5.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_6.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_7.getAttribute("value"));
			list.add(CHG_FLD_COLLECT_AMT_8.getAttribute("value"));
			
			/*double sum = 0;
			String as= "";
			for (int i = 0; i < list.size(); i++) {
				as = list.get(i).replace(",", "");
				sum = sum + Double.parseDouble(as);
			}
			StringBuilder strBuilder = new StringBuilder(CollectCCYAmount.replace(",", ""));
			strBuilder.delete(5, 6);
			if (strBuilder.toString().equals(Double.toString(sum))) {
				System.out.println("Collect CCY/Amount total equal " + CollectCCYAmount);
			} else {
				Collect_CCY.error("Collect CCY/Amount total not equal " + CollectCCYAmount);
			}*/
			
//	Pay CCY/Amount
			WebElement CHG_FLD_PAY_AMT_1 = locateElement("name","CHG_FLD_PAY_AMT_1");
			WebElement CHG_FLD_PAY_AMT_2 = locateElement("name","CHG_FLD_PAY_AMT_2");
			WebElement CHG_FLD_PAY_AMT_3 = locateElement("name","CHG_FLD_PAY_AMT_3");
			WebElement CHG_FLD_PAY_AMT_4 = locateElement("name","CHG_FLD_PAY_AMT_4");
			WebElement CHG_FLD_PAY_AMT_5 = locateElement("name","CHG_FLD_PAY_AMT_5");
			WebElement CHG_FLD_PAY_AMT_6 = locateElement("name","CHG_FLD_PAY_AMT_6");
			WebElement CHG_FLD_PAY_AMT_7 = locateElement("name","CHG_FLD_PAY_AMT_7");
			WebElement CHG_FLD_PAY_AMT_8 = locateElement("name","CHG_FLD_PAY_AMT_8");
			
			ArrayList<String> list_1=new ArrayList<String>();
			list_1.add(CHG_FLD_PAY_AMT_1.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_2.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_3.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_4.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_5.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_6.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_7.getAttribute("value"));
			list_1.add(CHG_FLD_PAY_AMT_8.getAttribute("value"));
			
/*//	Unpaid CCY/Amount			
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
//	Discount Rate
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
//Discount Amount
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
//Collect CCY/Amount	
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
//Pay CCY/Amount 				
			
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			WebElement CHG_FLD_ACTIVE_AMT_1 = locateElement("name","CHG_FLD_ACTIVE_AMT_1");
			*/
		switch (PaidAt) {
		case "TRANSACTION":
			Row rowPA = sheet.getRow(37);
			Cell cell071 = rowPA.getCell(2);
			String PaidAt1 = getCellValueAsString(cell071);
			if (Paid.equals("DRAWER")) {
				color(CHG_FLD_LOCAL_CUST_AC_NO, Protected);
				color(CHG_FLD_LOCAL_CUST_CCY, Protected);
				verifyisDisable(CHG_GETAC_BTN);
			}

			color(CHG_FLD_ALL_CHARGE_AT, Optional);
			color(CHG_FLD_ALL_CHARGE_FOR, Optional);
			color(CHG_FLD_LOCAL_CUST_AC_NO, Mandatory);
			color(CHG_VALUE_DATE, Mandatory);
			color(CHG_FOREIGN_CUST_PAY_RATE, Mandatory);
			color(CHG_LOCAL_CUST_PAY_RATE, Mandatory);
			color(CHG_FLD_LOCAL_CUST_CCY, Mandatory);
			color(CHG_FLD_COLLECT_CCY, Optional);
			color(CHG_FLD_ALL_BAL_CCY, Optional);
			verifyisEnabled(CHG_GETAC_BTN);

			if (PaidAt1 == null || PaidAt1.isEmpty()) {
				click(CHG_GETAC_BTN);
				Thread.sleep(1000);
				Set<String> windowId = driver.getWindowHandles();
				Iterator<String> itererator = windowId.iterator();
				String mainWinID = itererator.next();
				String newAdwinID = itererator.next();
				driver.switchTo().window(newAdwinID);
				driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
				driver.switchTo().window(mainWinID);
				switchToFramest("work"); 
				Thread.sleep(1000);
				Logger(CHG_FLD_LOCAL_CUST_AC_NO);
			} else {
				type(CHG_FLD_LOCAL_CUST_AC_NO, PaidAt1);
				Logger(CHG_FLD_LOCAL_CUST_AC_NO);
			}
			// Collect CCY/Amount
			/*double sum = 0;
			for (int i = 0; i < list.size(); i++) {
				sum += Double.parseDouble(list.get(i));
			}

			StringBuilder strBuilder = new StringBuilder(CollectCCYAmount);
			strBuilder.delete(5, 6);
			if (strBuilder.toString().equals(Double.toString(sum))) {
				System.out.println("Collect CCY/Amount total equal" + CollectCCYAmount);
			} else {
				Collect_CCY.error("Collect CCY/Amount total equal" + CollectCCYAmount);
			}*/
			// Pay CCY/Amount
			/*for (int i = 0; i < list.size(); i++) {
				sum += Double.parseDouble(list.get(i));
			}

			StringBuilder strBuilder_2 = new StringBuilder(CollectCCYAmount);
			strBuilder_2.delete(5, 6);
			if (strBuilder.toString().equals(Double.toString(sum))) {
				System.out.println("Collect CCY/Amount total equal" + CollectCCYAmount);
			} else {
				Collect_CCY.error("Collect CCY/Amount total equal" + CollectCCYAmount);
			}*/
			break;
		case "DEFERRED":
		case "WAIVED":
			// Collect CCY/Amount
			break;
		}
/*//Note
			WebElement Note = locateElement("id","F");
			click(Note);
			Logger log05 = Logger.getLogger("Note");
			WebElement Note1 = locateElement("id","NOTES");
			type(Note1,"Welcome");
			log05.info(Note1.getAttribute("value"));
//Diary
			WebElement Our21 = locateElement("id", "I");
			click(Our21);
			Diary(); */
//Advice 			
			/*WebElement Advice = locateElement("id", "E");
			click(Advice);
			Advice();
			Thread.sleep(1000);*/
//Voucher
			/*if(PaidAt.equals("TRANSACTION")){
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame("eeToolbar");
				driver.findElement(By.name("_vchview")).click();
				Thread.sleep(5000);
				Set<String> window = driver.getWindowHandles();
				Iterator<String> itererator2 = window.iterator();
				String mainWin = itererator2.next();
				String newAdwin = itererator2.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				Thread.sleep(2000);
				driver.manage().window().maximize();
				
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='_Cancel']")).click();
				Thread.sleep(5000);
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
				switchToFramest("work");
			}*/
			
// Swift MT410
			// First window
            /*Set<String> win = driver.getWindowHandles();
            Iterator<String> iterate = win.iterator();
            String first_window = iterate.next();
            
            driver.switchTo().defaultContent();
			driver.switchTo().frame("eeToolbar");
			driver.findElement(By.name("_preswift")).click();
			Thread.sleep(1000);
			
            // second window
            win = driver.getWindowHandles();
            iterate = win.iterator();

            first_window = iterate.next();
            String second_window = iterate.next();
            driver.switchTo().window(second_window);
            Thread.sleep(10000);

            driver.findElement(By.xpath("/html/body/form/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/div/table/tbody/tr[4]/td/table/tbody/tr[2]/td[3]/p")).click();

            // ThirWindow
            win = driver.getWindowHandles();
            iterate = win.iterator();
            first_window = iterate.next();
            second_window = iterate.next();
            String Third_window = iterate.next();
            driver.switchTo().window(Third_window);
            Thread.sleep(10000);
         
            Logger Swift = Logger.getLogger("SWIFT");
			WebElement Receiver_BIC_Code = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[7]/td[2]");
			WebElement Sending_Bank_TRN = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[10]/td[2]");
			WebElement Related_Reference = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[13]/td[2]");
			WebElement Amount_Acknowledged = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[16]/td[2]");
			String BIC_Code = Receiver_BIC_Code.getText();
			String Sending_Bank = Sending_Bank_TRN.getText();
			String Related_Ref2 = Related_Reference.getText();
			String Amount_Ack = Amount_Acknowledged.getText();
//Receiver_BIC_Code            
            if(BIC_Code.contains(REMIT_BK_SWift_ADD)){
            	Swift.info("Receiver_BIC_Code and Remitting Bank SWIFT Tag/Address is equal ");
            }
            else{
            	Swift.error("Receiver_BIC_Code and Remitting Bank SWIFT Tag/Address is not equal ");
            }
//Sending_Bank_TRN           
            if(Sending_Bank.equals(C_MAIN_REF)){
            	Swift.info("Sending_Bank_TRN and C_MAIN_REF is equal ");
            }
            else{
            	Swift.error("Sending_Bank_TRN and C_MAIN_REF is not equal ");
            }
//Related_Reference           
            if(Related_Ref2.equals(COLL_NO)){
            	Swift.info("Related_Reference and COLL_NO is equal ");
            }
            else{
            	Swift.error("Related_Reference and COLL_NO is not equal ");
            }
//Amount_Acknowledged    
            
            if(MT410_TAG_32K.equals("K")){
            	
            	if (Amount_Ack.contains(Currency) && Amount_Ack.contains(Amount) && Amount_Ack.contains(TEMP_TENOR_32)) {
					Swift.info("Amount_Acknowledged, collection amount, collection currency, Tag32k fields value are equal");
				}else{
	            	Swift.error("Amount_Acknowledged, collection amount, collection currency, Tag32k fields value are not equal");
	            }
            }
            else{
				if (Amount_Ack.contains(Currency) && Amount_Ack.contains(Amount)) {
					Swift.info("Amount_Acknowledged, collection amount, collection currency is equal");
				}
				else{
					Swift.error("Amount_Acknowledged, collection amount, collection currency fields value are not equal");
				}
            }
            
            Thread.sleep(3000);
            driver.close();
            driver.switchTo().window(second_window);
            Thread.sleep(3000);
            driver.close();
            driver.switchTo().window(first_window);
			Thread.sleep(3000);*/
			
// Swift Advice Message
		/*switch (Type_of_message) {
		case "MT199":
		case "MT299":
		case "MT499":
		case "MT999":
			// First window
			Set<String> win1 = driver.getWindowHandles();
			Iterator<String> iterate1 = win1.iterator();
			String first_window1 = iterate1.next();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("eeToolbar");
			driver.findElement(By.name("_preswift")).click();
			Thread.sleep(1000);
			// second window
			win1 = driver.getWindowHandles();
			iterate1 = win1.iterator();

			first_window1 = iterate1.next();
			String second_window1 = iterate1.next();
			driver.switchTo().window(second_window1);
			Thread.sleep(10000);
			driver.findElement(By.xpath("/html/body/form/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/div/table/tbody/tr[4]/td/table/tbody/tr[3]/td[3]/p")).click();
			// ThirWindow
			win1 = driver.getWindowHandles();
			iterate1 = win1.iterator();
			first_window1 = iterate1.next();
			second_window1 = iterate1.next();
			String Third_window1 = iterate1.next();
			driver.switchTo().window(Third_window1);
			Thread.sleep(10000);
			Logger Swift1 = Logger.getLogger("SWIFT");
			WebElement Receiver_BIC_Code1 = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[7]/td[2]");
			WebElement Transaction_Reference_Number = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[10]/td[2]");
			WebElement Related_Reference1 = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[13]/td[2]");
			WebElement Narrative = locateElement("xpath","/html/body/table/tbody/tr[5]/td/table/tbody/tr[1]/td[2]/div/table[1]/tbody/tr[4]/td/table/tbody/tr[16]/td[2]");
			String BIC_Code1 = Receiver_BIC_Code1.getText();
			String Reference_Number = Transaction_Reference_Number.getText();
			String Related_Ref1 = Related_Reference1.getText();
			String Narr = Narrative.getText();

			// Receiver_BIC_Code
			if (BIC_Code1.contains(SWIFT_TagAddress)) {
				Swift1.info("Receiver_BIC_Code and Remitting Bank SWIFT Tag/Address is equal ");
			} else {
				Swift1.error("Receiver_BIC_Code and Remitting Bank SWIFT Tag/Address is not equal ");
			}
			// Transaction_Reference_Number
			if (Reference_Number.equals(C_MAIN_REF)) {
				Swift1.info("Transaction_Reference_Number and C_MAIN_REF is equal ");
			} else {
				Swift1.error("Transaction_Reference_Number and C_MAIN_REF is not equal ");
			}
			// Related_Reference
			if (Related_Ref1.equals(Related_Ref)) {
				Swift1.info("Related_Reference and COLL_NO is equal ");
			} else {
				Swift1.error("Related_Reference and COLL_NO is not equal ");
			}
			// Narrative
			if (Narr.equalsIgnoreCase(Narrative_MT)) {
				Swift1.info("Narrative (MT n99 Tag 79Z) value is equal ");
			} else {
				Swift1.error("Narrative (MT n99 Tag 79Z) value is not equal ");
			}

			Thread.sleep(3000);
			driver.close();

			driver.switchTo().window(second_window1);

			Thread.sleep(3000);
			driver.close();

			driver.switchTo().window(first_window1);
			Thread.sleep(3000);
			break;
		case"Mail":
		case"Fax":
		case"Email":
			
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("eeToolbar");
			driver.findElement(By.name("_predoc")).click();
			Thread.sleep(5000);
			Set<String> window = driver.getWindowHandles();
			Iterator<String> itererator2 = window.iterator();
			String mainWin = itererator2.next();
			Thread.sleep(1000);
			String newAdwin = itererator2.next();
			driver.switchTo().window(newAdwin);
			System.out.println(driver.getTitle());
			Thread.sleep(2000);
			driver.manage().window().maximize();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='_Cancel']")).click();
			Thread.sleep(5000);
			driver.switchTo().window(mainWin);
			System.out.println(driver.getTitle());
			switchToFramest("work");
			break;
		}*/
//confirmation button 
			Confirm();
			RecoredStop();
			
//supervisor Release
			
			IMCO_SupervisorRelease();

	}
}


	