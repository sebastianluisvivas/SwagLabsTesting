package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	//web elements
	@FindBy(id="user-name")
	WebElement txtUsername;
	
	@FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="login-button")
    WebElement buttonLogin;
	
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //init elements on THIS class on THIS DRIVER
		
	}
	
	
	//methods (actions on page)
	
	public void enterUsername(String username) {
		txtUsername.sendKeys(username);
	}

	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clearFields() {
	    System.out.println("Limpiando campos de texto...");

	    txtUsername.clear();
	    txtPassword.clear();
	}


	public void clickLogin() {
		buttonLogin.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("login-button")));
	}
	
	


	
	

}
