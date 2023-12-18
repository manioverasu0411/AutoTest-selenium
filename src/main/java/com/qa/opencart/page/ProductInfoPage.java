package com.qa.opencart.page;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImgs = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By prodcutPricing = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	private Map<String, String> productMap = new LinkedHashMap<String, String>();
	
	
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	
	public String getProductHeaderName() {
		String productHeaderVal = elementUtil.doElementGetText(productHeader);
		System.out.println(productHeaderVal);
		return productHeaderVal;
	}


	public int getProductImageCount() {
		int imgsCount =  elementUtil.waitForVisibilityOfElements(productImgs,AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product "+ getProductHeaderName()+ " images count : " + imgsCount);
		return imgsCount;
	}
	
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData() {		
		 List<WebElement> metaDataList = elementUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		 for (WebElement e : metaDataList) {
			 String metaData = e.getText(); //Brand: Apple
			 String metaKey = metaData.split(":")[0];
			 String metaValue = metaData.split(":")[1].trim();
			 productMap.put(metaKey, metaValue);
		
		}		
	}
	
	
	private void getProductPriceData() {
		
		 List<WebElement> metaPriceList = elementUtil.waitForVisibilityOfElements(prodcutPricing, AppConstants.MEDIUM_DEFAULT_WAIT);		 
		 String productPrice = metaPriceList.get(0).getText();
		 String productExTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim();		 
		 productMap.put("Price", productPrice);
		 productMap.put("productExTaxPrice", productExTaxPrice);
		 
	}
	
	
	public  Map<String, String> getProductDetails() {
		productMap.put("Product Name", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();		
		return productMap;
	}
	
	

}
