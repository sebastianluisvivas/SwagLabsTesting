package tests;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;
import pages.CheckOutCompletePage;
import pages.CheckOutInformationPage;
import pages.CheckOutOverviewPage;
import pages.LoginPage;
import pages.ProductListingPage;
import pages.ShoppingCartPage;
import utilities.DataExcel;
import utilities.EvidenceCap;


	public class TestToDo {
	String url = "https://www.saucedemo.com/";
	WebDriver driver;
	String EvidenceDirectoryFolder = "..\\SwagLabs\\Evidences\\";
	File screen; // - Screenshot - 
	String DocumentName = "Test Evidences - EvidencesPOM.docx";
	
	String directorioDatos="..\\SwagLabs\\DataLoginFolder\\";
	String nombreArchivoDatos = "dataLogin.xlsx";
	String nombreHoja = "Sheet1";
	
	String email;
    String password;
    private boolean loginSuccessful = false;
	@BeforeSuite
	public void setUp() throws Exception {	
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
	    
	}

	
	
	
	@Test(dataProvider = "Datos Login Excel", description = "login write user and password", priority = 1)
	public void login(String email, String password) throws IOException, InterruptedException, InvalidFormatException, TimeoutException {
	    
	    LoginPage home = new LoginPage(driver); 
	    
	    
		    
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
		    home.enterUsername(email);
		    home.enterPassword(password);
	
		    
		    EvidenceCap.setTittleForDocument(
		        EvidenceDirectoryFolder + DocumentName,
		        "Test Evidences - EvidencesPOM.docx",
		        25);
	
		    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "01_loginScreenshot.png"));
		    EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Login Screenshot");
	
		    home.clickLogin();
		    
		    try {
		        WebDriverWait waitLogout = new WebDriverWait(driver, Duration.ofSeconds(10));
		        waitLogout.until(ExpectedConditions.presenceOfElementLocated(By.id("logout_sidebar_link")));
		        loginSuccessful = true;
		    } catch (TimeoutException e) {
		        loginSuccessful = false;
		    }
		    
	}

	

	@Test (description = "remove product from the shopping cart", dependsOnMethods = "login")
	
	public void removeProduct () throws IOException, InvalidFormatException, InterruptedException {
		if (loginSuccessful) {
			
			ProductListingPage shoppingCart = new ProductListingPage(driver);
			shoppingCart.purchaseProduct();
			shoppingCart.clickOnFullCart();
			screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "02_ShoppCartFull.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName,
												"Shopping cart full after buy a product and before remove it");
		    
			ShoppingCartPage cart = new ShoppingCartPage(driver);
		    cart.clickRemoveButton();
		    
		    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "03_ItemRemove.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName,
												"The item was removed");
		    cart.clickContinueShopping();
			
			
		}
	}
	
	
	

	@DataProvider(name= "Datos Login Excel")
	public Object [][] obtenerDatosExcel() throws Exception {
		return DataExcel.leerExcel(
				directorioDatos + nombreArchivoDatos,
				nombreHoja);
	} 

	
	
	
	
	@Test (description="Verify that the number of products on the PLP equals six.", dependsOnMethods = "login")
	
	public void checkProductCount () throws IOException, InvalidFormatException, InterruptedException {
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "04_ProductsPLP.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName,
											"Products on PLP");
		
	    List<WebElement> productList = driver.findElements(By.className("inventory_item"));
	    
	    
	    Assert.assertEquals(productList.size(), 6, "The number of products is not as expected");

	}
	
	
	
	
	
	@Test (description = "buy a product on the PLP", priority=2, dependsOnMethods = "login")
	public void buyProduct() throws InvalidFormatException, IOException, InterruptedException {
		if (loginSuccessful) {
			ProductListingPage purchase = new ProductListingPage(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    wait.until(ExpectedConditions.elementToBeClickable(purchase.getButtonAddToCart()));
		    
		    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "05_PLPBeforeBuy.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Product listing page (PLP) Before buy a product");
		    
			purchase.purchaseProduct();
		}
	}
	
	
	
	
	
	@Test (description = "after purchase view full cart", priority=3, dependsOnMethods = "login")
	public void viewFullCart() throws InvalidFormatException, IOException, InterruptedException {
		if (loginSuccessful) {
			ProductListingPage fullcart = new ProductListingPage(driver);
			fullcart.clickOnFullCart();
		    ShoppingCartPage cart = new ShoppingCartPage(driver);
		    
		    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "06_ShoppingCartFull.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Shopping cart full after buy a product");
		    
			cart.clickOnCheckOut();
		}
	
  }
	
	
	
	@Test (description = "after click on checkout, complete with personal information", priority=4, dependsOnMethods = "login")
		
		public void personalInformation() throws IOException, InvalidFormatException, InterruptedException {
		if (loginSuccessful) {
			CheckOutInformationPage completePersonalInformation = new CheckOutInformationPage(driver);
			completePersonalInformation.completeInformation();
			
			screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "07_CheckOutInformationPage.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, 
												"CheckOutInformation Page");
		}	
	}
	
	
	
	
	@Test (description = "after complete personal information, confirm checkout", priority=5, dependsOnMethods = "login")
	public void confirmInformationCheckout() throws InvalidFormatException, IOException, InterruptedException {
		if (loginSuccessful) {

			CheckOutOverviewPage confirmPersonalInformation = new CheckOutOverviewPage(driver);
			screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "08_CompletePersonalInformation.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName,
												"Checkout overview page - personal information");
			
			confirmPersonalInformation.clickOnFinishButton();
			
			screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "09_PurchaseCompleted.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Purchase completed");
	}
}
	
	
	
	
	@Test (description = " information completed and purchase finished - back to PLP", priority=6, dependsOnMethods = "login" )
	
	public void checkOutComplete() throws InvalidFormatException, IOException, InterruptedException {
		if (loginSuccessful) {

			CheckOutCompletePage backToPLP = new CheckOutCompletePage(driver);
			backToPLP.clickOnButtonBackHome();
			
			screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "010_PLP_AfterPurchase.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, 
												"Product listing page displayed after a product has been purchased");
			
	}
	
}
	
	

	@Test(description="open leftList and click on logout",priority=7)
	public void logout() throws InterruptedException, IOException, InvalidFormatException {
		if (loginSuccessful) {
			try {
		    ProductListingPage open = new ProductListingPage(driver);
		    open.openLeftList();
		    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "011_openLeftList.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, 
												"Before logOut");
			
			
		    open.buttonLogOut();
		    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "012_logOutComplete.png"));
			EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, 
												"Log out complete");
		    LoginPage loginPage = new LoginPage(driver);
		    
	
	        loginPage.clearFields();
	        
		    
		}	catch (NoSuchWindowException e) {
			System.out.println("La ventana del navegador ya está cerrada.");
		}
		
		}	
	}
	
	
	@AfterSuite
	public void tearDown() {
	    
	        driver.quit();
	    }
	}



