package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@name='email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@name='confirm']")
	WebElement txtConfirmPassword;


	@FindBy(xpath = "//input[@name='agree']")
	WebElement privacyCheckBox;


	@FindBy(xpath = "//input[@type='submit']")
	WebElement continueBtn;

	@FindBy(xpath = "//div[@id='content']//h1[contains(text(),'Your Account Has Been Created!')]")
	WebElement confirmationMsg;

	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String cpwd)
	{
		txtConfirmPassword.sendKeys(cpwd);
	}

	public void clickPrivacyPolicy()
	{
		privacyCheckBox.click();
	}

	public void clickContinue()
	{
		continueBtn.click();
	}

	public String getConfirmationMsg()
	{
		try
		{
			return (confirmationMsg.getText());
		}catch(Exception e)
		{
			return (e.getMessage());
		}
	}



















}
