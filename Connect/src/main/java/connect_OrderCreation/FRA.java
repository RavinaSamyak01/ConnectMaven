package connect_OrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OCBaseMethods.Board;
import connect_OCBaseMethods.Board1;
import connect_OCBaseMethods.Deliver;
import connect_OCBaseMethods.Drop;
import connect_OCBaseMethods.ExcelDataProvider;
import connect_OCBaseMethods.Pickup;
import connect_OCBaseMethods.ReadyForDispatch;
import connect_OCBaseMethods.Recover;
import connect_OCBaseMethods.SendDelAlert;
import connect_OCBaseMethods.ServiceDetail;
import connect_OCBaseMethods.TCAcknowledge;
import connect_OCBaseMethods.WaitForArrival;
import connect_OCBaseMethods.WaitForDeptarture;
import connect_OCBaseMethods.XerWaitForArrival;
import connect_OCBaseMethods.XerWaitForDeparture;

public class FRA extends ServiceDetail {

	@Test
	public static void fraFreight() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		
		jse.executeScript("window.scrollBy(0,-350)", "");
		driver.findElement(By.id("idNewOrderServiceId")).clear();
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys("FRA");
		driver.findElement(By.id("idNewOrderServiceId")).sendKeys(Keys.TAB);
		Thread.sleep(9000);
		
		driver.findElement(By.id("idSendByAir")).click();
		Thread.sleep(7000);
		driver.findElement(By.id("txtVerifyFedex")).sendKeys("Knows Shipper Test");
		Thread.sleep(10000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(7000);
		
		
		jse.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(3000);
		driver.findElement(By.id("btnOrderProcess")).click();
		Thread.sleep(9000);
		
		boolean sameairport = driver.getPageSource().contains("Pickup and Delivery airport are different. Do you want to make it same?");			
		
			if(sameairport == true) {
				driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/div/div[2]/span/button[1]")).click();							
			}
		

		String pck = driver.findElement(By.xpath("//*[@id='lblPickup']/span/b")).getText();
		System.out.println("Service FRA :: Pickup # " +pck);
		logs.info("Service FRA :: Pickup # "+ pck + "\n");
		Thread.sleep(1000);
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		excelDataProvider.writeData("Sheet1", 8, 32, pck);
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='hlkGoDirectlytoEditOrder']")).click();
		Thread.sleep(9000);
		
		WebElement Job = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='idJobOverview']")));
		jse.executeScript("arguments[0].click();",Job);
		Thread.sleep(5000);
		WebElement Selectflight = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSelectFlights")));
		jse.executeScript("arguments[0].click();",Selectflight);
		Thread.sleep(5000);
		WebElement sel = wait.until(ExpectedConditions.elementToBeClickable(By.id("hlkSel_0")));
		jse.executeScript("arguments[0].click();",sel);
		Thread.sleep(9000);
		WebElement assign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Assign')]")));
		jse.executeScript("arguments[0].click();",assign);
		Thread.sleep(15000);
				
				//TC Acknowledge
				Thread.sleep(7000);
				TCAcknowledge.tcAcknowledge();
				
				//Pickup Alert
				Thread.sleep(7000);
				ReadyForDispatch.pickupAlert();
		
				//PICKEDUP
				Thread.sleep(3000);
				Pickup.confirmPickup();
		
				//DROP
				Thread.sleep(3000);
				Drop.dropAtOrigin();
				
				//Send Del Alert
				Thread.sleep(3000);
				SendDelAlert.delAlert();
				
				//Wait for Departure
				Thread.sleep(5000);
				WaitForDeptarture.waitForDept();

				//OnBorad
				Thread.sleep(5000);
				Board.onBoard();
				
				//XER wait for Arrival
				Thread.sleep(5000);
				XerWaitForArrival.xerWaitForArr();
						
				//XER Wait for Departure
				Thread.sleep(5000);
				XerWaitForDeparture.xerWaitForDept();
				
				//board2
				Thread.sleep(5000);
				Board1.onBoard1();
				
				//Wait for Arrival
				Thread.sleep(5000);
				WaitForArrival.waitForArr();
				
				//Recover
				Thread.sleep(3000);
				Recover.recoverAtDestination();
				
				//DELIVERED
				Thread.sleep(3000);
				Deliver.confirmDelivery();

		Thread.sleep(3000);
	}
}
