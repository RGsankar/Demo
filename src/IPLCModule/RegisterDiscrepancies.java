package IPLCModule;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterDiscrepancies{
	public static WebDriver dr;
	
	@BeforeTest
	public static void arun() throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
		 
		// Initialize browser
		dr=new ChromeDriver();
			 
			 dr.get("http://192.168.2.9:9082/EximBillWeb/");
			 dr.manage().window().maximize();
	}
	 @Test
	 public static void Check () throws InterruptedException{
			 
			 dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
			 dr.findElement(By.xpath(".//*[@id='ext-gen6']/form/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/div[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("CSBANKOP");
			 dr.findElement(By.xpath(".//*[@id='tipLogPwd']")).sendKeys("1Q1Q1Q1Q");	
			 dr.findElement(By.xpath(".//*[@id='Image1']")).click();
			  Thread.sleep(1000);
				//Commented the code for finding the index of the element
			    dr.switchTo().frame(3); //Switching to the frame
				System.out.println("********We are switched to the iframe*******");
				Thread.sleep(1000);
				dr.findElement(By.xpath(".//*[@name='Import Letter of Credit']")).click();
				
				dr.findElement(By.xpath(".//*[@name='IPLC Discrepancies']")).click();
				Thread.sleep(1000);
				dr.findElement(By.xpath(".//*[@name='G49082300322F05030701990']")).click();
				
				Thread.sleep(5000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("work");
				Thread.sleep(1000);
	
				dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input")).sendKeys(Keys.chord(Keys.CONTROL, "v"));
				 
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
			Thread.sleep(3000);
			dr.findElement(By.xpath("//*[@id='DOC_STAT']")).sendKeys("Compliant");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='B']")).click();
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='I']")).click();
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='C']")).click();
			Thread.sleep(1000);
//Advise Applicant
			dr.findElement(By.xpath("//*[@id='ADV_APPL_FLG']")).sendKeys("Yes");
			//dr.findElement(By.xpath("//*[@id='ADV_APPL_FLG']")).sendKeys("No");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='REL_IN_TRUST_FLG']")).sendKeys("Yes");
			
			//Confirm				
			 Thread.sleep(1000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("eeToolbar"); 
			
				 Thread.sleep(3000);
				dr.findElement(By.xpath("//*[@id='_confirm']")).click();
				 //Screenshot					
				 File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
				 try {
				   // now copy the  screenshot to desired location using copyFile method
				  
				 FileUtils.copyFile(src, new File("E:\\Testing\\Baseline\\ScreenShot\\RegisterDiscrepancy.png"));
				 
				        }
				  
				 catch (IOException e)
				  
				 {
				  
				 System.out.println(e.getMessage());
				  
				     }							        							      	
		       
//cancel			         
		        Thread.sleep(3000);   
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("eeToolbar"); 
			//dr.findElement(By.xpath("//*[@id='_vchview']")).click();
				 Thread.sleep(3000);
				 dr.findElement(By.xpath("//*[@id='_cancel']")).click(); 	
//Refuse Document				 
				 try {
					 Thread.sleep(2000);
					 dr.switchTo().defaultContent();
						//Commented the code for finding the index of the element
					    dr.switchTo().frame(3);
					Thread.sleep(1000);
					// dr.findElement(By.xpath(".//*[@name='IPLC Discrepancies']")).click();
						Thread.sleep(1000);
						dr.findElement(By.xpath(".//*[@name='G49082300322F05030701996']")).click();
						
						Thread.sleep(2000);
						dr.switchTo().defaultContent();
						  
						//switch to frame2
						dr.switchTo().frame("work");
						Thread.sleep(1000);

						dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input")).sendKeys(Keys.chord(Keys.CONTROL, "v"));
						 
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
					Thread.sleep(3000);
					dr.findElement(By.xpath("//*[@id='REFUSE_DT']")).sendKeys("2018-05-25");
					 Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='MESG_TYPE']")).sendKeys("MT734");
					
					dr.findElement(By.xpath("//*[@id='DOC_DISP_FLG']")).sendKeys("NOTIFY");
					dr.findElement(By.xpath("//*[@id='ADV_DIS_DT']")).sendKeys("2018-05-25");
					dr.findElement(By.xpath("//*[@id='DISC_DET']")).sendKeys("test123");
					
//Confirm				
					 Thread.sleep(1000);
						dr.switchTo().defaultContent();
						  
						//switch to frame2
						dr.switchTo().frame("eeToolbar"); 
					
						 Thread.sleep(3000);
						dr.findElement(By.xpath("//*[@id='_confirm']")).click();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 Thread.sleep(1000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("eeToolbar"); 
				
					 Thread.sleep(3000);
					//dr.findElement(By.xpath("//*[@id='_confirm']")).click();
				 
				 dr.findElement(By.xpath("//*[@id='_cancel']")).click(); 	
	 }
// Discrepancy Response MT752
	 @AfterTest
				 public static void mani() throws InterruptedException {				 
				 Thread.sleep(3000);
				 dr.switchTo().defaultContent();
				 Thread.sleep(1000);
					//Commented the code for finding the index of the element
				    dr.switchTo().frame(3);
				// dr.findElement(By.xpath(".//*[@name='IPLC Discrepancies']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath(".//*[@name='G49082300322F05030701991']")).click();
					
					Thread.sleep(5000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("work");
					Thread.sleep(1000);

					dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input")).sendKeys(Keys.chord(Keys.CONTROL, "v"));
					 
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
				Thread.sleep(3000);
				
				dr.findElement(By.xpath("//*[@id='APPL_RESP_DISC']")).sendKeys("Discrepancies Accepted");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='B']")).click();
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='C']")).click();
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='DISC_DET']")).sendKeys("test1");
				Thread.sleep(1000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("eeToolbar"); 
			
				 Thread.sleep(3000);
				dr.findElement(By.xpath("//*[@id='_confirm']")).click();
				Thread.sleep(1000);
				 dr.findElement(By.xpath("//*[@id='_cancel']")).click(); 
				 
}
}