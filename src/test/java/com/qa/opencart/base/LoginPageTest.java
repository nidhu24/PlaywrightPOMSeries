package com.qa.opencart.base;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage=homePage.NavigateToLoginPage();
		String actLoginPageTitle=loginPage.getLoginPageTitle();
		System.out.println("Page act title:"+actLoginPageTitle);
		Assert.assertEquals(actLoginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Test(priority=2)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPswLinkExist());

	}
	@Test(priority=3)
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password")));
		
	}
}
