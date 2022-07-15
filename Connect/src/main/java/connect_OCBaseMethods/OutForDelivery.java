package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OutForDelivery extends BaseInit{

	@Test
    public static void outForDel() throws Exception
	{
		Thread.sleep(5000);
		driver.findElement(By.id("btnViewGreenTickDropped")).click();
	}
}
