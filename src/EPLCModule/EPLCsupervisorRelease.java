package EPLCModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class EPLCsupervisorRelease {
public static WebDriver dr;
	
	protected WebDriver GetDriver()
	{
		return null;
	}
	
	@AfterClass
	public void release() throws InterruptedException, IOException {
		Thread.sleep(2000);
		dr = GetDriver();
		dr.switchTo().defaultContent();
		Thread.sleep(2000);
		dr.switchTo().frame("FunctionList");
		Thread.sleep(2000);
		dr.findElement(By.xpath(".//*[@name='EPLC Advice']")).click();
		Logger log23 = Logger.getLogger("Function");
		dr.findElement(By.name("EPLC Maintenance")).click();
		log23.info("EPLC Maintenance");
		Logger log24 = Logger.getLogger("Function Group");
		dr.findElement(By.xpath(".//*[@name='G49082300270F05030701999']")).click();
		log24.info("Supervisor Release");
		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");

		FileInputStream fis1 = new FileInputStream("E:\\Testing\\Baseline\\sankar.xlsx");
		// FileInputStream fis = new FileInputStream("E:\\Testing\\sss.xlsx");
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);

		XSSFSheet sheet1 = workbook1.getSheet("Datatypes in Java");

		System.out.println(workbook1);
		Row row = sheet1.getRow(6);
		Cell cell = row.getCell(4);
		String LCNO = cell.getStringCellValue();
		WebElement san = dr.findElement(By.xpath("/html/body/form/div[1]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[4]/input"));
		// san.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		san.sendKeys(LCNO);

		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("eeToolbar");

		dr.findElement(By.xpath("//*[@id='_next']")).click();

		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");
		Thread.sleep(2000);
		dr.findElement(By.xpath("//*[@id='transaction']")).click();

		Thread.sleep(1000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("eeToolbar");
		Thread.sleep(3000);
		Logger log26 = Logger.getLogger("Confirm");
		dr.findElement(By.xpath("//*[@name='_confirm']")).click();
		log26.info("SupervisorRelease");
		Thread.sleep(2000);
		// Screenshot
		File src = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File("E:/Testing/ADIB/sankar/Release.png"));

		}

		catch (IOException e)

		{

			System.out.println(e.getMessage());

		}

		// cancel

		Thread.sleep(1000);
		dr.findElement(By.xpath("//*[@id='_cancel']")).click();
		Thread.sleep(1000);
		// IPLCModule.IssueLetterofCredit.issue();

		// Log Off
		Thread.sleep(2000);
		dr.switchTo().defaultContent();

		// switch to frame2
		dr.switchTo().frame("work");
		Thread.sleep(3000);
		Logger log25 = Logger.getLogger("End");
		dr.findElement(By.xpath("//*[@id='ext-gen6']/table[1]/tbody/tr/td/span[1]")).click();
		log25.info("Log off");
		Thread.sleep(1000);
		dr.quit();

		System.out.println("********Transaction compleled *******");

	}
}
