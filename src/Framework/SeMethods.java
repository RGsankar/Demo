package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class SeMethods implements WdMethods{
	public RemoteWebDriver driver;
	public int i = 1;
	public int j = 1;
	public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //Date Formatter	
	public Date date = new Date();  //Current Date 
	public String Type_of_message; // Type of message (Advice)
	public String Related_Ref; //Related Reference Number (Advice)
	public String SWIFT_TagAddress; //SWIFT_TagAddress (Advice)
	public String Narrative_MT; // Narrative (Advice)
//Mandatory,Optional and Protected field value
    String Mandatory = "153, 204, 255";
    String Optional = "255, 255, 255";
    String Protected = "242, 242, 242";
    ATUTestRecorder recorder; //Video Recored
	public void startApp(String browser, String url) {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","E:\\Testing\\Baseline\\drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
				 driver = new FirefoxDriver();
			}
			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			System.out.println("The Browser "+browser+" launched successfully");
			//takeSnap();
		}	catch (WebDriverException e) {
			System.err.println("Webdriver not present");
			throw new RuntimeException();
		}
	}

	public WebElement locateElement(String locator, String locValue) {
		try {
			switch(locator) {
			case "id": 
				return driver.findElementById(locValue);			
			case "class":
				return driver.findElementByClassName(locValue);
			case "name":
				return driver.findElementByName(locValue);
			case "linktext": 
				return driver.findElementByLinkText(locValue);			
			case "tagname":
				return driver.findElementByTagName(locValue);
			case "partiallinktext":
				return driver.findElementByPartialLinkText(locValue);
			case "xpath":
				return driver.findElementByXPath(locValue);
			case "cssselector":
				return driver.findElementByCssSelector(locValue);
			}
		} catch (NoSuchElementException e) {
			System.err.println("The"+locValue+"  is not present");
		}
		catch (WebDriverException e) {
			System.err.println("The driver is not present");
		}
		return null;
	}

	public WebElement locateElement(String locValue) {		
		try {
			return driver.findElementById(locValue);
		} catch (NoSuchElementException e) {
			System.err.println("Element not found");
			throw new RuntimeException();
		}catch (WebDriverException e) {
			System.err.println("Driver not found");
			throw new RuntimeException();
		}
		
		//return null;
	}
	public void Clear(WebElement toClear) {
		
		toClear.sendKeys(Keys.CONTROL + "a");
		toClear.sendKeys(Keys.DELETE);
	}
	public void type(WebElement ele, String data) {
		//ele.clear();
		ele.sendKeys(data);
		System.out.println("The data "+data+"  is entered successfully");
		//takeSnap();
	}

	public void click(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,3);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println("The Element "+ele+" is clicked successfully");
			//takeSnap();
		} catch (TimeoutException e) {
			
			System.err.println("Time out error ");	
	}}

	public void clicknoSnap(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,2);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println("The Element "+ele+" is clicked successfully");
		
		} catch (TimeoutException e) {
			System.err.println("Time out error ");	
	}}
	public String getText(WebElement ele) {		
		String text = ele.getText();
		//takeSnap();
		return text;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {
		Select dd=new Select(ele);
		dd.selectByVisibleText(value);
		System.out.println("The dropdown option "+value+" is selected successfully");
		/*List<WebElement> dropdown=dd.getOptions();
		 
		 for(int i=0;i<dropdown.size();i++){
		 
		 String drop_down_values=dropdown.get(i).getText();
		 
		 System.out.println("dropdown values are "+drop_down_values);
		 }
		//takeSnap();
*/	}

	public void selectDropDownUsingIndex(WebElement ele, int index) {
		Select dd=new Select(ele);
		dd.selectByIndex(index);
		System.out.println("The dropdown option is selected successfully");
		//takeSnap();
	}

	public boolean verifyTitle(String expectedTitle) {
		try {
			if(driver.getTitle().equals(expectedTitle))
				return true;
			else
				return false;
		} catch (WebDriverException e) {
			System.err.println("Webdriver not present");
			throw new RuntimeException();
		}
	}

	public void verifyExactText(WebElement ele, String expectedText) {
		if(ele.getText().equals(expectedText))	
		{
			System.out.println("Verified the given text with on the given element text");
		}
		else
		{
			System.out.println("The element text doesn't match the given text");
		}
	}

	public void verifyPartialText(WebElement ele, String expectedText) {
		if(ele.getText().contains(expectedText))	
		{
			System.out.println("Verified the given text with given element text");
		}
		else
		{
			System.out.println("The element text doesn't match the given text");
		}
	}

	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		String att = ele.getAttribute(attribute);
		if(att.equals(value))
		{
			System.out.println("Verified the given attribute value with element attribute value");
		}
		else
		{
			System.out.println("Verified the given attribute value with element attribute value");
		}
	}

	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		String att = ele.getAttribute(attribute);
		if(att.contains(value))
		{
			System.out.println("Verified the given value present in element's attribute value");
		}
		else
		{
			System.out.println("The given value is not present in element's attribute value");
		}
		
	}

	public void verifySelected(WebElement ele) {
		if(ele.isSelected()==true)
		{
			System.out.println("The given element is selected");
			//takeSnap();
		}
		else
		{
			System.out.println("The given element is not selected");
			//takeSnap();
		}
		
	}
	
	public void verifyisEnabled(WebElement ele){
		Logger Enabled = Logger.getLogger("Enabled");
		if(ele.isEnabled()==true)
		{
			System.out.println("To check the " + ele.getAttribute("name")+" field is enabled");
		}
		else
		{
			Enabled.error("To check the " + ele.getAttribute("name")+" field is Disabled");
		}
	}
	public void verifyisDisable(WebElement ele){
		Logger Disabled = Logger.getLogger("Disabled");
		if(ele.isEnabled()==false)
		{
			System.out.println("To check the " + ele.getAttribute("name")+" field is always Disabled");
		}
		else
		{
			Disabled.error("To check the " + ele.getAttribute("name")+" field is Enable");
		
		}
	}
	
	public void color(WebElement ele, String color1){
		Logger MOP = Logger.getLogger("Mandatory,Optional,Protected");
		String BG_Color =ele.getCssValue("background-color");
		switch (color1) {
		case "153, 204, 255":
			if (BG_Color.contains(color1)) {
				System.out.println(ele.getAttribute("title")+" field is always mandatory");
			} else {
				MOP.error(ele.getAttribute("name")+" field is should not mandatory");
				takeSnap();
			}
			break;
		case "242, 242, 242":
			if (BG_Color.contains(color1)) {
				System.out.println(ele.getAttribute("title")+" field is always Protected");
			} else {
				MOP.error(ele.getAttribute("name")+" field is should not Protected");
				takeSnap();
			}
			break;
		case "255, 255, 255":
			if (BG_Color.contains(color1)) {
				System.out.println(ele.getAttribute("title")+" field is always Optional");
			} else {
				takeSnap();
				MOP.error(ele.getAttribute("name")+" field is should not Optional");
			}
			break;
		}
	}

	public void Amount(WebElement ele) {
		Logger Negative = Logger.getLogger(ele.getAttribute("title"));
		String Amount1 = ele.getAttribute("Value");
		Amount1 = ele.getAttribute("value");
		double Amount2 = Double.parseDouble(Amount1.replace(",", ""));
		if (Amount2 < 0) {
			acceptAlert();
			Negative.error(ele.getAttribute("title") + " field accept Negative value :" + Amount2);
			takeSnap();
		}
		if (Amount1.length() > 18) {
			acceptAlert();
			Negative.error(ele.getAttribute("title") +" field accept more than 18 digital value:" + Amount1);
			takeSnap();
		}
		
		String alphavalue = "[a-zA-Z]*";
		if(Amount1.matches(alphavalue)){
			acceptAlert();
			Negative.error(" field not accept Character:" + Amount1);
			takeSnap();
		}
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		Amount1 = df.format(Amount2);
		if (Amount1.contains(",") || Amount1.equals("0.00")) {
			Negative.info(ele.getAttribute("title")+" field accept decimal range:" + Amount1);
		} else {
			acceptAlert();
			Negative.error(ele.getAttribute("title")+" field not accept decimal range:" + Amount1);
			takeSnap();
		}
	}
	
	public void Date_After(WebElement ele) throws ParseException, InterruptedException {
		Logger Date_Log = Logger.getLogger(ele.getAttribute("title"));
		String date_Cur= ele.getAttribute("Value");
		date_Cur = ele.getAttribute("value");
		String Date_Formate = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
		if (date_Cur.matches(Date_Formate)) {
			Date Remittance = formatter.parse(date_Cur);  
			if(Remittance.after(date)){
				acceptAlert();
				Date_Log.error("The Remittance Date is not allowed in future! " + formatter.format(Remittance));
			}
		}
		Thread.sleep(1000);
		if (date_Cur.trim().matches(Date_Formate)|| date_Cur.equals("")) {
			Date_Log.info("Remittance Date Format YYYY-MM-DD " + date_Cur);
		} else {
			acceptAlert();
			Date_Log.error("[W4423] Remittance Date format is error, please use YYYY-MM-DD." + date_Cur);
			takeSnap();
		}
		String str = "[a-zA-Z]*";
		if (date_Cur.matches(str) && !date_Cur.equals("")) {
			acceptAlert();
			Date_Log.error("The Remittance date field is accept alphanumeric Value:" + date_Cur);
			takeSnap();
		} 		
		
	}
	
	
	public void Date_Before(WebElement ele) throws ParseException, InterruptedException {
		Logger Date_Log = Logger.getLogger(ele.getAttribute("title"));
		String date_Cur= ele.getAttribute("Value");
		date_Cur = ele.getAttribute("value");
		String Date_Formate = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
		if (date_Cur.matches(Date_Formate)) {
			Date Remittance = formatter.parse(date_Cur);  
			if(Remittance.before(date)){
				acceptAlert();
				Date_Log.error("The Remittance Date is not allowed in future! " + formatter.format(Remittance));
			}
		}
		Thread.sleep(1000);
		if (date_Cur.trim().matches(Date_Formate)|| date_Cur.equals("")) {
			Date_Log.info("Remittance Date Format YYYY-MM-DD " + date_Cur);
		} else {
			acceptAlert();
			Date_Log.error("[W4423] Remittance Date format is error, please use YYYY-MM-DD." + date_Cur);
			takeSnap();
		}
		String str = "[a-zA-Z]*";
		if (date_Cur.matches(str) && !date_Cur.equals("")) {
			acceptAlert();
			Date_Log.error("The Remittance date field is accept alphanumeric Value:" + date_Cur);
			takeSnap();
		} 		
	}
	public void verifyDisplayed(WebElement ele) {
		if(ele.isDisplayed()==true)
		{
			System.out.println("The given element is Displayed");
			//takeSnap();
		}
		else
		{
			System.out.println("The given element is not Displayed");
			//takeSnap();
		}
		
	}

	public void switchToWindow(int index) {
		Set<String> allwindowHandles = driver.getWindowHandles();
		List<String> listwindow=new ArrayList<String>();
		listwindow.addAll(allwindowHandles);
		String secondWindow = listwindow.get(index);
		driver.switchTo().window(secondWindow);
		//switchToFramest("work");
	}
	public void switchToWindowset() {
		Set<String> set1 = driver.getWindowHandles();    
        Iterator<String> itererator11 = set1.iterator();   
        String set = itererator11.next();
        String  newAdwinID11 = itererator11.next();
        driver.switchTo().window(newAdwinID11);
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//*[@id='3']/td[2]/a")).click();
        driver.switchTo().window(set);
        System.out.println(driver.getTitle());
        switchToFramest("work");
        
	}
	public void Logger(WebElement ele){
		Logger title = Logger.getLogger(ele.getAttribute("title"));
		title.info(ele.getAttribute("value"));
	}
	
	public void switchToFrame(int ele) throws InterruptedException {
		Thread.sleep(1000);
		driver.switchTo().frame(ele);
	}
	
	public void switchToFramest(String ele) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(ele);
	}
	
	public void RecoredStart() throws ATUTestRecorderException{
		recorder = new ATUTestRecorder("Recored/",Integer.toString(j), false);
		recorder.start();
		j++;
	}
	public void RecoredStop() throws ATUTestRecorderException{
		recorder.stop();
	}
	public void MouseEvent(){
		Actions action= new Actions(driver);
		action.contextClick().build().perform();
	}
	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();	
			//Alert alert = driver.switchTo().alert();
			//alert.accept();
		} catch (NoAlertPresentException e) {
			System.err.println("Alert is not present");
		}catch (WebDriverException e) {
			System.err.println("driver is not present");
		}
		
	}
	public void AcceptAlert(){
		Logger Alert1 = Logger.getLogger("Alert Meaasge");
		try {
			Alert simpleAlert = driver.switchTo().alert();
			String alertText = simpleAlert.getText();
			System.out.println("Alert text is " + alertText);
			simpleAlert.accept();
		} catch (NoAlertPresentException e) {
			System.err.println("Alert is not present");
			Alert1.error("Alert is not present");
			e.printStackTrace();
		}catch (WebDriverException e) {
			System.err.println("driver is not present");
		}
	}
	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
		} catch (NoAlertPresentException e) {
			System.err.println("Alert is not present");
		}catch (WebDriverException e) {
			System.err.println("driver is not present");
		}
	}

	public String getAlertText() {
		// TODO Auto-generated method stub
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void takeSnap() {
	File src = driver.getScreenshotAs(OutputType.FILE);
	File desc = new File("./snaps/img"+i+".png");
	try {
		FileUtils.copyFile(src, desc);
	} catch (IOException e) {
		e.printStackTrace();
	}
		i++;
	}

	public void closeBrowser() {
		driver.close();
	}

	public void closeAllBrowsers() {
		driver.quit();
	}
	public void loginpage() throws InterruptedException {
	
		Logger log = Logger.getLogger("URL Open");
		startApp("chrome", "http://192.168.2.205:9084/EximBillWeb/");
		log.info("chrome");
		Logger log0 = Logger.getLogger("Unit Code");
		PropertyConfigurator.configure("Log4j.properties");
		WebElement uCode = locateElement("name", "C_BUSINESS_UNIT");
		type(uCode, "CSBANK");
		log0.info(uCode.getAttribute("value"));
		Logger log1 = Logger.getLogger("User Name");
		WebElement Username = locateElement("name", "C_USER_ID");
		type(Username, "CSBANKOP");
		log1.info(Username.getAttribute("value"));
		Logger log2 = Logger.getLogger("Password");
		WebElement Password = locateElement("name", "C_PASSWORD");
		type(Password, "1Q1Q1Q1Q");
		log2.info(Password.getAttribute("value"));
		WebElement login = locateElement("name", "Image1");
		click(login);
		Thread.sleep(1000);
		switchToFrame(3);
	}
	public void Advice() throws IOException, InterruptedException {
//Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheet("Collection");
		XSSFSheet sheet = workbook.getSheet("SameFile");
		workbook.close();
//Advice 
		 Logger log2011 = Logger.getLogger("Open");	
		 driver.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
		 log2011.info("Bank ");
//Bank			
		 Thread.sleep(1000);
		 driver.switchTo().frame("frame.AdivceForBankCust");
//Type of Message			 
		 WebElement Method9 = locateElement("name","MESG_TYPE_BANK");
		 selectDropDownUsingText(Method9,"Mail"); // Rechecks
		 Thread.sleep(1000);
		 Logger log222 = Logger.getLogger("Type of Message");	
		 Row Message = sheet.getRow(3);
		 Cell Type = Message.getCell(4);
		 Type_of_message = Type.getStringCellValue();
		 selectDropDownUsingText(Method9,Type_of_message);
		 log222.info(Method9.getAttribute("value"));
		 Thread.sleep(1000);
		 
//Bank Id
		 WebElement SND_TO_ID_BANK_BTN = locateElement("name","SND_TO_ID_BANK_BTN");
		 verifyisEnabled(SND_TO_ID_BANK_BTN);
		 Row Bank_Id = sheet.getRow(3);
		 Cell Bank_Id1 = Bank_Id.getCell(6);
		 String Bank_Id2 = getCellValueAsString(Bank_Id1);
		if (Bank_Id2 == null || Bank_Id2.isEmpty()) {
			click(SND_TO_ID_BANK_BTN);
																										
			// Alert Message
			Thread.sleep(1000);
			acceptAlert();
			Thread.sleep(1000);
			Set<String> windowId = driver.getWindowHandles();
			Iterator<String> itererator = windowId.iterator();
			String mainWinID = itererator.next();
			String newAdwinID = itererator.next();
			driver.switchTo().window(newAdwinID);
			driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
			driver.switchTo().window(mainWinID);

			//Frame		        
			Thread.sleep(1000);
		      switchToFramest("work");
		      driver.switchTo().frame("frame.AdivceForBankCust");
		}
		 else{
			 WebElement SEND_TO_BANK_ID = locateElement("name","SEND_TO_BANK_ID");
			 type(SEND_TO_BANK_ID,Bank_Id2);
		 }
//Xpath
		WebElement SEND_TO_BANK_NM = locateElement("name","SEND_TO_BANK_NM");//Name
		WebElement SEND_TO_BK_SW_ADD = locateElement("name","SEND_TO_BK_SW_ADD");//SWIFT Tag/Address
		WebElement SEND_TO_BANK_REF = locateElement("name","SEND_TO_BANK_REF");//Related Reference
		WebElement SEND_TO_BANK_POST_ADD = locateElement("name","SEND_TO_BANK_POST_ADD");//Mail Address
		WebElement SEND_TO_BANK_POST_ADD_BTN = locateElement("name","SEND_TO_BANK_POST_ADD_BTN"); //Button Mail Address
		WebElement SEND_TO_BANK_FAX = locateElement("name","SEND_TO_BANK_FAX");// Fax No
		WebElement SEND_TO_BANK_EMAIL = locateElement("name","SEND_TO_BANK_EMAIL");// Email Address
		WebElement BANK_NARR_TAG_79  = locateElement("id","BANK_NARR_TAG_79"); //Narrative (MT n99 Tag 79Z)	
		WebElement BANK_NARR_MAIL  = locateElement("id","BANK_NARR_MAIL");// Narrative (Mail)
		WebElement SEND_TO_BANK_LANG  = locateElement("id","SEND_TO_BANK_LANG");// Language
		
		switch(Type_of_message){
		case"MT199":
		case"MT299":
		case"MT499":
		case"MT999":
			color(SEND_TO_BANK_NM,Optional);
			color(SEND_TO_BK_SW_ADD,Mandatory);
			color(SEND_TO_BANK_REF,Mandatory);
			color(SEND_TO_BANK_POST_ADD,Optional);
			color(SEND_TO_BANK_FAX,Optional);
			color(SEND_TO_BANK_EMAIL,Optional);
			color(BANK_NARR_TAG_79,Mandatory);
			color(BANK_NARR_MAIL,Protected);
			
			//Save Button	 	  
			   switchToFramest("work");
			   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
			   AcceptAlert();
			Thread.sleep(1000);   
			//Frame
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
			Thread.sleep(1000);   
			type(BANK_NARR_TAG_79, "Test1");
			Logger(BANK_NARR_TAG_79);
			
			break;
		case"Mail":
			//selectDropDownUsingText(Method9,"Mail"); // Rechecks
			Row Post_Address = sheet.getRow(5);
			Cell Post_Address1 = Post_Address.getCell(4);
			String POST_ADD = getCellValueAsString(Post_Address1);
			
			Clear(SEND_TO_BANK_POST_ADD);
			type(BANK_NARR_MAIL, "Test1");
			//Save Button	 	  
			   switchToFramest("work");
			   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
			   AcceptAlert();
			Thread.sleep(1000);   
			//Frame
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
			Thread.sleep(1000);   
			
			if (POST_ADD == null || POST_ADD.isEmpty()) {
				click(SEND_TO_BANK_POST_ADD_BTN);
				Thread.sleep(1000);
				Set<String> windowId = driver.getWindowHandles();
				Iterator<String> itererator = windowId.iterator();
				String mainWinID = itererator.next();
				String newAdwinID = itererator.next();
				driver.switchTo().window(newAdwinID);
				driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
				driver.switchTo().window(mainWinID);

				//Frame		        
				Thread.sleep(1000);
			      switchToFramest("work");
			      driver.switchTo().frame("frame.AdivceForBankCust");
			}
			else{
				type(SEND_TO_BANK_POST_ADD, POST_ADD);
			}
			Logger(SEND_TO_BANK_POST_ADD);
			Logger(BANK_NARR_MAIL);

			color(SEND_TO_BANK_NM, Mandatory);
			color(SEND_TO_BK_SW_ADD, Optional);
			color(SEND_TO_BANK_REF, Optional);
			color(SEND_TO_BANK_POST_ADD, Mandatory);
			color(SEND_TO_BANK_FAX, Optional);
			color(SEND_TO_BANK_EMAIL, Optional);
			color(BANK_NARR_TAG_79, Protected);
			color(BANK_NARR_MAIL, Mandatory);
			break;
		case"Fax":
			//selectDropDownUsingText(Method9,"Fax"); // Rechecks
			Row BANK_FAX = sheet.getRow(5);
			Cell BANK_FAX1 = BANK_FAX.getCell(6);
			String BANK_FAX11 = getCellValueAsString(BANK_FAX1);
			
			Clear(SEND_TO_BANK_FAX);
			//Save Button	 	  
			   switchToFramest("work");
			   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
			   AcceptAlert();
			Thread.sleep(1000);   
			//Frame
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
			Thread.sleep(1000);   
			
			if (BANK_FAX11 == null || BANK_FAX11.isEmpty()) {
				type(SEND_TO_BANK_FAX, "797974464641");
			}
			else{
				type(SEND_TO_BANK_FAX, BANK_FAX11);
			}
			
			color(SEND_TO_BANK_NM,Mandatory);
			color(SEND_TO_BK_SW_ADD,Optional);
			color(SEND_TO_BANK_REF,Optional);
			color(SEND_TO_BANK_POST_ADD,Optional);
			color(SEND_TO_BANK_FAX,Mandatory);
			color(SEND_TO_BANK_EMAIL,Optional);
			color(BANK_NARR_TAG_79,Protected);
			color(BANK_NARR_MAIL,Protected);
			break;
		case"Email":
		//	selectDropDownUsingText(Method9,"Email"); // Rechecks
			Row BANK_EMAIL = sheet.getRow(7);
			Cell BANK_EMAIL1 = BANK_EMAIL.getCell(2);
			String BANK_EMAIL11 = getCellValueAsString(BANK_EMAIL1);
			
			Clear(SEND_TO_BANK_EMAIL);
			//Save Button	 	  
			
			   switchToFramest("work");
			   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
			   AcceptAlert();
			Thread.sleep(1000);   
			//Frame
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
			Thread.sleep(1000);   
			
			type(SEND_TO_BANK_EMAIL, BANK_EMAIL11); // EMAIL 
		
			Logger EmailAddress = Logger.getLogger("Email Address");
			String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			String EmailAddress1 = driver.findElement(By.name("SEND_TO_BANK_EMAIL")).getAttribute("value");
			
			if (EmailAddress1.matches(EMAIL_REGEX)) {
				EmailAddress.info("is e-mail: " + EmailAddress1 + " :Valid ");
			} else {
				EmailAddress.error("is e-mail: " + EmailAddress1 + " :not Valid ");
				takeSnap();
			}
			color(SEND_TO_BANK_NM,Mandatory);
			color(SEND_TO_BK_SW_ADD,Optional);
			color(SEND_TO_BANK_REF,Optional);
			color(SEND_TO_BANK_POST_ADD,Optional);
			color(SEND_TO_BANK_FAX,Optional);
			color(SEND_TO_BANK_EMAIL,Mandatory);
			color(BANK_NARR_TAG_79,Protected);
			color(BANK_NARR_MAIL,Protected);
			break;
		case"None":
			//selectDropDownUsingText(Method9,"None"); // Rechecks
			color(SEND_TO_BANK_NM,Optional);
			color(SEND_TO_BK_SW_ADD,Protected);
			color(SEND_TO_BANK_REF,Protected);
			color(SEND_TO_BANK_POST_ADD,Optional);
			color(SEND_TO_BANK_FAX,Optional);
			color(SEND_TO_BANK_EMAIL,Optional);
			color(BANK_NARR_TAG_79,Protected);
			color(BANK_NARR_MAIL,Protected);
			color(SEND_TO_BANK_LANG,Protected);
			color(SEND_TO_BANK_LANG,Protected);
			
			//Save Button	 	  
			   switchToFramest("work");
			   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
			   AcceptAlert();
			Thread.sleep(1000);   
			//Frame
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
			Thread.sleep(1000);   
			break;
		}
		Narrative_MT = BANK_NARR_TAG_79.getAttribute("value");
//Related Reference
		Logger REL_REF = Logger.getLogger("Related Reference");
		Row BANK_REF11 = sheet.getRow(5);
		Cell BANK_REF111 = BANK_REF11.getCell(2);
		String BANK_REF21 = getCellValueAsString(BANK_REF111);
		if (BANK_REF21 == null || BANK_REF21.isEmpty()) {
			Row BANK_REF = sheet1.getRow(5);
			Cell BANK_REF1 = BANK_REF.getCell(2);
			String BANK_REF2 = getCellValueAsString(BANK_REF1);
			String Related_Reference = SEND_TO_BANK_REF.getAttribute("value");
			if (!BANK_REF2.equals(Related_Reference)) {
				REL_REF.error("Remitting Party Reference and Related Reference should display the same value but here not same value");
			}
		} else {
			
			Clear(SEND_TO_BANK_REF);
			type(SEND_TO_BANK_REF, BANK_REF21);
			click(SEND_TO_BANK_POST_ADD);
			String Related_Reference1 = SEND_TO_BANK_REF.getAttribute("value");
			if (Related_Reference1.length() > 16) {
				REL_REF.error("Related Reference accept more than 16 digital");
			}
		}
		
		Related_Ref = SEND_TO_BANK_REF.getAttribute("value");
		
//SWIFT Tag/Address
		 Logger Tag_Address = Logger.getLogger("SWIFT Tag/Address");
		 WebElement SEND_TO_BK_SW_TAG  = locateElement("name","SEND_TO_BK_SW_TAG");
		 SWIFT_TagAddress = SEND_TO_BK_SW_ADD.getAttribute("value");
		 if(SWIFT_TagAddress != ""){
			 String SW_TAG = SEND_TO_BK_SW_TAG.getAttribute("value");
			 if(!SW_TAG.equals("A")){
				 Tag_Address.error("SWIFT Tag/Address should be A but here getting D ");
			 }
		 }
		 Clear(SEND_TO_BK_SW_ADD);
		 click(SEND_TO_BANK_POST_ADD);// OnClick
		 String SWIFT_TagAddress1 = SEND_TO_BK_SW_ADD.getAttribute("value");
		 if(SWIFT_TagAddress1.equals("")){
			 String SW_TAG1 = SEND_TO_BK_SW_TAG.getAttribute("value");
			 if(!SW_TAG1.equals("D")){
				 Tag_Address.error("SWIFT Tag/Address should be D but here getting A ");
			 }
		 }
		type(SEND_TO_BK_SW_ADD,SWIFT_TagAddress);
		
		 
//ID		      
	        Logger ID = Logger.getLogger("ID");
		    WebElement Narrative111  = locateElement("name","SEND_TO_BANK_ID");
		    ID.info(Narrative111.getAttribute("value"));
//Name			
		    Logger ID1 = Logger.getLogger("Name");
		    WebElement Name  = locateElement("name","SEND_TO_BANK_NM");
		    ID1.info(Name.getAttribute("value"));
//Address
		    Logger ID11 = Logger.getLogger("Name");
		    WebElement Address  = locateElement("name","SEND_TO_BANK_ADD1");
		    ID11.info(Address.getAttribute("value"));
//Customer
			driver.findElement(By.xpath("//*[@id='B']")).click();
			Logger log131 = Logger.getLogger("Type of Message");
			Row row28 = sheet.getRow(3);
			Cell cell002 = row28.getCell(2);
			String cus = cell002.getStringCellValue();
			//String cus = "Email";
			WebElement Our2 = locateElement("id", "MESG_TYPE_CUST");
			selectDropDownUsingText(Our2, cus);
			log131.info(Our2.getAttribute("value"));
			driver.findElement(By.xpath("//*[@id='B_div']/table/tbody/tr[4]/td[2]/input[2]")).click();
//Alert message close				
		    acceptAlert();
//Narrative Mail
		    Logger log1211 = Logger.getLogger("Narrative (Mail)");
			WebElement Narrative11  = locateElement("id","CUST_NARR_TAG_79");
			type(Narrative11,"Test1");
			log1211.info(Narrative11.getAttribute("value"));
		    
				Set<String> window = driver.getWindowHandles(); 
				Iterator<String> itererator2 = window.iterator();
				String mainWin = itererator2.next();
				String newAdwin = itererator2.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='4']/td[2]/a")).click();
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
//Frame		        
			switchToFramest("work");
			driver.switchTo().frame("frame.AdivceForBankCust");
//ID
			Logger ID2 = Logger.getLogger("ID");
			WebElement Narrative1111 = locateElement("name", "SEND_TO_CUST_ID");
			ID2.info(Narrative1111.getAttribute("value"));
//Name
			Logger ID3 = Logger.getLogger("Name");
			WebElement Name1 = locateElement("name", "SEND_TO_CUST_NM");
			ID3.info(Name1.getAttribute("value"));
//Address
			Logger ID111 = Logger.getLogger("Name");
			WebElement Address1 = locateElement("name", "SEND_TO_CUST_ADD1");
			ID111.info(Address1.getAttribute("value"));
//Save Button	 	  
		   switchToFramest("work");
		   driver.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();
		   driver.findElement(By.xpath("//*[@id='AdivceForBankCustClose']")).click();
	}
	public void EXCOCatalog() throws IOException, InterruptedException {
		switchToFramest("work");
		Logger log7 = Logger.getLogger("Reference Number");
		FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\Ref No\\EXCO.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = workbook1.getSheet("Reference Number");
		System.out.println(workbook1);
		Row row = sheet1.getRow(6);
		Cell cell = row.getCell(4);
		String LCNO = cell.getStringCellValue();
		WebElement san = driver.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		//san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		san.sendKeys(LCNO);
		log7.info(san.getAttribute("value"));
//confirm button					
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//switch to frame2
		driver.switchTo().frame("eeToolbar"); 
		driver.findElement(By.xpath("//*[@id='_next']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='_next']")).click();
	   
// Frame
	    
		switchToFramest("work");
	}
	
	public void IWGTCatalog() throws IOException, InterruptedException {
		switchToFramest("work");
		Logger log7 = Logger.getLogger("Reference Number");
		FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\Ref No\\IWGT.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = workbook1.getSheet("Reference Number");
		System.out.println(workbook1);
		Row row = sheet1.getRow(6);
		Cell cell = row.getCell(4);
		String LCNO = cell.getStringCellValue();
		WebElement san = driver.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		//san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		san.sendKeys(LCNO);
		log7.info(san.getAttribute("value"));
//confirm button					
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//switch to frame2
		driver.switchTo().frame("eeToolbar"); 
		driver.findElement(By.xpath("//*[@id='_next']")).click();
		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='_next']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// Frame
		switchToFramest("work");
	}
	
	public void Catalog() throws IOException, InterruptedException {
		switchToFramest("work");
		Logger log7 = Logger.getLogger("Reference Number");
		FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\IMCO.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = workbook1.getSheet("Reference Number");
		System.out.println(workbook1);
		Row row = sheet1.getRow(6);
		Cell cell = row.getCell(4);
		String LCNO = cell.getStringCellValue();
		WebElement san = driver.findElement (By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		san.sendKeys(LCNO);
		log7.info(san.getAttribute("value"));
//confirm button					
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//switch to frame2
		driver.switchTo().frame("eeToolbar"); 
		driver.findElement(By.xpath("//*[@id='_next']")).click();
		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='_next']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
// Frame
	    
		switchToFramest("work");
	}
	public void IWGTSupervisorRelease() throws InterruptedException, IOException {
		Thread.sleep(2000);
		//switchToFrame(3);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FunctionList");
		Thread.sleep(1000);
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IWGT Maintenance");
		click(Function);
		log5.info("IWGT Maintenance");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300290F05030702120");
		click(FunctionGroup);
		log6.info("Supervisor release");
// Frame
		switchToFramest("work");
//Catalog 
		
		IWGTCatalog();
// Frame
		switchToFramest("work");
//Release
		WebElement Release = locateElement("name", "transaction");
		click(Release);
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("eeToolbar");
//Confirm
		Logger log26 = Logger.getLogger("Confirm");
		WebElement Confirm = locateElement("name", "_confirm");
		click(Confirm);
		log26.info("SupervisorRelease");
//cancel		
		WebElement cancel = locateElement("name", "_cancel");
		click(cancel);
//log off	
// Frame
		Thread.sleep(3000);
		switchToFramest("work");
		Logger log25 = Logger.getLogger("End");
		driver.findElement(By.xpath("//*[@id='ext-gen6']/table[1]/tbody/tr/td/span[1]")).click();
		log25.info("Log off");
		driver.quit();
		System.out.println("********Transaction compleled *******");
	}
	
	public void EXCOSupervisorRelease() throws InterruptedException, IOException {
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("FunctionList");
		Thread.sleep(1000);
		//driver.findElement(By.xpath(".//*[@name='IMCO Registration']")).click();
		Logger log23 = Logger.getLogger("Function");
		Thread.sleep(1000);
		driver.findElement(By.name("EXCO Maintenance")).click();
		log23.info("EPLC Maintenance");
		Logger log24 = Logger.getLogger("Function Group");
		driver.findElement(By.xpath(".//*[@name='G49082300223F05030701684']")).click();
		log24.info("Supervisor Release");
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		// switch to frame2
		driver.switchTo().frame("work");
		//switchToFramest("work");
		FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\Ref No\\EXCO.xlsx");
		// FileInputStream fis = new FileInputStream("E:\\Testing\\sss.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = workbook1.getSheet("Reference Number");
		System.out.println(workbook1);
		Row row = sheet1.getRow(6);
		Cell cell = row.getCell(4);
		String LCNO = cell.getStringCellValue();
		Logger log101 = Logger.getLogger("Confirm");
		WebElement san = driver.findElement(By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		// san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		san.sendKeys(LCNO);
		log101.info(LCNO);
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("eeToolbar");
		driver.findElement(By.xpath("//*[@id='_next']")).click();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		// switch to frame2
		driver.switchTo().frame("work");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='transaction']")).click();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("eeToolbar");
		Thread.sleep(3000);
		Logger log26 = Logger.getLogger("Confirm");
		driver.findElement(By.xpath("//*[@name='_confirm']")).click();
		log26.info("SupervisorRelease");
		Thread.sleep(2000);
		// Screenshot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src, new File("E:/Testing/ADIB/sankar/Release.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// cancel
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='_cancel']")).click();
		Thread.sleep(1000);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("work");
		Thread.sleep(3000);
		Logger log25 = Logger.getLogger("End");
		driver.findElement(By.xpath("//*[@id='ext-gen6']/table[1]/tbody/tr/td/span[1]")).click();
		log25.info("Log off");
		Thread.sleep(1000);
		driver.quit();
		System.out.println("********Transaction compleled *******");
	}
	
	public void IMCOCatalog() throws IOException, InterruptedException {
// Frame
		switchToFramest("work");
		Logger log7 = Logger.getLogger("Reference Number");
		FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\IMCO.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = workbook1.getSheet("Reference Number");
		System.out.println(workbook1);
		Row row = sheet1.getRow(3);
		Cell cell = row.getCell(4);
		String LCNO = cell.getStringCellValue();
		WebElement san = driver.findElement(By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		san.sendKeys(LCNO);
		log7.info(LCNO);
//confirm button					
		Thread.sleep(2000);
		switchToFramest("eeToolbar");
		driver.findElement(By.xpath("//*[@id='_next']")).click();
		/*try {
			Thread.sleep(9000);
			driver.findElement(By.xpath("//*[@id='_next']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
// Frame
		switchToFramest("work");
	}
	public void IMCO_SupervisorRelease() throws InterruptedException, IOException, ATUTestRecorderException, ParseException {
		
		Thread.sleep(5000);
		switchToFramest("FunctionList");
		Thread.sleep(1000);
		Logger log23 = Logger.getLogger("Function");
		Thread.sleep(1000);
		driver.findElement(By.name("IMCO Maintenance")).click();
		Thread.sleep(1000);
		log23.info("IMCO Maintenance");
		Logger log24 = Logger.getLogger("Function Group");
		driver.findElement(By.xpath(".//*[@name='G49082300291F05030701640']")).click();
		log24.info("Supervisor Release");
		Thread.sleep(1000);
		
//Catalog 
		
		IMCOCatalog();
		
// Excel Sheet get Value
		
		FileInputStream MAIN_REF_NO = new FileInputStream("E:\\Testing\\IMCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(MAIN_REF_NO);
		//XSSFSheet sheet1 = workbook.getSheet("Collection");
		XSSFSheet sheet = workbook.getSheet("SameFile");
		workbook.close();
		
// Event Type 	
		Thread.sleep(1000);
		WebElement Event_Type = locateElement("name", "_F_C_FUNC_SHORT_NAME");
		String Create_Coll = Event_Type.getText();
		Thread.sleep(1000);
		
//NormarRelease
		WebElement Release = locateElement("name", "transaction");
		click(Release);
		Thread.sleep(1000);
		
		
		Row row = sheet.getRow(23);
		Cell cell = row.getCell(2);
		String NormarRelease = getCellValueAsString(cell);
		switch(NormarRelease){
		case"Release":
//Confirm
			Confirm();
			break;
		case"Reject":
			
			Thread.sleep(1000);
			WebElement Reject = locateElement("xpath", "/html/body/form/table/tbody/tr[5]/td[2]/input");
			click(Reject);
			Confirm();
//Transaction type 
			
			if(Create_Coll.equals("Create_Coll"));{
				Thread.sleep(2000);
				//Frame
				switchToFramest("FunctionList"); 
				Logger log6 = Logger.getLogger("Function Group");
				Thread.sleep(1000);
				WebElement FunctionGroup = locateElement("name", "G49082300226F05030701642");
				click(FunctionGroup);
				log6.info("Create Collection");
				
				Thread.sleep(2000);
				switchToFramest("work");
				Thread.sleep(1000);
				FileInputStream MAIN_REF_NO1 = new FileInputStream("E:\\Testing\\Baseline\\IMCO.xlsx");
				XSSFWorkbook workbook1 = new XSSFWorkbook(MAIN_REF_NO1);
				XSSFSheet sheet1 = workbook1.getSheet("Reference Number");
				System.out.println(workbook1);
				Row row1 = sheet1.getRow(3);
				Cell cell1 = row1.getCell(4); 
				String LCNO = cell1.getStringCellValue();
				
				Thread.sleep(3000);
				Logger log16 = Logger.getLogger("Reference Number");
				WebElement C_MAIN_REF = locateElement("name", "C_MAIN_REF");
				String Main_REF = C_MAIN_REF.getAttribute("value");
				if(Main_REF.equals(LCNO)){
					log16 .info(" Reject Reference Number and after create collection generated new reference number both are same  " + Main_REF);
				}
				else{
					log16 .error(" Reject Reference Number and after create collection generated new reference number both are not same  " + Main_REF);
				}
			}
			
			break;
		case"Refuse":
			
			Thread.sleep(1000);
			WebElement Refuse = locateElement("xpath", "/html/body/form/table/tbody/tr[6]/td[2]/input");
			click(Refuse);
			Thread.sleep(1000);
			WebElement C_REFUSE_REASON = locateElement("name", "C_REFUSE_REASON");
			type(C_REFUSE_REASON, "Test");
			Confirm();
			
//Fix Rejected Transaction			
			//Frame
			Thread.sleep(2000);
			switchToFramest("FunctionList"); 
			Logger log = Logger.getLogger("Function Group");
			driver.findElement(By.xpath(".//*[@name='G49082300291F05030703396']")).click();
			log.info("Fix Rejected Transaction");
			Thread.sleep(1000);
//Catalog 
			
			IMCOCatalog();
			
// Event Type 	
			Thread.sleep(1000);
			Logger log1 = Logger.getLogger("Fix Rejected Transaction");
			WebElement FUNC_SHORT_NAME = locateElement("name", "_F_C_FUNC_SHORT_NAME");
			String _F_C_FUNC_SHORT_NAME = FUNC_SHORT_NAME.getText();
			Thread.sleep(1000);
			if(_F_C_FUNC_SHORT_NAME.equals(Create_Coll)){
				log1.info("Fix Rejected Transaction is available ");
			}
			else{
				
				log1.error("Fix Rejected Transaction is not available ");
			}
			break;
			
		}
		
		System.out.println("******** Transaction compleled ********");
	}
	
	public void Confirm() throws InterruptedException {
//confirmation button 
		Thread.sleep(1000);
		switchToFramest("eeToolbar");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='_confirm']")).click();
		acceptAlert();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='_cancel']")).click();
		Thread.sleep(3000);
	}
	
	public void Diary() throws IOException, InterruptedException {
//Diary
		FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("SameFile");
		workbook.close();
		
		Row row = sheet.getRow(9);
		Cell cell = row.getCell(2);
		String invalid = getCellValueAsString(cell);
//Narrative			
		Logger Due_Date = Logger.getLogger("Diary Due Date");
		Date Current_date = new Date(); //Current date 
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd"); // Date Format
		WebElement Narrative = locateElement("name", "DIARY_NARRATIVE");
		WebElement DIARY_DT = locateElement("id","DIARY_DT");
		WebElement DIARY_RELATED_REF = locateElement("name","DIARY_RELATED_REF");
		//invalid = (invalid != null ? invalid : "");
		type(Narrative, invalid);
		driver.findElement(By.name("DIARY_RELATED_REF")).click(); //OnClick
		Logger(Narrative);
		Thread.sleep(1000);
		
		if (invalid == null || invalid.isEmpty())
		{
			color(Narrative, Optional);
			color(DIARY_DT, Optional);
			color(DIARY_RELATED_REF, Optional);
		}
		else
		{
			String date13 = formatter1.format(Current_date);
			Thread.sleep(1000);
			String san5 = DIARY_DT.getAttribute("value");
			if (date13.equals(san5)){
				Due_Date.info("The Diary Due Date should be always current date:" + san5);
				color(DIARY_DT,Mandatory);
			}
			else{
				Due_Date.error("The Diary Due Date should not current date:" + san5);
			}
		}
		
//Diary Due Date	
		
		Row row1 = sheet.getRow(9);
		Cell cell1 = row1.getCell(4);
	    CellType type = cell1.getCellTypeEnum();
		if (type == CellType.NUMERIC) {
			Date numberAsString1 = cell1.getDateCellValue();
			if (numberAsString1.before(Current_date)) {
				driver.findElement(By.id("DIARY_DT")).clear();
				type(DIARY_DT,formatter1.format(numberAsString1));
				Due_Date.error("The Remittance Date is not allowed in before! " + formatter1.format(numberAsString1));

			} else {
				type(DIARY_DT, formatter1.format(numberAsString1));
				Due_Date.info(DIARY_DT.getAttribute("value"));
			}
			Thread.sleep(1000);
			driver.findElement(By.name("DIARY_RELATED_REF")).click();
			String remi1 = driver.findElement(By.name("DIARY_DT")).getAttribute("value");
			acceptAlert();	
			if (formatter1.format(numberAsString1).equals(remi1)) {
				Due_Date.info("Remittance Date Format YYYY-MM-DD " + remi1);
			} else {
				Due_Date.error("[W4423] Remittance Date format is error, please use YYYY-MM-DD." + remi1);
			}
		}
		if (type == CellType.STRING) {
			String date = cell1.getStringCellValue();	
			driver.findElement(By.name("DIARY_DT")).sendKeys(date);
			String remi = driver.findElement(By.name("DIARY_DT")).getAttribute("value");
			driver.findElement(By.name("DIARY_RELATED_REF")).click();
			if (remi.equals(date)) {
				Due_Date.error("The Remittance date fields String accept:" + remi);
			} else {
				Due_Date.info("The Remittance Date fields String value not accepting:" + date);
			}
		}

//Related Reference
		Logger log0511 = Logger.getLogger("Diary - Related Reference");
		Row row11 = sheet.getRow(9);
		Cell cell11 = row11.getCell(6);
		String Related_Reference = getCellValueAsString(cell11);
		type(DIARY_RELATED_REF, Related_Reference);
		driver.findElement(By.name("DIARY_NARRATIVE")).click();
		String RELATED_REF = DIARY_DT.getAttribute("value");
		if(RELATED_REF.length() >16){
			log0511.error("The Related Reference field accept more than 16 character "+DIARY_RELATED_REF.getAttribute("value"));
		}
	}
//Charges
		public void Charges() throws IOException {
			FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("SameFile");
			workbook.close();
// Paid By
		Logger log0611 = Logger.getLogger("Paid By");
		Row row0611 = sheet.getRow(7);
		Cell cell0611 = row0611.getCell(4);
		String Paid = cell0611.getStringCellValue();
		WebElement Method1111 = locateElement("name", "CHG_FLD_ALL_CHARGE_FOR");
		selectDropDownUsingText(Method1111, Paid);
		log0611.info(Method1111.getAttribute("value"));
// PaidAt
		Logger log07 = Logger.getLogger("Paid At");
		Row row07 = sheet.getRow(7);
		Cell cell07 = row07.getCell(2);
		String PaidAt = cell07.getStringCellValue();
		switch (PaidAt) {
		case "TRANSACTION":
			WebElement Method3 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method3, PaidAt);
			log07.info(Method3.getAttribute("value"));
			Logger log09 = Logger.getLogger("Paid At");
			WebElement Method6 = locateElement("name", "CHG_FLD_LOCAL_CUST_AC_NO");
			type(Method6, "763915060");
			log09.info(Method6.getAttribute("value"));
			/*
			 * WebElement Method5 = locateElement("name","CHG_GETAC_BTN");
			 * click(Method5); Set<String> windowId = driver.getWindowHandles();
			 * Iterator<String> itererator = windowId.iterator(); String
			 * mainWinID = itererator.next(); String newAdwinID =
			 * itererator.next(); driver.switchTo().window(newAdwinID);
			 * System.out.println(driver.getTitle());
			 * driver.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
			 * driver.switchTo().window(mainWinID);
			 * System.out.println(driver.getTitle()); switchToFramest("work");
			 */
			break;
		case "DEFERRED":
		case "WAIVED":
			WebElement Method31 = locateElement("name", "CHG_FLD_ALL_CHARGE_AT");
			selectDropDownUsingText(Method31, PaidAt);
			log07.info(Method31.getAttribute("value"));
			break;
		}
	}
		public void Payment() throws IOException {
//Excel Sheet get Value
			FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Prepayment");
			workbook.close();
			// Payment Debit
			WebElement Debit = locateElement("id", "do_PaymentDebitHeader_Tab");
			click(Debit);
	// add
			WebElement add = locateElement("id", "PaymentDebit_ADD");
			click(add);
			acceptAlert();
	//Debit Value Date
			Logger log81 = Logger.getLogger("Debit Value Date");
			Row row1111 = sheet.getRow(3);
			Cell cell1111 = row1111.getCell(6);
			SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
			Date numberAsString11 = cell1111.getDateCellValue();
			WebElement ele11 = locateElement("name", "CPYT_DR_VAL_DATE");
			type(ele11, formatter11.format(numberAsString11));
			log81.info(ele11.getAttribute("value"));
			acceptAlert();
	// Account Type
			Logger log10 = Logger.getLogger("Account Type");
			Row row11 = sheet.getRow(5);
			Cell cell11 = row11.getCell(2);
			String Role = cell11.getStringCellValue();
			WebElement Account = locateElement("name", "CPYT_DR_AC_TYPE");
			selectDropDownUsingText(Account, Role);
			log10.info(Account.getAttribute("value"));
	// Account Owner ID
			Row row11111 = sheet.getRow(5);
			Cell cell11111 = row11111.getCell(4);
			int Amount11 = (int) cell11111.getNumericCellValue();
			if ((Integer.toString(Amount11) == null) || (Amount11 == 0)) {
				WebElement Our1 = locateElement("name", "CPYT_DR_AC_OWNER_BTN");
				click(Our1);
				Set<String> window = driver.getWindowHandles();
				Iterator<String> itererator2 = window.iterator();
				String mainWin = itererator2.next();
				String newAdwin = itererator2.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
				switchToFramest("work");
			} else {
	// Account Owner ID
				Logger log1011 = Logger.getLogger("Account Owner ID");
				WebElement Our11 = locateElement("name", "CPYT_DR_ID");
				type(Our11, Integer.toString(Amount11));
				log1011.info(Our11.getAttribute("value"));
	// Account No.
				Logger log10111 = Logger.getLogger("Send Amendment by");
				Row row1111111 = sheet.getRow(5);
				Cell cell5 = row1111111.getCell(6);
				int Amount1111 = (int) cell5.getNumericCellValue();
				WebElement Our111 = locateElement("name", "CPYT_DR_AC");
				type(Our111, Integer.toString(Amount1111));
				log10111.info(Our111.getAttribute("value"));
			}
	// Payment Save Button
			WebElement Save = locateElement("id", "PaymentDebit_SAVE");
			click(Save);
	// Payment Credit
			WebElement Credit = locateElement("id", "do_PaymentCreditHeader_Tab");
			click(Credit);
	// Credit Add
			WebElement CreditAdd = locateElement("id", "PaymentCredit_ADD");
			click(CreditAdd);
	// Credit Value Date
			Logger log811 = Logger.getLogger("Debit Value Date");
			Row row111111 = sheet.getRow(9);
			Cell cell56 = row111111.getCell(2);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date numberAsString111 = cell56.getDateCellValue();
			WebElement ele111 = locateElement("name", "CPYT_CR_VAL_DATE");
			type(ele111, format.format(numberAsString111));
			log811.info(ele111.getAttribute("value"));
			acceptAlert();
	// Account Type 
			Logger log101 = Logger.getLogger("Account Type");
			Row row116 = sheet.getRow(7);
			Cell cell116 = row116.getCell(2);
			String Role1 = cell116.getStringCellValue();
			WebElement Account1 = locateElement("name", "CPYT_CR_AC_TYPE");
			selectDropDownUsingText(Account1, Role1);
			log101.info(Account1.getAttribute("value"));
	// Account Owner ID
			Row row113 = sheet.getRow(7);
			Cell cell3 = row113.getCell(4);
			int Amount = (int) cell3.getNumericCellValue();
			if ((Integer.toString(Amount) == null) || (Amount == 0)) {
				WebElement Our1 = locateElement("name", "CPYT_ASSGN_ID_BTN");
				click(Our1);
				Set<String> window = driver.getWindowHandles();
				Iterator<String> itererator2 = window.iterator();
				String mainWin = itererator2.next();
				String newAdwin = itererator2.next();
				driver.switchTo().window(newAdwin);
				System.out.println(driver.getTitle());
				driver.findElement(By.xpath("//*[@id='2']/td[2]/a")).click();
				driver.switchTo().window(mainWin);
				System.out.println(driver.getTitle());
				switchToFramest("work");
			} else {
	// Account Owner ID
				Logger log1011 = Logger.getLogger("Account Owner ID");
				WebElement Our11 = locateElement("name", "CPYT_ASSGN_ID");
				type(Our11, Integer.toString(Amount));
				log1011.info(Our11.getAttribute("value"));
	// Account No.
				Logger log10111 = Logger.getLogger("Send Amendment by");
				Row row1111111 = sheet.getRow(7);
				Cell cell5 = row1111111.getCell(6);
				int Amount1111 = (int) cell5.getNumericCellValue();
				WebElement Our111 = locateElement("name", "CPYT_CR_AC");
				type(Our111, Integer.toString(Amount1111));
				log10111.info(Our111.getAttribute("value"));
			}
//Payment Advice Message			
				Logger log9911 = Logger.getLogger("Payment Advice Message");
				Row row2611 = sheet.getRow(9);
				Cell cell2611 = row2611.getCell(4);
				String Mail1 = cell2611.getStringCellValue();
				switch(Mail1){
				case "None":
				case "Fedwire":
				case "Mail":
				WebElement Method1 = locateElement("name","CPYT_PAY_ADV_MSG");
				selectDropDownUsingText(Method1,Mail1);
				log9911.info(Method1.getAttribute("value"));
				break;
				case "MT103":
					WebElement Method11 = locateElement("name","CPYT_PAY_ADV_MSG");
					selectDropDownUsingText(Method11,Mail1);
					log9911.info(Method11.getAttribute("value"));
//103 Swift message				
					WebElement Swift = locateElement("id","do_PaymentMT103_Tab");
					click(Swift);
					WebElement ID = locateElement("name","X103_ADV_BKID_B2");
					type(ID,"ABNADEHHCGN");
//Ordering Customer [50]				
					WebElement Ordering = locateElement("name","X103_ORDCU_ID_50A");
					type(Ordering,"BNLIITRRCNX");
//Beneficiary Customer
					WebElement Beneficiary = locateElement("name","X103_BENECU_ID_59A");
					type(Beneficiary,"C000087");
//Sender Charges[71F]	
					Logger log8111 = Logger.getLogger("	Sender Charges[71F]	");
					Row row211 = sheet.getRow(11);
					Cell cell211 = row211.getCell(2);
					double Amount1 = cell211.getNumericCellValue();
					WebElement ele = locateElement("name", "X103_SENDCHGAMT71F");
					click(ele);
					WebElement ele811 = locateElement("name", "X103_SENDCHGAMT71F");
					type(ele811, Double.toString(Amount1));
					log8111.info(ele811.getAttribute("value"));
	//main			
					WebElement main = locateElement("id", "do_PaymentCredit_M_Tab");
					click(main);
					break;
				case "MT400":
					WebElement Method111 = locateElement("name","CPYT_PAY_ADV_MSG");
					selectDropDownUsingText(Method111,Mail1);
					log9911.info(Method111.getAttribute("value"));
	//400 Swift message				
					WebElement Swift1 = locateElement("id","do_PaymentMT400_Tab");
					click(Swift1);
					WebElement message1 = locateElement("name","X400_ADV_BK_ID_BTN");
					click(message1);
					
					WebElement main1 = locateElement("id", "do_PaymentCredit_M_Tab");
					click(main1);
					break;
				}
				
	//Payment Cover Message			
				Logger log06 = Logger.getLogger("Payment Cover Message	");
				Row row06 = sheet.getRow(9);
				Cell cell06 = row06.getCell(6);
				String Ship = cell06.getStringCellValue();
				switch(Ship){
				case "None":
				case "Mail":
					WebElement Method11 = locateElement("name","CPYT_PAY_COV_MSG");
					selectDropDownUsingText(Method11,Ship);
					log06.info(Method11.getAttribute("value"));
					break;
				case "MT202":
				case "MT202COV":
					acceptAlert();
					WebElement Method111 = locateElement("name","CPYT_PAY_COV_MSG");
					selectDropDownUsingText(Method111,Ship);
					log06.info(Method111.getAttribute("value"));
	//PaymentMT202 
					WebElement main = locateElement("id", "do_PaymentMT202_Tab");
					click(main);
	//Receiver Bank[B2]				
					WebElement ID = locateElement("name","X202_ADV_BKID_B2");
					type(ID,"ABNADEHHCGN");
	//Beneficiary Institution [58]				
					WebElement Ordering = locateElement("name","X202_BENE_BKID_58A");
					type(Ordering,"BDGLCH22XXX");
	//Beneficiary Customer
					break;
				}
			
	// Payment Save Button
			WebElement Save1 = locateElement("id", "PaymentCredit_SAVE");
			click(Save1);
}
		public void Notes() throws IOException {
			FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("SameFile");
			workbook.close();
			Row row = sheet.getRow(3);
			Cell cell = row.getCell(6);
			String invalid = cell.getStringCellValue();
//Notes		
			invalid = (invalid != null ? invalid : "");
			Logger log051 = Logger.getLogger("Notes");
			WebElement Narrative = locateElement("name", "NOTES");
			type(Narrative, invalid);
			log051.info(Narrative.getAttribute("value"));
			
		}
		
		public void Copy() {
//Copy and Past
			
			WebElement locOfOrder = locateElement("name", "C_MAIN_REF");
			Actions act = new Actions(driver);
			act.moveToElement(locOfOrder).doubleClick().build().perform();
			// now apply copy command
			Logger log16 = Logger.getLogger("Reference Number");
			WebElement san2 = locateElement("name", "C_MAIN_REF");
			san2.sendKeys(Keys.chord(Keys.CONTROL, "c"));
			log16.info(san2.getAttribute("value"));
		}
		
		/**
	     * This method for the type of data in the cell, extracts the data and
	     * returns it as a string.
	     */
	    public static String getCellValueAsString(Cell cell) {
	        String strCellValue = null;
	        if (cell != null) {
	            switch (cell.getCellType()) {
	            case Cell.CELL_TYPE_STRING:
	                strCellValue = cell.toString();
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    strCellValue = dateFormat.format(cell.getDateCellValue());
	                } else {
	                    Double value = cell.getNumericCellValue();
	                    Long longValue = value.longValue();
	                    strCellValue = new String(longValue.toString());
	                }
	                break;
	            case Cell.CELL_TYPE_BOOLEAN:
	                strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
	                break;
	            case Cell.CELL_TYPE_BLANK:
	                strCellValue = "";
	                break;
	            }
	        }
	        return strCellValue;
	    }
}
