package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOverviewPage {

	@FindBy(id="finish")
	WebElement finishbutton;
	
	
	
	public CheckOutOverviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnFinishButton() {
		finishbutton.click();
	}
	
	
	
	
	
	
	
	
}
