package IPLCModule;

import java.io.IOException;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class demo1 {
	@Test
	public static void m1() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\Testing\\chromedriver.exe");
		 
		// Initialize browser
		WebDriver dr=new ChromeDriver();
		 //Logger log = Logger.getLogger("IPLC Register");
		 // configure log4j properties file
	       //PropertyConfigurator.configure("Log4j.properties");
	      
		 dr.get("http://demoqa.com/registration/");
		 //log.info("Browser Opened");
			 dr.manage().window().maximize();
			 //Logger log2 = Logger.getLogger("name");
			 WebElement ele = dr.findElement(By.xpath("//*[@id='pie_register']/li[1]/div[1]/label"));
			 ele.click();
			 System.out.println(ele.getText());
			 //System.out.println(ele.getAttribute("value"));
			 //dr.findElement(By.xpath(".//*[@id='name_3_firstname']")).sendKeys("sankar");
			// log2.info(ele.getAttribute("value"));
			 //log2.info(ele.getAttribute("value"));
			 dr.findElement(By.xpath(".//*[@id='name_3_lastname']")).sendKeys("Govindharaj");
			 //log.info("govindharaj");
			 try {
				
				dr.findElement(By.xpath(".//*[@id='pie_register']/li[3]/div/div/input[1]")).click();
			} finally {
				// TODO: handle finally clause
				dr.findElement(By.xpath(".//*[@id='pie_register']/li[2]/div/div/input[1]")).click();
			}
			//dr.findElement(By.xpath(".//*[@id='dropdown_7']")).clear();
			 try {
				Select Country = new Select (dr.findElement(By.xpath(".//*[@id='dropdown_7']")));
				 
				 Country.selectByVisibleText("India");
				 Select month = new Select (dr.findElement(By.xpath(".//*[@id='mm_date_8']")));
				 
				 month.selectByVisibleText("7");
				 Select date = new Select (dr.findElement(By.xpath(".//*[@id='dd_date_8']")));
				 
				 date.selectByVisibleText("1");
				 Select year = new Select (dr.findElement(By.xpath(".//*[@id='yy_date_8']")));
				 
				 year.selectByVisibleText("1994");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 try {
				dr.findElement(By.xpath(".//*[@id='phone_9']")).sendKeys("4597914545455");
				dr.findElement(By.xpath(".//*[@id='username']")).sendKeys("sankar");
				dr.findElement(By.xpath(".//*[@id='email_1']")).sendKeys("dsgfdgf@gmail.com");
				dr.findElement(By.xpath(".//*[@id='description']")).sendKeys("dsgfdgf@gmail.com");
				dr.findElement(By.xpath(".//*[@id='password_2']")).sendKeys("sankar12");
				dr.findElement(By.xpath(".//*[@id='confirm_password_password_2']")).sendKeys("sankar12");
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("Welcome");
			 

	

}

}
