package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class ReadyForDispatch extends BaseInit {

	@Test
	public static void pickupAlert() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		// --Check Contacted
		if (isElementPresent("TLRDContacted_id").isDisplayed()) {
			WebElement email = isElementPresent("TLRDContacted_id");
			wait.until(ExpectedConditions.elementToBeClickable(email));
			jse.executeScript("arguments[0].click();", email);
			Select CBy = new Select(email);
			CBy.selectByValue("number:377");
			System.out.println("email selected");
			logs.info("Email is selected as a Contact By");
		} else {
			Select Contacttype = new Select(isElementPresent("TLRDContacted_id"));
			Contacttype.selectByVisibleText("Email");
			logs.info("Email is selected as a Contact By");

		}

		// --Enter ContactBy Value
		WebElement emailValue = isElementPresent("TLRDContValue_id");
		wait.until(ExpectedConditions.elementToBeClickable(emailValue));
		emailValue.clear();
		emailValue.sendKeys("Ravina.prajapati@samyak.com");
		logs.info("Entered EmailID");

		// --Spoke With
		WebElement spoke = isElementPresent("TLRDSpokeW_id");
		wait.until(ExpectedConditions.elementToBeClickable(spoke));
		spoke.clear();
		spoke.sendKeys("Ravina");
		logs.info("Entered Spoke With");

		// --Click on Send PU Alert
		WebElement Sendpualert = isElementPresent("TLRDSPUALert_id");
		wait.until(ExpectedConditions.elementToBeClickable(Sendpualert));
		Sendpualert.click();
		logs.info("Clicked on Send Pu Alert button");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

	}

}
