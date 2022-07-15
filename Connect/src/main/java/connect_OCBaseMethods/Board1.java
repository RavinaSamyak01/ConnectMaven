package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Board1 extends ServiceDetail {

	@Test
	public static void onBoard1() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click

	 
	 WebElement update = driver.findElement(By.id("btnGreenTick"));
	 jse.executeScript("arguments[0].click();",update);
	 Thread.sleep(4000);
	 
	}
}
