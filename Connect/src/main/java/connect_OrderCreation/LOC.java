package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.TCAcknowledge;

public class LOC extends BaseInit {
	@Test
	public static void locLocal() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		// --Click on Create Order button
		WebElement order = isElementPresent("OCOProcess_id");
		wait.until(ExpectedConditions.elementToBeClickable(order));
		jse.executeScript("arguments[0].click();", order);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		boolean sameairport = driver.getPageSource()
				.contains("Pickup and Delivery airport are different. Do you want to make it same?");

		if (sameairport == true) {
			driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")).click();
		}

		// --Get the PickUPID
		WebElement PickUPID = isElementPresent("OCPickuPID_xpath");
		wait.until(ExpectedConditions.elementToBeClickable(PickUPID));
		String pck = PickUPID.getText();
		System.out.println("Service LOC :: Pickup # " + pck);
		logs.info("Service LOC :: Pickup # " + pck + "\n");
		msg.append("Service LOC :: Pickup # " + pck + "\n\n");


		// --Set PickUPID
		setData("Sheet1", 1, 32, pck);

		// --Click on Edit Order
		WebElement EditOrder = isElementPresent("OCEditOrder_id");
		EditOrder.click();
		logs.info("Clicked on Edit Order");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// --Scroll to get Rate
		jse.executeScript("window.scrollBy(0,400)", "");
		String cost = isElementPresent("TLActualRate_id").getText();
		setData("Sheet1", 1, 31, cost);
		logs.info("Scroll down to Get the Rate");

		// --Moved to Job Status
		WebElement idJob = isElementPresent("TLJobStatusTab_id");
		wait.until(ExpectedConditions.elementToBeClickable(idJob));
		jse.executeScript("arguments[0].click();", idJob);
		logs.info("Clicked on Job Status Tab");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		// TC Acknowledge
		TCAcknowledge.tcAcknowledge();

		// Pickup Alert
		ReadyForDispatch.pickupAlert();

		// PICKEDUP
		Pickup.confirmPickup();

		// DELIVERED
		Deliver.confirmDelivery();

	}
}
