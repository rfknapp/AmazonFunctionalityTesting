package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchPage  {
	private static WebElement element = null;
	
	public static WebElement firstSearchResult(ChromeDriver driver) {
		element = driver.findElement(By.xpath("//li[@id='result_0']//h2"));
		
		return element;
	}

}
