package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.Drop;
import connect_OCBaseMethods.ExcelDataProvider;
import connect_OCBaseMethods.HAANotify;
import connect_OCBaseMethods.OnHandAtDestination;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.ServiceDetail;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.WaitForArrival;
import connect_OCBaseMethods.WaitForDeptarture;
import connect_OCBaseMethods.XerWaitForArrival;
import connect_OCBaseMethods.XerWaitForDeparture;

public class AIR extends ServiceDetail {

	@Test
	public static void airService() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		Thread.sleep(8000);
		jse.executeScript("window.scrollBy(0,-450)", "");

		driver.findElement(By.id("idSendByAir")).click();
		Thread.sleep(5000);

		driver.findElement(By.id("txtVerifyFedex")).sendKeys("KSMS DONE");
		driver.findElement(By.id("txtVerifyFedex")).sendKeys(Keys.TAB);

		driver.findElement(By.id("btnYes")).click();

		Thread.sleep(8000);

		driver.findElement(By.id("btnSelectFlights")).click();

		Thread.sleep(25000);

		driver.findElement(By.id("hlkSel_0")).click();

		Thread.sleep(9000);
		driver.findElement(By.xpath("//button[contains(.,'Assign')]")).click();
		Thread.sleep(15000);

		WebElement Check = wait.until(ExpectedConditions.elementToBeClickable(By.id("idcheckdelhaa")));
		jse.executeScript("arguments[0].click();", Check);
		Thread.sleep(3000);

		jse.executeScript("window.scrollBy(0,450)", "");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ShipmentChargeSection']/fieldset/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(7000);

		WebElement Order = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOrderProcess")));
		jse.executeScript("arguments[0].click();", Check);
		Thread.sleep(9000);

		boolean sameairport = driver.getPageSource()
				.contains("Pickup and Delivery airport are different. Do you want to make it same?");

		if (sameairport == true) {
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")).click();
		}

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service AIR :: Pickup # " + pck);
		logs.info("Service AIR :: Pickup # " + pck + "\n");

		Thread.sleep(1000);

		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 6, 32, pck);

		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")).click();
		Thread.sleep(9000);

		// jse.executeScript("window.scrollBy(0,400)", "");
		// String cost = driver.findElement(By.id("lblActualRate")).getText();
		// excelDataProvider.writeData("Sheet1", 6, 31, cost);
		// Thread.sleep(3000);

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

		// DROP
		Thread.sleep(3000);
		Drop.dropAtOrigin();

		// HAA Notify
		Thread.sleep(3000);
		HAANotify.haaNtfy();

		// Wait for Departure
		Thread.sleep(5000);
		WaitForDeptarture.waitForDept();

		// XER wait for Arrival
		Thread.sleep(5000);
		XerWaitForArrival.xerWaitForArr();

		// XER Wait for Departure
		Thread.sleep(5000);
		XerWaitForDeparture.xerWaitForDept();

		// Wait for Arrival
		Thread.sleep(5000);
		WaitForArrival.waitForArr();

		// ONHAND @ DESTINATION
		Thread.sleep(5000);
		OnHandAtDestination.onHandDst();

		// DELIVERED
		Thread.sleep(3000);
		Deliver.confirmDelivery();

		Thread.sleep(3000);
	}
}
