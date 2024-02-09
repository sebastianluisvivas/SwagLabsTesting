package tests;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;


import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CheckOutCompletePage;
import pages.CheckOutInformationPage;
import pages.CheckOutOverviewPage;
import pages.LoginPage;
import pages.ProductListingPage;
import pages.ShoppingCartPage;
import utilities.EvidenceCap;
import utilities.DataExcel;


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
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = new EdgeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
	    
	}

	
	
	
	@Test(dataProvider = "Datos Login Excel", description = "login write user and password", priority = 1)
	public void login(String email, String password) throws IOException, InterruptedException, InvalidFormatException, TimeoutException {
	    // Limpiar los campos de inicio de sesión antes de cada inicio de sesión
	    

	    
	    LoginPage home = new LoginPage(driver); 
	    //ESPERA
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
	    home.enterUsername(email);
	    home.enterPassword(password);

	    // Resto del código para el inicio de sesión
	    EvidenceCap.setTittleForDocument(
	        EvidenceDirectoryFolder + DocumentName,
	        "Test Evidences - EvidencesPOM.docx",
	        25);

	    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "01_loginScreenshot.png"));
	    EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Login Screenshot");

	    home.clickLogin();
	}
	
	
	
	@AfterMethod(description = "logout on each test to start the following")
	public void logout() throws InterruptedException {
		try {
	    ProductListingPage open = new ProductListingPage(driver);
	    open.openLeftList();
	    open.buttonLogOut();
	
	    LoginPage loginPage = new LoginPage(driver);
	    driver.manage().window().maximize();

        loginPage.clearFields();
        Thread.sleep(1000);
	    
	}catch (NoSuchWindowException e) {
		System.out.println("La ventana del navegador ya está cerrada.");
	}
		
		
	}

	
	
	

	@DataProvider(name= "Datos Login Excel")
	public Object [][] obtenerDatosExcel() throws Exception {
		return DataExcel.leerExcel(
				directorioDatos + nombreArchivoDatos,
				nombreHoja);
	} 

	
		
	
	
	
	
	@Test (description = "buy a product on the PLP", priority=2, dependsOnMethods = "login")
	public void buyProduct() throws InvalidFormatException, IOException, InterruptedException {
		ProductListingPage purchase = new ProductListingPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.elementToBeClickable(purchase.getButtonAddToCart()));
	    
	    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "02_PLPBeforeBuy.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Product listing page (PLP) Before buy a product");
	    
		purchase.purchaseProduct();
	}
	
	
	
	
	@Test (description = "after purchase view full cart", priority=3, dependsOnMethods = "login")
	public void viewFullCart() throws InvalidFormatException, IOException, InterruptedException {
		ProductListingPage fullcart = new ProductListingPage(driver);
		fullcart.clickOnFullCart();
	    ShoppingCartPage cart = new ShoppingCartPage(driver);
	    
	    screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "03_ShoppingCartFull.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Shopping cart full after buy a product");
	    
		cart.clickOnCheckOut();
	}
	
	
	
	
	@Test (description = "after click on checkout, complete with personal information", priority=4, dependsOnMethods = "login")
		
		public void personalInformation() {
		CheckOutInformationPage completePersonalInformation = new CheckOutInformationPage(driver);
		completePersonalInformation.completeInformation();
		
	}
	
	
	
	
	@Test (description = "after complete personal information, confirm checkout", priority=5, dependsOnMethods = "login")
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
	
	
	
	
	
	@Test (description = " information completed and purchase finished - back to PLP", priority=6, dependsOnMethods = "login" )
	
	public void checkOutComplete() throws InvalidFormatException, IOException, InterruptedException {
		CheckOutCompletePage backToPLP = new CheckOutCompletePage(driver);
		backToPLP.clickOnButtonBackHome();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File(EvidenceDirectoryFolder + "05_PLP_AfterPurchase.png"));
		EvidenceCap.captureScreenOnDocument(driver, ".\\SwagLabs\\Evidences\\", EvidenceDirectoryFolder + DocumentName, "Product listing page displayed after a product has been purchased");
		
	}
	
	
	
	
	
	
	@AfterSuite
	public void tearDown() {
	    
	        driver.quit();
	    }
	}



