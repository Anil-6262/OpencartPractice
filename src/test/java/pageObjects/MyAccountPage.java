package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']//h2[contains(text(),'My Account')]")
	WebElement msgHeading;

	@FindBy(xpath = "//div[@class='list-group']//a[contains(text(),'Logout')]")
	WebElement logoutLink;

	public boolean isMyAccountPageExists()
	{
		try
		{
			return (msgHeading.isDisplayed());
		}catch(Exception e)
		{
			return false;
		}
	}

	public void clickLogout()
	{
		logoutLink.click();
	}

}
