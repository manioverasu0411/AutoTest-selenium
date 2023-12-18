package com.qa.opencart.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By accHeaders = By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("div#search button");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
// page Actions
	
	public String getAccountsPageTitle() {
		String title = elementUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page title: " + title);
		return title;
	}

	public String getAccountPageURL() {
		String URL = elementUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account page URL " + URL);
		return URL;
	}
	
	
	public boolean isLogoutLinkExist() {
	return elementUtil.waitForVisibilityOfElement(logoutLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();	
	}
	
	
	public void logout() {
		if(isLogoutLinkExist()) {
			elementUtil.doClick(logoutLink);
		}
	}
	
	public boolean isSearchFieldExist() {
		return elementUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	
	
	public List<String> getAccountHeaders() {
		List<WebElement> headersList = elementUtil.waitForVisibilityOfElements(accHeaders, AppConstants.SHORT_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			 String text = e.getText();
			 headersValList.add(text);
		}
		
		return headersValList;
	}
	
	
	public SearchResultsPage doSearch(String searchKey) {
		elementUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		elementUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		elementUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
	
	

}
