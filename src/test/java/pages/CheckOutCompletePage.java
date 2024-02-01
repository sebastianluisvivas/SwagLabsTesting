package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutCompletePage {

	@FindBy(id="back-to-products")
	WebElement backHome;
	
	
	public CheckOutCompletePage (WebDriver driver) {
		PageFactory.initElements(driver, this); 
	}
	
	public void clickOnButtonBackHome() {
		backHome.click();
	}
	
	
}
