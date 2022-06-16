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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CheckDocument1 extends SupervisorRelease1 {
	public static WebDriver dr;
	public WebDriver GetDriver()
	{
		return dr;
	}
	@Test(priority = 5)
	public static void Check() throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
		 
		// Initialize browser
		dr=new ChromeDriver();
		 Logger log = Logger.getLogger("URL Open");
		 // configure log4j properties file
	      PropertyConfigurator.configure("Log4j.properties");
			 
			 dr.get("http://192.168.2.9:9082/EximBillWeb/");
			 dr.manage().window().maximize();
			 log.info("Chrome Browser");
/*	}
	 @Test
	 public static void Check () throws InterruptedException{*/
			 
		 Logger log0 = Logger.getLogger("Unit Code:	");
			 dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
			 log0.info("CSBANK");
			 Logger log1 = Logger.getLogger("User ID:");
			 dr.findElement(By.xpath(".//*[@id='ext-gen6']/form/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/div[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("CSBANKOP");
			 log1.info("CSBANKOP");
			 Logger log2 = Logger.getLogger("Password:");
			 dr.findElement(By.xpath(".//*[@id='tipLogPwd']")).sendKeys("1Q1Q1Q1Q");
			 log2.info("1Q1Q1Q1Q");
			 Logger log3 = Logger.getLogger("Click");
			 dr.findElement(By.xpath(".//*[@id='Image1']")).click();
			 log3.info("Submit");
			  Thread.sleep(1000);
				//Commented the code for finding the index of the element
			    dr.switchTo().frame(3); //Switching to the frame
				System.out.println("********We are switched to the iframe*******");
				Thread.sleep(1000);
				 Logger log4 = Logger.getLogger("IPLC Module");
				dr.findElement(By.xpath(".//*[@name='Import Letter of Credit']")).click();
				 log4.info("Import Letter of Credit");
				 Logger log5 = Logger.getLogger("Function");
				dr.findElement(By.xpath(".//*[@name='IPLC Presentation']")).click();
				log5.info("IPLC Presentation");
				Thread.sleep(1000);
				 Logger log6 = Logger.getLogger("Function Group");
				dr.findElement(By.xpath(".//*[@name='G49082300273F05030701982']")).click();
				log6.info("Check Document");
				Thread.sleep(3000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("work");
				Thread.sleep(1000);
				Logger log7 = Logger.getLogger("Reference Number");
				WebElement san = dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
				san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
				log7.info(san.getAttribute("value"));	
				 
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
		 Thread.sleep(1000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(2000);
			Logger log8 = Logger.getLogger("Document Status");
			String 	DocumentStatus = "Compliant";
			
			switch(DocumentStatus){
			
			case "Compliant": 
				
			dr.findElement(By.xpath("//*[@id='DOC_STAT']")).sendKeys("Compliant");
			log8.info("Compliant");
			break;
			
			case "Discrepancy Found": 
			dr.findElement(By.xpath("//*[@id='DOC_STAT']")).sendKeys("Discrepancy Found");
			log8.info("Discrepancy Found");
			break;
			
			case "Under Shipping Guarantee": 
			dr.findElement(By.xpath("//*[@id='DOC_STAT']")).sendKeys("Under Shipping Guarantee");
			log8.info("Under Shipping Guarantee");
			break;
			
			default:
			dr.findElement(By.xpath("//*[@id='DOC_STAT']")).sendKeys("Compliant");
			log8.info("Compliant");
			break;
			}
//Document
			dr.findElement(By.xpath("//*[@id='C']")).click();
			Thread.sleep(500);
			Logger log9 = Logger.getLogger("Documents Original");
			dr.findElement(By.xpath("//*[@id='INVOICE_1']")).sendKeys("4");
			log9.info("4");
			Thread.sleep(500);
			Logger log10 = Logger.getLogger("Documents Copies");
			dr.findElement(By.xpath("//*[@id='INVOICE_2']")).sendKeys("3");
			log10.info("3");
			dr.findElement(By.xpath("//*[@id='C_div']/table[1]/tbody/tr/td")).click(); // Outside Click
			Thread.sleep(1000);
			
//Confirm				
			 Thread.sleep(1000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("eeToolbar"); 
			
				 Thread.sleep(2000);
				 Logger log11 = Logger.getLogger("Confirm");
				dr.findElement(By.xpath("//*[@id='_confirm']")).click();
				log11.info("Transaction Completed");
				 //Screenshot					
				 File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
				 try {
				   // now copy the  screenshot to desired location using copyFile method
				  
				 FileUtils.copyFile(src, new File("E:\\Testing\\Baseline\\ScreenShot\\CheckDocument.png"));
				 
				        }
				  
				 catch (IOException e)
				  
				 {
				  
				 System.out.println(e.getMessage());
				  
				     }							        							      	
		       
//cancel			         
		        Thread.sleep(5000);   
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("eeToolbar"); 
			//dr.findElement(By.xpath("//*[@id='_vchview']")).click();
				 Thread.sleep(1000);
				 dr.findElement(By.xpath("//*[@id='_cancel']")).click(); 			

}
}