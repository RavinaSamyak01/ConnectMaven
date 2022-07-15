package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class WaitForDeptarture extends ServiceDetail {
	
    @Test
	public static void waitForDept() throws Exception
	{
				Thread.sleep(6000);
				driver.findElement(By.id("txtDepartureTime")).sendKeys(rdytime);
				driver.findElement(By.id("txtDepartureTime")).sendKeys(Keys.TAB);
				Thread.sleep(6000);
				driver.findElement(By.id("btnWaitGreyTick")).click();

	}
}
