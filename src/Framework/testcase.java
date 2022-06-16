package Framework;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class testcase extends SeMethods {
	@Test
	public void editLead() {		
		startApp("chrome", "http://leaftaps.com/opentaps");
		WebElement uName = locateElement("id", "username");
		//WebElement uName = locateElement("username1");
		type(uName, "DemoSalesManager");
		WebElement pwd = locateElement("id", "password");
		type(pwd, "crmsfa");
		WebElement loginButton = locateElement("class", "decorativeSubmit");
		click(loginButton);
		
		WebElement crm_sfaLink = locateElement("linktext","CRM/SFA");
		click(crm_sfaLink);
		
	}


}
