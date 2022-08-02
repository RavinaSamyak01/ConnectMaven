package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Pickup extends ServiceDetail {

	@Test
	public static void confirmPickup() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		Actions act = new Actions(driver);
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

			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@id=\"idValidationforMain\"]//ul[@id=\"errorid\"]")));
				String Valmsg = isElementPresent("OCValOnePack_xpath").getText();
				logs.info("Validation message is displayed=" + Valmsg);
				if (Valmsg.contains("Parameter(s) are modified. Please recalculate customer charges.")) {
					// Recalculate the charges
					// --Go to Edit Job tab
					WebElement EditOrTab = isElementPresent("EOEditOrderTab_id");
					act.moveToElement(EditOrTab).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(EditOrTab));
					act.moveToElement(EditOrTab).click().perform();
					logs.info("Click on Edit Order Tab");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// -Recalculate button
					WebElement ReCalc = isElementPresent("EORecal_id");
					act.moveToElement(ReCalc).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(ReCalc));
					act.moveToElement(ReCalc).click().perform();
					logs.info("Click on Recalculate button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Click on Save Changes button
					WebElement SaveChanges = isElementPresent("TLSaveChanges_id");
					act.moveToElement(SaveChanges).build().perform();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSaveChanges")));
					wait.until(ExpectedConditions.elementToBeClickable(SaveChanges));
					act.moveToElement(SaveChanges).click().perform();
					logs.info("Click on Save Changes button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Go to job Status Tab
					WebElement JobOverTab = isElementPresent("TLJobStatusTab_id");
					act.moveToElement(JobOverTab).build().perform();
					wait.until(ExpectedConditions.elementToBeClickable(JobOverTab));
					act.moveToElement(JobOverTab).click().perform();
					logs.info("Click on Job Overview Tab");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

					// --Enter Actual Pickup Time
					isElementPresent("TLPActPUpTime_id").clear();
					isElementPresent("TLPActPUpTime_id").sendKeys(rdytime);
					isElementPresent("TLPActPUpTime_id").sendKeys(Keys.TAB);
					logs.info("Enter Actual pickup time");

					// --Click on Confirm PU button
					WebElement ConfPU=isElementPresent("TLPConfPU_id");
					wait.until(ExpectedConditions.elementToBeClickable(ConfPU));
					Thread.sleep(2000);
					act.moveToElement(ConfPU).click().build().perform();
					logs.info("Click on Confirm PU button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

			} catch (Exception PModify) {
				logs.info("Validation message is not displayed for Recalculate the charges");

			}

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
