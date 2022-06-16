package Framework;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.WebElement;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public interface WdMethods {

			/**
			 * This method will launch the browser and 
			 * maximise the browser and set the wait for 30 seconds 
			 * and load the url
			 * @author sankar
			 * @param browser - This will load the specified browser
			 * 		 * 
			 */
			public void startApp(String browser, String url) ;

			/**
			 * This method will locate the element using any given locator
			 * @param locator  - The locator by which the element to be found
			 * @param locValue - The locator value by which the element to be found
			 * @author sankar
			 * @throws NoSuchElementException
			 */
			public WebElement locateElement(String locator, String locValue) ;	
			
			/**
			 * This method will locate the element using only id
			 * @param locValue - The locator value by which the element to be found
			 * @author sankar
			 * @throws NoSuchElementException
			 */
			public WebElement locateElement(String locValue1) ;	
			
			/**
			 * This method will enter the value in the given text field 
			 * @param ele   - The Webelement (text field) in which the data to be entered
			 * @param data  - The data to be sent to the webelement
			 * @author sankar
			 * @throws ElementNotVisibleException		 * 
			 */
			public void type(WebElement ele, String data) ;
			
			/**
			 * This method will click the element and take snap
			 * @param ele   - The Webelement (button/link/element) to be clicked
			 * @author sankar
			 */
			public void click(WebElement ele);

			/**
			 * This method will get the text of the element
			 * @param ele   - The Webelement (button/link/element) in which text to be retrieved
			 * @author sankar
			 */
			public String getText(WebElement ele);

			/**
			 * This method will select the drop down visible text
			 * @param ele   - The Webelement (dropdown) to be selected
			 * @param value The value to be selected (visibletext) from the dropdown 
			 * @author sankar
			 */
			public void selectDropDownUsingText(WebElement ele, String value) ;
			
			/**
			 * This method will select the drop down using index
			 * @param ele   - The Webelement (dropdown) to be selected
			 * @param index The index to be selected from the dropdown 
			 * @author sankar
			 */
			public void selectDropDownUsingIndex(WebElement ele, int index) ;

			/**
			 * This method will verify browser actual title with expected
			 * @param title - The expected title of the browser
			 * @author sankar
			 */
			public boolean verifyTitle(String expectedTitle);
			
			/**
			 * This method will verify exact given text with actual text on the given element
			 * @param ele   - The Webelement in which the text to be need to be verified
			 * @param expectedText  - The expected text to be verified
			 * @author sankar
			 */
			public void verifyExactText(WebElement ele, String expectedText);
			
			/**
			 * This method will verify given text contains actual text on the given element
			 * @param ele   - The Webelement in which the text to be need to be verified
			 * @param expectedText  - The expected text to be verified
			 * @author sankar
			 */
			public void verifyPartialText(WebElement ele, String expectedText);

			/**
			 * This method will verify exact given attribute's value with actual value on the given element
			 * @param ele   - The Webelement in which the attribute value to be need to be verified
			 * @param attribute  - The attribute to be checked (like value, href etc)
			 * @param value  - The value of the attribute
			 * @author sankar
			 */
			public void verifyExactAttribute(WebElement ele, String attribute, String value);
			
			/**
			 * This method will verify partial given attribute's value with actual value on the given element
			 * @param ele   - The Webelement in which the attribute value to be need to be verified
			 * @param attribute  - The attribute to be checked (like value, href etc)
			 * @param value  - The value of the attribute
			 * @author sankar
			 */
			public void verifyPartialAttribute(WebElement ele, String attribute, String value);
		
			/**
			 * This method will verify if the element (Radio button, Checkbox)  is selected
			 * @param ele   - The Webelement (Radio button, Checkbox) to be verified
			 * @author sankar
			 */
			public void verifySelected(WebElement ele);
			
			/**
			 * This method will verify if the element is visible in the DOM
			 * @param ele   - The Webelement to be checked
			 * @author sankar
			 */
			public void verifyDisplayed(WebElement ele);
			
			/**
			 * This method will switch to the Window of interest
			 * @param index The window index to be switched to. 0 -> first window 
			 * @author sankar
			 */
			public void switchToWindow(int index);
			
			public void switchToWindowset();
			
			/**
			 * This method will switch to the specific frame
			 * @param ele   - The Webelement (frame) to be switched
			 * @author sankar
			 * @throws InterruptedException 
			 */
			public void switchToFrame(int ele) throws InterruptedException;
			
			/**
			 * This method will switch to the specific frame
			 * @param ele   - The Webelement (frame) to be switched
			 * @author sankar
			 */
			public void switchToFramest(String ele);
	
			/*excel sheet get value */
			
			public void Clear(WebElement toClear1);
			
			
			/**
			 * This method will accept the alert opened
			 * @author sankar
			 */
			public void acceptAlert();
			public void AcceptAlert();
			
			/**
			 * This method will dismiss the alert opened
			 *
			public void dismissAlert();
			
			/**
			 * This method will return the text of the alert
			 * @author sankar
			 */
			public String getAlertText();
			
			/**
			 * This method will take snapshot of the browser
			 * @author sankar
			 */
			public void takeSnap();
				
			/**
			 * This method will close the active browser
			 * @author sankar
			 */
			public void closeBrowser();		
			
			/**
			 * This method will close all the browsers
			 * @author sankar
			 */
			public void closeAllBrowsers();
			
			//login page
			
			public void loginpage() throws InterruptedException;
			//Advice
			public void Advice() throws IOException, InterruptedException;
			
			//catalog
			public void Catalog() throws IOException, InterruptedException;
			
			//EXCOCatalog
			public void EXCOCatalog() throws IOException, InterruptedException;
			
			//IWGTCatalog
			public void IWGTCatalog() throws IOException, InterruptedException;
			
			//IMCOCatalog
			public void IMCOCatalog() throws IOException, InterruptedException;
			
			//IMCOSupervisor Release
			public void IMCO_SupervisorRelease() throws InterruptedException, IOException, ATUTestRecorderException, ParseException;
			
			//EXCOSupervisor Release
			public void EXCOSupervisorRelease() throws InterruptedException, IOException;
			
			//IWGTSupervisor Release
			public void IWGTSupervisorRelease() throws InterruptedException, IOException;
			
			// confirmation button
			public void Confirm() throws InterruptedException;
			
			//Diary
			
			public void Diary() throws IOException, InterruptedException;
			
			// Charges
			public void Charges() throws IOException;
			
			//Payment
			public void Payment() throws IOException;
			//Notes
			public void Notes() throws IOException;
			
			//Copy and Past 
			public void Copy();
			
			// color
			public void color(WebElement ele, String color1);
			
			//isEnabled 
			public void verifyisEnabled(WebElement ele);
			public void verifyisDisable(WebElement ele);
			
			//negative values
			public void Amount(WebElement ele);
			/**
			 * logger function
			 * @author sankar
			 */
			public void Logger(WebElement ele);
			//video Recored
			public void RecoredStart() throws ATUTestRecorderException;
			public void RecoredStop() throws ATUTestRecorderException;
			
			//Mouse Event
			public void MouseEvent();
			
			//Date_After
			public void Date_After(WebElement ele) throws ParseException, InterruptedException;
			
			//Date_Before
			public void Date_Before(WebElement ele) throws ParseException, InterruptedException;
	}






