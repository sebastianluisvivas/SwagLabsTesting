package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.github.javafaker.Faker;



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
        Faker faker = new Faker();

        
        String fakefirstname = faker.name().firstName(); 
        String fakelastname = faker.name().lastName(); 
        String fakepostalcode = faker.address().zipCode(); 
		
		firstname.sendKeys(fakefirstname);
		lastname.sendKeys(fakelastname);
		postalcode.sendKeys(fakepostalcode);
		continuebutton.click();
	}






}
