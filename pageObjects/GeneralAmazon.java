package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeneralAmazon {
	private static WebElement element = null;
	
	public static WebElement goToCart(ChromeDriver driver) {
		element = driver.findElement(By.id("nav-cart"));
		
		return element;
	}
	
	public static WebElement searchBar(ChromeDriver driver) {
		
		element = driver.findElement(By.id("twotabsearchtextbox"));

		return element;
	}
	
	public static WebElement searchButton(ChromeDriver driver) {
		
		element = driver.findElement(By.cssSelector(".nav-input"));
		
		return element;
	}
}
