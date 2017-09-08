package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ItemListPage {
	private static WebElement element = null;
	
	public static WebElement asinForItem(ChromeDriver driver) {
		
		element = driver.findElement(By.id("ASIN"));
		
		return element;
	}
	
	public static WebElement addToCart(ChromeDriver driver) {
		
		element = driver.findElement(By.id("add-to-cart-button"));
		
		return element;
	}
	
	public static void clickDenyProtectionIfExists(ChromeDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		
		if(driver.findElements(By.id("siNoCoverage-announce")).size() != 0) {
			driver.findElement(By.id("siNoCoverage-announce")).click();
		}
	}
}
