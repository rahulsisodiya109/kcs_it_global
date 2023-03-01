package com.kcs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KCS {

	public static WebDriver driver;
	public static Actions act;
	public static JavascriptExecutor jse;

	// @BeforeClass
	public static void setEnv() {
		//System.setProperty("webdriver.chrome.driver","D:\\SELENIUM_AUTOMATION_TESTING\\SOFTWARE\\ChromeDriver110\\chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();
				
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://www.makemytrip.com/flights/");

		act = new Actions(driver);
		jse = (JavascriptExecutor) driver;

	}

	// @Test(priority = 1)
	public void Select_From_Ahmedabad_To_Pune() throws Exception {

		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@aria-controls='react-autowhatever-1']")).sendKeys("amd");
		driver.findElement(By.xpath("//p[contains(text(),'Ahmedabad, India')]")).click();
		act.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@aria-controls='react-autowhatever-1']")).sendKeys("pnq");
		driver.findElement(By.xpath("//p[contains(text(),'Pune, India')]")).click();
		act.sendKeys(Keys.ESCAPE).build().perform();
	}

	// @Test(priority = 2)
	public void Select_Departure_date_as_1st_date_of_next_month() throws Exception {

		driver.findElement(By.xpath("//div[@class='fsw_inputBox dates inactiveWidget ']")).click();
		Thread.sleep(2000);

		List<WebElement> dateList = driver
				.findElements(By.xpath("//div[@class='DayPicker-Months']/div[2]/div[3]/div[1]/div/div/p[1]"));

		for (int i = 0; i < dateList.size(); i++) {
			String str = dateList.get(i).getText();
			if (str.contains("1")) {
				dateList.get(i).click();
				break;
			}

		}
	}

	// @Test(priority = 3)
	public void Select_ADULTS2_CHILDREN1_INFANTS1() throws Exception {
		driver.findElement(By.xpath("//div[@class='fsw_inputBox flightTravllers inactiveWidget ']")).click();
		Thread.sleep(2000);

		List<WebElement> adultList = driver.findElements(By.xpath("//div[@class='appendBottom20']/ul[1]/li"));
		for (int i = 0; i < adultList.size(); i++) {
			if (adultList.get(i).getText().contains("2")) {
				adultList.get(i).click();
			}
		}

		List<WebElement> childrenList = driver
				.findElements(By.xpath("//div[@class='makeFlex appendBottom25']/div[1]/ul/li"));
		for (int i = 0; i < childrenList.size(); i++) {
			if (childrenList.get(i).getText().contains("1")) {
				childrenList.get(i).click();
			}
		}

		List<WebElement> infantList = driver
				.findElements(By.xpath("//div[@class='makeFlex appendBottom25']/div[2]/ul/li"));
		for (int i = 0; i < infantList.size(); i++) {
			if (infantList.get(i).getText().contains("1")) {
				infantList.get(i).click();
			}
		}

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'APPLY')]")).click();
	}

	// @Test(priority = 4)
	public void Click_on_search() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();

		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(text(),'OKAY, GOT IT!')]")).click();

	}

	// @Test(priority = 5)
	public void Verify_TripType_FROM_TO_DEPART_PASSENGERS_CLASS() throws Exception {

		if (driver.findElement(By.xpath("//div[@class='hsw_inner']/div[1]/div/div")).getText().contains("One Way")) {
			System.out.println("Trip: good");
			System.out.println(driver.findElement(By.xpath("//div[@class='hsw_inner']/div[1]/div/div")).getText());
		}

		if (driver.findElement(By.xpath("//div[@class='hsw_inner']/div[2]/input")).getAttribute("value")
				.contains("Ahmedabad, India")) {
			System.out.println("From: good");
			System.out.println(
					driver.findElement(By.xpath("//div[@class='hsw_inner']/div[2]/input")).getAttribute("value"));
		}

		if (driver.findElement(By.xpath("//div[@class='hsw_inner']/div[4]/input")).getAttribute("value")
				.contains("Pune, India")) {
			System.out.println("To: good");
			System.out.println(
					driver.findElement(By.xpath("//div[@class='hsw_inner']/div[4]/input")).getAttribute("value"));

		}

		if (driver.findElement(By.xpath("//div[@class='hsw_inner']/div[7]/input")).getAttribute("value")
				.contains("4 Travellers, Economy")) {
			System.out.println("passanger&class: good");
			System.out.println(
					driver.findElement(By.xpath("//div[@class='hsw_inner']/div[7]/input")).getAttribute("value"));
		}

	}

	// @Test(priority = 6)
	public void Filter_By_One_Way_Price_and_validate_all_flight_prices_are_between_that_range() throws Exception {
		WebElement slider = driver.findElement(By.xpath("//div[@class='rangeslider__handle']"));

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='rangeslider__handle']")));
		int xwidth = slider.getSize().width;

		act.dragAndDropBy(slider, -(xwidth + 150), 0).release().build().perform();
		slider.click();

		// validation
		String str2 = driver.findElement(By.xpath("//div[@class='listingLhs ']/div[2]/div[1]/ul/li")).getText();
		System.out.println(str2);
		String[] str2Arr = str2.split(" - ");
		List<String> listStr2 = new ArrayList<>();

		for (String word : str2Arr) {
			String word1 = "";
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) >= '0' && word.charAt(i) <= '9') {
					word1 = word1 + word.charAt(i);
				}
			}
			listStr2.add(word1);
		}

		System.out.println(listStr2.get(1));
		Thread.sleep(4000);
//		for (int i = 1; i <= 1; i++) {
//			act.click().build().perform();
//			act.sendKeys(Keys.END).build().perform();
//			Thread.sleep(2000);
//		}

		for (WebElement priceWB : driver
				.findElements(By.xpath("//div[contains(@id,'flight_list_item_')]/div/div[2]/div[4]/div/div[1]/p"))) {
			String word = "";
			for (int i = 0; i < priceWB.getText().length(); i++) {
				if (priceWB.getText().charAt(i) >= '0' && priceWB.getText().charAt(i) <= '9') {
					word = word + priceWB.getText().charAt(i);
				}
			}
			int price = Integer.parseInt(word);
			if (price >= Integer.parseInt(listStr2.get(0)) && price <= Integer.parseInt(listStr2.get(1))) {
				System.out.println("in range");
			}
		}

	}

	// @Test(priority = 7)
	public void Filter_by_Nonstop_and_validate_count() throws Exception {

		// jse.executeScript("window.scrollBy(0,10)", ""); Thread.sleep(2000);;
		driver.findElement(By.xpath("//p[contains(text(),'Stops From ')]/following-sibling::div/div[1]/label/div/p"))
				.click();
		String str = driver
				.findElement(By.xpath("//p[contains(text(),'Stops From ')]/following-sibling::div/div[1]/label/div/p"))
				.getText();
		System.out.println(str);
		String str2 = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				str2 = str2 + ch;
			}
		}
		int NS = Integer.parseInt(str2);

		// check total number of NS flight on that page
		for (int i = 1; i <= NS; i++) {
			act.sendKeys(Keys.END).build().perform();
			Thread.sleep(1000);
		}

		List<WebElement> NSList = driver.findElements(By.xpath("//div[contains(@id,'flight_list_item_')]"));
		System.out.println(NSList.size());
		if (NSList.size() == NS) {
			System.out.println("Verified");
		}

	}

}
