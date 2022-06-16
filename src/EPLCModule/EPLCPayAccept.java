package EPLCModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

public class EPLCPayAccept extends EPLCsupervisorRelease{
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@Test(priority = 7)
	public void PayAccept() throws InterruptedException, IOException {
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
			 //FileInputStream fis = new FileInputStream("E:\\Testing\\sss.xlsx");
			 XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 
			 XSSFSheet sheet = workbook.getSheet("Settle");
	
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
			 dr.findElement(By.xpath(".//*[@name='EPLC Settlement']")).click();
			 log5.info("EPLC Presentation");
			 Thread.sleep(1000);
			 Logger log6 = Logger.getLogger("Function Group");
			 dr.findElement(By.xpath(".//*[@name='G49082300311F05030702144']")).click();
			 log6.info("Pay/Accept");
			 Thread.sleep(2000);
			
			dr.switchTo().defaultContent();
			Logger log7 = Logger.getLogger("Reference Number");
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(1000);
			FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\sankar.xlsx");
			 //FileInputStream fis = new FileInputStream("E:\\Testing\\sss.xlsx");
			 XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
			 
			 XSSFSheet sheet1 = workbook1.getSheet("Datatypes in Java");
	
			 System.out.println(workbook1);
			Row row2 = sheet1.getRow(6);
			Cell cell2 = row2.getCell(4);
			String Charges = cell2.getStringCellValue();
			
			WebElement san = dr.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[8]/td[4]/input"));
			//san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
			san.sendKeys(Charges);
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
			Thread.sleep(1000);
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
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(2000);
//main	
		
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
//Maturity Date 
			/*Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@id='MATURITY_DT']")).sendKeys("2018-07-04");
			 Thread.sleep(1000);
			 dr.findElement(By.xpath(".//*[@id='PRES_BK_CORR_MED']")).click();
			 try {
				Alert alert = dr.switchTo().alert();
				 alert.accept();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			 
			 Thread.sleep(1000);
			 Logger log141 = Logger.getLogger("Maturity Date");
			 Row row11 = sheet.getRow(8);
			 Cell cell1111 = row11.getCell(2);
			 //double date = cell.getNumericCellValue();
			 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			 Date numberAsString1 = cell1111.getDateCellValue();
			 WebElement ele1 =dr.findElement(By.xpath(".//*[@id='MATURITY_DT']"));
			 ele1.sendKeys(formatter1.format(numberAsString1));
			 log141.info(ele1.getAttribute("value"));
			 Thread.sleep(1000);
			 dr.findElement(By.xpath(".//*[@id='PRES_BK_CORR_MED']")).click();
			 try {
				Alert alert = dr.switchTo().alert();
				 alert.accept();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//By Payment
			 FileInputStream fis11 = new FileInputStream("E:\\Testing\\test.xlsx");
				
			 XSSFWorkbook workbook11 = new XSSFWorkbook(fis11);
			 System.out.println(workbook11);
			 XSSFSheet sheet11 = workbook11.getSheet("RegLC");
			 Logger log14 = Logger.getLogger("Available by");
			 Row row6 = sheet11.getRow(4);
			 Cell cell6 = row6.getCell(6);
			 String Available = cell6.getStringCellValue();

			 //String payment = "BY MIXED PYMT";
			 switch(Available){
			

			 case "By Payment":
				 log14.info("By Payment");
				 break;
			 case "BY DEF PAYMENT":
			  log14.info("BY DEF PAYMENT");
				 break;
				 
			 case "BY ACCEPTANCE":
			  log14.info("BY ACCEPTANCE");
				 break;
			 case "BY NEGOTIATION":
			  log14.info("BY NEGOTIATION");
			 	break;
			 case "BY MIXED PYMT":
			// Settlement Amount
			Thread.sleep(1000);

			try {
				Logger log25 = Logger.getLogger("Settlement Amount");
				/*
				 * Row row22 = sheet.getRow(3); 
				 * Cell cell22 = row22.getCell(2);
				 * String Settlement = cell22.getStringCellValue();
				 */
				String Settlement = "20000";
				dr.findElement(By.xpath("//*[@id='STL_AMT']")).click();
				WebElement san1 = dr.findElement(By.xpath("//*[@id='STL_AMT']"));
				san1.sendKeys(Settlement);
				log25.info(san1.getAttribute("value"));
				// dr.findElement(By.xpath("//*[@id='PRES_BK_AC_NO']")).click();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
//Total Amount Received
			Thread.sleep(1000);
			try {
				Logger log21 = Logger.getLogger("Total Amount ReceivedSettlement Amount");
				/*
				 * Row row22 = sheet.getRow(3); 
				 * Cell cell22 = row22.getCell(4);
				 * String Total = cell22.getStringCellValue();
				 */
				String Total = "20000";
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='TTL_STL_AMT_RCV']")).click();
				WebElement san11 = dr.findElement(By.xpath("//*[@id='TTL_STL_AMT_RCV']"));
				san11.sendKeys(Total);
				log21.info(san11.getAttribute("value"));
				log14.info("BY MIXED PYMT");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

//	Value Date Debit
			
			dr.findElement(By.xpath(".//*[@id='VALUE_DT_DR']")).sendKeys("2018-07-04");
			 Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='C']")).click();
			 Thread.sleep(1000);
			dr.findElement(By.xpath(".//*[@id='VALUE_DT_CR']")).sendKeys("2018-07-04");
			 break;
			 }
//settlement 
				 Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='C']")).click();
					Thread.sleep(1000);
					Logger log12 = Logger.getLogger("Settlement Instruction");
					Row row21 = sheet.getRow(8);
					 Cell cell21 = row21.getCell(4);
					String Charges11 = cell21.getStringCellValue();
					
					//String Charges11  = "Take Charges Separately";
					switch (Charges11){
					case "Deduct Charges from Proceeds":
						Thread.sleep(1000);
						Select Settlement1 = new Select (dr.findElement(By.xpath("//*[@id='STL_INSTR_FLG']")));
						Settlement1.selectByVisibleText("Deduct Charges from Proceeds");
						log12.info(Settlement1);
						break;
					case "Take Charges Separately":
						Thread.sleep(1000);
						Select Settlement11 = new Select (dr.findElement(By.xpath("//*[@id='STL_INSTR_FLG']")));
						Settlement11.selectByVisibleText("Take Charges Separately");
						log12.info(Settlement11);
						break;
					}
					
				// Payment
				Logger log13 = Logger.getLogger("Open Tab");
				dr.findElement(By.xpath("//*[@id='G']")).click();
				log13.info("Payment");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='GridDO_Child_1_0']")).click();
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='PaymentInstrDeal_EDIT']")).click();

				// Payment Debit

				try {
					Thread.sleep(1000);
					Logger log1411 = Logger.getLogger("Click");
					dr.findElement(By.xpath("//*[@id='do_PaymentDebitHeader_Tab']")).click();
					log1411.info("PaymentDebitHeader_Tab");
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='GridDO_Child_1_0_0_0']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='PaymentDebit_EDIT']")).click();
					Thread.sleep(1000);
					Logger log16 = Logger.getLogger("Account Type");
					dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[2]/td[4]/select")).sendKeys("NOSTRO");
					log16.info("NOSTRO");
					Thread.sleep(1000);
					Logger log17 = Logger.getLogger("Account Owner ID");
					dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[3]/td[4]/input[1]")).sendKeys("78965412");
					log17.info("78965412");
					Logger log171 = Logger.getLogger("	Account No.");
					dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[6]/td[4]/input[1]")).sendKeys("EP465554");
					log171.info("EP465554");
					Thread.sleep(1000);
					//dr.findElement(By.xpath("//*[@id='PaymentDebit_SAVE']")).click();

					// Payment Credit

					Thread.sleep(3000);
					Logger log18 = Logger.getLogger("Click");
					dr.findElement(By.xpath(".//*[@id='do_PaymentCreditHeader_Tab']")).click();
					log18.info("PaymentCreditHeader_Tab");
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='PaymentCredit_ADD']")).click();
					Thread.sleep(1000);
					Logger log20 = Logger.getLogger("Account Type");
					dr.findElement(By.xpath("//*[@id='CPYT_CR_AC_TYPE']")).sendKeys("VOSTRO");
					log20.info("VOSTRO");
					Thread.sleep(1000);
					Logger log21 = Logger.getLogger("Account Type");
					dr.findElement(By.xpath("//*[@id='do_PaymentCredit_M']/table/tbody/tr[5]/td[4]/span/input[1]")).sendKeys("98784562");
					log21.info("98784562");
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='PaymentCredit_SAVE']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='do_PaymentDebitHeader_Tab']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='PaymentDebit_SAVE']")).click();
					Thread.sleep(1000);
					dr.findElement(By.xpath("//*[@id='PaymentInstrDeal_SAVE']")).click();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// PAYMENT MATURITY
				// Acceptance, Mixed Payment

				try {
					Thread.sleep(1000);
					Logger log22 = Logger.getLogger("Maturity Date:");
					dr.findElement(By.xpath("//*[@id='CPYT_D_TENOR_START_DATE']")).sendKeys("2018-07-10");
					Thread.sleep(2000);
					log22.info("2018-06-10");
					dr.findElement(By.xpath("//*[@id='PaymentInstrDeal_SAVE']")).click();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}
				// PayMEnt Close
			 
//Advice TAB						
				Thread.sleep(2000);
				Logger log211111 = Logger.getLogger("Open");			
				dr.findElement(By.xpath(".//*[@id='K']")).click();
				log211111.info("Advice Tab");
				Logger log2011 = Logger.getLogger("Open");	
				dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
				log2011.info("Customer");
//Customer			
				 Thread.sleep(1000);
				 dr.switchTo().frame("frame.AdivceForBankCust");
					Thread.sleep(3000);
					Logger log101 = Logger.getLogger("	Narrative Mail");	
				dr.findElement(By.xpath("//*[@id='CUST_NARR_TAG_79']")).sendKeys("Test1");
				log101.info("Test1");
				// Save Button
				Thread.sleep(1000);
				dr.switchTo().defaultContent();
		
				// switch to frame2
				dr.switchTo().frame("work");
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();// *[@id="ext-gen310"]
		
				Thread.sleep(1000);
				dr.findElement(By.xpath("//*[@id='AdivceForBankCustClose']")).click();// *[@id="AdivceForBankCustClose"]
				Thread.sleep(1000);
//Charges				
			Logger log131 = Logger.getLogger("Charges");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='D']")).click();
			Row row211 = sheet.getRow(8);
			Cell cell211 = row211.getCell(6);
			String Charges1 = cell211.getStringCellValue();
			
			//String Charges1  = "DEFERRED";
			switch (Charges1){
			case "TRANSACTION" :
				WebElement ele8 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
				ele8.sendKeys("TRANSACTION");
				log131.info("TRANSACTION");
				Thread.sleep(1000);
				Logger log01 = Logger.getLogger("AC/NO	");
				WebElement ele01 =dr.findElement(By.xpath("//*[@id='CHG_FLD_LOCAL_CUST_AC_NO']"));
				ele01.sendKeys("54964654");
				log01.info("54964654");
				break;
			case "DEFERRED" :
				WebElement ele81 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
				ele81.sendKeys("DEFERRED");
				log131.info("DEFERRED");
				break;
			case "WAIVED" :
				WebElement ele11 =dr.findElement(By.xpath("//*[@id='CHG_FLD_ALL_CHARGE_AT']"));
				ele11.sendKeys("WAIVED");
				log131.info("WAIVED");
				break;
				
			}
//Confirm button					        
			dr.switchTo().defaultContent();
	
			// switch to frame2
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
			File src1 = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
			try {
				// now copy the screenshot to desired location using copyFile method
	
				FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\EPLCPayaccept.png"));
	
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