package IPLCModule;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegisterAmendment extends SupervisorRelease1 {
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@Test(priority = 2)
	public void RegisterAmendmentLC() throws InterruptedException {
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
/*}
		 @Test
		 public static void Register() throws InterruptedException{*/
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
			dr.findElement(By.xpath(".//*[@name='G49082300296F05030702018']")).click();
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
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='AMD_DT']")).sendKeys("2018-06-20");
			dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[13]/td[2]/input")).click();
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[13]/td[2]/input")).sendKeys("1000");
			
			
			Thread.sleep(1000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("eeToolbar"); 
	Logger log16 = Logger.getLogger("End");
	 dr.findElement(By.xpath("//*[@id='_confirm']")).click();
	log16.info("Transaction Completed");
	 Thread.sleep(5000);
	 
//Screenshot					
	 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
	 try {
	   // now copy the  screenshot to desired location using copyFile method
	  
	 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\RegisterAmend.png"));
	 
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