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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegisterAmendment  extends EPLCsupervisorRelease {
	
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@Test(priority = 2)
	public void m3() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
 //WebDriver dr = new FirefoxDriver();
 System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
 
	// Initialize browser
	 dr=new ChromeDriver();
	Logger log = Logger.getLogger("URL Open");
	//configure log4j properties file
     PropertyConfigurator.configure("Log4j.properties");
      Thread.sleep(1000);
		  dr.get("http://192.168.2.200:9080/EximBillWeb");
		 log.info("Browser Chrome");
		 dr.manage().window().maximize();
/*}
		 @Test
		 public static void Amend1() throws InterruptedException, IOException{*/
			 FileInputStream fis = new FileInputStream("E:\\Testing\\test.xlsx");
			
			 XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 
			 XSSFSheet sheet = workbook.getSheet("RegAmendment");
	
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
			dr.findElement(By.xpath(".//*[@name='EPLC Amendment']")).click();
			log5.info("EPLC Amendment");
			Thread.sleep(1000);
			Logger log6 = Logger.getLogger("Function Group");
			dr.findElement(By.xpath(".//*[@name='G49082300309F05030701968']")).click();
			log6.info("RegisterAmendment");
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
//main		
			Thread.sleep(1000);
		
//Excel Sheet Value 			
			 Row row1 = sheet.getRow(6);
			 Cell cell1 = row1.getCell(2);
			 String Amount = cell1.getStringCellValue();
			//String Amount  = "Increase Amount";
			switch (Amount){
			case "Increase Amount" :
				Thread.sleep(1000);
				Logger log11 = Logger.getLogger("Increase Amount");
				dr.findElement(By.xpath(".//*[@id='INC_AMT']")).click();
				WebElement ele6 =dr.findElement(By.xpath(".//*[@id='INC_AMT']"));
				ele6.sendKeys("1000");
				log11.info(ele6.getAttribute("value"));
				break;
			case "Decrease Amount" :
				Logger log12 = Logger.getLogger("Decrease Amount");
				Thread.sleep(1000);
				dr.findElement(By.xpath(".//*[@id='DEC_AMT']")).click();
				WebElement ele =dr.findElement(By.xpath(".//*[@id='DEC_AMT']"));
				ele.sendKeys("1000");
				log12.info(ele.getAttribute("value"));
				break;
			}
			
//Confirmation Instruction [49]
			Logger log13 = Logger.getLogger("Confirmation Instruction [49]");
			Thread.sleep(1000);
		
//Excel Sheet Value 			
			 Row row11 = sheet.getRow(6);
			 Cell cell11 = row11.getCell(6);
			 String Confirmation = cell11.getStringCellValue();
			//String Confirmation  = "MAY ADD";
			switch (Confirmation){
			case "CONFIRM" :
				Thread.sleep(1000);
				WebElement ele6 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
				ele6.sendKeys("CONFIRM");
				log13.info(ele6.getAttribute("value"));
				break;
			case "MAY ADD":
				Thread.sleep(1000);
				WebElement ele7 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
				ele7.sendKeys("MAY ADD");
				log13.info(ele7.getAttribute("value"));
				break;
			case "WITHOUT" :
				Thread.sleep(1000);
				WebElement ele8 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
				ele8.sendKeys("WITHOUT");
				log13.info(ele8.getAttribute("value"));
				break;
			}
			
//	Our Engagement.
			
			try {
				Logger log14 = Logger.getLogger("Our Engagement");
				 Row row2 = sheet.getRow(6);
				 Cell cell2 = row2.getCell(4);
				 String Our = cell2.getStringCellValue();
				//String Our  = "CONFIRMATION";
				switch (Our){
				case "ADVICE" :
					Thread.sleep(1000);
					WebElement ele8 =dr.findElement(By.xpath(".//*[@id='OUR_ENG']"));
					ele8.sendKeys("ADVICE");
					log14.info(ele8.getAttribute("value"));
					break;
				case "CONFIRMATION" :
					Thread.sleep(1000);
					WebElement ele81 =dr.findElement(By.xpath(".//*[@id='OUR_ENG']"));
					ele81.sendKeys("CONFIRMATION");
					log14.info(ele81.getAttribute("value"));
					Thread.sleep(1000);
					break;
				case "SILENT CONFIRMATION" :
					Thread.sleep(1000);
					WebElement ele811 =dr.findElement(By.xpath(".//*[@id='OUR_ENG']"));
					ele811.sendKeys("SILENT CONFIRMATION");
					log14.info(ele811.getAttribute("value"));
					Thread.sleep(1000);
					break;
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//Add Confirmation to Increased Amount		
				
				try {
					Logger log15 = Logger.getLogger("Add Confirmation to Increased Amount	");
					 Row row3 = sheet.getRow(8);
					 Cell cell3 = row3.getCell(2);
					 String Add = cell3.getStringCellValue();
					//String Add  = "YES";
					switch (Add){
					case "YES" :
						Thread.sleep(1000);
						WebElement ele9 =dr.findElement(By.xpath(".//*[@id='CONF_ADDED']"));
						ele9.sendKeys("YES");
						log15.info(ele9.getAttribute("value"));
						break;
					case "NO" :
						Thread.sleep(1000);
						WebElement add =dr.findElement(By.xpath(".//*[@id='CONF_ADDED']"));
						add.sendKeys("NO");
						log15.info(add.getAttribute("value"));
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//New Expiry Date 
				Logger log16 = Logger.getLogger("New Expiry Date ");
				Thread.sleep(1000);
				WebElement ele9 =dr.findElement(By.xpath(".//*[@id='NEW_EXPIRY_DT']"));
				ele9.sendKeys("2018-06-25");
				log16.info(ele9.getAttribute("value"));
				Thread.sleep(1000);
//New Place 				
				Logger log161 = Logger.getLogger("NEW_EXPIRY_PLACE");
				Thread.sleep(1000);
				WebElement ele91 =dr.findElement(By.xpath(".//*[@id='NEW_EXPIRY_PLC_NARR']"));
				ele91.sendKeys("Chennai");
				log161.info(ele91.getAttribute("value"));
				Thread.sleep(1000);
				
//Detrimental Flag				
				
				Logger log17 = Logger.getLogger("Detrimental Flag");
				 Row row2 = sheet.getRow(8);
				 Cell cell2 = row2.getCell(4);
				 String Flag = cell2.getStringCellValue();
				//String Flag  = "NO";
				switch (Flag){
				case "YES" :
					Thread.sleep(1000);
					WebElement ele8 =dr.findElement(By.xpath(".//*[@id='DETRMNTL_FLG']"));
					ele8.sendKeys("YES");
					log17.info(ele8.getAttribute("value"));
					break;
				case "NO" :
					Thread.sleep(1000);
					WebElement ele81 =dr.findElement(By.xpath(".//*[@id='DETRMNTL_FLG']"));
					ele81.sendKeys("NO");
					log17.info(ele81.getAttribute("value"));
					break;
				}
				
//Tenor Information
				
					Thread.sleep(1000);
					dr.findElement(By.xpath(".//*[@id='C']")).click();
				
//Tenor
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='E']")).click();
					Logger log24 = Logger.getLogger("Available By");
//Excel sheet get value 					
					 Row row6 = sheet.getRow(8);
					 Cell cell6 = row6.getCell(6);
					 String Available = cell6.getStringCellValue();
					//String Available = "BY MIXED PYMT";
					switch(Available){
					case "BY PAYMENT":
						WebElement payment = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
						payment.sendKeys("BY PAYMENT");
						log24.info(payment.getAttribute("value"));
						Thread.sleep(1000);
						dr.findElement(By.xpath("//*[@id='E_div']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/input[2]")).click();
						Thread.sleep(1000);
						 Set<String> windowId = dr.getWindowHandles();    // get  window id of current window
					        Iterator<String> itererator = windowId.iterator();   

					        String mainWinID = itererator.next();
					        String  newAdwinID = itererator.next();

					        dr.switchTo().window(newAdwinID);
					        System.out.println(dr.getTitle());
					       
					        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
					       // dr.findElement(By.xpath("//*[@id='BANK_NARR_TAG_79']")).sendKeys("wfgshfgdsfgfhsfsgfhsfgsfgs");
					        //dr.close();
					        dr.switchTo().window(mainWinID);
					        System.out.println(dr.getTitle());
					       
					        Thread.sleep(2000);
					        dr.switchTo().defaultContent();
							  
							//switch to frame2
							dr.switchTo().frame("work");
					    break;    
					case "BY ACCEPTANCE":
						WebElement payment1 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
						payment1.sendKeys("BY ACCEPTANCE");
						log24.info(payment1.getAttribute("value"));
						Thread.sleep(1000);	
						Logger log25 = Logger.getLogger("Tenor");
						dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']")).clear();
						dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']")).click();
						WebElement Tenor = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
						Tenor.sendKeys("10");
						log25.info(Tenor.getAttribute("value"));
						Thread.sleep(1000);
						Logger log26 = Logger.getLogger("Tenor");
						Thread.sleep(1000);
						WebElement Tenor1 = dr.findElement(By.xpath(".//*[@id='TENOR_TYPE']"));
						Tenor1.sendKeys("DAYS AFTER SIGHT");
						log26.info(Tenor1.getAttribute("value"));
						Thread.sleep(1000);
						dr.findElement(By.xpath("//*[@id='E_div']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/input[2]")).click();
						
						 Set<String> windowId11 = dr.getWindowHandles();    // get  window id of current window
					        Iterator<String> itererator11 = windowId11.iterator();   

					        String mainWinID11 = itererator11.next();
					        String  newAdwinID11 = itererator11.next();

					        dr.switchTo().window(newAdwinID11);
					        System.out.println(dr.getTitle());
					       
					        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
					        dr.switchTo().window(mainWinID11);
					        System.out.println(dr.getTitle());
					        Thread.sleep(2000);
					        dr.switchTo().defaultContent();
							  
							//switch to frame2
							dr.switchTo().frame("work");
							break;
					case "BY NEGOTIATION":
						WebElement payment11 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
						payment11.sendKeys("BY NEGOTIATION");
						log24.info(payment11.getAttribute("value"));
						Thread.sleep(1000);	
						break;
					case "BY DEF PAYMENT":
						WebElement payment111 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
						payment111.sendKeys("BY DEF PAYMENT");
						log24.info(payment111.getAttribute("value"));
						Thread.sleep(1000);	
						dr.findElement(By.xpath(".//*[@id='DEF_PMT_DET']")).sendKeys("test");
						break;
					case "BY MIXED PYMT":
						WebElement payment2 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
						payment2.sendKeys("BY MIXED PYMT");
						log24.info(payment2.getAttribute("value"));
						break;
					}
					Logger log26 = Logger.getLogger("Payable At ");
					Thread.sleep(1000);
					WebElement payment2 = dr.findElement(By.xpath(".//*[@id='PAY_AT']"));
					payment2.sendKeys("at Issuing Bank");
					log26.info(payment2.getAttribute("value"));
					Thread.sleep(1000);
					Logger log27 = Logger.getLogger("Acceptance By ");
					WebElement payment3 = dr.findElement(By.xpath(".//*[@id='ACPT_BY']"));
					payment3.sendKeys("OUR");
					log27.info(payment3.getAttribute("value"));
					Thread.sleep(1000);							
					try {
						dr.findElement(By.xpath(".//*[@id='D']")).click();								
						Thread.sleep(1000);
						dr.findElement(By.xpath("//*[@id='PaymentTerms_ADD']")).click();
						Logger log28 = Logger.getLogger("Sight/Def/Acc Flag");
						Row row7 = sheet.getRow(10);
						 Cell cell7 = row7.getCell(2);
						 String Flag1 = cell7.getStringCellValue();
						//String Flag = "Acceptance";
						switch (Flag1){
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
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("10");
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[5]/td[4]/select")).sendKeys("DAYS AFTER SIGHT");
							break;
						case "Acceptance" :
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Acceptance");
							log28.info("Acceptance");
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).clear();
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).click();
							
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("10");
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
					
//Advice Tab	
					 try {
						Thread.sleep(2000);
						   // dr.switchTo().defaultContent();
							  
							//switch to frame2
							dr.switchTo().frame("work");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Thread.sleep(2000);
					Logger log211111 = Logger.getLogger("Open");			
					dr.findElement(By.xpath(".//*[@id='J']")).click();
					log211111.info("Advice Tab");
					Logger log2011 = Logger.getLogger("Open");	
					dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
					log2011.info("Bank ");
//Bank			
					 Thread.sleep(1000);
						//dr.switchTo().defaultContent();
						  
						//switch to frame2
						dr.switchTo().frame("frame.AdivceForBankCust");
						Thread.sleep(3000);
						Logger log101 = Logger.getLogger("Type of Message");	
					dr.findElement(By.xpath("//*[@id='MESG_TYPE_BANK']")).sendKeys("Mail");
					log101.info("Mail");
					Logger log111 = Logger.getLogger("ID,Name,Address");
					dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[4]/td[2]/input[2]")).click();//CUBK Button
					log111.info("ABNADEHHCGN,ABN AMRO BANK (DEUTSCHLAND) AG,undefined");
	//Alert Message				
					
					Alert alert = dr.switchTo().alert();		
	        		
	// Capturing alert message.    
			        String alertMessage= dr.switchTo().alert().getText();		
			        		
			        // Displaying alert message		
			        System.out.println(alertMessage);	
			        Thread.sleep(3000);
			        		
			        // Accepting alert		
			        alert.accept();		
			        Thread.sleep(3000);
			        Logger log121 = Logger.getLogger("Narrative (Mail)");
			        dr.findElement(By.xpath("//*[@id='BANK_NARR_MAIL']")).sendKeys("Test1");
			       log121.info("Test1");
			        
			        Set<String> windowId = dr.getWindowHandles();    // get  window id of current window
			        Iterator<String> itererator = windowId.iterator();   

			        String mainWinID = itererator.next();
			        String  newAdwinID = itererator.next();

			        dr.switchTo().window(newAdwinID);
			        System.out.println(dr.getTitle());
			       
			        dr.findElement(By.xpath("//*[@id='1']/td[2]/a")).click();
			       // dr.findElement(By.xpath("//*[@id='BANK_NARR_TAG_79']")).sendKeys("wfgshfgdsfgfhsfsgfhsfgsfgs");
			        //dr.close();
			        dr.switchTo().window(mainWinID);
			        System.out.println(dr.getTitle());
			        Thread.sleep(1000);
	//Customer
			        Thread.sleep(1000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("work");
			        
			      
			        dr.switchTo().frame("frame.AdivceForBankCust");
					Thread.sleep(3000);
	//Click Customer
					
					dr.findElement(By.xpath("//*[@id='B']")).click();
					//log4.info("Customer");
					Logger log131 = Logger.getLogger("Type of Message");
					dr.findElement(By.xpath("//*[@id='MESG_TYPE_CUST']")).sendKeys("Mail");
					log131.info("Mail");
					Logger log141 = Logger.getLogger("ID,Name,Address");
					dr.findElement(By.xpath("//*[@id='B_div']/table/tbody/tr[4]/td[2]/input[2]")).click();
					log141.info("009800722,SHANGHAI FEI LONG CO LTD,SHANG HAI,");
	//Alert message close				
					try {
						Alert alert1 = dr.switchTo().alert();		
						
						// Capturing alert message.    
								        String alertMessage1= dr.switchTo().alert().getText();		
								        		
								        // Displaying alert message		
								        System.out.println(alertMessage1);	
								        Thread.sleep(2000);
								        		
								        // Accepting alert		
								        alert1.accept();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
							        Thread.sleep(2000);
							        dr.findElement(By.xpath("//*[@id='CUST_NARR_TAG_79']")).sendKeys("Test");
							        
							        
							        Set<String> window = dr.getWindowHandles();    // get  window id of current window
							        Iterator<String> itererator2 = window.iterator();   

							        String mainWin = itererator2.next();
							        String  newAdwin = itererator2.next();

							        dr.switchTo().window(newAdwin);
							        System.out.println(dr.getTitle());
							       
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							       
							        //dr.close();
							        dr.switchTo().window(mainWin);
							        System.out.println(dr.getTitle());
							        Thread.sleep(1000);
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
									  
									 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\EPLCRegisterAmend.png"));
									 
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