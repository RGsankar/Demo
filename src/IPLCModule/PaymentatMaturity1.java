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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PaymentatMaturity1 extends SupervisorRelease1 {
	public static WebDriver dr;
	public WebDriver GetDriver()
	{
		return dr;
	}
	@Test(priority = 7)
	public void Accept() throws InterruptedException {
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
	/*}			
	 @Test
	 public static void Accept() throws InterruptedException{ */
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
				
				dr.findElement(By.xpath(".//*[@name='IPLC Settlement']")).click();
				dr.findElement(By.xpath(".//*[@name='G49082300323F05030701994']")).click();
				
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
//Transactions List			 
			 try {
				Thread.sleep(1000);
				 
				 dr.switchTo().defaultContent();
				  
					//switch to frame2
					dr.switchTo().frame("work");

					 Thread.sleep(1000);
				 dr.findElement(By.xpath("//*[@id='CataListTab']/tbody/tr[4]/td[2]/input")).click();
				 Thread.sleep(1000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("eeToolbar"); 
					 dr.findElement(By.xpath("//*[@id='_next']")).click();
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			 try {
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
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
//Main		
		 Thread.sleep(1000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(3000);
			dr.findElement(By.xpath("//*[@id='G']")).click();
			 Thread.sleep(1000);
			  dr.findElement(By.xpath(".//*[@id='CHG_FLD_ALL_CHARGE_AT']")).sendKeys("DEFERRED");
			  Thread.sleep(1000);
			  dr.findElement(By.xpath("//*[@id='D']")).click();
			  
//Payment Debit1			
				dr.findElement(By.xpath("//*[@id='do_PaymentDebitHeader_Tab']")).click();
				Thread.sleep(3000);
				dr.findElement(By.xpath("//*[@id='PaymentDebit_ADD']")).click();
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[8]/td[2]/input")).sendKeys("2018-05-30");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[2]/td[4]/select")).sendKeys("VOSTRO");
				try {
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='CPYT_DR_ID']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='CPYT_DR_ID']")).clear();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[3]/td[4]/input[2]")).click();
	//Pop window	
				 Set<String> windowId = dr.getWindowHandles();    // get  window id of current window
			        Iterator<String> itererator = windowId.iterator();   

			        String mainWinID = itererator.next();
			        String  newAdwinID = itererator.next();

			        dr.switchTo().window(newAdwinID);
			        System.out.println(dr.getTitle());
			       
					dr.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
					
					Thread.sleep(1000);
			       // dr.close();
			        dr.switchTo().window(mainWinID);
			      
			        System.out.println(dr.getTitle());
			        Thread.sleep(1000);
	
	//frame work		        
			        Thread.sleep(1000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("work");
					Thread.sleep(5000);			
					dr.findElement(By.xpath("//*[@id='PaymentDebit_SAVE']")).click();
					Thread.sleep(1000);
			       
	//Payment Credit
			       
			        Thread.sleep(5000);
			        dr.findElement(By.xpath(".//*[@id='do_PaymentCreditHeader_Tab']")).click();
					Thread.sleep(3000);
					dr.findElement(By.xpath("//*[@id='PaymentCredit_ADD']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='do_PaymentCredit_M']/table/tbody/tr[7]/td[2]/span/input")).sendKeys("2018-05-30");
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='CPYT_CR_AC_TYPE']")).sendKeys("VOSTRO");
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='do_PaymentCredit_M']/table/tbody/tr[3]/td[4]/input[2]")).click();
		//Pop window	
					 Set<String> windowId6 = dr.getWindowHandles();    // get  window id of current window
				        Iterator<String> itererator6 = windowId6.iterator();   

				        String mainWinID6 = itererator6.next();
				        String  newAdwinID6 = itererator6.next();

				        dr.switchTo().window(newAdwinID6);
				        System.out.println(dr.getTitle());
				       
						dr.findElement(By.xpath("//*[@id='3']/td[2]/a")).click();
						
						Thread.sleep(1000);
				       // dr.close();
				        dr.switchTo().window(mainWinID6);
				      
				        System.out.println(dr.getTitle());
				        Thread.sleep(1000);
		
		//frame work		        
				        Thread.sleep(1000);
						dr.switchTo().defaultContent();
						  
						//switch to frame2
						dr.switchTo().frame("work");
						Thread.sleep(5000);			
						dr.findElement(By.xpath("//*[@id='PaymentCredit_SAVE']")).click(); // Save button 
						Thread.sleep(1000);
				       
						//Confirm				
						 Thread.sleep(3000);
							dr.switchTo().defaultContent();
							  
							//switch to frame2
							dr.switchTo().frame("eeToolbar"); 
						
							 Thread.sleep(1000);
							
							try {
								Alert alert2 = dr.switchTo().alert();
								alert2.accept();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							 Thread.sleep(3000);
							dr.findElement(By.xpath("//*[@id='_confirm']")).click();
							 Thread.sleep(5000);
//Screenshot					
							 File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
							 try {
							   // now copy the  screenshot to desired location using copyFile method
							  
							 FileUtils.copyFile(src, new File("E:\\Testing\\Baseline\\ScreenShot\\PaymentMaturity.png"));
							 
							        }
							  
							 catch (IOException e)
							  
							 {
							  
							 System.out.println(e.getMessage());
							  
							     }	
//cancel			         
								dr.switchTo().defaultContent();
								  
								//switch to frame2
								dr.switchTo().frame("eeToolbar"); 
								 Thread.sleep(3000);
								 dr.findElement(By.xpath("//*[@id='_cancel']")).click(); 						
	
}
	
}