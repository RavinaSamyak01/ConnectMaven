package connect_OCBaseMethods;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_OrderCreation.DRV;
import connect_OrderCreation.FRA;
import connect_OrderCreation.FRG;
import connect_OrderCreation.LOC;
import connect_OrderCreation.P3P;
import connect_OrderCreation.PA;
import connect_OrderCreation.SD;
import connect_OrderCreation.SDC;

public class ServiceDetail extends BaseInit {
	static String pck, rdytime, rectime, arrtime, tndrtime;

	@Test
	public static void SvcDetail() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		Robot r = new Robot();

		for (int i = 2; i < 10; i++) {
			Thread.sleep(2000);
			WebElement Order = wait.until(ExpectedConditions.elementToBeClickable(By.id("hlkNewOrder"))); // Click on
																											// New Order
																											// Link
			jse.executeScript("arguments[0].click();", Order);
			Thread.sleep(10000);

			ExcelDataProvider excelDataProvider = new ExcelDataProvider(); // Call ExcelDataProvider class for Read/
																			// Write data
			excelDataProvider.getData("Sheet1", i, 0);
			Thread.sleep(5000);

			// Enter Caller
			String Caller = excelDataProvider.getData("Sheet1", i, 0);
			driver.findElement(By.id("txtCallerName")).sendKeys(Caller);
			// Enter Phone
			String Phone = excelDataProvider.getData("Sheet1", i, 1);
			driver.findElement(By.id("txtContactPhone")).sendKeys(Phone);
			Thread.sleep(1000);

			// Enter Account#
			String Account = excelDataProvider.getData("Sheet1", i, 2);
			driver.findElement(By.id("txtCustCode")).sendKeys(Account);
			driver.findElement(By.id("txtCustCode")).sendKeys(Keys.TAB);
			Thread.sleep(10000);

			String PUZip = excelDataProvider.getData("Sheet1", i, 3); // Enter Pickup Zip code
			driver.findElement(By.id("txtPUZipcode")).sendKeys(PUZip);
			driver.findElement(By.id("txtPUZipcode")).sendKeys(Keys.TAB);
			Thread.sleep(2000);

			WebElement Puaddr = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='idPUAddr']")));
			jse.executeScript("arguments[0].click();", Puaddr);
			Thread.sleep(4000);

			String PickupCom = excelDataProvider.getData("Sheet1", i, 4);
			driver.findElement(By.id("txtPUCompany")).sendKeys(PickupCom); // Enter Pickup Company Name

			String PUAddress1 = excelDataProvider.getData("Sheet1", i, 5);
			driver.findElement(By.id("txtPUAddrLine1")).sendKeys(PUAddress1); // Pu Address Line 1

			// String Add2 = excelDataProvider.getData("Sheet1", i, 6);
			// driver.findElement(By.id("txtPUAddrLine2")).sendKeys(Add2);

			String Attn = excelDataProvider.getData("Sheet1", i, 7);
			driver.findElement(By.id("txtPUAttention")).sendKeys(Attn); // Pu Attn

			String PuPhone = excelDataProvider.getData("Sheet1", i, 8);
			driver.findElement(By.id("txtPUPhone")).sendKeys(PuPhone); // Pickup Phone

			// String PUInst = excelDataProvider.getData("Sheet1", i, 9);
			// driver.findElement(By.id(" ")).sendKeys(PUInst);
			Thread.sleep(6000);
			rdytime = driver.findElement(By.id("txtReadyforPickupTime")).getAttribute("value"); // Get Ready Time in
																								// variable
			rectime = driver.findElement(By.id("txtReadyforPickupTime")).getAttribute("value");
			arrtime = driver.findElement(By.id("txtReadyforPickupTime")).getAttribute("value");
			// tndrtime = driver.findElement(By.id("txtReadyforPickupTime")).getText();

			Thread.sleep(3000);
			String pmi = driver.findElement(By.id("txtPUMiles")).getAttribute("value");
			Thread.sleep(1000);

			String DLZip = excelDataProvider.getData("Sheet1", i, 11);
			driver.findElement(By.id("txtDLZipCode")).sendKeys(DLZip);
			driver.findElement(By.id("txtDLZipCode")).sendKeys(Keys.TAB);
			Thread.sleep(12000);

			WebElement DL = driver.findElement(By.xpath("//*[@id='idDLAddr']"));
			jse.executeScript("arguments[0].click();", DL);

			Thread.sleep(2000);

			String DelCompany = excelDataProvider.getData("Sheet1", i, 12);
			driver.findElement(By.id("txtDLCompany")).sendKeys(DelCompany);

			String DLAddress1 = excelDataProvider.getData("Sheet1", i, 13);
			driver.findElement(By.id("txtDLAddrLine1")).sendKeys(DLAddress1);

			// String DLAddr2 = excelDataProvider.getData("Sheet1", i, 14);
			// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

			Thread.sleep(3000);

			String DLAttn = excelDataProvider.getData("Sheet1", i, 15);
			driver.findElement(By.id("txtDLAttention")).sendKeys(DLAttn);

			String DLPhone = excelDataProvider.getData("Sheet1", i, 16);
			driver.findElement(By.id("txtDLPhone")).sendKeys(DLPhone);

			Thread.sleep(3000);
			String dmi = driver.findElement(By.id("txtDelMiles")).getAttribute("value");

			// String DLInst = excelDataProvider.getData("Sheet1", i, 17);
			// driver.findElement(By.id("txtDLPhone")).sendKeys(DLInst);
			Thread.sleep(1000);
			// String srv =
			// driver.findElement(By.id("idNewOrderServiceId")).getAttribute("value");

			Thread.sleep(3000);
			driver.findElement(By.id("txtTotalQty_NewOrder")).clear();
			driver.findElement(By.id("txtTotalQty_NewOrder")).sendKeys("1");
			driver.findElement(By.id("txtTotalQty_NewOrder")).sendKeys(Keys.TAB);

			Thread.sleep(1000);
			String Weight = excelDataProvider.getData("Sheet1", i, 19);
			driver.findElement(By.xpath(
					"html/body/div[2]/section/div[2]/div/div/div[2]/div[2]/div/ng-form[2]/div[3]/div/fieldset/div[2]/package-controller/div[3]/div[2]/fieldset/div/div[2]/div[2]/div[1]/input"))
					.clear();
			driver.findElement(By.xpath(
					"html/body/div[2]/section/div[2]/div/div/div[2]/div[2]/div/ng-form[2]/div[3]/div/fieldset/div[2]/package-controller/div[3]/div[2]/fieldset/div/div[2]/div[2]/div[1]/input"))
					.sendKeys(Weight);
			driver.findElement(By.xpath(
					"html/body/div[2]/section/div[2]/div/div/div[2]/div[2]/div/ng-form[2]/div[3]/div/fieldset/div[2]/package-controller/div[3]/div[2]/fieldset/div/div[2]/div[2]/div[1]/input"))
					.sendKeys(Keys.TAB);

			Thread.sleep(1000);

			String Len = excelDataProvider.getData("Sheet1", i, 20);
			driver.findElement(By.id("txtlength_NewOrder")).clear();
			driver.findElement(By.id("txtlength_NewOrder")).sendKeys(Len);

			String Width = excelDataProvider.getData("Sheet1", i, 21);
			driver.findElement(By.id("txtwidth_NewOrder")).clear();
			driver.findElement(By.id("txtwidth_NewOrder")).sendKeys(Width);

			String Height = excelDataProvider.getData("Sheet1", i, 22);
			driver.findElement(By.id("txtheight_NewOrder")).clear();
			driver.findElement(By.id("txtheight_NewOrder")).sendKeys(Height);

			// Thread.sleep(6000);

			String Commodity = excelDataProvider.getData("Sheet1", i, 23);
			driver.findElement(By.id("txtdescription_NewOrder")).sendKeys(Commodity);
			driver.findElement(By.id("txtdescription_NewOrder")).sendKeys(Keys.TAB);

			Thread.sleep(7000);
			r.keyPress(KeyEvent.VK_TAB);

			jse.executeScript("window.scrollBy(0,250)", "");

			Thread.sleep(3000);

			// driver.findElement(By.xpath("//*[@id='ShipmentChargeSection']/fieldset/div[1]/div[1]/div[1]/a")).click();
			Thread.sleep(9000);

			String tmile = driver.findElement(By.id("txtTotalMileage")).getAttribute("value");

			excelDataProvider.writeData("Sheet1", i, 25, pmi);
			excelDataProvider.writeData("Sheet1", i, 27, dmi);
			excelDataProvider.writeData("Sheet1", i, 29, tmile);

			if (i == 1) // LOC service
			{
				Thread.sleep(3000);
				LOC.locLocal();

			}

			if (i == 2) // SD Service
			{
				Thread.sleep(3000);
				SD.sdSameDay();
			}

			if (i == 3) // P3P Service
			{
				Thread.sleep(3000);
				P3P.p3pservice();
			}

			if (i == 4) // PA Service
			{
				Thread.sleep(3000);
				PA.paPriorityAir();
			}

			if (i == 5) // DRV Service with HAS
			{
				Thread.sleep(3000);
				DRV.drvDriver();

			}

			if (i == 6) // AIR with HAA
			{
				Thread.sleep(3000);
				AIR.airService();

			}

			if (i == 7) // SDC Service
			{
				Thread.sleep(3000);
				SDC.sdcSameDayCity();

			}

			if (i == 8) // FRA Service
			{
				Thread.sleep(3000);
				FRA.fraFreight();

			}

			if (i == 9) // FRG Service
			{
				Thread.sleep(3000);
				FRG.frgFreight();

			}
		}
	}
}
