package EPLCModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
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

public class EPLCRegisterLC extends EPLCsupervisorRelease {
public static WebDriver dr;
	
	public WebDriver GetDriver()
	{
		return dr;
	}
	
@Test(priority = 0)
	public void RegisterLC() throws InterruptedException, IOException {
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
		 public static void Register() throws InterruptedException, IOException{*/
			 FileInputStream fis = new FileInputStream("E:\\Testing\\test.xlsx");
			
			 XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 
			 XSSFSheet sheet = workbook.getSheet("RegLC");
			                      
			 Row row = sheet.getRow(4);
			 Cell cell = row.getCell(2);
			
			 //String cellval = cell.getStringCellValue();
			 System.out.println(workbook);
		 Logger log0 = Logger.getLogger(" Bussiness Unit Code  ");
		 Thread.sleep(1000);
		 dr.findElement(By.xpath(".//*[@name='C_BUSINESS_UNIT']")).sendKeys("CSBANK");
		 log0.info("CSBANK");
		Logger log1 = Logger.getLogger("User ID");
		Thread.sleep(1000);
		 dr.findElement(By.xpath(".//*[@name='C_USER_ID']")).sendKeys("CSBANKOP");
		 log1.info("CSBANKOP");
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
			dr.findElement(By.xpath(".//*[@name='EPLC Advice']")).click();
			log5.info("EPLC Advice");
			Thread.sleep(1000);
			Logger log6 = Logger.getLogger("Function Group");
			dr.findElement(By.xpath(".//*[@name='G49082300265F05030701976']")).click();
			log6.info("Register Letter of Credit");
			Thread.sleep(3000);
			dr.switchTo().defaultContent();
			  
			//switch to frame2
			dr.switchTo().frame("work");
//Main		
//LC Number			
			
			Thread.sleep(1000);
			 Logger log90= Logger.getLogger("LC_AMT");
			 Row row21 = sheet.getRow(3);
			 Cell cell21 = row21.getCell(2);
			 //double LCNumber = cell21.getNumericCellValue();
			 String LCNumber = cell21.getStringCellValue();
			 WebElement san81 =dr.findElement(By.xpath("//*[@id='LC_NO']"));
			 san81.sendKeys(LCNumber);
			 log90.info(san81.getAttribute("value"));
//Message Type
			Thread.sleep(1000);
			Logger log8 = Logger.getLogger("Message Type");
			 String Message = cell.getStringCellValue();
			 System.out.println(Message);
			//String Type = "MT700";
			switch(Message){
			case "MT700" :
				WebElement ele1 =dr.findElement(By.xpath("//*[@id='MESG_TYPE']"));
				ele1.sendKeys("MT700");
				log8.info(ele1.getAttribute("value"));
				break;
			case "MT710" :
				WebElement ele2 =dr.findElement(By.xpath("//*[@id='MESG_TYPE']"));
				ele2.sendKeys("MT710");
				log8.info(ele2.getAttribute("value"));
				Logger log8a = Logger.getLogger(" Sender's Reference");
				Thread.sleep(1000);
				WebElement san =dr.findElement(By.xpath(".//*[@id='SENDER_REF']"));
				san.sendKeys("56453122");
				log8a.info(san.getAttribute("value"));
				Logger log8b = Logger.getLogger(" MT710 / MT720 Form of LC [40B]");
				Thread.sleep(1000);
				WebElement san1 =dr.findElement(By.xpath(".//*[@id='FORM_OF_LC_40B']"));
				san1.sendKeys("ADDING OUR CONFIRMATION");
				log8b.info(san1.getAttribute("value"));
				break;
			case "MT720" :
				WebElement ele3 =dr.findElement(By.xpath("//*[@id='MESG_TYPE']"));
				ele3.sendKeys("MT720");
				log8.info(ele3.getAttribute("value"));
				Logger log9a = Logger.getLogger(" Sender's Reference");
				Thread.sleep(1000);
				WebElement san11 =dr.findElement(By.xpath(".//*[@id='SENDER_REF']"));
				san11.sendKeys("3122");
				log9a.info(san11.getAttribute("value"));
				Logger log9b = Logger.getLogger(" MT710 / MT720 Form of LC [40B]");
				Thread.sleep(1000);
				WebElement san111 =dr.findElement(By.xpath(".//*[@id='FORM_OF_LC_40B']"));
				san111.sendKeys("ADDING OUR CONFIRMATION");
				log9b.info(san111.getAttribute("value"));
				break;
			}
			Thread.sleep(1000);
			Logger log9 = Logger.getLogger("Date of Expiry [31D]");
			 Row row1 = sheet.getRow(3);
			 Cell cell1 = row1.getCell(6	);
			 //double date = cell.getNumericCellValue();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 Date numberAsString = cell1.getDateCellValue();
			 WebElement ele =dr.findElement(By.xpath(".//*[@id='EXPIRY_DT']"));
			 ele.sendKeys(formatter.format(numberAsString));
			 log9.info(ele.getAttribute("value"));
			 Thread.sleep(1000);
			
			Logger log10 = Logger.getLogger("Place of Expiry [31D]");
			WebElement ele5 =dr.findElement(By.xpath(".//*[@id='EXPIRY_PLC_NARR']"));
			ele5.sendKeys("Chennai");
			log10.info(ele5.getAttribute("value"));
			Thread.sleep(1000);
//Confirmation Instruction [49]	
			Logger log11 = Logger.getLogger("Confirmation Instruction [49]");
			Thread.sleep(1000);
//Excel Sheet Value 			
			 Row row11 = sheet.getRow(14);
			 Cell cell11 = row11.getCell(2);
			 String Confirmation = cell11.getStringCellValue();
			//String Confirmation  = "CONFIRM";
			switch (Confirmation){
			case "CONFIRM" :
				WebElement ele6 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
				ele6.sendKeys("CONFIRM");
				log11.info(ele6.getAttribute("value"));
				break;
			case "MAY ADD":
				WebElement ele7 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
				ele7.sendKeys("MAY ADD");
				log11.info(ele7.getAttribute("value"));
				break;
			case "WITHOUT" :
				WebElement ele8 =dr.findElement(By.xpath(".//*[@id='CONF_INSTR']"));
				ele8.sendKeys("WITHOUT");
				log11.info(ele8.getAttribute("value"));
				break;
			}
			
//Form of LC[40A]	
					Thread.sleep(1000);
					Logger log12 = Logger.getLogger("Form of LC[40A]");
					Row row2 = sheet.getRow(7);
					 Cell cell2 = row2.getCell(2);
					 String FormOfLC = cell2.getStringCellValue();
					//String FormOfLC = "IRREVOCABLE";
					switch(FormOfLC){
					case "IRREVOCABLE":
						Thread.sleep(1000);
						Select Country = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country.selectByVisibleText("IRREVOCABLE");
					
						//dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")).sendKeys("IRREVOCABLE");
						log12.info("IRREVOCABLE");
						break;
					case "REVOCABLE":
						Thread.sleep(1000);
						Select Country1 = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country1.selectByVisibleText("REVOCABLE");
						log12.info("REVOCABLE");
						break;
					case "IRREVOCABLE TRANSFERABLE":
						Thread.sleep(1000);
						Select Country2 = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country2.selectByVisibleText("IRREVOCABLE TRANSFERABLE");
						log12.info("IRREVOCABLE TRANSFERABLE");
						break;
					case "REVOCABLE TRANSFERABLE":
						Thread.sleep(1000);
						Select Country3 = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country3.selectByVisibleText("REVOCABLE TRANSFERABLE");
						log12.info("REVOCABLE TRANSFERABLE");
						break;
					case "IRREVOCABLE STANDBY":
						Thread.sleep(1000);
						Select Country4 = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country4.selectByVisibleText("IRREVOCABLE STANDBY");
						log12.info("IRREVOCABLE STANDBY");
						break;
					case "REVOCABLE STANDBY":
						Thread.sleep(1000);
						Select Country5 = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country5.selectByVisibleText("REVOCABLE STANDBY");
						log12.info("REVOCABLE STANDBY");
						break;
					case "IRREVOC TRANS STANDBY":
						Thread.sleep(1000);
						Select Country6 = new Select (dr.findElement(By.xpath("//*[@id='FORM_OF_LC']")));
						 
						 Country6.selectByVisibleText("IRREVOC TRANS STANDBY");
						log12.info("IRREVOC TRANS STANDBY");
						break;
					}
//Revolving Information
					Thread.sleep(1000);
					Logger log13 = Logger.getLogger("Revolving Information");
//Excel sheet get value 					
					Row row3 = sheet.getRow(7);
					 Cell cell3 = row3.getCell(4);
					 String Revolving = cell3.getStringCellValue();
					//String Revolving  =  "YES";
					switch(Revolving){
					case "YES" :
						
				        Thread.sleep(1000);
						WebElement ele51 =dr.findElement(By.xpath(".//*[@id='REV_LC']"));
						ele51.sendKeys("YES");
						 Thread.sleep(1000);
						try {
							Alert alert = dr.switchTo().alert();	
							alert.accept();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}		
						log13.info(ele51.getAttribute("value"));
						   Thread.sleep(2000);									       
				    	Logger log13a = Logger.getLogger("Cumulative");
//Excel sheet get value 					
						Row row4 = sheet.getRow(14);
						 Cell cell4 = row4.getCell(4);
						 String Cumulative = cell4.getStringCellValue();
				        //String Cumulative = "Cumulative";
				        switch(Cumulative){
				        case "Cumulative":
				        	Thread.sleep(1000);
							WebElement ele511 =dr.findElement(By.xpath(".//*[@id='CUMULATIVE']"));
							ele511.sendKeys("Cumulative");
							log13a.info(ele511.getAttribute("value"));
							Thread.sleep(1000);
							Logger log13b = Logger.getLogger(" Auto Renewal?");
//Excel sheet get value 					
							Row row5 = sheet.getRow(14);
							 Cell cell5 = row5.getCell(6);
							 String Renewal = cell5.getStringCellValue();
							//String Renewal = "YES";
							switch(Renewal){
							case "YES":
					        	Thread.sleep(1000);
								WebElement ele5111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
								ele5111.sendKeys("YES");
								log13b.info(ele5111.getAttribute("value"));
								break;
							case "NO":
					        	Thread.sleep(1000);
								WebElement ele51111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
								ele51111.sendKeys("NO");
								log13b.info(ele51111.getAttribute("value"));
								break;
							}
// No of Times Revolving
							Thread.sleep(1000);
							Logger log13c = Logger.getLogger("No of Times Revolving");
							
							WebElement toClear = dr.findElement(By.xpath(".//*[@id='NO_PRD']"));
							toClear.sendKeys(Keys.CONTROL + "a");
							toClear.sendKeys(Keys.DELETE);
							WebElement Times = dr.findElement(By.xpath(".//*[@id='NO_PRD']"));
							Times.sendKeys("10");
							log13c.info(Times.getAttribute("value"));
							break;
				        case "Non Cumulative":
				        	Thread.sleep(1000);
							WebElement ele5111 =dr.findElement(By.xpath(".//*[@id='CUMULATIVE']"));
							ele5111.sendKeys("Non Cumulative");
							log13a.info(ele5111.getAttribute("value"));
							Thread.sleep(1000);
							Logger log13b1 = Logger.getLogger(" Auto Renewal?");
							String Renewal1 = "YES";
							switch(Renewal1){
							case "YES":
					        	Thread.sleep(1000);
								WebElement ele51111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
								ele51111.sendKeys("YES");
								log13b1.info(ele51111.getAttribute("value"));
								break;
							case "NO":
					        	Thread.sleep(1000);
								WebElement ele511111 =dr.findElement(By.xpath(".//*[@id='EVERGREEN']"));
								ele511111.sendKeys("NO");
								log13b1.info(ele511111.getAttribute("value"));
								break;
							}
// No of Times Revolving
							Thread.sleep(1000);
							Logger log13c1 = Logger.getLogger("No of Times Revolving");
							dr.findElement(By.xpath(".//*[@id='NO_PRD']")).clear();
							dr.findElement(By.xpath(".//*[@id='NO_PRD']")).click();
							WebElement Times1 = dr.findElement(By.xpath(".//*[@id='NO_PRD']"));
							Times1.sendKeys("10");
							log13c1.info(Times1.getAttribute("value"));
							break;
							
				        }
						break;
					case "NO" :
						Thread.sleep(1000);
						WebElement ele511 =dr.findElement(By.xpath(".//*[@id='REV_LC']"));
						ele511.sendKeys("NO");
						log13.info(ele511.getAttribute("value"));
						break;
					
					}
//LC Currency and Amount [32B]
					Thread.sleep(1000);
//Excel sheet get value 					
					Row row4 = sheet.getRow(10);
					 Cell cell4 = row4.getCell(4);
					 String LCAMT = cell4.getStringCellValue();
					//String LCAMT = "AED";
					switch (LCAMT){
					case "USD":
						Thread.sleep(1000);
						Logger log101 = Logger.getLogger("LC_CCY");
						dr.findElement(By.xpath(".//*[@id='A_div']/table[2]/tbody/tr[12]/td[2]/select")).sendKeys("USD");
						log101.info("USD");
						break;
					case "AED":
						Thread.sleep(1000);
						Logger log111 = Logger.getLogger("LC_CCY");
						dr.findElement(By.xpath(".//*[@id='A_div']/table[2]/tbody/tr[12]/td[2]/select")).sendKeys("AED");
						log111.info("AED");
						break;
					case "EUR":
						Thread.sleep(1000);
						Logger log121 = Logger.getLogger("LC_CCY");
						dr.findElement(By.xpath(".//*[@id='A_div']/table[2]/tbody/tr[12]/td[2]/select")).sendKeys("EUR");
						log121.info("EUR");
						break;
					case "GBP":
						Thread.sleep(1000);
						Logger log131 = Logger.getLogger("LC_CCY");
						dr.findElement(By.xpath(".//*[@id='A_div']/table[2]/tbody/tr[12]/td[2]/select")).sendKeys("GBP");
						log131.info("GBP");
						break;
					}
					
//Amount					
					/*Thread.sleep(1000);
					dr.findElement(By.xpath(".//*[@id='LC_AMT']")).click();
					WebElement ele511 =dr.findElement(By.xpath(".//*[@id='LC_AMT']"));
					ele511.sendKeys("10000");
					log13.info(ele511.getAttribute("value"));	*/
					
					Thread.sleep(1000);
					 Logger log811 = Logger.getLogger("LC_AMT");
					 Row row211 = sheet.getRow(12);
					 Cell cell211 = row211.getCell(2);
					 double Amount = cell211.getNumericCellValue();
					 dr.findElement(By.xpath(".//*[@id='LC_AMT']")).click();
					 WebElement ele811 =dr.findElement(By.xpath(".//*[@id='LC_AMT']"));
					 ele811.sendKeys(Double.toString(Amount));
					 log811.info(ele811.getAttribute("value"));
					 
//Issue DAte 
					 Logger log14 = Logger.getLogger("Issue Date");
					 Row row111 = sheet.getRow(3);
					 Cell cell111 = row111.getCell(4);
					 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
					 Date numberAsString1 = cell111.getDateCellValue();
					 WebElement ele1 =dr.findElement(By.xpath(".//*[@id='ISSUE_DT']"));
					 ele1.sendKeys(formatter1.format(numberAsString1));
					 log14.info(ele1.getAttribute("value"));
//	Applicable Rules [40E]
					Thread.sleep(1000);
					Logger log15 = Logger.getLogger("Applicable Rules [40E]");
//Excel sheet get value 					
					 Row row5 = sheet.getRow(4);
					 Cell cell5 = row5.getCell(4);
					 String Rules1 = cell5.getStringCellValue();
					//String Rules1 = "UCP LATEST VERSION";
					switch(Rules1){
					case"EUCP LATEST VERSION":	
						WebElement Rules = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
						Rules.sendKeys("EUCP LATEST VERSION");
						log15.info(Rules.getAttribute("value"));
						break;
					case"EUCPURR LATEST VERSION":	
						WebElement Rules11 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
						Rules11.sendKeys("EUCPURR LATEST VERSION");
						log15.info(Rules11.getAttribute("value"));
						break;
					case"ISP LATEST VERSION":	
						WebElement Rules2 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
						Rules2.sendKeys("ISP LATEST VERSION");
						log15.info(Rules2.getAttribute("value"));
						break;
					case"UCP LATEST VERSION":	
						WebElement Rules3 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
						Rules3.sendKeys("UCP LATEST VERSION");
						log15.info(Rules3.getAttribute("value"));
						break;
					case"UCPURR LATEST VERSION":	
						WebElement Rules31 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
						Rules31.sendKeys("UCPURR LATEST VERSION");
						log15.info(Rules31.getAttribute("value"));
						break;
					case"OTHR":	
						WebElement Rules15 = dr.findElement(By.xpath(".//*[@id='APLB_RULE']"));
						Rules15.sendKeys("OTHR");
						log15.info(Rules15.getAttribute("value"));
						Thread.sleep(1000);
						dr.findElement(By.xpath("//*[@id='APLB_RULE_NARR']")).sendKeys("test");
						break;
						
					}
					
//Copy and past
					 WebElement locOfOrder = dr.findElement(By.id("C_MAIN_REF"));
					 Actions act = new Actions(dr);
					 act.moveToElement(locOfOrder).doubleClick().build().perform();
					 // catch here is double click on the text will by default select the text 
					 // now apply copy command 
					 Logger log16 = Logger.getLogger("Reference Number");
					 WebElement san2 = dr.findElement(By.id("C_MAIN_REF"));
					 san2.sendKeys(Keys.chord(Keys.CONTROL,"c"));
					
					 Thread.sleep(2000);
					 
//Excel Sheet Write Value
					 
					 XSSFWorkbook workbook1 = new XSSFWorkbook();
				     XSSFSheet sheet1 = workbook1.createSheet("Datatypes in Java");
				     FileOutputStream outputStream = new FileOutputStream(new File("E:\\Testing\\Baseline\\sankar.xlsx"));
					 System.out.println(san2.getAttribute("value"));
					 
					 sheet1.createRow(6).createCell(4).setCellValue(san2.getAttribute("value"));
					 sheet1.createRow(5).createCell(4).setCellValue("Reference Number :");
						
			         workbook1.write(outputStream);
			         workbook1.close();		
			         log16.info(san2.getAttribute("value"));
				
//Parties
					 dr.findElement(By.xpath(".//*[@id='B']")).click();
					 Logger log161 = Logger.getLogger("Beneficiary/Second Beneficiary [59]");
						dr.findElement(By.xpath(".//*[@id='BENE_ID_BTN']")).click();
						log161.info("BUYER,BUYER,Oxford St,Oxford St");
						Thread.sleep(1000);
				        
						 Set<String> windowId1 = dr.getWindowHandles(); 
						 // get  window id of current window
					        Iterator<String> itererator1 = windowId1.iterator();   

					        String mainWinID1 = itererator1.next();
					        String  newAdwinID1 = itererator1.next();

					        dr.switchTo().window(newAdwinID1);
					        System.out.println(dr.getTitle());
					        dr.findElement(By.xpath(".//*[@id='SEARCH_VALUE']")).sendKeys("BUYER");
					        Thread.sleep(1000);
					        dr.findElement(By.xpath("html/body/form[3]/table/tbody/tr/td[8]/a/b")).click();
					        Thread.sleep(1000);
							dr.findElement(By.xpath(".//*[@id='0']/td[2]/a")).click();//*[@id='1']/td[2]/a
							 Thread.sleep(1000);
					        //dr.close();
					        dr.switchTo().window(mainWinID1);
					        System.out.println(dr.getTitle());
					        Thread.sleep(1000);
					        dr.switchTo().defaultContent();
							  
							//switch to frame2
							dr.switchTo().frame("work");
							Thread.sleep(1000);
							
							Logger log20 = Logger.getLogger("Email Address");
							WebElement Email = dr.findElement(By.xpath(".//*[@id='BENE_EMAIL']"));
							Email.sendKeys("sankar@gmail.com");
							log20.info(Email.getAttribute("value"));
							Logger log21 = Logger.getLogger(" Issuing Bank");
							Thread.sleep(1000);
							WebElement Bank = dr.findElement(By.xpath(".//*[@id='ISSUE_BK_ID']"));
							Bank.sendKeys("BK000034");
							log21.info("UNIBANCO-UNIAO DE BANCOS BRASIL,ADDRESS1,SAO PAULO,BRAZIL");
							Thread.sleep(1000);
							Logger log201 = Logger.getLogger("Available with Bank[41A]");
							WebElement Email1 = dr.findElement(By.xpath(".//*[@id='AVAL_WT_BK_OPT']"));
							Email1.sendKeys("Issuing Bank");
							log201.info(Email1.getAttribute("value"));
// 	Applicant / First Beneficiary [50]
							Logger log211 = Logger.getLogger("Applicant / First Beneficiary [50]");
							WebElement Email11 = dr.findElement(By.xpath(".//*[@id='APPL_ID']"));
							Email11.sendKeys("C000121");
							log211.info("C000121,AMY WEI,,ADD1,ADD2");
							Thread.sleep(1000);
//Advise LC By
							Logger log21111 = Logger.getLogger("Advise LC By");
							Row row51 = sheet.getRow(11);
							 Cell cell51 = row51.getCell(6);
							 String Advice = cell51.getStringCellValue();
							//String Advice = "";
							switch(Advice){
							case "Mail to Beneficiary":
									WebElement Email1111 = dr.findElement(By.xpath(".//*[@id='ADV_LC_BY']"));
									Email1111.sendKeys("Mail to Beneficiary");
									log21111.info(Email1111.getAttribute("value"));
									break;
							case "SWIFT to Beneficiary's Bank":
								WebElement Advice1 = dr.findElement(By.xpath(".//*[@id='ADV_LC_BY']"));
								Advice1.sendKeys("SWIFT to Beneficiary's Bank");
								log21111.info(Advice1.getAttribute("value"));
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='B_div']/table[2]/tbody/tr[1]/td/table/tbody/tr[28]/td[2]/input[2]")).click();
								
								 Set<String> windowId11 = dr.getWindowHandles();    // get  window id of current window
							        Iterator<String> itererator11 = windowId11.iterator();   
							        String mainWinID11 = itererator11.next();
							        String  newAdwinID11 = itererator11.next();
							        dr.switchTo().window(newAdwinID11);
							        System.out.println(dr.getTitle());
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							        dr.switchTo().window(mainWinID11);
							        System.out.println(dr.getTitle());
							        Thread.sleep(2000);
							        dr.switchTo().defaultContent();
									//switch to frame2
									dr.switchTo().frame("work");
								break;
							}
							try {
								Alert alert = dr.switchTo().alert();		
								alert.accept();
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							Thread.sleep(1000);
//Tenor
							dr.findElement(By.xpath(".//*[@id='C']")).click();
							Logger log24 = Logger.getLogger("Available By");
//Excel sheet get value 					
							Row row6 = sheet.getRow(4);
							 Cell cell6 = row6.getCell(6);
							 String Available = cell6.getStringCellValue();
							//String Available = "BY PAYMENT";
							switch(Available){
							case "BY PAYMENT":
								WebElement payment = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
								payment.sendKeys("BY PAYMENT");
								log24.info(payment.getAttribute("value"));
								Thread.sleep(1000);
								dr.findElement(By.xpath(".//*[@id='C_div']/table[2]/tbody/tr[1]/td/table/tbody/tr[3]/td[4]/input[2]")).click();
								
								 Set<String> windowId = dr.getWindowHandles();    // get  window id of current window
							        Iterator<String> itererator = windowId.iterator();   

							        String mainWinID = itererator.next();
							        String  newAdwinID = itererator.next();

							        dr.switchTo().window(newAdwinID);
							        System.out.println(dr.getTitle());
							       
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							       // dr.findElement(By.xpath("//*[@id='BANK_NARR_TAG_79']")).sendKeys("wfgshfgdsfgfhsfsgfhsfgsfgs");
							        //dr.close();
							        dr.switchTo().window(mainWinID);
							        System.out.println(dr.getTitle());
							       
							        Thread.sleep(2000);
							        dr.switchTo().defaultContent();
									  
									//switch to frame2
									dr.switchTo().frame("work");
							    break;    
							case "BY ACCEPTANCE":
								WebElement payment1 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
								payment1.sendKeys("BY ACCEPTANCE");
								log24.info(payment1.getAttribute("value"));
								Thread.sleep(1000);	
								Logger log25 = Logger.getLogger("Tenor");
								WebElement toClear = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
								toClear.sendKeys(Keys.CONTROL + "a");
								toClear.sendKeys(Keys.DELETE);
								WebElement Tenor = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
								Tenor.sendKeys("10");
								log25.info(Tenor.getAttribute("value"));
								Thread.sleep(1000);
								Logger log26 = Logger.getLogger("Tenor");
								Thread.sleep(1000);
								WebElement Tenor1 = dr.findElement(By.xpath(".//*[@id='TENOR_TYPE']"));
								Tenor1.sendKeys("DAYS AFTER SIGHT");
								log26.info(Tenor1.getAttribute("value"));
								Thread.sleep(1000);
								dr.findElement(By.xpath(".//*[@id='C_div']/table[2]/tbody/tr[1]/td/table/tbody/tr[3]/td[4]/input[2]")).click();
								
								 Set<String> windowId11 = dr.getWindowHandles();    // get  window id of current window
							        Iterator<String> itererator11 = windowId11.iterator();   

							        String mainWinID11 = itererator11.next();
							        String  newAdwinID11 = itererator11.next();

							        dr.switchTo().window(newAdwinID11);
							        System.out.println(dr.getTitle());
							       
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							        dr.switchTo().window(mainWinID11);
							        System.out.println(dr.getTitle());
							        Thread.sleep(2000);
							        dr.switchTo().defaultContent();
									  
									//switch to frame2
									dr.switchTo().frame("work");
									break;
							case "BY NEGOTIATION":
								WebElement payment11 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
								payment11.sendKeys("BY NEGOTIATION");
								log24.info(payment11.getAttribute("value"));
								Thread.sleep(1000);	
							
								Logger log251 = Logger.getLogger("Tenor");
								WebElement toClear1 = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
								toClear1.sendKeys(Keys.CONTROL + "a");
								toClear1.sendKeys(Keys.DELETE);
								WebElement Tenor11 = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
								Tenor11.sendKeys("10");
								log251.info(Tenor11.getAttribute("value"));
								Thread.sleep(1000);
								Logger log261 = Logger.getLogger("Tenor");
								Thread.sleep(1000);
								WebElement Tenor111 = dr.findElement(By.xpath(".//*[@id='TENOR_TYPE']"));
								Tenor111.sendKeys("DAYS AFTER SIGHT");
								log261.info(Tenor111.getAttribute("value"));
								dr.findElement(By.xpath(".//*[@id='DEF_PMT_DET']")).sendKeys("test");
								Thread.sleep(1000);
								dr.findElement(By.xpath(".//*[@id='C_div']/table[2]/tbody/tr[1]/td/table/tbody/tr[3]/td[4]/input[2]")).click();
								
								 Set<String> windowId111 = dr.getWindowHandles();    // get  window id of current window
							        Iterator<String> itererator111 = windowId111.iterator();   

							        String mainWinID111 = itererator111.next();
							        String  newAdwinID111 = itererator111.next();

							        dr.switchTo().window(newAdwinID111);
							        System.out.println(dr.getTitle());
							       
							        dr.findElement(By.xpath("//*[@id='0']/td[2]/a")).click();
							        dr.switchTo().window(mainWinID111);
							        System.out.println(dr.getTitle());
							        Thread.sleep(2000);
							        dr.switchTo().defaultContent();
									  
									//switch to frame2
									dr.switchTo().frame("work");
									break;
								
							case "BY DEF PAYMENT":
								WebElement payment111 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
								payment111.sendKeys("BY DEF PAYMENT");
								log24.info(payment111.getAttribute("value"));
								Thread.sleep(1000);	
								Logger log2511 = Logger.getLogger("Tenor");
								WebElement toClear11 = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
								toClear11.sendKeys(Keys.CONTROL + "a");
								toClear11.sendKeys(Keys.DELETE);
								WebElement Tenor1111 = dr.findElement(By.xpath(".//*[@id='TENOR_DAYS']"));
								Tenor1111.sendKeys("10");
								log2511.info(Tenor1111.getAttribute("value"));
								Thread.sleep(1000);
								Logger log2611 = Logger.getLogger("Tenor");
								Thread.sleep(1000);
								WebElement Tenor11111 = dr.findElement(By.xpath(".//*[@id='TENOR_TYPE']"));
								Tenor11111.sendKeys("DAYS AFTER SIGHT");
								log2611.info(Tenor11111.getAttribute("value"));
								Thread.sleep(1000);
								//dr.findElement(By.xpath(".//*[@id='DEF_PMT_DET']")).sendKeys("test");
								break;
							case "BY MIXED PYMT":
								WebElement payment2 = dr.findElement(By.xpath(".//*[@id='AVAL_BY']"));
								payment2.sendKeys("BY MIXED PYMT");
								log24.info(payment2.getAttribute("value"));
								

								break;
							}
							Logger log26 = Logger.getLogger("Payable At ");
							WebElement payment2 = dr.findElement(By.xpath(".//*[@id='PAY_AT']"));
							payment2.sendKeys("at our counters");
							log26.info(payment2.getAttribute("value"));
							Thread.sleep(1000);
							Logger log27 = Logger.getLogger("Acceptance By ");
							WebElement payment3 = dr.findElement(By.xpath(".//*[@id='ACPT_BY']"));
							payment3.sendKeys("OUR");
							log27.info(payment3.getAttribute("value"));
							Thread.sleep(1000);							
							try {
								dr.findElement(By.xpath(".//*[@id='D']")).click();								
								Thread.sleep(1000);
								dr.findElement(By.xpath("//*[@id='PaymentTerms_ADD']")).click();
								Logger log28 = Logger.getLogger("Sight/Def/Acc Flag");
								Row row7 = sheet.getRow(7);
								 Cell cell7 = row7.getCell(6);
								 String Flag = cell7.getStringCellValue();
								//String Flag = "Acceptance";
								switch (Flag){
								case "Sight" :
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Sight");
									log28.info("Sight");
									break;
								case "Deferred" :
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Deferred");
									log28.info("Deferred");
									Thread.sleep(1000);
									WebElement toClear = dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[2]/input"));
									toClear.sendKeys(Keys.CONTROL + "a");
									toClear.sendKeys(Keys.DELETE);
									 dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[2]/input")).sendKeys("50");
									Thread.sleep(1000);
									WebElement toClear1 = dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input"));
									toClear1.sendKeys(Keys.CONTROL + "a");
									toClear1.sendKeys(Keys.DELETE);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("10");
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[5]/td[4]/select")).sendKeys("DAYS AFTER SIGHT");
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='PaymentTerms_SAVE']")).click();
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='PaymentTerms_ADD']")).click();
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Deferred");
									Thread.sleep(1000);
									WebElement toClear11 = dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input"));
									toClear11.sendKeys(Keys.CONTROL + "a");
									toClear11.sendKeys(Keys.DELETE);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input")).sendKeys("10");
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[5]/td[4]/select")).sendKeys("DAYS AFTER SIGHT");
									break;
								case "Acceptance" :
									Thread.sleep(1000);
									dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[3]/td[2]/select")).sendKeys("Acceptance");
									log28.info("Acceptance");
									Thread.sleep(1000);
									WebElement toClear111 = dr.findElement(By.xpath("//*[@id='do_PaymentTerms_M']/table/tbody/tr[4]/td[4]/input"));
									toClear111.sendKeys(Keys.CONTROL + "a");
									toClear111.sendKeys(Keys.DELETE);
									
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
//Advice Tab	
							 try {
								Thread.sleep(2000);
								   // dr.switchTo().defaultContent();
									  
									//switch to frame2
									dr.switchTo().frame("work");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Thread.sleep(2000);
							Logger log211111 = Logger.getLogger("Open");			
							dr.findElement(By.xpath(".//*[@id='G']")).click();
							log211111.info("Advice Tab");
							Logger log2011 = Logger.getLogger("Open");	
							dr.findElement(By.xpath("//*[@id='ext-gen91']")).click();//add button
							log2011.info("Bank ");
		//Bank			
							 Thread.sleep(1000);
								//dr.switchTo().defaultContent();
								  
								//switch to frame2
								dr.switchTo().frame("frame.AdivceForBankCust");
								Thread.sleep(3000);
								Logger log101 = Logger.getLogger("Type of Message");	
							dr.findElement(By.xpath("//*[@id='MESG_TYPE_BANK']")).sendKeys("Mail");
							log101.info("Mail");
							Logger log111 = Logger.getLogger("ID,Name,Address");
							dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[4]/td[2]/input[2]")).click();//CUBK Button
							log111.info("ABNADEHHCGN,ABN AMRO BANK (DEUTSCHLAND) AG,undefined");
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
					        Logger log121 = Logger.getLogger("Narrative (Mail)");
					        dr.findElement(By.xpath("//*[@id='BANK_NARR_MAIL']")).sendKeys("Test1");
					       log121.info("Test1");
					        
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
							Logger log131 = Logger.getLogger("Type of Message");
							dr.findElement(By.xpath("//*[@id='MESG_TYPE_CUST']")).sendKeys("Mail");
							log131.info("Mail");
							Logger log141 = Logger.getLogger("ID,Name,Address");
							dr.findElement(By.xpath("//*[@id='B_div']/table/tbody/tr[4]/td[2]/input[2]")).click();
							log141.info("009800722,SHANGHAI FEI LONG CO LTD,SHANG HAI,");
			//Alert message close				
							try {
								Alert alert1 = dr.switchTo().alert();
					
								// Capturing alert message.
								String alertMessage1 = dr.switchTo().alert().getText();
					
								// Displaying alert message
								System.out.println(alertMessage1);
								Thread.sleep(2000);
					
								// Accepting alert
								alert1.accept();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}		
									        Thread.sleep(2000);
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
											 Thread.sleep(1000);
											dr.findElement(By.xpath("//*[@id='AdivceForBankCustsave']")).click();//*[@id="ext-gen310"]
											
											
											 Thread.sleep(1000);
											dr.findElement(By.xpath("//*[@id='AdivceForBankCustClose']")).click();//*[@id="AdivceForBankCustClose"]

											 Thread.sleep(2000);
//Confirm button					        
											dr.switchTo().defaultContent();
													  
													//switch to frame2
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
											 Thread.sleep(3000);
											 
								//Screenshot			
											 File src1= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
											 try {
											   // now copy the  screenshot to desired location using copyFile method
											  
											 FileUtils.copyFile(src1, new File("E:\\Testing\\Baseline\\ScreenShot\\EPLCRegisterLC.png"));
											 
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