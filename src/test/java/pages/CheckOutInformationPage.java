package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInformationPage {

	@FindBy(id="first-name")
	WebElement firstname;

	@FindBy(id="last-name")
	WebElement lastname;
	
	@FindBy(id="postal-code")
	WebElement postalcode;

	@FindBy(id="continue")
	WebElement continuebutton;

	
	public CheckOutInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
}


	public void completeInformation() {
		firstname.sendKeys("Sebastian");
		lastname.sendKeys("Vivas");
		postalcode.sendKeys("91218");
		continuebutton.click();
	}






}
