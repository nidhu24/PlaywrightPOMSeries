package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest {

	@Test
	public void homePageTitleTest(){
	String actualTitle = homePage.getHomePageTitle();
	Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
																		//Assert.assertEquals(actualTitle, "Your Store"); // we compare actual title and when we get the url the homepage has this your store name in the url so that we are gonna expected
}																		//we just did the constant so put this ypur store into that constant folder
	@Test
	public void homePageURLTest(){
	String actualURL = homePage.getHomePageUrl();
	Assert.assertEquals(actualURL, prop.getProperty("url"));
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object [][]	{				//2 dimention array
			{"macbook"},
			{"imac"},
			{"samsung"}
		};
	}
	@Test(dataProvider ="getProductData")
	public void SearchTest(String productName) {
	String actualSearchHeader = homePage.doSearch(productName);
	Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}
	
	
	}

