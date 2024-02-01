package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListingPage {

	//WebElements to use
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
	WebElement buttonAddToCart;
	
	public WebElement getButtonAddToCart() {
	    return buttonAddToCart;
	}
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")
	WebElement fullcart;
	
	
	
	//Constructor
	public ProductListingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//Methods (actions)
	public void purchaseProduct() {
		buttonAddToCart.click();
	}
	
	public void clickOnFullCart() {
		fullcart.click();
	}
	
}
 