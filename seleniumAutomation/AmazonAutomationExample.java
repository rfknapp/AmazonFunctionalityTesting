package seleniumAutomation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pageObjects.GeneralAmazon;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import pageObjects.ItemListPage;

public class AmazonAutomationExample {
	
	private static ChromeDriver driver;
	private static int numberOfItems;
	private static int totalSearchTerms;
	
	public static void main(String[] args) throws InterruptedException {
	
		File file = new File("D:/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		//This will store all preferences 
		Map<String, Object> prefs = new HashMap<String, Object>();
	
		//This will switch off browser notification
		prefs.put("profile.default_content_setting_values.notifications", 2);
	
		//Create chrome options to set this prefs
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--incognito");
		
		driver = new ChromeDriver(options);

		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		String[] searchTerms = {"LG 24UD58-B 24-Inch 4k Monitor", "Metasploit: The Penetration Tester's Guide Book", "cup with a cat on it"};
		String[] asins = new String[searchTerms.length];
		
		totalSearchTerms = searchTerms.length;
		
		for (int i = 0; i < totalSearchTerms; i++) {
			if(searchTerms[i] == "cup with a cat on it") {
				asins[i] = SearchForAndAddToCart(searchTerms[i], driver, true);
			} else {
				asins[i] = SearchForAndAddToCart(searchTerms[i], driver, false);
			}
		}
		
		GeneralAmazon.goToCart(driver).click();
		ShoppingCartPage.assertOnAmazonCartPage(driver);
		System.out.println("PASS: We are on the shopping car page.");
		numberOfItems = ShoppingCartPage.numberOfItemsInCart(driver).size();
		ShoppingCartPage.assertShoppingCartSize(driver, totalSearchTerms, numberOfItems);
		System.out.println("PASS: The number of items in the cart is " + totalSearchTerms);
		
		String itemName = ShoppingCartPage.getItemText(driver, asins[0]).getText();
		ShoppingCartPage.deleteLink(driver, asins[0]).click();
		Thread.sleep(1000);
		
		String deletedMessage = ShoppingCartPage.getDeletedMessage(driver, asins[0]).getText();
		ShoppingCartPage.assertDeletedMessage(driver, itemName, deletedMessage);
		System.out.println("PASS: " + itemName + " was removed from Shopping Cart.");		
		
		GeneralAmazon.goToCart(driver).click();
		ShoppingCartPage.assertOnAmazonCartPage(driver);
		System.out.println("PASS: We are on the shopping car page.");
		numberOfItems = ShoppingCartPage.numberOfItemsInCart(driver).size();
		ShoppingCartPage.assertShoppingCartSize(driver, totalSearchTerms-1, numberOfItems);
		System.out.println("PASS: The number of items in the cart is " + (totalSearchTerms-1));		
		
		driver.quit();
		
	}
	
	public static String SearchForAndAddToCart(String searchTerm, ChromeDriver driver, Boolean takeScreenshot) throws InterruptedException {
		
		GeneralAmazon.searchBar(driver).sendKeys(searchTerm);
		GeneralAmazon.searchButton(driver).click();
		
		SearchPage.assertOnAmazonSearchPage(driver);
		System.out.println("PASS: We are on the search page.");
		
		SearchPage.firstSearchResult(driver).click();
		
		String itemAsin = ItemListPage.asinForItem(driver).getAttribute("value");
		ItemListPage.assertOnAmazonItemListPage(driver);
		System.out.println("PASS: We are on the item list.");
		
		
		if(takeScreenshot) {
			// Take screenshot and store as a file format
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
			 // now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File("D:/selenium/screenshot.png"));
			} catch (IOException e) {
				  System.out.println(e.getMessage());
			}
		}
		
		ItemListPage.addToCart(driver).click();
		ItemListPage.clickDenyProtectionIfExists(driver);
		
		ItemListPage.assertOnAmazonItemAddedToCartPage(driver);
		System.out.println("PASS: We are on the item added to cart page.");
		
		return itemAsin;
		
	}
}
