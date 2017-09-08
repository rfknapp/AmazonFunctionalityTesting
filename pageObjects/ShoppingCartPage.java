package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class ShoppingCartPage  {
	private static WebElement element = null;
	private static List<WebElement> elements;
	
	public static WebElement deleteLink(ChromeDriver driver, String asin) {
		
		element = driver.findElement(By.xpath("//div[@data-asin='" + asin + "']//input[@value='Delete']"));
		
		return element;
	}
	
	public static List<WebElement> numberOfItemsInCart(ChromeDriver driver) {
		
		elements = driver.findElements(By.cssSelector(".sc-list-item-content"));
		
		return elements;
	}
	
	@SuppressWarnings("deprecation")
	public static void assertShoppingCartSize(ChromeDriver driver, int totalSearchTerms, int numberOfItems) {
		try {
			Assert.assertEquals("The number of items in the cart should be " + totalSearchTerms, totalSearchTerms, numberOfItems);
		} catch (AssertionError ex) {
			System.out.println("FAIL! The exception was: " + ex.getMessage());
			driver.quit();
		}
	}
	
	public static WebElement getDeletedMessage(ChromeDriver driver, String asin) {
		
		element = driver.findElement(By.xpath("//div[@data-asin='" + asin + "']//div[@class='sc-list-item-removed-msg a-padding-medium']/div/span"));
		
		return element;
	}
	
	public static WebElement getItemText(ChromeDriver driver, String asin) {
		
		element = driver.findElement(By.xpath("//div[@data-asin='" + asin + "']//span[@class='a-size-medium sc-product-title a-text-bold']"));
		
		return element;
	}
	
	@SuppressWarnings("deprecation")
	public static void assertDeletedMessage(ChromeDriver driver, String itemName, String deletedMessage) {
		try {
			Assert.assertEquals(itemName + " was removed from Shopping Cart.", deletedMessage);
		} catch (AssertionError ex) {
			System.out.println("FAIL! The exception was: " + ex.getMessage());
			driver.quit();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void assertOnAmazonCartPage(ChromeDriver driver) {
		
		String fullUrl = driver.getCurrentUrl();
		
		try {
			Assert.assertEquals(true, fullUrl.startsWith("https://www.amazon.com/gp/cart/"));
		} catch (AssertionError ex) {
			System.out.println("FAIL! The exception was: " + ex.getMessage());
			driver.quit();
		}
	}
}


