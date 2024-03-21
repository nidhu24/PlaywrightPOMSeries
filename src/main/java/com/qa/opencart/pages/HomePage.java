package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage  {
	Page page; 											//right now to access the page property we have to make a constructor right now it's null
	//1. String locators i.e. Object repository
	private String search="input[name='search']";
	private String searchIcon="div#search button";
	private String searchPageHeader="div#content h1";
	private String loginLink ="a:text('Login')";
	private String myAccountLink ="a[title='My Account']";
	//2. Constructor
	public HomePage(Page page) {
		this.page=page;
	}
	//3.Action class
	public String getHomePageTitle() {
	String title= page.title();
	System.out.println("Page title:" +title);
		return title;
	}
	public String getHomePageUrl() {
		String url = page.url();
		System.out.println("Page url:" +url);
		return url;	
	}
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String header = page.textContent(searchPageHeader);
		System.out.println("Serch Header:" +header);
		return header;									//after search we need to display that product so in selenium gettex methosd over here content and after that we validate that thing
	}
	public LoginPage NavigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		return new LoginPage(page);	//login class method refer to same object that is page						//TDD approch bcz here once we click on login but we landed on new login page so we need to create those page 
		
		
	}
}
