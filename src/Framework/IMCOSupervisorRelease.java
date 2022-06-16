package Framework;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

public class IMCOSupervisorRelease extends IMCORegister{
	/*public RemoteWebDriver GetDriver()
	{
		return null;
	}
	*/
	@AfterClass
	public void release() throws InterruptedException, IOException {
		loginpage();
		//driver = GetDriver();
		Thread.sleep(3000);
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

//Release
		WebElement Release = locateElement("name", "transaction");
		click(Release);
		Thread.sleep(1000);
		switchToFramest("eeToolbar");
		
//Confirm
		Confirm();
}
}