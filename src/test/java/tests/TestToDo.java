package tests;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.CheckOutCompletePage;
import pages.CheckOutInformationPage;
import pages.CheckOutOverviewPage;
import pages.LoginPage;
import pages.ProductListingPage;
import pages.ShoppingCartPage;
import utilities.EvidenceCap;



	public class TestToDo {
	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	String EvidenceDirectoryFolder = "..\\SwagLabs\\Evidences\\";
	File screen; // - Screenshot - 
	String DocumentName = "Test Evidences - EvidencesPOM.docx";
	
	
	
	
	@BeforeSuite
	public void setUp() {
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	
	
	
	@Test (description = "login write user and password", priority=1)
	public void login() throws IOException, InterruptedException, InvalidFormatException {
		//steps to login
		LoginPage home = new LoginPage(driver); //import from package "pages"
		home.enterUsername("standard_user");
		home.enterPassword("secret_sauce");
		
		
		EvidenceCap.setTittleForDocument(
				EvidenceDirectoryFolder + DocumentName,
				"Test Evidences - EvidencesPOM.docx",
				25);
		
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "01_loginScreenshot.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Login Screenshot");
		
		home.clickLogin();
		
	
	}
	
	
	
	
	@Test (description = "buy a product on the PLP", priority=2)
	public void buyProduct() throws InvalidFormatException, IOException, InterruptedException {
		ProductListingPage purchase = new ProductListingPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.elementToBeClickable(purchase.getButtonAddToCart()));
	    
	    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "02_PLPBeforeBuy.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Product listing page (PLP) Before buy a product");
	    
		purchase.purchaseProduct();
	}
	
	
	
	
	@Test (description = "after purchase view full cart", priority=3)
	public void viewFullCart() throws InvalidFormatException, IOException, InterruptedException {
		ProductListingPage fullcart = new ProductListingPage(driver);
		fullcart.clickOnFullCart();
	    ShoppingCartPage cart = new ShoppingCartPage(driver);
	    
	    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "03_ShoppingCartFull.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Shopping cart full after buy a product");
	    
		cart.clickOnCheckOut();
	}
	
	
	
	
	@Test (description = "after click on checkout, complete with personal information", priority=4)
		
		public void personalInformation() {
		CheckOutInformationPage completePersonalInformation = new CheckOutInformationPage(driver);
		completePersonalInformation.completeInformation();
		
	}
	
	
	
	
	@Test (description = "after complete personal information, confirm checkout", priority=5)
	public void confirmInformationCheckout() throws InvalidFormatException, IOException, InterruptedException {
		CheckOutOverviewPage confirmPersonalInformation = new CheckOutOverviewPage(driver);
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "03_CompletePersonalInformation.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Checkout overview page - personal information");
		
		confirmPersonalInformation.clickOnFinishButton();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "04_PurchaseCompleted.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Purchase completed");
	}
	
	
	
	
	
	@Test (description = " information completed and purchase finished - back to PLP", priority=6 )
	
	public void checkOutComplete() throws InvalidFormatException, IOException, InterruptedException {
		CheckOutCompletePage backToPLP = new CheckOutCompletePage(driver);
		backToPLP.clickOnButtonBackHome();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "05_PLP_AfterPurchase.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Product listing page displayed after a product has been purchased");
		
	}
	
	
	
	
	@AfterSuite()
	public void tearDown() {
		driver.close();
	}

}
