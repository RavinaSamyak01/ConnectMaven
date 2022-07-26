package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class HoldAtOrigin extends BaseInit {
	
	@Test
	public static void hldAtOrigin() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

		Thread.sleep(5000);
		WebElement greenTick = driver.findElement(By.id("btnViewGreenTickDropped"));
		jse.executeScript("arguments[0].click();",greenTick);
	}
}