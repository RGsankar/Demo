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

public class PaymentAccept extends SupervisorRelease1 {
	public static WebDriver dr;

	public WebDriver GetDriver() {
		return dr;
	}

	@Test(priority = 6)
	public void payment() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");

		// Initialize browser
		dr = new ChromeDriver();
		Logger log = Logger.getLogger("URL Open");
		// configure log4j properties file
		PropertyConfigurator.configure("Log4j.properties");

		dr.get("http://192.168.2.9:9082/EximBillWeb/");
		log.info("Chrome Browser");
		dr.manage().window().maximize();
		/*
		 * }
		 * 
		 * @Test public static void Accept() throws InterruptedException{
		 */
		Logger log1 = Logger.getLogger("Unite Code");
		dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
		log1.info("CSBANK");
		Logger log2 = Logger.getLogger("User Name");
		dr.findElement(By
				.xpath(".//*[@id='ext-gen6']/form/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td/table/tbody/tr[3]/td[4]/div[2]/table/tbody/tr[2]/td[2]/input"))
				.sendKeys("CSBANKOP");
		log2.info("CSBANKOP");
		Logger log3 = Logger.getLogger("Pass word");
		dr.findElement(By.xpath(".//*[@id='tipLogPwd']")).sendKeys("1Q1Q1Q1Q");
		log3.info("1Q1Q1Q1Q");
		Logger log4 = Logger.getLogger("CLICK");
		dr.findElement(By.xpath(".//*[@id='Image1']")).click();
		log4.info("Submit");
		Thread.sleep(1000);
		// Commented the code for finding the index of the element
		dr.switchTo().frame(3); // Switching to the frame
		System.out.println("********We are switched to the iframe*******");
		Thread.sleep(1000);
		Logger log5 = Logger.getLogger("IPLC Module");
		dr.findElement(By.xpath(".//*[@name='Import Letter of Credit']")).click();
		log5.info("Import Letter of Credit");
		Logger log6 = Logger.getLogger("Function");
		dr.findElement(By.xpath(".//*[@name='IPLC Settlement']")).click();
		log6.info("IPLC Settlement");
		Logger log7 = Logger.getLogger("Function Group");
		dr.findElement(By.xpath(".//*[@name='G49082300323F05030703386']")).click();
		log7.info("Pay/Accept");

		Thread.sleep(2000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");
		Thread.sleep(1000);
		Logger log8 = Logger.getLogger("Reference Number");
		WebElement san = dr
				.findElement(By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		log8.info(san.getAttribute("value"));
		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("eeToolbar");

		dr.findElement(By.xpath("//*[@id='_next']")).click();

		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");

		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='CataListTab']/tbody/tr[2]/td/table/tbody/tr/td[2]")).click();

		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("eeToolbar");

		dr.findElement(By.xpath("//*[@id='_next']")).click();
		// Main
		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");
		Thread.sleep(3000);
		Logger log9 = Logger.getLogger("Tenor Start Date");
		dr.findElement(By.xpath("//*[@id='TENOR_START_DT']")).sendKeys("2018-05-30");
		log9.info("2018-05-30");
		// Mixed
		try {
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='TOTAL_AMT']")).click();
			Logger log10 = Logger.getLogger("Total Amount Claimed (32B)	");
			dr.findElement(By.xpath("//*[@id='TOTAL_AMT']")).sendKeys("1000");
			log10.info("1000");
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}

		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='C']")).click();

		// MAIL ACCEPT
		try {
			Logger log11 = Logger.getLogger("Acceptance Msg");
			dr.findElement(By.xpath("//*[@id='C_div']/table/tbody/tr[3]/td[4]/select")).sendKeys("Mail");
			log11.info("Mail");
			Thread.sleep(1000);
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		// Charges
		try {
			dr.findElement(By.xpath("//*[@id='G']")).click();
			Logger log12 = Logger.getLogger("AC/NO");
			dr.findElement(By.xpath("//*[@id='CHG_GETAC_BTN']")).click();
			log12.info("320318");
			// Pop window
			Set<String> windowId1 = dr.getWindowHandles();
			Iterator<String> itererator1 = windowId1.iterator();

			String mainWinID1 = itererator1.next();
			String newAdwinID1 = itererator1.next();

			dr.switchTo().window(newAdwinID1);
			System.out.println(dr.getTitle());

			dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();

			Thread.sleep(1000);
			// dr.close();
			dr.switchTo().window(mainWinID1);

			System.out.println(dr.getTitle());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Thread.sleep(1000);

		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");
		Thread.sleep(3000);

		// Payment
		Logger log13 = Logger.getLogger("Open Tab");
		dr.findElement(By.xpath("//*[@id='D']")).click();
		log13.info("Payment");
		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='GridDO_Child_0_0']")).click();
		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='PaymentInstrDeal_EDIT']")).click();

		// Payment Debit

		try {
			Thread.sleep(1000);
			Logger log14 = Logger.getLogger("Click");
			dr.findElement(By.xpath("//*[@id='do_PaymentDebitHeader_Tab']")).click();
			log14.info("PaymentDebitHeader_Tab");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='PaymentDebit_ADD']")).click();
			Thread.sleep(1000);
			Logger log15 = Logger.getLogger("Debit Value Date");
			dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[8]/td[2]/input")).sendKeys("2018-05-30");
			log15.info("2018-05-30");
			Thread.sleep(1000);
			Logger log16 = Logger.getLogger("Account Type");
			dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[2]/td[4]/select")).sendKeys("NOSTRO");
			log16.info("NOSTRO");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[3]/td[4]/input[1]")).clear();
			Logger log17 = Logger.getLogger("Account Owner ID");
			dr.findElement(By.xpath("//*[@id='do_PaymentDebit']/table/tbody/tr[3]/td[4]/input[2]")).click();
			log17.info("PTSABMABXXX");
			// Pop window
			Set<String> windowId = dr.getWindowHandles();
			Iterator<String> itererator = windowId.iterator();

			String mainWinID = itererator.next();
			String newAdwinID = itererator.next();

			dr.switchTo().window(newAdwinID);
			System.out.println(dr.getTitle());
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='3']/td[2]/a")).click();

			Thread.sleep(1000);
			// dr.close();
			dr.switchTo().window(mainWinID);

			System.out.println(dr.getTitle());
			Thread.sleep(1000);
			// frame work
			Thread.sleep(1000);
			dr.switchTo().defaultContent();

			// switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='PaymentDebit_SAVE']")).click();

			// Payment Credit

			Thread.sleep(3000);
			Logger log18 = Logger.getLogger("Click");
			dr.findElement(By.xpath(".//*[@id='do_PaymentCreditHeader_Tab']")).click();
			log18.info("PaymentCreditHeader_Tab");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//*[@id='PaymentCredit_ADD']")).click();
			Thread.sleep(1000);
			Logger log19 = Logger.getLogger("Credit Value Date	");
			dr.findElement(By.xpath("//*[@id='do_PaymentCredit_M']/table/tbody/tr[7]/td[2]/span/input"))
					.sendKeys("2018-05-30");
			log19.info("2018-05-30");
			Thread.sleep(1000);
			Logger log20 = Logger.getLogger("Account Type");
			dr.findElement(By.xpath("//*[@id='CPYT_CR_AC_TYPE']")).sendKeys("VOSTRO");
			log20.info("VOSTRO");
			Thread.sleep(2000);
			Logger log21 = Logger.getLogger("Account Type");
			dr.findElement(By.xpath("//*[@id='do_PaymentCredit_M']/table/tbody/tr[3]/td[4]/input[2]")).click();
			log21.info("SUMIJP50XXX");
			// Pop window
			Set<String> windowId6 = dr.getWindowHandles(); // get window id of
															// current window
			Iterator<String> itererator6 = windowId6.iterator();

			String mainWinID6 = itererator6.next();
			String newAdwinID6 = itererator6.next();

			dr.switchTo().window(newAdwinID6);
			System.out.println(dr.getTitle());

			dr.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();

			Thread.sleep(1000);
			// dr.close();
			dr.switchTo().window(mainWinID6);

			System.out.println(dr.getTitle());
			Thread.sleep(1000);
			// frame work
			Thread.sleep(3000);
			dr.switchTo().defaultContent();

			// switch to frame2
			dr.switchTo().frame("work");
			Thread.sleep(2000);

			Thread.sleep(1000);
			dr.findElement(By.xpath("//*[@id='PaymentCredit_SAVE']")).click();
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
			dr.findElement(By.xpath("//*[@id='do_PaymentInstrDeal_M']/table/tbody/tr[4]/td[4]/input"))
					.sendKeys("2018-06-10");
			Thread.sleep(2000);
			log22.info("2018-06-10");
			dr.findElement(By.xpath("//*[@id='PaymentInstrDeal_SAVE']")).click();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();

		}
		// PayMEnt Close
		Thread.sleep(1000);

		// Advice Tab
		Thread.sleep(2000);
		dr.findElement(By.xpath("//*[@id='K']")).click();
		// log4.info("Advice");
		dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();// add button
		// log4.info("Bank");
		// Bank
		Thread.sleep(1000);
		// dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("frame.AdivceForBankCust");
		Thread.sleep(3000);
		Logger log23 = Logger.getLogger("Type of Message");
		dr.findElement(By.xpath("//*[@id='MESG_TYPE_BANK']")).sendKeys("Mail");
		log23.info("Mail");
		Logger log24 = Logger.getLogger("ID,Name,Address");
		dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[4]/td[2]/input[2]")).click();// CUBK
																								// Button
		log24.info("ABNADEHHCGN,ABN AMRO BANK (DEUTSCHLAND) AG,undefined");
		// Alert Message

		Alert alert = dr.switchTo().alert();

		// Capturing alert message.
		String alertMessage = dr.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);
		Thread.sleep(3000);

		// Accepting alert
		alert.accept();
		Thread.sleep(3000);
		Logger log25 = Logger.getLogger("Narrative (Mail)");
		dr.findElement(By.xpath("//*[@id='BANK_NARR_MAIL']")).sendKeys("Test1");
		log25.info("Test1");

		Set<String> windowId3 = dr.getWindowHandles(); // get window id of
														// current window
		Iterator<String> itererator3 = windowId3.iterator();

		String mainWinID3 = itererator3.next();
		String newAdwinID3 = itererator3.next();

		dr.switchTo().window(newAdwinID3);
		System.out.println(dr.getTitle());

		dr.findElement(By.xpath("//*[@id='1']/td[2]/a")).click();

		dr.switchTo().window(mainWinID3);
		System.out.println(dr.getTitle());
		Thread.sleep(1000);
		// Customer
		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");

		dr.switchTo().frame("frame.AdivceForBankCust");
		Thread.sleep(3000);
		// Click Customer

		dr.findElement(By.xpath("//*[@id='B']")).click();
		// log4.info("Customer");
		Logger log26 = Logger.getLogger("Type of Message");
		dr.findElement(By.xpath("//*[@id='MESG_TYPE_CUST']")).sendKeys("Mail");
		log26.info("Mail");
		Logger log27 = Logger.getLogger("ID,Name,Address");
		dr.findElement(By.xpath("//*[@id='B_div']/table/tbody/tr[4]/td[2]/input[2]")).click();
		log27.info("009800722,SHANGHAI FEI LONG CO LTD,SHANG HAI,");
		// Alert message close
		Alert alert1 = dr.switchTo().alert();

		// Capturing alert message.
		String alertMessage1 = dr.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage1);
		Thread.sleep(3000);

		// Accepting alert
		alert1.accept();
		Thread.sleep(3000);
		dr.findElement(By.xpath("//*[@id='CUST_NARR_TAG_79']")).sendKeys("Test");

		Set<String> window = dr.getWindowHandles(); // get window id of current
													// window
		Iterator<String> itererator2 = window.iterator();

		String mainWin = itererator2.next();
		String newAdwin = itererator2.next();

		dr.switchTo().window(newAdwin);
		System.out.println(dr.getTitle());

		dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();

		// dr.close();
		dr.switchTo().window(mainWin);
		System.out.println(dr.getTitle());
		Thread.sleep(1000);
		// Save Button

		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");

		dr.findElement(By.xpath("//*[@id='ext-gen286']")).click();
		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='ext-gen294']")).click();

		// Confirm
		Thread.sleep(2000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("eeToolbar");

		Thread.sleep(2000);
		Logger Log28 = Logger.getLogger("Confirm");
		dr.findElement(By.xpath("//*[@id='_confirm']")).click();
		Log28.info("Trnsaction completed");
		try {
			Alert alert2 = dr.switchTo().alert();
			alert2.accept();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread.sleep(5000);
		// Screenshot
		File src = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File("E:\\Testing\\Baseline\\ScreenShot\\PaymentAccept.png"));

		}

		catch (IOException e)

		{

			System.out.println(e.getMessage());

		}
		// cancel

		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("eeToolbar");

		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='_cancel']")).click();

	}

}