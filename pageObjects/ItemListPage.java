package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ItemListPage {
	private static WebElement element = null;
	private static List<WebElement> elements;
	
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
	
	@SuppressWarnings("deprecation")
	public static void assertOnAmazonItemListPage(ChromeDriver driver) {
		
		elements = driver.findElements(By.id("add-to-wishlist-button-submit"));
		
		try {
			Assert.assertNotEquals(0, elements.size());
		} catch (AssertionError ex) {
			System.out.println("FAIL! The exception was: " + ex.getMessage());
			driver.quit();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void assertOnAmazonItemAddedToCartPage(ChromeDriver driver) {
		
		String fullUrl = driver.getCurrentUrl();
		
		try {
			Assert.assertEquals(true, fullUrl.startsWith("https://www.amazon.com/gp/huc/"));
		} catch (AssertionError ex) {
			System.out.println("FAIL! The exception was: " + ex.getMessage());
			driver.quit();
		}
	}
}
