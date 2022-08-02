package connect_OCBaseMethods;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Deliver extends ServiceDetail {

	@Test
	public static void confirmDelivery() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		Actions act = new Actions(driver);

		// --Get the ServiceID
		String svc = isElementPresent("TLServID_id").getText();
		System.out.println(svc);
		logs.info("ServiceID=" + svc);

		if (svc.equals("LOC") || svc.equals("DRV") || svc.equals("SDC") || svc.equals("FRG")) {
			// --Enter Actual DL time
			isElementPresent("TLDActDLTime_id").clear();
			isElementPresent("TLDActDLTime_id").sendKeys(rdytime);
			logs.info("Enter Actual DL Time");

			// --Enter SIgnature
			isElementPresent("TLDSignature_id").clear();
			isElementPresent("TLDSignature_id").sendKeys("RVOza");
			logs.info("Enter Signature");

			// --Click on Confirm DL
			isElementPresent("TLDConfDL_id").click();
			logs.info("Clicked on Confirm DEL button");
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

					// --Enter SIgnature
					isElementPresent("TLDSignature_id").clear();
					isElementPresent("TLDSignature_id").sendKeys("RVOza");
					logs.info("Enter Signature");

					// --Enter Actual DL time
					isElementPresent("TLDActDLTime_id").clear();
					isElementPresent("TLDActDLTime_id").sendKeys(rdytime);
					logs.info("Enter Actual DL Time");

					// --Click on Confirm DL button
					WebElement ConfDL = isElementPresent("TLDConfDL_id");
					wait.until(ExpectedConditions.elementToBeClickable(ConfDL));
					Thread.sleep(2000);
					act.moveToElement(ConfDL).click().build().perform();
					logs.info("Click on Confirm DEL button");
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

				}

			} catch (Exception PModify) {
				logs.info("Validation message is not displayed for Recalculate the charges");

			}

			// --Pop Up
			boolean dlpop = driver.getPageSource().contains("NetLink Global Logistics");

			if (dlpop == true) {
				isElementPresent("TLDPUOK_id").click();
				logs.info("Clicked on OK button of pop up");

			}

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
		}

		if (svc.equals("SD") || svc.equals("PA") || svc.equals("FRA")) {
			// --Get the timeZone
			String tzone = isElementPresent("TLDACDTimeZone_id").getText();
			String rectime = getTime(tzone);

			// --Enter Actual Del Time
			isElementPresent("TLDActDLTime_id").clear();
			isElementPresent("TLDActDLTime_id").sendKeys(rectime);
			isElementPresent("TLDActDLTime_id").sendKeys(Keys.TAB);

			// --Enter Signature
			isElementPresent("TLDSignature_id").clear();
			isElementPresent("TLDSignature_id").sendKeys("RVOza");
			logs.info("Enter Signature");

			// --Click on Confirm DL
			WebElement ConDL = isElementPresent("TLDConfDL2_id");
			wait.until(ExpectedConditions.elementToBeClickable(ConDL));
			jse.executeScript("arguments[0].click();", ConDL);
			logs.info("Clicked on Confirm DEL button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}

		if (svc.equals("AIR")) {
			// --Get the timeZone
			String tzone = isElementPresent("TLDAIRTZone_id").getText();
			String rectime = getTime(tzone);

			// --Enter Actual Del Time
			isElementPresent("TLDAIRActualDTime_id").clear();
			isElementPresent("TLDAIRActualDTime_id").sendKeys(rectime);
			isElementPresent("TLDAIRActualDTime_id").sendKeys(Keys.TAB);

			// --Enter Signature
			isElementPresent("TLDAIRSign_id").clear();
			isElementPresent("TLDAIRSign_id").sendKeys("RVOza");
			logs.info("Enter Signature");

			// --Click on Confirm DL
			WebElement ConDL = isElementPresent("TLDAIRDLStage_id");
			wait.until(ExpectedConditions.elementToBeClickable(ConDL));
			jse.executeScript("arguments[0].click();", ConDL);
			logs.info("Clicked on Confirm DEL button");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

		}

	}

	public static String getTime(String timeZone) {

		System.out.println("ZoneID of is==" + timeZone);
		logs.info("ZoneID of is==" + timeZone);
		if (timeZone.equalsIgnoreCase("EDT")) {
			timeZone = "America/New_York";
		} else if (timeZone.equalsIgnoreCase("CDT")) {
			timeZone = "CST";
		} else if (timeZone.equalsIgnoreCase("PDT")) {
			timeZone = "PST";
		}

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		logs.info(dateFormat.format(date));
		String Time = dateFormat.format(date);
		System.out.println("Time==" + Time);
		return Time;

	}
}
