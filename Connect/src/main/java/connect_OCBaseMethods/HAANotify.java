package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class HAANotify extends BaseInit{

	@Test
		
		public static void haaNtfy() throws Exception
		{
			Thread.sleep(7000);
			driver.findElement(By.id("hlkOrderSearch")).click();
			for (int j=1; j<2; j++)
			{
				if(j==1) // HAA for AIR
				{
					Thread.sleep(5000);
					ExcelDataProvider excelDataProvider = new ExcelDataProvider();
					String pickupid = excelDataProvider.getData("Sheet1", 6, 32);
					driver.findElement(By.id("txtPickup")).sendKeys(pickupid);
					Thread.sleep(2000);
				}
			}
			
			driver.findElement(By.id("btnSearch")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("lblJobIdValue_0")).click();
			Thread.sleep(6000);
			String svc = driver.findElement(By.id("lblServiceID")).getText();
			System.out.println(svc);
		
			if(svc.equals("AIR"))
			{
				Thread.sleep(4000);
				driver.findElement(By.xpath(".//*[@id='idJobOverview']")).click();
				Thread.sleep(3000);
			}
			
			driver.findElement(By.id("txtHAASpokeWith")).sendKeys("Abhishek Sharma");
			driver.findElement(By.id("txtHAASpokeWith")).sendKeys(Keys.TAB);
			
			driver.findElement(By.id("btnHAAOnHandDeliveryStages")).click();
			Thread.sleep(2000);
		}
}
