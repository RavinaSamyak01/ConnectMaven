package connect_OCBaseMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;

public class SendDelAlert extends BaseInit {
    @Test
	public static void delAlert() throws Exception
	{
		Thread.sleep(4000);
		
		Select Contacttype = new Select(driver.findElement(By.id("cmbContacted")));
		Contacttype.selectByVisibleText("Email");
		Thread.sleep(1000);
		
		driver.findElement(By.id("lblContactedValue1")).clear();
		driver.findElement(By.id("lblContactedValue1")).sendKeys("asharma@samyak.com");			
		
		driver.findElement(By.id("txtSpokeWith")).clear();
		driver.findElement(By.id("txtSpokeWith")).sendKeys("Abhishek");
		Thread.sleep(1000);
		
		driver.findElement(By.id("alertconfirmbtn")).click();
		Thread.sleep(5000);
	}
}
