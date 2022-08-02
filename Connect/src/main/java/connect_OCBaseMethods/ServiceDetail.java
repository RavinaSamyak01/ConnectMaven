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
import connect_BasePackage.BaseInit;
import connect_OrderCreation.AIR;
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
	public void SvcDetail() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
//		/Actions act = new Actions(driver);
		Robot r = new Robot();

		for (int i = 1; i < 10; i++) {

			// --Click on New Order
			WebElement Order = isElementPresent("NewOrder_id");
			wait.until(ExpectedConditions.visibilityOf(Order));
			wait.until(ExpectedConditions.elementToBeClickable(Order));
			jse.executeScript("arguments[0].click();", Order);

			// --Waiting for Order section
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("idOrder")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --get the Data
			getData("Sheet1", i, 0);

			// Enter Caller
			String Caller = getData("Sheet1", i, 0);
			isElementPresent("OCCallerName_id").clear();
			isElementPresent("OCCallerName_id").sendKeys(Caller);
			logs.info("Entered CallerName");

			// Enter Phone
			String Phone = getData("Sheet1", i, 1);
			isElementPresent("OCContactPh_id").clear();
			isElementPresent("OCContactPh_id").sendKeys(Phone);
			logs.info("Entered Contact/Phone");

			// Enter Account#
			String Account = getData("Sheet1", i, 2);
			isElementPresent("OCCustCode_id").clear();
			isElementPresent("OCCustCode_id").sendKeys(Account);
			isElementPresent("OCCustCode_id").sendKeys(Keys.TAB);
			logs.info("Entered Customer Code");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --wait until pU section is enabled
			try {
				wait.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//*[@id=\"PickupSection\"][@disabled=\"disabled\"]")));
			} catch (Exception PUDIsable) {
				logs.error(PUDIsable);
				getScreenshot(driver, "PUSDisabled");
				WebDriverWait waitPUD = new WebDriverWait(driver, 120);// wait time
				waitPUD.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//*[@id=\"PickupSection\"][@disabled=\"disabled\"]")));
				logs.info("PU Section is Enabled");

			}
			// Enter Pickup Zip code
			String PUZip = getData("Sheet1", i, 3);
			isElementPresent("OCPUZp_id").clear();
			isElementPresent("OCPUZp_id").sendKeys(PUZip);
			isElementPresent("OCPUZp_id").sendKeys(Keys.TAB);
			logs.info("Entered PU Zip");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --PU Address
			WebElement Puaddr = isElementPresent("OCPUAdd_id");
			wait.until(ExpectedConditions.elementToBeClickable(Puaddr));
			jse.executeScript("arguments[0].click();", Puaddr);
			logs.info("Click on PU Address");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --PU Company
			String PickupCom = getData("Sheet1", i, 4);
			isElementPresent("OCPUComp_id").clear();
			isElementPresent("OCPUComp_id").sendKeys(PickupCom);
			logs.info("Entered PU Company");

			// --PU AddressLine1
			String PUAddress1 = getData("Sheet1", i, 5);
			isElementPresent("OCPUAddL1_id").clear();
			isElementPresent("OCPUAddL1_id").sendKeys(PUAddress1);
			logs.info("Entered PU AddressLine1");

			// String Add2 = getData("Sheet1", i, 6);
			// driver.findElement(By.id("txtPUAddrLine2")).sendKeys(Add2);

			// --PU Attention
			String Attn = getData("Sheet1", i, 7);
			isElementPresent("OCPUAtt_id").clear();
			isElementPresent("OCPUAtt_id").sendKeys(Attn);
			logs.info("Entered PU Attention");

			// --PU Phone
			String PuPhone = getData("Sheet1", i, 8);
			isElementPresent("OCPUPhone_id").clear();
			isElementPresent("OCPUPhone_id").sendKeys(PuPhone);
			logs.info("Entered PU Phone");

			// String PUInst = getData("Sheet1", i, 9);
			// driver.findElement(By.id(" ")).sendKeys(PUInst);

			// --Wait to get PU Ready time
			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(
						By.xpath("//input[contains(@class,'ng-invalid ng-invalid-required')]")));
				logs.info("PU Ready time is blank");
			} catch (Exception PUTimeNotExist) {
				logs.error(PUTimeNotExist);
				logs.info("PU Ready time is exist");

			}
			// --Getting ready PickupTime
			rdytime = isElementPresent("OCPURTime_id").getAttribute("value");
			logs.info("PU Ready Time==" + rdytime);

			rectime = isElementPresent("OCPURTime_id").getAttribute("value");
			logs.info("PU Receive Time==" + rectime);

			arrtime = isElementPresent("OCPURTime_id").getAttribute("value");
			logs.info("PU Arrival Time==" + arrtime);

			// tndrtime = driver.findElement(By.id("txtReadyforPickupTime")).getText();

			// --PU Miles
			String pmi = isElementPresent("OCPUMiles_id").getAttribute("value");
			logs.info("PU Mileage==" + pmi);

			// --Del Zip
			String DLZip = getData("Sheet1", i, 11);
			isElementPresent("OCDLZip_id").clear();
			isElementPresent("OCDLZip_id").sendKeys(DLZip);
			logs.info("Entered DL Zip");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));

			// --Del Address
			WebElement DL = isElementPresent("OCDLAdd_id");
			jse.executeScript("arguments[0].click();", DL);
			logs.info("Entered DL Address");

			// --DEL Company
			String DelCompany = getData("Sheet1", i, 12);
			isElementPresent("OCDLComp_id").clear();
			isElementPresent("OCDLComp_id").sendKeys(DelCompany);
			logs.info("Entered DL Company");

			// --DEL Address1
			String DLAddress1 = getData("Sheet1", i, 13);
			isElementPresent("OCDLAddL1_id").clear();
			isElementPresent("OCDLAddL1_id").sendKeys(DLAddress1);
			logs.info("Entered DL Address Line1");

			// String DLAddr2 = getData("Sheet1", i, 14);
			// driver.findElement(By.id("txtDelAddrLine2")).sendKeys(DLAddr2);

			// --DL Attention
			String DLAttn = getData("Sheet1", i, 15);
			isElementPresent("OCDLAtt_id").clear();
			isElementPresent("OCDLAtt_id").sendKeys(DLAttn);
			logs.info("Entered DL Attention");

			// --DL Phone
			String DLPhone = getData("Sheet1", i, 16);
			isElementPresent("OCDLPhone_id").clear();
			isElementPresent("OCDLPhone_id").sendKeys(DLPhone);
			logs.info("Entered DL Phone");

			// --DL Miles
			String dmi = isElementPresent("OCDLMiles_id").getAttribute("value");
			logs.info("DL Miles==" + dmi);

			// String DLInst = getData("Sheet1", i, 17);
			// driver.findElement(By.id("txtDLPhone")).sendKeys(DLInst);
			// String srv =
			// driver.findElement(By.id("idNewOrderServiceId")).getAttribute("value");

			// --Total Qty
			isElementPresent("OCTotalQty_id").clear();
			// isElementPresent("OCTotalQty_id").sendKeys(Keys.BACK_SPACE);
			isElementPresent("OCTotalQty_id").sendKeys(Keys.CONTROL, "a");
			isElementPresent("OCTotalQty_id").sendKeys(Keys.BACK_SPACE);
			isElementPresent("OCTotalQty_id").sendKeys("1");
			isElementPresent("OCTotalQty_id").sendKeys(Keys.TAB);
			Thread.sleep(2000);
			logs.info("Entered Total Qty");

			// --Weight
			String Weight = getData("Sheet1", i, 19);
			isElementPresent("OCWeight_id").clear();
			isElementPresent("OCWeight_id").sendKeys(Weight);
			isElementPresent("OCWeight_id").sendKeys(Keys.TAB);
			logs.info("Entered Weight");

			// --Length
			String Len = getData("Sheet1", i, 20);
			isElementPresent("OCLength_id").clear();
			isElementPresent("OCLength_id").sendKeys(Len);
			isElementPresent("OCLength_id").sendKeys(Keys.TAB);
			logs.info("Entered Length");

			// --Width
			String Width = getData("Sheet1", i, 21);
			isElementPresent("OCWidth_id").clear();
			isElementPresent("OCWidth_id").sendKeys(Width);
			isElementPresent("OCWidth_id").sendKeys(Keys.TAB);
			logs.info("Entered Width");

			// --Height
			String Height = getData("Sheet1", i, 22);
			isElementPresent("OCHeight_id").clear();
			isElementPresent("OCHeight_id").sendKeys(Height);
			isElementPresent("OCHeight_id").sendKeys(Keys.TAB);
			logs.info("Entered Height");

			// --Commodity
			String Commodity = getData("Sheet1", i, 23);
			isElementPresent("OCDesc_id").clear();
			isElementPresent("OCDesc_id").sendKeys(Commodity);
			isElementPresent("OCDesc_id").sendKeys(Keys.TAB);
			logs.info("Entered Commodity");

			// --Scroll Up
			r.keyPress(KeyEvent.VK_TAB);
			jse.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(2000);

			// --Total Mileage
			String tmile = isElementPresent("OCTotalMil_id").getAttribute("value");
			logs.info("Total Mileage==" + tmile);

			setData("Sheet1", i, 25, pmi);
			setData("Sheet1", i, 27, dmi);
			setData("Sheet1", i, 29, tmile);

			if (i == 1) // LOC service
			{
				logs.info("Service=LOC");
				msg.append("Service=LOC" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				LOC loc=new LOC();
				loc.locLocal();

			}

			if (i == 2) // SD Service
			{
				logs.info("Service=SD");
				msg.append("Service=SD" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				SD.sdSameDay();
			}

			if (i == 3) // P3P Service
			{
				logs.info("Service=P3P");
				msg.append("Service=P3P" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				P3P.p3pservice();
			}

			if (i == 4) // PA Service
			{
				logs.info("Service=PA");
				msg.append("Service=PA" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				PA.paPriorityAir();
			}

			if (i == 5) // DRV Service with HAS
			{
				logs.info("Service=DRV");
				msg.append("Service=DRV" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				DRV.drvDriver();

			}

			if (i == 6) // AIR with HAA
			{
				logs.info("Service=AIR with HAA");
				msg.append("Service=AIR with HAA" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				AIR.airService();

			}

			if (i == 7) // SDC Service
			{
				logs.info("Service=SDC");
				msg.append("Service=SDC" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				SDC.sdcSameDayCity();

			}

			if (i == 8) // FRA Service
			{
				logs.info("Service=FRA");
				msg.append("Service=FRA" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				FRA.fraFreight();

			}

			if (i == 9) // FRG Service
			{
				logs.info("Service=FRG");
				msg.append("Service=FRG" + "\n");
				msg.append("PU Mileage=" + pmi + "\n");
				msg.append("DL Mileage=" + dmi + "\n");
				msg.append("Total Mileage=" + tmile + "\n\n");

				FRG.frgFreight();

			}
		}
	}
}
