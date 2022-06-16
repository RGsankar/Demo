package DEMO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Framework.SeMethods;

public class Sample extends SeMethods{
	@Test
	public void IMCOREG () throws InterruptedException, IOException{
	startApp("chrome", "https://www.policybazaar.com/ifsc/");
	Thread.sleep(1000);
	WebElement Financia5 = locateElement("name","bank");
	selectDropDownUsingText(Financia5,"State Bank of India");
	Thread.sleep(1000);
	WebElement state = locateElement("name","state");
	selectDropDownUsingText(state,"TAMIL NADU");
	Thread.sleep(1000);
	WebElement district = locateElement("name","district");
	selectDropDownUsingText(district,"CHENNAI");
	Thread.sleep(1000);
	WebElement branch = locateElement("name","branch");
	selectDropDownUsingText(branch,"ADYAR");
	
	XSSFWorkbook workbook1 = new XSSFWorkbook();
	XSSFSheet sheet1 = workbook1.createSheet("Reference Number");
	FileOutputStream outputStream = new FileOutputStream(new File("E:\\Testing\\Baseline\\IFSC.xlsx"));
	
	/*Thread.sleep(2000);
	WebElement IFSC11 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]");
	String BIC_Code111 = IFSC11.getText();
	Thread.sleep(2000);
	Row row6 = sheet1.createRow(1);
	row6.createCell(3).setCellValue(BIC_Code111);*/
	
		Select bank = new Select(driver.findElement(By.name("bank")));
		bank.isMultiple();
		List<WebElement> dropdown = bank.getOptions();

		for (int i = 1; i < dropdown.size(); i++) {
			String drop_down_values = dropdown.get(i).getText();
			WebElement Financia51 = locateElement("name", "bank");
			selectDropDownUsingText(Financia51, drop_down_values);
			Thread.sleep(1000);
			
			Select State = new Select(driver.findElement(By.name("state")));
			State.isMultiple();
			List<WebElement> State_val = State.getOptions();
			for (int j = 1; j < State_val.size(); j++) {
				String drop_down_state = State_val.get(j).getText();
				Thread.sleep(1000);
				WebElement state_value = locateElement("name","state");
				selectDropDownUsingText(state_value,drop_down_state);
				Thread.sleep(1000);
				
				Select district1 = new Select(driver.findElement(By.name("district")));
				district1.isMultiple();
				List<WebElement> district1_val = district1.getOptions();
				for (int j1 = 1; j1 < district1_val.size(); j1++) {
					String drop_down_district = district1_val.get(j1).getText();
					Thread.sleep(1000);
					WebElement district_value = locateElement("name","district");
					selectDropDownUsingText(district_value,drop_down_district);
					
					Thread.sleep(1000);
					
					Select branch1 = new Select(driver.findElement(By.name("branch")));
					branch1.isMultiple();
					List<WebElement> branch1_val = branch1.getOptions();
					for (int j11 = 1; j11 < branch1_val.size(); j11++) {
						String drop_down_branch = branch1_val.get(j11).getText();
						Thread.sleep(1000);
						WebElement branch_value = locateElement("name","branch");
						selectDropDownUsingText(branch_value,drop_down_branch);
						
						Thread.sleep(2000);
						WebElement IFSC11 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]");
						String BIC_Code111 = IFSC11.getText();
						/*Thread.sleep(2000);
						Row row6 = sheet1.createRow(1);
						row6.createCell(3).setCellValue(BIC_Code111);
						
						workbook1.write(outputStream);
						workbook1.close();*/
					}
				}
			}
		
			System.out.println("dropdown values are " + drop_down_values);
		}               
		
		
	/*Thread.sleep(2000);
	WebElement IFSC1114 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div");
	String BIC_Code11115 = IFSC1114.getText();
	Thread.sleep(2000);
	Row row9 = sheet1.createRow(0);
	row9.createCell(3).setCellValue(BIC_Code11115);
//IFSC
	Thread.sleep(1000);
	WebElement IFSC = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[1]/div[1]/label");
	String BIC_Code1 = IFSC.getText();
	Thread.sleep(1000);
	WebElement IFSC1 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[1]/div[1]/p");
	String BIC_Code11 = IFSC1.getText();
	
	Row row = sheet1.createRow(2);
	row.createCell(3).setCellValue(BIC_Code1);
	row.createCell(4).setCellValue(BIC_Code11);
//Bank
	Thread.sleep(1000);
	WebElement Bank = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[2]/div[1]/label");
	String Bank_lable = Bank.getText();
	Thread.sleep(1000);
	WebElement State_Bank = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[2]/div[1]/p");
	String State_Bank_Of_India = State_Bank.getText();
	
	Row row1 = sheet1.createRow(3);
	row1.createCell(3).setCellValue(Bank_lable);
	row1.createCell(4).setCellValue(State_Bank_Of_India);
	
//District :
		Thread.sleep(1000);
		WebElement District = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[3]/div[1]/label");
		String District_lable = District.getText();
		Thread.sleep(1000);
		WebElement District_1 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[3]/div[1]/p");
		String District_Chennai = District_1.getText();
		
		Row row11 = sheet1.createRow(4);
		row11.createCell(3).setCellValue(District_lable);
		row11.createCell(4).setCellValue(District_Chennai);
//Branch : 
		Thread.sleep(1000);
		WebElement Branch = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[4]/div/label");
		String Branch_1 = Branch.getText();
		Thread.sleep(1000);
		WebElement Branch_2 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[4]/div/p");
		String Branch_3 = Branch_2.getText();
		
		Row row2 = sheet1.createRow(5);
		row2.createCell(3).setCellValue(Branch_1);
		row2.createCell(4).setCellValue(Branch_3);
//MICR Code
		Thread.sleep(1000);
		WebElement MICR_Code = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[1]/div[2]/label");
		String MICR_Code_1 = MICR_Code.getText();
		Thread.sleep(1000);
		WebElement MICR_Code_2 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[1]/div[2]/p");
		String MICR_Code_3 = MICR_Code_2.getText();
		
		Row row3 = sheet1.createRow(6);
		row3.createCell(3).setCellValue(MICR_Code_1);
		row3.createCell(4).setCellValue(MICR_Code_3);
//Address 
		Thread.sleep(1000);
		WebElement Address = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[2]/div[2]/label");
		String Address_1 = Address.getText();
		Thread.sleep(1000);
		WebElement Address_2 = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[2]/div[2]/p");
		String Address_3 = Address_2.getText();
		
		Row row4 = sheet1.createRow(7);
		row4.createCell(3).setCellValue(Address_1);
		row4.createCell(4).setCellValue(Address_3);
//State
		Thread.sleep(1000);
		WebElement State = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[3]/div[2]/label");
		String State_Lable = State.getText();
		Thread.sleep(1000);
		WebElement State_name = locateElement("xpath","//*[@id='content']/div[3]/div/div[1]/div/div[2]/div/div[3]/div[3]/div[2]/p");
		String State_name1 = State_name.getText();
		
		Row row5 = sheet1.createRow(8);
		row5.createCell(3).setCellValue(State_Lable);
		row5.createCell(4).setCellValue(State_name1);*/
		
		
}
}