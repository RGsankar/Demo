package IPLCModule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class framework {
	public static WebDriver dr;
	public static void main(String args[]){
		Select oSelect = new Select(dr.findElement(By.xpath("//*[@id='AVAL_BY']")));
		oSelect.selectByVisibleText("By Payment");
	List<WebElement> oSize = oSelect.getOptions();
	int iListSize = oSize.size();

	// Setting up the loop to print all the options
	for(int i =0; i < iListSize ; i++){
		// Storing the value of the option	
		String sValue = oSelect.getOptions().get(i).getText();
		// Printing the stored value
		System.out.println(sValue);
	}

}
}
