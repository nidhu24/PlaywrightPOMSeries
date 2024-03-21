package com.qa.opencart.base;   // this class mainly contain 2 methods before nd after

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	PlaywrightFactory pf;
	Page page; 
	protected Properties prop; 
	
	protected HomePage homePage;  
	protected LoginPage loginPage;			//r here we defined protected so base class is chil;d class so parent class can acess it that's why otherwise it shows an error at homepagetest

	
	
	@BeforeTest
	public void setup() {    					//initialized the browser here how we do that so we have to call the playwritefactory class method here with the object of pf
		pf = new PlaywrightFactory();
												//we called init method bczwe share a path of cofig file which has this url, browser all stuff, we stored our url ,browser stuff into it that's why
		prop = pf.init_prop();							//we store a object of prop over here
		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
}
	@AfterTest
	public void tearDown() {
		if(page != null) {
		System.out.println("Page was not null and getting closed.");
		page.context().browser().close();
		}
	}
}
