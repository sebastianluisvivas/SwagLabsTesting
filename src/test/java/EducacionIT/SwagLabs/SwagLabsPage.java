package EducacionIT.SwagLabs;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.EvidenceCap;

//import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class SwagLabsPage {
	String url = "https://www.saucedemo.com/";
	String EvidenceDirectoryFolder = "..\\SwagLabs\\Evidences\\";
	WebDriver driver;  //global variable 
	File screen; // - Screenshot - 
	String nombreDocumento = "Evidencias de prueba - Automation Practice.docx";
	
	
	
	
	@BeforeSuite
	public void beforeTest() {
		//--  browser setup  --
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	@Test (description="login username and password",priority=1)
	public void login() throws IOException, InterruptedException {
		// --- login ---
		WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
		username.sendKeys("standard_user");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");
		
		// |           _____    ______________ SCREEN AND DOCUMENT ___________________________ //
		EvidenceCap.setTittleForDocument(
				EvidenceDirectoryFolder + nombreDocumento,
				"Documento de evidencias - Automation Practice",
				20);
		
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "01_loginScreenshot.jpg"));
		
		WebElement buttonLogin = driver.findElement(By.id("login-button"));
		buttonLogin.sendKeys(Keys.ENTER);
	}
	
	@Test(description="Test Case - buy products",priority = 2)
	
	public void buyProducts () throws IOException, InvalidFormatException, InterruptedException {
	
	// --- buy process ---
	
	driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
	driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
	// |           _____    ______________ SCREEN AND DOCUMENT ___________________________ //
	EvidenceCap.captureScreenOnDocument(
	driver,
	EvidenceDirectoryFolder + "img.jpg",
	EvidenceDirectoryFolder + nombreDocumento,
	"Pantalla principal");
	screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "02_PurchaseScreenshot.jpg"));
	// --         explicit wait   -- 
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
	// --      ---       ---
	
	driver.findElement(By.id("shopping_cart_container")).click();
	// |           _____    ______________ SCREEN AND DOCUMENT ___________________________ //
	EvidenceCap.captureScreenOnDocument(
			driver,
			EvidenceDirectoryFolder + "img.jpg",
			EvidenceDirectoryFolder + nombreDocumento,
			"Shopping cart container");
	screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "03_cartfull.jpg"));
	driver.findElement(By.id("checkout")).click();
	
	
	//--- after buy, checkout information ---
	driver.findElement(By.id("first-name")).sendKeys("Sebastian");
	driver.findElement(By.id("last-name")).sendKeys("Vivas");
	driver.findElement(By.id("postal-code")).sendKeys("91218");
	driver.findElement(By.id("continue")).click();
	driver.findElement(By.id("finish")).click();
	driver.findElement(By.id("back-to-products")).click();
		
	}
	
	
	@Test(description="TestCase 2 - sort products by price",priority = 3)
	
	public void sortProductsByPrice() {
	
		
	Select option = new Select(driver.findElement(By.tagName("select")));
	option.selectByValue("lohi");
	
	}
	
	
	@Test(priority = 4)
	
	public void productCount() throws IOException, InvalidFormatException, InterruptedException {
		
		List<WebElement> products = driver.findElements(By.className("inventory_item"));
		// |           _____    ______________ SCREEN AND DOCUMENT ___________________________ //
		EvidenceCap.captureScreenOnDocument(
				driver,
				EvidenceDirectoryFolder + "img.jpg",
				EvidenceDirectoryFolder + nombreDocumento,
				"PLP Product listing page");
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "04_plp.jpg"));
		Assert.assertEquals(products.size(), 6, "La cantidad de productos no es la esperada");
	}
	
	
	
	
	@Test(priority = 5)
	
	public void aboutPanel () {
		
		
		//explicit wait 1
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#react-burger-menu-btn")));
		
		
		driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();;
		driver.findElement(By.xpath("//a[@id='about_sidebar_link']")).click();
	}
	
	@AfterSuite
	public void afterSuite() {
		driver.close();
	}
	
	
	
}
