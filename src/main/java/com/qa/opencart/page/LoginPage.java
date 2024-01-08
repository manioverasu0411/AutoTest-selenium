package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// By locators : OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By RegistPageLink = By.linkText("Register");

	// page constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// Page action methods
	@Step("This step is getting login page title")
	public String getLoginPageTitle() {
		String title = elementUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, 5);
		System.out.println("Login page title: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String URL = elementUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, 5);
		System.out.println("Login page URL " + URL);
		return URL;
	}

	public boolean isForgotPasswordExist() {
		return elementUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public boolean isLogoExist() {
		return elementUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	@Step("username is: {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("Credentials are: " + username + " : " + pwd);
		elementUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

	public RegistrationPage navigateToRegisterPage() {
		elementUtil.waitForVisibilityOfElement(RegistPageLink, AppConstants.SHORT_DEFAULT_WAIT).click();
		return new RegistrationPage(driver);

	}

}
