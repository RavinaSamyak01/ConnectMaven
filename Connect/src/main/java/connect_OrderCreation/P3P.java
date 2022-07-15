package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.CSRAcknowledge;
import connect_OCBaseMethods.ExcelDataProvider;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.ServiceDetail;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.TenderTo3P;

public class P3P extends ServiceDetail {

	@Test
	public static void p3pservice() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		Thread.sleep(5000);

		jse.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.id("idNewOrderServiceId")).clear();
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys("P3P");
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys(Keys.TAB);
		Thread.sleep(9000);

		WebElement p3acc = wait.until(ExpectedConditions.elementToBeClickable(By.id("cmb3PAccount")));
		jse.executeScript("arguments[0].click();", p3acc);
		Select list1 = new Select(p3acc);
		list1.selectByValue("string:1");
//		Select Contacttype = new Select(driver.findElement(By.id("cmb3PAccount")));
//		Contacttype.selectByVisibleText("OTHER");
		Thread.sleep(5000);
//		driver.findElement(By.id("txtOtherCarrierName")).clear();
//		driver.findElement(By.id("txtOtherCarrierName")).sendKeys("FEDEX");
//		driver.findElement(By.id("txtOtherCarrierName")).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		WebElement drop = driver.findElement(By.id("btnSelDropOffLoc"));
		jse.executeScript("arguments[0].click();", drop);
		Thread.sleep(6000);
		WebElement assn = driver.findElement(By.id("AssnLocAddr_0"));
		jse.executeScript("arguments[0].click();", assn);
		Thread.sleep(5000);

		jse.executeScript("window.scrollBy(0,350)", "");
		WebElement Order = driver.findElement(By.id("btnOrderProcess"));
		jse.executeScript("arguments[0].click();", Order);
		Thread.sleep(5000);

		boolean sameairport = driver.getPageSource()
				.contains("Pickup and Delivery airport are different. Do you want to make it same?");

		if (sameairport == true) {
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")).click();
		}

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service P3P :: Pickup # " + pck);
		logger.info("Service P3P :: Pickup # " + pck + "\n");
		Thread.sleep(1000);
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 3, 32, pck);
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")).click();
		Thread.sleep(5000);

		jse.executeScript("window.scrollBy(0,400)", "");
		String cost = driver.findElement(By.id("lblActualRate")).getText();
		excelDataProvider.writeData("Sheet1", 3, 31, cost);
		Thread.sleep(3000);

		driver.findElement(By.xpath(".//*[@id='idJobOverview']")).click();

		// CSR Acknowledge
		Thread.sleep(7000);
		CSRAcknowledge.csrAcknowledge();

		// TC Acknowledge
		Thread.sleep(7000);
		TCAcknowledge.tcAcknowledge();

		// Pickup Alert
		Thread.sleep(7000);
		ReadyForDispatch.pickupAlert();

		// PICKEDUP
		Thread.sleep(3000);
		Pickup.confirmPickup();

		// Tender to 3P
		Thread.sleep(3000);
		TenderTo3P.tndrTo3P();

		Thread.sleep(3000);
	}
}
