package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.ExcelDataProvider;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.ServiceDetail;
import connect_OCBaseMethods.TCAcknowledge;

public class SDC extends ServiceDetail {

	public static void sdcSameDayCity() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		jse.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.id("idNewOrderServiceId")).clear();
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys("SDC");
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys(Keys.TAB);
		Thread.sleep(7000);
		jse.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(3000);
		WebElement Order = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOrderProcess")));
		jse.executeScript("arguments[0].click();", Order);
		Thread.sleep(9000);

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service SDC :: Pickup # " + pck);
		logger.info("Service SDC :: Pickup # " + pck + "\n");
		Thread.sleep(1000);

		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 7, 32, pck);
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")).click();
		Thread.sleep(9000);
		driver.findElement(By.xpath(".//*[@id='idJobOverview']")).click();
		Thread.sleep(7000);

		// TC Acknowledge
		Thread.sleep(7000);
		TCAcknowledge.tcAcknowledge();

		// Pickup Alert
		Thread.sleep(7000);
		ReadyForDispatch.pickupAlert();

		// PICKEDUP
		Thread.sleep(3000);
		Pickup.confirmPickup();

		// DELIVERED
		Thread.sleep(3000);
		Deliver.confirmDelivery();

	}
}
