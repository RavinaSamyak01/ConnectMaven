package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Pickup extends ServiceDetail {
	
	@Test
	public static void confirmPickup() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);
		if(svc.equals("LOC") || svc.equals("P3P") || svc.equals("DRV")|| svc.equals("SDC")|| svc.equals("FRG"))
		{		
			Thread.sleep(5000);
			driver.findElement(By.id("txtEditActualPickupTime")).sendKeys(rdytime);
			driver.findElement(By.id("txtEditActualPickupTime")).sendKeys(Keys.TAB);
			Thread.sleep(9000);
			driver.findElement(By.id("GreenTickDropped")).click();
			Thread.sleep(8000);
		}
		
		if(svc.equals("SD") || svc.equals("PA") || svc.equals("AIR")|| svc.equals("FRA"))
		{		
			Thread.sleep(5000);
			driver.findElement(By.id("txtEditActualPickupTime")).sendKeys(rdytime);
			driver.findElement(By.id("txtEditActualPickupTime")).sendKeys(Keys.TAB);
			Thread.sleep(9000);
			driver.findElement(By.id("btnGreenTickDropped")).click();
			Thread.sleep(8000);
		}
		
	}

}
