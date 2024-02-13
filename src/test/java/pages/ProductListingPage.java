package pages;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ProductListingPage {

	//WebElements to use
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
	WebElement buttonAddToCart;
	
	WebDriver driver;
	
	public WebElement getButtonAddToCart() {
	    return buttonAddToCart;
	}
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")
	WebElement fullcart;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement leftList;
	
	@FindBy(id="logout_sidebar_link")
	WebElement buttonLogOut;
	
	
	
	//Constructor
	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Methods (actions)
	public void purchaseProduct() {
		buttonAddToCart.click();
	}
	
	public void clickOnFullCart() {
		fullcart.click();
	}
	
	public void openLeftList() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.elementToBeClickable(leftList)).click();
		;
	
}
	
	public void buttonLogOut() {
		buttonLogOut.click();
	}
	
	
	
	
	
}