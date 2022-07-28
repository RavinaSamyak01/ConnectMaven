package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Pickup extends ServiceDetail {

	@Test
	public static void confirmPickup() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		if (svc.equals("LOC") || svc.equals("P3P") || svc.equals("DRV") || svc.equals("SDC") || svc.equals("FRG")) {
			// --Enter Actual Pickup Time
			isElementPresent("TLPActPUpTime_id").clear();
			isElementPresent("TLPActPUpTime_id").sendKeys(rdytime);
			isElementPresent("TLPActPUpTime_id").sendKeys(Keys.TAB);
			logs.info("Enter Actual pickup time");

			// --Click on Confirm PU button
			isElementPresent("TLPConfPU_id").click();
			logs.info("Click on Confirm PU button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}

		if (svc.equals("SD") || svc.equals("PA") || svc.equals("AIR") || svc.equals("FRA")) {
			// --Enter Actual Pickup Time
			isElementPresent("TLPActPUpTime_id").clear();
			isElementPresent("TLPActPUpTime_id").sendKeys(rdytime);
			isElementPresent("TLPActPUpTime_id").sendKeys(Keys.TAB);
			logs.info("Enter Actual pickup time");

			// --Click on Confirm PU button
			isElementPresent("TLPUConfPU2_id").click();
			logs.info("Click on Confirm PU button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}

	}

}
