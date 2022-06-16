package EPLCFiledValidation;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class EplcRegisterLC {
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
			/* FileInputStream fis = new FileInputStream("E:\\Testing\\test.xlsx");
			
			 XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 
			 XSSFSheet sheet = workbook.getSheet("RegLC");
			                      
			 Row row = sheet.getRow(4);
			 Cell cell = row.getCell(2);
			
			 String san = cell.getStringCellValue();
			 System.out.println(workbook);*/
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
			String[] invalidChars = {"#0$$%##", "!5/*-**-**/", "$12212121213544215",};
			for (String invalid : invalidChars) {
		    	 dr.findElement(By.xpath("//*[@id='LC_NO']")).clear();
		    	 Logger log71 = Logger.getLogger(" Lc Number ");
				 dr.findElement(By.xpath("//*[@id='LC_NO']")).sendKeys(invalid);
		    	 Thread.sleep(1000);
		    	 log71.info(invalid +"This charactor accepting");
			}
			
			Logger log7 = Logger.getLogger("LC Number [20]");
			 dr.findElement(By.xpath("//*[@id='LC_NO']")).clear();
			WebElement ele =dr.findElement(By.xpath("//*[@id='LC_NO']"));
			ele.sendKeys("54565258");
			log7.info(ele.getAttribute("value"));
			
			
			
			/*String[] invalidChars = {"#0", "!5", "$", "@", "%", "^", "&"
					+ "",};
		    //String name = "a1"; 
		    for (String invalid : invalidChars) {
		    	 dr.findElement(By.id("POS_TOL")).clear();
		    	 Thread.sleep(1000);
		    	 Logger log71 = Logger.getLogger("[W4498] Positive Tolerance must be an integer, please check it!");
			 dr.findElement(By.id("POS_TOL")).sendKeys(invalid);
			 log71.info(invalid);
			 Thread.sleep(1000);
		       dr.findElement(By.xpath("//*[@id='A_div']/table/tbody/tr[7]/td[3]")).click();
		       Thread.sleep(1000);
		        String alertMessage = dr.switchTo().alert().getText();
		        Thread.sleep(100);
		        System.out.println(invalid);
		        System.out.println(alertMessage);
		        //dr.switchTo().alert().dismiss();
		      if (alertMessage.equals("[W4498] Positive Tolerance must be an integer, please check it!")){
		            System.out.println("Error displayed: First name Should not contain Special Characters");
		            dr.switchTo().alert().dismiss();
		        } else{
		            System.out.println("Accepted");
		        }
		      }*/
			
			
			

}
}