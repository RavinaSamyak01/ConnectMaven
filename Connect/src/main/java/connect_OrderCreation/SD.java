package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class SD extends ServiceDetail {
	@Test
	public static void sdSameDay() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		Thread.sleep(3000);
		WebElement order = driver.findElement(By.id("btnOrderProcess"));
		jse.executeScript("arguments[0].click();", order);

		Thread.sleep(9000);

		boolean sameairport = driver.getPageSource()
				.contains("Pickup and Delivery airport are different. Do you want to make it same?");

		if (sameairport == true) {
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")).click();
		}

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service SD :: Pickup # " + pck);
		logger.info("Service SD :: Pickup # " + pck + "\n");

		Thread.sleep(1000);

		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 2, 32, pck);

		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")).click();
		Thread.sleep(9000);

		driver.findElement(By.id("idEditSendByAir")).click();
		Thread.sleep(7000);
		driver.findElement(By.id("btnConfirmExtrernal")).click();
		Thread.sleep(10000);

		jse.executeScript("window.scrollBy(0,400)", "");
		String cost = driver.findElement(By.id("lblActualRate")).getText();
		excelDataProvider.writeData("Sheet1", 2, 31, cost);
		Thread.sleep(5000);

		driver.findElement(By.id("btnSaveChanges")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath(".//*[@id='idJobOverview']")).click();
		Thread.sleep(7000);
		driver.findElement(By.id("btnSelectFlights")).click();

		Thread.sleep(25000);

		driver.findElement(By.id("hlkSel_0")).click();

		Thread.sleep(9000);
		driver.findElement(By.xpath("//button[contains(.,'Assign')]")).click();
		Thread.sleep(15000);

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

		// board
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
