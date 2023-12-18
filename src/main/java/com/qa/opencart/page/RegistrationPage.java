package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By successMsge = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By RegistPageLink = By.linkText("Register");

	
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	public boolean userRegistration(String firstName,String lastName,String email,String telePhone,String password,String subscribe) {
		
		elementUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telePhone, telePhone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscribeYes);
		}else {
			elementUtil.doClick(subscribeNo);
		}
		
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueBtn);
		
		String successMessage = elementUtil.waitForVisibilityOfElement(successMsge, AppConstants.SHORT_DEFAULT_WAIT).getText();
		System.out.println(successMessage);
		
		if(successMessage.contains(AppConstants.REGISTER_SUCCESS_MESSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(RegistPageLink);
			return true;
		}else {
			return false;
		}
	}

}
