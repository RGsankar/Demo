package IPLCModule;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class RegisterLC extends SupervisorRelease1 
{

	public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@Test(priority = 0)
	public void m1() throws InterruptedException {
		// TODO Auto-generated method stub
 //WebDriver dr = new FirefoxDriver();
 System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
 
	// Initialize browser
	 dr=new ChromeDriver();
	Logger log = Logger.getLogger("URL Open");
	//configure log4j properties file
     PropertyConfigurator.configure("Log4j.properties");
      Thread.sleep(1000);
		  dr.get("http://192.168.2.9:9082/EximBillWeb/");
		 log.info("Browser Chrome");
		 dr.manage().window().maximize();

		 
		// public static void Register() throws InterruptedException{
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
			Logger log4 = Logger.getLogger("IPLC Module");
			Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@name='Import Letter of Credit']")).click();
			log4.info("Import Letter of Credit");
			Thread.sleep(1000);
			Logger log5 = Logger.getLogger("Function");
			dr.findElement(By.xpath(".//*[@name='IPLC Issuance']")).click();
			log5.info("IPLC Issuance");
			Thread.sleep(1000);
			Logger log6 = Logger.getLogger("Function Group");
			dr.findElement(By.xpath(".//*[@name='G49082300272F05030702010']")).click();
			log6.info("Register Letter of Credit");
			Thread.sleep(5000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
//Main			
			
//Available By
			
			String expPageTitle1 = "By Payment";
			switch(expPageTitle1){
			
			case "By Payment":
				//dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("By Payment");
				Logger log7 = Logger.getLogger("Available By");
				Select oSelect = new Select(dr.findElement(By.xpath("//*[@id='AVAL_BY']")));
				oSelect.selectByVisibleText("By Payment");
				log7.info("By Payment");
				Thread.sleep(1000);
				break;
			case "BY ACCEPTANCE":
				Logger log8 = Logger.getLogger("Available By");
				dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY ACCEPTANCE"); 
				log8.info("BY ACCEPTANCE");
				dr.findElement(By.xpath("//*[@id='TENOR_DAYS']")).clear();
				dr.findElement(By.xpath("//*[@id='TENOR_DAYS']")).sendKeys("10");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='TENOR_TYPE']")).sendKeys("DAYS AFTER SIGHT");
				break;
			case "By Negotiation":
				Logger log61 = Logger.getLogger("Available By");
				dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("By Negotiation"); 
				log61.info("By Negotiation");
				dr.findElement(By.xpath("//*[@id='TENOR_DAYS']")).clear();
				dr.findElement(By.xpath("//*[@id='TENOR_DAYS']")).sendKeys("10");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='TENOR_TYPE']")).sendKeys("DAYS AFTER SIGHT");
				break;
			case "BY DEF PAYMENT":
				Logger log62 = Logger.getLogger("Available By");
				dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY DEF PAYMENT"); 
				log62.info("By DEF Payment");
				dr.findElement(By.xpath("//*[@id='TENOR_DAYS']")).clear();
				dr.findElement(By.xpath("//*[@id='TENOR_DAYS']")).sendKeys("10");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='TENOR_TYPE']")).sendKeys("DAYS AFTER SIGHT");
				break;
			case "BY Mixed Payment":
				Logger log51 = Logger.getLogger("Available By");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='AVAL_BY']")).sendKeys("BY Mixed Payment"); 
				log51.info("BY Mixed Payment");
				Thread.sleep(1000);
				break;
			}
			
//Financial Information		
//LC CCY and Amt[32B]
			Thread.sleep(2000);
			dr.findElement(By.xpath("//*[@id='LC_AMT']")).click();
			Logger log9 = Logger.getLogger("LC_AMT");
			WebElement san= dr.findElement(By.xpath("//*[@id='LC_AMT']"));
			san.sendKeys("1000");
			log9.info(san.getAttribute("value"));
			Thread.sleep(1000);
			
			String LCAMT = "USD";
			switch (LCAMT){
			case "USD":
				Thread.sleep(1000);
				Logger log10 = Logger.getLogger("LC_CCY");
				dr.findElement(By.xpath("//*[@id='LC_CCY']")).sendKeys("USD");
				log10.info("USD");
				break;
			case "AED":
				Thread.sleep(1000);
				Logger log11 = Logger.getLogger("LC_CCY");
				dr.findElement(By.xpath("//*[@id='LC_CCY']")).sendKeys("AED");
				log11.info("AED");
				break;
			case "EUR":
				Thread.sleep(1000);
				Logger log12 = Logger.getLogger("LC_CCY");
				dr.findElement(By.xpath("//*[@id='LC_CCY']")).sendKeys("EUR");
				log12.info("EUR");
				break;
			case "GBP":
				Thread.sleep(1000);
				Logger log13 = Logger.getLogger("LC_CCY");
				dr.findElement(By.xpath("//*[@id='LC_CCY']")).sendKeys("GBP");
				log13.info("GBP");
				break;
			}
			
			
//Date of Expiry[31D]
			Thread.sleep(1000);
			Logger log14 = Logger.getLogger("Date of Expiry[31D]");
			dr.findElement(By.xpath("//*[@id='EXPIRY_DT']")).sendKeys("2018-07-25");
			log14.info("2018-07-25");
			
//	Form of LC[40A]	
			Thread.sleep(1000);
			Logger log15 = Logger.getLogger("Form of LC[40A]");
			String FormOfLC = "IRREVOCABLE";
			switch(FormOfLC){
			case "IRREVOCABLE":
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")).sendKeys("IRREVOCABLE");
				log15.info("IRREVOCABLE");
				break;
			case "IRREVOCABLE TRANSFERABLE":
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")).sendKeys("IRREVOCABLE TRANSFERABLE");
				log15.info("IRREVOCABLE TRANSFERABLE");
				break;
			case "IRREVOCABLE STANDBY":
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")).sendKeys("IRREVOCABLE STANDBY");
				log15.info("IRREVOCABLE STANDBY");
				break;
			case "IRREVOC TRANS STANDBY":
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")).sendKeys("IRREVOC TRANS STANDBY");
				log15.info("IRREVOC TRANS STANDBY");
				break;
			}
			
//Parties 		
			Logger log111 = Logger.getLogger("Open");
			WebElement ss = dr.findElement(By.xpath(".//*[@id='C']"));
			//ss.getAttribute("value");
			ss.click();
			log111.info("parties");
			Thread.sleep(1000);
			//dr.findElement(By.xpath(".//*[@id='APPL_ID']")).sendKeys("");
			Logger log16 = Logger.getLogger("Applicant");
			dr.findElement(By.xpath(".//*[@id='C_div']/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input[2]")).click();
			log16.info("BUYER,BUYER,Oxford St,Oxford St");
//Alert Message				
			
			Alert alert = dr.switchTo().alert();		
        
// Accepting alert		
	        alert.accept();		
	        Thread.sleep(3000);
	        
			 Set<String> windowId1 = dr.getWindowHandles(); 
			 // get  window id of current window
		        Iterator<String> itererator1 = windowId1.iterator();   

		        String mainWinID1 = itererator1.next();
		        String  newAdwinID1 = itererator1.next();

		        dr.switchTo().window(newAdwinID1);
		        System.out.println(dr.getTitle());
		        dr.findElement(By.xpath("html/body/form[3]/table/tbody/tr/td[4]/input")).sendKeys("BUYER");
		        Thread.sleep(1000);
		        dr.findElement(By.xpath("html/body/form[3]/table/tbody/tr/td[8]/a/b")).click();
		        Thread.sleep(1000);
				dr.findElement(By.xpath(".//*[@id='1']/td[2]/a")).click();//*[@id='1']/td[2]/a
				 Thread.sleep(1000);
		        //dr.close();
		        dr.switchTo().window(mainWinID1);
		        System.out.println(dr.getTitle());
		        Thread.sleep(2000);
		        dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("work");
				Logger log11 = Logger.getLogger("Account Office Code ");
		       dr.findElement(By.xpath(".//*[@id='AC_OFFICER_CODE']")).sendKeys("46465466");
		       log11.info("46465466");
		       Logger log12 = Logger.getLogger("Beneficiary");
		        dr.findElement(By.xpath(".//*[@id='BENE_ID']")).sendKeys("009800722");
		        log12.info("009800722,SHANGHAI FEI LONG CO LTD,SHANG HAI,HUANG HE  ROAD");
		        Thread.sleep(1000);
		        dr.findElement(By.xpath(".//*[@id='SAME_AS_APPL_FLG']")).sendKeys("Yes");
//Liability		        
		        dr.findElement(By.xpath(".//*[@id='B']")).click();
		        Logger log13 = Logger.getLogger("Bank Liability Account");
		        dr.findElement(By.xpath(".//*[@id='B_div']/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input[2]")).click();
		        log13.info("BANK12345");
		        Thread.sleep(1000);
		        
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
		        dr.switchTo().defaultContent();
				  
				//switch to frame2
				dr.switchTo().frame("work");
				Logger log17 = Logger.getLogger("Customer Liability Account ");
				dr.findElement(By.xpath(".//*[@id='B_div']/table/tbody/tr[1]/td/table/tbody/tr[2]/td[4]/input[2]")).click();
				log17.info("CUST54321");
		        Set<String> windowId2 = dr.getWindowHandles();    // get  window id of current window
		        Iterator<String> itererator2 = windowId2.iterator();   

		        String mainWinID2 = itererator2.next();
		        String  newAdwinID2 = itererator2.next();

		        dr.switchTo().window(newAdwinID2);
		        System.out.println(dr.getTitle());
		        Thread.sleep(1000);
		       
		        WebElement san1 = dr.findElement(By.xpath("//*[@id='0']/td[2]/a"));
		        san1.click();
		       
		        dr.switchTo().window(mainWinID2);
		        System.out.println(dr.getTitle());
		        Thread.sleep(1000);
//Charges
		        dr.switchTo().defaultContent();

				//switch to frame2
				dr.switchTo().frame("work");
				
		        dr.findElement(By.xpath(".//*[@id='F']")).click();
		        log4.info("Charges");
		        Thread.sleep(1000);
		        Logger log18 = Logger.getLogger("Paid At");
		        dr.findElement(By.xpath(".//*[@id='CHG_FLD_ALL_CHARGE_AT']")).sendKeys("DEFERRED");
		        log18.info("DEFERRED");
//Copy and past
				 WebElement locOfOrder = dr.findElement(By.id("C_MAIN_REF"));
				 Actions act = new Actions(dr);
				 act.moveToElement(locOfOrder).doubleClick().build().perform();
				 // catch here is double click on the text will by default select the text 
				 // now apply copy command 
				 Logger log19 = Logger.getLogger("Reference Number");
				 WebElement san2 = dr.findElement(By.id("C_MAIN_REF"));
				 san2.sendKeys(Keys.chord(Keys.CONTROL,"c"));
				 log19.info(san2.getAttribute("value"));
				 Thread.sleep(2000);
				//Confirm button					        
				 Thread.sleep(1000);
					dr.switchTo().defaultContent();
					  
					//switch to frame2
					dr.switchTo().frame("eeToolbar"); 
			Logger log20 = Logger.getLogger("End");
			 dr.findElement(By.xpath("//*[@id='_confirm']")).click();
			log20.info("Transaction Completed");
			 Thread.sleep(5000);
			 
//Screenshot					
			 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
			 try {
			   // now copy the  screenshot to desired location using copyFile method
			  
			 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\RegisterLC.png"));
			 
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