package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkout;
	
	@FindBy(xpath="//button[@id='remove-sauce-labs-bolt-t-shirt']")
	WebElement removeButton;
	
	@FindBy(xpath="//button[@id='continue-shopping']")
	WebElement continueShoppingButton;
	
	
	
	
	public ShoppingCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
}
	
	
	public void clickOnCheckOut() {
		checkout.click();
	}
	
	public void clickRemoveButton() {
		removeButton.click();
	}

	public void clickContinueShopping() {
		continueShoppingButton.click();
	}
	
}
