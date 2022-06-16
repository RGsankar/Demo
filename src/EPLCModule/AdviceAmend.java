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

public class AdviceAmend extends EPLCsupervisorRelease {
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
	@Test(priority = 3)
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
			 
			 XSSFSheet sheet = workbook.getSheet("AdviceAmend");
	
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
			dr.findElement(By.xpath(".//*[@name='G49082300309F05030701969']")).click();
			log6.info("Advice Amendment");
			Thread.sleep(2000);
			
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(1000);
			Logger log7 = Logger.getLogger("Reference Number");
			WebElement san = dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
			san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
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
			//Detrimental Flag				
			
			Logger log17 = Logger.getLogger("Detrimental Flag");
			 Row row2 = sheet.getRow(7);
			 Cell cell2 = row2.getCell(2);
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
			
//Send 
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='I']")).click();
			
			Logger log71 = Logger.getLogger("Send MT730?");
			 Row row21 = sheet.getRow(7);
			 Cell cell21 = row21.getCell(6);
			 String Send = cell21.getStringCellValue();
			//String Send  = "NO";
			switch (Send){
			case "YES" :
				Thread.sleep(1000);
				WebElement ele8 =dr.findElement(By.xpath(".//*[@id='SENT_MT730_FLG']"));
				ele8.sendKeys("YES");
				log71.info(ele8.getAttribute("value"));
				break;
			case "NO" :
				Thread.sleep(1000);
				WebElement ele81 =dr.findElement(By.xpath(".//*[@id='SENT_MT730_FLG']"));
				ele81.sendKeys("NO");
				log71.info(ele81.getAttribute("value"));
				break;
			}
//Charges						
			Logger log13 = Logger.getLogger("Charges");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='J']")).click();
			Row row211 = sheet.getRow(7);
			 Cell cell211 = row211.getCell(4);
			String Charges = cell211.getStringCellValue();
			//String Charges  = "TRANSACTION";
			switch (Charges){
			case "TRANSACTION" :
				Thread.sleep(1000);
				WebElement ele8 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
				ele8.sendKeys("TRANSACTION");
				log13.info(ele8.getAttribute("value"));
				Thread.sleep(1000);
				Logger log01 = Logger.getLogger("AC/NO	");
				WebElement ele01 =dr.findElement(By.xpath("//*[@id='CHG_FLD_LOCAL_CUST_AC_NO']"));
				ele01.sendKeys("54964654");
				log01.info("54964654");
				break;
			case "DEFERRED" :
				Thread.sleep(1000);
				WebElement ele81 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
				ele81.sendKeys("DEFERRED");
				log13.info(ele81.getAttribute("value"));
				break;
			case "WAIVED" :
				Thread.sleep(1000);
				WebElement ele1 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
				ele1.sendKeys("WAIVED");
				log13.info(ele1.getAttribute("value"));
				break;
				
			}
//Advice Tab :			
			Thread.sleep(2000);
			Logger log211111 = Logger.getLogger("Open");			
			dr.findElement(By.xpath(".//*[@id='K']")).click();
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
			Logger log1311 = Logger.getLogger("Type of Message");
			dr.findElement(By.xpath("//*[@id='MESG_TYPE_CUST']")).sendKeys("Mail");
			log1311.info("Mail");
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
							  
							 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\EPLCRegisterLC.png"));
							 
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