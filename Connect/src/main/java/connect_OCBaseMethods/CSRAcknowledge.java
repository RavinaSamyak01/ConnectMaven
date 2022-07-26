package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class CSRAcknowledge extends BaseInit  {

        @Test	
		public static void csrAcknowledge() throws Exception
		{
			Thread.sleep(7000);
			String svc = driver.findElement(By.id("lblServiceID")).getText();
			System.out.println(svc);

			if(svc.equals("LOC") || svc.equals("P3P"))
				{
				driver.findElement(By.id("GreenTick")).click();
				}
			
			if(svc.equals("SD") || svc.equals("PA"))
				{
				driver.findElement(By.id("btnGreenTick")).click();
				}
		}
}
