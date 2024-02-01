package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//web elements
	@FindBy(id="user-name")
	WebElement txtUsername;
	
	@FindBy(id="password")
    WebElement txtPassword;

    @FindBy(id="login-button")
    WebElement buttonLogin;
	
	
	//constructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this); //init elements on THIS class on THIS DRIVER
		
	}
	
	
	//methods (actions on page)
	
	public void enterUsername(String username) {
		txtUsername.sendKeys(username);
	}

	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickLogin() {
		buttonLogin.click();
	}
	


}
