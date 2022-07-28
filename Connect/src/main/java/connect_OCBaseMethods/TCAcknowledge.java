package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class TCAcknowledge extends BaseInit {

	@Test
	public static void tcAcknowledge() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		
		//--Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);
		
		//--Click on TC Ack button
		if (svc.equals("LOC") || svc.equals("P3P") || svc.equals("DRV") || svc.equals("SDC") || svc.equals("FRG")) {
			WebElement TCAckBtn = isElementPresent("TLAcknBTN_id");
			wait.until(ExpectedConditions.elementToBeClickable(TCAckBtn));
			TCAckBtn.click();
			logs.info("Clicked on TC Acknowledge button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}

		if (svc.equals("SD") || svc.equals("PA") || svc.equals("AIR") || svc.equals("FRA")) {
			WebElement TCAckBtn = isElementPresent("TLAckBTn2_id");
			wait.until(ExpectedConditions.elementToBeClickable(TCAckBtn));
			TCAckBtn.click();
			logs.info("Clicked on TC Acknowledge button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}
	}
}
