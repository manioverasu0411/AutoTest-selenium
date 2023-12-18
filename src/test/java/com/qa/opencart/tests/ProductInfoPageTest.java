package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		//accountsPage = loginPage.doLogin("manioverasu@gmail.com", "Mani@1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData(){
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1},
		};
	}
	
	
	@Test(dataProvider = "getSearchData")
	public void productImagesCountTestdataProvider(String searchKey,String productName, int imageCount) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		 Assert.assertEquals(productInfoPage.getProductImageCount(),imageCount);
		
	}
	
	@Test
	public void productImagesCountTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		 Assert.assertEquals(productInfoPage.getProductImageCount(),4);
		
	}
	
	@Test
	public void productDetailsTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		 Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		 
		 System.out.println(productDetailsMap);
		 
		 softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		 softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		 softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		 softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		 
		 softAssert.assertEquals(productDetailsMap.get("Price"), "$2,000.00");
		 softAssert.assertEquals(productDetailsMap.get("productExTaxPrice"), "$2,000.00");
		 softAssert.assertAll();

	}

}
