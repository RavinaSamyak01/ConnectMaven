package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.ExcelDataProvider;
import connect_OCBaseMethods.HoldAtOrigin;
import connect_OCBaseMethods.OutForDelivery;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReAlertForDelivery;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.ServiceDetail;
import connect_OCBaseMethods.TCAcknowledge;

public class DRV extends ServiceDetail {

	@Test
	public static void drvDriver() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		Actions actions = new Actions(driver);

		Thread.sleep(3000);
		WebElement Order = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOrderProcess")));
		jse.executeScript("arguments[0].click();", Order);
		Thread.sleep(9000);

		boolean sameairport = driver.getPageSource()
				.contains("Pickup and Delivery airport are different. Do you want to make it same?");

		if (sameairport == true) {
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")).click();
		}

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service DRV :: Pickup # " + pck);
		logs.info("Service DRV :: Pickup # " + pck + "\n");
		Thread.sleep(1000);

		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 5, 32, pck);
		Thread.sleep(9000);
		WebElement EditOrder = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")));
		jse.executeScript("arguments[0].click();", EditOrder);
		Thread.sleep(9000);
		jse.executeScript("scroll(0,500)");
		Thread.sleep(4000);
		WebElement HAS = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idchkbHAS\"]")));
		actions.moveToElement(HAS).click().build().perform();
		Thread.sleep(9000);
		WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSaveChanges")));
		jse.executeScript("arguments[0].click();", save);
		Thread.sleep(10000);

		// JavascriptExecutor jse = (JavascriptExecutor)driver;
		// jse.executeScript("window.scrollBy(0,400)", "");
		// String cost = driver.findElement(By.id("lblActualRate")).getText();
		// excelDataProvider.writeData("Sheet1", 5, 31, cost);
		// Thread.sleep(3000);

		WebElement job = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idJobOverview\"]")));
		jse.executeScript("arguments[0].click();", job);

		// TC Acknowledge
		Thread.sleep(7000);
		TCAcknowledge.tcAcknowledge();

		// Pickup Alert
		Thread.sleep(7000);
		ReadyForDispatch.pickupAlert();

		// PICKEDUP
		Thread.sleep(3000);
		Pickup.confirmPickup();

		// HOLD @ ORIGIN
		Thread.sleep(3000);
		HoldAtOrigin.hldAtOrigin();

		// RE-ALERT FOR DELIVERY
		Thread.sleep(3000);
		ReAlertForDelivery.reAlertfordel();

		// OUT FOR DELIVERY
		Thread.sleep(3000);
		OutForDelivery.outForDel();

		// DELIVERED
		Thread.sleep(3000);
		Deliver.confirmDelivery();

		Thread.sleep(3000);
	}

}
