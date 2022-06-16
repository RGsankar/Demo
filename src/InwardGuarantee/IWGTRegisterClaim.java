package InwardGuarantee;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class IWGTRegisterClaim extends SeMethods {
	@Test
	public void RegisterClaim() throws IOException, InterruptedException {

// login Page
		loginpage();
// IWGT Module
		Logger log4 = Logger.getLogger("Module");
		WebElement Import = locateElement("name", "Inward Guarantee");
		click(Import);
		log4.info("Inward Guarantee");
		Logger log5 = Logger.getLogger("Function");
		WebElement Function = locateElement("name", "IWGT Claims");
		click(Function);
		log5.info("IWGT Claims");
		Logger log6 = Logger.getLogger("Function Group");
		WebElement FunctionGroup = locateElement("name", "G49082300308F05030702104");
		click(FunctionGroup);
		log6.info("Register Claim");
// Frame
		switchToFramest("work");
// Catalog
		IWGTCatalog();
// Excel Sheet get Value
		FileInputStream fis = new FileInputStream("E:\\Testing\\Module\\IWGT.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("IWGTClaims");
		workbook.close();
// Main
//Claim		
		WebElement Claim = locateElement("id", "C");
		click(Claim);
// Claim Reference
		Logger log10111 = Logger.getLogger("Claim Reference");
		Row row33 = sheet.getRow(3);
		Cell cell33 = row33.getCell(2);
		int Reference = (int) cell33.getNumericCellValue();
		WebElement ele811111 = locateElement("id","GTEE_CLM_REF");
		type(ele811111, Integer.toString(Reference));
		log10111.info(ele811111.getAttribute("value"));
//Claim Registration Date
		Logger lo = Logger.getLogger("Claim Registration Date");
		Row row1111 = sheet.getRow(3);
		Cell cell1111 = row1111.getCell(4);
		SimpleDateFormat formatter11 = new SimpleDateFormat("yyyy-MM-dd");
		Date StartDate = cell1111.getDateCellValue();
		WebElement date11 = locateElement("id", "CLM_DT");
		Clear(date11);
		WebElement date = locateElement("id", "CLM_DT");
		type(date, formatter11.format(StartDate));
		lo.info(date.getAttribute("value"));

// Claim Amount
		Logger log101111 = Logger.getLogger("Claim Amount");
		Row row331 = sheet.getRow(3);
		Cell cell331 = row331.getCell(6);
		int Reference1 = (int) cell331.getNumericCellValue();
		WebElement ele8 = locateElement("id","CLM_TRX_CCY_AMT");
		click(ele8);
		WebElement ele8111111 = locateElement("id","CLM_TRX_CCY_AMT");
		type(ele8111111, Integer.toString(Reference1));
		log101111.info(ele8111111.getAttribute("value"));
//Claim Narrative
		Logger log20 = Logger.getLogger("Claim Narrative");
		Row row13111 = sheet.getRow(5);
		Cell cell13111 = row13111.getCell(2);
		String Release = cell13111.getStringCellValue();
		WebElement Documents = locateElement("name","NARR");
		type(Documents,Release);
		log20.info(Documents.getAttribute("value"));

// Advice
		WebElement Advice = locateElement("id", "D");
		click(Advice);
		Advice();
// Notes
		WebElement Notes = locateElement("id", "E");
		click(Notes);
// Diary
		WebElement Diary = locateElement("id", "F");
		click(Diary);
		Diary();
// confirm
		Confirm();
// Supervisor Release
		IWGTSupervisorRelease();

}
}