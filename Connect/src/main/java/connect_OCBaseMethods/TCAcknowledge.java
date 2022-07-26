package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class TCAcknowledge extends BaseInit {
	
	@Test
	public static void tcAcknowledge() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		Thread.sleep(2000);
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		System.out.println(svc);
		if(svc.equals("LOC") || svc.equals("P3P") || svc.equals("DRV")|| svc.equals("SDC")|| svc.equals("FRG"))
			{
			driver.findElement(By.id("GreenTick")).click();
			}
		
		if(svc.equals("SD") || svc.equals("PA") || svc.equals("AIR")|| svc.equals("FRA"))
			{
			driver.findElement(By.id("btnGreenTick")).click();
			}
	}
}
