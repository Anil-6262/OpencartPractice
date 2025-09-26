package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	public HomePage(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement myAccountLink;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Register')]")
	WebElement registerLink;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Login')]")
	WebElement loginLink;

	public void clickMyAccount()
	{
		myAccountLink.click();
	}

	public void clickRegister()
	{
		registerLink.click();
	}

	public void clickLogin()
	{
		loginLink.click();
	}


}
