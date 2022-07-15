package connect_OrderCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_BasePackage.BaseInit;

public class CreateOrder extends BaseInit {
	public void createorder() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 50);

		msg.append("Create Order Test start" + "\n");
		logs.info("Create Order Test start");

		// --Go to Operations
		WebElement Operations = isElementPresent("Operations_id");
		highLight(Operations, driver);
		act.moveToElement(Operations).click().perform();
		logs.info("Click on Operations");

		// --Create Order
		highLight(isElementPresent("CreateOrder_id"), driver);
		isElementPresent("CreateOrder_id").click();
		logs.info("Click on Create Order");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idOrder")));

		// --Initialize ExcelFile to get data
		File src = new File(".\\NA_STG.xls");
		FileInputStream fis = new FileInputStream(src);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sh = workbook.getSheet("OrderCreation");
		// int rcount = sh0.getLastRowNum();

		// --Initialize excelfile to set data
		File src1 = new File(".\\src\\TestFiles\\MDSiTestResult.xlsx");
		FileOutputStream fis1 = new FileOutputStream(src1);
		Sheet sh2 = workbook.getSheet("Sheet1");

		DataFormatter formatter = new DataFormatter();

		String CallerName = formatter.formatCellValue(sh.getRow(1).getCell(1));
		String PhoneNo = formatter.formatCellValue(sh.getRow(1).getCell(2));
		String AccountNo = formatter.formatCellValue(sh.getRow(1).getCell(3));
		String Reference = formatter.formatCellValue(sh.getRow(1).getCell(4));
		String PickUpZip = formatter.formatCellValue(sh.getRow(1).getCell(5));
		String PickUpCompany = formatter.formatCellValue(sh.getRow(1).getCell(6));
		String PickUpAddress = formatter.formatCellValue(sh.getRow(1).getCell(7));
		String PickUpAttention = formatter.formatCellValue(sh.getRow(1).getCell(8));
		String PickUpPhone = formatter.formatCellValue(sh.getRow(1).getCell(9));


		// --Enter Caller
		highLight(isElementPresent("CallerName_id"), driver);
		isElementPresent("CallerName_id").sendKeys(CallerName);
		// --Enter Phone
		highLight(isElementPresent("CallPhone_id"), driver);
		isElementPresent("CallPhone_id").sendKeys(PhoneNo);
		// --Enter Customer
		highLight(isElementPresent("CallCustomer_id"), driver);
		isElementPresent("CallCustomer_id").sendKeys(AccountNo);
		isElementPresent("CallCustomer_id").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		// --Enter Reference
		highLight(isElementPresent("CallReference_id"), driver);
		isElementPresent("CallReference_id").sendKeys(Reference);

		// --Get the Customer Name
		String AccountName = isElementPresent("AccountName_id").getText();
		sh2.getRow(1).createCell(0).setCellValue(AccountName);
		workbook.write(fis1);
		fis1.close();
		
		// --Enter PickUpZipCode
		highLight(isElementPresent("CallCustomer_id"), driver);
		isElementPresent("CallCustomer_id").sendKeys(AccountNo);
		isElementPresent("CallCustomer_id").sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		

	}

}
