package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	
	public ProductInfoPage selectProduct(String productName) {
		elementUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.SHORT_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
		
	}
	
	
	

}
