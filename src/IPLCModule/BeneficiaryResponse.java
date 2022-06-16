package IPLCModule;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BeneficiaryResponse extends SupervisorRelease1 {
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@BeforeClass
	public void m1() throws InterruptedException {
		// TODO Auto-generated method stub
 //WebDriver dr = new FirefoxDriver();
 System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
 
	// Initialize browser
	 dr=new ChromeDriver();
	Logger log = Logger.getLogger("URL Open");
	// configure log4j properties file
     PropertyConfigurator.configure("Log4j.properties");
      Thread.sleep(1000);
		  dr.get("http://192.168.2.9:9082/EximBillWeb/");
		 log.info("Browser Opened");
		 dr.manage().window().maximize();
}
		 @Test
		 public static void Register() throws InterruptedException{
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
			Logger log4 = Logger.getLogger(" Open ");
			Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@name='Import Letter of Credit']")).click();
			log4.info("Import Letter of Credit");
			Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@name='IPLC Amendment']")).click();
			log4.info("IPLC Issuance");
			Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@name='G49082300296F05030702059']")).click();
			log4.info("Register Letter of Credit");
			Thread.sleep(5000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(1000);
			 dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input")).sendKeys(Keys.chord(Keys.CONTROL, "v"));
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
//Main		
			Thread.sleep(3000);
			dr.switchTo().defaultContent();
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(2000);
//Beneficiary Decision		
			
			String 	BeneficiaryDecision = "Accepted";
			if(BeneficiaryDecision == "Accepted"){
				dr.findElement(By.xpath("//*[@id='BENE_CONS_FLG']")).sendKeys("Accepted");
				
			}else{
				dr.findElement(By.xpath("//*[@id='BENE_CONS_FLG']")).sendKeys("Rejected");
			}
			Thread.sleep(1000);
//Advice Tab				
			dr.findElement(By.xpath("//*[@id='E']")).click();
			log4.info("Advice");
			dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
			log4.info("Bank");
//Bank			
			 Thread.sleep(1000);
				//dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("frame.AdivceForBankCust");
				Thread.sleep(3000);
				Logger log10 = Logger.getLogger("Type of Message");	
			dr.findElement(By.xpath("//*[@id='MESG_TYPE_BANK']")).sendKeys("Mail");
			log10.info("Mail");
			Logger log11 = Logger.getLogger("ID,Name,Address");
			dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[4]/td[2]/input[2]")).click();//CUBK Button
			log11.info("ABNADEHHCGN,ABN AMRO BANK (DEUTSCHLAND) AG,undefined");
//Alert Message				
			
			Alert alert = dr.switchTo().alert();		
    		
// Capturing alert message.    
	        String alertMessage= dr.switchTo().alert().getText();		
	        		
	        // Displaying alert message		
	        System.out.println(alertMessage);	
	        Thread.sleep(5000);
	        		
	        // Accepting alert		
	        alert.accept();		
	        Thread.sleep(5000);
	        Logger log12 = Logger.getLogger("Narrative (Mail)");
	        dr.findElement(By.xpath("//*[@id='BANK_NARR_MAIL']")).sendKeys("Test1");
	       log12.info("Test1");
	        
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
			Logger log13 = Logger.getLogger("Type of Message");
			dr.findElement(By.xpath("//*[@id='MESG_TYPE_CUST']")).sendKeys("Mail");
			log13.info("Mail");
			Logger log14 = Logger.getLogger("ID,Name,Address");
			dr.findElement(By.xpath("//*[@id='B_div']/table/tbody/tr[4]/td[2]/input[2]")).click();
			log14.info("009800722,SHANGHAI FEI LONG CO LTD,SHANG HAI,");
//Alert message close				
			Alert alert1 = dr.switchTo().alert();		
    		
			// Capturing alert message.    
					        String alertMessage1= dr.switchTo().alert().getText();		
					        		
					        // Displaying alert message		
					        System.out.println(alertMessage1);	
					        Thread.sleep(5000);
					        		
					        // Accepting alert		
					        alert1.accept();		
					        Thread.sleep(5000);
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
							
							dr.findElement(By.xpath("//*[@id='ext-gen286']")).click();
							dr.findElement(By.xpath("//*[@id='ext-gen294']")).click();
							//Confirm button					        
							 Thread.sleep(1000);
								dr.switchTo().defaultContent();
								  
								//switch to frame2
								dr.switchTo().frame("eeToolbar"); 
						Logger log15 = Logger.getLogger("End");
						 Thread.sleep(3000);
						 dr.findElement(By.xpath("//*[@id='_confirm']")).click();						 
						 log15.info("Transaction Completed");
						 Thread.sleep(3000);
//Screenshot					
						 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
						 try {
						   // now copy the  screenshot to desired location using copyFile method
						  
						 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\Beneficiary Response.png"));
						 
						        }
						  
						 catch (IOException e)
						  
						 {
						  
						 System.out.println(e.getMessage());
						  
						     }
//cancel			         
						 Thread.sleep(3000);
						 dr.findElement(By.xpath("//*[@id='_cancel']")).click();  
						 Thread.sleep(3000);

}
}