package Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class test1 extends SeMethods {
	@Test
	public void IMCOREG1 () throws IOException, InterruptedException{	
	//login Page
	
			loginpage();
	//IPLC Module		
			
			Logger log4 = Logger.getLogger("Module");
			WebElement Import  = locateElement("name", "Import Collection");
			click(Import);
			log4.info("Import Collection");
			Logger log5 = Logger.getLogger("Function");
			WebElement Function = locateElement("name", "IMCO Registration");
			click(Function);
			log5.info("IMCO Registration");
			Logger log6 = Logger.getLogger("Function Group");
			WebElement FunctionGroup = locateElement("name", "G49082300226F05030701642");
			click(FunctionGroup);
			log6.info("Create Collection");
	//Excel Sheet get Value
			FileInputStream fis = new FileInputStream("E:\\Testing\\IMCO.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Collection");
			workbook.close();  
	//Frame 
			 switchToFramest("work");

}
	public void san(){
		Logger log72 = Logger.getLogger("Registration Date");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date3 = new Date();
	    String date13 = dateFormat.format(date3);
		
		WebElement Date2 = locateElement("xpath", "//*[@id='A_div']/table/tbody/tr[2]/td[2]/input");
		String san5 = Date2.getAttribute("value");
		
		if (date13.equals(san5)){
			log72.info("The Registration date should be always current date:" + san5);
		}
		else{
			log72.info("The Registration date should be always current date:" + san5);
		}
			
	}
	

}

	