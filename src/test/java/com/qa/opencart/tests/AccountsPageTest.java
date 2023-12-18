package com.qa.opencart.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetUp() {
		//accountsPage = loginPage.doLogin("manioverasu@gmail.com", "Mani@1234");
		accountsPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accountsPage.getAccountPageURL().contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accountsPage.isSearchFieldExist());
	}
	
	@Test
	public void accPageHeaderCountTest() {
		 List<String> actAccPageHeaderList = accountsPage.getAccountHeaders();
		 System.out.println(actAccPageHeaderList);
		 Assert.assertEquals(actAccPageHeaderList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeaderTest() {
		 List<String> actAccPageHeaderList = accountsPage.getAccountHeaders();
		 System.out.println(actAccPageHeaderList.stream().sorted().collect(Collectors.toList()));
		 List<String> sorted_actAccPageList = actAccPageHeaderList.stream().sorted().collect(Collectors.toList());
		 System.out.println(AppConstants.ACCOUNTS_PAGE_HEADERS_LIST.stream().sorted().collect(Collectors.toList()));
		 Assert.assertEquals(sorted_actAccPageList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST.stream().sorted().collect(Collectors.toList()));
		 
	}

	@Test
	public void productSearchTest() {
		searchResultsPage = accountsPage.doSearch("macBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}
	
}
