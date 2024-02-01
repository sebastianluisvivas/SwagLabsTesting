package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkout;
	
	
	
	
	public ShoppingCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
}
	
	
	public void clickOnCheckOut() {
		checkout.click();
	}
	
}
