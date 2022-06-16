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

public class IssueLetterofCredit extends SupervisorRelease1  {
	public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
	@Test(priority = 1)
		public void vijay() throws InterruptedException {
			// TODO Auto-generated method stub
		// dr = GetDriver() ;
			System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
			 
			// Initialize browser
			dr=new ChromeDriver();
			Logger log = Logger.getLogger("URL Open");
			 // configure log4j properties file
		      PropertyConfigurator.configure("Log4j.properties");
				 dr.get("http://192.168.2.9:9082/EximBillWeb/");
				 log.info("Chrome Browser");
				 dr.manage().window().maximize();
	
		 Logger log0 = Logger.getLogger("Business Unit Code");
		 dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
		 log0.info("01");
		 Logger log1 = Logger.getLogger("User ID");
		 dr.findElement(By.xpath(".//*[@id='ext-gen6']/form/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/div[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("CSBANKOP");
		 log1.info("USER_OP1");
		 Logger log2 = Logger.getLogger("Password");
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
		 //dr = GetDriver1();
		Thread.sleep(1000);
					dr.switchTo().defaultContent();
					Thread.sleep(2000);
					
				    dr.switchTo().frame("FunctionList"); //Switching to the frame
					
					Thread.sleep(2000);
					Logger log5 = Logger.getLogger("Function");
					dr.findElement(By.xpath(".//*[@name='IPLC Issuance']")).click();
					log5.info("IPLC Issuance");
					Logger log6 = Logger.getLogger("Function Group");
					dr.findElement(By.xpath(".//*[@name='G49082300272F05030702015']")).click();
					log6.info("Issue Letter of Credit");
					Thread.sleep(3000);
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
//Main		
			 Thread.sleep(1000);
				dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("work");
				Thread.sleep(3000);
				dr.findElement(By.xpath(".//*[@id='B']")).click();
				Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='D']")).click();
					Thread.sleep(2000);
					Logger log8 = Logger.getLogger("Available By");
					String AvailableBy = "BY PAYMENT";
					switch(AvailableBy){
					case "BY PAYMENT":
						dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY PAYMENT");
						log8.info("BY PAYMENT");
						break;
					case "BY ACCEPTANCE":
						dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY ACCEPTANCE");
						log8.info("BY ACCEPTANCE");
						Thread.sleep(1000);
						Logger log9 = Logger.getLogger("Drafts at [ 42C ]");
						dr.findElement(By.xpath("//*[@id='DRAFTS_AT']")).sendKeys("test");
						log9.info("test");
						Thread.sleep(1000);
						Logger log12 = Logger.getLogger("Drawee ID[42A]	");
						dr.findElement(By.xpath("//*[@id='DRWE_ID']")).sendKeys("ABNADEHHCGN");//*[@id="DRWE_ID"]
						log12.info("ABNADEHHCGN,ABN AMRO BANK (DEUTSCHLAND) AG,undefined");
						Thread.sleep(1000);
						dr.findElement(By.xpath(".//*[@id='DRAFTS_AT']")).click();
						break;	
					case "BY NEGOTIATION":
						dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY NEGOTIATION");
						log8.info("BY NEGOTIATION");
						
						break;	
					case "BY DEF PAYMENT":
						dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY DEF PAYMENT");
						log8.info("BY DEF PAYMENT");
						Thread.sleep(1000);
						Logger log10 = Logger.getLogger("Deferred Payment Details [42P]");
						dr.findElement(By.xpath("//*[@id='DEF_PMT_DET']")).sendKeys("test");
						log10.info("test");
						break;	
					case "BY MIXED PYMT":
						dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY MIXED PYMT");
						log8.info("BY MIXED PYMT");
						Thread.sleep(1000);
						Logger log11 = Logger.getLogger("Mix Payment Details (Tag42M)");
						dr.findElement(By.xpath("//*[@id='MIX_PMT_DETL']")).sendKeys("test");
						log11.info("test");
						break;	
					}
					Thread.sleep(1000);
					try {
						dr.findElement(By.xpath("//*[@id='K']")).click();
						Thread.sleep(1000);
						dr.findElement(By.xpath("//*[@id='PaymentTerms_ADD']")).click();
						Logger log13 = Logger.getLogger("Sight/Def/Acc Flag");
						String Flag = "Acceptance";
						switch (Flag){
						case "Sight" :
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Sight");
							log13.info("Sight");
							break;
						case "Deferred" :
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Deferred");
							log13.info("Deferred");
							Thread.sleep(1000);
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("10");
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[5]/td[4]/select")).sendKeys("DAYS AFTER SIGHT");
							break;
						case "Acceptance" :
							Thread.sleep(1000);
							dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Acceptance");
							log13.info("Acceptance");
							Thread.sleep(1000);
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
	
				Thread.sleep(1000);
		
//Advice Tab		
					Logger log21 = Logger.getLogger("Open");			
					dr.findElement(By.xpath("//*[@id='J']")).click();
					log21.info("Advice Tab");
					Logger log20 = Logger.getLogger("Open");	
					dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
					log20.info("Bank ");
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
			        Thread.sleep(3000);
			        		
			        // Accepting alert		
			        alert.accept();		
			        Thread.sleep(3000);
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
//Voucher								
									Thread.sleep(1000);
									dr.switchTo().defaultContent();
									  
									//switch to frame2
									dr.switchTo().frame("eeToolbar"); 
									Thread.sleep(1000);
									
									dr.findElement(By.xpath("//*[@id='_vchview']")).click();
									
									Alert alert2 = dr.switchTo().alert();	
									 alert2.accept();		
									Thread.sleep(3000);
									
	//Screenshot					
									
									 Set<String> windowId11 = dr.getWindowHandles();    // get  window id of current window
								        Iterator<String> itererator11 = windowId11.iterator();   

								        String mainWinID11 = itererator11.next();
								        String  newAdwinID11 = itererator11.next();

								        dr.switchTo().window(newAdwinID11);
								        System.out.println(dr.getTitle());
								        Thread.sleep(2000);
								        dr.manage().window().maximize();
								        Thread.sleep(3000);
								        
								        File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
										 try {
										   // now copy the  screenshot to desired location using copyFile method
										  
										 FileUtils.copyFile(src, new File("E:\\Testing\\Baseline\\ScreenShot\\IssueVoucher.png"));
										 
										        }
										  
										 catch (IOException e)
										  
										 {
										  
										 System.out.println(e.getMessage());
										  
										     }	
										dr.findElement(By.xpath("//*[@id='_Cancel']")).click();
										
								        //dr.close();
								        dr.switchTo().window(mainWinID11);
								        System.out.println(dr.getTitle());
		
	//Confirm button					        
									 Thread.sleep(1000);
										dr.switchTo().defaultContent();
										  
										//switch to frame2
										dr.switchTo().frame("eeToolbar"); 
								Logger log15 = Logger.getLogger("End");
								 Thread.sleep(3000);
								 dr.findElement(By.xpath("//*[@id='_confirm']")).click();
								 Alert alert3 = dr.switchTo().alert();	
								 alert3.accept();
								 log15.info("Transaction Completed");
								 Thread.sleep(5000);
								 
	//Screenshot					
								 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
								 try {
								   // now copy the  screenshot to desired location using copyFile method
								  
								 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\Issue.png"));
								 
								        }
								  
								 catch (IOException e)
								  
								 {
								  
								 System.out.println(e.getMessage());
								  
								     }
	//cancel			         
								 Thread.sleep(3000);
								 dr.findElement(By.xpath("//*[@id='_cancel']")).click();  
								 Thread.sleep(1000);
}
}
