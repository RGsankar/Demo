package EPLCModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AdviceLC extends EPLCsupervisorRelease{
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@Test(priority = 1)
	public void Advice() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
 //WebDriver dr = new FirefoxDriver();
 System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
 
	// Initialize browser
	 dr=new ChromeDriver();
	Logger log = Logger.getLogger("URL Open");
	//configure log4j properties file
     PropertyConfigurator.configure("Log4j.properties");
      Thread.sleep(1000);
		  dr.get("http://192.168.2.200:9080/EximBillWeb/");
		 log.info("Browser Chrome");
		 dr.manage().window().maximize();
/*}
		 @Test
		 public static void Advice() throws InterruptedException, IOException{*/
			 FileInputStream fis = new FileInputStream("E:\\Testing\\test.xlsx");
			
			 XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 
			 XSSFSheet sheet = workbook.getSheet("AdviceLC");
	
			 System.out.println(workbook);
		 Logger log0 = Logger.getLogger(" Bussiness Unit Code  ");
		 Thread.sleep(1000);
		 dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
		 log0.info("CSBANK");
		Logger log1 = Logger.getLogger("User ID");
		Thread.sleep(1000);
		 dr.findElement(By.xpath(".//*[@name='C_USER_ID']")).sendKeys("CSBANKOP");
		 log1.info("USER_OP1");
		 Logger log2 = Logger.getLogger(" Password ");
		 Thread.sleep(1000);
		 dr.findElement(By.xpath(".//*[@id='tipLogPwd']")).sendKeys("1Q1Q1Q1Q");	
		log2.info("1Q1Q1Q1Q");
		 Logger log3 = Logger.getLogger(" Click ");
		 dr.findElement(By.xpath(".//*[@id='Image1']")).click();
		 log3.info("Submit");
		  Thread.sleep(1000);
			//Commented the code for finding the index of the element
		    dr.switchTo().frame(3); //Switching to the frame
			//System.out.println("********We are switched to the iframe*******");
			Thread.sleep(1000);
			Logger log4 = Logger.getLogger("EPLC Module");
			Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@name='Export Letter of Credit']")).click();
			log4.info("Export Letter of Credit");
			Thread.sleep(1000);
			Logger log5 = Logger.getLogger("Function");
			dr.findElement(By.xpath(".//*[@name='EPLC Advice']")).click();
			log5.info("EPLC Advice");
			Thread.sleep(1000);
			Logger log6 = Logger.getLogger("Function Group");
			dr.findElement(By.xpath(".//*[@name='G49082300265F05030701977']")).click();
			log6.info("Advise LC");
			Thread.sleep(2000);
			
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(1000);
			Logger log7 = Logger.getLogger("Reference Number");
			FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\sankar.xlsx");
			 //FileInputStream fis = new FileInputStream("E:\\Testing\\sss.xlsx");
			 XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
			 
			 XSSFSheet sheet1 = workbook1.getSheet("Datatypes in Java");
	
			 System.out.println(workbook1);
			Row row = sheet1.getRow(6);
			 Cell cell = row.getCell(4);
			String LCNO = cell.getStringCellValue();
			WebElement san = dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
			//san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
			san.sendKeys(LCNO);
			log7.info(san.getAttribute("value"));
//confirm button					
			 Thread.sleep(1000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("eeToolbar"); 
		 
		 dr.findElement(By.xpath("//*[@id='_next']")).click();
		
		 Thread.sleep(1000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");

			 Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='CataListTab']/tbody/tr[2]/td/table/tbody/tr/td[2]")).click();

		 Thread.sleep(1000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("eeToolbar"); 
	 
	 dr.findElement(By.xpath("//*[@id='_next']")).click();
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
//Confirmation Instruction [49]
						Logger log11 = Logger.getLogger("Confirmation Instruction [49]");
						Thread.sleep(1000);
					
//Excel Sheet Value 			
						Row row1 = sheet.getRow(4);
						 Cell cell1 = row1.getCell(2);
						 String Confirmation = cell1.getStringCellValue();
						//String Confirmation  = "WITHOUT";
						switch (Confirmation){
						case "CONFIRM" :
							WebElement ele6 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
							ele6.sendKeys("CONFIRM");
							log11.info(ele6.getAttribute("value"));
							break;
						case "MAY ADD":
							WebElement ele7 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
							ele7.sendKeys("MAY ADD");
							log11.info(ele7.getAttribute("value"));
							break;
						case "WITHOUT" :
							WebElement ele8 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
							ele8.sendKeys("WITHOUT");
							log11.info(ele8.getAttribute("value"));
							break;
						}
			
//	Our Engagement
						try {
							Logger log12 = Logger.getLogger("Our Engagement");
							 Row row2 = sheet.getRow(7);
							 Cell cell2 = row2.getCell(2);
							 String Our = cell2.getStringCellValue();
							//String Our  = "CONFIRMATION";
							switch (Our){
							case "ADVICE" :
								WebElement ele8 =dr.findElement(By.xpath(".//*[@id='OUR_ENG']"));
								ele8.sendKeys("ADVICE");
								log12.info(ele8.getAttribute("value"));
								break;
							case "CONFIRMATION" :
								WebElement ele81 =dr.findElement(By.xpath(".//*[@id='OUR_ENG']"));
								ele81.sendKeys("CONFIRMATION");
								log12.info(ele81.getAttribute("value"));
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[20]/td[2]/input[2]")).click();
								
								// get  window id of current window
								 Set<String> windowId11 = dr.getWindowHandles();   
							        Iterator<String> itererator11 = windowId11.iterator();   
							        String mainWinID11 = itererator11.next();
							        String  newAdwinID11 = itererator11.next();
							        dr.switchTo().window(newAdwinID11);
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							        dr.switchTo().window(mainWinID11);
							        Thread.sleep(1000);
							        
							        dr.switchTo().defaultContent();
							        Thread.sleep(2000);
								//switch to frame2
									dr.switchTo().frame("work");
									dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[20]/td[4]/input[2]")).click();
									 Set<String> windowId111 = dr.getWindowHandles();   
								        Iterator<String> itererator111 = windowId111.iterator();   
								        String mainWinID111 = itererator111.next();
								        String  newAdwinID111 = itererator111.next();
								        dr.switchTo().window(newAdwinID111);
								        dr.findElement(By.xpath("//*[@id='1']/td[2]/a")).click();
								        dr.switchTo().window(mainWinID111);
								        Thread.sleep(1000);
								        
								        dr.switchTo().defaultContent();
								        Thread.sleep(2000);
									//switch to frame2
										dr.switchTo().frame("work");
								break;
							case "SILENT CONFIRMATION" :
								WebElement ele811 =dr.findElement(By.xpath(".//*[@id='OUR_ENG']"));
								ele811.sendKeys("SILENT CONFIRMATION");
								log12.info(ele811.getAttribute("value"));
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[20]/td[2]/input[2]")).click();
								
								// get  window id of current window
								 Set<String> windowId2 = dr.getWindowHandles();   
							        Iterator<String> itererator2 = windowId2.iterator();   
							        String mainWinID2 = itererator2.next();
							        String  newAdwinID2 = itererator2.next();
							        dr.switchTo().window(newAdwinID2);
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							        dr.switchTo().window(mainWinID2);
							        Thread.sleep(1000);
							        
							        dr.switchTo().defaultContent();
							        Thread.sleep(2000);
								//switch to frame2
									dr.switchTo().frame("work");
									dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[20]/td[4]/input[2]")).click();
									 Set<String> windowId13 = dr.getWindowHandles();   
								        Iterator<String> itererator3 = windowId13.iterator();   
								        String mainWinID3 = itererator3.next();
								        String  newAdwinID3 = itererator3.next();
								        dr.switchTo().window(newAdwinID3);
								        dr.findElement(By.xpath("//*[@id='1']/td[2]/a")).click();
								        dr.switchTo().window(mainWinID3);
								        Thread.sleep(1000);
								        
								        dr.switchTo().defaultContent();
								        Thread.sleep(2000);
									//switch to frame2
										dr.switchTo().frame("work");
								break;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
//Revolving Information
						Thread.sleep(1000);
						Logger log13 = Logger.getLogger("Revolving Information");
	//Excel sheet get value 					
						Row row3 = sheet.getRow(7);
						 Cell cell3 = row3.getCell(4);
						 String Revolving = cell3.getStringCellValue();
						//String Revolving  =  "YES";
						switch(Revolving){
						case "YES" :
							
					        Thread.sleep(1000);
							WebElement ele51 =dr.findElement(By.xpath(".//*[@id='REV_LC']"));
							ele51.sendKeys("YES");
							 Thread.sleep(1000);
							try {
								Alert alert = dr.switchTo().alert();	
								alert.accept();
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}		
							log13.info(ele51.getAttribute("value"));
							   Thread.sleep(2000);									       
					    	Logger log13a = Logger.getLogger("Cumulative");
	//Excel sheet get value 					
							Row row4 = sheet.getRow(4);
							 Cell cell4 = row4.getCell(6);
							 String Cumulative = cell4.getStringCellValue();
					        //String Cumulative = "Cumulative";
					        switch(Cumulative){
					        case "Cumulative":
					        	Thread.sleep(1000);
								WebElement ele511 =dr.findElement(By.xpath(".//*[@id='CUMULATIVE']"));
								ele511.sendKeys("Cumulative");
								log13a.info(ele511.getAttribute("value"));
								Thread.sleep(1000);
					        
								Logger log13b = Logger.getLogger(" Auto Renewal?");
	//Excel sheet get value 					
								Row row5 = sheet.getRow(11);
								 Cell cell5 = row5.getCell(2);
								 String Renewal = cell5.getStringCellValue();
								//String Renewal = "YES";
								switch(Renewal){
								case "YES":
						        	Thread.sleep(1000);
									WebElement ele5111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
									ele5111.sendKeys("YES");
									log13b.info(ele5111.getAttribute("value"));
									break;
								case "NO":
						        	Thread.sleep(1000);
									WebElement ele51111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
									ele51111.sendKeys("NO");
									log13b.info(ele51111.getAttribute("value"));
									break;
								}
	// No of Times Revolving
								Thread.sleep(1000);
								Logger log13c = Logger.getLogger("No of Times Revolving");
								
								WebElement toClear = dr.findElement(By.xpath(".//*[@id='NO_PRD']"));
								toClear.sendKeys(Keys.CONTROL + "a");
								toClear.sendKeys(Keys.DELETE);
								WebElement Times = dr.findElement(By.xpath(".//*[@id='NO_PRD']"));
								Times.sendKeys("10");
								log13c.info(Times.getAttribute("value"));
								break;
					        case "Non Cumulative":
					        	Thread.sleep(1000);
								WebElement ele5111 =dr.findElement(By.xpath(".//*[@id='CUMULATIVE']"));
								ele5111.sendKeys("Non Cumulative");
								log13a.info(ele5111.getAttribute("value"));
								Thread.sleep(1000);
								Logger log13b1 = Logger.getLogger(" Auto Renewal?");
								String Renewal1 = "YES";
								switch(Renewal1){
								case "YES":
						        	Thread.sleep(1000);
									WebElement ele51111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
									ele51111.sendKeys("YES");
									log13b1.info(ele51111.getAttribute("value"));
									break;
								case "NO":
						        	Thread.sleep(1000);
									WebElement ele511111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
									ele511111.sendKeys("NO");
									log13b1.info(ele511111.getAttribute("value"));
									break;
								}
	// No of Times Revolving
								Thread.sleep(1000);
								Logger log13c1 = Logger.getLogger("No of Times Revolving");
								dr.findElement(By.xpath(".//*[@id='NO_PRD']")).clear();
								dr.findElement(By.xpath(".//*[@id='NO_PRD']")).click();
								WebElement Times1 = dr.findElement(By.xpath(".//*[@id='NO_PRD']"));
								Times1.sendKeys("10");
								log13c1.info(Times1.getAttribute("value"));
								break;
								
					        }
							break;
						case "NO" :
							Thread.sleep(1000);
							WebElement ele511 =dr.findElement(By.xpath(".//*[@id='REV_LC']"));
							ele511.sendKeys("NO");
							log13.info(ele511.getAttribute("value"));
							break;
						
						}						

						
//Applicable Rules [40E]
						Thread.sleep(1000);
						Logger log15 = Logger.getLogger("Applicable Rules [40E]");
//Excel sheet get value 					
						Row row5 = sheet.getRow(4);
						 Cell cell5 = row5.getCell(4);
						 String Rules1 = cell5.getStringCellValue();
						//String Rules1 = "UCP LATEST VERSION";
						switch(Rules1){
						case"EUCP LATEST VERSION":	
							WebElement Rules = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
							Rules.sendKeys("EUCP LATEST VERSION");
							log15.info(Rules.getAttribute("value"));
							break;
						case"EUCPURR LATEST VERSION":	
							WebElement Rules11 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
							Rules11.sendKeys("EUCPURR LATEST VERSION");
							log15.info(Rules11.getAttribute("value"));
							break;
						case"ISP LATEST VERSION":	
							WebElement Rules2 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
							Rules2.sendKeys("ISP LATEST VERSION");
							log15.info(Rules2.getAttribute("value"));
							break;
						case"UCP LATEST VERSION":	
							WebElement Rules3 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
							Rules3.sendKeys("UCP LATEST VERSION");
							log15.info(Rules3.getAttribute("value"));
							break;
						case"UCPURR LATEST VERSION":	
							WebElement Rules31 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
							Rules31.sendKeys("UCPURR LATEST VERSION");
							log15.info(Rules31.getAttribute("value"));
							break;
						case"OTHR":	
							WebElement Rules15 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
							Rules15.sendKeys("OTHR");
							log15.info(Rules15.getAttribute("value"));
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='APLB_RULE_NARR']")).sendKeys("test");
							break;
							
						}
//Mixed Payment:
						try {
							dr.findElement(By.xpath(".//*[@id='D']")).click();								
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='GridDO_Child_0_0']")).click();
							dr.findElement(By.xpath("//*[@id='PaymentTerms_EDIT']")).click();
							
							Logger log28 = Logger.getLogger("Sight/Def/Acc Flag");
							Row row7 = sheet.getRow(7);
							 Cell cell7 = row7.getCell(6);
							 String Flag = cell7.getStringCellValue();
							//String Flag = "Acceptance";
							switch (Flag){
							case "Sight" :
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Sight");
								log28.info("Sight");
								break;
							case "Deferred" :
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Deferred");
								log28.info("Deferred");
								Thread.sleep(1000);
								WebElement toClear = dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input"));
								toClear.sendKeys(Keys.CONTROL + "a");
								toClear.sendKeys(Keys.DELETE);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("50");
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[5]/td[4]/select")).sendKeys("DAYS AFTER SIGHT");
								break;
							case "Acceptance" :
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Acceptance");
								log28.info("Acceptance");
								Thread.sleep(1000);
								WebElement toClear1 = dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input"));
								toClear1.sendKeys(Keys.CONTROL + "a");
								toClear1.sendKeys(Keys.DELETE);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("50");
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[5]/td[4]/select")).sendKeys("DAYS AFTER SIGHT");
								break;
								
							}
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='PaymentTerms_SAVE']")).click();
							
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
//Charges						
						Logger log131 = Logger.getLogger("Charges");
						Thread.sleep(1000);
						dr.findElement(By.xpath("//*[@id='K']")).click();
						Row row2 = sheet.getRow(11);
						 Cell cell2 = row2.getCell(4);
						String Charges1 = cell2.getStringCellValue();
						//String Charges  = "TRANSACTION";
						switch (Charges1){
						case "TRANSACTION" :
							WebElement ele8 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
							ele8.sendKeys("TRANSACTION");
							log131.info(ele8.getAttribute("value"));
							Thread.sleep(1000);
							Logger log01 = Logger.getLogger("AC/NO	");
							WebElement ele01 =dr.findElement(By.xpath("//*[@id='CHG_FLD_LOCAL_CUST_AC_NO']"));
							ele01.sendKeys("54964654");
							log01.info("54964654");
							break;
						case "DEFERRED" :
							WebElement ele81 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
							ele81.sendKeys("DEFERRED");
							log131.info(ele81.getAttribute("value"));
							break;
						case "WAIVED" :
							WebElement ele1 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
							ele1.sendKeys("WAIVED");
							log131.info(ele1.getAttribute("value"));
							break;
							
						}
//Advice TAB						
						Thread.sleep(2000);
						Logger log211111 = Logger.getLogger("Open");			
						dr.findElement(By.xpath(".//*[@id='G']")).click();
						log211111.info("Advice Tab");
						Logger log2011 = Logger.getLogger("Open");	
						dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
						log2011.info("Customer");
	//Bank			
						 Thread.sleep(1000);
						 dr.switchTo().frame("frame.AdivceForBankCust");
							Thread.sleep(3000);
							Logger log101 = Logger.getLogger("	Narrative Mail");	
						dr.findElement(By.xpath("//*[@id='CUST_NARR_TAG_79']")).sendKeys("Test1");
						log101.info("Test1");
//Save Button				        
								        
								        Thread.sleep(1000);
										dr.switchTo().defaultContent();
										  
										//switch to frame2
										dr.switchTo().frame("work");
										 Thread.sleep(1000);
										dr.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();//*[@id="ext-gen310"]
										
										
										 Thread.sleep(1000);
										dr.findElement(By.xpath("//*[@id='AdivceForBankCustClose']")).click();//*[@id="AdivceForBankCustClose"]

										 Thread.sleep(2000);
//Confirm button					        
										dr.switchTo().defaultContent();
												  
												//switch to frame2
												dr.switchTo().frame("eeToolbar"); 
										Logger log01 = Logger.getLogger("End");
										dr.findElement(By.xpath("//*[@id='_confirm']")).click();
										log01.info("Transaction Completed");
										try {
											Alert alert1 = dr.switchTo().alert();		
											alert1.accept();
										} catch (Exception e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
										 Thread.sleep(3000);
										 
							//Screenshot			
										 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
										 try {
										   // now copy the  screenshot to desired location using copyFile method
										  
										 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\EPLCAdviceLC.png"));
										 
										        }
										  
										 catch (IOException e)
										  
										 {
										  
										 System.out.println(e.getMessage());
										  
										     }
							//cancel			         
										 Thread.sleep(5000);
										dr.findElement(By.xpath("//*[@id='_cancel']")).click();
										 

										
										
						

}
}