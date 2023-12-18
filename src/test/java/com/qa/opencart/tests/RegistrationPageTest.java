package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetUp() {
		//accountsPage = loginPage.doLogin("manioverasu@gmail.com", "Mani@1234");
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	
	@Test
	public String getRandomEmailID() {	
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	/*
	 * @DataProvider public Object[][] getUserRegistData() { return new Object[][] {
	 * 
	 * {"PeppaNew", "George1","652422544","passw0rd","yes"}, {"PeppaBoy",
	 * "George2","652422545","passw0rd","yes"}, {"PeppaSweety",
	 * "George3","652422546","passw0rd","yes"},
	 * 
	 * };
	 * 
	 * }
	 */
	
	
	@DataProvider
	public Object[][] getUserRegisterDataFromExcel(){
		Object regData[][] = ExcelUtil.getTesData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
		
	}
	
	
	@Test(dataProvider = "getUserRegisterDataFromExcel")
	public void userRegistrationUsingDataProviderTest(String firstName,String lastName,String telePhone,String password,String subscribe) {
		boolean isRegDone = registrationPage.userRegistration(firstName, lastName, getRandomEmailID(), telePhone, password, subscribe);
		Assert.assertTrue(isRegDone);
	}
	
	
	/*
	 * @Test public void userRegistrationTest() { boolean isRegDone =
	 * registrationPage.userRegistration("Peppa",
	 * "George","peppaaaa@gmail.com","652422544","passw0rd","yes");
	 * Assert.assertTrue(isRegDone); }
	 */
}
