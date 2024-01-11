package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

@Epic("EPIC ID: 1720411: Design open cart login page ")
@Story("US 101: Login page features")
@Feature("F50: Feature login pgae")
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page title test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();	
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login Page URL test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
    public void getLoginPageURLTest(){
        String actURL = loginPage.getLoginPageURL();
        Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
    }
	
	@Description("Verify forgot password link...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
    public void isForgotPasswordExistTest(){
        Assert.assertTrue(loginPage.isForgotPasswordExist());
    }

	@Description("Verify Application logo test...")
	@Severity(SeverityLevel.MINOR)
    @Test(priority = 4)
    public void isLogoExistTest(){
        Assert.assertTrue(loginPage.isLogoExist());
    }

	@Description("User is able to login with valid credentials ..")
	@Severity(SeverityLevel.BLOCKER)
    @Test(priority = 5)
    public void doLoginTest(){
       accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
       Assert.assertTrue(accountsPage.isLogoutLinkExist());
       
    }
	
	
	
}
