package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Board;
import connect_OCBaseMethods.Board1;
import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.Drop;
import connect_OCBaseMethods.ExcelDataProvider;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.Recover;
import connect_OCBaseMethods.SendDelAlert;
import connect_OCBaseMethods.ServiceDetail;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.WaitForArrival;
import connect_OCBaseMethods.WaitForDeptarture;
import connect_OCBaseMethods.XerWaitForArrival;
import connect_OCBaseMethods.XerWaitForDeparture;

public class PA extends ServiceDetail {

	@Test
	public static void paPriorityAir() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		jse.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.id("idNewOrderServiceId")).clear();
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys("PA");
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys(Keys.TAB);
		Thread.sleep(7000);
		jse.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(2000);
		WebElement btnOrder = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOrderProcess")));
		jse.executeScript("arguments[0].click();", btnOrder);
		Thread.sleep(9000);

		boolean sameairport = driver.getPageSource()
				.contains("Pickup and Delivery airport are different. Do you want to make it same?");

		if (sameairport == true) {
			WebElement airport = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")));
			jse.executeScript("arguments[0].click();", airport);
		}

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service PA :: Pickup # " + pck);
		logger.info("Service PA :: Pickup # " + pck + "\n");
		Thread.sleep(1000);
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 4, 32, pck);
		Thread.sleep(2000);
		WebElement editOrder = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")));
		jse.executeScript("arguments[0].click();", editOrder);
		Thread.sleep(2000);
		WebElement send = wait.until(ExpectedConditions.elementToBeClickable(By.id("idEditSendByAir")));
		jse.executeScript("arguments[0].click();", send);
		Thread.sleep(2000);
		WebElement Confirm = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnConfirmExtrernal")));
		jse.executeScript("arguments[0].click();", Confirm);
		Thread.sleep(2000);
		WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSaveChanges")));
		jse.executeScript("arguments[0].click();", save);
		Thread.sleep(2000);
		WebElement job = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='idJobOverview']")));
		jse.executeScript("arguments[0].click();", job);
		Thread.sleep(5000);
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSelectFlights")));
		jse.executeScript("arguments[0].click();", btn);
		Thread.sleep(2000);
		WebElement sel = wait.until(ExpectedConditions.elementToBeClickable(By.id("hlkSel_0")));
		jse.executeScript("arguments[0].click();", sel);
		Thread.sleep(2000);
		WebElement assign = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Assign')]")));
		jse.executeScript("arguments[0].click();", assign);
		Thread.sleep(10000);

		// jse.executeScript("window.scrollBy(0,400)", "");
		// String cost = driver.findElement(By.id("lblActualRate")).getText();
		// excelDataProvider.writeData("Sheet1", 4, 31, cost);
		// Thread.sleep(3000);

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

		// Send Del Alert
		Thread.sleep(3000);
		SendDelAlert.delAlert();

		// Wait for Departure
		Thread.sleep(5000);
		WaitForDeptarture.waitForDept();

		// OnBorad
		Thread.sleep(5000);
		Board.onBoard();

		// XER wait for Arrival
		Thread.sleep(5000);
		XerWaitForArrival.xerWaitForArr();

		// XER Wait for Departure
		Thread.sleep(5000);
		XerWaitForDeparture.xerWaitForDept();

		// board2
		Thread.sleep(5000);
		Board1.onBoard1();

		// Wait for Arrival
		Thread.sleep(5000);
		WaitForArrival.waitForArr();

		// Recover
		Thread.sleep(3000);
		Recover.recoverAtDestination();

		// DELIVERED
		Thread.sleep(3000);
		Deliver.confirmDelivery();

		Thread.sleep(3000);
	}

}
