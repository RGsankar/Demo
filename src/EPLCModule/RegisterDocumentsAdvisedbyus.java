package EPLCModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class RegisterDocumentsAdvisedbyus extends EPLCsupervisorRelease {
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
	@Test(priority = 4)
	public void RegisterDocument() throws InterruptedException, IOException {
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
		 public static void Amend1() throws InterruptedException, IOException{*/
			 FileInputStream fis = new FileInputStream("E:\\Testing\\test.xlsx");
			
			 XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 
			 XSSFSheet sheet = workbook.getSheet("RegDocument");
	
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
			 dr.findElement(By.xpath(".//*[@name='EPLC Presentation']")).click();
			 log5.info("EPLC Presentation");
			 Thread.sleep(1000);
			 Logger log6 = Logger.getLogger("Function Group");
			 dr.findElement(By.xpath(".//*[@name='G49082300266F05030702064']")).click();
			 log6.info("Register Documents Advised by us");
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
			
//Excel sheet get a date value
			
			try {
			    
			} catch (UnhandledAlertException f) {
			    try {
			        Alert alert = dr.switchTo().alert();
			        String alertText = alert.getText();
			        System.out.println("Alert data: " + alertText);
			        alert.accept();
			    } catch (NoAlertPresentException e) {
			        e.printStackTrace();
			    }
			}
			
			Thread.sleep(1000);
			 Logger log81 = Logger.getLogger("Document Cover Letter Date");
			 Row row1 = sheet.getRow(7);
			 Cell cell1 = row1.getCell(2);
			 //double date = cell.getNumericCellValue();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 Thread.sleep(1000);
			 Date numberAsString = cell1.getDateCellValue();
			 WebElement ele =dr.findElement(By.xpath(".//*[@id='PRES_DT']"));
			 ele.sendKeys(formatter.format(numberAsString));
			 log81.info(ele.getAttribute("value"));
			 
//Documents Presented by				
				Thread.sleep(1000);
				Logger log101 = Logger.getLogger("DOC_PRES_BY");
				//Excel sheet get value 					
				 Row row41 = sheet.getRow(7);
				 Cell cell41 = row41.getCell(4);
				 String Presented = cell41.getStringCellValue();
				//String Presented = "Beneficiary";
				switch (Presented){
				case "Beneficiary":
					Thread.sleep(1000);
					Select Country = new Select (dr.findElement(By.xpath("//*[@id='DOC_PRES_BY']")));
					Country.selectByVisibleText("Beneficiary");
					log101.info("Beneficiary");
					break;
				case "Beneficiary's Bank":
					Thread.sleep(1000);
					Select Country1 = new Select (dr.findElement(By.xpath("//*[@id='DOC_PRES_BY']")));
					Country1.selectByVisibleText("Beneficiary's Bank");
					log101.info("Beneficiary's Bank");
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[16]/td/table/tbody/tr[2]/td[2]/input[2]")).click();
					
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
				}
				
//LC Currency and Amount [32B]
				Thread.sleep(1000);
//Excel sheet get value 					
				Row row4 = sheet.getRow(7);
				 Cell cell4 = row4.getCell(6);
				 String LCAMT = cell4.getStringCellValue();
				//String LCAMT = "AED";
				switch (LCAMT){
				case "USD":
					Thread.sleep(1000);
					Logger log1011 = Logger.getLogger("LC_CCY");
					dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[10]/td[2]/select")).sendKeys("USD");
					log1011.info("USD");
					break;
				case "AED":
					Thread.sleep(1000);
					Logger log111 = Logger.getLogger("LC_CCY");
					dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[10]/td[2]/select")).sendKeys("AED");
					log111.info("AED");
					break;
				case "EUR":
					Thread.sleep(1000);
					Logger log121 = Logger.getLogger("LC_CCY");
					dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[10]/td[2]/select")).sendKeys("EUR");
					log121.info("EUR");
					break;
				case "GBP":
					Thread.sleep(1000);
					Logger log131 = Logger.getLogger("LC_CCY");
					dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[10]/td[2]/select")).sendKeys("GBP");
					log131.info("GBP");
					break;
				}
//Amount
				Thread.sleep(1000);
				 Logger log811 = Logger.getLogger("LC_AMT");
				 Row row21 = sheet.getRow(7);
				 Cell cell21 = row21.getCell(8);
				 double Amount = cell21.getNumericCellValue();
				 dr.findElement(By.xpath(".//*[@id='PRES_AMT']")).click();
				 WebElement ele81 =dr.findElement(By.xpath(".//*[@id='PRES_AMT']"));
				 ele81.sendKeys(Double.toString(Amount));
				 log811.info(ele81.getAttribute("value"));
				 dr.findElement(By.xpath("//*[@id='PRES_BK_FAX']")).click();
				 try {
					 Thread.sleep(1000);
						Alert alert = dr.switchTo().alert();		
						alert.accept();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				 
//Advice
				 
				 Logger log211111 = Logger.getLogger("Open");			
					dr.findElement(By.xpath(".//*[@id='D']")).click();
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
						Logger log1011 = Logger.getLogger("Type of Message");	
					dr.findElement(By.xpath("//*[@id='MESG_TYPE_BANK']")).sendKeys("Mail");
					log1011.info("Mail");
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
			        Thread.sleep(2000);
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
					Thread.sleep(2000);
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
									 Thread.sleep(2000);
									 
						//Screenshot			
									 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
									 try {
									   // now copy the  screenshot to desired location using copyFile method
									  
									 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\EPLCRegisterDoc.png"));
									 
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