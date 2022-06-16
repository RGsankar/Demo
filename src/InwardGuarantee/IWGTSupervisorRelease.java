package InwardGuarantee;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class IWGTSupervisorRelease extends SeMethods {
	@Test
	public void release() throws IOException, InterruptedException {

// login Page

		loginpage();
// IWGT Module
		//switchToFrame(3);
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Inward Guarantee");
		click(Import);
		log4.info("Inward Guarantee");
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
}