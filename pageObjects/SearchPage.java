package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class SearchPage  {
	private static WebElement element = null;
	
	public static WebElement firstSearchResult(ChromeDriver driver) {
		element = driver.findElement(By.xpath("//li[@id='result_0']//h2"));
		
		return element;
	}
	
	@SuppressWarnings("deprecation")
	public static void assertOnAmazonSearchPage(ChromeDriver driver) {
		
		String fullUrl = driver.getCurrentUrl();

		try {
			Assert.assertEquals(true, fullUrl.startsWith("https://www.amazon.com/s/"));
		} catch (AssertionError ex) {
			System.out.println("FAIL! The exception was: " + ex.getMessage());
			driver.quit();
		}
	}

}
