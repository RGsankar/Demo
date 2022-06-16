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

public class IMCOAcceptance extends SeMethods{
	
	@Test
	public void IMCOAccept() throws IOException, InterruptedException, ATUTestRecorderException, ParseException{		
//login Page
		
		loginpage();
//IMCO Acceptance
		
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Import Collection");
		click(Import);
		log4.info("Import Collection");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IMCO Acceptance");
		click(Function);
		log5.info("IMCO Acceptance");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300320F05030701644");
		click(FunctionGroup);
		log6.info("Acceptance");
//catalog page	
		
		Catalog();
// Main
		
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Acceptance");
		workbook.close();
//Bill Instruction
		
		Logger Bill = Logger.getLogger("Bill Instruction");
		Row row19 = sheet.getRow(3);
		Cell cell19 = row19.getCell(6);
		String Instruction1 = cell19.getStringCellValue();
		WebElement Our111 = locateElement("name","BILL_INSTR");
		selectDropDownUsingText(Our111,Instruction1);
		Bill.info(Our111.getAttribute("value"));
//Release Order Reference
		Logger log8111 = Logger.getLogger("Release Order Reference");
		Row row21111 = sheet.getRow(3);
		Cell cell21111 = row21111.getCell(4);
		int Amount11 = (int) cell21111.getNumericCellValue();
		if((Integer.toString(Amount11)==null)||(Amount11==0)){
			WebElement ele811111 = locateElement("id", "REL_ORDER_REF");
			type(ele811111,Integer.toString(Amount11));
			log8111.info(ele811111.getAttribute("value"));
		}
		else{
			WebElement ele811111 = locateElement("id", "REL_ORDER_REF");
			type(ele811111,Integer.toString(Amount11));
			log8111.info(ele811111.getAttribute("value"));
		}
//Acceptance Date
		Logger log8 = Logger.getLogger("Acceptance Date");
		Row row111 = sheet.getRow(3);
		Cell cell111 = row111.getCell(2);
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date numberAsString1 = cell111.getDateCellValue();
		WebElement ele1 = locateElement("id","ACPT_DT");
		type(ele1,formatter1.format(numberAsString1));
		log8.info(ele1.getAttribute("value"));
//	Advice Type
		Logger log10 = Logger.getLogger("Advice Type");
		Row row11 = sheet.getRow(5);
		Cell cell11 = row11.getCell(2);
		String Role = cell11.getStringCellValue();
		WebElement Our = locateElement("name", "ADV_FLG");
		selectDropDownUsingText(Our, Role);
		log10.info(Our.getAttribute("value"));
// Advice Type No
		Logger log81 = Logger.getLogger("Advice Type No");
		Row row21 = sheet.getRow(5);
		Cell cell21 = row21.getCell(4);
		int Amount111 = (int) cell21.getNumericCellValue();
		if((Integer.toString(Amount111)==null)||(Amount111==0)){
			WebElement ele811111 = locateElement("id", "APLB_RULE_NARR");
			type(ele811111,Integer.toString(Amount111));
			log81.info(ele811111.getAttribute("value"));
		}
		else{
			WebElement ele811111 = locateElement("id", "APLB_RULE_NARR");
			type(ele811111,Integer.toString(Amount111));
			log81.info(ele811111.getAttribute("value"));
		}
//Sender's Charges
		Row row31 = sheet.getRow(5);
		Cell cell31 = row31.getCell(6);
		String Month = cell31.getStringCellValue();
		if (Month==""){
		WebElement Financia = locateElement("id","MT416_71F_CCY");
		selectDropDownUsingText(Financia,Month);
		}
		else{
			Logger log911 = Logger.getLogger("Sender's Charges");
			WebElement Financia = locateElement("id","MT416_71F_CCY");
			selectDropDownUsingText(Financia,Month);
			log911.info(Financia.getAttribute("value"));
		}
////Sender's Amount		
		Logger log81111 = Logger.getLogger("Sender's Amount	");
		Row row211111 = sheet.getRow(7);
		Cell cell211111 = row211111.getCell(2);
		int Amount1111 = (int) cell211111.getNumericCellValue();
		if((Integer.toString(Amount1111)==null)||(Amount1111==0)){
			WebElement ele11 = locateElement("id", "MT416_71F_AMT");
			Clear(ele11);
			WebElement ele811111 = locateElement("id", "MT416_71F_AMT");
			type(ele811111,Integer.toString(Amount1111));
			log81111.info(ele811111.getAttribute("value"));
		}
		else{
			WebElement ele111 = locateElement("id", "MT416_71F_AMT");
			click(ele111);
			WebElement ele8111111 = locateElement("id", "MT416_71F_AMT");
			type(ele8111111,Integer.toString(Amount1111));
			log81111.info(ele8111111.getAttribute("value"));
		}
//Narrative
		WebElement ele88 = locateElement("id", "G");
		click(ele88);
		
//Sender To Receiver Instructions(MT412 Tag 72)
		WebElement Receiver = locateElement("id", "BK_TO_BK_INFO");
		type(Receiver,"Welcome");
		
//Advice 
		 Logger log211111 = Logger.getLogger("Open");			
		 WebElement Advice  = locateElement("id","D");
		 click(Advice);
		 log211111.info("Advice Tab");
		 Logger log2011 = Logger.getLogger("Open");	
		 driver.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
		 log2011.info("Bank ");
//Bank			
		 driver.switchTo().frame("frame.AdivceForBankCust");
//Type of Message			 
		 Logger log222 = Logger.getLogger("Type of Message");	
		 Row Message = sheet.getRow(7);
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
			 WebElement Narrative1  = locateElement("id","BANK_NARR_MAIL");
			 type(Narrative1,"Test1");
			 log121.info(Narrative1.getAttribute("value"));
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
			Row row28 = sheet.getRow(7);
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