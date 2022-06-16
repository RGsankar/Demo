package IPLCModule;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

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

public class RegisterDocumentLC extends SupervisorRelease1 {
	public static WebDriver dr;
	public WebDriver GetDriver()  
	{
		return dr;
	}
	@Test(priority = 4)
	public void RegisterDocument() throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
		 
		// Initialize browser
		 dr=new ChromeDriver();
		 Logger log = Logger.getLogger("URL Open");
		 // configure log4j properties file
	      PropertyConfigurator.configure("Log4j.properties");
			 
			 dr.get("http://192.168.2.9:9082/EximBillWeb/");
			 log.info("Chrome Browser");
			 dr.manage().window().maximize();
	/*}
	 @Test
	 public static void Register() throws InterruptedException{*/
			 
			 dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
			 dr.findElement(By.xpath(".//*[@id='ext-gen6']/form/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/div[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("CSBANKOP");
			 dr.findElement(By.xpath(".//*[@id='tipLogPwd']")).sendKeys("1Q1Q1Q1Q");	
			 dr.findElement(By.xpath(".//*[@id='Image1']")).click();
			  Thread.sleep(1000);
				//Commented the code for finding the index of the element
			    dr.switchTo().frame(3); //Switching to the frame
				System.out.println("********We are switched to the iframe*******");
				Thread.sleep(1000);
				Logger log0 = Logger.getLogger("IPLC Module");
				dr.findElement(By.xpath(".//*[@name='Import Letter of Credit']")).click();
				log0.info("Import Letter Of Credit");
				Logger log1 = Logger.getLogger("Function");
				dr.findElement(By.xpath(".//*[@name='IPLC Presentation']")).click();
				log1.info("IPLC CSU Function");
				Logger log2 = Logger.getLogger("Function Group");
				dr.findElement(By.xpath(".//*[@name='G49082300273F05030701981']")).click();
				log2.info("Register Document");
				
				Thread.sleep(2000);
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
			Logger log3 = Logger.getLogger("Open");
			dr.findElement(By.xpath("//*[@id='PRES_AMT']")).click();
			log3.info("Main Tab");
			Logger log4 = Logger.getLogger("Presentation Amount");
			dr.findElement(By.xpath("//*[@id='PRES_AMT']")).sendKeys("1000");
			log4.info("1000");
			dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[13]/td[2]")).click(); //Outside Click
			
			 Logger log5 = Logger.getLogger("Presenter Reference");
				dr.findElement(By.xpath("//*[@id='PRES_BK_REF']")).sendKeys("45646464");
				log5.info("45646464");
				Logger log6 = Logger.getLogger("Open Tab");
				dr.findElement(By.xpath("//*[@id='B']")).click();
				log6.info("Parties");
				//Parties - Presenting Bank
				Logger log8 = Logger.getLogger("Presenting Bank,ID,Name");
				dr.findElement(By.xpath("//*[@id='PRES_BK_ID_BTN']")).click();
				log8.info("BK000049,PTSABMAB,ADD1,ADD2,ADD3");
				Thread.sleep(1000);
				
				//pop Window				
				Set<String> windowId = dr.getWindowHandles();    // get  window id of current window
		        Iterator<String> itererator = windowId.iterator();   

		        String mainWinID = itererator.next();
		        String  newAdwinID = itererator.next();

		        dr.switchTo().window(newAdwinID);
		        System.out.println(dr.getTitle());
		       Thread.sleep(2000);
				dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
				
				Thread.sleep(1000);
		       // dr.close();
		        dr.switchTo().window(mainWinID);
		      
		        System.out.println(dr.getTitle());
		        Thread.sleep(1000);
		        
		        Thread.sleep(1000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("work");
//Document
				Logger log9 = Logger.getLogger("Open Tab");
				dr.findElement(By.xpath("//*[@id='C']")).click();
				log9.info("Document");
				Thread.sleep(500);
				Logger log10 = Logger.getLogger("Documents Original(s)");
				dr.findElement(By.xpath("//*[@id='DRAFT_1']")).sendKeys("4");
				log10.info("4");
				Thread.sleep(500);
				Logger log11 = Logger.getLogger("Copies");
				dr.findElement(By.xpath("//*[@id='DRAFT_2']")).sendKeys("4");
				log11.info("4");
//Confirm				
				 Thread.sleep(1000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("eeToolbar"); 
				//dr.findElement(By.xpath("//*[@id='_vchview']")).click();
					 Thread.sleep(3000);
					 Logger log12 = Logger.getLogger("Confirm");
					dr.findElement(By.xpath("//*[@id='_confirm']")).click();
					log12.info("Trnsaction Completed");
					 Thread.sleep(1000);
				

 //Screenshot													      
								        Thread.sleep(5000);
										 File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
										 try {
										   // now copy the  screenshot to desired location using copyFile method
										  
										 FileUtils.copyFile(src, new File("E:\\Testing\\Baseline\\ScreenShot\\RegDocument.png"));
										 
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
