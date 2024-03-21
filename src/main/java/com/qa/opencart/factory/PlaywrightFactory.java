package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;									// refrence
	Page page; 														// at the class level this page refrence use below 
	Properties prop;
	
	
	//private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	//private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	//private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	
	
	
	public Page initBrowser(Properties prop) {
		String browserName=prop.getProperty("browser").trim(); 		// trim for removing white space and this we store in browser name variable and it called cross browser concept
		System.out.println("browser Name is:"+ browserName);
		
		playwright=Playwright.create();
		Browser browser = null;
		
		//cross browser logic
		switch(browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			
		case "firefox":
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			
		case "safari":
			browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
			
		case "chrome":
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			
		default:
			System.out.println("please pass the right browser name......");
			break;
	}
		browserContext = browser.newContext();//refrence context initializing here
		page = browserContext.newPage();// contaxt refrence create a new page here so new page method gives you fresh page so this will store in the the page refrence above
		page.navigate(prop.getProperty("url").trim());//this page refrence we use navigate method to get url
		return page;
	}
	public Page getPage()
	{
	return this.page;
	}

// this method is used to initialized the properties from config file so we need to add a path for that so used file input stream class nd create object

	public Properties init_prop() {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return prop;
	}
	
	//Take ScreenShot Method
	public String takeScreenshot() {
	String path = System.getProperty("user.dir")+"/Screenshot/" +System.currentTimeMillis()+".png";
	this.page.screenshot(new Page.ScreenshotOptions()
			.setPath(Paths.get(path))
			.setFullPage(true));
	return path;
	}
}
			