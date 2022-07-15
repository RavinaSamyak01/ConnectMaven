package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ReAlertForDelivery extends BaseInit{
    @Test
	public static void reAlertfordel() throws Exception
	{
		Thread.sleep(5000);
		driver.findElement(By.id("AlertConfirmbtn")).click();
	}

}
