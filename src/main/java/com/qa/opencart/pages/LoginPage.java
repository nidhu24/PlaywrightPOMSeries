package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	  Page page; 
	 
	 // 1.String Locators - Object Refrence
	 private String emailId= "//input[@id=\'input-email\']";
	 private String password= "//input[@id=\'input-password\']";
	 private String loginBtn ="//input[@value='Login']";
	 private String forgotPwdLink ="(//a[normalize-space()='Forgotten Password'])[1]";
	 private String logoutLink = "//html[1]/body[1]/div[2]/div[1]/aside[1]/div[1]/a[13]";
	 
	 
	//2.constructor of login page clas to access the methods
	 public LoginPage(Page page) {
			this.page=page;

}
	//3.Action class
	 public String getLoginPageTitle() {
		 return page.title();
	 }
	 public boolean isForgotPswLinkExist() {
		return page.isVisible(forgotPwdLink);
		
	 }
	 public boolean doLogin(String appUserName, String appPassword) {
		 System.out.println("app creds:" +appUserName + ":" +appPassword);
		 page.fill(emailId, appUserName);
		 page.fill(password, appPassword);
		 page.click(loginBtn);
		 if(page.isVisible(logoutLink)){
			 System.out.print("User is successfully.....");
			 return true;
		 }
		 return false;
		 
	 }
}
